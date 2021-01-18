(ns blog.template.grid-page
  (:require [clojure.tools.reader.edn :as edn]
            [blog.template.shared :as shared]
            [clj-time.format :as f]))

(def config (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string))

(defn article-item [[page-key {{:keys [video-arrow title date tags subtitle thumb thumb-alt slug link-rewrite grid-media-item grid-img link-copy] :as metadata} :metadata content :content idx :idx}]]
  [:article {:class "masonry-item article-item open-lightbox"  :data-lightbox (str idx) :tags (apply str (interpose "," (conj tags "All")))}
   (when thumb
     [:figure (when video-arrow {:class "grid-video"})
      [:img {:src (format "/assets/img/%s/%s" (name page-key) thumb), :alt thumb-alt}]])
   [:div {:class "excerpt"}
    [:p title]]])

(defn article-lightbox [[page-key {{:keys [video-arrow title date tags subtitle thumb thumb-alt slug link-rewrite grid-media-item grid-img link-copy] :as metadata} :metadata content :content idx :idx}]]
  [:div {:class "lightbox", :data-lightbox (str idx)}
   [:div {:class "inner"}
    [:a {:href "#", :class "close"}]
    [:div {:class "container"}
     [:div {:class "row"}
      [:div {
             :class "col-12"}
       [:article {:class "article-body"}
        [:div {:class "date"} (f/unparse  (f/formatter "d MMMM, yyyy") date)]
        [:h2 {:class "h1"}
         [:strong title]]
        (when grid-img
          [:div {:class "video-wrapper"}
           [:img {:src (format "/assets/img/%s/%s" (name page-key) grid-img), :alt "article image"}]])
        (when grid-media-item
          [:div {:class "video-wrapper"}
           grid-media-item])
        [:p subtitle]]]]]
    (let [options (cond-> {:class "view-article"}
                    link-rewrite              (assoc :href link-rewrite
                                                     :target "_blank")
                    (nil? link-rewrite) (assoc :href (str (name page-key) "/")))
          copy (if link-copy
                 (str link-copy " →")
                 "Read full article →")]
      [:a options copy])]])

(defn article-actions [tags]
  [:div {:class "articles-actions"}
   (into [:div {:class "tags"}
          [:span "Tags:"]
          [:a {:href "" :class "tag not-selected" :tag "All"} "All"]]
         (mapv (fn [t] [:a {:href "" :class "tag not-selected" :tag t} t]) tags))
   [:div {:class "view"}
    [:span "View:"]
    [:a {:href "#", :class "list not-selected"} "List"]
    [:a {:href "#", :class "grid"} "Grid"]]])

(defn grid [list grid lightbox tags]
  [:div {:class "col-12"}
   (article-actions tags)
   (into [:div] lightbox)
   (into
    [:div {:class "masonry-grid articles-grid list-component list hide"}] list)
   (into
    [:div {:class "masonry-grid articles-grid grid-component"}] grid) ])

(defn main [pages]
  (let [indexed-pages (->> pages
                           (reduce-kv (fn [res k v]
                                        (if ((:skip config) k)
                                          res
                                          (assoc res k (assoc v :idx (count res))))) {}))
        list (->>
              indexed-pages
              (sort-by #(-> % last :metadata :date))
              reverse
              (mapv article-item))

        grid-items (->>
                    indexed-pages
                    (sort-by #(-> % first))
                    (mapv article-item))

        tags (->> indexed-pages
                  (reduce-kv (fn [res k v]
                               (into res (-> v :metadata :tags))) [])
                  distinct)

        lightbox-items (->>
                        indexed-pages
                        (mapv article-lightbox))]
    (shared/main {:title (:head-title config) :subtitle (:meta-description config)} (grid list grid-items lightbox-items tags))))

#_(->> (main (blog.core/parse-markdowns (-> "src/blog/config.edn" slurp edn/read-string :root)))
     #_hiccup.core/html
     #_(spit (str (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string :root) "/index.html")))

#_(main (blog.core/parse-markdowns (-> "src/blog/config.edn" slurp edn/read-string :root)))
