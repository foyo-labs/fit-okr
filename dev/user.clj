(ns user
  (:require [clojure.tools.namespace.repl :as tools-ns]
            [integrant.repl :as ig-repl　:refer [go halt]]
            [integrant.repl.state :as state]
            [nrepl.server]
            [fitokr.services.config :as c]
            [ring.util.request :as req]
            [clojure.pprint :as pprint]
            [next.jdbc :as jdbc]))

(tools-ns/set-refresh-dirs "dev" "server/src")

(ig-repl/set-prep!
 (fn []
   (c/read-config :dev)))

(declare router db)


(defn start-interactive []
  (go)
  (def router (:reitit/routes state/system))
  (def db (:postgres/db state/system))
  :ready!)

(defn restart []
  (halt)
  (tools-ns/refresh :after 'user/start-interactive))

(comment
  (halt)
  (restart)
  state/system
  )


(def req {:host "http://www.ckmro.com"
          :path "/api/v1/orders"
          :trace [:received]
          :x-forward-to "ABEDX08D"})

(defn prepare
  [req]
  (-> req 
      (assoc :url (str (:host req) (:path req)))
      (dissoc :x-forward-to)
      (update :trace conj :prepared)))

(System/setProperty "javax.net.ssl.protocols" "TLSv1.0")

(def db-spec {:dbtype "mssql"
              :host "fzsld2021.gnway.vip"
              :port 58682
              :dbname "EmpireData2012New"
              :user "ckmro"
              :password "ckmro"})

(def con (jdbc/get-connection db-spec))
;; (jdbc/execute! con ["select top 2 * from P_StockOrderNew where htbid='HT-230720-1001'"])


(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")

(comment
  (def v (jdbc/execute! con ["select name from sysobjects where xtype='U'"]))
  (prn v)

  (def data (jdbc/execute! con ["select top 10 * from P_StockOrderProcessFx where HTBID='HT-230725-1001' order by id asc"]))
  ;; (def data (jdbc/execute! con ["select top 1 * from P_StockPayment where order by id asc "]))
  (def data (jdbc/execute! con ["select top 1 * from P_StockOrderProcess where HTBID='HT-230720-1001' "]))

  (def data (jdbc/execute! con ["select top 1 * from P_StockOut where PBID='HT-230720-1001'"]))

  (prn data)
  )