Blog engine in clojure. Frontend javascript bundled with shadow.cljs.

Watches `/pages` and renders whole blog on changes.

Use `npx live-server` for instance on root to serve html files.

[available styles](/available-styles.html) for formulas.

### Lunch a shadow build process:

`cider-jack-in-cljs` in emacs or

```npx shadow-cljs watch frontend```

### Blog engine process

`cider-jack-in-clj` and run blog.user/init or

`lein run`


### Using

- [MathJax 3](https://www.mathjax.org) for formulas
- [highlight.js](https://highlightjs.org) for code highlighting

Both Server side rendered.

### tools

`npx shadow-cljs run shadow.cljs.build-report frontend report.html`
`npx sass sass/main.scss assets/css/main.css`
