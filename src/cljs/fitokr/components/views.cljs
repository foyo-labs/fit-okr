(ns fitokr.components.views
  (:require [fitokr.components.login :refer [login]]
            [fitokr.components.home :refer [home]]))


(defn register
  []
  [:div "Register Page"])


(defn pages
  [page-name]
  (case page-name
    :home [home]
    :login [login]
    :register [register]
    [home]))