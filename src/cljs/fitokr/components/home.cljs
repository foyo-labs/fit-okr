(ns fitokr.components.home)

;; -- Home -------------------------------------------------------------------
;;
(defn home
  []
  [:div {:class "flex justify-center text-center lg:pb-7 lg:pt-5 lg:text-left"}
   [:div {:class "flex max-w-[37rem] flex-col py-16 lg:pb-11 lg:pt-12"}
    [:h1 {:class "mt-6 text-[1.75rem] font-extrabold leading-9 tracking-tight text-slate-900 md:text-4xl"} "FIT-OKR是您实施OKR理念的理想起点，助力组织落地OKR。"]
    [:div {:class "order-first flex items-center justify-center gap-4 text-[0.8125rem] leading-6 text-slate-500 lg:justify-start"}
     [:p {:class "mb-0"} "开源项目"]
     [:svg {:viewBox "0 0 2 2" :aria-hidden "true" :class "w-0.5 fill-current"}
      [:circle {:cx "1" :cy "1" :r "1"}]]
     [:p {:class "mb-0"} "中小团队适用"]
     [:svg {:viewBox "0 0 2 2" :aria-hidden "true" :class "w-0.5 fill-current"}
      [:circle {:cx "1" :cy "1" :r "1"}]]
     [:p {:class "mb-0"} "一键部署"]]]
   [:div {:class "hidden lg:flex lg:flex-auto lg:justify-center"} 
    [:div {:class "relative aspect-square w-72 flex-none"} "HOME"]]])
