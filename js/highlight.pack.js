/*! highlight.js v9.12.0 | BSD3 License | git.io/hljslicense */
!function(e){var t="object"==typeof window&&window||"object"==typeof self&&self;"undefined"!=typeof exports?e(exports):t&&(t.hljs=e({}),"function"==typeof define&&define.amd&&define([],function(){return t.hljs}))}(function(e){function t(e){return e.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;")}function r(e){return e.nodeName.toLowerCase()}function n(e,t){var r=e&&e.exec(t);return r&&0===r.index}function a(e){return R.test(e)}function o(e){var t,r,n,o,i=e.className+" ";if(i+=e.parentNode?e.parentNode.className:"",r=M.exec(i))return k(r[1])?r[1]:"no-highlight";for(i=i.split(/\s+/),t=0,n=i.length;n>t;t++)if(o=i[t],a(o)||k(o))return o}function i(e){var t,r={},n=Array.prototype.slice.call(arguments,1);for(t in e)r[t]=e[t];return n.forEach(function(e){for(t in e)r[t]=e[t]}),r}function c(e){var t=[];return function n(e,a){for(var o=e.firstChild;o;o=o.nextSibling)3===o.nodeType?a+=o.nodeValue.length:1===o.nodeType&&(t.push({event:"start",offset:a,node:o}),a=n(o,a),r(o).match(/br|hr|img|input/)||t.push({event:"stop",offset:a,node:o}));return a}(e,0),t}function s(e,n,a){function o(){return e.length&&n.length?e[0].offset!==n[0].offset?e[0].offset<n[0].offset?e:n:"start"===n[0].event?e:n:e.length?e:n}function i(e){function n(e){return" "+e.nodeName+'="'+t(e.value).replace('"',"&quot;")+'"'}u+="<"+r(e)+N.map.call(e.attributes,n).join("")+">"}function c(e){u+="</"+r(e)+">"}function s(e){("start"===e.event?i:c)(e.node)}for(var l=0,u="",d=[];e.length||n.length;){var f=o();if(u+=t(a.substring(l,f[0].offset)),l=f[0].offset,f===e){d.reverse().forEach(c);do s(f.splice(0,1)[0]),f=o();while(f===e&&f.length&&f[0].offset===l);d.reverse().forEach(i)}else"start"===f[0].event?d.push(f[0].node):d.pop(),s(f.splice(0,1)[0])}return u+t(a.substr(l))}function l(e){return e.v&&!e.cached_variants&&(e.cached_variants=e.v.map(function(t){return i(e,{v:null},t)})),e.cached_variants||e.eW&&[i(e)]||[e]}function u(e){function t(e){return e&&e.source||e}function r(r,n){return new RegExp(t(r),"m"+(e.cI?"i":"")+(n?"g":""))}function n(a,o){if(!a.compiled){if(a.compiled=!0,a.k=a.k||a.bK,a.k){var i={},c=function(t,r){e.cI&&(r=r.toLowerCase()),r.split(" ").forEach(function(e){var r=e.split("|");i[r[0]]=[t,r[1]?Number(r[1]):1]})};"string"==typeof a.k?c("keyword",a.k):x(a.k).forEach(function(e){c(e,a.k[e])}),a.k=i}a.lR=r(a.l||/\w+/,!0),o&&(a.bK&&(a.b="\\b("+a.bK.split(" ").join("|")+")\\b"),a.b||(a.b=/\B|\b/),a.bR=r(a.b),a.e||a.eW||(a.e=/\B|\b/),a.e&&(a.eR=r(a.e)),a.tE=t(a.e)||"",a.eW&&o.tE&&(a.tE+=(a.e?"|":"")+o.tE)),a.i&&(a.iR=r(a.i)),null==a.r&&(a.r=1),a.c||(a.c=[]),a.c=Array.prototype.concat.apply([],a.c.map(function(e){return l("self"===e?a:e)})),a.c.forEach(function(e){n(e,a)}),a.starts&&n(a.starts,o);var s=a.c.map(function(e){return e.bK?"\\.?("+e.b+")\\.?":e.b}).concat([a.tE,a.i]).map(t).filter(Boolean);a.t=s.length?r(s.join("|"),!0):{exec:function(){return null}}}}n(e)}function d(e,r,a,o){function i(e,t){var r,a;for(r=0,a=t.c.length;a>r;r++)if(n(t.c[r].bR,e))return t.c[r]}function c(e,t){if(n(e.eR,t)){for(;e.endsParent&&e.parent;)e=e.parent;return e}return e.eW?c(e.parent,t):void 0}function s(e,t){return!a&&n(t.iR,e)}function l(e,t){var r=w.cI?t[0].toLowerCase():t[0];return e.k.hasOwnProperty(r)&&e.k[r]}function p(e,t,r,n){var a=n?"":I.classPrefix,o='<span class="'+a,i=r?"":B;return o+=e+'">',o+t+i}function b(){var e,r,n,a;if(!N.k)return t(R);for(a="",r=0,N.lR.lastIndex=0,n=N.lR.exec(R);n;)a+=t(R.substring(r,n.index)),e=l(N,n),e?(M+=e[1],a+=p(e[0],t(n[0]))):a+=t(n[0]),r=N.lR.lastIndex,n=N.lR.exec(R);return a+t(R.substr(r))}function g(){var e="string"==typeof N.sL;if(e&&!_[N.sL])return t(R);var r=e?d(N.sL,R,!0,x[N.sL]):f(R,N.sL.length?N.sL:void 0);return N.r>0&&(M+=r.r),e&&(x[N.sL]=r.top),p(r.language,r.value,!1,!0)}function m(){E+=null!=N.sL?g():b(),R=""}function h(e){E+=e.cN?p(e.cN,"",!0):"",N=Object.create(e,{parent:{value:N}})}function v(e,t){if(R+=e,null==t)return m(),0;var r=i(t,N);if(r)return r.skip?R+=t:(r.eB&&(R+=t),m(),r.rB||r.eB||(R=t)),h(r,t),r.rB?0:t.length;var n=c(N,t);if(n){var a=N;a.skip?R+=t:(a.rE||a.eE||(R+=t),m(),a.eE&&(R=t));do N.cN&&(E+=B),N.skip||N.sL||(M+=N.r),N=N.parent;while(N!==n.parent);return n.starts&&h(n.starts,""),a.rE?0:t.length}if(s(t,N))throw new Error('Illegal lexeme "'+t+'" for mode "'+(N.cN||"<unnamed>")+'"');return R+=t,t.length||1}var w=k(e);if(!w)throw new Error('Unknown language: "'+e+'"');u(w);var y,N=o||w,x={},E="";for(y=N;y!==w;y=y.parent)y.cN&&(E=p(y.cN,"",!0)+E);var R="",M=0;try{for(var C,L,z=0;;){if(N.t.lastIndex=z,C=N.t.exec(r),!C)break;L=v(r.substring(z,C.index),C[0]),z=C.index+L}for(v(r.substr(z)),y=N;y.parent;y=y.parent)y.cN&&(E+=B);return{r:M,value:E,language:e,top:N}}catch(A){if(A.message&&-1!==A.message.indexOf("Illegal"))return{r:0,value:t(r)};throw A}}function f(e,r){r=r||I.languages||x(_);var n={r:0,value:t(e)},a=n;return r.filter(k).forEach(function(t){var r=d(t,e,!1);r.language=t,r.r>a.r&&(a=r),r.r>n.r&&(a=n,n=r)}),a.language&&(n.second_best=a),n}function p(e){return I.tabReplace||I.useBR?e.replace(C,function(e,t){return I.useBR&&"\n"===e?"<br>":I.tabReplace?t.replace(/\t/g,I.tabReplace):""}):e}function b(e,t,r){var n=t?E[t]:r,a=[e.trim()];return e.match(/\bhljs\b/)||a.push("hljs"),-1===e.indexOf(n)&&a.push(n),a.join(" ").trim()}function g(e){var t,r,n,i,l,u=o(e);a(u)||(I.useBR?(t=document.createElementNS("http://www.w3.org/1999/xhtml","div"),t.innerHTML=e.innerHTML.replace(/\n/g,"").replace(/<br[ \/]*>/g,"\n")):t=e,l=t.textContent,n=u?d(u,l,!0):f(l),r=c(t),r.length&&(i=document.createElementNS("http://www.w3.org/1999/xhtml","div"),i.innerHTML=n.value,n.value=s(r,c(i),l)),n.value=p(n.value),e.innerHTML=n.value,e.className=b(e.className,u,n.language),e.result={language:n.language,re:n.r},n.second_best&&(e.second_best={language:n.second_best.language,re:n.second_best.r}))}function m(e){I=i(I,e)}function h(){if(!h.called){h.called=!0;var e=document.querySelectorAll("pre code");N.forEach.call(e,g)}}function v(){addEventListener("DOMContentLoaded",h,!1),addEventListener("load",h,!1)}function w(t,r){var n=_[t]=r(e);n.aliases&&n.aliases.forEach(function(e){E[e]=t})}function y(){return x(_)}function k(e){return e=(e||"").toLowerCase(),_[e]||_[E[e]]}var N=[],x=Object.keys,_={},E={},R=/^(no-?highlight|plain|text)$/i,M=/\blang(?:uage)?-([\w-]+)\b/i,C=/((^(<[^>]+>|\t|)+|(?:\n)))/gm,B="</span>",I={classPrefix:"hljs-",tabReplace:null,useBR:!1,languages:void 0};return e.highlight=d,e.highlightAuto=f,e.fixMarkup=p,e.highlightBlock=g,e.configure=m,e.initHighlighting=h,e.initHighlightingOnLoad=v,e.registerLanguage=w,e.listLanguages=y,e.getLanguage=k,e.inherit=i,e.IR="[a-zA-Z]\\w*",e.UIR="[a-zA-Z_]\\w*",e.NR="\\b\\d+(\\.\\d+)?",e.CNR="(-?)(\\b0[xX][a-fA-F0-9]+|(\\b\\d+(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?)",e.BNR="\\b(0b[01]+)",e.RSR="!|!=|!==|%|%=|&|&&|&=|\\*|\\*=|\\+|\\+=|,|-|-=|/=|/|:|;|<<|<<=|<=|<|===|==|=|>>>=|>>=|>=|>>>|>>|>|\\?|\\[|\\{|\\(|\\^|\\^=|\\||\\|=|\\|\\||~",e.BE={b:"\\\\[\\s\\S]",r:0},e.ASM={cN:"string",b:"'",e:"'",i:"\\n",c:[e.BE]},e.QSM={cN:"string",b:'"',e:'"',i:"\\n",c:[e.BE]},e.PWM={b:/\b(a|an|the|are|I'm|isn't|don't|doesn't|won't|but|just|should|pretty|simply|enough|gonna|going|wtf|so|such|will|you|your|they|like|more)\b/},e.C=function(t,r,n){var a=e.inherit({cN:"comment",b:t,e:r,c:[]},n||{});return a.c.push(e.PWM),a.c.push({cN:"doctag",b:"(?:TODO|FIXME|NOTE|BUG|XXX):",r:0}),a},e.CLCM=e.C("//","$"),e.CBCM=e.C("/\\*","\\*/"),e.HCM=e.C("#","$"),e.NM={cN:"number",b:e.NR,r:0},e.CNM={cN:"number",b:e.CNR,r:0},e.BNM={cN:"number",b:e.BNR,r:0},e.CSSNM={cN:"number",b:e.NR+"(%|em|ex|ch|rem|vw|vh|vmin|vmax|cm|mm|in|pt|pc|px|deg|grad|rad|turn|s|ms|Hz|kHz|dpi|dpcm|dppx)?",r:0},e.RM={cN:"regexp",b:/\//,e:/\/[gimuy]*/,i:/\n/,c:[e.BE,{b:/\[/,e:/\]/,r:0,c:[e.BE]}]},e.TM={cN:"title",b:e.IR,r:0},e.UTM={cN:"title",b:e.UIR,r:0},e.METHOD_GUARD={b:"\\.\\s*"+e.UIR,r:0},e.registerLanguage("bash",function(e){var t={cN:"variable",v:[{b:/\$[\w\d#@][\w\d_]*/},{b:/\$\{(.*?)}/}]},r={cN:"string",b:/"/,e:/"/,c:[e.BE,t,{cN:"variable",b:/\$\(/,e:/\)/,c:[e.BE]}]},n={cN:"string",b:/'/,e:/'/};return{aliases:["sh","zsh"],l:/\b-?[a-z\._]+\b/,k:{keyword:"if then else elif fi for while in do done case esac function",literal:"true false",built_in:"break cd continue eval exec exit export getopts hash pwd readonly return shift test times trap umask unset alias bind builtin caller command declare echo enable help let local logout mapfile printf read readarray source type typeset ulimit unalias set shopt autoload bg bindkey bye cap chdir clone comparguments compcall compctl compdescribe compfiles compgroups compquote comptags comptry compvalues dirs disable disown echotc echoti emulate fc fg float functions getcap getln history integer jobs kill limit log noglob popd print pushd pushln rehash sched setcap setopt stat suspend ttyctl unfunction unhash unlimit unsetopt vared wait whence where which zcompile zformat zftp zle zmodload zparseopts zprof zpty zregexparse zsocket zstyle ztcp",_:"-ne -eq -lt -gt -f -d -e -s -l -a"},c:[{cN:"meta",b:/^#![^\n]+sh\s*$/,r:10},{cN:"function",b:/\w[\w\d_]*\s*\(\s*\)\s*\{/,rB:!0,c:[e.inherit(e.TM,{b:/\w[\w\d_]*/})],r:0},e.HCM,r,n,t]}}),e.registerLanguage("clojure",function(e){var t={"builtin-name":"def defonce cond apply if-not if-let if not not= = < > <= >= == + / * - rem quot neg? pos? delay? symbol? keyword? true? false? integer? empty? coll? list? set? ifn? fn? associative? sequential? sorted? counted? reversible? number? decimal? class? distinct? isa? float? rational? reduced? ratio? odd? even? char? seq? vector? string? map? nil? contains? zero? instance? not-every? not-any? libspec? -> ->> .. . inc compare do dotimes mapcat take remove take-while drop letfn drop-last take-last drop-while while intern condp case reduced cycle split-at split-with repeat replicate iterate range merge zipmap declare line-seq sort comparator sort-by dorun doall nthnext nthrest partition eval doseq await await-for let agent atom send send-off release-pending-sends add-watch mapv filterv remove-watch agent-error restart-agent set-error-handler error-handler set-error-mode! error-mode shutdown-agents quote var fn loop recur throw try monitor-enter monitor-exit defmacro defn defn- macroexpand macroexpand-1 for dosync and or when when-not when-let comp juxt partial sequence memoize constantly complement identity assert peek pop doto proxy defstruct first rest cons defprotocol cast coll deftype defrecord last butlast sigs reify second ffirst fnext nfirst nnext defmulti defmethod meta with-meta ns in-ns create-ns import refer keys select-keys vals key val rseq name namespace promise into transient persistent! conj! assoc! dissoc! pop! disj! use class type num float double short byte boolean bigint biginteger bigdec print-method print-dup throw-if printf format load compile get-in update-in pr pr-on newline flush read slurp read-line subvec with-open memfn time re-find re-groups rand-int rand mod locking assert-valid-fdecl alias resolve ref deref refset swap! reset! set-validator! compare-and-set! alter-meta! reset-meta! commute get-validator alter ref-set ref-history-count ref-min-history ref-max-history ensure sync io! new next conj set! to-array future future-call into-array aset gen-class reduce map filter find empty hash-map hash-set sorted-map sorted-map-by sorted-set sorted-set-by vec vector seq flatten reverse assoc dissoc list disj get union difference intersection extend extend-type extend-protocol int nth delay count concat chunk chunk-buffer chunk-append chunk-first chunk-rest max min dec unchecked-inc-int unchecked-inc unchecked-dec-inc unchecked-dec unchecked-negate unchecked-add-int unchecked-add unchecked-subtract-int unchecked-subtract chunk-next chunk-cons chunked-seq? prn vary-meta lazy-seq spread list* str find-keyword keyword symbol gensym force rationalize"},r="a-zA-Z_\\-!.?+*=<>&#'",n="["+r+"]["+r+"0-9/;:]*",a="[-+]?\\d+(\\.\\d+)?",o={b:n,r:0},i={cN:"number",b:a,r:0},c=e.inherit(e.QSM,{i:null}),s=e.C(";","$",{r:0}),l={cN:"literal",b:/\b(true|false|nil)\b/},u={b:"[\\[\\{]",e:"[\\]\\}]"},d={cN:"comment",b:"\\^"+n},f=e.C("\\^\\{","\\}"),p={cN:"symbol",b:"[:]{1,2}"+n},b={b:"\\(",e:"\\)"},g={eW:!0,r:0},m={k:t,l:n,cN:"name",b:n,starts:g},h=[b,c,d,f,s,p,u,i,l,o];return b.c=[e.C("comment",""),m,g],g.c=h,u.c=h,f.c=[u],{aliases:["clj"],i:/\S/,c:[b,c,d,f,s,p,u,i,l]}}),e.registerLanguage("dockerfile",function(e){return{aliases:["docker"],cI:!0,k:"from maintainer expose env arg user onbuild stopsignal",c:[e.HCM,e.ASM,e.QSM,e.NM,{bK:"run cmd entrypoint volume add copy workdir label healthcheck shell",starts:{e:/[^\\]\n/,sL:"bash"}}],i:"</"}}),e.registerLanguage("javascript",function(e){var t="[A-Za-z$_][0-9A-Za-z$_]*",r={keyword:"in of if for while finally var new function do return void else break catch instanceof with throw case default try this switch continue typeof delete let yield const export super debugger as async await static import from as",literal:"true false null undefined NaN Infinity",built_in:"eval isFinite isNaN parseFloat parseInt decodeURI decodeURIComponent encodeURI encodeURIComponent escape unescape Object Function Boolean Error EvalError InternalError RangeError ReferenceError StopIteration SyntaxError TypeError URIError Number Math Date String RegExp Array Float32Array Float64Array Int16Array Int32Array Int8Array Uint16Array Uint32Array Uint8Array Uint8ClampedArray ArrayBuffer DataView JSON Intl arguments require module console window document Symbol Set Map WeakSet WeakMap Proxy Reflect Promise"},n={cN:"number",v:[{b:"\\b(0[bB][01]+)"},{b:"\\b(0[oO][0-7]+)"},{b:e.CNR}],r:0},a={cN:"subst",b:"\\$\\{",e:"\\}",k:r,c:[]},o={cN:"string",b:"`",e:"`",c:[e.BE,a]};a.c=[e.ASM,e.QSM,o,n,e.RM];var i=a.c.concat([e.CBCM,e.CLCM]);return{aliases:["js","jsx"],k:r,c:[{cN:"meta",r:10,b:/^\s*['"]use (strict|asm)['"]/},{cN:"meta",b:/^#!/,e:/$/},e.ASM,e.QSM,o,e.CLCM,e.CBCM,n,{b:/[{,]\s*/,r:0,c:[{b:t+"\\s*:",rB:!0,r:0,c:[{cN:"attr",b:t,r:0}]}]},{b:"("+e.RSR+"|\\b(case|return|throw)\\b)\\s*",k:"return throw case",c:[e.CLCM,e.CBCM,e.RM,{cN:"function",b:"(\\(.*?\\)|"+t+")\\s*=>",rB:!0,e:"\\s*=>",c:[{cN:"params",v:[{b:t},{b:/\(\s*\)/},{b:/\(/,e:/\)/,eB:!0,eE:!0,k:r,c:i}]}]},{b:/</,e:/(\/\w+|\w+\/)>/,sL:"xml",c:[{b:/<\w+\s*\/>/,skip:!0},{b:/<\w+/,e:/(\/\w+|\w+\/)>/,skip:!0,c:[{b:/<\w+\s*\/>/,skip:!0},"self"]}]}],r:0},{cN:"function",bK:"function",e:/\{/,eE:!0,c:[e.inherit(e.TM,{b:t}),{cN:"params",b:/\(/,e:/\)/,eB:!0,eE:!0,c:i}],i:/\[|%/},{b:/\$[(.]/},e.METHOD_GUARD,{cN:"class",bK:"class",e:/[{;=]/,eE:!0,i:/[:"\[\]]/,c:[{bK:"extends"},e.UTM]},{bK:"constructor",e:/\{/,eE:!0}],i:/#(?!!)/}}),e.registerLanguage("lua",function(e){var t="\\[=*\\[",r="\\]=*\\]",n={b:t,e:r,c:["self"]},a=[e.C("--(?!"+t+")","$"),e.C("--"+t,r,{c:[n],r:10})];return{l:e.UIR,k:{literal:"true false nil",keyword:"and break do else elseif end for goto if in local not or repeat return then until while",built_in:"_G _ENV _VERSION __index __newindex __mode __call __metatable __tostring __len __gc __add __sub __mul __div __mod __pow __concat __unm __eq __lt __le assert collectgarbage dofile error getfenv getmetatable ipairs load loadfile loadstringmodule next pairs pcall print rawequal rawget rawset require select setfenvsetmetatable tonumber tostring type unpack xpcall arg selfcoroutine resume yield status wrap create running debug getupvalue debug sethook getmetatable gethook setmetatable setlocal traceback setfenv getinfo setupvalue getlocal getregistry getfenv io lines write close flush open output type read stderr stdin input stdout popen tmpfile math log max acos huge ldexp pi cos tanh pow deg tan cosh sinh random randomseed frexp ceil floor rad abs sqrt modf asin min mod fmod log10 atan2 exp sin atan os exit setlocale date getenv difftime remove time clock tmpname rename execute package preload loadlib loaded loaders cpath config path seeall string sub upper len gfind rep find match char dump gmatch reverse byte format gsub lower table setn insert getn foreachi maxn foreach concat sort remove"},c:a.concat([{cN:"function",bK:"function",e:"\\)",c:[e.inherit(e.TM,{b:"([_a-zA-Z]\\w*\\.)*([_a-zA-Z]\\w*:)?[_a-zA-Z]\\w*"}),{cN:"params",b:"\\(",eW:!0,c:a}].concat(a)},e.CNM,e.ASM,e.QSM,{cN:"string",b:t,e:r,c:[n],r:5}])}}),e.registerLanguage("nginx",function(e){var t={cN:"variable",v:[{b:/\$\d+/},{b:/\$\{/,e:/}/},{b:"[\\$\\@]"+e.UIR}]},r={eW:!0,l:"[a-z/_]+",k:{literal:"on off yes no true false none blocked debug info notice warn error crit select break last permanent redirect kqueue rtsig epoll poll /dev/poll"},r:0,i:"=>",c:[e.HCM,{cN:"string",c:[e.BE,t],v:[{b:/"/,e:/"/},{b:/'/,e:/'/}]},{b:"([a-z]+):/",e:"\\s",eW:!0,eE:!0,c:[t]},{cN:"regexp",c:[e.BE,t],v:[{b:"\\s\\^",e:"\\s|{|;",rE:!0},{b:"~\\*?\\s+",e:"\\s|{|;",rE:!0},{b:"\\*(\\.[a-z\\-]+)+"},{b:"([a-z\\-]+\\.)+\\*"}]},{cN:"number",b:"\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}(:\\d{1,5})?\\b"},{cN:"number",b:"\\b\\d+[kKmMgGdshdwy]*\\b",r:0},t]};return{aliases:["nginxconf"],c:[e.HCM,{b:e.UIR+"\\s+{",rB:!0,e:"{",c:[{cN:"section",b:e.UIR}],r:0},{b:e.UIR+"\\s",e:";|{",rB:!0,c:[{cN:"attribute",b:e.UIR,starts:r}],r:0}],i:"[^\\s\\}]"}}),e});