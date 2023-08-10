(ns fitokr.components.core
  (:require [re-frame.core :as rf]
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

(defn header []
  (let [account @(rf/subscribe [::auth/account])]
    [:nav {:class "flex justify-between p-4 items-center mx-auto max-w-7xl px-4 sm:px-6 lg:px-8"}
     [:div {:class "text-lg text-bold"} "S-OKR管理"]
     [:div {:class "flex items-center"}
      [:button {:on-click #(rf/dispatch [::ui/set-active-page {:page :home}])}
       [:> HomeIcon {:class "inline-block w-6 h-6 mx-2" }]]
      [:button
       [:> BellAlertIcon {:class "inline-block w-6 h-6 mx-2"}]]
      [:button
       [:> EnvelopeIcon {:class "inline-block w-6 h-6 mx-2"}]]
      [:button
       [:> Cog8ToothIcon {:class "inline-block w-6 h-6 mx-2"}]]
      [:button {:on-click #(rf/dispatch [::ui/set-active-page {:page :login}])}
       [:> UserCircleIcon {:class (str "w-6 h-6 mx-2" (when account "fill-indigo-500"))}]]]]))



(defn app []
  (let [active-page @(rf/subscribe [::ui/active-page])]
    [:<>
     [:header {:class "bg-slate-50 pt-6 border-b"}
      [header]]
     [:main {:class "mx-auto max-w-7xl px-4 sm:px-6 lg:px-8"}
      [views/pages active-page]
      ]
     [dialog]]))