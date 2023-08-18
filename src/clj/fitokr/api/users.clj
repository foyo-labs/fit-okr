(ns fitokr.api.users
  (:require [fitokr.models.users.handler :refer [handle-login, handle-create-user]]
            [fitokr.models.specs :as spec]))


(def user-routes
  ["/users"
   ["/login" {:post {:summary "login with email and password"
                     :handler handle-login
                     :parameters {:body ::spec/login-user}} }]
   ["/sign" {:post {:summary "sign up user with email and password"
                    :handler handle-create-user
                    :parameters {:body ::spec/register-user}}}]])