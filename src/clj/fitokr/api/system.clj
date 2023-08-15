(ns fitokr.api.system
  (:require [fitokr.models.settings.handler :as settings]
             [fitokr.models.specs :as spec]))
  
  
  (def system-routes
    ["/system"
     ["/state" {:get {:summary "fetch current system state..."
                      :handler settings/handle-get-settings}}]])