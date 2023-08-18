(ns fitokr.components.header
  (:require ["@heroicons/react/24/outline" :refer [PlusIcon]]
            ["@heroicons/react/24/solid" :refer [Cog8ToothIcon]]
            [fitokr.components.common :refer [button]]
            [reagent.dom :as rdom]
            [reagent.core :as reagent]))

(defonce dropdown-state (reagent/atom false))

(defn toggle-dropdown []
  (swap! dropdown-state not))

(defn setting-dropdown-menu 
  "dropdown menu for setting button"
  []
  [:div {:class "absolute z-10 top-[36px] right-0 mt-2 w-40 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 transition ease-in-out duration-100 delay-100"
         :role "menu"
         :aria-orientation "vertical"
         :aria-labelledby "setting-menu-btn"
         :id "dropdown-content"}
   [:div {:class "py-1" :role "none"}
    [:a {:href "#"
         :class "block px-6 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900"
         :role "menuitem"
         :tabIndex "-1"} "账户设置"]
    [:a {:href "#"
         :class "block px-6 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900"
         :role "menuitem"
         :tabIndex "-1"} "管理设置"]
    [:a {:href "#"
         :class "block px-6 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900"
         :role "menuitem"
         :tabIndex "-1"} "关于FIT-OKR"]
    [:a {:href "#"
         :class "block px-6 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900"
         :role "menuitem"
         :tabIndex "-1"} "注销"]]])

(defn header-view []
  [:header {:class "flex"}
   [:div {:class "flex items-center px-4 justify-items-center h-12 border-b w-full"}
    [:div {:class "right flex grow shrink items-center"}
     [:a {:class "cursor-pointer flex items-center justify-items-center header-logo block w-[28px] h-[28px] truncate"} ""]]
    [:div {:class "left flex justify-center items-center max-w-lg space-x-4"}
     [:div {:class ""}
      [button {:id "cc"
               :type "button"
               :icon [:> PlusIcon {:class (str "w-4 h-4")}]
               :text "创建"}]]
     [:div {:class "relative inline-block text-left"}
      [:div {:class "flex justify-center items-center cursor-pointer hover:bg-[#509ee3] h-[36px] w-[36px] hover:text-white hover:border hover:border-[#509ee3] hover:rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
             :id "setting-menu-btn"
             :aria-expanded (str @dropdown-state)
             :aria-haspopup "true"
             :on-click toggle-dropdown}
       [:> Cog8ToothIcon {:class (str "w-6 h-6")}]]
      (js/console.log @dropdown-state)
      (when @dropdown-state
        [setting-dropdown-menu])]]]])