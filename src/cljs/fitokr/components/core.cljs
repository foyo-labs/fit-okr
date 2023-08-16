(ns fitokr.components.core
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            ["@heroicons/react/24/outline" :refer [PlusIcon
                                                   UserCircleIcon
                                                   HomeIcon
                                                   BellAlertIcon
                                                   Cog8ToothIcon
                                                   EnvelopeIcon]]
            [fitokr.components.dialogs :refer [dialog]]
            [fitokr.db.auth :as auth]
            [fitokr.db.ui :as ui]
            [fitokr.components.views :as views]))

(defn user-panel-header
  "
   Header for user panel.
  "
  []
  (let [account @(rf/subscribe [::auth/account])]
    [:<>
     [:div {:class "text-lg text-bold"} "FIT-OKR管理"]
     [:div {:class "flex items-center"}
      [:button {:on-click #(rf/dispatch [::ui/set-active-page {:page :home}])}
       [:> HomeIcon {:class "inline-block w-6 h-6 mx-2"}]]
      [:button
       [:> BellAlertIcon {:class "inline-block w-6 h-6 mx-2"}]]
      [:button
       [:> EnvelopeIcon {:class "inline-block w-6 h-6 mx-2"}]]
      [:button
       [:> Cog8ToothIcon {:class "inline-block w-6 h-6 mx-2"}]]
      [:button {:on-click #(rf/dispatch [::ui/set-active-page {:page :login}])}
       [:> UserCircleIcon {:class (str "w-6 h-6 mx-2" (when account "fill-indigo-500"))}]]]]))


(defn admin-panel-header
  "
   Header for admin panel.
   Users/Departments/GeneralSettings
  "
  []
  (let [is-open (r/atom false)]
    [:<>
     [:div {:class "text-lg text-bold"} "FIT-OKR管理"]
     [:div {:class "flex items-center"}
      [:div {:class "ml-3"}
       [:div
        [:button {:class "relative flex rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
                  :id "user-menu-button"
                  :aria-expanded "false"
                  :aria-haspopup "true"}
         [:> Cog8ToothIcon {:class "inline-block w-6 h-6 mx-2"}]]]
       [:div {:class "absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
              :role "menu"
              :aria-orientation "vertical"
              :aria-labelledby "user-menu-button"
              :tabIndex "-1"}
        [:a {:class "block px-4 py-2 text-sm text-gray-700"
             :role "menuitem"
             :tabIndex "-1"
             :id "user-menu-item-0"} "Your Profile"]]]]]))
      ;;下拉菜单不生效...


(defn app []
  (let [active-page @(rf/subscribe [::ui/active-page])
        admin? @(rf/subscribe [::auth/admin-account])]
    [:<>
     [:header {:class "bg-slate-50 pt-6 border-b"}
      [:nav {:class "flex justify-between p-4 items-center mx-auto max-w-7xl px-4 sm:px-6 lg:px-8"}
       (if admin?
         [admin-panel-header]
         [user-panel-header])]]
     [:main {:class "mx-auto max-w-7xl px-4 sm:px-6 lg:px-8"}
      [views/pages active-page]]
     [dialog]]))
