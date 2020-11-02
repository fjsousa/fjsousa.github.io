(ns blog.template.single-page
  (:require [clojure.tools.reader.edn :as edn]
            [blog.template.shared :as shared]))

(defn main [[page-key {{:keys [title date tags subtitle thumb thumb-alt slug link-rewrite] :as metadata} :metadata content :content}]]
  [page-key
   (shared/main metadata (into [:article {:class "article-body"}] content))])


(comment
  (->> (main [:index {:metadata {:title "A title"
                                        :subtitle "Some description."
                                        :tags ["blog" "open-data"]
                                        :date (blog.core/string->date "2020 10 24")}
                             :content [
                                       [:h1 "Remote work in the age of Covid-19!"]
                                       [:p "Bla bla."]

                                       [:h2 "1 - Desk setup for the space deprived"]
                                       [:p "Bla bla."]
                                       [:p
                                        [:img {:alt "A minimalist affordable remote desk setup.", :src "assets/img/remote/desk_s.jpg", :title "A minimalist affordable remote desk setup, complete with a cat holder."}]]]}])
       last
       hiccup.core/html
       (spit (str (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string :root) "/index.html")))
  )
