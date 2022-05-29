(ns my-website.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [my-website.markdown-parser :as md-parser]
            [selmer.parser]
            [selmer.filters]
            [markdown.core :as md]
            [clojure.string :as string]))


(def root_path (.getCanonicalPath (io/file ".")))
(def templates-folder "/src/my_website/templates")

;; def selmer stuff
(def selmer-resource-path (str root_path templates-folder))
(selmer.parser/set-resource-path! selmer-resource-path)
(selmer.filters/add-filter! :markdown (fn [s] [:safe (md/md-to-html-string s)]))


(defn get-metadata-field [metadata field]
  (get-in metadata [:metadata field]))


(defn render-post [source-file]
  (let [parsed-post (md-parser/parse-post source-file)]
    (selmer.parser/render-file
     "post.html"
     {:title (get-metadata-field parsed-post :title)
      :content (get parsed-post :html)})))


(defn write-html [rendered-file content]
  (clojure.java.io/make-parents rendered-file)
  (spit rendered-file content))


(defn create-html-file [source] 
  (let [content (render-post source)
        destination (str "dist/" (string/replace source ".md" ".html"))] 
    (write-html destination content)))


(create-html-file "posts/1-hello-world.md")

(defn -main [& args] ())