(ns blog.frontend.main)
;;require module as
;;(:require ["highlightjs" :as hljs])
;;load script as
;;["/js/file.js" :as dep]

(defn get-param [p]
  (.get (new js/URLSearchParams (-> js/window .-location .-search)) p))

(defn set-param [p v]
  (let [u (new js/URLSearchParams (-> js/window .-location .-search))]
    (.set u  p v)
    (let [query-str (.toString u)
          new-relative-path (str (-> js/window .-location .-pathname) "?" query-str)]
      (.pushState js/history nil "" new-relative-path))))

(defn get-hash []
  (apply str (-> js/window .-location .-hash rest )))

(defn set-hash [string]
  (-> js/window .-location .-hash (set! string)))

(comment
  (get-param "a")
  (set-param "a" "456")
  (get-hash)
  (set-hash "blaq123"))

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

(defn view-mode
  "view mode can be :list or :grid"
  [mode]
  (let [articles-grid-el (-> "articles-grid grid-component" class->els (.item 0))
        articles-list-el (-> "articles-grid list-component" class->els (.item 0))
        list-button-el (-> "list" class->els (.item 0))
        grid-button-el (-> "grid" class->els (.item 0))
        list? (= mode :list)]
    (class-remove "hide" (if list? articles-list-el articles-grid-el))
    (class-add "hide" (if list? articles-grid-el articles-list-el))
    (class-remove "not-selected" (if list? list-button-el grid-button-el))
    (class-add "not-selected" (if list? grid-button-el list-button-el))
    (set-param "mode" (name mode))))

(defn init-grid []
  (let [lightboxes (.getElementsByClassName js/document "open-lightbox")
        close-elem (.getElementsByClassName js/document "close")
        list-button-el (-> "list" class->els (.item 0))
        grid-button-el (-> "grid" class->els (.item 0))
        articles-grid-el (-> "articles-grid grid-component" class->els (.item 0))
        articles-list-el (-> "articles-grid list-component" class->els (.item 0))
        mode (keyword (get-param "mode"))]
    (when mode (view-mode mode))
    (.addEventListener grid-button-el "click" (fn []
                                                (view-mode :grid)))
    (.addEventListener list-button-el "click" (fn []
                                                (view-mode :list)))

    (-> lightboxes js/Array.from (.forEach (fn [el]
                                             (.addEventListener el "click" (open-modal-evl el)))))
    (-> close-elem js/Array.from (.forEach (fn [el]
                                             (.addEventListener el "click" (close-modal-evl el)))))
    (.addEventListener js/document "click" close-modal-click-outside-evl true)))


(defn ^:export init []
  (init-grid))
