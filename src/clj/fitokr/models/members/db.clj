(ns fitokr.models.members.db
  (:require [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [fitokr.services.db]))

(defn create 
  "创建成员数据"
  [db data]
  (q/db-query-one! db {:insert-into :members
                       :values [data]}))

(defn query-members-by-dept-id 
  "根据部门编号查询成员列表，仅编号"
  [db dept_id]
  (q/db-query! db {:select [:*]
                   :from [:members]
                   :where [:= :dept_id dept_id]}))

(defn query-members-with-info-by-dept-id
  "根据部门编号查询成员信息，包括用户及部门主要信息"
  [db dept_id]
  (q/db-query! db {:select [:u.id [:u.name :user_name] :m.dept_id [:d.name :dept_name]]
                   :from [[:members :m]]
                   :where [:= :dept_id dept_id]
                   :join [[:users :u] [:= :m.user_id :u.id] [:departments :d] [:= :m.dept_id :d.id]]}))

(comment
  (require '[clojure.spec.alpha :as s]
           '[clojure.spec.gen.alpha :as gen]
           '[fitokr.services.config :refer [read-config]])

  (def db (:postgres/db (ig/init (dissoc (read-config) :reitit/routes :http/server))))

  (def member-1 {:dept_id 5 :user_id 1})
  (create db member-1)
  (query-members-by-dept-id db 5)
  (query-members-with-info-by-dept-id db 5)
  )