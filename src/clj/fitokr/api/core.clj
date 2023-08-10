(ns fitokr.api.core
  (:require [fitokr.api.objectives :as objectives]
            [fitokr.api.users :as users]))

(def health-route
  ["/health-check"
   {:get (fn [_]
           {:status 200
            :body {:ping "pong"}})}])

(def routes
  [["/api"
    health-route
    objectives/objective-routes
    users/user-routes]])