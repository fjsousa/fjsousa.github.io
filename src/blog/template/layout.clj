(ns blog.template.layout
  (:require [clj-time.format :as f]
            [clj-time.coerce :as c]
            [clj-rss.core :as rss]))

(defn menu [dates]
  (->> dates
       (mapcat
              (fn [{:keys [:slug :date :short-name]}]
                [[:a {:href (str slug ".html")} [:span.nav-item-title short-name]]
                 [:span.nav-item-date  (f/unparse (f/formatters :year-month-day) date)]
                 [:br]]))
       (into [:div#menu ])))

(defn modal-menu [dates]
  (->> dates
       (mapcat
              (fn [{:keys [:slug :date :short-name]}]
                [[:a {:href (str slug ".html")} [:span.modal-nav-item-title short-name]]
                 [:span.modal-nav-item-date (str "(" (f/unparse (f/formatters :year-month-day) date) ")")]
                 [:br]]))
       (into [:div.modal-menu-items ])))

(defn front-page-hiccup
  [blog-structure]
  [:div.index-container
   (->> blog-structure
        (into '())
        (sort-by (fn [[slug {:keys [:date]}]] date))
        reverse
        (map (fn [[k {:keys [:title :subtitle :date :tags :thumb :slug]}]]
               [:div.index-item
                [:div.index-title [:a {:href (str slug ".html")} title]]
                (when tags
                  [:div.index-tags (map (fn [tag]
                                          (if (<= 7 (count tag))
                                            [:span.index-tag.nowrap tag]
                                            [:span.index-tag tag])) tags)])
                [:div.index-date (f/unparse (f/formatters :year-month-day) date)]
                (when thumb
                  (into [:div.index-thumb] thumb))
                [:div.index-description subtitle]
                [:div.continue-reading [:a {:href (str slug ".html")} "Continue Reading »"]]])))])

(def description-copy "A place for tech and numerical experimentalism. Be welcome.")

(def short-description
  [:p description-copy])

(def tracker-info
  [:a {:style "color: rgba(150,150,150,0.8);text-decoration: underline;"  :target "_blank" :href "https://blog.mozilla.org/firefox/what-is-a-web-tracker/"} "This website is tracker free!"])


(defn layout [title description content menu modal-menu]
  [:html
   {:lang "en"}
   [:head
    [:meta
     {:content "width=device-width, initial-scale=1, maximum-scale=1" :name "viewport"}]
    [:meta {:name "description" :content description-copy}]
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
    [:div#desktop-source.source [:a {:target "_blank" :href "https://github.com/data-journal/data-journal.github.io"} "Source"]]
    [:div#side-bar

     ;;modal
     [:div#menu-modal.menu-modal
      [:div.modal-content
       [:span.menu-close "×"]
       modal-menu]]
     ;;modal

     [:button#menu-btn [:i.fa.fa-bars {:aria-hidden "true"}]]
     [:div#header

      [:div#title [:a {:href "/"} "Data Journal"]]
      [:div#author [:div#name "Flávio Sousa"]

       [:div#social-media
        [:a {:target "_blank" :href "https://medium.com/@fjsousa"}
         [:i.fa.fa-medium]]
        [:a {:target "_blank" :href "https://pt.linkedin.com/pub/flávio-sousa/3a/a06/770/"}
         [:i.fa.fa-linkedin]]
        [:a {:target "_blank", :href "https://twitter.com/verysocialsousa"}
         [:i.fa.fa-twitter]]
        [:a {:target "_blank", :href "https://github.com/fjsousa"}
         [:i.fa.fa-github]]]]]

     menu

     [:div#footer-bar

      [:div#mc_embed_signup
       [:div.rss-feed [:a {:href "feed.xml"}  [:i.fa.fa-rss {:aria-hidden "true"}]]]
       #_[:form#mc-embedded-subscribe-form.validate
        {:target "_blank"
         :action "//github.us10.list-manage.com/subscribe/post?u=5b26850668dc6b3f84778ca5e&id=cb5f4eedfe"
         :name "mc-embedded-subscribe-form"
         :novalidate "",
         :method "post"}
        [:div#mc_embed_signup_scroll
         [:label {:for "mce-EMAIL"} "Subscribe to the mailing list"]
         [:input#mce-EMAIL.email
          {:required "",
           :value "",
           :type "email",
           :placeholder "email address",
           :name "EMAIL"}]
         ;;real people should not fill this in and expect good things - do not remove this or risk form bot signups
         [:div
          {:style "position: absolute; left: -5000px;"}
          [:input
           {:value "",
            :type "text",
            :name "b_5b26850668dc6b3f84778ca5e_cb5f4eedfe",
            :tabindex "-1"}]]
         [:div.clear
          [:input#mc-embedded-subscribe.button
           {:value "Subscribe", :type "submit", :name "subscribe"}]]
         [:div#mobile-source.source [:a {:target "_blank" :href "https://github.com/data-journal/data-journal.github.io"} "Source"]]]]]

      [:div#about
       [:p#about-title "About:"]
       short-description
       [:br]
       tracker-info
       ]]]

    [:div#container
     [:div#content

      content

      #_twitter-el

      #_disqus-el]]

    [:div#footer]
    #_[:script
     "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n  ga('create', 'UA-53583095-1', 'auto');\n  ga('send', 'pageview');\n"]
    [:script {:src "https://code.jquery.com/jquery-1.11.2.min.js"}]
    [:script {:type "text/javascript" :src "assets/src/modal.js"}]
    [:script {:type "text/javascript" :src "assets/src/highlight.pack.js"}]
    [:script "hljs.initHighlightingOnLoad();"]
    ;;cellular automata post
    [:script {:type "text/javascript" :src "assets/src/rags.js"}]
    [:script {:type "text/javascript" :src "assets/src/fgm-main.js"}]
    ]])

(defn sitemap [root blog-structure]
  (->> blog-structure
       (map (fn [[k {:keys [slug]}]] (str root "/" slug ".html")))
       (reduce (fn [map-str page] (str map-str page "\n")) (str root "\n"))))

(def index-title
  (str "Data Journal - " short-description))

(def index-description
  "This is a blog about Numerical Algorithms and Software Engineering. I write about experiments I did or
  ongoing work which I think is relevant to the subject. Subscribe to the newsletter to get the latest updates. I won't blog that frequently so don't worry, you won't get spammed!")


(defn rss-feed
  [base-url blog-structure]
  (->> blog-structure
       (map (fn [[k {:keys [:slug :title :subtitle :date :tags :thumb]}]]
              {:title title
               :link (str base-url "/" slug ".html")
               :description subtitle
               :category tags
               :pubDate (c/to-date date)}))
       (filter (fn [{:keys [:title]}] (not (nil? title))))
       (apply rss/channel-xml)))


(defn single-page [{:keys [title description content]}]
  [:html
   {:lang "en"}
   [:head
    [:meta
     {:content "width=device-width, initial-scale=1, maximum-scale=1" :name "viewport"}]
    [:meta {:name "description" :content description-copy}]
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
    [:div#desktop-source.source [:a {:target "_blank" :href "https://github.com/data-journal/data-journal.github.io"} "Source"]]
    [:div#side-bar

     ;;modal
     [:div#menu-modal.menu-modal
      [:div.modal-content
       [:span.menu-close "×"]
       modal-menu]]
     ;;modal

     [:button#menu-btn [:i.fa.fa-bars {:aria-hidden "true"}]]
     [:div#header

      [:div#title [:a {:href "/"} "Data Journal"]]
      [:div#author [:div#name "Flávio Sousa"]

       [:div#social-media
        [:a {:target "_blank" :href "https://medium.com/@fjsousa"}
         [:i.fa.fa-medium]]
        [:a {:target "_blank" :href "https://pt.linkedin.com/pub/flávio-sousa/3a/a06/770/"}
         [:i.fa.fa-linkedin]]
        [:a {:target "_blank", :href "https://twitter.com/verysocialsousa"}
         [:i.fa.fa-twitter]]
        [:a {:target "_blank", :href "https://github.com/fjsousa"}
         [:i.fa.fa-github]]]]]

     menu

     [:div#footer-bar

      [:div#mc_embed_signup
       [:div.rss-feed [:a {:href "feed.xml"}  [:i.fa.fa-rss {:aria-hidden "true"}]]]
       #_[:form#mc-embedded-subscribe-form.validate
        {:target "_blank"
         :action "//github.us10.list-manage.com/subscribe/post?u=5b26850668dc6b3f84778ca5e&id=cb5f4eedfe"
         :name "mc-embedded-subscribe-form"
         :novalidate "",
         :method "post"}
        [:div#mc_embed_signup_scroll
         [:label {:for "mce-EMAIL"} "Subscribe to the mailing list"]
         [:input#mce-EMAIL.email
          {:required "",
           :value "",
           :type "email",
           :placeholder "email address",
           :name "EMAIL"}]
         ;;real people should not fill this in and expect good things - do not remove this or risk form bot signups
         [:div
          {:style "position: absolute; left: -5000px;"}
          [:input
           {:value "",
            :type "text",
            :name "b_5b26850668dc6b3f84778ca5e_cb5f4eedfe",
            :tabindex "-1"}]]
         [:div.clear
          [:input#mc-embedded-subscribe.button
           {:value "Subscribe", :type "submit", :name "subscribe"}]]
         [:div#mobile-source.source [:a {:target "_blank" :href "https://github.com/data-journal/data-journal.github.io"} "Source"]]]]]

      [:div#about
       [:p#about-title "About:"]
       short-description
       [:br]
       tracker-info
       ]]]

    [:div#container
     [:div#content

      content

      #_twitter-el

      #_disqus-el]]


    [:div#footer]
    #_[:script
     "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n  ga('create', 'UA-53583095-1', 'auto');\n  ga('send', 'pageview');\n"]
    [:script {:src "https://code.jquery.com/jquery-1.11.2.min.js"}]
    [:script {:type "text/javascript" :src "assets/src/modal.js"}]
    [:script {:type "text/javascript" :src "assets/src/highlight.pack.js"}]
    [:script "hljs.initHighlightingOnLoad();"]
    ;;cellular automata post
    [:script {:type "text/javascript" :src "assets/src/rags.js"}]
    [:script {:type "text/javascript" :src "assets/src/fgm-main.js"}]
    ]])
