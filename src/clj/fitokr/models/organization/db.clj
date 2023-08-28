(ns fitokr.models.organization.db
  (:require [fitokr.utils.query :as q]))


(defn get-all-organizations [db]
  (q/db-query! db {:select [:*]
                   :from [:organizations]}))

(defn get-org-by-domain [db domain]
  (q/db-query-one! db {:select [:*]
                       :from [:organizations]
                       :where [:= :domain domain]}))

(defn create-organization [db data]
  (q/db-query-one! db {:insert-into :organizations
                       :values [data]}))



(comment
  
  (def entity {:name "花儿与少年"
               :description "组织简介"
               :domain "http://localhost:3000"
               :email "37505218@qq.com"
               :active true})
  
  )