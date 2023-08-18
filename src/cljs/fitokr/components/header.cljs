(ns fitokr.components.header
  (:require ["@heroicons/react/24/outline" :refer [PlusIcon]]
            ["@heroicons/react/24/solid" :refer [Cog8ToothIcon]]
            [fitokr.components.common :refer [button]]))


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
     [:div {:class "flex justify-center items-center cursor-pointer hover:bg-[#509ee3] h-[36px] w-[36px] hover:text-white hover:border hover:border-[#509ee3] hover:rounded-md"}
      [:> Cog8ToothIcon {:class (str "w-6 h-6")}]]]]])