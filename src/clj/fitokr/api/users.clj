(ns fitokr.api.users
  (:require [fitokr.models.users.handler :refer [handle-login]]
            [fitokr.router.middleware :refer [authorize-middleware]]
            [fitokr.models.specs :as spec]))


(def user-routes
  ["/users"
   ["/login" {:post {:summary "login with email and password"
                     :handler handle-login
                     :parameters {:body ::spec/login-user}}}]
   ["/" {:post {:summary "create user info by admin"}
         :get {:summary "query users by paramaters"}
         :delete {:summary "disable user"}}
    :middleware [authorize-middleware ["ADMIN"]]]])