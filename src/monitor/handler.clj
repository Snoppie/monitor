(ns monitor.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-params]])
  (:use [clojure.repl :refer [source]]))

(defroutes app-routes
  #_(GET "/" [] {:status "ok"
               :data "Back when PHP had less than 100 functions and the function hashing mechanism was strlen()"})
  (POST "/" request
        request)
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      #_(wrap-defaults api-defaults)
      wrap-keyword-params
      wrap-json-params))
