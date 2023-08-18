(ns fitokr.pages.layout
  (:require [re-frame.core :as rf]
            [fitokr.db.auth :as auth]
            [fitokr.db.ui :as ui]
            [fitokr.pages.views :as views]
            [fitokr.components.header :refer [header-view]]
            [fitokr.components.aside :refer [aside-view]]))



(defn layout []
  (let [active-page @(rf/subscribe [::ui/active-page])
        login? @(rf/subscribe [::auth/account])
        page (if login? active-page :login)
        page-component (views/pages page)]
    [:div {:class "flex flex-col justify-center w-full h-screen container-bg"}
     (when login? [header-view])
     (if login?
       [:div {:class "aside flex grow flex-row bg-white overflow-x-hidden overflow-y-hidden relative"}
        [aside-view]
        [:main {:class "main"}
         [page-component]]]
       [page-component])]))