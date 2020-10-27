(ns blog.frontend.main
  (:require ["highlight.js" :as hljs]))

(defn ^:export init []
  :ok)

(.initHighlightingOnLoad hljs)
