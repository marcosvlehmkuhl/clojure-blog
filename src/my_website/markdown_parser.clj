(ns my-website.markdown-parser
  (:require
   [markdown.core :as md]))

(defn parse-post [filepath] 
  (let [content (slurp filepath)] 
     (md/md-to-html-string-with-meta content)))

(parse-post "posts/1-hello-world.md")