(ns monitor.eval.store)

(def db (atom {:cameras [{:id 0
                          :data nil}]}))

(defn- update-camera! [{:keys [id data] :as vals}]
  (swap! db assoc-in [:cameras id] vals))

;; @db
;; (update-camera! {:id 0 :data {:message "Muuuh!"}})
;; (update-camera! {:id 1 :data {:message "Oi!"}})

(defn process-update [{:keys [params]}]
  (update-camera! params)
  (println @db))
