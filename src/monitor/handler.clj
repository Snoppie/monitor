(ns monitor.handler
  (:require [monitor.config :refer [api-defaults]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults]])
  (:use [clojure.repl :only (source)]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/" [] "post")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
