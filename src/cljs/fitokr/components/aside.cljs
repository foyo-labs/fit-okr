(ns fitokr.components.aside
  (:require ["@heroicons/react/24/outline" :refer [HomeIcon]]))

(defn aside-view []
  [:div {:class "relative h-full shrink item-center overflow-x-auto overflow-y-hidden w-80 max-w-xs border-r border-lime-100 border-solid"} 
   [:nav {:class "w-80 flex flex-col pt-2 h-full"} 
    [:div {:class "flex grow shrink basis flex-col justify-items-center"}
     [:<> 
      [:div {:class "mt-2 mb-4 pl-4 pr-4"} 
       [:ul {:class "p-0 m-0"}
        [:li {:class "flex items-center justify-items-center text-[#509ee3] rounded-md bg-[#509ee3] bg-opacity-20 p-0 m-0 pr-2 pl-4 h-9"} 
         [:a {:class "flex space-x-2 w-full item-center transition ease-linear duration-75 delay-0 font-bold"} 
          [:div {:class ""} [:> HomeIcon {:class (str "w-5 h-5")}]]
          [:div {:class ""} "花儿与少年工作室"]]]]]]]]])