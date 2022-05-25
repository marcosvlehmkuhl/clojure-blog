(ns my-website.core
  (:gen-class)
  (:require [my-website.markdown-parser :as md-parser]))

(md-parser/parse-post "posts/1-hello-world.md")

(defn -main [& args] ())