(ns fitokr.api.core
  (:require [fitokr.api.objectives :as objectives]
            [fitokr.api.users :as users]
            [fitokr.api.system :as system]
            [fitokr.api.departments :as departments]))

(def health-route
  ["/health-check"
   {:get (fn [_]
           {:status 200
            :body {:ping "pong"}})}])

(def routes
  [["/api"
    health-route
    system/system-routes
    objectives/objective-routes
    users/user-routes
    departments/departments-routes]])