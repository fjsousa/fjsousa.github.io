(ns blog.core
  (:gen-class)
  (:require [clj-time.core :as t]
            [clojure-watch.core :refer [start-watch]]
            [clojure.spec.alpha :as s]
            [clojure.string :as string]
            [clojure.tools.reader.edn :as edn]
            [hiccup.core :as hiccup]
            [hickory.core :as hickory]
            [markdown.core :as md]
            [org.satta.glob :as glob]))

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
(s/def ::slug not-nil?)
(s/def ::link-rewrite not-nil?)
(s/def ::html not-nil?)

(s/def ::metadata
  (s/keys :req-un [::title ::date ::tags ::subtitle ::slug]
          :opt-un [::thumb ::thumb-alt ::link-rewrite]))

(s/def :blog/page
  (s/keys :opt-un []
          :req-un [::metadata ::html]))

(defn parse-page
  [page]
  (let [page-key (->> page .getName (re-find #"([a-z\d-]+)") last keyword)
        page-val (-> page
                     slurp
                     (md/md-to-html-string-with-meta :inhibit-separator "%"))
        _ (when (not (s/valid? :blog/page page-val))
            (do (println (s/explain :blog/page page-val))
                (throw (Exception. (format "Markdown not valid for '%s' Check logs." page-key)))))
        page-parsed (-> page-val (update-in [:html] #(->> % hickory/parse-fragment (mapv hickory/as-hiccup) concat (into [:div])))
                        (clojure.set/rename-keys {:html :content})
                        (update-in [:metadata :title]  first)
                        (update-in [:metadata :subtitle] first)
                        (update-in [:metadata :date] #(-> % first string->date))
                        (update-in [:metadata :tags] #(string->tags %))
                        (update-in [:metadata :thumb] first)
                        (update-in [:metadata :thumb-alt] first)
                        (update-in [:metadata :slug] first)
                        (update-in [:metadata :link-rewrite] first))]

    [page-key page-parsed]))

(defn parse-markdowns
  "Loads markdown files into an unparsed datastructure
  {:some-blog-post {:title :subtitle :tags ...}"
  [root]
  (->> (get-pages root)
       (map parse-page)
       (into {})))

(defn single-page [[page-key {{:keys [title date tags subtitle thumb thumb-alt slug link-rewrite]} :metadata}]]
  [page-key
   [:html
    {:lang "en"}
    [:head
     [:meta
      {:content "width=device-width, initial-scale=1, maximum-scale=1" :name "viewport"}]
     [:meta {:name "description" :content "description-copy"}]
     [:title title]
     [:link {:href "assets/css/font.css", :rel "stylesheet"}]
     [:link {:href "assets/css/font-awesome.min.css" :rel "stylesheet"}]
     [:link {:href "assets/css/style.css" :rel "stylesheet"}]
     [:link {:href "assets/css/github.css" :rel "stylesheet"}]
     #_[:link {:type "text/css" :href "//cdn-images.mailchimp.com/embedcode/slim-081711.css" :rel "stylesheet"}]
     [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-MML-AM_CHTML"
               :async "async":type "text/javascript"}]
     [:script {:src "https://plausible.io/js/plausible.js"
               :async "defer" :data-domain "datajournal.co.uk"}]]

    [:body
     [:p page-key]]]])

(defn build-hiccup
  [root]
  (->> root
       parse-markdowns
       (map single-page)
       (into {})))

(defn output!
  [root]
  (doseq [[k page] (build-hiccup root)]
   (let [path (str root "/" k ".html")]
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
  (watch-fn {}))