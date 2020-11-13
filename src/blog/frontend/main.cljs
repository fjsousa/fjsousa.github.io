(ns blog.frontend.main
  (:require ["highlightjs" :as hljs]
            ["masonry-layout" :as masonry]))
;;load script as
;;["/js/file.js" :as dep]

(defn ^:export init []
  (.initHighlightingOnLoad hljs))

(def grid-elem (.querySelector js/document ".masonry-grid"))

(def grid-masonry (new masonry grid-elem #js {:itemSelector ".masonry-item", :fitWidth true, :gutter 30}))

(.querySelector js/document ".open-lightbox")

(.forEach (js/Array.from (.getElementsByClassName js/document "open-lightbox"))
          (fn [el]
            (.addEventListener el "click" (fn [_]
                                            (let [lightbox-nr (.getAttribute el "data-lightbox")
                                                  data-lightbox (.querySelector js/document (str ".lightbox[data-lightbox=\"" lightbox-nr "\"]") )]
                                              (.add (.-classList data-lightbox ) "open")
                                              )))))
