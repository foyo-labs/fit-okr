(ns fitokr.models.departments.handler
  (:require [clojure.spec.alpha :as s]
            [fitokr.models.departments.db :as db]
            [fitokr.models.specs :as spec]
            [ring.util.response :as rr]
            [taoensso.timbre :as log]))


(defn handle-get-all-depts 
  "Query deptements with parent-id"
  [{:keys [env parameters]}]
  (let [{:keys [db]} env
        query-params (get-in parameters [:query])
        result (db/get-department-hierarchy db (or (:parent-id query-params) 0))]
    (rr/response result)))