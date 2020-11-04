(ns blog.template.grid-page
  (:require [clojure.tools.reader.edn :as edn]
            [blog.template.shared :as shared]))

(def title "Index grid title - add me to config")
;;todo change other places subtitle->description
(def description "Index grid description - add me to config")


(defn article
  [res [page-key {{:keys [title date tags subtitle thumb thumb-alt slug link-rewrite] :as metadata} :metadata content :content}]]
  (into res
        [[:article {:class "masonry-item article-item open-lightbox", :data-lightbox (str (+ 1 (/ (count res) 2)))}
          (when thumb
            [:figure
             [:img {:src (format "assets/img/%s/%s" (name page-key) thumb), :alt thumb-alt}]])
          [:div {:class "excerpt"}
           [:p title]]]
         [:div {:class "lightbox", :data-lightbox (str (+ 1 (count res)))}
          [:div {:class "inner"}
           [:a {:href "#", :class "close"}]
           [:div {:class "container"}
            [:div {:class "row"}
             [:div {:class "col-12"}
              [:article {:class "article-body"}
               [:h2 {:class "h1"}
                [:strong title]]
               [:p subtitle]]]]]
           [:a {:href (str page-key ".html"), :class "view-article", :target "_blank"} "Read full article →"]]]]))

(defn main [pages]
  (let [content [:div {:class "col-12"}
                 (into
                  [:div {:class "masonry-grid articles-grid"}] (reduce article [] pages))]]
   [:index
    (shared/main {:title title :subtitle description} content)]))


(->> (main (blog.core/parse-markdowns (-> "src/blog/config.edn" slurp edn/read-string :root)))
     last
     hiccup.core/html
     (spit (str (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string :root) "/index.html")))
