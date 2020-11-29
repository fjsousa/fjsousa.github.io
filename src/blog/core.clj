(ns blog.core
  (:require [blog.template.single-page :as single-page]
            [blog.template.grid-page :as grid-page]
            [clj-time.core :as t]
            [clojure-watch.core :refer [start-watch]]
            [clojure.spec.alpha :as s]
            [clojure.set]
            [clojure.string :as string]
            [clojure.tools.reader.edn :as edn]
            [hiccup.core :as hiccup]
            [hickory.core :as hickory]
            [markdown.core :as md]
            [org.satta.glob :as glob]
            [clojure.java.shell :refer [sh]]
            [clojure.walk :refer [postwalk]]))

(defn get-pages
  "root: markdown folder path. returns a glob - java.io object"
  [root]
  (->> (str root "/pages/*.md") glob/glob))

(defn string->date
  [string]
  (when string
    (->> (string/split string #" ")
         (map #(Integer. %))
         (apply t/date-time))))

(defn string->tags
  [string]
  (if string
    (-> string first (string/split #","))
    (prn "Error: Tags string is nil")))

(defn not-nil? [x] (not (nil? x)))

(s/def ::title not-nil?)
(s/def ::date not-nil?)
(s/def ::tags not-nil?)
(s/def ::subtitle not-nil?)
(s/def ::thumb not-nil?)
(s/def ::thumb-alt not-nil?)
(s/def ::link-rewrite not-nil?)
(s/def ::html not-nil?)
(s/def ::link-rewrite not-nil?)
(s/def ::link-copy not-nil?)

(s/def ::metadata
  (s/keys :req-un [::title ::date ::tags ::subtitle]
          :opt-un [::thumb ::thumb-alt ::link-rewrite ::link-rewrite ::link-copy]))

(s/def :blog/page
  (s/keys :opt-un []
          :req-un [::metadata ::html]))

(defn highlight-parse
  [language code]
  (let [code (-> code (clojure.string/escape char-escape-string)
                 (clojure.string/replace  "'" "\\'")
                 (clojure.string/replace "&quot;" "\\\""))
        raw-string (str "console.log(require('highlightjs').highlight('" language "','" code "').value)")
        {:keys [out exit err]} (sh "node" "-e" raw-string)
        _ (when (not (= 0 exit))
            (do (println err)
                (throw (Exception. "Error highlighting code. Check logs"))))]

    (->>
     out
     hickory/parse-fragment
     (mapv hickory/as-hiccup) concat (into [:div]))))


(defn highlight-code [form]
  (let [language (-> form (nth 2) second :class)
        language (or language "bash" #_"javascript")
        code (-> form (nth 2) last)]
    (update-in form [2 2] #(highlight-parse language %))))

(defn walk-fn [form]
  (if (and (vector? form)
             (= :pre (first form))
             (= :code (-> form (nth 2) first)))
    (highlight-code form)
    form))

(defn parse-page
  [page]
  (let [page-key (->> page .getName (re-find #"([a-z\d-]+)") last keyword)
        page-val (-> page
                     slurp
                     (md/md-to-html-string-with-meta :inhibit-separator "%"))
        _ (when (not (s/valid? :blog/page page-val))
            (do (println (s/explain :blog/page page-val))
                (throw (Exception. (format "Markdown not valid for '%s' Check logs." page-key)))))
        page-parsed (-> page-val
                        (update-in [:html] #(->> % hickory/parse-fragment (mapv hickory/as-hiccup) concat (into [:div])))
                        (update-in [:html] #(postwalk walk-fn %))
                        (clojure.set/rename-keys {:html :content})
                        (update-in [:metadata :title]  first)
                        (update-in [:metadata :subtitle] first)
                        (update-in [:metadata :date] #(-> % first string->date))
                        (update-in [:metadata :tags] #(string->tags %))
                        (update-in [:metadata :thumb] first)
                        (update-in [:metadata :thumb-alt] first)
                        (update-in [:metadata :link-rewrite] first)
                        (update-in [:metadata :grid-img] first)
                        (update-in [:metadata :link-rewrite] identity)
                        (update-in [:metadata :link-copy] #(when % (first %)))
                        (update-in [:metadata :grid-media-item] #(when % (-> % first hickory/parse-fragment first hickory/as-hiccup)))
                        (update-in [:content] (fn [[div & rest-el]]
                                                rest-el)))]

    [page-key page-parsed]))

(md/md-to-html-string-with-meta (slurp (first (glob/glob (str (-> "src/blog/config.edn" slurp edn/read-string :root) "/pages/available-styles.md")))) :inhibit-separator "%")
(parse-page (first (glob/glob (str (-> "src/blog/config.edn" slurp edn/read-string :root) "/pages/available-styles.md"))))


(defn parse-markdowns
  "Loads markdown files into an unparsed datastructure
  {:some-blog-post {:title :subtitle :tags ...}"
  [root]
  (->> (get-pages root)
       (map parse-page)
       (into {})))


(defn add-grid [pages]
  (assoc pages :index (grid-page/main pages)))

(defn map-pages [pages]
  (conj (map single-page/main (dissoc pages :index)) [:index (:index pages)]))

(defn build-hiccup
  [root]
  (->> root
       parse-markdowns
       add-grid
       map-pages
       (into {})))

(defn output!
  [root]
  (doseq [[k page] (build-hiccup root)]
    (let [path (str root "/" (name k) ".html")]
      (spit path (hiccup/html page)))))

(defn watch-fn []
  (let [{:keys [root base-url]} (-> "src/blog/config.edn" slurp edn/read-string)]
    (println "\nBuilding markdown pages")
    (output! root) ;;build once, then watch
    (start-watch [{:path  (str root "/pages/")
                   :event-types [:create :modify :delete]
                   :bootstrap (fn [path] (println "Starting to watch " path))
                   :callback (fn [event filename] (do (output! root)
                                                      (println (str "File " filename " with event " event))))
                   :options {:recursive true}}])))

(defn -main
  [& args]
  (watch-fn ))

(comment
  (output! (-> "src/blog/config.edn" slurp edn/read-string :root)))
