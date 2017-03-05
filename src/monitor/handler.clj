(ns monitor.handler
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response wrap-json-body]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :refer [response]]
            [cognitect.transit :as transit]
            [monitor.cameras :as cameras])
  (:use [clojure.repl :refer [source]]))

(defroutes app-routes
  (GET "/" [] (response {:status :ok
                         :data {:quote "Back when PHP had less than 100 functions and the function hashing mechanism was strlen()"
                                :author "The author of PHP"}}))
  (GET "/camera/:id{[0-9]+}" {:keys [params]}
       (response (cameras/by-id (:id params))))
  (GET "/camera/:id{[0-9]+}/latest" {:keys [params]}
       (response (cameras/latest-by-id (:id params))))
  (GET "/cameras" _
       (response (cameras/all)))
  (GET "/heartbeat" _
       (response (cameras/get-heartbeat)))
  (POST "/update" {:keys [params]}
        (cameras/process-update params)
        (response {:status :ok}))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-json-body {:keywords? true :bigdecimals? true})
      wrap-keyword-params
      wrap-json-params
      wrap-json-response
      (wrap-cors identity)))

;; (require '[clojure.repl :refer :all])
(source wrap-cors)
