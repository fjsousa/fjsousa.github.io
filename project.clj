(defproject blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/tools.reader "1.3.3"]
                 [org.clojure/spec.alpha "0.2.187"]
                 [org.clojure/clojure "1.10.0" :exclusions
                  [org.clojure/clojurescript]]
                 [markdown-clj "1.10.5"]
                 [clj-glob "1.0.0"]
                 [hickory "0.7.1"]
                 [hiccup "1.0.5"]
                 [ring "1.8.2"]
                 [clj-time "0.15.2"]
                 [org.clojure/tools.namespace "1.0.0"]
                 [clj-rss "0.2.6"]
                 [clojure-watch "LATEST"]]
  :source-paths     ["src"]
  :jvm-opts ["-Xmx6g" "-Duser.timezone=UTC"]
  :java-source-paths ["java"]
  :repl-options {:init-ns blog.user}
  :plugins [[lein-ancient "0.6.15"]]
  :main ^:skip-aot blog.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
