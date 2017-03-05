(ns monitor.cameras
  (:require [clj-time.core :as time]
            [clj-time.coerce :as tc]
            [clojure.data.json :as json]))

(def db (atom {:cameras {}}))

(defn- add-timestamp [data]
  (assoc data :time (tc/to-date (time/now))))

(defn- update-camera! [{:keys [id data] :as entry}]
  (let [with-time (add-timestamp entry)]
    (swap! db update-in [:cameras id] conj with-time)))

;; @db
;; (dotimes [_ 5] (update-camera! {:id 0 :data {:data "Muuuh!"}}))
;; (update-camera! {:id 1 :data {:data "Oi!"}})


;; -----------------------------------------------------------------------------
;; Reponse to requests

;; POST
(defn process-update [params]
  (update-camera! params))

;; GET
(defn by-id [id]
  (get-in @db [:cameras id]))

(defn latest-by-id [id]
  (first (get-in @db [:cameras 0])))

(defn all []
  (:cameras @db))

(defn get-heartbeat []
  (let [file (:data (first (get-in @db [:cameras 0])))]
    {:status :ok
     :data {:heartbeat (:bpm file)
            :face (:face file)}}))
