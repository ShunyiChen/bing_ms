(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-19e27812"],{"080a":function(e,t,o){"use strict";o("94f2")},"5a20":function(e,t,o){"use strict";o("d8fc")},"8a8f":function(e,t,o){"use strict";o.d(t,"c",(function(){return n})),o.d(t,"a",(function(){return r})),o.d(t,"b",(function(){return i}));var a=o("b775");function n(e){return Object(a["a"])({url:"/filingsystem/filing/boxprofile/borrowrecord/searchBoxBorrowRecords",method:"post",data:e})}function r(e){return Object(a["a"])({url:"/filingsystem/filing/boxprofile/borrowrecord/changeBoxBorrowStatus",method:"post",data:e})}function i(e){return Object(a["a"])({url:"/filingsystem/filing/boxprofile/borrowrecord/changeChargeCode",method:"post",data:e})}},"94f2":function(e,t,o){},a11e:function(e,t,o){"use strict";o.r(t);var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"app-container"},[e.showFileID?o("div",[o("BoxReturnSearch",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},fetchBoxResult:e.fetchBoxResult}}),o("right-toolbar",{attrs:{showSearch:e.showSearch,columns:e.columns},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.searchByPaging}})],1):e._e(),e.showConfirmMessage?o("div",e._l(e.messageItems,(function(t){return o("ul",{key:Math.random(),staticStyle:{margin:"0",padding:"3px 10px"}},[o("li",{style:"color: "+("Available"==t.state?"#13ce66":"red")},[e._v(e._s(e.addMessageItem(t)))])])})),0):e._e(),e.showTotalFiles?o("div",[o("span",[e._v(" There is(are) total "+e._s(e.totalSelected)+" files in the list. ")]),o("el-table",{attrs:{border:"",loading:e.loading,data:e.selectedProfileList,"cell-style":e.columnStyle,"header-cell-style":e.headerColStyle}},[e.columns[0].visible?o("el-table-column",{key:Math.random(),attrs:{label:"ID",align:"center",width:"140",prop:"id"}}):e._e(),e.columns[1].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Box ID",align:"center",width:"140",prop:"boxId"}}):e._e(),e.columns[2].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Dept ID",align:"center",width:"140",prop:"deptId"}}):e._e(),o("el-table-column",{key:Math.random(),attrs:{label:"Location",align:"center",width:"140",prop:"deptId"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("span",[e._v(e._s(e.getLocation(t.row.deptId)))])]}}],null,!1,3049742724)}),e.columns[3].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Box No",align:"center",width:"140",prop:"boxNumber"}}):e._e(),e.columns[4].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Vendor Barcode",align:"center",width:"140",prop:"vendorBarcode"}}):e._e(),e.columns[5].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Charge Code",align:"center",width:"140",prop:"chargeCode"}}):e._e(),o("el-table-column",{key:Math.random(),attrs:{label:"Return State",align:"center",width:"200",prop:"state"}}),e.columns[6].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Staff ID",align:"center",width:"140",prop:"staffId"}}):e._e(),e.columns[7].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Staff Name",align:"center",width:"255",prop:"staffName"}}):e._e(),e.columns[8].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Staff Email",align:"center",width:"255",prop:"staffEmail"}}):e._e(),e.columns[9].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Status",align:"center",width:"140",prop:"status"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("span",[e._v(e._s(1==t.row.status?"Borrowed":"Returned"))])]}}],null,!1,2428909401)}):e._e(),e.columns[10].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Loan Date",align:"center",width:"200",prop:"loanDate"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("span",[e._v(e._s(e.parseTime(t.row.loanDate,"{y}-{m}-{d} {h}:{i}:{s}")))])]}}],null,!1,3082848049)}):e._e(),e.columns[11].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Return Date",align:"center",width:"200",prop:"returnDate"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("span",[e._v(e._s(e.parseTime(t.row.returnDate,"{y}-{m}-{d} {h}:{i}:{s}")))])]}}],null,!1,2044323351)}):e._e(),e.columns[12].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Notification Status",align:"center",width:"140",prop:"notify"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("span",[e._v(e._s(0==t.row.notify?"Not Notified":"Notified"))])]}}],null,!1,239340901)}):e._e(),e.columns[13].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Update Time",align:"center",width:"200",prop:"updateTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("span",[e._v(e._s(e.parseTime(t.row.updateTime,"{y}-{m}-{d} {h}:{i}:{s}")))])]}}],null,!1,3807545165)}):e._e(),e.columns[14].visible?o("el-table-column",{key:Math.random(),attrs:{label:"Update By",align:"center",width:"255",prop:"updateBy"}}):e._e(),o("el-table-column",{attrs:{label:"Actions",fixed:"right",align:"center",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("el-button",{attrs:{type:"text",size:"small"},nativeOn:{click:function(o){return o.preventDefault(),e.deleteRow(t.$index,e.selectedProfileList)}}},[e._v(" Remove ")])]}}],null,!1,3102271372)})],1)],1):e._e(),this.showConfirm?o("div",{staticStyle:{"text-align":"center","margin-top":"5px"}},[o("el-button",{attrs:{type:"primary",loading:e.confirmLoading},on:{click:e.handleConfirm}},[e._v("Confirm")])],1):e._e()])},n=[],r=(o("d3b7"),o("159b"),o("4de4"),o("14d9"),o("a434"),o("d81d"),o("8a8f")),i=function(){var e=this,t=e.$createElement,o=e._self._c||t;return e.showSearch?o("el-form",{staticClass:"demo-form-inline",attrs:{inline:!1,model:e.form,"label-width":"120px"}},[o("el-form-item",{attrs:{label:"Box No",prop:"boxNo"}},[o("div",{staticStyle:{display:"flex","flex-direction":"row",width:"300px",gap:"5px"}},[o("el-input",{attrs:{clearable:""},model:{value:e.form.boxNo,callback:function(t){e.$set(e.form,"boxNo","string"===typeof t?t.trim():t)},expression:"form.boxNo"}}),o("el-button",{attrs:{type:"primary",loading:e.boxNoLoading,icon:"el-icon-search"},on:{click:e.fetchByBoxNo}},[e._v("Fetch")])],1)]),o("el-form-item",{attrs:{label:"Vendor Barcode",prop:"vendorBarcode"}},[o("div",{staticStyle:{display:"flex","flex-direction":"row",width:"300px",gap:"5px"}},[o("el-input",{attrs:{clearable:""},model:{value:e.form.vendorBarcode,callback:function(t){e.$set(e.form,"vendorBarcode","string"===typeof t?t.trim():t)},expression:"form.vendorBarcode"}}),o("el-button",{attrs:{type:"primary",loading:e.vendorBarCodeLoading,icon:"el-icon-search"},on:{click:e.fetchByVendorBarcode}},[e._v("Fetch")])],1)])],1):e._e()},l=[],s={name:"Boxreturnsearch",components:{},props:{showSearch:{required:!0,type:Boolean}},data:function(){return{boxNoLoading:!1,vendorBarCodeLoading:!1,form:{boxNo:"",vendorBarcode:""}}},methods:{fetchByBoxNo:function(){var e=this;if(this.form.boxNo){this.boxNoLoading=!0;var t={boxNumber:this.form.boxNo,status:1,pageNum:0,pageSize:10};Object(r["c"])(t).then((function(t){e.$emit("fetchBoxResult",t,e.form.boxNo,""),e.boxNoLoading=!1})).catch((function(t){e.boxNoLoading=!1}))}},fetchByVendorBarcode:function(){var e=this;if(this.form.vendorBarcode){this.vendorBarCodeLoading=!0;var t={vendorBarcode:this.form.vendorBarcode,status:1,pageNum:0,pageSize:10};Object(r["c"])(t).then((function(t){e.$emit("fetchBoxResult",t,"",e.form.vendorBarcode),e.vendorBarCodeLoading=!1})).catch((function(t){e.vendorBarCodeLoading=!1}))}}}},c=s,d=(o("5a20"),o("2877")),u=Object(d["a"])(c,i,l,!1,null,"2ec16cc8",null),f=u.exports,h=o("6e56"),m={name:"Boxreturn",components:{BoxReturnSearch:f},data:function(){return{showSearch:!0,columns:[{key:0,label:"ID",visible:!1},{key:1,label:"Box ID",visible:!1},{key:2,label:"Dept ID",visible:!1},{key:3,label:"Box No",visible:!0},{key:4,label:"Vendor Barcode",visible:!0},{key:5,label:"Charge Code",visible:!0},{key:6,label:"Staff ID",visible:!0},{key:7,label:"Staff Name",visible:!0},{key:8,label:"Staff Email",visible:!0},{key:9,label:"Status",visible:!0},{key:10,label:"Loan Date",visible:!0},{key:11,label:"Return Date",visible:!0},{key:12,label:"Notification Status",visible:!0},{key:13,label:"Update Time",visible:!0},{key:14,label:"Update By",visible:!0}],loading:!1,confirmLoading:!1,showFileID:!0,showTotalFiles:!1,selectedProfileList:[],messageItems:[],showConfirmMessage:!1}},computed:{showConfirm:{get:function(){return this.selectedProfileList.length>0},set:function(e){}},totalSelected:{get:function(){return this.selectedProfileList.length},set:function(e){}}},methods:{getLocation:function(e){return 1==e?"BJ":2==e?"GZ":3==e?"HK":4==e?"SH":5==e?"SZ":""},fetchBoxResult:function(e,t,o){var a=this;if(this.showConfirmMessage=!1,this.showFileID=!0,this.showTotalFiles=!0,e.rows&&e.rows.length>0)e.rows.forEach((function(e){1==e.status?e.state="Available":e.state="Not in Borrowed List";var t=a.selectedProfileList.filter((function(t){return t.id==e.id&&t.deptId==e.deptId})).length;0==t&&a.selectedProfileList.push(e)}));else{var n={boxNumber:t,vendorBarcode:o,chargeCode:"",state:"Not in Borrowed List",staffId:"",staffName:"",staffEmail:"",status:"",loanDate:"0000-00-00 00:00:00",returnDate:"0000-00-00 00:00:00",notify:0,updateTime:"0000-00-00 00:00:00",id:-1,deptId:-1},r=this.selectedProfileList.filter((function(e){return e.boxNumber==n.boxNumber&&e.vendorBarcode==n.vendorBarcode&&e.id==n.id&&e.deptId==n.deptId})).length;0==r&&this.selectedProfileList.push(n)}},addMessageItem:function(e){return"Available"==e.state?"Box No: "+e.boxNumber+" successfully returned.":"Not in Borrowed List"==e.state?"Box No: "+e.boxNumber+" is not in borrow record.":void 0},searchByPaging:function(){},deleteRow:function(e,t){t.splice(e,1)},saveBoxBorrowStatus:function(e,t){var o=this;this.confirmLoading=!0;var a=t.map((function(e){return{id:e.id,deptId:e.deptId,boxId:e.boxId,boxNo:e.boxNumber,staffId:e.staffId,staffName:e.staffName,staffEmail:e.staffEmail}})),n={status:0,returnChargeCode:e,keys:a};Object(r["a"])(n).then((function(e){o.showConfirmMessage=!0,o.selectedProfileList=[],o.showTotalFiles=!1,o.confirmLoading=!1})).catch((function(e){o.confirmLoading=!1}))},handleConfirm:function(){var e=this;this.messageItems=this.selectedProfileList;var t=this.selectedProfileList.filter((function(e){return"Available"==e.state}));t.length>0?this.$prompt("Please input a charge code","Input",{confirmButtonText:"OK",cancelButtonText:"Cancel",inputPattern:/^.{1,100}$/,inputErrorMessage:"The input must be within the range of 1 to 100 characters."}).then((function(o){var a=o.value;e.confirmLoading=!0,Object(h["f"])({eid:a}).then((function(o){e.confirmLoading=!1,"CHARGEABLE"==o.data.chargeable?e.saveBoxBorrowStatus(a,t):e.$message({showClose:!0,message:"This charge code is not chargeable. Please provide another input.",type:"error"})})).catch((function(t){e.confirmLoading=!1}))})):(this.showConfirmMessage=!0,this.selectedProfileList=[],this.showTotalFiles=!1,this.confirmLoading=!1)},firstColumnStyle:function(e,t,o,a){return"color: black;padding:0px 0px;"},columnStyle:function(e,t,o,a){return"Not in Borrowed List"==e.row.state?"color: red;padding:0px 0px;":"color: #13ce66;padding:0px 0px;"},headerColStyle:function(e,t,o,a){return"padding:0px 0px;height:10px"}}},b=m,p=(o("080a"),Object(d["a"])(b,a,n,!1,null,"51ff4670",null));t["default"]=p.exports},d8fc:function(e,t,o){}}]);