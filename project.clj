(defproject monitor "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [clj-time "0.13.0"]
                 [compojure "1.5.2"]
                 [ring/ring-defaults "0.2.3"]
                 [ring/ring-json "0.4.0"]
                 [jumblerg/ring.middleware.cors "1.0.1"]
                 [com.cognitect/transit-clj "0.8.297"]
                 [org.clojure/data.json "0.2.6"]
                 [com.cemerick/friend "0.2.3"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-ancient "0.6.10"]]
  :ring {:handler monitor.handler/app
         :nrepl {:start? true}}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
