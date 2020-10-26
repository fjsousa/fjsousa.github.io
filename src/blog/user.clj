(ns blog.user
  (:require [blog.core :as core]
            [clojure-watch.core :refer [start-watch]]
            [clojure.tools.reader.edn :as edn]
            [clojure.tools.namespace.repl :as tools-ns]))

(tools-ns/disable-reload!)

(def root (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string :root))
(def base-url (-> "src/blog/config.edn" slurp clojure.tools.reader.edn/read-string :base-url))

(def app-state nil)

(defn stop []
  (let [{:keys [stop-markdown]} app-state]
    (println :stopping)
    (stop-markdown)
    (alter-var-root #'app-state (fn [_] nil))))

(defn refresh-restart []
  (stop)
  (tools-ns/set-refresh-dirs
   "src/blog/")
  (tools-ns/refresh :after 'blog.user/init))

(defn watch-markdown []
  (println "\nBuilding markdown pages once...")
  (core/output! root)
  (start-watch [{:path  (str root "/pages/")
                 :event-types [:create :modify :delete]
                 :bootstrap (fn [path] (println (format "Watching %s ..." path)))
                 :callback (fn [event filename] (do (core/output! root)
                                                    (println (format "File %s with event %s." filename event))))
                 :options {:recursive true}}]))

(defn init []
  (println :init)
  (alter-var-root #'app-state (fn [_] {:stop-markdown (watch-markdown)})))

(comment

  app-state

  (core/output! root)

  (core/parse-markdowns root)

  (init)
  (refresh-restart)
  (stop)

  (stop))
