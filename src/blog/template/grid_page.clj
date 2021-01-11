(ns blog.template.grid-page
  (:require [clojure.tools.reader.edn :as edn]
            [blog.template.shared :as shared]
            [clj-time.format :as f]))

(def config (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string))

(defn article
  [res [page-key {{:keys [video-arrow title date tags subtitle thumb thumb-alt slug link-rewrite grid-media-item grid-img link-copy] :as metadata} :metadata content :content}]]
  (if ((:skip config) page-key)
    res
    (into res
          [[:article {:class "masonry-item article-item open-lightbox", :data-lightbox (str (+ 1 (/ (count res) 2)))}
            (when thumb
              [:figure (when video-arrow {:class "grid-video"})
               [:img {:src (format "assets/img/%s/%s" (name page-key) thumb), :alt thumb-alt}]])
            [:div {:class "excerpt"}
             [:p title]]]

           [:div {:class "lightbox", :data-lightbox (str (+ 1 (/ (count res) 2)))}
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
                    [:img {:src (format "assets/img/%s/%s" (name page-key) grid-img), :alt "article image"}]])
                 (when grid-media-item
                   [:div {:class "video-wrapper"}
                    grid-media-item])
                 [:p subtitle]]]]]
             (let [options (cond-> {:class "view-article"}
                             link-rewrite              (assoc :href link-rewrite
                                                              :target "_blank")
                             (nil? link-rewrite) (assoc :href (str (name page-key) ".html")))
                   copy (if link-copy
                          (str link-copy " →")
                          "Read full article →")]
               [:a options copy])]]])))

(defn main [pages]
  (let [pages (->> pages
                   (into [])
                   #_(sort-by #(-> % last :metadata :date))
                   #_reverse
                   (reduce article []))
        content [:div {:class "col-12"}
                 #_[:div {:class "articles-actions"}
                  [:div {:class "tags"}
                   [:span "Tags:"]
                   [:a {:href "#"} "All"]
                   [:a {:href "#"} "Personal"]
                   [:a {:href "#"} "Software"]
                   [:a {:href "#"} "Experiments"]]
                  [:div {:class "view"}
                   [:span "View:"]
                   [:a {:href "#", :class "list"} "List"]
                   [:a {:href "#", :class "grid"} "Grid"]]]
                 (into
                  [:div {:class "masonry-grid articles-grid"}] pages )]]
   (shared/main {:title (:head-title config) :subtitle (:meta-description config)} content)))

#_(->> (main (blog.core/parse-markdowns (-> "src/blog/config.edn" slurp edn/read-string :root)))
     hiccup.core/html
     #_(spit (str (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string :root) "/index.html")))
