(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3f6ffe3e"],{"21f2":function(t,e,n){"use strict";n.d(e,"a",(function(){return c}));var i=n("24e5"),o=n.n(i),a="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY\n7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKN\nPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gA\nkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWow\ncSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99Ecv\nDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthh\nYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3\nUP8iWi1Qw0Y=";function c(t){var e=new o.a;return e.setPrivateKey(a),e.decrypt(t)}},"4c52":function(t,e,n){"use strict";n("d363")},"6c8a":function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"login"},[t.loading?n("i",{staticClass:"el-icon-loading",staticStyle:{"margin-bottom":"20px","font-size":"xx-large",color:"white"}}):t._e(),n("div",{staticStyle:{display:"flex","flex-direction":"column","justify-content":"center","align-items":"center"}},[n("el-row",{staticStyle:{color:"white","font-size":"xx-large"}},[t._v(" "+t._s(t.msg)+" ")]),t.loading?t._e():n("el-link",{staticStyle:{"font-size":"large","padding-top":"20px"},attrs:{type:"primary",href:"https://cbsapp-dev.ey.com.cn/filing"}},[t._v("Re-Login")])],1)])},o=[],a=(n("14d9"),n("852e")),c=n.n(a),r=(n("21f2"),{name:"Login",data:function(){return{msg:"Logging in, please wait a while.",loading:!1,isForbiddenRole:!1}},watch:{$route:{handler:function(t){this.redirect=t.query&&t.query.redirect},immediate:!0}},mounted:function(){this.handleSSOLogin()},created:function(){},methods:{handleSSOLogin:function(){var t=this,e=c.a.get("d");this.loading=!0,e?this.$store.dispatch("AADLogin",e).then((function(){t.$router.push({path:t.redirect||"/"}).catch((function(){}))})).catch((function(){t.loading=!1})):(this.msg="Timeout occurred while fetching AAD token.",this.loading=!1,this.$message({type:"error",message:"Failed to get AAD token."}))}}}),s=r,l=(n("4c52"),n("2877")),u=Object(l["a"])(s,i,o,!1,null,null,null);e["default"]=u.exports},d363:function(t,e,n){}}]);