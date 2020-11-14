(ns blog.frontend.main
  (:require ["highlightjs" :as hljs]
            ["masonry-layout" :as masonry]))
;;load script as
;;["/js/file.js" :as dep]

(defn open-modal-evl [el]
  (fn [_]
    (let [i (.getAttribute el "data-lightbox")
          data-lightbox (.querySelector js/document (str ".lightbox[data-lightbox=\"" i "\"]") )]
      (.add (.-classList data-lightbox ) "open"))))

(defn close-modal-evl [el]
  (fn [_]
    (-> el .-parentElement .-parentElement .-classList (.remove "open"))
    (js/console.log el)))

(defn init-grid []
  (let [grid-elem (.querySelector js/document ".masonry-grid")
        lightboxes (.getElementsByClassName js/document "open-lightbox")
        close-elem (.getElementsByClassName js/document "close")]
    (new masonry grid-elem #js {:itemSelector ".masonry-item", :fitWidth true, :gutter 30})
    (-> lightboxes js/Array.from (.forEach (fn [el]
                                             (.addEventListener el "click" (open-modal-evl el)))))
    (-> close-elem js/Array.from (.forEach (fn [el]
                                             (.addEventListener el "click" (close-modal-evl el)))))))


(defn ^:export init []
  (.initHighlightingOnLoad hljs)
  (init-grid))
