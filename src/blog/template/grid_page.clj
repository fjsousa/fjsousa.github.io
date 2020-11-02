(ns blog.template.grid-page
  (:require [clojure.tools.reader.edn :as edn]
            [blog.template.shared :as shared]))

(def title "Index grid title - add me to config")
;;todo change other places subtitle->description
(def description "Index grid description - add me to config")


(defn main [pages]
  (let [content (into [:article {:class "article-body"}] "content")]
   [:index
    (shared/main {:title title :subtitle description} content)]))

(comment)
(->> (main (blog.core/parse-markdowns (-> "src/blog/config.edn" slurp edn/read-string :root)))
     last
     hiccup.core/html
     (spit (str (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string :root) "/index.html")))
