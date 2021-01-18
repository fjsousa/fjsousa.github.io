(ns blog.template.shared)

(def config (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string))

(def social (:social config))

(defn main [{:keys [title date tags subtitle thumb thumb-alt slug link-rewrite]} content]
  [:html {:lang "en" :dir "ltr"}
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:content "width=device-width, initial-scale=1, maximum-scale=1" :name "viewport"}]
    [:meta {:name "description" :content subtitle}]
    [:title title]
    [:link {:href "/assets/css/main.css", :rel "stylesheet"}]
    [:script {:src "https://plausible.io/js/plausible.js"
              :async "defer" :data-domain "flaviosousa.co"}]]

   [:body

    [:header
     [:div {:class "container"}
      [:div {:class "row"}
       [:div {:class "col-12 col-sm-4 col-md-3"}
        [:p {:class "h1"}
         [:strong
          [:a {:href "/"} (:title config)]]]
        [:p (:sub-title config)]]
       [:div {:class "col-12 col-sm-4 col-md-5"}
        [:div {:class "about"}
         [:p
          [:i "Personal website."
           [:br]"Past projects, blogging."]]]]
       [:div {:class "col-12 col-sm-4"}
        [:div {:class "social-links"}
         [:div [:a {:href "/about/"} [:strong "About me"]]]
         [:a {:href (:medium social), :target "_blank"}
          [:img {:src "/assets/img/icon-medium.svg"}]]
         [:a {:href (:linkedin social), :target "_blank"}
          [:img {:src "/assets/img/icon-linkedin.svg"}]]
         [:a {:href (:twitter social), :target "_blank"}
          [:img {:src "/assets/img/icon-twitter.svg"}]]
         [:a {:href (:github social), :target "_blank"}
          [:img {:src "/assets/img/icon-github.svg"}]]]]]]]
    [:main
     [:div {:class "container"}
      [:div {:class "row"}
       content]]]
    [:footer
     [:div {:class "container"}
      [:div {:class "row"}
       [:div {:class "col-12"}
        [:p
         [:small
          [:a {:target "_blank" :href "https://blog.mozilla.org/firefox/what-is-a-web-tracker/"} "I'm tracker free! "]
          [:span " • " [:a {:href "https://github.com/fjsousa/fjsousa.github.io", :target "_blank"} "View on Github"]]]]]]]]
    [:a {:href "#", :class "back-to-top"}]]
   [:script {:type "text/javascript" :src "/assets/js/main.js"}]
   [:script {:type "text/javascript" :src "/assets/js/rags.js"}]
   [:script {:type "text/javascript" :src "/assets/js/fgm-main.js"}]]
  )
