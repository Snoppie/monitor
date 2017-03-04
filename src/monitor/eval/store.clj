(ns monitor.eval.store
  (:require [clj-time.core :as time]
            [clj-time.coerce :as tc]))

(def db (atom {:cameras {}}))

(defn- add-timestamp [data]
  (assoc data :time (tc/to-date (time/now))))

(defn- update-camera! [{:keys [id data]}]
  (let [with-time (add-timestamp data)]
    (swap! db update-in [:cameras id] conj with-time)))

;; @db
;; (update-camera! {:id 0 :data {:data "Muuuh!"}})
;; (update-camera! {:id 1 :data {:data "Oi!"}})

(defn process-update [{:keys [params]}]
  (update-camera! params))
