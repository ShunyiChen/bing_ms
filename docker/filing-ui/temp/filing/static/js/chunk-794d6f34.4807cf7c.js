(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-794d6f34"],{"05df":function(e,t,l){"use strict";l("c34f")},"3c90":function(e,t,l){"use strict";l("70f3")},"70f3":function(e,t,l){},c34f:function(e,t,l){},d61a:function(e,t,l){"use strict";l.r(t);var i=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"app-container",staticStyle:{"background-color":"white"}},[l("BoxProfileSearch",{ref:"boxSearch",attrs:{loading:e.loading,show:e.showSearch,pageSize:e.queryParams.pageSize,historyQuery:!0},on:{whenChangeDeptId:e.whenChangeDeptId,getResult:e.receiveSearchResult,resetPageInfo:e.resetPageInfo,"update:loading":function(t){e.loading=t},"update:show":function(t){e.showSearch=t},"update:pageSize":function(t){return e.$set(e.queryParams,"pageSize",t)},"update:page-size":function(t){return e.$set(e.queryParams,"pageSize",t)}}}),""!=this.newFileID?l("div",{staticStyle:{width:"100%","text-align":"center",height:"auto","margin-bottom":"10px"}},[l("el-tag",{staticClass:"custom-tag",style:"background-color:"+this.$store.state.settings.theme+";--themeColor:"+this.themeColor,attrs:{effect:"dark",type:"success"}},[e._v(" "+e._s(e.title)+" - "+e._s(this.newFileID)+" "),l("i",{staticClass:"el-icon-copy-document",staticStyle:{cursor:"pointer","padding-left":"10px"},attrs:{id:"copyNode"}}),l("i",{staticClass:"el-icon-download",staticStyle:{cursor:"pointer","padding-left":"10px"},attrs:{id:"exportNode"},on:{click:e.handleSingleExport}})])],1):e._e(),l("div",[l("el-row",{attrs:{gutter:10}},[e.showAllButtons?l("el-col",{attrs:{span:1.5}},[l("el-dropdown",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["filingsystem:profile:export"],expression:"['filingsystem:profile:export']"}],on:{command:e.exportSearchData}},[l("el-button",{attrs:{type:"primary",icon:"el-icon-s-data"}},[e._v(" Export Data"),l("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),l("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[l("el-dropdown-item",{attrs:{command:"Export Selected"}},[e._v("Export Selected")]),l("el-dropdown-item",{attrs:{command:"Export All"}},[e._v("Export All")])],1)],1)],1):e._e(),l("right-toolbar",{attrs:{showSearch:e.showSearch,columns:e.columns},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.searchByPaging}})],1)],1),e.showTable?l("div",{staticStyle:{padding:"5px 0px"}},[e._v("Your search return "+e._s(this.total)+" results.")]):e._e(),e.showTable?l("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"multipleTable",attrs:{border:"",data:e.profileList,"cell-style":e.columnStyle,"header-cell-style":e.headerColStyle,"row-style":e.rowStyle,"row-key":function(e){return e.id+"-"+e.deptId+"-"+e.boxId}},on:{"selection-change":e.handleSelectionChange,"row-click":e.handleRowClick}},[l("el-table-column",{key:Math.random(),attrs:{"reserve-selection":!0,type:"selection",width:"55",align:"center"}}),e.columns[0].visible?l("el-table-column",{key:Math.random(),attrs:{fixed:"",label:"Box No",align:"center",width:"140",prop:"boxNumber"}}):e._e(),e.columns[1].visible?l("el-table-column",{key:Math.random(),attrs:{label:"ID",align:"center",width:"140",prop:"id"}}):e._e(),e.columns[2].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Dept ID",align:"center",width:"140",prop:"deptId"}}):e._e(),e.columns[3].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Raw Data",align:"center",width:"140",prop:"oldSystemboxMark"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v(e._s(0==t.row.oldSystemboxMark?"Y":"N"))])]}}],null,!1,2039141382)}):e._e(),e.columns[4].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Box Department",align:"center",width:"140",prop:"boxDepartment"}}):e._e(),e.columns[5].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Vendor Number",align:"center",width:"140",prop:"vendorNumber"}}):e._e(),e.columns[6].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Vendor Barcode",align:"center",width:"140",prop:"vendorBarcode"}}):e._e(),e.columns[7].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Retention Date",align:"center",prop:"retentionDate",width:"140"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v(e._s(e.parseTime(t.row.retentionDate,"{y}-{m}-{d}")))])]}}],null,!1,2710040573)}):e._e(),e.columns[8].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Charge Code",align:"center",width:"140",prop:"chargeCode"}}):e._e(),e.columns[9].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Approver LPN",align:"center",width:"140",prop:"approvedstaffidLpn"}}):e._e(),e.columns[10].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Approver Email",align:"center",width:"255",prop:"approverEmail"}}):e._e(),e.columns[11].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Requester LPN",align:"center",width:"140",prop:"staffidLpn"}}):e._e(),e.columns[12].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Requester Email",align:"center",width:"255",prop:"requesterEmail"}}):e._e(),e.columns[13].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Box Status",align:"center",width:"140",prop:"boxState"}}):e._e(),e.columns[14].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Location",align:"center",width:"140",prop:"areaLocation"}}):e._e(),e.columns[15].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Borrow State",align:"center",width:"140",prop:"borrowState"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v(e._s(0==t.row.borrowState?"Available":"Not Available"))])]}}],null,!1,429398756)}):e._e(),e.columns[16].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Borrower LPN",align:"center",width:"140",prop:"borrowerStaffId"}}):e._e(),e.columns[17].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Borrower Name",align:"center",width:"255",prop:"borrowerName"}}):e._e(),e.columns[18].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Destruction Time",align:"center",width:"255",prop:"destructionTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v(e._s(e.parseTime(t.row.destructionTime,"{y}-{m}-{d} {h}:{i}:{s}")))])]}}],null,!1,1514640546)}):e._e(),e.columns[19].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Destruction Officer",align:"center",width:"255",prop:"destructionPeople"}}):e._e(),e.columns[20].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Create Time",align:"center",width:"255",prop:"createTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v(e._s(e.parseTime(t.row.createTime,"{y}-{m}-{d} {h}:{i}:{s}")))])]}}],null,!1,1080907512)}):e._e(),e.columns[21].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Create By",align:"center",width:"255",prop:"createBy"}}):e._e(),e.columns[22].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Update Time",align:"center",width:"255",prop:"updateTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v(e._s(e.parseTime(t.row.updateTime,"{y}-{m}-{d} {h}:{i}:{s}")))])]}}],null,!1,3807545165)}):e._e(),e.columns[23].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Update By",align:"center",width:"255",prop:"updateBy"}}):e._e(),e.columns[24].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Account Code",align:"center",width:"140",prop:"accountCode"}}):e._e(),e.columns[25].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Box Remark",align:"center",width:"400",prop:"boxRemark"}}):e._e(),e.columns[26].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Approval Files",align:"center",width:"355",prop:"approvalFiles"},scopedSlots:e._u([{key:"default",fn:function(t){return e._l(t.row.approvalFiles,(function(e){return l("ul",{key:Math.random(),staticClass:"horizontal-list-flex"},[l("FileLinkWithoutClose",{attrs:{file:e}})],1)}))}}],null,!1,2139656012)}):e._e(),e.columns[27].visible?l("el-table-column",{key:Math.random(),attrs:{label:"File Details",align:"center",width:"355",prop:"fileDetails"},scopedSlots:e._u([{key:"default",fn:function(t){return e._l(t.row.fileDetails,(function(e){return l("ul",{key:Math.random(),staticClass:"horizontal-list-flex"},[l("FileLinkWithoutClose",{attrs:{file:e}})],1)}))}}],null,!1,3694992666)}):e._e(),e.columns[28].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Action",align:"center",width:"140",prop:"action"}}):e._e(),e.columns[29].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Operator",align:"center",width:"255",prop:"operator"}}):e._e(),e.columns[30].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Operation Time",align:"center",width:"255",prop:"operationTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v(e._s(e.parseTime(t.row.operationTime,"{y}-{m}-{d} {h}:{i}:{s}")))])]}}],null,!1,23447433)}):e._e(),e.columns[31].visible?l("el-table-column",{key:Math.random(),attrs:{label:"Box ID",align:"center",width:"140",prop:"boxId"}}):e._e()],1):e._e(),l("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.searchByPaging}}),l("BoxProfileExportConfirm",{attrs:{open:e.showExportConfirm,multipleSelection:e.multipleSelection,exportAll:e.exportAll,total:e.total},on:{"update:open":function(t){e.showExportConfirm=t},"update:multipleSelection":function(t){e.multipleSelection=t},"update:multiple-selection":function(t){e.multipleSelection=t},"update:exportAll":function(t){e.exportAll=t},"update:export-all":function(t){e.exportAll=t},"update:total":function(t){e.total=t},closeExportConfirm:e.closeExportConfirm,confirmToExport:e.confirmToExport}})],1)},o=[],a=l("5530"),r=(l("a9e3"),l("d3b7"),l("159b"),l("b64b"),l("d81d"),l("4de4"),l("b311")),n=l.n(r),s=l("ed08"),c=l("c38a"),u=l("3ead"),p=l("8f24"),d=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("li",{on:{mouseover:e.onMouseOver,mouseleave:e.onMouseOut}},[l("a",{attrs:{target:"_self",href:e.fileDownloadURL}},[e._v(e._s(e.file.fileName))])])},m=[],h={name:"FileLinkWithoutClose",components:{},props:{file:{required:!0,type:Object}},data:function(){return{showCloseIcon:!1}},computed:{fileDownloadURL:{get:function(){return"https://cri-riqnfw19m2zc7zaf-registry.oss-cn-shanghai.aliyuncs.com/filingsystem/box/"+this.file.filePath},set:function(e){}}},mounted:function(){},methods:{onMouseOver:function(){this.showCloseIcon=!0},onMouseOut:function(){this.showCloseIcon=!1}}},b=h,f=(l("3c90"),l("2877")),y=Object(f["a"])(b,d,m,!1,null,"b682b0d8",null),w=y.exports,g={name:"Boxedithistory",components:{BoxProfileSearch:u["a"],BoxProfileExportConfirm:p["a"],FileLinkWithoutClose:w},data:function(){return{autocompleteEIC:"new-password",autocompletePIC:"new-password",showSearch:!0,columns:[{key:0,label:"Box No",visible:!0},{key:1,label:"ID",visible:!1},{key:2,label:"Dept ID",visible:!1},{key:3,label:"Raw Data",visible:!0},{key:4,label:"Box Department",visible:!0},{key:5,label:"Vendor Number",visible:!0},{key:6,label:"Vendor Barcode",visible:!0},{key:7,label:"Retention Date",visible:!0},{key:8,label:"Charge Code",visible:!0},{key:9,label:"Approver LPN",visible:!0},{key:10,label:"Approver Email",visible:!0},{key:11,label:"Requester LPN",visible:!0},{key:12,label:"Requester Email",visible:!0},{key:13,label:"Box Status",visible:!0},{key:14,label:"Location",visible:!0},{key:15,label:"Borrow State",visible:!0},{key:16,label:"Borrower LPN",visible:!0},{key:17,label:"Borrower Name",visible:!0},{key:18,label:"Destruction Time",visible:!0},{key:19,label:"Destruction Officer",visible:!0},{key:20,label:"Create Time?",visible:!0},{key:21,label:"Create By",visible:!0},{key:22,label:"Update Time",visible:!0},{key:23,label:"Update By",visible:!0},{key:24,label:"Account Code",visible:!0},{key:25,label:"Box Remark",visible:!0},{key:26,label:"Approval Files",visible:!0},{key:27,label:"File Details",visible:!0},{key:28,label:"Action",visible:!0},{key:29,label:"Operator",visible:!0},{key:30,label:"Operation Time",visible:!0},{key:31,label:"Box ID",visible:!1}],mode:"Insert",editRowIndex:null,clipboard:null,loading:!1,btnLoading:!1,ids:[],single:!0,multiple:!0,total:0,profileList:[],queryParams:{pageNum:1,pageSize:Number(this.$cache.local.get("displayPageRows"))||10},open:!1,dpn:["Y","N"],form:{engagementNumber:null,deptId:null,areaLocation:"",fileDescription:"",clientNumber:"",clientCompanyName:"",fileType:"",fileState:null,yearEndDate:null,auditReportDate:null,engagementEIC:"",engagementEICEmail:"",engagementPartner:"",engagementPartnerEmail:"",moveTo:"",fileremarks:"",clientGroupName:"",boxNumber:"",ccId:"",retentionPeriod:10,isDPN:"",staffId:"",requesterName:"",newFileState:"",approvalFiles:"",fileDetails:"",action:"",operator:"",operationTime:null},areaLocations:[],fileTypes:["A","T","I","O"],fileStates:["LOSE"],newFileID:"",showTable:!1,searchCriteria:null,exportCriteria:{},multipleSelection:[],activeRowIndex:null,confirmSelectedDialogVisible:!1,showExportConfirm:!1,exportAll:!1,showDestoryBox:!1,showPermOut:!1,showUploadApprovalFiles:!1,showUploadFileDetails:!1}},computed:{themeColor:{get:function(){return"#FFE600"==this.$store.state.settings.theme?"black":"white"}},themeLight:{get:function(){return Object(c["c"])(this.$store.state.settings.theme,.9)}},title:{get:function(){return"Insert"==this.mode?"New File":"Update File"},set:function(e){}},showAllButtons:{get:function(){return 1==this.showTable&&this.profileList.length>0},set:function(e){}}},activated:function(){this.$route.query.boxNumber?this.$refs.boxSearch.activeThenQuery(this.$route.query.boxNumber):this.$refs.boxSearch.isFormEmpty()||this.$refs.boxSearch.handleQueryByPageNum(this.queryParams.pageNum,this.queryParams.pageSize)},mounted:function(){var e=this;this.clipboard=new n.a("#copyNode",{text:function(t){return e.$notify.currentNotice&&e.$notify.currentNotice.close(),e.$notify({title:"Copied!",message:"",type:"success"}),e.newFileID}}),this.clipboard.on("error",(function(t){e.$message.error("FileID复制失败")}))},destroyed:function(){var e;null===(e=this.clipboard)||void 0===e||e.destroy()},methods:{receiveSearchResult:function(e,t){this.showTable=!0,this.profileList=e.rows,this.searchCriteria=t,this.total=e.total,this.profileList.forEach((function(e,t){var l=JSON.parse(e.filesJson);e.approvalFiles=l.filter((function(e){return 1===e.usage})).map((function(e){return{fileName:e.file_name,filePath:e.file_path}})),e.fileDetails=l.filter((function(e){return 2===e.usage})).map((function(e){return{fileName:e.file_name,filePath:e.file_path}}))}))},resetPageInfo:function(){var e;this.queryParams.pageNum=1,this.queryParams.pageSize=Number(this.$cache.local.get("displayPageRows"))||10,null===(e=this.$refs.multipleTable)||void 0===e||e.clearSelection()},handleSelectionChange:function(e){this.multipleSelection=e},searchByPaging:function(){null!=this.searchCriteria&&(this.loading=!0,this.$refs.boxSearch.handleQueryByPageNum(this.queryParams.pageNum,this.queryParams.pageSize))},refreshTableRow:function(e){this.$set(this.profileList,this.editRowIndex,e)},columnStyle:function(e,t,l,i){return"color: black;padding:1px 0px;"},headerColStyle:function(e,t,l,i){return"padding:0px 0px;height:10px"},handleRowClick:function(e,t,l){this.activeRowIndex=e.id+"-"+e.deptId+"-"+e.boxId},rowStyle:function(e){var t=e.row;e.rowIndex;return this.activeRowIndex===t.id+"-"+t.deptId+"-"+t.boxId?{backgroundColor:this.themeLight}:{}},whenChangeDeptId:function(e){this.exportCriteria.deptId=e},exportSearchData:function(e){if("Export Selected"===e){if(0==this.multipleSelection.length)return void this.$modal.alert("Please select check box!");if(this.multipleSelection.length>1e4)return void this.$modal.alert("The selected ID count cannot exceed 10,000");this.exportAll=!1}else this.exportAll=!0;this.showExportConfirm=!0},closeExportConfirm:function(){this.showExportConfirm=!1},confirmToExport:function(e){e?(this.exportCriteria=this.searchCriteria,this.exportCriteria.lstId=new Array,this.exportCriteria.lstDeptId=new Array,this.download("filingsystem/filing/boxprofile/exportBoxEditHistory",Object(a["a"])({},this.exportCriteria),"".concat(Object(s["d"])(new Date)," Box Edit History.xlsx"))):(this.exportCriteria.lstId=this.multipleSelection.map((function(e){return e.id})),this.exportCriteria.lstDeptId=this.multipleSelection.map((function(e){return e.deptId})),this.download("filingsystem/filing/boxprofile/exportBoxEditHistory",Object(a["a"])({},this.exportCriteria),"".concat(Object(s["d"])(new Date)," Box Edit History.xlsx"))),this.showExportConfirm=!1}}},v=g,x=(l("05df"),Object(f["a"])(v,i,o,!1,null,"b6b73e04",null));t["default"]=x.exports}}]);