(ns fitokr.api.system
  (:require [fitokr.models.settings.handler :as settings]
            [fitokr.models.specs :as spec]))


(def system-routes
  ["/system"
   ["/state" {:get {:summary "fetch current system state..."
                    :handler settings/handle-get-settings}}]
   ["/setup" {:port {:summary "initalize system, create company and init cycle..."
                     :handler settings/handle-system-initialize
                     :parameters {:body ::spec/create-initial-data}}}]])