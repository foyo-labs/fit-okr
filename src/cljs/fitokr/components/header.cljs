(ns fitokr.components.header
  (:require ["@heroicons/react/24/outline" :refer [PlusIcon]]
            ["@heroicons/react/24/solid" :refer [Cog8ToothIcon]]
            [fitokr.components.common :refer [button]]))


(defn header-view []
  [:header {:class "flex"}
   [:div {:class "flex items-center px-4 justify-items-center h-12 border-b w-full"}
    [:div {:class "right flex grow shrink items-center"} "left"]
    [:div {:class "left flex justify-center items-center max-w-lg space-x-4"}
     [:div {:class ""}
      [button {:id "cc"
               :type "button"
               :icon [:> PlusIcon {:class (str "w-4 h-4")}]
               :text "创建"}]]
     [:div {:class ""}
      [:> Cog8ToothIcon {:class (str "w-6 h-6")}]]]]])