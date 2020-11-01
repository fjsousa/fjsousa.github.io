(ns blog.frontend.main
  (:require ["highlightjs" :as hljs]
            ))

(defn ^:export init []
  (.initHighlightingOnLoad hljs))


;;load script as
;;["/js/file.js" :as dep]

;;todo remove npm highlight.js
