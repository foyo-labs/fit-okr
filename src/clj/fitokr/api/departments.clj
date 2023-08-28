(ns fitokr.api.departments
  (:require [fitokr.models.departments.handler :refer [handle-get-all-depts]]
            [fitokr.models.specs :as spec]))


(def departments-routes
  ["/departments" {:get {:summary "query all departments by condition"
                         :handler handle-get-all-depts}}])