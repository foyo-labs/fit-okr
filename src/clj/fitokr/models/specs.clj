(ns fitokr.models.specs
  (:require [clojure.spec.alpha :as s]))

(def available-cycle-str #"^(Q[1-4]|M[1-9]|M1[0-2])")

;;common spec
(s/def ::id integer?)
(s/def ::oid integer?)
(s/def ::parent integer?)
(s/def ::limit (s/and int? #(>= % 0)))
(s/def ::offset (s/and int? #(>= % 0)))

;;objectives spec
(s/def ::cycle #(re-matches available-cycle-str %))
(s/def ::owner integer?)
(s/def ::name string?)
(s/def ::position integer?)


(s/def ::create-key-result (s/keys :req-un [::name ::position]))

(s/def ::create-objective (s/keys :req-un [::owner ::name ::cycle ::position ]))
(s/def ::query-objective (s/keys :req-un [::cycle ::limit ::offset]))
(s/def ::create-objective-response (s/keys :req-un [::id]))


;;user spec
(s/def ::email string?)
(s/def ::password string?)

(s/def ::register-user (s/keys :req-un [::email ::password ::name]))
(s/def ::login-user (s/keys :req-un [::email ::password]))

;;setting spec
(def all-setting-lables {:company-name :company-name})
(def all-setting-groups {:account "账号" :general "通用"})

;;(":company-name", "花儿与少年工作室", "string", ":g-general")
(s/def ::create-setting-item (s/keys :req-un [::label ::content ::dtype ::section]))


;;department spec
(s/def ::create-department (s/keys :req-un [::name ::description ::parent ::position ::code ::ownid]))


;; Initialize spec
(s/def ::create-initial-data (s/keys :req-un [::company-name ::cycle]))

(comment
  (def objective {:owner 1 :name "oooo" :cycle "Q1" :position 1})
  (s/valid? ::create-objective objective)
  )