(ns monitor.handler
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response wrap-json-body]]
            [ring.util.response :refer [response]]
            [cognitect.transit :as transit]
            [monitor.eval.store :as store])
  (:use [clojure.repl :refer [source]]))

(defroutes app-routes
  (GET "/" [] (response {:status :ok
                         :data {:quote "Back when PHP had less than 100 functions and the function hashing mechanism was strlen()"
                                :author "The author of PHP"}}))
  (POST "/update" request
        (store/process-update request)
        (response {:status :ok}))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-json-body {:keywords? true :bigdecimals? true})
      wrap-keyword-params
      wrap-json-params
      (wrap-json-response app-routes)))
