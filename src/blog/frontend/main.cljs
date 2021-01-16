(ns blog.frontend.main)
;;require module as
;;(:require ["highlightjs" :as hljs])
;;load script as
;;["/js/file.js" :as dep]

(defn open-modal-evl [el]
            (fn [_]
              (let [i (.getAttribute el "data-lightbox")
                    data-lightbox (.querySelector js/document (str ".lightbox[data-lightbox=\"" i "\"]") )]
                (.add (.-classList data-lightbox ) "open"))))

(defn close-modal-evl [el]
  (fn [_]
    (-> el .-parentElement .-parentElement .-classList (.remove "open"))))

(defn close-modal-click-outside-evl [ev]
  (let [modal-el (.querySelector js/document ".inner")
        is-outside-modal? (not (.contains modal-el (.-target ev)))
        modal-open? (not (nil? (.querySelector js/document ".lightbox.open *")))]
    (when (and modal-open? is-outside-modal?)
      (-> (.getElementsByClassName js/document "lightbox") js/Array.from (.forEach (fn [el]
                                                                                     ;;true means the event is handled by this event handler
                                                                                     ;;before being passed to the elements below

                                                                                     (-> el .-classList (.remove "open"))))))))
(defn class->els
  [class]
  (.getElementsByClassName js/document class))

(defn class->array
  [class]
  (js/Array.from (class->els class)))

(defn class-remove [class el]
  (-> el .-classList (.remove class)))

(defn class-add [class el]
  (-> el .-classList (.add class)))

(defn init-grid []
  (let [lightboxes (.getElementsByClassName js/document "open-lightbox")
        close-elem (.getElementsByClassName js/document "close")
        list-button-el (-> "list" class->els (.item 0))
        grid-button-el (-> "grid" class->els (.item 0))
        article-item-els (-> "article-item" class->array)]
    #_(.addEventListener grid-button-el "click" (fn []
                                                 (-> article-item-els (.forEach (partial class-remove "list")))))
    #_(.addEventListener list-button-el "click" (fn []
                                                (-> article-item-els (.forEach (partial class-add "list")))))
    (-> lightboxes js/Array.from (.forEach (fn [el]
                                             (.addEventListener el "click" (open-modal-evl el)))))
    (-> close-elem js/Array.from (.forEach (fn [el]
                                             (.addEventListener el "click" (close-modal-evl el)))))
    (.addEventListener js/document "click" close-modal-click-outside-evl true)))


(defn ^:export init []
  (init-grid))
