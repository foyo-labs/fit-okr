(ns fitokr.api.users
  (:require [fitokr.models.users.handler :as users]
            [fitokr.models.specs :as spec]))


(def user-routes
  ["/users"
   ["/login" {:post {:summary "login with email and password"
                     :handler users/handle-login
                     :parameters {:body ::spec/login-user}} }]
   ["/sign" {:post {:summary "sign up user with email and password"
                    :handler users/handle-sign
                    :parameters {:body ::spec/register-user}}}]])