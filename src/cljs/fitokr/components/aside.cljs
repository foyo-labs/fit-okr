(ns fitokr.components.aside
  (:require ["@heroicons/react/24/outline" :refer [HomeIcon, EllipsisHorizontalIcon]]))


(defn tree-node
  "
   部门/团队视图
   This is a example.
   TODO: 包装一个递归函数，获取数据 .
  "
  [data]
  (letfn [(render-node [node]
            [:li {:class "mb-2" :key (:key node)} (:label node)
             (when (:children node)
               [:ul {:class "list-none ml-4 p-0"}
                (map render-node (:children node))])])]
    [:ul {:class "list-none p-0"}
     (map render-node data)]))

(def example-data
  "Example data for tree node"
  [{:label "研发中心"
    :key "t1"
    :children [{:label "开发部"
                :key "t1-1"
                :children [{:label "开发一部" :key "t1-1-1"}
                           {:label "开发二部" :key "t1-1-2"}]}
               {:label "产品部"
                :key "t1-2"}]}
   {:label "运营中心"
    :key "t2"}])

(defn aside-view []
  [:div {:class "relative h-full shrink item-center overflow-x-auto overflow-y-hidden w-80 max-w-xs border-r border-lime-100 border-solid"} 
   [:nav {:class "w-80 flex flex-col pt-2 h-full"} 
    [:div {:class "flex grow shrink basis flex-col justify-items-center"}
     [:<>
      [:div {:class "mt-2 mb-4 pl-4 pr-4"}
       [:ul {:class "p-0 m-0"}
        [:li {:class "flex items-center justify-items-center text-[#509ee3] rounded-md bg-[#509ee3] bg-opacity-20 p-0 m-0 pr-2 pl-4 h-9"}
         [:a {:class "flex space-x-2 w-full item-center transition ease-linear duration-75 delay-0 font-bold cursor-pointer"}
          [:div {:class ""} [:> HomeIcon {:class (str "w-5 h-5")}]]
          [:div {:class ""} "花儿与少年工作室"]]]]]
      [:div {:class "mt-2 mb-4 pl-4 pr-4"}
       [:div {:class "flex items-center mb-2"}
        [:h4 {:class "pl-4 text-[#696E7B] text-xs font-bold"} "部门/团队"]
        [:button {:class "m-auto mr-2 cursor-pointer"}
         [:> EllipsisHorizontalIcon {:class (str "w-[12px] h-[17px]")}]]]
       ;;根据用户类型，属于管理者显示所在树节点，属于用户不显示
       [:div {:class "mb-2"}
        [:div {:class "pl-4"}
         [tree-node example-data]]]]]]]])