(ns blog.shadow-repl
  (:require
   [shadow.cljs.devtools.server :as server]
   [shadow.cljs.devtools.api :as shadow]
   [shadow.cljs.devtools.cli]))


(defn watch-build []
  (shadow/watch :frontend {:verbose true}))

(defn stop-build []
  (shadow/stop-worker :frontend))

(defn release-build []
  (shadow/release :frontend {:verbose true}))


(defn cljs-repl
  ([]
   (cljs-repl :frontend))
  ([build-id]
   (shadow/watch build-id {:verbose true})
   (shadow/nrepl-select build-id)))

(server/start!)

(comment
  (watch-build)
  (release-build)
  (shadow/nrepl-select :frontend)
  (stop-build))
