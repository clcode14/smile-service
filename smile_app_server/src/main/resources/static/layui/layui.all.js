/** layui-v2.5.4 MIT License By https://www.layui.com */
 ;!function(e){"use strict";var t=document,o={modules:{},status:{},timeout:10,event:{}},n=function(){this.v="2.5.4"},r=function(){var e=t.currentScript?t.currentScript.src:function(){for(var e,o=t.scripts,n=o.length-1,r=n;r>0;r--)if("interactive"===o[r].readyState){e=o[r].src;break}return e||o[n].src}();return e.substring(0,e.lastIndexOf("/")+1)}(),i=function(t){e.console&&console.error&&console.error("Layui hint: "+t)},a="undefined"!=typeof opera&&"[object Opera]"===opera.toString(),u={layer:"modules/layer",laydate:"modules/laydate",laypage:"modules/laypage",laytpl:"modules/laytpl",layim:"modules/layim",layedit:"modules/layedit",form:"modules/form",upload:"modules/upload",transfer:"modules/transfer",tree:"modules/tree",table:"modules/table",element:"modules/element",rate:"modules/rate",colorpicker:"modules/colorpicker",slider:"modules/slider",carousel:"modules/carousel",flow:"modules/flow",util:"modules/util",code:"modules/code",jquery:"modules/jquery",mobile:"modules/mobile","layui.all":"../layui.all"};n.prototype.cache=o,n.prototype.define=function(e,t){var n=this,r="function"==typeof e,i=function(){var e=function(e,t){layui[e]=t,o.status[e]=!0};return"function"==typeof t&&t(function(n,r){e(n,r),o.callback[n]=function(){t(e)}}),this};return r&&(t=e,e=[]),!layui["layui.all"]&&layui["layui.mobile"]?i.call(n):(n.use(e,i),n)},n.prototype.use=function(e,n,l){function s(e,t){var n="PLaySTATION 3"===navigator.platform?/^complete$/:/^(complete|loaded)$/;("load"===e.type||n.test((e.currentTarget||e.srcElement).readyState))&&(o.modules[f]=t,d.removeChild(v),function r(){return++m>1e3*o.timeout/4?i(f+" is not a valid module"):void(o.status[f]?c():setTimeout(r,4))}())}function c(){l.push(layui[f]),e.length>1?y.use(e.slice(1),n,l):"function"==typeof n&&n.apply(layui,l)}var y=this,p=o.dir=o.dir?o.dir:r,d=t.getElementsByTagName("head")[0];e="string"==typeof e?[e]:e,window.jQuery&&jQuery.fn.on&&(y.each(e,function(t,o){"jquery"===o&&e.splice(t,1)}),layui.jquery=layui.$=jQuery);var f=e[0],m=0;if(l=l||[],o.host=o.host||(p.match(/\/\/([\s\S]+?)\//)||["//"+location.host+"/"])[0],0===e.length||layui["layui.all"]&&u[f]||!layui["layui.all"]&&layui["layui.mobile"]&&u[f])return c(),y;if(o.modules[f])!function g(){return++m>1e3*o.timeout/4?i(f+" is not a valid module"):void("string"==typeof o.modules[f]&&o.status[f]?c():setTimeout(g,4))}();else{var v=t.createElement("script"),h=(u[f]?p+"lay/":/^\{\/\}/.test(y.modules[f])?"":o.base||"")+(y.modules[f]||f)+".js";h=h.replace(/^\{\/\}/,""),v.async=!0,v.charset="utf-8",v.src=h+function(){var e=o.version===!0?o.v||(new Date).getTime():o.version||"";return e?"?v="+e:""}(),d.appendChild(v),!v.attachEvent||v.attachEvent.toString&&v.attachEvent.toString().indexOf("[native code")<0||a?v.addEventListener("load",function(e){s(e,h)},!1):v.attachEvent("onreadystatechange",function(e){s(e,h)}),o.modules[f]=h}return y},n.prototype.getStyle=function(t,o){var n=t.currentStyle?t.currentStyle:e.getComputedStyle(t,null);return n[n.getPropertyValue?"getPropertyValue":"getAttribute"](o)},n.prototype.link=function(e,n,r){var a=this,u=t.createElement("link"),l=t.getElementsByTagName("head")[0];"string"==typeof n&&(r=n);var s=(r||e).replace(/\.|\//g,""),c=u.id="layuicss-"+s,y=0;return u.rel="stylesheet",u.href=e+(o.debug?"?v="+(new Date).getTime():""),u.media="all",t.getElementById(c)||l.appendChild(u),"function"!=typeof n?a:(function p(){return++y>1e3*o.timeout/100?i(e+" timeout"):void(1989===parseInt(a.getStyle(t.getElementById(c),"width"))?function(){n()}():setTimeout(p,100))}(),a)},o.callback={},n.prototype.factory=function(e){if(layui[e])return"function"==typeof o.callback[e]?o.callback[e]:null},n.prototype.addcss=function(e,t,n){return layui.link(o.dir+"css/"+e,t,n)},n.prototype.img=function(e,t,o){var n=new Image;return n.src=e,n.complete?t(n):(n.onload=function(){n.onload=null,"function"==typeof t&&t(n)},void(n.onerror=function(e){n.onerror=null,"function"==typeof o&&o(e)}))},n.prototype.config=function(e){e=e||{};for(var t in e)o[t]=e[t];return this},n.prototype.modules=function(){var e={};for(var t in u)e[t]=u[t];return e}(),n.prototype.extend=function(e){var t=this;e=e||{};for(var o in e)t[o]||t.modules[o]?i("模块名 "+o+" 已被占用"):t.modules[o]=e[o];return t},n.prototype.router=function(e){var t=this,e=e||location.hash,o={path:[],search:{},hash:(e.match(/[^#](#.*$)/)||[])[1]||""};return/^#\//.test(e)?(e=e.replace(/^#\//,""),o.href="/"+e,e=e.replace(/([^#])(#.*$)/,"$1").split("/")||[],t.each(e,function(e,t){/^\w+=/.test(t)?function(){t=t.split("="),o.search[t[0]]=t[1]}():o.path.push(t)}),o):o},n.prototype.data=function(t,o,n){if(t=t||"layui",n=n||localStorage,e.JSON&&e.JSON.parse){if(null===o)return delete n[t];o="object"==typeof o?o:{key:o};try{var r=JSON.parse(n[t])}catch(i){var r={}}return"value"in o&&(r[o.key]=o.value),o.remove&&delete r[o.key],n[t]=JSON.stringify(r),o.key?r[o.key]:r}},n.prototype.sessionData=function(e,t){return this.data(e,t,sessionStorage)},n.prototype.device=function(t){var o=navigator.userAgent.toLowerCase(),n=function(e){var t=new RegExp(e+"/([^\\s\\_\\-]+)");return e=(o.match(t)||[])[1],e||!1},r={os:function(){return/windows/.test(o)?"windows":/linux/.test(o)?"linux":/iphone|ipod|ipad|ios/.test(o)?"ios":/mac/.test(o)?"mac":void 0}(),ie:function(){return!!(e.ActiveXObject||"ActiveXObject"in e)&&((o.match(/msie\s(\d+)/)||[])[1]||"11")}(),weixin:n("micromessenger")};return t&&!r[t]&&(r[t]=n(t)),r.android=/android/.test(o),r.ios="ios"===r.os,r},n.prototype.hint=function(){return{error:i}},n.prototype.each=function(e,t){var o,n=this;if("function"!=typeof t)return n;if(e=e||[],e.constructor===Object){for(o in e)if(t.call(e[o],o,e[o]))break}else for(o=0;o<e.length&&!t.call(e[o],o,e[o]);o++);return n},n.prototype.sort=function(e,t,o){var n=JSON.parse(JSON.stringify(e||[]));return t?(n.sort(function(e,o){var n=/^-?\d+$/,r=e[t],i=o[t];return n.test(r)&&(r=parseFloat(r)),n.test(i)&&(i=parseFloat(i)),r&&!i?1:!r&&i?-1:r>i?1:r<i?-1:0}),o&&n.reverse(),n):n},n.prototype.stope=function(t){t=t||e.event;try{t.stopPropagation()}catch(o){t.cancelBubble=!0}},n.prototype.onevent=function(e,t,o){return"string"!=typeof e||"function"!=typeof o?this:n.event(e,t,null,o)},n.prototype.event=n.event=function(e,t,n,r){var i=this,a=null,u=t.match(/\((.*)\)$/)||[],l=(e+"."+t).replace(u[0],""),s=u[1]||"",c=function(e,t){var o=t&&t.call(i,n);o===!1&&null===a&&(a=!1)};return r?(o.event[l]=o.event[l]||{},o.event[l][s]=[r],this):(layui.each(o.event[l],function(e,t){return"{*}"===s?void layui.each(t,c):(""===e&&layui.each(t,c),void(s&&e===s&&layui.each(t,c)))}),a)},e.layui=new n}(window);layui.define(function(a){var i=layui.cache;layui.config({dir:i.dir.replace(/lay\/dest\/$/,"")}),a("layui.all",layui.v)});layui.define(function(e){"use strict";var r={open:"{{",close:"}}"},c={exp:function(e){return new RegExp(e,"g")},query:function(e,c,t){var o=["#([\\s\\S])+?","([^{#}])*?"][e||0];return n((c||"")+r.open+o+r.close+(t||""))},escape:function(e){return String(e||"").replace(/&(?!#?[a-zA-Z0-9]+;)/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/'/g,"&#39;").replace(/"/g,"&quot;")},error:function(e,r){var c="Laytpl Error：";return"object"==typeof console&&console.error(c+e+"\n"+(r||"")),c+e}},n=c.exp,t=function(e){this.tpl=e};t.pt=t.prototype,window.errors=0,t.pt.parse=function(e,t){var o=this,p=e,a=n("^"+r.open+"#",""),l=n(r.close+"$","");e=e.replace(/\s+|\r|\t|\n/g," ").replace(n(r.open+"#"),r.open+"# ").replace(n(r.close+"}"),"} "+r.close).replace(/\\/g,"\\\\").replace(n(r.open+"!(.+?)!"+r.close),function(e){return e=e.replace(n("^"+r.open+"!"),"").replace(n("!"+r.close),"").replace(n(r.open+"|"+r.close),function(e){return e.replace(/(.)/g,"\\$1")})}).replace(/(?="|')/g,"\\").replace(c.query(),function(e){return e=e.replace(a,"").replace(l,""),'";'+e.replace(/\\/g,"")+';view+="'}).replace(c.query(1),function(e){var c='"+(';return e.replace(/\s/g,"")===r.open+r.close?"":(e=e.replace(n(r.open+"|"+r.close),""),/^=/.test(e)&&(e=e.replace(/^=/,""),c='"+_escape_('),c+e.replace(/\\/g,"")+')+"')}),e='"use strict";var view = "'+e+'";return view;';try{return o.cache=e=new Function("d, _escape_",e),e(t,c.escape)}catch(u){return delete o.cache,c.error(u,p)}},t.pt.render=function(e,r){var n,t=this;return e?(n=t.cache?t.cache(e,c.escape):t.parse(t.tpl,e),r?void r(n):n):c.error("no data")};var o=function(e){return"string"!=typeof e?c.error("Template not found"):new t(e)};o.config=function(e){e=e||{};for(var c in e)r[c]=e[c]},o.v="1.2.0",e("laytpl",o)});layui.define(function(e){"use strict";var a=document,t="getElementById",n="getElementsByTagName",i="laypage",r="layui-disabled",u=function(e){var a=this;a.config=e||{},a.config.index=++s.index,a.render(!0)};u.prototype.type=function(){var e=this.config;if("object"==typeof e.elem)return void 0===e.elem.length?2:3},u.prototype.view=function(){var e=this,a=e.config,t=a.groups="groups"in a?0|a.groups:5;a.layout="object"==typeof a.layout?a.layout:["prev","page","next"],a.count=0|a.count,a.curr=0|a.curr||1,a.limits="object"==typeof a.limits?a.limits:[10,20,30,40,50],a.limit=0|a.limit||10,a.pages=Math.ceil(a.count/a.limit)||1,a.curr>a.pages&&(a.curr=a.pages),t<0?t=1:t>a.pages&&(t=a.pages),a.prev="prev"in a?a.prev:"&#x4E0A;&#x4E00;&#x9875;",a.next="next"in a?a.next:"&#x4E0B;&#x4E00;&#x9875;";var n=a.pages>t?Math.ceil((a.curr+(t>1?1:0))/(t>0?t:1)):1,i={prev:function(){return a.prev?'<a href="javascript:;" class="layui-laypage-prev'+(1==a.curr?" "+r:"")+'" data-page="'+(a.curr-1)+'">'+a.prev+"</a>":""}(),page:function(){var e=[];if(a.count<1)return"";n>1&&a.first!==!1&&0!==t&&e.push('<a href="javascript:;" class="layui-laypage-first" data-page="1"  title="&#x9996;&#x9875;">'+(a.first||1)+"</a>");var i=Math.floor((t-1)/2),r=n>1?a.curr-i:1,u=n>1?function(){var e=a.curr+(t-i-1);return e>a.pages?a.pages:e}():t;for(u-r<t-1&&(r=u-t+1),a.first!==!1&&r>2&&e.push('<span class="layui-laypage-spr">&#x2026;</span>');r<=u;r++)r===a.curr?e.push('<span class="layui-laypage-curr"><em class="layui-laypage-em" '+(/^#/.test(a.theme)?'style="background-color:'+a.theme+';"':"")+"></em><em>"+r+"</em></span>"):e.push('<a href="javascript:;" data-page="'+r+'">'+r+"</a>");return a.pages>t&&a.pages>u&&a.last!==!1&&(u+1<a.pages&&e.push('<span class="layui-laypage-spr">&#x2026;</span>'),0!==t&&e.push('<a href="javascript:;" class="layui-laypage-last" title="&#x5C3E;&#x9875;"  data-page="'+a.pages+'">'+(a.last||a.pages)+"</a>")),e.join("")}(),next:function(){return a.next?'<a href="javascript:;" class="layui-laypage-next'+(a.curr==a.pages?" "+r:"")+'" data-page="'+(a.curr+1)+'">'+a.next+"</a>":""}(),count:'<span class="layui-laypage-count">共 '+a.count+" 条</span>",limit:function(){var e=['<span class="layui-laypage-limits"><select lay-ignore>'];return layui.each(a.limits,function(t,n){e.push('<option value="'+n+'"'+(n===a.limit?"selected":"")+">"+n+" 条/页</option>")}),e.join("")+"</select></span>"}(),refresh:['<a href="javascript:;" data-page="'+a.curr+'" class="layui-laypage-refresh">','<i class="layui-icon layui-icon-refresh"></i>',"</a>"].join(""),skip:function(){return['<span class="layui-laypage-skip">&#x5230;&#x7B2C;','<input type="text" min="1" value="'+a.curr+'" class="layui-input">','&#x9875;<button type="button" class="layui-laypage-btn">&#x786e;&#x5b9a;</button>',"</span>"].join("")}()};return['<div class="layui-box layui-laypage layui-laypage-'+(a.theme?/^#/.test(a.theme)?"molv":a.theme:"default")+'" id="layui-laypage-'+a.index+'">',function(){var e=[];return layui.each(a.layout,function(a,t){i[t]&&e.push(i[t])}),e.join("")}(),"</div>"].join("")},u.prototype.jump=function(e,a){if(e){var t=this,i=t.config,r=e.children,u=e[n]("button")[0],l=e[n]("input")[0],p=e[n]("select")[0],c=function(){var e=0|l.value.replace(/\s|\D/g,"");e&&(i.curr=e,t.render())};if(a)return c();for(var o=0,y=r.length;o<y;o++)"a"===r[o].nodeName.toLowerCase()&&s.on(r[o],"click",function(){var e=0|this.getAttribute("data-page");e<1||e>i.pages||(i.curr=e,t.render())});p&&s.on(p,"change",function(){var e=this.value;i.curr*e>i.count&&(i.curr=Math.ceil(i.count/e)),i.limit=e,t.render()}),u&&s.on(u,"click",function(){c()})}},u.prototype.skip=function(e){if(e){var a=this,t=e[n]("input")[0];t&&s.on(t,"keyup",function(t){var n=this.value,i=t.keyCode;/^(37|38|39|40)$/.test(i)||(/\D/.test(n)&&(this.value=n.replace(/\D/,"")),13===i&&a.jump(e,!0))})}},u.prototype.render=function(e){var n=this,i=n.config,r=n.type(),u=n.view();2===r?i.elem&&(i.elem.innerHTML=u):3===r?i.elem.html(u):a[t](i.elem)&&(a[t](i.elem).innerHTML=u),i.jump&&i.jump(i,e);var s=a[t]("layui-laypage-"+i.index);n.jump(s),i.hash&&!e&&(location.hash="!"+i.hash+"="+i.curr),n.skip(s)};var s={render:function(e){var a=new u(e);return a.index},index:layui.laypage?layui.laypage.index+1e4:0,on:function(e,a,t){return e.attachEvent?e.attachEvent("on"+a,function(a){a.target=a.srcElement,t.call(e,a)}):e.addEventListener(a,t,!1),this}};e(i,s)});!function(){"use strict";var e=window.layui&&layui.define,t={getPath:function(){var e=document.currentScript?document.currentScript.src:function(){for(var e,t=document.scripts,n=t.length-1,a=n;a>0;a--)if("interactive"===t[a].readyState){e=t[a].src;break}return e||t[n].src}();return e.substring(0,e.lastIndexOf("/")+1)}(),getStyle:function(e,t){var n=e.currentStyle?e.currentStyle:window.getComputedStyle(e,null);return n[n.getPropertyValue?"getPropertyValue":"getAttribute"](t)},link:function(e,a,i){if(n.path){var r=document.getElementsByTagName("head")[0],o=document.createElement("link");"string"==typeof a&&(i=a);var s=(i||e).replace(/\.|\//g,""),l="layuicss-"+s,d=0;o.rel="stylesheet",o.href=n.path+e,o.id=l,document.getElementById(l)||r.appendChild(o),"function"==typeof a&&!function c(){return++d>80?window.console&&console.error("laydate.css: Invalid"):void(1989===parseInt(t.getStyle(document.getElementById(l),"width"))?a():setTimeout(c,100))}()}}},n={v:"5.0.9",config:{},index:window.laydate&&window.laydate.v?1e5:0,path:t.getPath,set:function(e){var t=this;return t.config=w.extend({},t.config,e),t},ready:function(a){var i="laydate",r="",o=(e?"modules/laydate/":"theme/")+"default/laydate.css?v="+n.v+r;return e?layui.addcss(o,a,i):t.link(o,a,i),this}},a=function(){var e=this;return{hint:function(t){e.hint.call(e,t)},config:e.config}},i="laydate",r=".layui-laydate",o="layui-this",s="laydate-disabled",l="开始日期超出了结束日期<br>建议重新选择",d=[100,2e5],c="layui-laydate-static",m="layui-laydate-list",u="laydate-selected",h="layui-laydate-hint",y="laydate-day-prev",f="laydate-day-next",p="layui-laydate-footer",g=".laydate-btns-confirm",v="laydate-time-text",D=".laydate-btns-time",T=function(e){var t=this;t.index=++n.index,t.config=w.extend({},t.config,n.config,e),n.ready(function(){t.init()})},w=function(e){return new C(e)},C=function(e){for(var t=0,n="object"==typeof e?[e]:(this.selector=e,document.querySelectorAll(e||null));t<n.length;t++)this.push(n[t])};C.prototype=[],C.prototype.constructor=C,w.extend=function(){var e=1,t=arguments,n=function(e,t){e=e||(t.constructor===Array?[]:{});for(var a in t)e[a]=t[a]&&t[a].constructor===Object?n(e[a],t[a]):t[a];return e};for(t[0]="object"==typeof t[0]?t[0]:{};e<t.length;e++)"object"==typeof t[e]&&n(t[0],t[e]);return t[0]},w.ie=function(){var e=navigator.userAgent.toLowerCase();return!!(window.ActiveXObject||"ActiveXObject"in window)&&((e.match(/msie\s(\d+)/)||[])[1]||"11")}(),w.stope=function(e){e=e||window.event,e.stopPropagation?e.stopPropagation():e.cancelBubble=!0},w.each=function(e,t){var n,a=this;if("function"!=typeof t)return a;if(e=e||[],e.constructor===Object){for(n in e)if(t.call(e[n],n,e[n]))break}else for(n=0;n<e.length&&!t.call(e[n],n,e[n]);n++);return a},w.digit=function(e,t,n){var a="";e=String(e),t=t||2;for(var i=e.length;i<t;i++)a+="0";return e<Math.pow(10,t)?a+(0|e):e},w.elem=function(e,t){var n=document.createElement(e);return w.each(t||{},function(e,t){n.setAttribute(e,t)}),n},C.addStr=function(e,t){return e=e.replace(/\s+/," "),t=t.replace(/\s+/," ").split(" "),w.each(t,function(t,n){new RegExp("\\b"+n+"\\b").test(e)||(e=e+" "+n)}),e.replace(/^\s|\s$/,"")},C.removeStr=function(e,t){return e=e.replace(/\s+/," "),t=t.replace(/\s+/," ").split(" "),w.each(t,function(t,n){var a=new RegExp("\\b"+n+"\\b");a.test(e)&&(e=e.replace(a,""))}),e.replace(/\s+/," ").replace(/^\s|\s$/,"")},C.prototype.find=function(e){var t=this,n=0,a=[],i="object"==typeof e;return this.each(function(r,o){for(var s=i?[e]:o.querySelectorAll(e||null);n<s.length;n++)a.push(s[n]);t.shift()}),i||(t.selector=(t.selector?t.selector+" ":"")+e),w.each(a,function(e,n){t.push(n)}),t},C.prototype.each=function(e){return w.each.call(this,this,e)},C.prototype.addClass=function(e,t){return this.each(function(n,a){a.className=C[t?"removeStr":"addStr"](a.className,e)})},C.prototype.removeClass=function(e){return this.addClass(e,!0)},C.prototype.hasClass=function(e){var t=!1;return this.each(function(n,a){new RegExp("\\b"+e+"\\b").test(a.className)&&(t=!0)}),t},C.prototype.attr=function(e,t){var n=this;return void 0===t?function(){if(n.length>0)return n[0].getAttribute(e)}():n.each(function(n,a){a.setAttribute(e,t)})},C.prototype.removeAttr=function(e){return this.each(function(t,n){n.removeAttribute(e)})},C.prototype.html=function(e){return this.each(function(t,n){n.innerHTML=e})},C.prototype.val=function(e){return this.each(function(t,n){n.value=e})},C.prototype.append=function(e){return this.each(function(t,n){"object"==typeof e?n.appendChild(e):n.innerHTML=n.innerHTML+e})},C.prototype.remove=function(e){return this.each(function(t,n){e?n.removeChild(e):n.parentNode.removeChild(n)})},C.prototype.on=function(e,t){return this.each(function(n,a){a.attachEvent?a.attachEvent("on"+e,function(e){e.target=e.srcElement,t.call(a,e)}):a.addEventListener(e,t,!1)})},C.prototype.off=function(e,t){return this.each(function(n,a){a.detachEvent?a.detachEvent("on"+e,t):a.removeEventListener(e,t,!1)})},T.isLeapYear=function(e){return e%4===0&&e%100!==0||e%400===0},T.prototype.config={type:"date",range:!1,format:"yyyy-MM-dd",value:null,isInitValue:!0,min:"1900-1-1",max:"2099-12-31",trigger:"focus",show:!1,showBottom:!0,btns:["clear","now","confirm"],lang:"cn",theme:"default",position:null,calendar:!1,mark:{},zIndex:null,done:null,change:null},T.prototype.lang=function(){var e=this,t=e.config,n={cn:{weeks:["日","一","二","三","四","五","六"],time:["时","分","秒"],timeTips:"选择时间",startTime:"开始时间",endTime:"结束时间",dateTips:"返回日期",month:["一","二","三","四","五","六","七","八","九","十","十一","十二"],tools:{confirm:"确定",clear:"清空",now:"现在"}},en:{weeks:["Su","Mo","Tu","We","Th","Fr","Sa"],time:["Hours","Minutes","Seconds"],timeTips:"Select Time",startTime:"Start Time",endTime:"End Time",dateTips:"Select Date",month:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],tools:{confirm:"Confirm",clear:"Clear",now:"Now"}}};return n[t.lang]||n.cn},T.prototype.init=function(){var e=this,t=e.config,n="yyyy|y|MM|M|dd|d|HH|H|mm|m|ss|s",a="static"===t.position,i={year:"yyyy",month:"yyyy-MM",date:"yyyy-MM-dd",time:"HH:mm:ss",datetime:"yyyy-MM-dd HH:mm:ss"};t.elem=w(t.elem),t.eventElem=w(t.eventElem),t.elem[0]&&(t.range===!0&&(t.range="-"),t.format===i.date&&(t.format=i[t.type]),e.format=t.format.match(new RegExp(n+"|.","g"))||[],e.EXP_IF="",e.EXP_SPLIT="",w.each(e.format,function(t,a){var i=new RegExp(n).test(a)?"\\d{"+function(){return new RegExp(n).test(e.format[0===t?t+1:t-1]||"")?/^yyyy|y$/.test(a)?4:a.length:/^yyyy$/.test(a)?"1,4":/^y$/.test(a)?"1,308":"1,2"}()+"}":"\\"+a;e.EXP_IF=e.EXP_IF+i,e.EXP_SPLIT=e.EXP_SPLIT+"("+i+")"}),e.EXP_IF=new RegExp("^"+(t.range?e.EXP_IF+"\\s\\"+t.range+"\\s"+e.EXP_IF:e.EXP_IF)+"$"),e.EXP_SPLIT=new RegExp("^"+e.EXP_SPLIT+"$",""),e.isInput(t.elem[0])||"focus"===t.trigger&&(t.trigger="click"),t.elem.attr("lay-key")||(t.elem.attr("lay-key",e.index),t.eventElem.attr("lay-key",e.index)),t.mark=w.extend({},t.calendar&&"cn"===t.lang?{"0-1-1":"元旦","0-2-14":"情人","0-3-8":"妇女","0-3-12":"植树","0-4-1":"愚人","0-5-1":"劳动","0-5-4":"青年","0-6-1":"儿童","0-9-10":"教师","0-9-18":"国耻","0-10-1":"国庆","0-12-25":"圣诞"}:{},t.mark),w.each(["min","max"],function(e,n){var a=[],i=[];if("number"==typeof t[n]){var r=t[n],o=(new Date).getTime(),s=864e5,l=new Date(r?r<s?o+r*s:r:o);a=[l.getFullYear(),l.getMonth()+1,l.getDate()],r<s||(i=[l.getHours(),l.getMinutes(),l.getSeconds()])}else a=(t[n].match(/\d+-\d+-\d+/)||[""])[0].split("-"),i=(t[n].match(/\d+:\d+:\d+/)||[""])[0].split(":");t[n]={year:0|a[0]||(new Date).getFullYear(),month:a[1]?(0|a[1])-1:(new Date).getMonth(),date:0|a[2]||(new Date).getDate(),hours:0|i[0],minutes:0|i[1],seconds:0|i[2]}}),e.elemID="layui-laydate"+t.elem.attr("lay-key"),(t.show||a)&&e.render(),a||e.events(),t.value&&t.isInitValue&&(t.value.constructor===Date?e.setValue(e.parse(0,e.systemDate(t.value))):e.setValue(t.value)))},T.prototype.render=function(){var e=this,t=e.config,n=e.lang(),a="static"===t.position,i=e.elem=w.elem("div",{id:e.elemID,"class":["layui-laydate",t.range?" layui-laydate-range":"",a?" "+c:"",t.theme&&"default"!==t.theme&&!/^#/.test(t.theme)?" laydate-theme-"+t.theme:""].join("")}),r=e.elemMain=[],o=e.elemHeader=[],s=e.elemCont=[],l=e.table=[],d=e.footer=w.elem("div",{"class":p});if(t.zIndex&&(i.style.zIndex=t.zIndex),w.each(new Array(2),function(e){if(!t.range&&e>0)return!0;var a=w.elem("div",{"class":"layui-laydate-header"}),i=[function(){var e=w.elem("i",{"class":"layui-icon laydate-icon laydate-prev-y"});return e.innerHTML="&#xe65a;",e}(),function(){var e=w.elem("i",{"class":"layui-icon laydate-icon laydate-prev-m"});return e.innerHTML="&#xe603;",e}(),function(){var e=w.elem("div",{"class":"laydate-set-ym"}),t=w.elem("span"),n=w.elem("span");return e.appendChild(t),e.appendChild(n),e}(),function(){var e=w.elem("i",{"class":"layui-icon laydate-icon laydate-next-m"});return e.innerHTML="&#xe602;",e}(),function(){var e=w.elem("i",{"class":"layui-icon laydate-icon laydate-next-y"});return e.innerHTML="&#xe65b;",e}()],d=w.elem("div",{"class":"layui-laydate-content"}),c=w.elem("table"),m=w.elem("thead"),u=w.elem("tr");w.each(i,function(e,t){a.appendChild(t)}),m.appendChild(u),w.each(new Array(6),function(e){var t=c.insertRow(0);w.each(new Array(7),function(a){if(0===e){var i=w.elem("th");i.innerHTML=n.weeks[a],u.appendChild(i)}t.insertCell(a)})}),c.insertBefore(m,c.children[0]),d.appendChild(c),r[e]=w.elem("div",{"class":"layui-laydate-main laydate-main-list-"+e}),r[e].appendChild(a),r[e].appendChild(d),o.push(i),s.push(d),l.push(c)}),w(d).html(function(){var e=[],i=[];return"datetime"===t.type&&e.push('<span lay-type="datetime" class="laydate-btns-time">'+n.timeTips+"</span>"),w.each(t.btns,function(e,r){var o=n.tools[r]||"btn";t.range&&"now"===r||(a&&"clear"===r&&(o="cn"===t.lang?"重置":"Reset"),i.push('<span lay-type="'+r+'" class="laydate-btns-'+r+'">'+o+"</span>"))}),e.push('<div class="laydate-footer-btns">'+i.join("")+"</div>"),e.join("")}()),w.each(r,function(e,t){i.appendChild(t)}),t.showBottom&&i.appendChild(d),/^#/.test(t.theme)){var m=w.elem("style"),u=["#{{id}} .layui-laydate-header{background-color:{{theme}};}","#{{id}} .layui-this{background-color:{{theme}} !important;}"].join("").replace(/{{id}}/g,e.elemID).replace(/{{theme}}/g,t.theme);"styleSheet"in m?(m.setAttribute("type","text/css"),m.styleSheet.cssText=u):m.innerHTML=u,w(i).addClass("laydate-theme-molv"),i.appendChild(m)}e.remove(T.thisElemDate),a?t.elem.append(i):(document.body.appendChild(i),e.position()),e.checkDate().calendar(),e.changeEvent(),T.thisElemDate=e.elemID,"function"==typeof t.ready&&t.ready(w.extend({},t.dateTime,{month:t.dateTime.month+1}))},T.prototype.remove=function(e){var t=this,n=(t.config,w("#"+(e||t.elemID)));return n.hasClass(c)||t.checkDate(function(){n.remove()}),t},T.prototype.position=function(){var e=this,t=e.config,n=e.bindElem||t.elem[0],a=n.getBoundingClientRect(),i=e.elem.offsetWidth,r=e.elem.offsetHeight,o=function(e){return e=e?"scrollLeft":"scrollTop",document.body[e]|document.documentElement[e]},s=function(e){return document.documentElement[e?"clientWidth":"clientHeight"]},l=5,d=a.left,c=a.bottom;d+i+l>s("width")&&(d=s("width")-i-l),c+r+l>s()&&(c=a.top>r?a.top-r:s()-r,c-=2*l),t.position&&(e.elem.style.position=t.position),e.elem.style.left=d+("fixed"===t.position?0:o(1))+"px",e.elem.style.top=c+("fixed"===t.position?0:o())+"px"},T.prototype.hint=function(e){var t=this,n=(t.config,w.elem("div",{"class":h}));t.elem&&(n.innerHTML=e||"",w(t.elem).find("."+h).remove(),t.elem.appendChild(n),clearTimeout(t.hinTimer),t.hinTimer=setTimeout(function(){w(t.elem).find("."+h).remove()},3e3))},T.prototype.getAsYM=function(e,t,n){return n?t--:t++,t<0&&(t=11,e--),t>11&&(t=0,e++),[e,t]},T.prototype.systemDate=function(e){var t=e||new Date;return{year:t.getFullYear(),month:t.getMonth(),date:t.getDate(),hours:e?e.getHours():0,minutes:e?e.getMinutes():0,seconds:e?e.getSeconds():0}},T.prototype.checkDate=function(e){var t,a,i=this,r=(new Date,i.config),o=r.dateTime=r.dateTime||i.systemDate(),s=i.bindElem||r.elem[0],l=(i.isInput(s)?"val":"html",i.isInput(s)?s.value:"static"===r.position?"":s.innerHTML),c=function(e){e.year>d[1]&&(e.year=d[1],a=!0),e.month>11&&(e.month=11,a=!0),e.hours>23&&(e.hours=0,a=!0),e.minutes>59&&(e.minutes=0,e.hours++,a=!0),e.seconds>59&&(e.seconds=0,e.minutes++,a=!0),t=n.getEndDate(e.month+1,e.year),e.date>t&&(e.date=t,a=!0)},m=function(e,t,n){var o=["startTime","endTime"];t=(t.match(i.EXP_SPLIT)||[]).slice(1),n=n||0,r.range&&(i[o[n]]=i[o[n]]||{}),w.each(i.format,function(s,l){var c=parseFloat(t[s]);t[s].length<l.length&&(a=!0),/yyyy|y/.test(l)?(c<d[0]&&(c=d[0],a=!0),e.year=c):/MM|M/.test(l)?(c<1&&(c=1,a=!0),e.month=c-1):/dd|d/.test(l)?(c<1&&(c=1,a=!0),e.date=c):/HH|H/.test(l)?(c<1&&(c=0,a=!0),e.hours=c,r.range&&(i[o[n]].hours=c)):/mm|m/.test(l)?(c<1&&(c=0,a=!0),e.minutes=c,r.range&&(i[o[n]].minutes=c)):/ss|s/.test(l)&&(c<1&&(c=0,a=!0),e.seconds=c,r.range&&(i[o[n]].seconds=c))}),c(e)};return"limit"===e?(c(o),i):(l=l||r.value,"string"==typeof l&&(l=l.replace(/\s+/g," ").replace(/^\s|\s$/g,"")),i.startState&&!i.endState&&(delete i.startState,i.endState=!0),"string"==typeof l&&l?i.EXP_IF.test(l)?r.range?(l=l.split(" "+r.range+" "),i.startDate=i.startDate||i.systemDate(),i.endDate=i.endDate||i.systemDate(),r.dateTime=w.extend({},i.startDate),w.each([i.startDate,i.endDate],function(e,t){m(t,l[e],e)})):m(o,l):(i.hint("日期格式不合法<br>必须遵循下述格式：<br>"+(r.range?r.format+" "+r.range+" "+r.format:r.format)+"<br>已为你重置"),a=!0):l&&l.constructor===Date?r.dateTime=i.systemDate(l):(r.dateTime=i.systemDate(),delete i.startState,delete i.endState,delete i.startDate,delete i.endDate,delete i.startTime,delete i.endTime),c(o),a&&l&&i.setValue(r.range?i.endDate?i.parse():"":i.parse()),e&&e(),i)},T.prototype.mark=function(e,t){var n,a=this,i=a.config;return w.each(i.mark,function(e,a){var i=e.split("-");i[0]!=t[0]&&0!=i[0]||i[1]!=t[1]&&0!=i[1]||i[2]!=t[2]||(n=a||t[2])}),n&&e.html('<span class="laydate-day-mark">'+n+"</span>"),a},T.prototype.limit=function(e,t,n,a){var i,r=this,o=r.config,l={},d=o[n>41?"endDate":"dateTime"],c=w.extend({},d,t||{});return w.each({now:c,min:o.min,max:o.max},function(e,t){l[e]=r.newDate(w.extend({year:t.year,month:t.month,date:t.date},function(){var e={};return w.each(a,function(n,a){e[a]=t[a]}),e}())).getTime()}),i=l.now<l.min||l.now>l.max,e&&e[i?"addClass":"removeClass"](s),i},T.prototype.calendar=function(e){var t,a,i,r=this,s=r.config,l=e||s.dateTime,c=new Date,m=r.lang(),u="date"!==s.type&&"datetime"!==s.type,h=e?1:0,y=w(r.table[h]).find("td"),f=w(r.elemHeader[h][2]).find("span");if(l.year<d[0]&&(l.year=d[0],r.hint("最低只能支持到公元"+d[0]+"年")),l.year>d[1]&&(l.year=d[1],r.hint("最高只能支持到公元"+d[1]+"年")),r.firstDate||(r.firstDate=w.extend({},l)),c.setFullYear(l.year,l.month,1),t=c.getDay(),a=n.getEndDate(l.month||12,l.year),i=n.getEndDate(l.month+1,l.year),w.each(y,function(e,n){var d=[l.year,l.month],c=0;n=w(n),n.removeAttr("class"),e<t?(c=a-t+e,n.addClass("laydate-day-prev"),d=r.getAsYM(l.year,l.month,"sub")):e>=t&&e<i+t?(c=e-t,s.range||c+1===l.date&&n.addClass(o)):(c=e-i-t,n.addClass("laydate-day-next"),d=r.getAsYM(l.year,l.month)),d[1]++,d[2]=c+1,n.attr("lay-ymd",d.join("-")).html(d[2]),r.mark(n,d).limit(n,{year:d[0],month:d[1]-1,date:d[2]},e)}),w(f[0]).attr("lay-ym",l.year+"-"+(l.month+1)),w(f[1]).attr("lay-ym",l.year+"-"+(l.month+1)),"cn"===s.lang?(w(f[0]).attr("lay-type","year").html(l.year+"年"),w(f[1]).attr("lay-type","month").html(l.month+1+"月")):(w(f[0]).attr("lay-type","month").html(m.month[l.month]),w(f[1]).attr("lay-type","year").html(l.year)),u&&(s.range&&(e?r.endDate=r.endDate||{year:l.year+("year"===s.type?1:0),month:l.month+("month"===s.type?0:-1)}:r.startDate=r.startDate||{year:l.year,month:l.month},e&&(r.listYM=[[r.startDate.year,r.startDate.month+1],[r.endDate.year,r.endDate.month+1]],r.list(s.type,0).list(s.type,1),"time"===s.type?r.setBtnStatus("时间",w.extend({},r.systemDate(),r.startTime),w.extend({},r.systemDate(),r.endTime)):r.setBtnStatus(!0))),s.range||(r.listYM=[[l.year,l.month+1]],r.list(s.type,0))),s.range&&!e){var p=r.getAsYM(l.year,l.month);r.calendar(w.extend({},l,{year:p[0],month:p[1]}))}return s.range||r.limit(w(r.footer).find(g),null,0,["hours","minutes","seconds"]),s.range&&e&&!u&&r.stampRange(),r},T.prototype.list=function(e,t){var n=this,a=n.config,i=a.dateTime,r=n.lang(),l=a.range&&"date"!==a.type&&"datetime"!==a.type,d=w.elem("ul",{"class":m+" "+{year:"laydate-year-list",month:"laydate-month-list",time:"laydate-time-list"}[e]}),c=n.elemHeader[t],u=w(c[2]).find("span"),h=n.elemCont[t||0],y=w(h).find("."+m)[0],f="cn"===a.lang,p=f?"年":"",T=n.listYM[t]||{},C=["hours","minutes","seconds"],x=["startTime","endTime"][t];if(T[0]<1&&(T[0]=1),"year"===e){var M,b=M=T[0]-7;b<1&&(b=M=1),w.each(new Array(15),function(e){var i=w.elem("li",{"lay-ym":M}),r={year:M};M==T[0]&&w(i).addClass(o),i.innerHTML=M+p,d.appendChild(i),M<n.firstDate.year?(r.month=a.min.month,r.date=a.min.date):M>=n.firstDate.year&&(r.month=a.max.month,r.date=a.max.date),n.limit(w(i),r,t),M++}),w(u[f?0:1]).attr("lay-ym",M-8+"-"+T[1]).html(b+p+" - "+(M-1+p))}else if("month"===e)w.each(new Array(12),function(e){var i=w.elem("li",{"lay-ym":e}),s={year:T[0],month:e};e+1==T[1]&&w(i).addClass(o),i.innerHTML=r.month[e]+(f?"月":""),d.appendChild(i),T[0]<n.firstDate.year?s.date=a.min.date:T[0]>=n.firstDate.year&&(s.date=a.max.date),n.limit(w(i),s,t)}),w(u[f?0:1]).attr("lay-ym",T[0]+"-"+T[1]).html(T[0]+p);else if("time"===e){var E=function(){w(d).find("ol").each(function(e,a){w(a).find("li").each(function(a,i){n.limit(w(i),[{hours:a},{hours:n[x].hours,minutes:a},{hours:n[x].hours,minutes:n[x].minutes,seconds:a}][e],t,[["hours"],["hours","minutes"],["hours","minutes","seconds"]][e])})}),a.range||n.limit(w(n.footer).find(g),n[x],0,["hours","minutes","seconds"])};a.range?n[x]||(n[x]={hours:0,minutes:0,seconds:0}):n[x]=i,w.each([24,60,60],function(e,t){var a=w.elem("li"),i=["<p>"+r.time[e]+"</p><ol>"];w.each(new Array(t),function(t){i.push("<li"+(n[x][C[e]]===t?' class="'+o+'"':"")+">"+w.digit(t,2)+"</li>")}),a.innerHTML=i.join("")+"</ol>",d.appendChild(a)}),E()}if(y&&h.removeChild(y),h.appendChild(d),"year"===e||"month"===e)w(n.elemMain[t]).addClass("laydate-ym-show"),w(d).find("li").on("click",function(){var r=0|w(this).attr("lay-ym");if(!w(this).hasClass(s)){if(0===t)i[e]=r,l&&(n.startDate[e]=r),n.limit(w(n.footer).find(g),null,0);else if(l)n.endDate[e]=r;else{var c="year"===e?n.getAsYM(r,T[1]-1,"sub"):n.getAsYM(T[0],r,"sub");w.extend(i,{year:c[0],month:c[1]})}"year"===a.type||"month"===a.type?(w(d).find("."+o).removeClass(o),w(this).addClass(o),"month"===a.type&&"year"===e&&(n.listYM[t][0]=r,l&&(n[["startDate","endDate"][t]].year=r),n.list("month",t))):(n.checkDate("limit").calendar(),n.closeList()),n.setBtnStatus(),a.range||n.done(null,"change"),w(n.footer).find(D).removeClass(s)}});else{var S=w.elem("span",{"class":v}),k=function(){w(d).find("ol").each(function(e){var t=this,a=w(t).find("li");t.scrollTop=30*(n[x][C[e]]-2),t.scrollTop<=0&&a.each(function(e,n){if(!w(this).hasClass(s))return t.scrollTop=30*(e-2),!0})})},H=w(c[2]).find("."+v);k(),S.innerHTML=a.range?[r.startTime,r.endTime][t]:r.timeTips,w(n.elemMain[t]).addClass("laydate-time-show"),H[0]&&H.remove(),c[2].appendChild(S),w(d).find("ol").each(function(e){var t=this;w(t).find("li").on("click",function(){var r=0|this.innerHTML;w(this).hasClass(s)||(a.range?n[x][C[e]]=r:i[C[e]]=r,w(t).find("."+o).removeClass(o),w(this).addClass(o),E(),k(),(n.endDate||"time"===a.type)&&n.done(null,"change"),n.setBtnStatus())})})}return n},T.prototype.listYM=[],T.prototype.closeList=function(){var e=this;e.config;w.each(e.elemCont,function(t,n){w(this).find("."+m).remove(),w(e.elemMain[t]).removeClass("laydate-ym-show laydate-time-show")}),w(e.elem).find("."+v).remove()},T.prototype.setBtnStatus=function(e,t,n){var a,i=this,r=i.config,o=w(i.footer).find(g),d=r.range&&"date"!==r.type&&"time"!==r.type;d&&(t=t||i.startDate,n=n||i.endDate,a=i.newDate(t).getTime()>i.newDate(n).getTime(),i.limit(null,t)||i.limit(null,n)?o.addClass(s):o[a?"addClass":"removeClass"](s),e&&a&&i.hint("string"==typeof e?l.replace(/日期/g,e):l))},T.prototype.parse=function(e,t){var n=this,a=n.config,i=t||(e?w.extend({},n.endDate,n.endTime):a.range?w.extend({},n.startDate,n.startTime):a.dateTime),r=n.format.concat();return w.each(r,function(e,t){/yyyy|y/.test(t)?r[e]=w.digit(i.year,t.length):/MM|M/.test(t)?r[e]=w.digit(i.month+1,t.length):/dd|d/.test(t)?r[e]=w.digit(i.date,t.length):/HH|H/.test(t)?r[e]=w.digit(i.hours,t.length):/mm|m/.test(t)?r[e]=w.digit(i.minutes,t.length):/ss|s/.test(t)&&(r[e]=w.digit(i.seconds,t.length))}),a.range&&!e?r.join("")+" "+a.range+" "+n.parse(1):r.join("")},T.prototype.newDate=function(e){return e=e||{},new Date(e.year||1,e.month||0,e.date||1,e.hours||0,e.minutes||0,e.seconds||0)},T.prototype.setValue=function(e){var t=this,n=t.config,a=t.bindElem||n.elem[0],i=t.isInput(a)?"val":"html";return"static"===n.position||w(a)[i](e||""),this},T.prototype.stampRange=function(){var e,t,n=this,a=n.config,i=w(n.elem).find("td");if(a.range&&!n.endDate&&w(n.footer).find(g).addClass(s),n.endDate)return e=n.newDate({year:n.startDate.year,month:n.startDate.month,date:n.startDate.date}).getTime(),t=n.newDate({year:n.endDate.year,month:n.endDate.month,date:n.endDate.date}).getTime(),e>t?n.hint(l):void w.each(i,function(a,i){var r=w(i).attr("lay-ymd").split("-"),s=n.newDate({year:r[0],month:r[1]-1,date:r[2]}).getTime();w(i).removeClass(u+" "+o),s!==e&&s!==t||w(i).addClass(w(i).hasClass(y)||w(i).hasClass(f)?u:o),s>e&&s<t&&w(i).addClass(u)})},T.prototype.done=function(e,t){var n=this,a=n.config,i=w.extend({},n.startDate?w.extend(n.startDate,n.startTime):a.dateTime),r=w.extend({},w.extend(n.endDate,n.endTime));return w.each([i,r],function(e,t){"month"in t&&w.extend(t,{month:t.month+1})}),e=e||[n.parse(),i,r],"function"==typeof a[t||"done"]&&a[t||"done"].apply(a,e),n},T.prototype.choose=function(e){var t=this,n=t.config,a=n.dateTime,i=w(t.elem).find("td"),r=e.attr("lay-ymd").split("-"),l=function(e){new Date;e&&w.extend(a,r),n.range&&(t.startDate?w.extend(t.startDate,r):t.startDate=w.extend({},r,t.startTime),t.startYMD=r)};if(r={year:0|r[0],month:(0|r[1])-1,date:0|r[2]},!e.hasClass(s))if(n.range){if(w.each(["startTime","endTime"],function(e,n){t[n]=t[n]||{hours:0,minutes:0,seconds:0}}),t.endState)l(),delete t.endState,delete t.endDate,t.startState=!0,i.removeClass(o+" "+u),e.addClass(o);else if(t.startState){if(e.addClass(o),t.endDate?w.extend(t.endDate,r):t.endDate=w.extend({},r,t.endTime),t.newDate(r).getTime()<t.newDate(t.startYMD).getTime()){var d=w.extend({},t.endDate,{hours:t.startDate.hours,minutes:t.startDate.minutes,seconds:t.startDate.seconds});w.extend(t.endDate,t.startDate,{hours:t.endDate.hours,minutes:t.endDate.minutes,seconds:t.endDate.seconds}),t.startDate=d}n.showBottom||t.done(),t.stampRange(),t.endState=!0,t.done(null,"change")}else e.addClass(o),l(),t.startState=!0;w(t.footer).find(g)[t.endDate?"removeClass":"addClass"](s)}else"static"===n.position?(l(!0),t.calendar().done().done(null,"change")):"date"===n.type?(l(!0),t.setValue(t.parse()).remove().done()):"datetime"===n.type&&(l(!0),t.calendar().done(null,"change"))},T.prototype.tool=function(e,t){var n=this,a=n.config,i=a.dateTime,r="static"===a.position,o={datetime:function(){w(e).hasClass(s)||(n.list("time",0),a.range&&n.list("time",1),w(e).attr("lay-type","date").html(n.lang().dateTips))},date:function(){n.closeList(),w(e).attr("lay-type","datetime").html(n.lang().timeTips)},clear:function(){n.setValue("").remove(),r&&(w.extend(i,n.firstDate),n.calendar()),a.range&&(delete n.startState,delete n.endState,delete n.endDate,delete n.startTime,delete n.endTime),n.done(["",{},{}])},now:function(){var e=new Date;w.extend(i,n.systemDate(),{hours:e.getHours(),minutes:e.getMinutes(),seconds:e.getSeconds()}),n.setValue(n.parse()).remove(),r&&n.calendar(),n.done()},confirm:function(){if(a.range){if(!n.endDate)return n.hint("请先选择日期范围");if(w(e).hasClass(s))return n.hint("time"===a.type?l.replace(/日期/g,"时间"):l)}else if(w(e).hasClass(s))return n.hint("不在有效日期或时间范围内");n.done(),n.setValue(n.parse()).remove()}};o[t]&&o[t]()},T.prototype.change=function(e){var t=this,n=t.config,a=n.dateTime,i=n.range&&("year"===n.type||"month"===n.type),r=t.elemCont[e||0],o=t.listYM[e],s=function(s){var l=["startDate","endDate"][e],d=w(r).find(".laydate-year-list")[0],c=w(r).find(".laydate-month-list")[0];return d&&(o[0]=s?o[0]-15:o[0]+15,t.list("year",e)),c&&(s?o[0]--:o[0]++,t.list("month",e)),(d||c)&&(w.extend(a,{year:o[0]}),i&&(t[l].year=o[0]),n.range||t.done(null,"change"),t.setBtnStatus(),n.range||t.limit(w(t.footer).find(g),{year:o[0]})),d||c};return{prevYear:function(){s("sub")||(a.year--,t.checkDate("limit").calendar(),n.range||t.done(null,"change"))},prevMonth:function(){var e=t.getAsYM(a.year,a.month,"sub");w.extend(a,{year:e[0],month:e[1]}),t.checkDate("limit").calendar(),n.range||t.done(null,"change")},nextMonth:function(){var e=t.getAsYM(a.year,a.month);w.extend(a,{year:e[0],month:e[1]}),t.checkDate("limit").calendar(),n.range||t.done(null,"change")},nextYear:function(){s()||(a.year++,t.checkDate("limit").calendar(),n.range||t.done(null,"change"))}}},T.prototype.changeEvent=function(){var e=this;e.config;w(e.elem).on("click",function(e){w.stope(e)}),w.each(e.elemHeader,function(t,n){w(n[0]).on("click",function(n){e.change(t).prevYear()}),w(n[1]).on("click",function(n){e.change(t).prevMonth()}),w(n[2]).find("span").on("click",function(n){var a=w(this),i=a.attr("lay-ym"),r=a.attr("lay-type");i&&(i=i.split("-"),e.listYM[t]=[0|i[0],0|i[1]],e.list(r,t),w(e.footer).find(D).addClass(s))}),w(n[3]).on("click",function(n){e.change(t).nextMonth()}),w(n[4]).on("click",function(n){e.change(t).nextYear()})}),w.each(e.table,function(t,n){var a=w(n).find("td");a.on("click",function(){e.choose(w(this))})}),w(e.footer).find("span").on("click",function(){var t=w(this).attr("lay-type");e.tool(this,t)})},T.prototype.isInput=function(e){return/input|textarea/.test(e.tagName.toLocaleLowerCase())},T.prototype.events=function(){var e=this,t=e.config,n=function(n,a){n.on(t.trigger,function(){a&&(e.bindElem=this),e.render()})};t.elem[0]&&!t.elem[0].eventHandler&&(n(t.elem,"bind"),n(t.eventElem),w(document).on("click",function(n){n.target!==t.elem[0]&&n.target!==t.eventElem[0]&&n.target!==w(t.closeStop)[0]&&e.remove()}).on("keydown",function(t){13===t.keyCode&&w("#"+e.elemID)[0]&&e.elemID===T.thisElem&&(t.preventDefault(),w(e.footer).find(g)[0].click())}),w(window).on("resize",function(){return!(!e.elem||!w(r)[0])&&void e.position()}),t.elem[0].eventHandler=!0)},n.render=function(e){var t=new T(e);return a.call(t)},n.getEndDate=function(e,t){var n=new Date;return n.setFullYear(t||n.getFullYear(),e||n.getMonth()+1,1),new Date(n.getTime()-864e5).getDate()},window.lay=window.lay||w,e?(n.ready(),layui.define(function(e){n.path=layui.cache.dir,e(i,n)})):"function"==typeof define&&define.amd?define(function(){return n}):function(){n.ready(),window.laydate=n}()}();!function(e,t){"object"==typeof module&&"object"==typeof module.exports?module.exports=e.document?t(e,!0):function(e){if(!e.document)throw new Error("jQuery requires a window with a document");return t(e)}:t(e)}("undefined"!=typeof window?window:this,function(e,t){function n(e){var t=!!e&&"length"in e&&e.length,n=pe.type(e);return"function"!==n&&!pe.isWindow(e)&&("array"===n||0===t||"number"==typeof t&&t>0&&t-1 in e)}function r(e,t,n){if(pe.isFunction(t))return pe.grep(e,function(e,r){return!!t.call(e,r,e)!==n});if(t.nodeType)return pe.grep(e,function(e){return e===t!==n});if("string"==typeof t){if(Ce.test(t))return pe.filter(t,e,n);t=pe.filter(t,e)}return pe.grep(e,function(e){return pe.inArray(e,t)>-1!==n})}function i(e,t){do e=e[t];while(e&&1!==e.nodeType);return e}function o(e){var t={};return pe.each(e.match(De)||[],function(e,n){t[n]=!0}),t}function a(){re.addEventListener?(re.removeEventListener("DOMContentLoaded",s),e.removeEventListener("load",s)):(re.detachEvent("onreadystatechange",s),e.detachEvent("onload",s))}function s(){(re.addEventListener||"load"===e.event.type||"complete"===re.readyState)&&(a(),pe.ready())}function u(e,t,n){if(void 0===n&&1===e.nodeType){var r="data-"+t.replace(_e,"-$1").toLowerCase();if(n=e.getAttribute(r),"string"==typeof n){try{n="true"===n||"false"!==n&&("null"===n?null:+n+""===n?+n:qe.test(n)?pe.parseJSON(n):n)}catch(i){}pe.data(e,t,n)}else n=void 0}return n}function l(e){var t;for(t in e)if(("data"!==t||!pe.isEmptyObject(e[t]))&&"toJSON"!==t)return!1;return!0}function c(e,t,n,r){if(He(e)){var i,o,a=pe.expando,s=e.nodeType,u=s?pe.cache:e,l=s?e[a]:e[a]&&a;if(l&&u[l]&&(r||u[l].data)||void 0!==n||"string"!=typeof t)return l||(l=s?e[a]=ne.pop()||pe.guid++:a),u[l]||(u[l]=s?{}:{toJSON:pe.noop}),"object"!=typeof t&&"function"!=typeof t||(r?u[l]=pe.extend(u[l],t):u[l].data=pe.extend(u[l].data,t)),o=u[l],r||(o.data||(o.data={}),o=o.data),void 0!==n&&(o[pe.camelCase(t)]=n),"string"==typeof t?(i=o[t],null==i&&(i=o[pe.camelCase(t)])):i=o,i}}function f(e,t,n){if(He(e)){var r,i,o=e.nodeType,a=o?pe.cache:e,s=o?e[pe.expando]:pe.expando;if(a[s]){if(t&&(r=n?a[s]:a[s].data)){pe.isArray(t)?t=t.concat(pe.map(t,pe.camelCase)):t in r?t=[t]:(t=pe.camelCase(t),t=t in r?[t]:t.split(" ")),i=t.length;for(;i--;)delete r[t[i]];if(n?!l(r):!pe.isEmptyObject(r))return}(n||(delete a[s].data,l(a[s])))&&(o?pe.cleanData([e],!0):fe.deleteExpando||a!=a.window?delete a[s]:a[s]=void 0)}}}function d(e,t,n,r){var i,o=1,a=20,s=r?function(){return r.cur()}:function(){return pe.css(e,t,"")},u=s(),l=n&&n[3]||(pe.cssNumber[t]?"":"px"),c=(pe.cssNumber[t]||"px"!==l&&+u)&&Me.exec(pe.css(e,t));if(c&&c[3]!==l){l=l||c[3],n=n||[],c=+u||1;do o=o||".5",c/=o,pe.style(e,t,c+l);while(o!==(o=s()/u)&&1!==o&&--a)}return n&&(c=+c||+u||0,i=n[1]?c+(n[1]+1)*n[2]:+n[2],r&&(r.unit=l,r.start=c,r.end=i)),i}function p(e){var t=ze.split("|"),n=e.createDocumentFragment();if(n.createElement)for(;t.length;)n.createElement(t.pop());return n}function h(e,t){var n,r,i=0,o="undefined"!=typeof e.getElementsByTagName?e.getElementsByTagName(t||"*"):"undefined"!=typeof e.querySelectorAll?e.querySelectorAll(t||"*"):void 0;if(!o)for(o=[],n=e.childNodes||e;null!=(r=n[i]);i++)!t||pe.nodeName(r,t)?o.push(r):pe.merge(o,h(r,t));return void 0===t||t&&pe.nodeName(e,t)?pe.merge([e],o):o}function g(e,t){for(var n,r=0;null!=(n=e[r]);r++)pe._data(n,"globalEval",!t||pe._data(t[r],"globalEval"))}function m(e){Be.test(e.type)&&(e.defaultChecked=e.checked)}function y(e,t,n,r,i){for(var o,a,s,u,l,c,f,d=e.length,y=p(t),v=[],x=0;x<d;x++)if(a=e[x],a||0===a)if("object"===pe.type(a))pe.merge(v,a.nodeType?[a]:a);else if(Ue.test(a)){for(u=u||y.appendChild(t.createElement("div")),l=(We.exec(a)||["",""])[1].toLowerCase(),f=Xe[l]||Xe._default,u.innerHTML=f[1]+pe.htmlPrefilter(a)+f[2],o=f[0];o--;)u=u.lastChild;if(!fe.leadingWhitespace&&$e.test(a)&&v.push(t.createTextNode($e.exec(a)[0])),!fe.tbody)for(a="table"!==l||Ve.test(a)?"<table>"!==f[1]||Ve.test(a)?0:u:u.firstChild,o=a&&a.childNodes.length;o--;)pe.nodeName(c=a.childNodes[o],"tbody")&&!c.childNodes.length&&a.removeChild(c);for(pe.merge(v,u.childNodes),u.textContent="";u.firstChild;)u.removeChild(u.firstChild);u=y.lastChild}else v.push(t.createTextNode(a));for(u&&y.removeChild(u),fe.appendChecked||pe.grep(h(v,"input"),m),x=0;a=v[x++];)if(r&&pe.inArray(a,r)>-1)i&&i.push(a);else if(s=pe.contains(a.ownerDocument,a),u=h(y.appendChild(a),"script"),s&&g(u),n)for(o=0;a=u[o++];)Ie.test(a.type||"")&&n.push(a);return u=null,y}function v(){return!0}function x(){return!1}function b(){try{return re.activeElement}catch(e){}}function w(e,t,n,r,i,o){var a,s;if("object"==typeof t){"string"!=typeof n&&(r=r||n,n=void 0);for(s in t)w(e,s,n,r,t[s],o);return e}if(null==r&&null==i?(i=n,r=n=void 0):null==i&&("string"==typeof n?(i=r,r=void 0):(i=r,r=n,n=void 0)),i===!1)i=x;else if(!i)return e;return 1===o&&(a=i,i=function(e){return pe().off(e),a.apply(this,arguments)},i.guid=a.guid||(a.guid=pe.guid++)),e.each(function(){pe.event.add(this,t,i,r,n)})}function T(e,t){return pe.nodeName(e,"table")&&pe.nodeName(11!==t.nodeType?t:t.firstChild,"tr")?e.getElementsByTagName("tbody")[0]||e.appendChild(e.ownerDocument.createElement("tbody")):e}function C(e){return e.type=(null!==pe.find.attr(e,"type"))+"/"+e.type,e}function E(e){var t=it.exec(e.type);return t?e.type=t[1]:e.removeAttribute("type"),e}function N(e,t){if(1===t.nodeType&&pe.hasData(e)){var n,r,i,o=pe._data(e),a=pe._data(t,o),s=o.events;if(s){delete a.handle,a.events={};for(n in s)for(r=0,i=s[n].length;r<i;r++)pe.event.add(t,n,s[n][r])}a.data&&(a.data=pe.extend({},a.data))}}function k(e,t){var n,r,i;if(1===t.nodeType){if(n=t.nodeName.toLowerCase(),!fe.noCloneEvent&&t[pe.expando]){i=pe._data(t);for(r in i.events)pe.removeEvent(t,r,i.handle);t.removeAttribute(pe.expando)}"script"===n&&t.text!==e.text?(C(t).text=e.text,E(t)):"object"===n?(t.parentNode&&(t.outerHTML=e.outerHTML),fe.html5Clone&&e.innerHTML&&!pe.trim(t.innerHTML)&&(t.innerHTML=e.innerHTML)):"input"===n&&Be.test(e.type)?(t.defaultChecked=t.checked=e.checked,t.value!==e.value&&(t.value=e.value)):"option"===n?t.defaultSelected=t.selected=e.defaultSelected:"input"!==n&&"textarea"!==n||(t.defaultValue=e.defaultValue)}}function S(e,t,n,r){t=oe.apply([],t);var i,o,a,s,u,l,c=0,f=e.length,d=f-1,p=t[0],g=pe.isFunction(p);if(g||f>1&&"string"==typeof p&&!fe.checkClone&&rt.test(p))return e.each(function(i){var o=e.eq(i);g&&(t[0]=p.call(this,i,o.html())),S(o,t,n,r)});if(f&&(l=y(t,e[0].ownerDocument,!1,e,r),i=l.firstChild,1===l.childNodes.length&&(l=i),i||r)){for(s=pe.map(h(l,"script"),C),a=s.length;c<f;c++)o=l,c!==d&&(o=pe.clone(o,!0,!0),a&&pe.merge(s,h(o,"script"))),n.call(e[c],o,c);if(a)for(u=s[s.length-1].ownerDocument,pe.map(s,E),c=0;c<a;c++)o=s[c],Ie.test(o.type||"")&&!pe._data(o,"globalEval")&&pe.contains(u,o)&&(o.src?pe._evalUrl&&pe._evalUrl(o.src):pe.globalEval((o.text||o.textContent||o.innerHTML||"").replace(ot,"")));l=i=null}return e}function A(e,t,n){for(var r,i=t?pe.filter(t,e):e,o=0;null!=(r=i[o]);o++)n||1!==r.nodeType||pe.cleanData(h(r)),r.parentNode&&(n&&pe.contains(r.ownerDocument,r)&&g(h(r,"script")),r.parentNode.removeChild(r));return e}function D(e,t){var n=pe(t.createElement(e)).appendTo(t.body),r=pe.css(n[0],"display");return n.detach(),r}function j(e){var t=re,n=lt[e];return n||(n=D(e,t),"none"!==n&&n||(ut=(ut||pe("<iframe frameborder='0' width='0' height='0'/>")).appendTo(t.documentElement),t=(ut[0].contentWindow||ut[0].contentDocument).document,t.write(),t.close(),n=D(e,t),ut.detach()),lt[e]=n),n}function L(e,t){return{get:function(){return e()?void delete this.get:(this.get=t).apply(this,arguments)}}}function H(e){if(e in Et)return e;for(var t=e.charAt(0).toUpperCase()+e.slice(1),n=Ct.length;n--;)if(e=Ct[n]+t,e in Et)return e}function q(e,t){for(var n,r,i,o=[],a=0,s=e.length;a<s;a++)r=e[a],r.style&&(o[a]=pe._data(r,"olddisplay"),n=r.style.display,t?(o[a]||"none"!==n||(r.style.display=""),""===r.style.display&&Re(r)&&(o[a]=pe._data(r,"olddisplay",j(r.nodeName)))):(i=Re(r),(n&&"none"!==n||!i)&&pe._data(r,"olddisplay",i?n:pe.css(r,"display"))));for(a=0;a<s;a++)r=e[a],r.style&&(t&&"none"!==r.style.display&&""!==r.style.display||(r.style.display=t?o[a]||"":"none"));return e}function _(e,t,n){var r=bt.exec(t);return r?Math.max(0,r[1]-(n||0))+(r[2]||"px"):t}function F(e,t,n,r,i){for(var o=n===(r?"border":"content")?4:"width"===t?1:0,a=0;o<4;o+=2)"margin"===n&&(a+=pe.css(e,n+Oe[o],!0,i)),r?("content"===n&&(a-=pe.css(e,"padding"+Oe[o],!0,i)),"margin"!==n&&(a-=pe.css(e,"border"+Oe[o]+"Width",!0,i))):(a+=pe.css(e,"padding"+Oe[o],!0,i),"padding"!==n&&(a+=pe.css(e,"border"+Oe[o]+"Width",!0,i)));return a}function M(t,n,r){var i=!0,o="width"===n?t.offsetWidth:t.offsetHeight,a=ht(t),s=fe.boxSizing&&"border-box"===pe.css(t,"boxSizing",!1,a);if(re.msFullscreenElement&&e.top!==e&&t.getClientRects().length&&(o=Math.round(100*t.getBoundingClientRect()[n])),o<=0||null==o){if(o=gt(t,n,a),(o<0||null==o)&&(o=t.style[n]),ft.test(o))return o;i=s&&(fe.boxSizingReliable()||o===t.style[n]),o=parseFloat(o)||0}return o+F(t,n,r||(s?"border":"content"),i,a)+"px"}function O(e,t,n,r,i){return new O.prototype.init(e,t,n,r,i)}function R(){return e.setTimeout(function(){Nt=void 0}),Nt=pe.now()}function P(e,t){var n,r={height:e},i=0;for(t=t?1:0;i<4;i+=2-t)n=Oe[i],r["margin"+n]=r["padding"+n]=e;return t&&(r.opacity=r.width=e),r}function B(e,t,n){for(var r,i=($.tweeners[t]||[]).concat($.tweeners["*"]),o=0,a=i.length;o<a;o++)if(r=i[o].call(n,t,e))return r}function W(e,t,n){var r,i,o,a,s,u,l,c,f=this,d={},p=e.style,h=e.nodeType&&Re(e),g=pe._data(e,"fxshow");n.queue||(s=pe._queueHooks(e,"fx"),null==s.unqueued&&(s.unqueued=0,u=s.empty.fire,s.empty.fire=function(){s.unqueued||u()}),s.unqueued++,f.always(function(){f.always(function(){s.unqueued--,pe.queue(e,"fx").length||s.empty.fire()})})),1===e.nodeType&&("height"in t||"width"in t)&&(n.overflow=[p.overflow,p.overflowX,p.overflowY],l=pe.css(e,"display"),c="none"===l?pe._data(e,"olddisplay")||j(e.nodeName):l,"inline"===c&&"none"===pe.css(e,"float")&&(fe.inlineBlockNeedsLayout&&"inline"!==j(e.nodeName)?p.zoom=1:p.display="inline-block")),n.overflow&&(p.overflow="hidden",fe.shrinkWrapBlocks()||f.always(function(){p.overflow=n.overflow[0],p.overflowX=n.overflow[1],p.overflowY=n.overflow[2]}));for(r in t)if(i=t[r],St.exec(i)){if(delete t[r],o=o||"toggle"===i,i===(h?"hide":"show")){if("show"!==i||!g||void 0===g[r])continue;h=!0}d[r]=g&&g[r]||pe.style(e,r)}else l=void 0;if(pe.isEmptyObject(d))"inline"===("none"===l?j(e.nodeName):l)&&(p.display=l);else{g?"hidden"in g&&(h=g.hidden):g=pe._data(e,"fxshow",{}),o&&(g.hidden=!h),h?pe(e).show():f.done(function(){pe(e).hide()}),f.done(function(){var t;pe._removeData(e,"fxshow");for(t in d)pe.style(e,t,d[t])});for(r in d)a=B(h?g[r]:0,r,f),r in g||(g[r]=a.start,h&&(a.end=a.start,a.start="width"===r||"height"===r?1:0))}}function I(e,t){var n,r,i,o,a;for(n in e)if(r=pe.camelCase(n),i=t[r],o=e[n],pe.isArray(o)&&(i=o[1],o=e[n]=o[0]),n!==r&&(e[r]=o,delete e[n]),a=pe.cssHooks[r],a&&"expand"in a){o=a.expand(o),delete e[r];for(n in o)n in e||(e[n]=o[n],t[n]=i)}else t[r]=i}function $(e,t,n){var r,i,o=0,a=$.prefilters.length,s=pe.Deferred().always(function(){delete u.elem}),u=function(){if(i)return!1;for(var t=Nt||R(),n=Math.max(0,l.startTime+l.duration-t),r=n/l.duration||0,o=1-r,a=0,u=l.tweens.length;a<u;a++)l.tweens[a].run(o);return s.notifyWith(e,[l,o,n]),o<1&&u?n:(s.resolveWith(e,[l]),!1)},l=s.promise({elem:e,props:pe.extend({},t),opts:pe.extend(!0,{specialEasing:{},easing:pe.easing._default},n),originalProperties:t,originalOptions:n,startTime:Nt||R(),duration:n.duration,tweens:[],createTween:function(t,n){var r=pe.Tween(e,l.opts,t,n,l.opts.specialEasing[t]||l.opts.easing);return l.tweens.push(r),r},stop:function(t){var n=0,r=t?l.tweens.length:0;if(i)return this;for(i=!0;n<r;n++)l.tweens[n].run(1);return t?(s.notifyWith(e,[l,1,0]),s.resolveWith(e,[l,t])):s.rejectWith(e,[l,t]),this}}),c=l.props;for(I(c,l.opts.specialEasing);o<a;o++)if(r=$.prefilters[o].call(l,e,c,l.opts))return pe.isFunction(r.stop)&&(pe._queueHooks(l.elem,l.opts.queue).stop=pe.proxy(r.stop,r)),r;return pe.map(c,B,l),pe.isFunction(l.opts.start)&&l.opts.start.call(e,l),pe.fx.timer(pe.extend(u,{elem:e,anim:l,queue:l.opts.queue})),l.progress(l.opts.progress).done(l.opts.done,l.opts.complete).fail(l.opts.fail).always(l.opts.always)}function z(e){return pe.attr(e,"class")||""}function X(e){return function(t,n){"string"!=typeof t&&(n=t,t="*");var r,i=0,o=t.toLowerCase().match(De)||[];if(pe.isFunction(n))for(;r=o[i++];)"+"===r.charAt(0)?(r=r.slice(1)||"*",(e[r]=e[r]||[]).unshift(n)):(e[r]=e[r]||[]).push(n)}}function U(e,t,n,r){function i(s){var u;return o[s]=!0,pe.each(e[s]||[],function(e,s){var l=s(t,n,r);return"string"!=typeof l||a||o[l]?a?!(u=l):void 0:(t.dataTypes.unshift(l),i(l),!1)}),u}var o={},a=e===Qt;return i(t.dataTypes[0])||!o["*"]&&i("*")}function V(e,t){var n,r,i=pe.ajaxSettings.flatOptions||{};for(r in t)void 0!==t[r]&&((i[r]?e:n||(n={}))[r]=t[r]);return n&&pe.extend(!0,e,n),e}function Y(e,t,n){for(var r,i,o,a,s=e.contents,u=e.dataTypes;"*"===u[0];)u.shift(),void 0===i&&(i=e.mimeType||t.getResponseHeader("Content-Type"));if(i)for(a in s)if(s[a]&&s[a].test(i)){u.unshift(a);break}if(u[0]in n)o=u[0];else{for(a in n){if(!u[0]||e.converters[a+" "+u[0]]){o=a;break}r||(r=a)}o=o||r}if(o)return o!==u[0]&&u.unshift(o),n[o]}function J(e,t,n,r){var i,o,a,s,u,l={},c=e.dataTypes.slice();if(c[1])for(a in e.converters)l[a.toLowerCase()]=e.converters[a];for(o=c.shift();o;)if(e.responseFields[o]&&(n[e.responseFields[o]]=t),!u&&r&&e.dataFilter&&(t=e.dataFilter(t,e.dataType)),u=o,o=c.shift())if("*"===o)o=u;else if("*"!==u&&u!==o){if(a=l[u+" "+o]||l["* "+o],!a)for(i in l)if(s=i.split(" "),s[1]===o&&(a=l[u+" "+s[0]]||l["* "+s[0]])){a===!0?a=l[i]:l[i]!==!0&&(o=s[0],c.unshift(s[1]));break}if(a!==!0)if(a&&e["throws"])t=a(t);else try{t=a(t)}catch(f){return{state:"parsererror",error:a?f:"No conversion from "+u+" to "+o}}}return{state:"success",data:t}}function G(e){return e.style&&e.style.display||pe.css(e,"display")}function K(e){for(;e&&1===e.nodeType;){if("none"===G(e)||"hidden"===e.type)return!0;e=e.parentNode}return!1}function Q(e,t,n,r){var i;if(pe.isArray(t))pe.each(t,function(t,i){n||rn.test(e)?r(e,i):Q(e+"["+("object"==typeof i&&null!=i?t:"")+"]",i,n,r)});else if(n||"object"!==pe.type(t))r(e,t);else for(i in t)Q(e+"["+i+"]",t[i],n,r)}function Z(){try{return new e.XMLHttpRequest}catch(t){}}function ee(){try{return new e.ActiveXObject("Microsoft.XMLHTTP")}catch(t){}}function te(e){return pe.isWindow(e)?e:9===e.nodeType&&(e.defaultView||e.parentWindow)}var ne=[],re=e.document,ie=ne.slice,oe=ne.concat,ae=ne.push,se=ne.indexOf,ue={},le=ue.toString,ce=ue.hasOwnProperty,fe={},de="1.12.3",pe=function(e,t){return new pe.fn.init(e,t)},he=/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,ge=/^-ms-/,me=/-([\da-z])/gi,ye=function(e,t){return t.toUpperCase()};pe.fn=pe.prototype={jquery:de,constructor:pe,selector:"",length:0,toArray:function(){return ie.call(this)},get:function(e){return null!=e?e<0?this[e+this.length]:this[e]:ie.call(this)},pushStack:function(e){var t=pe.merge(this.constructor(),e);return t.prevObject=this,t.context=this.context,t},each:function(e){return pe.each(this,e)},map:function(e){return this.pushStack(pe.map(this,function(t,n){return e.call(t,n,t)}))},slice:function(){return this.pushStack(ie.apply(this,arguments))},first:function(){return this.eq(0)},last:function(){return this.eq(-1)},eq:function(e){var t=this.length,n=+e+(e<0?t:0);return this.pushStack(n>=0&&n<t?[this[n]]:[])},end:function(){return this.prevObject||this.constructor()},push:ae,sort:ne.sort,splice:ne.splice},pe.extend=pe.fn.extend=function(){var e,t,n,r,i,o,a=arguments[0]||{},s=1,u=arguments.length,l=!1;for("boolean"==typeof a&&(l=a,a=arguments[s]||{},s++),"object"==typeof a||pe.isFunction(a)||(a={}),s===u&&(a=this,s--);s<u;s++)if(null!=(i=arguments[s]))for(r in i)e=a[r],n=i[r],a!==n&&(l&&n&&(pe.isPlainObject(n)||(t=pe.isArray(n)))?(t?(t=!1,o=e&&pe.isArray(e)?e:[]):o=e&&pe.isPlainObject(e)?e:{},a[r]=pe.extend(l,o,n)):void 0!==n&&(a[r]=n));return a},pe.extend({expando:"jQuery"+(de+Math.random()).replace(/\D/g,""),isReady:!0,error:function(e){throw new Error(e)},noop:function(){},isFunction:function(e){return"function"===pe.type(e)},isArray:Array.isArray||function(e){return"array"===pe.type(e)},isWindow:function(e){return null!=e&&e==e.window},isNumeric:function(e){var t=e&&e.toString();return!pe.isArray(e)&&t-parseFloat(t)+1>=0},isEmptyObject:function(e){var t;for(t in e)return!1;return!0},isPlainObject:function(e){var t;if(!e||"object"!==pe.type(e)||e.nodeType||pe.isWindow(e))return!1;try{if(e.constructor&&!ce.call(e,"constructor")&&!ce.call(e.constructor.prototype,"isPrototypeOf"))return!1}catch(n){return!1}if(!fe.ownFirst)for(t in e)return ce.call(e,t);for(t in e);return void 0===t||ce.call(e,t)},type:function(e){return null==e?e+"":"object"==typeof e||"function"==typeof e?ue[le.call(e)]||"object":typeof e},globalEval:function(t){t&&pe.trim(t)&&(e.execScript||function(t){e.eval.call(e,t)})(t)},camelCase:function(e){return e.replace(ge,"ms-").replace(me,ye)},nodeName:function(e,t){return e.nodeName&&e.nodeName.toLowerCase()===t.toLowerCase()},each:function(e,t){var r,i=0;if(n(e))for(r=e.length;i<r&&t.call(e[i],i,e[i])!==!1;i++);else for(i in e)if(t.call(e[i],i,e[i])===!1)break;return e},trim:function(e){return null==e?"":(e+"").replace(he,"")},makeArray:function(e,t){var r=t||[];return null!=e&&(n(Object(e))?pe.merge(r,"string"==typeof e?[e]:e):ae.call(r,e)),r},inArray:function(e,t,n){var r;if(t){if(se)return se.call(t,e,n);for(r=t.length,n=n?n<0?Math.max(0,r+n):n:0;n<r;n++)if(n in t&&t[n]===e)return n}return-1},merge:function(e,t){for(var n=+t.length,r=0,i=e.length;r<n;)e[i++]=t[r++];if(n!==n)for(;void 0!==t[r];)e[i++]=t[r++];return e.length=i,e},grep:function(e,t,n){for(var r,i=[],o=0,a=e.length,s=!n;o<a;o++)r=!t(e[o],o),r!==s&&i.push(e[o]);return i},map:function(e,t,r){var i,o,a=0,s=[];if(n(e))for(i=e.length;a<i;a++)o=t(e[a],a,r),null!=o&&s.push(o);else for(a in e)o=t(e[a],a,r),null!=o&&s.push(o);return oe.apply([],s)},guid:1,proxy:function(e,t){var n,r,i;if("string"==typeof t&&(i=e[t],t=e,e=i),pe.isFunction(e))return n=ie.call(arguments,2),r=function(){return e.apply(t||this,n.concat(ie.call(arguments)))},r.guid=e.guid=e.guid||pe.guid++,r},now:function(){return+new Date},support:fe}),"function"==typeof Symbol&&(pe.fn[Symbol.iterator]=ne[Symbol.iterator]),pe.each("Boolean Number String Function Array Date RegExp Object Error Symbol".split(" "),function(e,t){ue["[object "+t+"]"]=t.toLowerCase()});var ve=function(e){function t(e,t,n,r){var i,o,a,s,u,l,f,p,h=t&&t.ownerDocument,g=t?t.nodeType:9;if(n=n||[],"string"!=typeof e||!e||1!==g&&9!==g&&11!==g)return n;if(!r&&((t?t.ownerDocument||t:B)!==H&&L(t),t=t||H,_)){if(11!==g&&(l=ye.exec(e)))if(i=l[1]){if(9===g){if(!(a=t.getElementById(i)))return n;if(a.id===i)return n.push(a),n}else if(h&&(a=h.getElementById(i))&&R(t,a)&&a.id===i)return n.push(a),n}else{if(l[2])return Q.apply(n,t.getElementsByTagName(e)),n;if((i=l[3])&&w.getElementsByClassName&&t.getElementsByClassName)return Q.apply(n,t.getElementsByClassName(i)),n}if(w.qsa&&!X[e+" "]&&(!F||!F.test(e))){if(1!==g)h=t,p=e;else if("object"!==t.nodeName.toLowerCase()){for((s=t.getAttribute("id"))?s=s.replace(xe,"\\$&"):t.setAttribute("id",s=P),f=N(e),o=f.length,u=de.test(s)?"#"+s:"[id='"+s+"']";o--;)f[o]=u+" "+d(f[o]);p=f.join(","),h=ve.test(e)&&c(t.parentNode)||t}if(p)try{return Q.apply(n,h.querySelectorAll(p)),n}catch(m){}finally{s===P&&t.removeAttribute("id")}}}return S(e.replace(se,"$1"),t,n,r)}function n(){function e(n,r){return t.push(n+" ")>T.cacheLength&&delete e[t.shift()],e[n+" "]=r}var t=[];return e}function r(e){return e[P]=!0,e}function i(e){var t=H.createElement("div");try{return!!e(t)}catch(n){return!1}finally{t.parentNode&&t.parentNode.removeChild(t),t=null}}function o(e,t){for(var n=e.split("|"),r=n.length;r--;)T.attrHandle[n[r]]=t}function a(e,t){var n=t&&e,r=n&&1===e.nodeType&&1===t.nodeType&&(~t.sourceIndex||V)-(~e.sourceIndex||V);if(r)return r;if(n)for(;n=n.nextSibling;)if(n===t)return-1;return e?1:-1}function s(e){return function(t){var n=t.nodeName.toLowerCase();return"input"===n&&t.type===e}}function u(e){return function(t){var n=t.nodeName.toLowerCase();return("input"===n||"button"===n)&&t.type===e}}function l(e){return r(function(t){return t=+t,r(function(n,r){for(var i,o=e([],n.length,t),a=o.length;a--;)n[i=o[a]]&&(n[i]=!(r[i]=n[i]))})})}function c(e){return e&&"undefined"!=typeof e.getElementsByTagName&&e}function f(){}function d(e){for(var t=0,n=e.length,r="";t<n;t++)r+=e[t].value;return r}function p(e,t,n){var r=t.dir,i=n&&"parentNode"===r,o=I++;return t.first?function(t,n,o){for(;t=t[r];)if(1===t.nodeType||i)return e(t,n,o)}:function(t,n,a){var s,u,l,c=[W,o];if(a){for(;t=t[r];)if((1===t.nodeType||i)&&e(t,n,a))return!0}else for(;t=t[r];)if(1===t.nodeType||i){if(l=t[P]||(t[P]={}),u=l[t.uniqueID]||(l[t.uniqueID]={}),(s=u[r])&&s[0]===W&&s[1]===o)return c[2]=s[2];if(u[r]=c,c[2]=e(t,n,a))return!0}}}function h(e){return e.length>1?function(t,n,r){for(var i=e.length;i--;)if(!e[i](t,n,r))return!1;return!0}:e[0]}function g(e,n,r){for(var i=0,o=n.length;i<o;i++)t(e,n[i],r);return r}function m(e,t,n,r,i){for(var o,a=[],s=0,u=e.length,l=null!=t;s<u;s++)(o=e[s])&&(n&&!n(o,r,i)||(a.push(o),l&&t.push(s)));return a}function y(e,t,n,i,o,a){return i&&!i[P]&&(i=y(i)),o&&!o[P]&&(o=y(o,a)),r(function(r,a,s,u){var l,c,f,d=[],p=[],h=a.length,y=r||g(t||"*",s.nodeType?[s]:s,[]),v=!e||!r&&t?y:m(y,d,e,s,u),x=n?o||(r?e:h||i)?[]:a:v;if(n&&n(v,x,s,u),i)for(l=m(x,p),i(l,[],s,u),c=l.length;c--;)(f=l[c])&&(x[p[c]]=!(v[p[c]]=f));if(r){if(o||e){if(o){for(l=[],c=x.length;c--;)(f=x[c])&&l.push(v[c]=f);o(null,x=[],l,u)}for(c=x.length;c--;)(f=x[c])&&(l=o?ee(r,f):d[c])>-1&&(r[l]=!(a[l]=f))}}else x=m(x===a?x.splice(h,x.length):x),o?o(null,a,x,u):Q.apply(a,x)})}function v(e){for(var t,n,r,i=e.length,o=T.relative[e[0].type],a=o||T.relative[" "],s=o?1:0,u=p(function(e){return e===t},a,!0),l=p(function(e){return ee(t,e)>-1},a,!0),c=[function(e,n,r){var i=!o&&(r||n!==A)||((t=n).nodeType?u(e,n,r):l(e,n,r));return t=null,i}];s<i;s++)if(n=T.relative[e[s].type])c=[p(h(c),n)];else{if(n=T.filter[e[s].type].apply(null,e[s].matches),n[P]){for(r=++s;r<i&&!T.relative[e[r].type];r++);return y(s>1&&h(c),s>1&&d(e.slice(0,s-1).concat({value:" "===e[s-2].type?"*":""})).replace(se,"$1"),n,s<r&&v(e.slice(s,r)),r<i&&v(e=e.slice(r)),r<i&&d(e))}c.push(n)}return h(c)}function x(e,n){var i=n.length>0,o=e.length>0,a=function(r,a,s,u,l){var c,f,d,p=0,h="0",g=r&&[],y=[],v=A,x=r||o&&T.find.TAG("*",l),b=W+=null==v?1:Math.random()||.1,w=x.length;for(l&&(A=a===H||a||l);h!==w&&null!=(c=x[h]);h++){if(o&&c){for(f=0,a||c.ownerDocument===H||(L(c),s=!_);d=e[f++];)if(d(c,a||H,s)){u.push(c);break}l&&(W=b)}i&&((c=!d&&c)&&p--,r&&g.push(c))}if(p+=h,i&&h!==p){for(f=0;d=n[f++];)d(g,y,a,s);if(r){if(p>0)for(;h--;)g[h]||y[h]||(y[h]=G.call(u));y=m(y)}Q.apply(u,y),l&&!r&&y.length>0&&p+n.length>1&&t.uniqueSort(u)}return l&&(W=b,A=v),g};return i?r(a):a}var b,w,T,C,E,N,k,S,A,D,j,L,H,q,_,F,M,O,R,P="sizzle"+1*new Date,B=e.document,W=0,I=0,$=n(),z=n(),X=n(),U=function(e,t){return e===t&&(j=!0),0},V=1<<31,Y={}.hasOwnProperty,J=[],G=J.pop,K=J.push,Q=J.push,Z=J.slice,ee=function(e,t){for(var n=0,r=e.length;n<r;n++)if(e[n]===t)return n;return-1},te="checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped",ne="[\\x20\\t\\r\\n\\f]",re="(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+",ie="\\["+ne+"*("+re+")(?:"+ne+"*([*^$|!~]?=)"+ne+"*(?:'((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\"|("+re+"))|)"+ne+"*\\]",oe=":("+re+")(?:\\((('((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\")|((?:\\\\.|[^\\\\()[\\]]|"+ie+")*)|.*)\\)|)",ae=new RegExp(ne+"+","g"),se=new RegExp("^"+ne+"+|((?:^|[^\\\\])(?:\\\\.)*)"+ne+"+$","g"),ue=new RegExp("^"+ne+"*,"+ne+"*"),le=new RegExp("^"+ne+"*([>+~]|"+ne+")"+ne+"*"),ce=new RegExp("="+ne+"*([^\\]'\"]*?)"+ne+"*\\]","g"),fe=new RegExp(oe),de=new RegExp("^"+re+"$"),pe={ID:new RegExp("^#("+re+")"),CLASS:new RegExp("^\\.("+re+")"),TAG:new RegExp("^("+re+"|[*])"),ATTR:new RegExp("^"+ie),PSEUDO:new RegExp("^"+oe),CHILD:new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\("+ne+"*(even|odd|(([+-]|)(\\d*)n|)"+ne+"*(?:([+-]|)"+ne+"*(\\d+)|))"+ne+"*\\)|)","i"),bool:new RegExp("^(?:"+te+")$","i"),needsContext:new RegExp("^"+ne+"*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\("+ne+"*((?:-\\d)?\\d*)"+ne+"*\\)|)(?=[^-]|$)","i")},he=/^(?:input|select|textarea|button)$/i,ge=/^h\d$/i,me=/^[^{]+\{\s*\[native \w/,ye=/^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/,ve=/[+~]/,xe=/'|\\/g,be=new RegExp("\\\\([\\da-f]{1,6}"+ne+"?|("+ne+")|.)","ig"),we=function(e,t,n){var r="0x"+t-65536;return r!==r||n?t:r<0?String.fromCharCode(r+65536):String.fromCharCode(r>>10|55296,1023&r|56320)},Te=function(){L()};try{Q.apply(J=Z.call(B.childNodes),B.childNodes),J[B.childNodes.length].nodeType}catch(Ce){Q={apply:J.length?function(e,t){K.apply(e,Z.call(t))}:function(e,t){for(var n=e.length,r=0;e[n++]=t[r++];);e.length=n-1}}}w=t.support={},E=t.isXML=function(e){var t=e&&(e.ownerDocument||e).documentElement;return!!t&&"HTML"!==t.nodeName},L=t.setDocument=function(e){var t,n,r=e?e.ownerDocument||e:B;return r!==H&&9===r.nodeType&&r.documentElement?(H=r,q=H.documentElement,_=!E(H),(n=H.defaultView)&&n.top!==n&&(n.addEventListener?n.addEventListener("unload",Te,!1):n.attachEvent&&n.attachEvent("onunload",Te)),w.attributes=i(function(e){return e.className="i",!e.getAttribute("className")}),w.getElementsByTagName=i(function(e){return e.appendChild(H.createComment("")),!e.getElementsByTagName("*").length}),w.getElementsByClassName=me.test(H.getElementsByClassName),w.getById=i(function(e){return q.appendChild(e).id=P,!H.getElementsByName||!H.getElementsByName(P).length}),w.getById?(T.find.ID=function(e,t){if("undefined"!=typeof t.getElementById&&_){var n=t.getElementById(e);return n?[n]:[]}},T.filter.ID=function(e){var t=e.replace(be,we);return function(e){return e.getAttribute("id")===t}}):(delete T.find.ID,T.filter.ID=function(e){var t=e.replace(be,we);return function(e){var n="undefined"!=typeof e.getAttributeNode&&e.getAttributeNode("id");return n&&n.value===t}}),T.find.TAG=w.getElementsByTagName?function(e,t){return"undefined"!=typeof t.getElementsByTagName?t.getElementsByTagName(e):w.qsa?t.querySelectorAll(e):void 0}:function(e,t){var n,r=[],i=0,o=t.getElementsByTagName(e);if("*"===e){for(;n=o[i++];)1===n.nodeType&&r.push(n);return r}return o},T.find.CLASS=w.getElementsByClassName&&function(e,t){if("undefined"!=typeof t.getElementsByClassName&&_)return t.getElementsByClassName(e)},M=[],F=[],(w.qsa=me.test(H.querySelectorAll))&&(i(function(e){q.appendChild(e).innerHTML="<a id='"+P+"'></a><select id='"+P+"-\r\\' msallowcapture=''><option selected=''></option></select>",e.querySelectorAll("[msallowcapture^='']").length&&F.push("[*^$]="+ne+"*(?:''|\"\")"),e.querySelectorAll("[selected]").length||F.push("\\["+ne+"*(?:value|"+te+")"),e.querySelectorAll("[id~="+P+"-]").length||F.push("~="),e.querySelectorAll(":checked").length||F.push(":checked"),e.querySelectorAll("a#"+P+"+*").length||F.push(".#.+[+~]")}),i(function(e){var t=H.createElement("input");t.setAttribute("type","hidden"),e.appendChild(t).setAttribute("name","D"),e.querySelectorAll("[name=d]").length&&F.push("name"+ne+"*[*^$|!~]?="),e.querySelectorAll(":enabled").length||F.push(":enabled",":disabled"),e.querySelectorAll("*,:x"),F.push(",.*:")})),(w.matchesSelector=me.test(O=q.matches||q.webkitMatchesSelector||q.mozMatchesSelector||q.oMatchesSelector||q.msMatchesSelector))&&i(function(e){w.disconnectedMatch=O.call(e,"div"),O.call(e,"[s!='']:x"),M.push("!=",oe)}),F=F.length&&new RegExp(F.join("|")),M=M.length&&new RegExp(M.join("|")),t=me.test(q.compareDocumentPosition),R=t||me.test(q.contains)?function(e,t){var n=9===e.nodeType?e.documentElement:e,r=t&&t.parentNode;return e===r||!(!r||1!==r.nodeType||!(n.contains?n.contains(r):e.compareDocumentPosition&&16&e.compareDocumentPosition(r)))}:function(e,t){if(t)for(;t=t.parentNode;)if(t===e)return!0;return!1},U=t?function(e,t){if(e===t)return j=!0,0;var n=!e.compareDocumentPosition-!t.compareDocumentPosition;return n?n:(n=(e.ownerDocument||e)===(t.ownerDocument||t)?e.compareDocumentPosition(t):1,1&n||!w.sortDetached&&t.compareDocumentPosition(e)===n?e===H||e.ownerDocument===B&&R(B,e)?-1:t===H||t.ownerDocument===B&&R(B,t)?1:D?ee(D,e)-ee(D,t):0:4&n?-1:1)}:function(e,t){if(e===t)return j=!0,0;var n,r=0,i=e.parentNode,o=t.parentNode,s=[e],u=[t];if(!i||!o)return e===H?-1:t===H?1:i?-1:o?1:D?ee(D,e)-ee(D,t):0;if(i===o)return a(e,t);for(n=e;n=n.parentNode;)s.unshift(n);for(n=t;n=n.parentNode;)u.unshift(n);for(;s[r]===u[r];)r++;return r?a(s[r],u[r]):s[r]===B?-1:u[r]===B?1:0},H):H},t.matches=function(e,n){return t(e,null,null,n)},t.matchesSelector=function(e,n){if((e.ownerDocument||e)!==H&&L(e),n=n.replace(ce,"='$1']"),w.matchesSelector&&_&&!X[n+" "]&&(!M||!M.test(n))&&(!F||!F.test(n)))try{var r=O.call(e,n);if(r||w.disconnectedMatch||e.document&&11!==e.document.nodeType)return r}catch(i){}return t(n,H,null,[e]).length>0},t.contains=function(e,t){return(e.ownerDocument||e)!==H&&L(e),R(e,t)},t.attr=function(e,t){(e.ownerDocument||e)!==H&&L(e);var n=T.attrHandle[t.toLowerCase()],r=n&&Y.call(T.attrHandle,t.toLowerCase())?n(e,t,!_):void 0;return void 0!==r?r:w.attributes||!_?e.getAttribute(t):(r=e.getAttributeNode(t))&&r.specified?r.value:null},t.error=function(e){throw new Error("Syntax error, unrecognized expression: "+e)},t.uniqueSort=function(e){var t,n=[],r=0,i=0;if(j=!w.detectDuplicates,D=!w.sortStable&&e.slice(0),e.sort(U),j){for(;t=e[i++];)t===e[i]&&(r=n.push(i));for(;r--;)e.splice(n[r],1)}return D=null,e},C=t.getText=function(e){var t,n="",r=0,i=e.nodeType;if(i){if(1===i||9===i||11===i){if("string"==typeof e.textContent)return e.textContent;for(e=e.firstChild;e;e=e.nextSibling)n+=C(e)}else if(3===i||4===i)return e.nodeValue}else for(;t=e[r++];)n+=C(t);return n},T=t.selectors={cacheLength:50,createPseudo:r,match:pe,attrHandle:{},find:{},relative:{">":{dir:"parentNode",first:!0}," ":{dir:"parentNode"},"+":{dir:"previousSibling",first:!0},"~":{dir:"previousSibling"}},preFilter:{ATTR:function(e){return e[1]=e[1].replace(be,we),e[3]=(e[3]||e[4]||e[5]||"").replace(be,we),"~="===e[2]&&(e[3]=" "+e[3]+" "),e.slice(0,4)},CHILD:function(e){return e[1]=e[1].toLowerCase(),"nth"===e[1].slice(0,3)?(e[3]||t.error(e[0]),e[4]=+(e[4]?e[5]+(e[6]||1):2*("even"===e[3]||"odd"===e[3])),e[5]=+(e[7]+e[8]||"odd"===e[3])):e[3]&&t.error(e[0]),e},PSEUDO:function(e){var t,n=!e[6]&&e[2];return pe.CHILD.test(e[0])?null:(e[3]?e[2]=e[4]||e[5]||"":n&&fe.test(n)&&(t=N(n,!0))&&(t=n.indexOf(")",n.length-t)-n.length)&&(e[0]=e[0].slice(0,t),e[2]=n.slice(0,t)),e.slice(0,3))}},filter:{TAG:function(e){var t=e.replace(be,we).toLowerCase();return"*"===e?function(){return!0}:function(e){return e.nodeName&&e.nodeName.toLowerCase()===t}},CLASS:function(e){var t=$[e+" "];return t||(t=new RegExp("(^|"+ne+")"+e+"("+ne+"|$)"))&&$(e,function(e){return t.test("string"==typeof e.className&&e.className||"undefined"!=typeof e.getAttribute&&e.getAttribute("class")||"")})},ATTR:function(e,n,r){return function(i){var o=t.attr(i,e);return null==o?"!="===n:!n||(o+="","="===n?o===r:"!="===n?o!==r:"^="===n?r&&0===o.indexOf(r):"*="===n?r&&o.indexOf(r)>-1:"$="===n?r&&o.slice(-r.length)===r:"~="===n?(" "+o.replace(ae," ")+" ").indexOf(r)>-1:"|="===n&&(o===r||o.slice(0,r.length+1)===r+"-"))}},CHILD:function(e,t,n,r,i){var o="nth"!==e.slice(0,3),a="last"!==e.slice(-4),s="of-type"===t;return 1===r&&0===i?function(e){return!!e.parentNode}:function(t,n,u){var l,c,f,d,p,h,g=o!==a?"nextSibling":"previousSibling",m=t.parentNode,y=s&&t.nodeName.toLowerCase(),v=!u&&!s,x=!1;if(m){if(o){for(;g;){for(d=t;d=d[g];)if(s?d.nodeName.toLowerCase()===y:1===d.nodeType)return!1;h=g="only"===e&&!h&&"nextSibling"}return!0}if(h=[a?m.firstChild:m.lastChild],a&&v){for(d=m,f=d[P]||(d[P]={}),c=f[d.uniqueID]||(f[d.uniqueID]={}),
l=c[e]||[],p=l[0]===W&&l[1],x=p&&l[2],d=p&&m.childNodes[p];d=++p&&d&&d[g]||(x=p=0)||h.pop();)if(1===d.nodeType&&++x&&d===t){c[e]=[W,p,x];break}}else if(v&&(d=t,f=d[P]||(d[P]={}),c=f[d.uniqueID]||(f[d.uniqueID]={}),l=c[e]||[],p=l[0]===W&&l[1],x=p),x===!1)for(;(d=++p&&d&&d[g]||(x=p=0)||h.pop())&&((s?d.nodeName.toLowerCase()!==y:1!==d.nodeType)||!++x||(v&&(f=d[P]||(d[P]={}),c=f[d.uniqueID]||(f[d.uniqueID]={}),c[e]=[W,x]),d!==t)););return x-=i,x===r||x%r===0&&x/r>=0}}},PSEUDO:function(e,n){var i,o=T.pseudos[e]||T.setFilters[e.toLowerCase()]||t.error("unsupported pseudo: "+e);return o[P]?o(n):o.length>1?(i=[e,e,"",n],T.setFilters.hasOwnProperty(e.toLowerCase())?r(function(e,t){for(var r,i=o(e,n),a=i.length;a--;)r=ee(e,i[a]),e[r]=!(t[r]=i[a])}):function(e){return o(e,0,i)}):o}},pseudos:{not:r(function(e){var t=[],n=[],i=k(e.replace(se,"$1"));return i[P]?r(function(e,t,n,r){for(var o,a=i(e,null,r,[]),s=e.length;s--;)(o=a[s])&&(e[s]=!(t[s]=o))}):function(e,r,o){return t[0]=e,i(t,null,o,n),t[0]=null,!n.pop()}}),has:r(function(e){return function(n){return t(e,n).length>0}}),contains:r(function(e){return e=e.replace(be,we),function(t){return(t.textContent||t.innerText||C(t)).indexOf(e)>-1}}),lang:r(function(e){return de.test(e||"")||t.error("unsupported lang: "+e),e=e.replace(be,we).toLowerCase(),function(t){var n;do if(n=_?t.lang:t.getAttribute("xml:lang")||t.getAttribute("lang"))return n=n.toLowerCase(),n===e||0===n.indexOf(e+"-");while((t=t.parentNode)&&1===t.nodeType);return!1}}),target:function(t){var n=e.location&&e.location.hash;return n&&n.slice(1)===t.id},root:function(e){return e===q},focus:function(e){return e===H.activeElement&&(!H.hasFocus||H.hasFocus())&&!!(e.type||e.href||~e.tabIndex)},enabled:function(e){return e.disabled===!1},disabled:function(e){return e.disabled===!0},checked:function(e){var t=e.nodeName.toLowerCase();return"input"===t&&!!e.checked||"option"===t&&!!e.selected},selected:function(e){return e.parentNode&&e.parentNode.selectedIndex,e.selected===!0},empty:function(e){for(e=e.firstChild;e;e=e.nextSibling)if(e.nodeType<6)return!1;return!0},parent:function(e){return!T.pseudos.empty(e)},header:function(e){return ge.test(e.nodeName)},input:function(e){return he.test(e.nodeName)},button:function(e){var t=e.nodeName.toLowerCase();return"input"===t&&"button"===e.type||"button"===t},text:function(e){var t;return"input"===e.nodeName.toLowerCase()&&"text"===e.type&&(null==(t=e.getAttribute("type"))||"text"===t.toLowerCase())},first:l(function(){return[0]}),last:l(function(e,t){return[t-1]}),eq:l(function(e,t,n){return[n<0?n+t:n]}),even:l(function(e,t){for(var n=0;n<t;n+=2)e.push(n);return e}),odd:l(function(e,t){for(var n=1;n<t;n+=2)e.push(n);return e}),lt:l(function(e,t,n){for(var r=n<0?n+t:n;--r>=0;)e.push(r);return e}),gt:l(function(e,t,n){for(var r=n<0?n+t:n;++r<t;)e.push(r);return e})}},T.pseudos.nth=T.pseudos.eq;for(b in{radio:!0,checkbox:!0,file:!0,password:!0,image:!0})T.pseudos[b]=s(b);for(b in{submit:!0,reset:!0})T.pseudos[b]=u(b);return f.prototype=T.filters=T.pseudos,T.setFilters=new f,N=t.tokenize=function(e,n){var r,i,o,a,s,u,l,c=z[e+" "];if(c)return n?0:c.slice(0);for(s=e,u=[],l=T.preFilter;s;){r&&!(i=ue.exec(s))||(i&&(s=s.slice(i[0].length)||s),u.push(o=[])),r=!1,(i=le.exec(s))&&(r=i.shift(),o.push({value:r,type:i[0].replace(se," ")}),s=s.slice(r.length));for(a in T.filter)!(i=pe[a].exec(s))||l[a]&&!(i=l[a](i))||(r=i.shift(),o.push({value:r,type:a,matches:i}),s=s.slice(r.length));if(!r)break}return n?s.length:s?t.error(e):z(e,u).slice(0)},k=t.compile=function(e,t){var n,r=[],i=[],o=X[e+" "];if(!o){for(t||(t=N(e)),n=t.length;n--;)o=v(t[n]),o[P]?r.push(o):i.push(o);o=X(e,x(i,r)),o.selector=e}return o},S=t.select=function(e,t,n,r){var i,o,a,s,u,l="function"==typeof e&&e,f=!r&&N(e=l.selector||e);if(n=n||[],1===f.length){if(o=f[0]=f[0].slice(0),o.length>2&&"ID"===(a=o[0]).type&&w.getById&&9===t.nodeType&&_&&T.relative[o[1].type]){if(t=(T.find.ID(a.matches[0].replace(be,we),t)||[])[0],!t)return n;l&&(t=t.parentNode),e=e.slice(o.shift().value.length)}for(i=pe.needsContext.test(e)?0:o.length;i--&&(a=o[i],!T.relative[s=a.type]);)if((u=T.find[s])&&(r=u(a.matches[0].replace(be,we),ve.test(o[0].type)&&c(t.parentNode)||t))){if(o.splice(i,1),e=r.length&&d(o),!e)return Q.apply(n,r),n;break}}return(l||k(e,f))(r,t,!_,n,!t||ve.test(e)&&c(t.parentNode)||t),n},w.sortStable=P.split("").sort(U).join("")===P,w.detectDuplicates=!!j,L(),w.sortDetached=i(function(e){return 1&e.compareDocumentPosition(H.createElement("div"))}),i(function(e){return e.innerHTML="<a href='#'></a>","#"===e.firstChild.getAttribute("href")})||o("type|href|height|width",function(e,t,n){if(!n)return e.getAttribute(t,"type"===t.toLowerCase()?1:2)}),w.attributes&&i(function(e){return e.innerHTML="<input/>",e.firstChild.setAttribute("value",""),""===e.firstChild.getAttribute("value")})||o("value",function(e,t,n){if(!n&&"input"===e.nodeName.toLowerCase())return e.defaultValue}),i(function(e){return null==e.getAttribute("disabled")})||o(te,function(e,t,n){var r;if(!n)return e[t]===!0?t.toLowerCase():(r=e.getAttributeNode(t))&&r.specified?r.value:null}),t}(e);pe.find=ve,pe.expr=ve.selectors,pe.expr[":"]=pe.expr.pseudos,pe.uniqueSort=pe.unique=ve.uniqueSort,pe.text=ve.getText,pe.isXMLDoc=ve.isXML,pe.contains=ve.contains;var xe=function(e,t,n){for(var r=[],i=void 0!==n;(e=e[t])&&9!==e.nodeType;)if(1===e.nodeType){if(i&&pe(e).is(n))break;r.push(e)}return r},be=function(e,t){for(var n=[];e;e=e.nextSibling)1===e.nodeType&&e!==t&&n.push(e);return n},we=pe.expr.match.needsContext,Te=/^<([\w-]+)\s*\/?>(?:<\/\1>|)$/,Ce=/^.[^:#\[\.,]*$/;pe.filter=function(e,t,n){var r=t[0];return n&&(e=":not("+e+")"),1===t.length&&1===r.nodeType?pe.find.matchesSelector(r,e)?[r]:[]:pe.find.matches(e,pe.grep(t,function(e){return 1===e.nodeType}))},pe.fn.extend({find:function(e){var t,n=[],r=this,i=r.length;if("string"!=typeof e)return this.pushStack(pe(e).filter(function(){for(t=0;t<i;t++)if(pe.contains(r[t],this))return!0}));for(t=0;t<i;t++)pe.find(e,r[t],n);return n=this.pushStack(i>1?pe.unique(n):n),n.selector=this.selector?this.selector+" "+e:e,n},filter:function(e){return this.pushStack(r(this,e||[],!1))},not:function(e){return this.pushStack(r(this,e||[],!0))},is:function(e){return!!r(this,"string"==typeof e&&we.test(e)?pe(e):e||[],!1).length}});var Ee,Ne=/^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/,ke=pe.fn.init=function(e,t,n){var r,i;if(!e)return this;if(n=n||Ee,"string"==typeof e){if(r="<"===e.charAt(0)&&">"===e.charAt(e.length-1)&&e.length>=3?[null,e,null]:Ne.exec(e),!r||!r[1]&&t)return!t||t.jquery?(t||n).find(e):this.constructor(t).find(e);if(r[1]){if(t=t instanceof pe?t[0]:t,pe.merge(this,pe.parseHTML(r[1],t&&t.nodeType?t.ownerDocument||t:re,!0)),Te.test(r[1])&&pe.isPlainObject(t))for(r in t)pe.isFunction(this[r])?this[r](t[r]):this.attr(r,t[r]);return this}if(i=re.getElementById(r[2]),i&&i.parentNode){if(i.id!==r[2])return Ee.find(e);this.length=1,this[0]=i}return this.context=re,this.selector=e,this}return e.nodeType?(this.context=this[0]=e,this.length=1,this):pe.isFunction(e)?"undefined"!=typeof n.ready?n.ready(e):e(pe):(void 0!==e.selector&&(this.selector=e.selector,this.context=e.context),pe.makeArray(e,this))};ke.prototype=pe.fn,Ee=pe(re);var Se=/^(?:parents|prev(?:Until|All))/,Ae={children:!0,contents:!0,next:!0,prev:!0};pe.fn.extend({has:function(e){var t,n=pe(e,this),r=n.length;return this.filter(function(){for(t=0;t<r;t++)if(pe.contains(this,n[t]))return!0})},closest:function(e,t){for(var n,r=0,i=this.length,o=[],a=we.test(e)||"string"!=typeof e?pe(e,t||this.context):0;r<i;r++)for(n=this[r];n&&n!==t;n=n.parentNode)if(n.nodeType<11&&(a?a.index(n)>-1:1===n.nodeType&&pe.find.matchesSelector(n,e))){o.push(n);break}return this.pushStack(o.length>1?pe.uniqueSort(o):o)},index:function(e){return e?"string"==typeof e?pe.inArray(this[0],pe(e)):pe.inArray(e.jquery?e[0]:e,this):this[0]&&this[0].parentNode?this.first().prevAll().length:-1},add:function(e,t){return this.pushStack(pe.uniqueSort(pe.merge(this.get(),pe(e,t))))},addBack:function(e){return this.add(null==e?this.prevObject:this.prevObject.filter(e))}}),pe.each({parent:function(e){var t=e.parentNode;return t&&11!==t.nodeType?t:null},parents:function(e){return xe(e,"parentNode")},parentsUntil:function(e,t,n){return xe(e,"parentNode",n)},next:function(e){return i(e,"nextSibling")},prev:function(e){return i(e,"previousSibling")},nextAll:function(e){return xe(e,"nextSibling")},prevAll:function(e){return xe(e,"previousSibling")},nextUntil:function(e,t,n){return xe(e,"nextSibling",n)},prevUntil:function(e,t,n){return xe(e,"previousSibling",n)},siblings:function(e){return be((e.parentNode||{}).firstChild,e)},children:function(e){return be(e.firstChild)},contents:function(e){return pe.nodeName(e,"iframe")?e.contentDocument||e.contentWindow.document:pe.merge([],e.childNodes)}},function(e,t){pe.fn[e]=function(n,r){var i=pe.map(this,t,n);return"Until"!==e.slice(-5)&&(r=n),r&&"string"==typeof r&&(i=pe.filter(r,i)),this.length>1&&(Ae[e]||(i=pe.uniqueSort(i)),Se.test(e)&&(i=i.reverse())),this.pushStack(i)}});var De=/\S+/g;pe.Callbacks=function(e){e="string"==typeof e?o(e):pe.extend({},e);var t,n,r,i,a=[],s=[],u=-1,l=function(){for(i=e.once,r=t=!0;s.length;u=-1)for(n=s.shift();++u<a.length;)a[u].apply(n[0],n[1])===!1&&e.stopOnFalse&&(u=a.length,n=!1);e.memory||(n=!1),t=!1,i&&(a=n?[]:"")},c={add:function(){return a&&(n&&!t&&(u=a.length-1,s.push(n)),function r(t){pe.each(t,function(t,n){pe.isFunction(n)?e.unique&&c.has(n)||a.push(n):n&&n.length&&"string"!==pe.type(n)&&r(n)})}(arguments),n&&!t&&l()),this},remove:function(){return pe.each(arguments,function(e,t){for(var n;(n=pe.inArray(t,a,n))>-1;)a.splice(n,1),n<=u&&u--}),this},has:function(e){return e?pe.inArray(e,a)>-1:a.length>0},empty:function(){return a&&(a=[]),this},disable:function(){return i=s=[],a=n="",this},disabled:function(){return!a},lock:function(){return i=!0,n||c.disable(),this},locked:function(){return!!i},fireWith:function(e,n){return i||(n=n||[],n=[e,n.slice?n.slice():n],s.push(n),t||l()),this},fire:function(){return c.fireWith(this,arguments),this},fired:function(){return!!r}};return c},pe.extend({Deferred:function(e){var t=[["resolve","done",pe.Callbacks("once memory"),"resolved"],["reject","fail",pe.Callbacks("once memory"),"rejected"],["notify","progress",pe.Callbacks("memory")]],n="pending",r={state:function(){return n},always:function(){return i.done(arguments).fail(arguments),this},then:function(){var e=arguments;return pe.Deferred(function(n){pe.each(t,function(t,o){var a=pe.isFunction(e[t])&&e[t];i[o[1]](function(){var e=a&&a.apply(this,arguments);e&&pe.isFunction(e.promise)?e.promise().progress(n.notify).done(n.resolve).fail(n.reject):n[o[0]+"With"](this===r?n.promise():this,a?[e]:arguments)})}),e=null}).promise()},promise:function(e){return null!=e?pe.extend(e,r):r}},i={};return r.pipe=r.then,pe.each(t,function(e,o){var a=o[2],s=o[3];r[o[1]]=a.add,s&&a.add(function(){n=s},t[1^e][2].disable,t[2][2].lock),i[o[0]]=function(){return i[o[0]+"With"](this===i?r:this,arguments),this},i[o[0]+"With"]=a.fireWith}),r.promise(i),e&&e.call(i,i),i},when:function(e){var t,n,r,i=0,o=ie.call(arguments),a=o.length,s=1!==a||e&&pe.isFunction(e.promise)?a:0,u=1===s?e:pe.Deferred(),l=function(e,n,r){return function(i){n[e]=this,r[e]=arguments.length>1?ie.call(arguments):i,r===t?u.notifyWith(n,r):--s||u.resolveWith(n,r)}};if(a>1)for(t=new Array(a),n=new Array(a),r=new Array(a);i<a;i++)o[i]&&pe.isFunction(o[i].promise)?o[i].promise().progress(l(i,n,t)).done(l(i,r,o)).fail(u.reject):--s;return s||u.resolveWith(r,o),u.promise()}});var je;pe.fn.ready=function(e){return pe.ready.promise().done(e),this},pe.extend({isReady:!1,readyWait:1,holdReady:function(e){e?pe.readyWait++:pe.ready(!0)},ready:function(e){(e===!0?--pe.readyWait:pe.isReady)||(pe.isReady=!0,e!==!0&&--pe.readyWait>0||(je.resolveWith(re,[pe]),pe.fn.triggerHandler&&(pe(re).triggerHandler("ready"),pe(re).off("ready"))))}}),pe.ready.promise=function(t){if(!je)if(je=pe.Deferred(),"complete"===re.readyState||"loading"!==re.readyState&&!re.documentElement.doScroll)e.setTimeout(pe.ready);else if(re.addEventListener)re.addEventListener("DOMContentLoaded",s),e.addEventListener("load",s);else{re.attachEvent("onreadystatechange",s),e.attachEvent("onload",s);var n=!1;try{n=null==e.frameElement&&re.documentElement}catch(r){}n&&n.doScroll&&!function i(){if(!pe.isReady){try{n.doScroll("left")}catch(t){return e.setTimeout(i,50)}a(),pe.ready()}}()}return je.promise(t)},pe.ready.promise();var Le;for(Le in pe(fe))break;fe.ownFirst="0"===Le,fe.inlineBlockNeedsLayout=!1,pe(function(){var e,t,n,r;n=re.getElementsByTagName("body")[0],n&&n.style&&(t=re.createElement("div"),r=re.createElement("div"),r.style.cssText="position:absolute;border:0;width:0;height:0;top:0;left:-9999px",n.appendChild(r).appendChild(t),"undefined"!=typeof t.style.zoom&&(t.style.cssText="display:inline;margin:0;border:0;padding:1px;width:1px;zoom:1",fe.inlineBlockNeedsLayout=e=3===t.offsetWidth,e&&(n.style.zoom=1)),n.removeChild(r))}),function(){var e=re.createElement("div");fe.deleteExpando=!0;try{delete e.test}catch(t){fe.deleteExpando=!1}e=null}();var He=function(e){var t=pe.noData[(e.nodeName+" ").toLowerCase()],n=+e.nodeType||1;return(1===n||9===n)&&(!t||t!==!0&&e.getAttribute("classid")===t)},qe=/^(?:\{[\w\W]*\}|\[[\w\W]*\])$/,_e=/([A-Z])/g;pe.extend({cache:{},noData:{"applet ":!0,"embed ":!0,"object ":"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"},hasData:function(e){return e=e.nodeType?pe.cache[e[pe.expando]]:e[pe.expando],!!e&&!l(e)},data:function(e,t,n){return c(e,t,n)},removeData:function(e,t){return f(e,t)},_data:function(e,t,n){return c(e,t,n,!0)},_removeData:function(e,t){return f(e,t,!0)}}),pe.fn.extend({data:function(e,t){var n,r,i,o=this[0],a=o&&o.attributes;if(void 0===e){if(this.length&&(i=pe.data(o),1===o.nodeType&&!pe._data(o,"parsedAttrs"))){for(n=a.length;n--;)a[n]&&(r=a[n].name,0===r.indexOf("data-")&&(r=pe.camelCase(r.slice(5)),u(o,r,i[r])));pe._data(o,"parsedAttrs",!0)}return i}return"object"==typeof e?this.each(function(){pe.data(this,e)}):arguments.length>1?this.each(function(){pe.data(this,e,t)}):o?u(o,e,pe.data(o,e)):void 0},removeData:function(e){return this.each(function(){pe.removeData(this,e)})}}),pe.extend({queue:function(e,t,n){var r;if(e)return t=(t||"fx")+"queue",r=pe._data(e,t),n&&(!r||pe.isArray(n)?r=pe._data(e,t,pe.makeArray(n)):r.push(n)),r||[]},dequeue:function(e,t){t=t||"fx";var n=pe.queue(e,t),r=n.length,i=n.shift(),o=pe._queueHooks(e,t),a=function(){pe.dequeue(e,t)};"inprogress"===i&&(i=n.shift(),r--),i&&("fx"===t&&n.unshift("inprogress"),delete o.stop,i.call(e,a,o)),!r&&o&&o.empty.fire()},_queueHooks:function(e,t){var n=t+"queueHooks";return pe._data(e,n)||pe._data(e,n,{empty:pe.Callbacks("once memory").add(function(){pe._removeData(e,t+"queue"),pe._removeData(e,n)})})}}),pe.fn.extend({queue:function(e,t){var n=2;return"string"!=typeof e&&(t=e,e="fx",n--),arguments.length<n?pe.queue(this[0],e):void 0===t?this:this.each(function(){var n=pe.queue(this,e,t);pe._queueHooks(this,e),"fx"===e&&"inprogress"!==n[0]&&pe.dequeue(this,e)})},dequeue:function(e){return this.each(function(){pe.dequeue(this,e)})},clearQueue:function(e){return this.queue(e||"fx",[])},promise:function(e,t){var n,r=1,i=pe.Deferred(),o=this,a=this.length,s=function(){--r||i.resolveWith(o,[o])};for("string"!=typeof e&&(t=e,e=void 0),e=e||"fx";a--;)n=pe._data(o[a],e+"queueHooks"),n&&n.empty&&(r++,n.empty.add(s));return s(),i.promise(t)}}),function(){var e;fe.shrinkWrapBlocks=function(){if(null!=e)return e;e=!1;var t,n,r;return n=re.getElementsByTagName("body")[0],n&&n.style?(t=re.createElement("div"),r=re.createElement("div"),r.style.cssText="position:absolute;border:0;width:0;height:0;top:0;left:-9999px",n.appendChild(r).appendChild(t),"undefined"!=typeof t.style.zoom&&(t.style.cssText="-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:1px;width:1px;zoom:1",t.appendChild(re.createElement("div")).style.width="5px",e=3!==t.offsetWidth),n.removeChild(r),e):void 0}}();var Fe=/[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source,Me=new RegExp("^(?:([+-])=|)("+Fe+")([a-z%]*)$","i"),Oe=["Top","Right","Bottom","Left"],Re=function(e,t){return e=t||e,"none"===pe.css(e,"display")||!pe.contains(e.ownerDocument,e)},Pe=function(e,t,n,r,i,o,a){var s=0,u=e.length,l=null==n;if("object"===pe.type(n)){i=!0;for(s in n)Pe(e,t,s,n[s],!0,o,a)}else if(void 0!==r&&(i=!0,pe.isFunction(r)||(a=!0),l&&(a?(t.call(e,r),t=null):(l=t,t=function(e,t,n){return l.call(pe(e),n)})),t))for(;s<u;s++)t(e[s],n,a?r:r.call(e[s],s,t(e[s],n)));return i?e:l?t.call(e):u?t(e[0],n):o},Be=/^(?:checkbox|radio)$/i,We=/<([\w:-]+)/,Ie=/^$|\/(?:java|ecma)script/i,$e=/^\s+/,ze="abbr|article|aside|audio|bdi|canvas|data|datalist|details|dialog|figcaption|figure|footer|header|hgroup|main|mark|meter|nav|output|picture|progress|section|summary|template|time|video";!function(){var e=re.createElement("div"),t=re.createDocumentFragment(),n=re.createElement("input");e.innerHTML="  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>",fe.leadingWhitespace=3===e.firstChild.nodeType,fe.tbody=!e.getElementsByTagName("tbody").length,fe.htmlSerialize=!!e.getElementsByTagName("link").length,fe.html5Clone="<:nav></:nav>"!==re.createElement("nav").cloneNode(!0).outerHTML,n.type="checkbox",n.checked=!0,t.appendChild(n),fe.appendChecked=n.checked,e.innerHTML="<textarea>x</textarea>",fe.noCloneChecked=!!e.cloneNode(!0).lastChild.defaultValue,t.appendChild(e),n=re.createElement("input"),n.setAttribute("type","radio"),n.setAttribute("checked","checked"),n.setAttribute("name","t"),e.appendChild(n),fe.checkClone=e.cloneNode(!0).cloneNode(!0).lastChild.checked,fe.noCloneEvent=!!e.addEventListener,e[pe.expando]=1,fe.attributes=!e.getAttribute(pe.expando)}();var Xe={option:[1,"<select multiple='multiple'>","</select>"],legend:[1,"<fieldset>","</fieldset>"],area:[1,"<map>","</map>"],param:[1,"<object>","</object>"],thead:[1,"<table>","</table>"],tr:[2,"<table><tbody>","</tbody></table>"],col:[2,"<table><tbody></tbody><colgroup>","</colgroup></table>"],td:[3,"<table><tbody><tr>","</tr></tbody></table>"],_default:fe.htmlSerialize?[0,"",""]:[1,"X<div>","</div>"]};Xe.optgroup=Xe.option,Xe.tbody=Xe.tfoot=Xe.colgroup=Xe.caption=Xe.thead,Xe.th=Xe.td;var Ue=/<|&#?\w+;/,Ve=/<tbody/i;!function(){var t,n,r=re.createElement("div");for(t in{submit:!0,change:!0,focusin:!0})n="on"+t,(fe[t]=n in e)||(r.setAttribute(n,"t"),fe[t]=r.attributes[n].expando===!1);r=null}();var Ye=/^(?:input|select|textarea)$/i,Je=/^key/,Ge=/^(?:mouse|pointer|contextmenu|drag|drop)|click/,Ke=/^(?:focusinfocus|focusoutblur)$/,Qe=/^([^.]*)(?:\.(.+)|)/;pe.event={global:{},add:function(e,t,n,r,i){var o,a,s,u,l,c,f,d,p,h,g,m=pe._data(e);if(m){for(n.handler&&(u=n,n=u.handler,i=u.selector),n.guid||(n.guid=pe.guid++),(a=m.events)||(a=m.events={}),(c=m.handle)||(c=m.handle=function(e){return"undefined"==typeof pe||e&&pe.event.triggered===e.type?void 0:pe.event.dispatch.apply(c.elem,arguments)},c.elem=e),t=(t||"").match(De)||[""],s=t.length;s--;)o=Qe.exec(t[s])||[],p=g=o[1],h=(o[2]||"").split(".").sort(),p&&(l=pe.event.special[p]||{},p=(i?l.delegateType:l.bindType)||p,l=pe.event.special[p]||{},f=pe.extend({type:p,origType:g,data:r,handler:n,guid:n.guid,selector:i,needsContext:i&&pe.expr.match.needsContext.test(i),namespace:h.join(".")},u),(d=a[p])||(d=a[p]=[],d.delegateCount=0,l.setup&&l.setup.call(e,r,h,c)!==!1||(e.addEventListener?e.addEventListener(p,c,!1):e.attachEvent&&e.attachEvent("on"+p,c))),l.add&&(l.add.call(e,f),f.handler.guid||(f.handler.guid=n.guid)),i?d.splice(d.delegateCount++,0,f):d.push(f),pe.event.global[p]=!0);e=null}},remove:function(e,t,n,r,i){var o,a,s,u,l,c,f,d,p,h,g,m=pe.hasData(e)&&pe._data(e);if(m&&(c=m.events)){for(t=(t||"").match(De)||[""],l=t.length;l--;)if(s=Qe.exec(t[l])||[],p=g=s[1],h=(s[2]||"").split(".").sort(),p){for(f=pe.event.special[p]||{},p=(r?f.delegateType:f.bindType)||p,d=c[p]||[],s=s[2]&&new RegExp("(^|\\.)"+h.join("\\.(?:.*\\.|)")+"(\\.|$)"),u=o=d.length;o--;)a=d[o],!i&&g!==a.origType||n&&n.guid!==a.guid||s&&!s.test(a.namespace)||r&&r!==a.selector&&("**"!==r||!a.selector)||(d.splice(o,1),a.selector&&d.delegateCount--,f.remove&&f.remove.call(e,a));u&&!d.length&&(f.teardown&&f.teardown.call(e,h,m.handle)!==!1||pe.removeEvent(e,p,m.handle),delete c[p])}else for(p in c)pe.event.remove(e,p+t[l],n,r,!0);pe.isEmptyObject(c)&&(delete m.handle,pe._removeData(e,"events"))}},trigger:function(t,n,r,i){var o,a,s,u,l,c,f,d=[r||re],p=ce.call(t,"type")?t.type:t,h=ce.call(t,"namespace")?t.namespace.split("."):[];if(s=c=r=r||re,3!==r.nodeType&&8!==r.nodeType&&!Ke.test(p+pe.event.triggered)&&(p.indexOf(".")>-1&&(h=p.split("."),p=h.shift(),h.sort()),a=p.indexOf(":")<0&&"on"+p,t=t[pe.expando]?t:new pe.Event(p,"object"==typeof t&&t),t.isTrigger=i?2:3,t.namespace=h.join("."),t.rnamespace=t.namespace?new RegExp("(^|\\.)"+h.join("\\.(?:.*\\.|)")+"(\\.|$)"):null,t.result=void 0,t.target||(t.target=r),n=null==n?[t]:pe.makeArray(n,[t]),l=pe.event.special[p]||{},i||!l.trigger||l.trigger.apply(r,n)!==!1)){if(!i&&!l.noBubble&&!pe.isWindow(r)){for(u=l.delegateType||p,Ke.test(u+p)||(s=s.parentNode);s;s=s.parentNode)d.push(s),c=s;c===(r.ownerDocument||re)&&d.push(c.defaultView||c.parentWindow||e)}for(f=0;(s=d[f++])&&!t.isPropagationStopped();)t.type=f>1?u:l.bindType||p,o=(pe._data(s,"events")||{})[t.type]&&pe._data(s,"handle"),o&&o.apply(s,n),o=a&&s[a],o&&o.apply&&He(s)&&(t.result=o.apply(s,n),t.result===!1&&t.preventDefault());if(t.type=p,!i&&!t.isDefaultPrevented()&&(!l._default||l._default.apply(d.pop(),n)===!1)&&He(r)&&a&&r[p]&&!pe.isWindow(r)){c=r[a],c&&(r[a]=null),pe.event.triggered=p;try{r[p]()}catch(g){}pe.event.triggered=void 0,c&&(r[a]=c)}return t.result}},dispatch:function(e){e=pe.event.fix(e);var t,n,r,i,o,a=[],s=ie.call(arguments),u=(pe._data(this,"events")||{})[e.type]||[],l=pe.event.special[e.type]||{};if(s[0]=e,e.delegateTarget=this,!l.preDispatch||l.preDispatch.call(this,e)!==!1){for(a=pe.event.handlers.call(this,e,u),t=0;(i=a[t++])&&!e.isPropagationStopped();)for(e.currentTarget=i.elem,n=0;(o=i.handlers[n++])&&!e.isImmediatePropagationStopped();)e.rnamespace&&!e.rnamespace.test(o.namespace)||(e.handleObj=o,e.data=o.data,r=((pe.event.special[o.origType]||{}).handle||o.handler).apply(i.elem,s),void 0!==r&&(e.result=r)===!1&&(e.preventDefault(),e.stopPropagation()));return l.postDispatch&&l.postDispatch.call(this,e),e.result}},handlers:function(e,t){var n,r,i,o,a=[],s=t.delegateCount,u=e.target;if(s&&u.nodeType&&("click"!==e.type||isNaN(e.button)||e.button<1))for(;u!=this;u=u.parentNode||this)if(1===u.nodeType&&(u.disabled!==!0||"click"!==e.type)){for(r=[],n=0;n<s;n++)o=t[n],i=o.selector+" ",void 0===r[i]&&(r[i]=o.needsContext?pe(i,this).index(u)>-1:pe.find(i,this,null,[u]).length),r[i]&&r.push(o);r.length&&a.push({elem:u,handlers:r})}return s<t.length&&a.push({elem:this,handlers:t.slice(s)}),a},fix:function(e){if(e[pe.expando])return e;var t,n,r,i=e.type,o=e,a=this.fixHooks[i];for(a||(this.fixHooks[i]=a=Ge.test(i)?this.mouseHooks:Je.test(i)?this.keyHooks:{}),r=a.props?this.props.concat(a.props):this.props,e=new pe.Event(o),t=r.length;t--;)n=r[t],e[n]=o[n];return e.target||(e.target=o.srcElement||re),3===e.target.nodeType&&(e.target=e.target.parentNode),e.metaKey=!!e.metaKey,a.filter?a.filter(e,o):e},props:"altKey bubbles cancelable ctrlKey currentTarget detail eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),fixHooks:{},keyHooks:{props:"char charCode key keyCode".split(" "),filter:function(e,t){return null==e.which&&(e.which=null!=t.charCode?t.charCode:t.keyCode),e}},mouseHooks:{props:"button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),filter:function(e,t){var n,r,i,o=t.button,a=t.fromElement;return null==e.pageX&&null!=t.clientX&&(r=e.target.ownerDocument||re,i=r.documentElement,n=r.body,e.pageX=t.clientX+(i&&i.scrollLeft||n&&n.scrollLeft||0)-(i&&i.clientLeft||n&&n.clientLeft||0),e.pageY=t.clientY+(i&&i.scrollTop||n&&n.scrollTop||0)-(i&&i.clientTop||n&&n.clientTop||0)),!e.relatedTarget&&a&&(e.relatedTarget=a===e.target?t.toElement:a),e.which||void 0===o||(e.which=1&o?1:2&o?3:4&o?2:0),e}},special:{load:{noBubble:!0},focus:{trigger:function(){if(this!==b()&&this.focus)try{return this.focus(),!1}catch(e){}},delegateType:"focusin"},blur:{trigger:function(){if(this===b()&&this.blur)return this.blur(),!1},delegateType:"focusout"},click:{trigger:function(){if(pe.nodeName(this,"input")&&"checkbox"===this.type&&this.click)return this.click(),!1},_default:function(e){return pe.nodeName(e.target,"a")}},beforeunload:{postDispatch:function(e){void 0!==e.result&&e.originalEvent&&(e.originalEvent.returnValue=e.result)}}},simulate:function(e,t,n){var r=pe.extend(new pe.Event,n,{type:e,isSimulated:!0});pe.event.trigger(r,null,t),r.isDefaultPrevented()&&n.preventDefault()}},pe.removeEvent=re.removeEventListener?function(e,t,n){e.removeEventListener&&e.removeEventListener(t,n)}:function(e,t,n){var r="on"+t;e.detachEvent&&("undefined"==typeof e[r]&&(e[r]=null),e.detachEvent(r,n))},pe.Event=function(e,t){return this instanceof pe.Event?(e&&e.type?(this.originalEvent=e,this.type=e.type,this.isDefaultPrevented=e.defaultPrevented||void 0===e.defaultPrevented&&e.returnValue===!1?v:x):this.type=e,t&&pe.extend(this,t),this.timeStamp=e&&e.timeStamp||pe.now(),void(this[pe.expando]=!0)):new pe.Event(e,t)},pe.Event.prototype={constructor:pe.Event,isDefaultPrevented:x,isPropagationStopped:x,isImmediatePropagationStopped:x,preventDefault:function(){var e=this.originalEvent;this.isDefaultPrevented=v,e&&(e.preventDefault?e.preventDefault():e.returnValue=!1)},stopPropagation:function(){var e=this.originalEvent;this.isPropagationStopped=v,e&&!this.isSimulated&&(e.stopPropagation&&e.stopPropagation(),e.cancelBubble=!0)},stopImmediatePropagation:function(){var e=this.originalEvent;this.isImmediatePropagationStopped=v,e&&e.stopImmediatePropagation&&e.stopImmediatePropagation(),this.stopPropagation()}},pe.each({mouseenter:"mouseover",mouseleave:"mouseout",pointerenter:"pointerover",pointerleave:"pointerout"},function(e,t){pe.event.special[e]={delegateType:t,bindType:t,handle:function(e){var n,r=this,i=e.relatedTarget,o=e.handleObj;return i&&(i===r||pe.contains(r,i))||(e.type=o.origType,n=o.handler.apply(this,arguments),e.type=t),n}}}),fe.submit||(pe.event.special.submit={setup:function(){return!pe.nodeName(this,"form")&&void pe.event.add(this,"click._submit keypress._submit",function(e){var t=e.target,n=pe.nodeName(t,"input")||pe.nodeName(t,"button")?pe.prop(t,"form"):void 0;n&&!pe._data(n,"submit")&&(pe.event.add(n,"submit._submit",function(e){e._submitBubble=!0}),pe._data(n,"submit",!0))})},postDispatch:function(e){e._submitBubble&&(delete e._submitBubble,this.parentNode&&!e.isTrigger&&pe.event.simulate("submit",this.parentNode,e))},teardown:function(){return!pe.nodeName(this,"form")&&void pe.event.remove(this,"._submit")}}),fe.change||(pe.event.special.change={setup:function(){return Ye.test(this.nodeName)?("checkbox"!==this.type&&"radio"!==this.type||(pe.event.add(this,"propertychange._change",function(e){"checked"===e.originalEvent.propertyName&&(this._justChanged=!0)}),pe.event.add(this,"click._change",function(e){this._justChanged&&!e.isTrigger&&(this._justChanged=!1),pe.event.simulate("change",this,e)})),!1):void pe.event.add(this,"beforeactivate._change",function(e){var t=e.target;Ye.test(t.nodeName)&&!pe._data(t,"change")&&(pe.event.add(t,"change._change",function(e){!this.parentNode||e.isSimulated||e.isTrigger||pe.event.simulate("change",this.parentNode,e)}),pe._data(t,"change",!0))})},handle:function(e){var t=e.target;if(this!==t||e.isSimulated||e.isTrigger||"radio"!==t.type&&"checkbox"!==t.type)return e.handleObj.handler.apply(this,arguments)},teardown:function(){return pe.event.remove(this,"._change"),!Ye.test(this.nodeName)}}),fe.focusin||pe.each({focus:"focusin",blur:"focusout"},function(e,t){var n=function(e){pe.event.simulate(t,e.target,pe.event.fix(e))};pe.event.special[t]={setup:function(){var r=this.ownerDocument||this,i=pe._data(r,t);i||r.addEventListener(e,n,!0),pe._data(r,t,(i||0)+1)},teardown:function(){var r=this.ownerDocument||this,i=pe._data(r,t)-1;i?pe._data(r,t,i):(r.removeEventListener(e,n,!0),pe._removeData(r,t))}}}),pe.fn.extend({on:function(e,t,n,r){return w(this,e,t,n,r)},one:function(e,t,n,r){return w(this,e,t,n,r,1)},off:function(e,t,n){var r,i;if(e&&e.preventDefault&&e.handleObj)return r=e.handleObj,pe(e.delegateTarget).off(r.namespace?r.origType+"."+r.namespace:r.origType,r.selector,r.handler),this;if("object"==typeof e){for(i in e)this.off(i,t,e[i]);return this}return t!==!1&&"function"!=typeof t||(n=t,t=void 0),n===!1&&(n=x),this.each(function(){pe.event.remove(this,e,n,t)})},trigger:function(e,t){return this.each(function(){pe.event.trigger(e,t,this)})},triggerHandler:function(e,t){var n=this[0];if(n)return pe.event.trigger(e,t,n,!0)}});var Ze=/ jQuery\d+="(?:null|\d+)"/g,et=new RegExp("<(?:"+ze+")[\\s/>]","i"),tt=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:-]+)[^>]*)\/>/gi,nt=/<script|<style|<link/i,rt=/checked\s*(?:[^=]|=\s*.checked.)/i,it=/^true\/(.*)/,ot=/^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g,at=p(re),st=at.appendChild(re.createElement("div"));pe.extend({htmlPrefilter:function(e){return e.replace(tt,"<$1></$2>")},clone:function(e,t,n){var r,i,o,a,s,u=pe.contains(e.ownerDocument,e);if(fe.html5Clone||pe.isXMLDoc(e)||!et.test("<"+e.nodeName+">")?o=e.cloneNode(!0):(st.innerHTML=e.outerHTML,st.removeChild(o=st.firstChild)),!(fe.noCloneEvent&&fe.noCloneChecked||1!==e.nodeType&&11!==e.nodeType||pe.isXMLDoc(e)))for(r=h(o),s=h(e),a=0;null!=(i=s[a]);++a)r[a]&&k(i,r[a]);if(t)if(n)for(s=s||h(e),r=r||h(o),a=0;null!=(i=s[a]);a++)N(i,r[a]);else N(e,o);return r=h(o,"script"),r.length>0&&g(r,!u&&h(e,"script")),r=s=i=null,o},cleanData:function(e,t){for(var n,r,i,o,a=0,s=pe.expando,u=pe.cache,l=fe.attributes,c=pe.event.special;null!=(n=e[a]);a++)if((t||He(n))&&(i=n[s],o=i&&u[i])){if(o.events)for(r in o.events)c[r]?pe.event.remove(n,r):pe.removeEvent(n,r,o.handle);u[i]&&(delete u[i],l||"undefined"==typeof n.removeAttribute?n[s]=void 0:n.removeAttribute(s),ne.push(i))}}}),pe.fn.extend({domManip:S,detach:function(e){return A(this,e,!0)},remove:function(e){return A(this,e)},text:function(e){return Pe(this,function(e){return void 0===e?pe.text(this):this.empty().append((this[0]&&this[0].ownerDocument||re).createTextNode(e))},null,e,arguments.length)},append:function(){return S(this,arguments,function(e){if(1===this.nodeType||11===this.nodeType||9===this.nodeType){var t=T(this,e);t.appendChild(e)}})},prepend:function(){return S(this,arguments,function(e){if(1===this.nodeType||11===this.nodeType||9===this.nodeType){var t=T(this,e);t.insertBefore(e,t.firstChild)}})},before:function(){return S(this,arguments,function(e){this.parentNode&&this.parentNode.insertBefore(e,this)})},after:function(){return S(this,arguments,function(e){this.parentNode&&this.parentNode.insertBefore(e,this.nextSibling)})},empty:function(){for(var e,t=0;null!=(e=this[t]);t++){for(1===e.nodeType&&pe.cleanData(h(e,!1));e.firstChild;)e.removeChild(e.firstChild);e.options&&pe.nodeName(e,"select")&&(e.options.length=0)}return this},clone:function(e,t){return e=null!=e&&e,t=null==t?e:t,this.map(function(){return pe.clone(this,e,t)})},html:function(e){return Pe(this,function(e){var t=this[0]||{},n=0,r=this.length;if(void 0===e)return 1===t.nodeType?t.innerHTML.replace(Ze,""):void 0;if("string"==typeof e&&!nt.test(e)&&(fe.htmlSerialize||!et.test(e))&&(fe.leadingWhitespace||!$e.test(e))&&!Xe[(We.exec(e)||["",""])[1].toLowerCase()]){e=pe.htmlPrefilter(e);try{for(;n<r;n++)t=this[n]||{},1===t.nodeType&&(pe.cleanData(h(t,!1)),t.innerHTML=e);t=0}catch(i){}}t&&this.empty().append(e)},null,e,arguments.length)},replaceWith:function(){var e=[];return S(this,arguments,function(t){var n=this.parentNode;pe.inArray(this,e)<0&&(pe.cleanData(h(this)),
n&&n.replaceChild(t,this))},e)}}),pe.each({appendTo:"append",prependTo:"prepend",insertBefore:"before",insertAfter:"after",replaceAll:"replaceWith"},function(e,t){pe.fn[e]=function(e){for(var n,r=0,i=[],o=pe(e),a=o.length-1;r<=a;r++)n=r===a?this:this.clone(!0),pe(o[r])[t](n),ae.apply(i,n.get());return this.pushStack(i)}});var ut,lt={HTML:"block",BODY:"block"},ct=/^margin/,ft=new RegExp("^("+Fe+")(?!px)[a-z%]+$","i"),dt=function(e,t,n,r){var i,o,a={};for(o in t)a[o]=e.style[o],e.style[o]=t[o];i=n.apply(e,r||[]);for(o in t)e.style[o]=a[o];return i},pt=re.documentElement;!function(){function t(){var t,c,f=re.documentElement;f.appendChild(u),l.style.cssText="-webkit-box-sizing:border-box;box-sizing:border-box;position:relative;display:block;margin:auto;border:1px;padding:1px;top:1%;width:50%",n=i=s=!1,r=a=!0,e.getComputedStyle&&(c=e.getComputedStyle(l),n="1%"!==(c||{}).top,s="2px"===(c||{}).marginLeft,i="4px"===(c||{width:"4px"}).width,l.style.marginRight="50%",r="4px"===(c||{marginRight:"4px"}).marginRight,t=l.appendChild(re.createElement("div")),t.style.cssText=l.style.cssText="-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:0",t.style.marginRight=t.style.width="0",l.style.width="1px",a=!parseFloat((e.getComputedStyle(t)||{}).marginRight),l.removeChild(t)),l.style.display="none",o=0===l.getClientRects().length,o&&(l.style.display="",l.innerHTML="<table><tr><td></td><td>t</td></tr></table>",t=l.getElementsByTagName("td"),t[0].style.cssText="margin:0;border:0;padding:0;display:none",o=0===t[0].offsetHeight,o&&(t[0].style.display="",t[1].style.display="none",o=0===t[0].offsetHeight)),f.removeChild(u)}var n,r,i,o,a,s,u=re.createElement("div"),l=re.createElement("div");l.style&&(l.style.cssText="float:left;opacity:.5",fe.opacity="0.5"===l.style.opacity,fe.cssFloat=!!l.style.cssFloat,l.style.backgroundClip="content-box",l.cloneNode(!0).style.backgroundClip="",fe.clearCloneStyle="content-box"===l.style.backgroundClip,u=re.createElement("div"),u.style.cssText="border:0;width:8px;height:0;top:0;left:-9999px;padding:0;margin-top:1px;position:absolute",l.innerHTML="",u.appendChild(l),fe.boxSizing=""===l.style.boxSizing||""===l.style.MozBoxSizing||""===l.style.WebkitBoxSizing,pe.extend(fe,{reliableHiddenOffsets:function(){return null==n&&t(),o},boxSizingReliable:function(){return null==n&&t(),i},pixelMarginRight:function(){return null==n&&t(),r},pixelPosition:function(){return null==n&&t(),n},reliableMarginRight:function(){return null==n&&t(),a},reliableMarginLeft:function(){return null==n&&t(),s}}))}();var ht,gt,mt=/^(top|right|bottom|left)$/;e.getComputedStyle?(ht=function(t){var n=t.ownerDocument.defaultView;return n&&n.opener||(n=e),n.getComputedStyle(t)},gt=function(e,t,n){var r,i,o,a,s=e.style;return n=n||ht(e),a=n?n.getPropertyValue(t)||n[t]:void 0,""!==a&&void 0!==a||pe.contains(e.ownerDocument,e)||(a=pe.style(e,t)),n&&!fe.pixelMarginRight()&&ft.test(a)&&ct.test(t)&&(r=s.width,i=s.minWidth,o=s.maxWidth,s.minWidth=s.maxWidth=s.width=a,a=n.width,s.width=r,s.minWidth=i,s.maxWidth=o),void 0===a?a:a+""}):pt.currentStyle&&(ht=function(e){return e.currentStyle},gt=function(e,t,n){var r,i,o,a,s=e.style;return n=n||ht(e),a=n?n[t]:void 0,null==a&&s&&s[t]&&(a=s[t]),ft.test(a)&&!mt.test(t)&&(r=s.left,i=e.runtimeStyle,o=i&&i.left,o&&(i.left=e.currentStyle.left),s.left="fontSize"===t?"1em":a,a=s.pixelLeft+"px",s.left=r,o&&(i.left=o)),void 0===a?a:a+""||"auto"});var yt=/alpha\([^)]*\)/i,vt=/opacity\s*=\s*([^)]*)/i,xt=/^(none|table(?!-c[ea]).+)/,bt=new RegExp("^("+Fe+")(.*)$","i"),wt={position:"absolute",visibility:"hidden",display:"block"},Tt={letterSpacing:"0",fontWeight:"400"},Ct=["Webkit","O","Moz","ms"],Et=re.createElement("div").style;pe.extend({cssHooks:{opacity:{get:function(e,t){if(t){var n=gt(e,"opacity");return""===n?"1":n}}}},cssNumber:{animationIterationCount:!0,columnCount:!0,fillOpacity:!0,flexGrow:!0,flexShrink:!0,fontWeight:!0,lineHeight:!0,opacity:!0,order:!0,orphans:!0,widows:!0,zIndex:!0,zoom:!0},cssProps:{"float":fe.cssFloat?"cssFloat":"styleFloat"},style:function(e,t,n,r){if(e&&3!==e.nodeType&&8!==e.nodeType&&e.style){var i,o,a,s=pe.camelCase(t),u=e.style;if(t=pe.cssProps[s]||(pe.cssProps[s]=H(s)||s),a=pe.cssHooks[t]||pe.cssHooks[s],void 0===n)return a&&"get"in a&&void 0!==(i=a.get(e,!1,r))?i:u[t];if(o=typeof n,"string"===o&&(i=Me.exec(n))&&i[1]&&(n=d(e,t,i),o="number"),null!=n&&n===n&&("number"===o&&(n+=i&&i[3]||(pe.cssNumber[s]?"":"px")),fe.clearCloneStyle||""!==n||0!==t.indexOf("background")||(u[t]="inherit"),!(a&&"set"in a&&void 0===(n=a.set(e,n,r)))))try{u[t]=n}catch(l){}}},css:function(e,t,n,r){var i,o,a,s=pe.camelCase(t);return t=pe.cssProps[s]||(pe.cssProps[s]=H(s)||s),a=pe.cssHooks[t]||pe.cssHooks[s],a&&"get"in a&&(o=a.get(e,!0,n)),void 0===o&&(o=gt(e,t,r)),"normal"===o&&t in Tt&&(o=Tt[t]),""===n||n?(i=parseFloat(o),n===!0||isFinite(i)?i||0:o):o}}),pe.each(["height","width"],function(e,t){pe.cssHooks[t]={get:function(e,n,r){if(n)return xt.test(pe.css(e,"display"))&&0===e.offsetWidth?dt(e,wt,function(){return M(e,t,r)}):M(e,t,r)},set:function(e,n,r){var i=r&&ht(e);return _(e,n,r?F(e,t,r,fe.boxSizing&&"border-box"===pe.css(e,"boxSizing",!1,i),i):0)}}}),fe.opacity||(pe.cssHooks.opacity={get:function(e,t){return vt.test((t&&e.currentStyle?e.currentStyle.filter:e.style.filter)||"")?.01*parseFloat(RegExp.$1)+"":t?"1":""},set:function(e,t){var n=e.style,r=e.currentStyle,i=pe.isNumeric(t)?"alpha(opacity="+100*t+")":"",o=r&&r.filter||n.filter||"";n.zoom=1,(t>=1||""===t)&&""===pe.trim(o.replace(yt,""))&&n.removeAttribute&&(n.removeAttribute("filter"),""===t||r&&!r.filter)||(n.filter=yt.test(o)?o.replace(yt,i):o+" "+i)}}),pe.cssHooks.marginRight=L(fe.reliableMarginRight,function(e,t){if(t)return dt(e,{display:"inline-block"},gt,[e,"marginRight"])}),pe.cssHooks.marginLeft=L(fe.reliableMarginLeft,function(e,t){if(t)return(parseFloat(gt(e,"marginLeft"))||(pe.contains(e.ownerDocument,e)?e.getBoundingClientRect().left-dt(e,{marginLeft:0},function(){return e.getBoundingClientRect().left}):0))+"px"}),pe.each({margin:"",padding:"",border:"Width"},function(e,t){pe.cssHooks[e+t]={expand:function(n){for(var r=0,i={},o="string"==typeof n?n.split(" "):[n];r<4;r++)i[e+Oe[r]+t]=o[r]||o[r-2]||o[0];return i}},ct.test(e)||(pe.cssHooks[e+t].set=_)}),pe.fn.extend({css:function(e,t){return Pe(this,function(e,t,n){var r,i,o={},a=0;if(pe.isArray(t)){for(r=ht(e),i=t.length;a<i;a++)o[t[a]]=pe.css(e,t[a],!1,r);return o}return void 0!==n?pe.style(e,t,n):pe.css(e,t)},e,t,arguments.length>1)},show:function(){return q(this,!0)},hide:function(){return q(this)},toggle:function(e){return"boolean"==typeof e?e?this.show():this.hide():this.each(function(){Re(this)?pe(this).show():pe(this).hide()})}}),pe.Tween=O,O.prototype={constructor:O,init:function(e,t,n,r,i,o){this.elem=e,this.prop=n,this.easing=i||pe.easing._default,this.options=t,this.start=this.now=this.cur(),this.end=r,this.unit=o||(pe.cssNumber[n]?"":"px")},cur:function(){var e=O.propHooks[this.prop];return e&&e.get?e.get(this):O.propHooks._default.get(this)},run:function(e){var t,n=O.propHooks[this.prop];return this.options.duration?this.pos=t=pe.easing[this.easing](e,this.options.duration*e,0,1,this.options.duration):this.pos=t=e,this.now=(this.end-this.start)*t+this.start,this.options.step&&this.options.step.call(this.elem,this.now,this),n&&n.set?n.set(this):O.propHooks._default.set(this),this}},O.prototype.init.prototype=O.prototype,O.propHooks={_default:{get:function(e){var t;return 1!==e.elem.nodeType||null!=e.elem[e.prop]&&null==e.elem.style[e.prop]?e.elem[e.prop]:(t=pe.css(e.elem,e.prop,""),t&&"auto"!==t?t:0)},set:function(e){pe.fx.step[e.prop]?pe.fx.step[e.prop](e):1!==e.elem.nodeType||null==e.elem.style[pe.cssProps[e.prop]]&&!pe.cssHooks[e.prop]?e.elem[e.prop]=e.now:pe.style(e.elem,e.prop,e.now+e.unit)}}},O.propHooks.scrollTop=O.propHooks.scrollLeft={set:function(e){e.elem.nodeType&&e.elem.parentNode&&(e.elem[e.prop]=e.now)}},pe.easing={linear:function(e){return e},swing:function(e){return.5-Math.cos(e*Math.PI)/2},_default:"swing"},pe.fx=O.prototype.init,pe.fx.step={};var Nt,kt,St=/^(?:toggle|show|hide)$/,At=/queueHooks$/;pe.Animation=pe.extend($,{tweeners:{"*":[function(e,t){var n=this.createTween(e,t);return d(n.elem,e,Me.exec(t),n),n}]},tweener:function(e,t){pe.isFunction(e)?(t=e,e=["*"]):e=e.match(De);for(var n,r=0,i=e.length;r<i;r++)n=e[r],$.tweeners[n]=$.tweeners[n]||[],$.tweeners[n].unshift(t)},prefilters:[W],prefilter:function(e,t){t?$.prefilters.unshift(e):$.prefilters.push(e)}}),pe.speed=function(e,t,n){var r=e&&"object"==typeof e?pe.extend({},e):{complete:n||!n&&t||pe.isFunction(e)&&e,duration:e,easing:n&&t||t&&!pe.isFunction(t)&&t};return r.duration=pe.fx.off?0:"number"==typeof r.duration?r.duration:r.duration in pe.fx.speeds?pe.fx.speeds[r.duration]:pe.fx.speeds._default,null!=r.queue&&r.queue!==!0||(r.queue="fx"),r.old=r.complete,r.complete=function(){pe.isFunction(r.old)&&r.old.call(this),r.queue&&pe.dequeue(this,r.queue)},r},pe.fn.extend({fadeTo:function(e,t,n,r){return this.filter(Re).css("opacity",0).show().end().animate({opacity:t},e,n,r)},animate:function(e,t,n,r){var i=pe.isEmptyObject(e),o=pe.speed(t,n,r),a=function(){var t=$(this,pe.extend({},e),o);(i||pe._data(this,"finish"))&&t.stop(!0)};return a.finish=a,i||o.queue===!1?this.each(a):this.queue(o.queue,a)},stop:function(e,t,n){var r=function(e){var t=e.stop;delete e.stop,t(n)};return"string"!=typeof e&&(n=t,t=e,e=void 0),t&&e!==!1&&this.queue(e||"fx",[]),this.each(function(){var t=!0,i=null!=e&&e+"queueHooks",o=pe.timers,a=pe._data(this);if(i)a[i]&&a[i].stop&&r(a[i]);else for(i in a)a[i]&&a[i].stop&&At.test(i)&&r(a[i]);for(i=o.length;i--;)o[i].elem!==this||null!=e&&o[i].queue!==e||(o[i].anim.stop(n),t=!1,o.splice(i,1));!t&&n||pe.dequeue(this,e)})},finish:function(e){return e!==!1&&(e=e||"fx"),this.each(function(){var t,n=pe._data(this),r=n[e+"queue"],i=n[e+"queueHooks"],o=pe.timers,a=r?r.length:0;for(n.finish=!0,pe.queue(this,e,[]),i&&i.stop&&i.stop.call(this,!0),t=o.length;t--;)o[t].elem===this&&o[t].queue===e&&(o[t].anim.stop(!0),o.splice(t,1));for(t=0;t<a;t++)r[t]&&r[t].finish&&r[t].finish.call(this);delete n.finish})}}),pe.each(["toggle","show","hide"],function(e,t){var n=pe.fn[t];pe.fn[t]=function(e,r,i){return null==e||"boolean"==typeof e?n.apply(this,arguments):this.animate(P(t,!0),e,r,i)}}),pe.each({slideDown:P("show"),slideUp:P("hide"),slideToggle:P("toggle"),fadeIn:{opacity:"show"},fadeOut:{opacity:"hide"},fadeToggle:{opacity:"toggle"}},function(e,t){pe.fn[e]=function(e,n,r){return this.animate(t,e,n,r)}}),pe.timers=[],pe.fx.tick=function(){var e,t=pe.timers,n=0;for(Nt=pe.now();n<t.length;n++)e=t[n],e()||t[n]!==e||t.splice(n--,1);t.length||pe.fx.stop(),Nt=void 0},pe.fx.timer=function(e){pe.timers.push(e),e()?pe.fx.start():pe.timers.pop()},pe.fx.interval=13,pe.fx.start=function(){kt||(kt=e.setInterval(pe.fx.tick,pe.fx.interval))},pe.fx.stop=function(){e.clearInterval(kt),kt=null},pe.fx.speeds={slow:600,fast:200,_default:400},pe.fn.delay=function(t,n){return t=pe.fx?pe.fx.speeds[t]||t:t,n=n||"fx",this.queue(n,function(n,r){var i=e.setTimeout(n,t);r.stop=function(){e.clearTimeout(i)}})},function(){var e,t=re.createElement("input"),n=re.createElement("div"),r=re.createElement("select"),i=r.appendChild(re.createElement("option"));n=re.createElement("div"),n.setAttribute("className","t"),n.innerHTML="  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>",e=n.getElementsByTagName("a")[0],t.setAttribute("type","checkbox"),n.appendChild(t),e=n.getElementsByTagName("a")[0],e.style.cssText="top:1px",fe.getSetAttribute="t"!==n.className,fe.style=/top/.test(e.getAttribute("style")),fe.hrefNormalized="/a"===e.getAttribute("href"),fe.checkOn=!!t.value,fe.optSelected=i.selected,fe.enctype=!!re.createElement("form").enctype,r.disabled=!0,fe.optDisabled=!i.disabled,t=re.createElement("input"),t.setAttribute("value",""),fe.input=""===t.getAttribute("value"),t.value="t",t.setAttribute("type","radio"),fe.radioValue="t"===t.value}();var Dt=/\r/g,jt=/[\x20\t\r\n\f]+/g;pe.fn.extend({val:function(e){var t,n,r,i=this[0];{if(arguments.length)return r=pe.isFunction(e),this.each(function(n){var i;1===this.nodeType&&(i=r?e.call(this,n,pe(this).val()):e,null==i?i="":"number"==typeof i?i+="":pe.isArray(i)&&(i=pe.map(i,function(e){return null==e?"":e+""})),t=pe.valHooks[this.type]||pe.valHooks[this.nodeName.toLowerCase()],t&&"set"in t&&void 0!==t.set(this,i,"value")||(this.value=i))});if(i)return t=pe.valHooks[i.type]||pe.valHooks[i.nodeName.toLowerCase()],t&&"get"in t&&void 0!==(n=t.get(i,"value"))?n:(n=i.value,"string"==typeof n?n.replace(Dt,""):null==n?"":n)}}}),pe.extend({valHooks:{option:{get:function(e){var t=pe.find.attr(e,"value");return null!=t?t:pe.trim(pe.text(e)).replace(jt," ")}},select:{get:function(e){for(var t,n,r=e.options,i=e.selectedIndex,o="select-one"===e.type||i<0,a=o?null:[],s=o?i+1:r.length,u=i<0?s:o?i:0;u<s;u++)if(n=r[u],(n.selected||u===i)&&(fe.optDisabled?!n.disabled:null===n.getAttribute("disabled"))&&(!n.parentNode.disabled||!pe.nodeName(n.parentNode,"optgroup"))){if(t=pe(n).val(),o)return t;a.push(t)}return a},set:function(e,t){for(var n,r,i=e.options,o=pe.makeArray(t),a=i.length;a--;)if(r=i[a],pe.inArray(pe.valHooks.option.get(r),o)>-1)try{r.selected=n=!0}catch(s){r.scrollHeight}else r.selected=!1;return n||(e.selectedIndex=-1),i}}}}),pe.each(["radio","checkbox"],function(){pe.valHooks[this]={set:function(e,t){if(pe.isArray(t))return e.checked=pe.inArray(pe(e).val(),t)>-1}},fe.checkOn||(pe.valHooks[this].get=function(e){return null===e.getAttribute("value")?"on":e.value})});var Lt,Ht,qt=pe.expr.attrHandle,_t=/^(?:checked|selected)$/i,Ft=fe.getSetAttribute,Mt=fe.input;pe.fn.extend({attr:function(e,t){return Pe(this,pe.attr,e,t,arguments.length>1)},removeAttr:function(e){return this.each(function(){pe.removeAttr(this,e)})}}),pe.extend({attr:function(e,t,n){var r,i,o=e.nodeType;if(3!==o&&8!==o&&2!==o)return"undefined"==typeof e.getAttribute?pe.prop(e,t,n):(1===o&&pe.isXMLDoc(e)||(t=t.toLowerCase(),i=pe.attrHooks[t]||(pe.expr.match.bool.test(t)?Ht:Lt)),void 0!==n?null===n?void pe.removeAttr(e,t):i&&"set"in i&&void 0!==(r=i.set(e,n,t))?r:(e.setAttribute(t,n+""),n):i&&"get"in i&&null!==(r=i.get(e,t))?r:(r=pe.find.attr(e,t),null==r?void 0:r))},attrHooks:{type:{set:function(e,t){if(!fe.radioValue&&"radio"===t&&pe.nodeName(e,"input")){var n=e.value;return e.setAttribute("type",t),n&&(e.value=n),t}}}},removeAttr:function(e,t){var n,r,i=0,o=t&&t.match(De);if(o&&1===e.nodeType)for(;n=o[i++];)r=pe.propFix[n]||n,pe.expr.match.bool.test(n)?Mt&&Ft||!_t.test(n)?e[r]=!1:e[pe.camelCase("default-"+n)]=e[r]=!1:pe.attr(e,n,""),e.removeAttribute(Ft?n:r)}}),Ht={set:function(e,t,n){return t===!1?pe.removeAttr(e,n):Mt&&Ft||!_t.test(n)?e.setAttribute(!Ft&&pe.propFix[n]||n,n):e[pe.camelCase("default-"+n)]=e[n]=!0,n}},pe.each(pe.expr.match.bool.source.match(/\w+/g),function(e,t){var n=qt[t]||pe.find.attr;Mt&&Ft||!_t.test(t)?qt[t]=function(e,t,r){var i,o;return r||(o=qt[t],qt[t]=i,i=null!=n(e,t,r)?t.toLowerCase():null,qt[t]=o),i}:qt[t]=function(e,t,n){if(!n)return e[pe.camelCase("default-"+t)]?t.toLowerCase():null}}),Mt&&Ft||(pe.attrHooks.value={set:function(e,t,n){return pe.nodeName(e,"input")?void(e.defaultValue=t):Lt&&Lt.set(e,t,n)}}),Ft||(Lt={set:function(e,t,n){var r=e.getAttributeNode(n);if(r||e.setAttributeNode(r=e.ownerDocument.createAttribute(n)),r.value=t+="","value"===n||t===e.getAttribute(n))return t}},qt.id=qt.name=qt.coords=function(e,t,n){var r;if(!n)return(r=e.getAttributeNode(t))&&""!==r.value?r.value:null},pe.valHooks.button={get:function(e,t){var n=e.getAttributeNode(t);if(n&&n.specified)return n.value},set:Lt.set},pe.attrHooks.contenteditable={set:function(e,t,n){Lt.set(e,""!==t&&t,n)}},pe.each(["width","height"],function(e,t){pe.attrHooks[t]={set:function(e,n){if(""===n)return e.setAttribute(t,"auto"),n}}})),fe.style||(pe.attrHooks.style={get:function(e){return e.style.cssText||void 0},set:function(e,t){return e.style.cssText=t+""}});var Ot=/^(?:input|select|textarea|button|object)$/i,Rt=/^(?:a|area)$/i;pe.fn.extend({prop:function(e,t){return Pe(this,pe.prop,e,t,arguments.length>1)},removeProp:function(e){return e=pe.propFix[e]||e,this.each(function(){try{this[e]=void 0,delete this[e]}catch(t){}})}}),pe.extend({prop:function(e,t,n){var r,i,o=e.nodeType;if(3!==o&&8!==o&&2!==o)return 1===o&&pe.isXMLDoc(e)||(t=pe.propFix[t]||t,i=pe.propHooks[t]),void 0!==n?i&&"set"in i&&void 0!==(r=i.set(e,n,t))?r:e[t]=n:i&&"get"in i&&null!==(r=i.get(e,t))?r:e[t]},propHooks:{tabIndex:{get:function(e){var t=pe.find.attr(e,"tabindex");return t?parseInt(t,10):Ot.test(e.nodeName)||Rt.test(e.nodeName)&&e.href?0:-1}}},propFix:{"for":"htmlFor","class":"className"}}),fe.hrefNormalized||pe.each(["href","src"],function(e,t){pe.propHooks[t]={get:function(e){return e.getAttribute(t,4)}}}),fe.optSelected||(pe.propHooks.selected={get:function(e){var t=e.parentNode;return t&&(t.selectedIndex,t.parentNode&&t.parentNode.selectedIndex),null},set:function(e){var t=e.parentNode;t&&(t.selectedIndex,t.parentNode&&t.parentNode.selectedIndex)}}),pe.each(["tabIndex","readOnly","maxLength","cellSpacing","cellPadding","rowSpan","colSpan","useMap","frameBorder","contentEditable"],function(){pe.propFix[this.toLowerCase()]=this}),fe.enctype||(pe.propFix.enctype="encoding");var Pt=/[\t\r\n\f]/g;pe.fn.extend({addClass:function(e){var t,n,r,i,o,a,s,u=0;if(pe.isFunction(e))return this.each(function(t){pe(this).addClass(e.call(this,t,z(this)))});if("string"==typeof e&&e)for(t=e.match(De)||[];n=this[u++];)if(i=z(n),r=1===n.nodeType&&(" "+i+" ").replace(Pt," ")){for(a=0;o=t[a++];)r.indexOf(" "+o+" ")<0&&(r+=o+" ");s=pe.trim(r),i!==s&&pe.attr(n,"class",s)}return this},removeClass:function(e){var t,n,r,i,o,a,s,u=0;if(pe.isFunction(e))return this.each(function(t){pe(this).removeClass(e.call(this,t,z(this)))});if(!arguments.length)return this.attr("class","");if("string"==typeof e&&e)for(t=e.match(De)||[];n=this[u++];)if(i=z(n),r=1===n.nodeType&&(" "+i+" ").replace(Pt," ")){for(a=0;o=t[a++];)for(;r.indexOf(" "+o+" ")>-1;)r=r.replace(" "+o+" "," ");s=pe.trim(r),i!==s&&pe.attr(n,"class",s)}return this},toggleClass:function(e,t){var n=typeof e;return"boolean"==typeof t&&"string"===n?t?this.addClass(e):this.removeClass(e):pe.isFunction(e)?this.each(function(n){pe(this).toggleClass(e.call(this,n,z(this),t),t)}):this.each(function(){var t,r,i,o;if("string"===n)for(r=0,i=pe(this),o=e.match(De)||[];t=o[r++];)i.hasClass(t)?i.removeClass(t):i.addClass(t);else void 0!==e&&"boolean"!==n||(t=z(this),t&&pe._data(this,"__className__",t),pe.attr(this,"class",t||e===!1?"":pe._data(this,"__className__")||""))})},hasClass:function(e){var t,n,r=0;for(t=" "+e+" ";n=this[r++];)if(1===n.nodeType&&(" "+z(n)+" ").replace(Pt," ").indexOf(t)>-1)return!0;return!1}}),pe.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "),function(e,t){pe.fn[t]=function(e,n){return arguments.length>0?this.on(t,null,e,n):this.trigger(t)}}),pe.fn.extend({hover:function(e,t){return this.mouseenter(e).mouseleave(t||e)}});var Bt=e.location,Wt=pe.now(),It=/\?/,$t=/(,)|(\[|{)|(}|])|"(?:[^"\\\r\n]|\\["\\\/bfnrt]|\\u[\da-fA-F]{4})*"\s*:?|true|false|null|-?(?!0\d)\d+(?:\.\d+|)(?:[eE][+-]?\d+|)/g;pe.parseJSON=function(t){if(e.JSON&&e.JSON.parse)return e.JSON.parse(t+"");var n,r=null,i=pe.trim(t+"");return i&&!pe.trim(i.replace($t,function(e,t,i,o){return n&&t&&(r=0),0===r?e:(n=i||t,r+=!o-!i,"")}))?Function("return "+i)():pe.error("Invalid JSON: "+t)},pe.parseXML=function(t){var n,r;if(!t||"string"!=typeof t)return null;try{e.DOMParser?(r=new e.DOMParser,n=r.parseFromString(t,"text/xml")):(n=new e.ActiveXObject("Microsoft.XMLDOM"),n.async="false",n.loadXML(t))}catch(i){n=void 0}return n&&n.documentElement&&!n.getElementsByTagName("parsererror").length||pe.error("Invalid XML: "+t),n};var zt=/#.*$/,Xt=/([?&])_=[^&]*/,Ut=/^(.*?):[ \t]*([^\r\n]*)\r?$/gm,Vt=/^(?:about|app|app-storage|.+-extension|file|res|widget):$/,Yt=/^(?:GET|HEAD)$/,Jt=/^\/\//,Gt=/^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/,Kt={},Qt={},Zt="*/".concat("*"),en=Bt.href,tn=Gt.exec(en.toLowerCase())||[];pe.extend({active:0,lastModified:{},etag:{},ajaxSettings:{url:en,type:"GET",isLocal:Vt.test(tn[1]),global:!0,processData:!0,async:!0,contentType:"application/x-www-form-urlencoded; charset=UTF-8",accepts:{"*":Zt,text:"text/plain",html:"text/html",xml:"application/xml, text/xml",json:"application/json, text/javascript"},contents:{xml:/\bxml\b/,html:/\bhtml/,json:/\bjson\b/},responseFields:{xml:"responseXML",text:"responseText",json:"responseJSON"},converters:{"* text":String,"text html":!0,"text json":pe.parseJSON,"text xml":pe.parseXML},flatOptions:{url:!0,context:!0}},ajaxSetup:function(e,t){return t?V(V(e,pe.ajaxSettings),t):V(pe.ajaxSettings,e)},ajaxPrefilter:X(Kt),ajaxTransport:X(Qt),ajax:function(t,n){function r(t,n,r,i){var o,f,v,x,w,C=n;2!==b&&(b=2,u&&e.clearTimeout(u),c=void 0,s=i||"",T.readyState=t>0?4:0,o=t>=200&&t<300||304===t,r&&(x=Y(d,T,r)),x=J(d,x,T,o),o?(d.ifModified&&(w=T.getResponseHeader("Last-Modified"),w&&(pe.lastModified[a]=w),w=T.getResponseHeader("etag"),w&&(pe.etag[a]=w)),204===t||"HEAD"===d.type?C="nocontent":304===t?C="notmodified":(C=x.state,f=x.data,v=x.error,o=!v)):(v=C,!t&&C||(C="error",t<0&&(t=0))),T.status=t,T.statusText=(n||C)+"",o?g.resolveWith(p,[f,C,T]):g.rejectWith(p,[T,C,v]),T.statusCode(y),y=void 0,l&&h.trigger(o?"ajaxSuccess":"ajaxError",[T,d,o?f:v]),m.fireWith(p,[T,C]),l&&(h.trigger("ajaxComplete",[T,d]),--pe.active||pe.event.trigger("ajaxStop")))}"object"==typeof t&&(n=t,t=void 0),n=n||{};var i,o,a,s,u,l,c,f,d=pe.ajaxSetup({},n),p=d.context||d,h=d.context&&(p.nodeType||p.jquery)?pe(p):pe.event,g=pe.Deferred(),m=pe.Callbacks("once memory"),y=d.statusCode||{},v={},x={},b=0,w="canceled",T={readyState:0,getResponseHeader:function(e){var t;if(2===b){if(!f)for(f={};t=Ut.exec(s);)f[t[1].toLowerCase()]=t[2];t=f[e.toLowerCase()]}return null==t?null:t},getAllResponseHeaders:function(){return 2===b?s:null},setRequestHeader:function(e,t){var n=e.toLowerCase();return b||(e=x[n]=x[n]||e,v[e]=t),this},overrideMimeType:function(e){return b||(d.mimeType=e),this},statusCode:function(e){var t;if(e)if(b<2)for(t in e)y[t]=[y[t],e[t]];else T.always(e[T.status]);return this},abort:function(e){var t=e||w;return c&&c.abort(t),r(0,t),this}};if(g.promise(T).complete=m.add,T.success=T.done,T.error=T.fail,d.url=((t||d.url||en)+"").replace(zt,"").replace(Jt,tn[1]+"//"),d.type=n.method||n.type||d.method||d.type,d.dataTypes=pe.trim(d.dataType||"*").toLowerCase().match(De)||[""],null==d.crossDomain&&(i=Gt.exec(d.url.toLowerCase()),d.crossDomain=!(!i||i[1]===tn[1]&&i[2]===tn[2]&&(i[3]||("http:"===i[1]?"80":"443"))===(tn[3]||("http:"===tn[1]?"80":"443")))),d.data&&d.processData&&"string"!=typeof d.data&&(d.data=pe.param(d.data,d.traditional)),U(Kt,d,n,T),2===b)return T;l=pe.event&&d.global,l&&0===pe.active++&&pe.event.trigger("ajaxStart"),d.type=d.type.toUpperCase(),d.hasContent=!Yt.test(d.type),a=d.url,d.hasContent||(d.data&&(a=d.url+=(It.test(a)?"&":"?")+d.data,delete d.data),d.cache===!1&&(d.url=Xt.test(a)?a.replace(Xt,"$1_="+Wt++):a+(It.test(a)?"&":"?")+"_="+Wt++)),d.ifModified&&(pe.lastModified[a]&&T.setRequestHeader("If-Modified-Since",pe.lastModified[a]),pe.etag[a]&&T.setRequestHeader("If-None-Match",pe.etag[a])),(d.data&&d.hasContent&&d.contentType!==!1||n.contentType)&&T.setRequestHeader("Content-Type",d.contentType),T.setRequestHeader("Accept",d.dataTypes[0]&&d.accepts[d.dataTypes[0]]?d.accepts[d.dataTypes[0]]+("*"!==d.dataTypes[0]?", "+Zt+"; q=0.01":""):d.accepts["*"]);for(o in d.headers)T.setRequestHeader(o,d.headers[o]);if(d.beforeSend&&(d.beforeSend.call(p,T,d)===!1||2===b))return T.abort();w="abort";for(o in{success:1,error:1,complete:1})T[o](d[o]);if(c=U(Qt,d,n,T)){if(T.readyState=1,l&&h.trigger("ajaxSend",[T,d]),2===b)return T;d.async&&d.timeout>0&&(u=e.setTimeout(function(){T.abort("timeout")},d.timeout));try{b=1,c.send(v,r)}catch(C){if(!(b<2))throw C;r(-1,C)}}else r(-1,"No Transport");return T},getJSON:function(e,t,n){return pe.get(e,t,n,"json")},getScript:function(e,t){return pe.get(e,void 0,t,"script")}}),pe.each(["get","post"],function(e,t){pe[t]=function(e,n,r,i){return pe.isFunction(n)&&(i=i||r,r=n,n=void 0),pe.ajax(pe.extend({url:e,type:t,dataType:i,data:n,success:r},pe.isPlainObject(e)&&e))}}),pe._evalUrl=function(e){return pe.ajax({url:e,type:"GET",dataType:"script",cache:!0,async:!1,global:!1,"throws":!0})},pe.fn.extend({wrapAll:function(e){if(pe.isFunction(e))return this.each(function(t){pe(this).wrapAll(e.call(this,t))});if(this[0]){var t=pe(e,this[0].ownerDocument).eq(0).clone(!0);this[0].parentNode&&t.insertBefore(this[0]),t.map(function(){for(var e=this;e.firstChild&&1===e.firstChild.nodeType;)e=e.firstChild;return e}).append(this)}return this},wrapInner:function(e){return pe.isFunction(e)?this.each(function(t){pe(this).wrapInner(e.call(this,t))}):this.each(function(){var t=pe(this),n=t.contents();n.length?n.wrapAll(e):t.append(e)})},wrap:function(e){var t=pe.isFunction(e);return this.each(function(n){pe(this).wrapAll(t?e.call(this,n):e)})},unwrap:function(){return this.parent().each(function(){pe.nodeName(this,"body")||pe(this).replaceWith(this.childNodes)}).end()}}),pe.expr.filters.hidden=function(e){return fe.reliableHiddenOffsets()?e.offsetWidth<=0&&e.offsetHeight<=0&&!e.getClientRects().length:K(e)},pe.expr.filters.visible=function(e){return!pe.expr.filters.hidden(e)};var nn=/%20/g,rn=/\[\]$/,on=/\r?\n/g,an=/^(?:submit|button|image|reset|file)$/i,sn=/^(?:input|select|textarea|keygen)/i;pe.param=function(e,t){var n,r=[],i=function(e,t){t=pe.isFunction(t)?t():null==t?"":t,r[r.length]=encodeURIComponent(e)+"="+encodeURIComponent(t)};if(void 0===t&&(t=pe.ajaxSettings&&pe.ajaxSettings.traditional),pe.isArray(e)||e.jquery&&!pe.isPlainObject(e))pe.each(e,function(){i(this.name,this.value)});else for(n in e)Q(n,e[n],t,i);return r.join("&").replace(nn,"+")},pe.fn.extend({serialize:function(){return pe.param(this.serializeArray())},serializeArray:function(){return this.map(function(){var e=pe.prop(this,"elements");return e?pe.makeArray(e):this}).filter(function(){var e=this.type;return this.name&&!pe(this).is(":disabled")&&sn.test(this.nodeName)&&!an.test(e)&&(this.checked||!Be.test(e))}).map(function(e,t){var n=pe(this).val();return null==n?null:pe.isArray(n)?pe.map(n,function(e){return{name:t.name,value:e.replace(on,"\r\n")}}):{name:t.name,value:n.replace(on,"\r\n")}}).get()}}),pe.ajaxSettings.xhr=void 0!==e.ActiveXObject?function(){return this.mnKind>normal</ColumnKind>
    </column>
    <column id="881" parent="274" name="custom_field">
      <Position>15</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="882" parent="274" name="status">
      <Position>16</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="883" parent="274" name="date_added">
      <Position>17</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="884" parent="274" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="885" parent="275" name="customer_approval_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="886" parent="275" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="887" parent="275" name="type">
      <Position>3</Position>
      <DataType>varchar(9)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="888" parent="275" name="date_added">
      <Position>4</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="889" parent="275" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_approval_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="890" parent="276" name="customer_group_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="891" parent="276" name="approval">
      <Position>2</Position>
      <DataType>int(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="892" parent="276" name="sort_order">
      <Position>3</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="893" parent="276" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_group_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="894" parent="277" name="customer_group_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="895" parent="277" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="896" parent="277" name="name">
      <Position>3</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="897" parent="277" name="description">
      <Position>4</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="898" parent="277" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_group_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="899" parent="278" name="customer_history_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="900" parent="278" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="901" parent="278" name="comment">
      <Position>3</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="902" parent="278" name="date_added">
      <Position>4</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="903" parent="278" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_history_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="904" parent="279" name="customer_ip_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="905" parent="279" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="906" parent="279" name="ip">
      <Position>3</Position>
      <DataType>varchar(40)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="907" parent="279" name="date_added">
      <Position>4</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="908" parent="279" name="ip">
      <ColNames>ip</ColNames>
      <Type>btree</Type>
    </index>
    <key id="909" parent="279" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_ip_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="910" parent="280" name="customer_login_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="911" parent="280" name="email">
      <Position>2</Position>
      <DataType>varchar(96)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="912" parent="280" name="ip">
      <Position>3</Position>
      <DataType>varchar(40)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="913" parent="280" name="total">
      <Position>4</Position>
      <DataType>int(4)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="914" parent="280" name="date_added">
      <Position>5</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="915" parent="280" name="date_modified">
      <Position>6</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="916" parent="280" name="email">
      <ColNames>email</ColNames>
      <Type>btree</Type>
    </index>
    <index id="917" parent="280" name="ip">
      <ColNames>ip</ColNames>
      <Type>btree</Type>
    </index>
    <key id="918" parent="280" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_login_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="919" parent="281" name="ip">
      <Position>1</Position>
      <DataType>varchar(40)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="920" parent="281" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="921" parent="281" name="url">
      <Position>3</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="922" parent="281" name="referer">
      <Position>4</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="923" parent="281" name="date_added">
      <Position>5</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="924" parent="281" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>ip</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="925" parent="282" name="id">
      <Position>1</Position>
      <Comment>自增主键</Comment>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="926" parent="282" name="file_name">
      <Position>2</Position>
      <Comment>上传文件名称</Comment>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="927" parent="282" name="size">
      <Position>3</Position>
      <Comment>上传文件大小</Comment>
      <DataType>double|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="928" parent="282" name="file_path">
      <Position>4</Position>
      <Comment>文件相对路径</Comment>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="929" parent="282" name="customer_id">
      <Position>5</Position>
      <Comment>导入订单的用户ID</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="930" parent="282" name="import_mode">
      <Position>6</Position>
      <Comment>导入模式 0: 普通模式 1: FlatFair模式 pickup at buyer 3: pickup at seller</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="931" parent="282" name="address_id">
      <Position>7</Position>
      <Comment>取货地址ID import_mode = 1 用来表示buyer取货地址</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="932" parent="282" name="run_id">
      <Position>8</Position>
      <Comment>运行批次号</Comment>
      <DataType>varchar(20)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="933" parent="282" name="memo">
      <Position>9</Position>
      <Comment>备注</Comment>
      <DataType>varchar(1000)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="934" parent="282" name="create_user_name">
      <Position>10</Position>
      <Comment>创建者</Comment>
      <DataType>varchar(30)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="935" parent="282" name="create_time">
      <Position>11</Position>
      <Comment>创建时间</Comment>
      <DataType>datetime|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="936" parent="282" name="update_user_name">
      <Position>12</Position>
      <Comment>修改者</Comment>
      <DataType>varchar(30)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="937" parent="282" name="update_time">
      <Position>13</Position>
      <Comment>更新时间</Comment>
      <DataType>datetime|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="938" parent="282" name="program_code">
      <Position>14</Position>
      <Comment>程序号</Comment>
      <DataType>varchar(10)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="939" parent="282" name="handle_status">
      <Position>15</Position>
      <Comment>文件处理状态：1-成功；0-失败</Comment>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="940" parent="282" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="941" parent="283" name="customer_platform_id">
      <Position>1</Position>
      <Comment>关联id，自增</Comment>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="942" parent="283" name="customer_id">
      <Position>2</Position>
      <Comment>customer_id</Comment>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="943" parent="283" name="platform_id">
      <Position>3</Position>
      <Comment>平台id</Comment>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="944" parent="283" name="customer_plat_unq">
      <ColNames>customer_id
platform_id</ColNames>
      <Unique>1</Unique>
      <Type>btree</Type>
    </index>
    <key id="945" parent="283" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_platform_id</ColNames>
      <Primary>1</Primary>
    </key>
    <key id="946" parent="283" name="customer_plat_unq">
      <ColNames>customer_id
platform_id</ColNames>
      <UnderlyingIndexName>customer_plat_unq</UnderlyingIndexName>
    </key>
    <column id="947" parent="284" name="id">
      <Position>1</Position>
      <Comment>自增主键</Comment>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="948" parent="284" name="firstname">
      <Position>2</Position>
      <Comment>firstname</Comment>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="949" parent="284" name="lastname">
      <Position>3</Position>
      <Comment>lastname</Comment>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="950" parent="284" name="email">
      <Position>4</Position>
      <Comment>Email</Comment>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="951" parent="284" name="telephone">
      <Position>5</Position>
      <Comment>电话</Comment>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="952" parent="284" name="source">
      <Position>6</Position>
      <Comment>来源</Comment>
      <DataType>varchar(500)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="953" parent="284" name="street">
      <Position>7</Position>
      <Comment>街道</Comment>
      <DataType>varchar(500)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="954" parent="284" name="city">
      <Position>8</Position>
      <Comment>城市</Comment>
      <DataType>varchar(50)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="955" parent="284" name="zip">
      <Position>9</Position>
      <Comment>邮编</Comment>
      <DataType>varchar(20)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="956" parent="284" name="country_id">
      <Position>10</Position>
      <Comment>国家ID</Comment>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="957" parent="284" name="zone_id">
      <Position>11</Position>
      <Comment>地区ID</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="958" parent="284" name="remark">
      <Position>12</Position>
      <Comment>用户备注</Comment>
      <DataType>varchar(1000)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="959" parent="284" name="ip">
      <Position>13</Position>
      <Comment>申请用户IP地址</Comment>
      <DataType>varchar(20)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="960" parent="284" name="send">
      <Position>14</Position>
      <Comment>是否发送</Comment>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="961" parent="284" name="memo">
      <Position>15</Position>
      <Comment>备注</Comment>
      <DataType>varchar(1000)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="962" parent="284" name="create_user_name">
      <Position>16</Position>
      <Comment>创建者</Comment>
      <DataType>varchar(30)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="963" parent="284" name="create_time">
      <Position>17</Position>
      <Comment>创建时间</Comment>
      <DataType>datetime|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="964" parent="284" name="update_user_name">
      <Position>18</Position>
      <Comment>修改者</Comment>
      <DataType>varchar(30)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="965" parent="284" name="update_time">
      <Position>19</Position>
      <Comment>修改时间</Comment>
      <DataType>datetime|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="966" parent="284" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="967" parent="285" name="customer_reward_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="968" parent="285" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="969" parent="285" name="order_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="970" parent="285" name="description">
      <Position>4</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="971" parent="285" name="points">
      <Position>5</Position>
      <DataType>int(8)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="972" parent="285" name="date_added">
      <Position>6</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="973" parent="285" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_reward_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="974" parent="286" name="customer_search_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="975" parent="286" name="store_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="976" parent="286" name="language_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="977" parent="286" name="customer_id">
      <Position>4</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="978" parent="286" name="keyword">
      <Position>5</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="979" parent="286" name="category_id">
      <Position>6</Position>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="980" parent="286" name="sub_category">
      <Position>7</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="981" parent="286" name="description">
      <Position>8</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="982" parent="286" name="products">
      <Position>9</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="983" parent="286" name="ip">
      <Position>10</Position>
      <DataType>varchar(40)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="984" parent="286" name="date_added">
      <Position>11</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="985" parent="286" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_search_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="986" parent="287" name="customer_transaction_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="987" parent="287" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="988" parent="287" name="order_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="989" parent="287" name="description">
      <Position>4</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="990" parent="287" name="amount">
      <Position>5</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="991" parent="287" name="date_added">
      <Position>6</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="992" parent="287" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_transaction_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="993" parent="288" name="customer_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="994" parent="288" name="product_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="995" parent="288" name="date_added">
      <Position>3</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="996" parent="288" name="remind_qty">
      <Position>4</Position>
      <Comment>库存订阅阈值</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="997" parent="288" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_id
product_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="998" parent="289" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="999" parent="289" name="category_id">
      <Position>2</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1000" parent="289" name="fixed">
      <Position>3</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1001" parent="289" name="percentage">
      <Position>4</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1002" parent="289" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1003" parent="290" name="download_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1004" parent="290" name="seller_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1005" parent="290" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>download_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1006" parent="291" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1007" parent="291" name="partner_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1008" parent="291" name="amount">
      <Position>3</Position>
      <DataType>float|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1009" parent="291" name="tax_class_id">
      <Position>4</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1010" parent="291" name="status">
      <Position>5</Position>
      <DataType>int(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1011" parent="291" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1012" parent="292" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1013" parent="292" name="name">
      <Position>2</Position>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1014" parent="292" name="subject">
      <Position>3</Position>
      <DataType>varchar(1000)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1015" parent="292" name="message">
      <Position>4</Position>
      <DataType>varchar(5000)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1016" parent="292" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1017" parent="293" name="id">
      <Position>1</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1018" parent="293" name="seller_id">
      <Position>2</Position>
      <DataType>int(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1019" parent="293" name="country_code">
      <Position>3</Position>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1020" parent="293" name="zip_to">
      <Position>4</Position>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1021" parent="293" name="zip_from">
      <Position>5</Position>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1022" parent="293" name="price">
      <Position>6</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1023" parent="293" name="weight_from">
      <Position>7</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1024" parent="293" name="weight_to">
      <Position>8</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1025" parent="293" name="max_days">
      <Position>9</Position>
      <DataType>int(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1026" parent="293" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1027" parent="294" name="product_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1028" parent="294" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1029" parent="294" name="customer_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1030" parent="294" name="date_added">
      <Position>4</Position>
      <DataType>date|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;0000-00-00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1031" parent="294" name="tracking">
      <Position>5</Position>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1032" parent="295" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1033" parent="295" name="seller_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1034" parent="295" name="category_id">
      <Position>3</Position>
      <DataType>varchar(1000)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1035" parent="295" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1036" parent="296" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1037" parent="296" name="customer_id">
      <Position>2</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1038" parent="296" name="commission_id">
      <Position>3</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1039" parent="296" name="fixed">
      <Position>4</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1040" parent="296" name="percentage">
      <Position>5</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1041" parent="296" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1042" parent="297" name="customer_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1043" parent="297" name="is_partner">
      <Position>2</Position>
      <DataType>int(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1044" parent="297" name="screenname">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1045" parent="297" name="gender">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1046" parent="297" name="shortprofile">
      <Position>5</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1047" parent="297" name="avatar">
      <Position>6</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1048" parent="297" name="twitterid">
      <Position>7</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1049" parent="297" name="paypalid">
      <Position>8</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1050" parent="297" name="paypalfirstname">
      <Position>9</Position>
      <DataType>varchar(255)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1051" parent="297" name="paypallastname">
      <Position>10</Position>
      <DataType>varchar(255)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1052" parent="297" name="country">
      <Position>11</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1053" parent="297" name="facebookid">
      <Position>12</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1054" parent="297" name="backgroundcolor">
      <Position>13</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1055" parent="297" name="companybanner">
      <Position>14</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1056" parent="297" name="companylogo">
      <Position>15</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1057" parent="297" name="companylocality">
      <Position>16</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1058" parent="297" name="companyname">
      <Position>17</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1059" parent="297" name="companydescription">
      <Position>18</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1060" parent="297" name="countrylogo">
      <Position>19</Position>
      <DataType>varchar(1000)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1061" parent="297" name="otherpayment">
      <Position>20</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1062" parent="297" name="taxinfo">
      <Position>21</Position>
      <DataType>varchar(500)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1063" parent="297" name="commission">
      <Position>22</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1064" parent="297" name="self_support">
      <Position>23</Position>
      <Comment>是否自营 1为自营 0为非自营</Comment>
      <DataType>tinyint(1)|0s</DataType>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1065" parent="297" name="menu_show">
      <Position>24</Position>
      <Comment>是否展示在Menu上默认最多6个</Comment>
      <DataType>tinyint(1)|0s</DataType>
      <DefaultExpression>1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1066" parent="297" name="show">
      <Position>25</Position>
      <Comment>是否显示该Seller</Comment>
      <DataType>tinyint(1)|0s</DataType>
      <DefaultExpression>1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1067" parent="297" name="customer_name">
      <Position>26</Position>
      <Comment>在库-大建云 店铺名</Comment>
      <DataType>varchar(100)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1068" parent="297" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1069" parent="298" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1070" parent="298" name="customer_id">
      <Position>2</Position>
      <DataType>smallint(6)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1071" parent="298" name="seller_id">
      <Position>3</Position>
      <DataType>smallint(6)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1072" parent="298" name="nickname">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1073" parent="298" name="summary">
      <Position>5</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1074" parent="298" name="review">
      <Position>6</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1075" parent="298" name="status">
      <Position>7</Position>
      <DataType>int(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1076" parent="298" name="createdate">
      <Position>8</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1077" parent="298" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1078" parent="299" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1079" parent="299" name="seller_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1080" parent="299" name="information_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1081" parent="299" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1082" parent="300" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1083" parent="300" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1084" parent="300" name="customer_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1085" parent="300" name="product_id">
      <Position>4</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1086" parent="300" name="order_product_id">
      <Position>5</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1087" parent="300" name="price">
      <Position>6</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1088" parent="300" name="quantity">
      <Position>7</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1089" parent="300" name="shipping">
      <Position>8</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1090" parent="300" name="shipping_rate">
      <Position>9</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1091" parent="300" name="payment">
      <Position>10</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1092" parent="300" name="payment_rate">
      <Position>11</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1093" parent="300" name="admin">
      <Position>12</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1094" parent="300" name="customer">
      <Position>13</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1095" parent="300" name="shipping_applied">
      <Position>14</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1096" parent="300" name="commission_applied">
      <Position>15</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1097" parent="300" name="currency_code">
      <Position>16</Position>
      <DataType>varchar(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1098" parent="300" name="currency_value">
      <Position>17</Position>
      <DataType>decimal(15,8 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1099" parent="300" name="details">
      <Position>18</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1100" parent="300" name="paid_status">
      <Position>19</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1101" parent="300" name="date_added">
      <Position>20</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1102" parent="300" name="order_product_status">
      <Position>21</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1103" parent="300" name="option_data">
      <Position>22</Position>
      <DataType>varchar(5000)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1104" parent="300" name="seller_access">
      <Position>23</Position>
      <DataType>int(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="1105" parent="300" name="product_id">
      <ColNames>product_id</ColNames>
      <Type>btree</Type>
    </index>
    <index id="1106" parent="300" name="order_product_id">
      <ColNames>order_product_id</ColNames>
      <Type>btree</Type>
    </index>
    <key id="1107" parent="300" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1108" parent="301" name="change_orderstatus_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1109" parent="301" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1110" parent="301" name="product_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1111" parent="301" name="customer_id">
      <Position>4</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1112" parent="301" name="order_status_id">
      <Position>5</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1113" parent="301" name="comment">
      <Position>6</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1114" parent="301" name="date_added">
      <Position>7</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1115" parent="301" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>change_orderstatus_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1116" parent="302" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1117" parent="302" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1118" parent="302" name="amount">
      <Position>3</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1119" parent="302" name="text">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1120" parent="302" name="details">
      <Position>5</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1121" parent="302" name="request_type">
      <Position>6</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1122" parent="302" name="paid">
      <Position>7</Position>
      <DataType>int(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1123" parent="302" name="balance_reduced">
      <Position>8</Position>
      <DataType>int(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1124" parent="302" name="payment">
      <Position>9</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1125" parent="302" name="date_added">
      <Position>10</Position>
      <DataType>date|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;0000-00-00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1126" parent="302" name="date_modified">
      <Position>11</Position>
      <DataType>date|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;0000-00-00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1127" parent="302" name="added_by">
      <Position>12</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1128" parent="302" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1129" parent="303" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1130" parent="303" name="customer_id">
      <Position>2</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1131" parent="303" name="product_id">
      <Position>3</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1132" parent="303" name="price">
      <Position>4</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1133" parent="303" name="seller_price">
      <Position>5</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1134" parent="303" name="currency_code">
      <Position>6</Position>
      <DataType>varchar(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1135" parent="303" name="quantity">
      <Position>7</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="1136" parent="303" name="index_customer_id">
      <ColNames>customer_id</ColNames>
      <Type>btree</Type>
    </index>
    <index id="1137" parent="303" name="index_product_id">
      <ColNames>product_id</ColNames>
      <Type>btree</Type>
    </index>
    <key id="1138" parent="303" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1139" parent="304" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1140" parent="304" name="seller_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1141" parent="304" name="category_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1142" parent="304" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1143" parent="305" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1144" parent="305" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1145" parent="305" name="order_id">
      <Position>3</Position>
      <DataType>varchar(500)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1146" parent="305" name="order_product_id">
      <Position>4</Position>
      <DataType>varchar(500)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1147" parent="305" name="amount">
      <Position>5</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1148" parent="305" name="text">
      <Position>6</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1149" parent="305" name="details">
      <Position>7</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1150" parent="305" name="date_added">
      <Position>8</Position>
      <DataType>date|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;0000-00-00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1151" parent="305" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1152" parent="306" name="id">
      <Position>1</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1153" parent="306" name="seller_id">
      <Position>2</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1154" parent="306" name="buyer_id">
      <Position>3</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1155" parent="306" name="product_id">
      <Position>4</Position>
      <Comment>oc_product.product_id</Comment>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1156" parent="306" name="current_price">
      <Position>5</Position>
      <Comment>当前生效的价格</Comment>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1157" parent="306" name="product_display">
      <Position>6</Position>
      <Comment>是否显示价格（默认显示）</Comment>
      <DataType>tinyint(1) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1158" parent="306" name="price">
      <Position>7</Position>
      <Comment>精细化管理的价格</Comment>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1159" parent="306" name="effective_time">
      <Position>8</Position>
      <Comment>生效时间</Comment>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;1970-01-01 00:00:00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1160" parent="306" name="expiration_time">
      <Position>9</Position>
      <Comment>失效时间</Comment>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;9999-01-01 00:00:00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1161" parent="306" name="is_update">
      <Position>10</Position>
      <Comment>用于标识是否 更新了价格( price-&gt;current_price )</Comment>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1162" parent="306" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1163" parent="307" name="id">
      <Position>1</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1164" parent="307" name="type">
      <Position>2</Position>
      <Comment>类型：&#xd;
1-&gt; 新添加; 2-&gt;用户删除; 3-&gt;用户修改; 4-&gt;超时自动删除</Comment>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1165" parent="307" name="origin_id">
      <Position>3</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1166" parent="307" name="seller_id">
      <Position>4</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1167" parent="307" name="buyer_id">
      <Position>5</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1168" parent="307" name="product_id">
      <Position>6</Position>
      <Comment>oc_product.product_id</Comment>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1169" parent="307" name="current_price">
      <Position>7</Position>
      <Comment>当前生效的价格</Comment>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1170" parent="307" name="product_display">
      <Position>8</Position>
      <Comment>是否显示价格（默认显示）</Comment>
      <DataType>tinyint(1) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1171" parent="307" name="price">
      <Position>9</Position>
      <Comment>精细化管理的价格</Comment>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1172" parent="307" name="effective_time">
      <Position>10</Position>
      <Comment>生效时间</Comment>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;1970-01-01 00:00:00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1173" parent="307" name="expiration_time">
      <Position>11</Position>
      <Comment>失效时间</Comment>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;9999-01-01 00:00:00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1174" parent="307" name="add_time">
      <Position>12</Position>
      <Comment>历史记录添加时间</Comment>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;1970-01-01 00:00:00&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1175" parent="307" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1176" parent="308" name="download_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1177" parent="308" name="filename">
      <Position>2</Position>
      <DataType>varchar(160)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1178" parent="308" name="mask">
      <Position>3</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1179" parent="308" name="date_added">
      <Position>4</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1180" parent="308" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>download_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1181" parent="309" name="download_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1182" parent="309" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1183" parent="309" name="name">
      <Position>3</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1184" parent="309" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>download_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1185" parent="310" name="event_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1186" parent="310" name="code">
      <Position>2</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1187" parent="310" name="trigger">
      <Position>3</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1188" parent="310" name="action">
      <Position>4</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1189" parent="310" name="status">
      <Position>5</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1190" parent="310" name="sort_order">
      <Position>6</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1191" parent="310" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>event_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1192" parent="311" name="extension_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1193" parent="311" name="type">
      <Position>2</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1194" parent="311" name="code">
      <Position>3</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1195" parent="311" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>extension_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1196" parent="312" name="extension_install_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1197" parent="312" name="extension_download_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1198" parent="312" name="filename">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1199" parent="312" name="date_added">
      <Position>4</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1200" parent="312" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>extension_install_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1201" parent="313" name="extension_path_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1202" parent="313" name="extension_install_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1203" parent="313" name="path">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1204" parent="313" name="date_added">
      <Position>4</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1205" parent="313" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>extension_path_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1206" parent="314" name="filter_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1207" parent="314" name="filter_group_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1208" parent="314" name="sort_order">
      <Position>3</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1209" parent="314" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>filter_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1210" parent="315" name="filter_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1211" parent="315" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1212" parent="315" name="filter_group_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1213" parent="315" name="name">
      <Position>4</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1214" parent="315" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>filter_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1215" parent="316" name="filter_group_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1216" parent="316" name="sort_order">
      <Position>2</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1217" parent="316" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>filter_group_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1218" parent="317" name="filter_group_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1219" parent="317" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1220" parent="317" name="name">
      <Position>3</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1221" parent="317" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>filter_group_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1222" parent="318" name="geo_zone_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1223" parent="318" name="name">
      <Position>2</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1224" parent="318" name="description">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1225" parent="318" name="date_added">
      <Position>4</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1226" parent="318" name="date_modified">
      <Position>5</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1227" parent="318" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>geo_zone_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1228" parent="319" name="information_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1229" parent="319" name="bottom">
      <Position>2</Position>
      <DataType>int(1)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1230" parent="319" name="sort_order">
      <Position>3</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1231" parent="319" name="status">
      <Position>4</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1232" parent="319" name="country">
      <Position>5</Position>
      <Comment>支持的国别</Comment>
      <DataType>varchar(50)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1233" parent="319" name="parent_id">
      <Position>6</Position>
      <Comment>父节点</Comment>
      <DataType>int(11)|0s</DataType>
      <DefaultExpression>-1</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1234" parent="319" name="is_link">
      <Position>7</Position>
      <Comment>是否是链接</Comment>
      <DataType>tinyint(1)|0s</DataType>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1235" parent="319" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>information_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1236" parent="320" name="information_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1237" parent="320" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1238" parent="320" name="title">
      <Position>3</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1239" parent="320" name="description">
      <Position>4</Position>
      <DataType>mediumtext|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1240" parent="320" name="meta_title">
      <Position>5</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1241" parent="320" name="meta_description">
      <Position>6</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1242" parent="320" name="meta_keyword">
      <Position>7</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1243" parent="320" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>information_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1244" parent="321" name="information_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1245" parent="321" name="store_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1246" parent="321" name="layout_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1247" parent="321" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>information_id
store_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1248" parent="322" name="information_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1249" parent="322" name="store_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1250" parent="322" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>information_id
store_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1251" parent="323" name="id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1252" parent="323" name="customer_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1253" parent="323" name="invitation_code">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1254" parent="323" name="invitee_customer_id">
      <Position>4</Position>
      <Comment>被邀请人ID</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1255" parent="323" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1256" parent="324" name="language_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1257" parent="324" name="name">
      <Position>2</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1258" parent="324" name="code">
      <Position>3</Position>
      <DataType>varchar(5)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1259" parent="324" name="locale">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1260" parent="324" name="image">
      <Position>5</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1261" parent="324" name="directory">
      <Position>6</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1262" parent="324" name="sort_order">
      <Position>7</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1263" parent="324" name="status">
      <Position>8</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="1264" parent="324" name="name">
      <ColNames>name</ColNames>
      <Type>btree</Type>
    </index>
    <key id="1265" parent="324" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1266" parent="325" name="layout_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1267" parent="325" name="name">
      <Position>2</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1268" parent="325" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>layout_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1269" parent="326" name="layout_module_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1270" parent="326" name="layout_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1271" parent="326" name="code">
      <Position>3</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1272" parent="326" name="position">
      <Position>4</Position>
      <DataType>varchar(14)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1273" parent="326" name="sort_order">
      <Position>5</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1274" parent="326" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>layout_module_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1275" parent="327" name="layout_route_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1276" parent="327" name="layout_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1277" parent="327" name="store_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1278" parent="327" name="route">
      <Position>4</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1279" parent="327" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>layout_route_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1280" parent="328" name="length_class_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1281" parent="328" name="value">
      <Position>2</Position>
      <DataType>decimal(15,8 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1282" parent="328" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>length_class_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1283" parent="329" name="length_class_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1284" parent="329" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1285" parent="329" name="title">
      <Position>3</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1286" parent="329" name="unit">
      <Position>4</Position>
      <DataType>varchar(4)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1287" parent="329" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>length_class_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1288" parent="330" name="location_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1289" parent="330" name="name">
      <Position>2</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1290" parent="330" name="address">
      <Position>3</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1291" parent="330" name="telephone">
      <Position>4</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1292" parent="330" name="fax">
      <Position>5</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1293" parent="330" name="geocode">
      <Position>6</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1294" parent="330" name="image">
      <Position>7</Position>
      <DataType>varchar(255)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1295" parent="330" name="open">
      <Position>8</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1296" parent="330" name="comment">
      <Position>9</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="1297" parent="330" name="name">
      <ColNames>name</ColNames>
      <Type>btree</Type>
    </index>
    <key id="1298" parent="330" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>location_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1299" parent="331" name="manufacturer_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1300" parent="331" name="name">
      <Position>2</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1301" parent="331" name="image">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1302" parent="331" name="image_id">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1303" parent="331" name="sort_order">
      <Position>5</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1304" parent="331" name="customer_id">
      <Position>6</Position>
      <Comment>客户id</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1305" parent="331" name="is_partner">
      <Position>7</Position>
      <Comment>是否为卖家</Comment>
      <DataType>tinyint(4)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1306" parent="331" name="can_brand">
      <Position>8</Position>
      <Comment>能否被贴牌 0 ：不能1：能 默认为不能</Comment>
      <DataType>tinyint(4)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1307" parent="331" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>manufacturer_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1308" parent="332" name="manufacturer_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1309" parent="332" name="store_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1310" parent="332" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>manufacturer_id
store_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1311" parent="333" name="marketing_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1312" parent="333" name="name">
      <Position>2</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1313" parent="333" name="description">
      <Position>3</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1314" parent="333" name="code">
      <Position>4</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1315" parent="333" name="clicks">
      <Position>5</Position>
      <DataType>int(5)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1316" parent="333" name="date_added">
      <Position>6</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1317" parent="333" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>marketing_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1318" parent="334" name="modification_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1319" parent="334" name="extension_install_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1320" parent="334" name="name">
      <Position>3</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1321" parent="334" name="code">
      <Position>4</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1322" parent="334" name="author">
      <Position>5</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1323" parent="334" name="version">
      <Position>6</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1324" parent="334" name="link">
      <Position>7</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1325" parent="334" name="xml">
      <Position>8</Position>
      <DataType>mediumtext|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1326" parent="334" name="status">
      <Position>9</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1327" parent="334" name="date_added">
      <Position>10</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1328" parent="334" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>modification_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1329" parent="335" name="module_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1330" parent="335" name="name">
      <Position>2</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1331" parent="335" name="code">
      <Position>3</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1332" parent="335" name="setting">
      <Position>4</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1333" parent="335" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>module_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1334" parent="336" name="customer_activity_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1335" parent="336" name="id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1336" parent="336" name="key">
      <Position>3</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1337" parent="336" name="data">
      <Position>4</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1338" parent="336" name="ip">
      <Position>5</Position>
      <DataType>varchar(40)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1339" parent="336" name="date_added">
      <Position>6</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1340" parent="336" name="is_read">
      <Position>7</Position>
      <Comment>是否已读</Comment>
      <DataType>tinyint(1) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1341" parent="336" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>customer_activity_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1342" parent="337" name="id">
      <Position>1</Position>
      <DataType>int(11) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1343" parent="337" name="cart_id">
      <Position>2</Position>
      <DataType>int(11) unsigned|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1344" parent="337" name="seller_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1345" parent="337" name="customer_id">
      <Position>4</Position>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1346" parent="337" name="session_id">
      <Position>5</Position>
      <DataType>varchar(32)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1347" parent="337" name="product_id">
      <Position>6</Position>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1348" parent="337" name="recurring_id">
      <Position>7</Position>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1349" parent="337" name="option">
      <Position>8</Position>
      <DataType>text|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1350" parent="337" name="quantity">
      <Position>9</Position>
      <DataType>int(5)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1351" parent="337" name="date_added">
      <Position>10</Position>
      <DataType>datetime|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1352" parent="337" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1353" parent="338" name="option_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1354" parent="338" name="type">
      <Position>2</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1355" parent="338" name="sort_order">
      <Position>3</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1356" parent="338" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>option_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1357" parent="339" name="option_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1358" parent="339" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1359" parent="339" name="name">
      <Position>3</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1360" parent="339" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>option_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1361" parent="340" name="option_value_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1362" parent="340" name="option_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1363" parent="340" name="image">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1364" parent="340" name="sort_order">
      <Position>4</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1365" parent="340" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>option_value_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1366" parent="341" name="option_value_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1367" parent="341" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1368" parent="341" name="option_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1369" parent="341" name="name">
      <Position>4</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1370" parent="341" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>option_value_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1371" parent="342" name="order_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1372" parent="342" name="invoice_no">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1373" parent="342" name="invoice_prefix">
      <Position>3</Position>
      <DataType>varchar(26)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1374" parent="342" name="store_id">
      <Position>4</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1375" parent="342" name="store_name">
      <Position>5</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1376" parent="342" name="store_url">
      <Position>6</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1377" parent="342" name="customer_id">
      <Position>7</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1378" parent="342" name="customer_group_id">
      <Position>8</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1379" parent="342" name="firstname">
      <Position>9</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1380" parent="342" name="lastname">
      <Position>10</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1381" parent="342" name="email">
      <Position>11</Position>
      <DataType>varchar(96)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1382" parent="342" name="telephone">
      <Position>12</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1383" parent="342" name="fax">
      <Position>13</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1384" parent="342" name="custom_field">
      <Position>14</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1385" parent="342" name="payment_firstname">
      <Position>15</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1386" parent="342" name="payment_lastname">
      <Position>16</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1387" parent="342" name="payment_company">
      <Position>17</Position>
      <DataType>varchar(60)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1388" parent="342" name="payment_address_1">
      <Position>18</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1389" parent="342" name="payment_address_2">
      <Position>19</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1390" parent="342" name="payment_city">
      <Position>20</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1391" parent="342" name="payment_postcode">
      <Position>21</Position>
      <DataType>varchar(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1392" parent="342" name="payment_country">
      <Position>22</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1393" parent="342" name="payment_country_id">
      <Position>23</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1394" parent="342" name="payment_zone">
      <Position>24</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1395" parent="342" name="payment_zone_id">
      <Position>25</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1396" parent="342" name="payment_address_format">
      <Position>26</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1397" parent="342" name="payment_custom_field">
      <Position>27</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1398" parent="342" name="payment_method">
      <Position>28</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1399" parent="342" name="payment_code">
      <Position>29</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1400" parent="342" name="shipping_firstname">
      <Position>30</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1401" parent="342" name="shipping_lastname">
      <Position>31</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1402" parent="342" name="shipping_company">
      <Position>32</Position>
      <DataType>varchar(40)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1403" parent="342" name="shipping_address_1">
      <Position>33</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1404" parent="342" name="shipping_address_2">
      <Position>34</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1405" parent="342" name="shipping_city">
      <Position>35</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1406" parent="342" name="shipping_postcode">
      <Position>36</Position>
      <DataType>varchar(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1407" parent="342" name="shipping_country">
      <Position>37</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1408" parent="342" name="shipping_country_id">
      <Position>38</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1409" parent="342" name="shipping_zone">
      <Position>39</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1410" parent="342" name="shipping_zone_id">
      <Position>40</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1411" parent="342" name="shipping_address_format">
      <Position>41</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1412" parent="342" name="shipping_custom_field">
      <Position>42</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1413" parent="342" name="shipping_method">
      <Position>43</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1414" parent="342" name="shipping_code">
      <Position>44</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1415" parent="342" name="comment">
      <Position>45</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1416" parent="342" name="total">
      <Position>46</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1417" parent="342" name="order_status_id">
      <Position>47</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1418" parent="342" name="affiliate_id">
      <Position>48</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1419" parent="342" name="commission">
      <Position>49</Position>
      <DataType>decimal(15,4 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1420" parent="342" name="marketing_id">
      <Position>50</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1421" parent="342" name="tracking">
      <Position>51</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1422" parent="342" name="language_id">
      <Position>52</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1423" parent="342" name="currency_id">
      <Position>53</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1424" parent="342" name="currency_code">
      <Position>54</Position>
      <DataType>varchar(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1425" parent="342" name="currency_value">
      <Position>55</Position>
      <DataType>decimal(15,8 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>1.00000000</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1426" parent="342" name="ip">
      <Position>56</Position>
      <DataType>varchar(40)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1427" parent="342" name="forwarded_ip">
      <Position>57</Position>
      <DataType>varchar(40)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1428" parent="342" name="user_agent">
      <Position>58</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1429" parent="342" name="accept_language">
      <Position>59</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1430" parent="342" name="date_added">
      <Position>60</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1431" parent="342" name="date_modified">
      <Position>61</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1432" parent="342" name="current_currency_value">
      <Position>62</Position>
      <Comment>订单当前汇率</Comment>
      <DataType>decimal(15,8 digit)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1433" parent="342" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1434" parent="343" name="id">
      <Position>1</Position>
      <Comment>主键自增</Comment>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1435" parent="343" name="order_id">
      <Position>2</Position>
      <Comment>订单ID</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1436" parent="343" name="file_path">
      <Position>3</Position>
      <Comment>文件路径</Comment>
      <DataType>varchar(50)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1437" parent="343" name="plat_order_number">
      <Position>4</Position>
      <Comment>平台订单号</Comment>
      <DataType>varchar(50)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1438" parent="343" name="source_id">
      <Position>5</Position>
      <Comment>SOURCE_ID</Comment>
      <DataType>varchar(50)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1439" parent="343" name="create_date">
      <Position>6</Position>
      <Comment>创建时间</Comment>
      <DataType>datetime|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1440" parent="343" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1441" parent="344" name="order_history_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1442" parent="344" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1443" parent="344" name="order_status_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1444" parent="344" name="notify">
      <Position>4</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1445" parent="344" name="comment">
      <Position>5</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1446" parent="344" name="date_added">
      <Position>6</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1447" parent="344" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_history_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1448" parent="345" name="order_option_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1449" parent="345" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1450" parent="345" name="order_product_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1451" parent="345" name="product_option_id">
      <Position>4</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1452" parent="345" name="product_option_value_id">
      <Position>5</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1453" parent="345" name="name">
      <Position>6</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1454" parent="345" name="value">
      <Position>7</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1455" parent="345" name="type">
      <Position>8</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1456" parent="345" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_option_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1457" parent="346" name="id">
      <Position>1</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1458" parent="346" name="c_id">
      <Position>2</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1459" parent="346" name="product_id">
      <Position>3</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1460" parent="346" name="quantity">
      <Position>4</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1461" parent="346" name="order_id">
      <Position>5</Position>
      <DataType>int(200)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1462" parent="346" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1463" parent="347" name="id">
      <Position>1</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1464" parent="347" name="customer_id">
      <Position>2</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1465" parent="347" name="name">
      <Position>3</Position>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1466" parent="347" name="email">
      <Position>4</Position>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1467" parent="347" name="query">
      <Position>5</Position>
      <DataType>varchar(1000)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1468" parent="347" name="notify">
      <Position>6</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1469" parent="347" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1470" parent="348" name="order_product_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1471" parent="348" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1472" parent="348" name="product_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1473" parent="348" name="name">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1474" parent="348" name="model">
      <Position>5</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1475" parent="348" name="quantity">
      <Position>6</Position>
      <DataType>int(4)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1476" parent="348" name="price">
      <Position>7</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1477" parent="348" name="total">
      <Position>8</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1478" parent="348" name="tax">
      <Position>9</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1479" parent="348" name="reward">
      <Position>10</Position>
      <DataType>int(8)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1480" parent="348" name="service_fee">
      <Position>11</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1481" parent="348" name="poundage">
      <Position>12</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="1482" parent="348" name="order_id">
      <ColNames>order_id</ColNames>
      <Type>btree</Type>
    </index>
    <key id="1483" parent="348" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_product_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1484" parent="349" name="order_recurring_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1485" parent="349" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1486" parent="349" name="reference">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1487" parent="349" name="product_id">
      <Position>4</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1488" parent="349" name="product_name">
      <Position>5</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1489" parent="349" name="product_quantity">
      <Position>6</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1490" parent="349" name="recurring_id">
      <Position>7</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1491" parent="349" name="recurring_name">
      <Position>8</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1492" parent="349" name="recurring_description">
      <Position>9</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1493" parent="349" name="recurring_frequency">
      <Position>10</Position>
      <DataType>varchar(25)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1494" parent="349" name="recurring_cycle">
      <Position>11</Position>
      <DataType>smallint(6)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1495" parent="349" name="recurring_duration">
      <Position>12</Position>
      <DataType>smallint(6)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1496" parent="349" name="recurring_price">
      <Position>13</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1497" parent="349" name="trial">
      <Position>14</Position>
      <DataType>tinyint(1)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1498" parent="349" name="trial_frequency">
      <Position>15</Position>
      <DataType>varchar(25)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1499" parent="349" name="trial_cycle">
      <Position>16</Position>
      <DataType>smallint(6)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1500" parent="349" name="trial_duration">
      <Position>17</Position>
      <DataType>smallint(6)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1501" parent="349" name="trial_price">
      <Position>18</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1502" parent="349" name="status">
      <Position>19</Position>
      <DataType>tinyint(4)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1503" parent="349" name="date_added">
      <Position>20</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1504" parent="349" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_recurring_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1505" parent="350" name="order_recurring_transaction_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1506" parent="350" name="order_recurring_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1507" parent="350" name="reference">
      <Position>3</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1508" parent="350" name="type">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1509" parent="350" name="amount">
      <Position>5</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1510" parent="350" name="date_added">
      <Position>6</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1511" parent="350" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_recurring_transaction_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1512" parent="351" name="order_shipment_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1513" parent="351" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1514" parent="351" name="date_added">
      <Position>3</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1515" parent="351" name="shipping_courier_id">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1516" parent="351" name="tracking_number">
      <Position>5</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>&apos;&apos;</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1517" parent="351" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_shipment_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1518" parent="352" name="order_status_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1519" parent="352" name="language_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1520" parent="352" name="name">
      <Position>3</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1521" parent="352" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_status_id
language_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1522" parent="353" name="order_total_id">
      <Position>1</Position>
      <DataType>int(10)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1523" parent="353" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1524" parent="353" name="code">
      <Position>3</Position>
      <DataType>varchar(32)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1525" parent="353" name="title">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1526" parent="353" name="value">
      <Position>5</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0.00</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1527" parent="353" name="sort_order">
      <Position>6</Position>
      <DataType>int(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <index id="1528" parent="353" name="order_id">
      <ColNames>order_id</ColNames>
      <Type>btree</Type>
    </index>
    <key id="1529" parent="353" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_total_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1530" parent="354" name="order_voucher_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1531" parent="354" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1532" parent="354" name="voucher_id">
      <Position>3</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1533" parent="354" name="description">
      <Position>4</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1534" parent="354" name="code">
      <Position>5</Position>
      <DataType>varchar(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1535" parent="354" name="from_name">
      <Position>6</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1536" parent="354" name="from_email">
      <Position>7</Position>
      <DataType>varchar(96)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1537" parent="354" name="to_name">
      <Position>8</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1538" parent="354" name="to_email">
      <Position>9</Position>
      <DataType>varchar(96)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1539" parent="354" name="voucher_theme_id">
      <Position>10</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1540" parent="354" name="message">
      <Position>11</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1541" parent="354" name="amount">
      <Position>12</Position>
      <DataType>decimal(15,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1542" parent="354" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>order_voucher_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1543" parent="355" name="paypal_order_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1544" parent="355" name="order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1545" parent="355" name="date_added">
      <Position>3</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1546" parent="355" name="date_modified">
      <Position>4</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1547" parent="355" name="capture_status">
      <Position>5</Position>
      <DataType>enum(&apos;Complete&apos;, &apos;NotComplete&apos;)|0e</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1548" parent="355" name="currency_code">
      <Position>6</Position>
      <DataType>char(3)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1549" parent="355" name="authorization_id">
      <Position>7</Position>
      <DataType>varchar(30)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1550" parent="355" name="total">
      <Position>8</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1551" parent="355" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>paypal_order_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1552" parent="356" name="paypal_order_transaction_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1553" parent="356" name="paypal_order_id">
      <Position>2</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1554" parent="356" name="transaction_id">
      <Position>3</Position>
      <DataType>char(20)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1555" parent="356" name="parent_id">
      <Position>4</Position>
      <DataType>char(20)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1556" parent="356" name="date_added">
      <Position>5</Position>
      <DataType>datetime|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1557" parent="356" name="note">
      <Position>6</Position>
      <DataType>varchar(255)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1558" parent="356" name="msgsubid">
      <Position>7</Position>
      <DataType>char(38)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1559" parent="356" name="receipt_id">
      <Position>8</Position>
      <DataType>char(20)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1560" parent="356" name="payment_type">
      <Position>9</Position>
      <DataType>enum(&apos;none&apos;, &apos;echeck&apos;, &apos;instant&apos;, &apos;refund&apos;, &apos;void&apos;)|0e</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1561" parent="356" name="payment_status">
      <Position>10</Position>
      <DataType>char(20)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1562" parent="356" name="pending_reason">
      <Position>11</Position>
      <DataType>char(50)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1563" parent="356" name="transaction_entity">
      <Position>12</Position>
      <DataType>char(50)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1564" parent="356" name="amount">
      <Position>13</Position>
      <DataType>decimal(10,2 digit)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1565" parent="356" name="debug_data">
      <Position>14</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1566" parent="356" name="call_data">
      <Position>15</Position>
      <DataType>text|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1567" parent="356" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>paypal_order_transaction_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1568" parent="357" name="platform_id">
      <Position>1</Position>
      <Comment>平台id，自增</Comment>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1569" parent="357" name="platform_name">
      <Position>2</Position>
      <Comment>平台名称</Comment>
      <DataType>varchar(200)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1570" parent="357" name="state">
      <Position>3</Position>
      <Comment>状态 1-正常 2-停用</Comment>
      <DataType>int(11)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1571" parent="357" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>platform_id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1572" parent="358" name="id">
      <Position>1</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1573" parent="358" name="product_id">
      <Position>2</Position>
      <DataType>int(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1574" parent="358" name="base_price">
      <Position>3</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1575" parent="358" name="initial_price">
      <Position>4</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1576" parent="358" name="rem_price">
      <Position>5</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1577" parent="358" name="deduction_type">
      <Position>6</Position>
      <DataType>varchar(100)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1578" parent="358" name="cur_quantity">
      <Position>7</Position>
      <DataType>int(100)|0s</DataType>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1579" parent="358" name="cur_available_date">
      <Position>8</Position>
      <DataType>date|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1580" parent="358" name="wk_available_date">
      <Position>9</Position>
      <DataType>date|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1581" parent="358" name="wk_percentage_price">
      <Position>10</Position>
      <DataType>int(50)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1582" parent="358" name="wk_fixed_price">
      <Position>11</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1583" parent="358" name="wk_work_on">
      <Position>12</Position>
      <DataType>varchar(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1584" parent="358" name="status">
      <Position>13</Position>
      <DataType>int(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1585" parent="358" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1586" parent="359" name="id">
      <Position>1</Position>
      <DataType>int(10) unsigned|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1587" parent="359" name="order_id">
      <Position>2</Position>
      <DataType>int(50)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1588" parent="359" name="product_id">
      <Position>3</Position>
      <DataType>int(50)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1589" parent="359" name="customer_id">
      <Position>4</Position>
      <DataType>int(50)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1590" parent="359" name="quantity">
      <Position>5</Position>
      <DataType>int(50)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1591" parent="359" name="base_price">
      <Position>6</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1592" parent="359" name="initial_price">
      <Position>7</Position>
      <DataType>float|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1593" parent="359" name="notified">
      <Position>8</Position>
      <DataType>int(5)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1594" parent="359" name="status">
      <Position>9</Position>
      <DataType>int(10)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <key id="1595" parent="359" name="PRIMARY">
      <NameSurrogate>1</NameSurrogate>
      <ColNames>id</ColNames>
      <Primary>1</Primary>
    </key>
    <column id="1596" parent="360" name="product_id">
      <Position>1</Position>
      <DataType>int(11)|0s</DataType>
      <NotNull>1</NotNull>
      <SequenceIdentity>1</SequenceIdentity>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1597" parent="360" name="model">
      <Position>2</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1598" parent="360" name="sku">
      <Position>3</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1599" parent="360" name="upc">
      <Position>4</Position>
      <DataType>varchar(12)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1600" parent="360" name="ean">
      <Position>5</Position>
      <DataType>varchar(14)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1601" parent="360" name="jan">
      <Position>6</Position>
      <DataType>varchar(13)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1602" parent="360" name="isbn">
      <Position>7</Position>
      <DataType>varchar(17)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1603" parent="360" name="mpn">
      <Position>8</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1604" parent="360" name="asin">
      <Position>9</Position>
      <DataType>varchar(64)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1605" parent="360" name="location">
      <Position>10</Position>
      <DataType>varchar(128)|0s</DataType>
      <NotNull>1</NotNull>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1606" parent="360" name="quantity">
      <Position>11</Position>
      <DataType>int(4)|0s</DataType>
      <NotNull>1</NotNull>
      <DefaultExpression>0</DefaultExpression>
      <ColumnKind>normal</ColumnKind>
    </column>
    <column id="1607" parent="360" name="stock_status_id">
      <Position>