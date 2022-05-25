(ns my-website.markdown-parser
  (:require
   [clojure.string :as string]))

;; (?s) is DOTALL to make . matche newline also
(def metadata-regex #"(?s)---.+---\n")

(defn get-metadata-content [filepath]
  (let [filecontent (slurp filepath)]
    (string/replace (re-find metadata-regex filecontent) #"---\n|\n---" "")))

(defn replace-and-trim [pattern, string]
  (string/triml (string/replace string pattern "")))

(defn get-title [content-string]
  (replace-and-trim "title:" (re-find #"title\:.+" content-string)))

(def time-pattern (java.time.format.DateTimeFormatter/ofPattern "dd-MM-yyyy"))


(defn parse-date [content-string]
  (replace-and-trim "date:" (re-find #"date\:.+" content-string)))

(defn get-date [content-string]
  (java.time.LocalDate/parse (parse-date content-string) time-pattern))

(defn get-tags-content [content-string]
  (let [title (re-find #"tags:.+" content-string)]
    (string/split
     (string/triml (string/replace title #"tags: |[\[\]]" ""))
     #",")))

(defn get-tags [content]
  (let [tags (get-tags-content content)]
    (mapv string/trim tags)))


(defn get-metadata [post]
  (let [post-metadata (get-metadata-content post)]
    (hash-map :title (get-title post-metadata),
              :date (get-date post-metadata)
              :tags (get-tags post-metadata))))

(defn get-body-content [filepath]
  (let [filecontent (slurp filepath)]
    (string/replace filecontent metadata-regex "")))

(defn parse-post [filepath] 
  (let [metadata (get-metadata filepath)
        content (get-body-content filepath)] 
    (hash-map :metadata metadata
              :content content)))