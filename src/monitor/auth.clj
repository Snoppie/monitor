(ns monitor.auth
  (:require [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])))

(def users {"root" {:username "root"
                    :password (creds/hash-bcrypt "snoppie123")
                    :roles #{::admin}}
            "user" {:username "user"
                    :password (creds/hash-bcrypt "snoppie123")
                    :roles #{::user}}})

(def wrap-authentication
  (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn users)
                        :workflows [(workflows/interactive-form)]}))
