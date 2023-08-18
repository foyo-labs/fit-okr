(ns fitokr.models.departments.db
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [fitokr.services.db]))


(defn get-all-by-own [db own]
  (q/db-query! db {:select [:*]
                   :from [:departments]
                   :where [:= :own own]}))


(defn create [db data]
  (q/db-query-one! db {:insert-into :departments
                       :values [data]}))


(defn find-child-by-id [db id]
  (q/db-query-one! db {:select [:*]
                       :from [:departments]
                       :where [:= :parent id]}))


(defn get-department-hierarchy [db parent-id]
  (let [root-departments (q/db-query! db {:select [:*]
                              :from [:departments]
                              :where [:= :parent parent-id]})
        child-departments (for [dept root-departments] (assoc dept :children (get-department-hierarchy db (:id dept))))]
    child-departments)
)


(comment


  )