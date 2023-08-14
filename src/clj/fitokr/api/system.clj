(ns fitokr.api.system
  (:require [fitokr.models.users.handler :as users]
             [fitokr.models.specs :as spec]))
  
  
  (def system-routes
    ["/system"
     ["/state" {:get {:summary "fetch current system state..."
                      :handler users/handle-login
                      :parameters {:body ::spec/login-user}}}]])