(ns fitokr.pages.views
  (:require [fitokr.pages.login.page :as login]
            [fitokr.pages.home.page :as home]))


(defn pages
  [page-name]
  (case page-name
    :home #'home/page
    :login #'login/page
    :admin/home #'home/page
    #'home/page))