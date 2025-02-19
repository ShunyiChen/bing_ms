import{A as S,_ as X,r as v,I as Z,M as ee,h as c,O as le,o as m,c as k,d as e,e as s,k as p,n as U,x as Y,f as o,$ as ae,i as ue,N as te,j as y,t as oe,G as re,m as ie,F as M,C as $,l as me,z as ce}from"./index-CAgRbO-3.js";function Be(i){return S({url:"/legaldatabase/dpnrequest/list",method:"post",data:i})}function pe(i){return S({url:"/legaldatabase/dpnrequest/"+i,method:"get"})}function Ie(i){return S({url:"/legaldatabase/dpnrequest",method:"post",data:i})}function fe(i){return S({url:"/legaldatabase/dpnrequest/update",method:"post",data:i})}function qe(i){return S({url:"/legaldatabase/dpnrequest/bulkApproval",method:"post",data:i})}function Fe(i){return S({url:"/legaldatabase/dpnrequest/fetchEmployeeInfoByLPN",method:"post",data:i})}function ge(i){return S({url:"/legaldatabase/dpnrequest/getEngagementByEID",method:"post",data:i})}const ve={style:{display:"flex","flex-direction":"column"}},be={class:"dialog-footer"},ye={style:{display:"flex","justify-content":"space-between"}},_e={__name:"index",emits:["getSelectedUser"],setup(i,{expose:A,emit:T}){const q=v("first"),{proxy:F}=oe(),V=v(!1),w=v(!1),L=v(""),_=v([]),R=T,E=Z({form:{},rules:{}}),{form:b,rules:x}=ee(E),j=()=>{b.value.keyword&&(w.value=!0,_.value=[],ge({eid:b.value.keyword}).then(u=>{u.data.response.length>0&&u.data.response.map(f=>{const{eid:h,c_LNM:D}=f,N={eid:h,engagementName:D};_.value.push(N)}),w.value=!1}).catch(u=>{w.value=!1}))};function O(u,f,h){B(u)}const B=u=>{R("getSelectedEngagement",u),V.value=!1};function P(){V.value=!1,I()}function I(){b.value={keyword:null},F.resetForm("formRef")}function H(){I(),V.value=!0,L.value="Select Engagement",G()}const l=(u,f)=>{console.log(u,f)},G=()=>{setTimeout(()=>{var u;(u=document.getElementById("focusField"))==null||u.focus()},100)};return A({handleOpen:H}),(u,f)=>{const h=c("el-input"),D=c("el-button"),N=c("el-table-column"),K=c("el-table"),W=c("el-tab-pane"),z=c("el-tabs"),r=c("el-text"),t=c("el-dialog"),d=le("loading");return m(),k("div",null,[e(t,{modal:!1,title:L.value,modelValue:V.value,"onUpdate:modelValue":f[2]||(f[2]=n=>V.value=n),width:"45%","close-on-click-modal":!1,"close-on-press-escape":!1,draggable:"",style:{border:"1px solid var(--el-color-primary)"}},{footer:s(()=>[p("div",be,[p("div",ye,[e(r,null,{default:s(()=>[U(Y(u.$t("message.SelectEngagement.6",{num:_.value.length})),1)]),_:1}),e(D,{onClick:P},{default:s(()=>[U(Y(u.$t("message.SelectEngagement.5")),1)]),_:1})])])]),default:s(()=>[p("div",ve,[p("div",null,[e(h,{id:"focusField",modelValue:o(b).keyword,"onUpdate:modelValue":f[0]||(f[0]=n=>o(b).keyword=n),maxlength:"30",style:{width:"260px"},placeholder:u.$t("message.SelectEngagement.1"),"prefix-icon":o(ae),clearable:"",onKeyup:ue(j,["enter"])},null,8,["modelValue","placeholder","prefix-icon"]),e(D,{type:"primary",onClick:j},{default:s(()=>[U(Y(u.$t("message.SelectEngagement.2")),1)]),_:1})]),e(z,{modelValue:q.value,"onUpdate:modelValue":f[1]||(f[1]=n=>q.value=n),onTabClick:l},{default:s(()=>[e(W,{label:"Engagements",name:"first"},{default:s(()=>[te((m(),y(K,{data:_.value,onRowDblclick:O,style:{width:"100%",height:"400px"}},{default:s(()=>[e(N,{prop:"eid",label:"EID",width:"200"}),e(N,{prop:"engagementName",label:"Engagement Name",width:"400"}),e(N,{fixed:"right",label:u.$t("message.SelectEngagement.3"),"min-width":"100"},{default:s(n=>[e(D,{link:"",type:"primary",onClick:g=>B(n.row)},{default:s(()=>[U(Y(u.$t("message.SelectEngagement.4")),1)]),_:2},1032,["onClick"])]),_:1},8,["label"])]),_:1},8,["data"])),[[d,w.value]])]),_:1})]),_:1},8,["modelValue"])])]),_:1},8,["title","modelValue"])])}}},Ve=X(_e,[["__scopeId","data-v-4c71a2e5"]]),Re={class:"form-row"},he={class:"form-row"},De={class:"form-row"},Ne={class:"form-row"},ke={key:0,class:"form-row"},Se={class:"form-row"},we={class:"form-row"},Ee={class:"form-row"},Ce={class:"form-row"},Ue={class:"form-row"},Ye={class:"dialog-footer"},xe={__name:"index",props:{getDicts:{type:Function,required:!0}},emits:["update:loading","refreshData","updateUnreadCount"],setup(i,{expose:A,emit:T}){const q=i,{proxy:F}=oe(),{legaldb_device_type:V,legaldb_remark:w,legaldb_office:L}=q.getDicts(),_=v(null),R=v(!1),E=v(!1),b=v(!1),x=v(new Array),j=v(null),O=re(),B=v(""),P=v(null),I=T,H=Z({form:{},rules:{status:[{required:!0,message:"Status cannot be empty.",trigger:["change","blur"]}],rejectReason:[{required:!0,message:"RejectReason cannot be empty.",trigger:["change","blur"]}],cancelReason:[{required:!0,message:"CancelReason cannot be empty.",trigger:["change","blur"]}],fidsReceivedBy:[{required:!0,message:"FidsReceivedBy cannot be empty.",trigger:["change","blur"]},{type:"email",message:"Please input correct email address",trigger:["blur","change"]}],fidsReceivedDate:[{required:!0,message:"FidsReceivedDate cannot be empty.",trigger:["change","blur"]}]}}),{form:l,rules:G}=ee(H);function u(r){l.value.engagementName=r.engagementName,l.value.engagementCode=r.eid}function f(){R.value=!1,h()}function h(){l.value={id:null,staffNo:null,lpn:null,staffName:null,email:null,office:null,serviceLine:null,rankName:null,createdBy:null,created:null,status:null,rejectReason:null,returnedDate:null,deviceType:null,eyAssetNo:null,serialNo:null,remark:null,receivedBy:null,engagementCode:null,engagementName:null,cancelReason:null,fidsReceivedBy:null,fidsReceivedDate:null,deviceLocation:null,approved:null,rejected:null,approver:null,rejecter:null},F.resetForm("formRef")}function D(){j.value.handleOpen()}function N(r){h(),R.value=!0,B.value=ce.global.t("message.EditDPNRequest.1"),E.value=!0,pe(r).then(t=>{l.value=t.data,P.value=t.data.status,E.value=!1,W()}).catch(t=>{E.value=!1})}function K(r){return O.roles[0]===r}function W(){x.value=[];const r=l.value.status,t={OSTS:{Approved:["Formatted","Cancelled by IT"],Rejected:["Cancelled by IT"],"Waiting for Confirmation":["Cancelled by IT"],"Pending Legal Review":["Cancelled by IT"]},Legal:{Approved:["Rejected"],Rejected:["Approved"],"Waiting for Confirmation":["Approved","Rejected"],"Pending Legal Review":["Approved","Rejected"]},FIDS:{Rejected:["FIDS Received"]}};for(const[d,n]of Object.entries(t))if(K(d)&&n[r]){x.value.push(...n[r].map(g=>({value:g}))),l.value.status=null;return}x.value.push({value:r})}function z(){const r=_.value.fields;let t=[];r.forEach(d=>{const n=new Promise(g=>{_.value.validateField(d.prop,C=>{g(C)})});t.push(n)}),Promise.all(t).then(d=>{d.every(g=>g)?(b.value=!0,l.value.newStatus=l.value.status,l.value.status=P.value,fe(l.value).then(g=>{F.$modal.msgSuccess("Updated successfully"),R.value=!1,b.value=!1,I("updateUnreadCount",l.value.newStatus),I("refreshData")}).catch(g=>{b.value=!1})):console.log("表单验证失败")})}return A({handleEdit:N}),(r,t)=>{const d=c("el-input"),n=c("el-form-item"),g=c("el-option"),C=c("el-select"),Q=c("el-date-picker"),J=c("el-button"),ne=c("el-form"),se=c("el-dialog"),de=le("loading");return m(),k("div",null,[e(se,{modal:!1,title:B.value,modelValue:R.value,"onUpdate:modelValue":t[23]||(t[23]=a=>R.value=a),width:"52%","close-on-click-modal":!1,draggable:"",overflow:"",style:{border:"1px solid var(--el-color-primary)"}},{footer:s(()=>[p("div",Ye,[e(J,{type:"primary",onClick:z,loading:b.value},{default:s(()=>[U(Y(r.$t("message.EditDPNRequest.2")),1)]),_:1},8,["loading"]),e(J,{onClick:f},{default:s(()=>[U(Y(r.$t("message.EditDPNRequest.3")),1)]),_:1})])]),default:s(()=>[te((m(),y(ne,{ref_key:"formRef",ref:_,onSubmit:t[22]||(t[22]=ie(()=>{},["prevent"])),"element-loading-text":"Verifying...",model:o(l),"label-suffix":":",rules:o(G),"label-width":"auto","label-position":"right"},{default:s(()=>[p("div",Re,[e(n,{label:"LPN",prop:"lpn",class:"left-column"},{default:s(()=>[e(d,{id:"lpnField",modelValue:o(l).lpn,"onUpdate:modelValue":t[0]||(t[0]=a=>o(l).lpn=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),e(n,{label:"Staff No. (GPN)",prop:"staffNo",class:"right-column"},{default:s(()=>[e(d,{modelValue:o(l).staffNo,"onUpdate:modelValue":t[1]||(t[1]=a=>o(l).staffNo=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1})]),p("div",he,[e(n,{label:"Staff Name",prop:"staffName",class:"left-column"},{default:s(()=>[e(d,{modelValue:o(l).staffName,"onUpdate:modelValue":t[2]||(t[2]=a=>o(l).staffName=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),e(n,{label:"Email",prop:"email",class:"right-column"},{default:s(()=>[e(d,{modelValue:o(l).email,"onUpdate:modelValue":t[3]||(t[3]=a=>o(l).email=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1})]),p("div",De,[e(n,{label:"Office",prop:"office",class:"left-column"},{default:s(()=>[e(d,{modelValue:o(l).office,"onUpdate:modelValue":t[4]||(t[4]=a=>o(l).office=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),e(n,{label:"Dept/Service Line",prop:"serviceLine",class:"right-column"},{default:s(()=>[e(d,{modelValue:o(l).serviceLine,"onUpdate:modelValue":t[5]||(t[5]=a=>o(l).serviceLine=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1})]),p("div",Ne,[e(n,{label:"EY Rank",prop:"rankName",class:"left-column"},{default:s(()=>[e(d,{modelValue:o(l).rankName,"onUpdate:modelValue":t[6]||(t[6]=a=>o(l).rankName=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),e(n,{label:"Status",prop:"status",class:"right-column"},{default:s(()=>[e(C,{modelValue:o(l).status,"onUpdate:modelValue":t[7]||(t[7]=a=>o(l).status=a),placeholder:"Select",clearable:""},{default:s(()=>[(m(!0),k(M,null,$(x.value,a=>(m(),y(g,{key:a.value,label:a.value,value:a.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1})]),o(l).status=="FIDS Received"?(m(),k("div",ke,[e(n,{label:"FIDS Received By",prop:"fidsReceivedBy",class:"left-column"},{default:s(()=>[e(d,{modelValue:o(l).fidsReceivedBy,"onUpdate:modelValue":t[8]||(t[8]=a=>o(l).fidsReceivedBy=a),placeholder:""},null,8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),e(n,{label:"FIDS Received Date",prop:"fidsReceivedDate",class:"right-column"},{default:s(()=>[e(Q,{clearable:"",modelValue:o(l).fidsReceivedDate,"onUpdate:modelValue":t[9]||(t[9]=a=>o(l).fidsReceivedDate=a),type:"datetime",format:"YYYY-MM-DD HH:mm:ss","value-format":"YYYY-MM-DD HH:mm:ss","date-format":"YYYY-MM-DD","time-format":"hh:mm:ss",placeholder:"",style:{width:"100%"}},null,8,["modelValue"])]),_:1})])):me("",!0),p("div",Se,[e(n,{label:"Date Returned to IT",prop:"returnedDate",class:"left-column"},{default:s(()=>[e(Q,{clearable:"",modelValue:o(l).returnedDate,"onUpdate:modelValue":t[10]||(t[10]=a=>o(l).returnedDate=a),type:"datetime",disabled:!0,format:"YYYY-MM-DD HH:mm:ss","value-format":"YYYY-MM-DD HH:mm:ss","date-format":"YYYY-MM-DD","time-format":"hh:mm:ss",placeholder:"",style:{width:"100%"}},null,8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),e(n,{label:"Device Type",prop:"deviceType",class:"right-column"},{default:s(()=>[e(C,{modelValue:o(l).deviceType,"onUpdate:modelValue":t[11]||(t[11]=a=>o(l).deviceType=a),placeholder:"Select",clearable:"",disabled:!0},{default:s(()=>[(m(!0),k(M,null,$(o(V),a=>(m(),y(g,{key:a.value,label:a.label,value:a.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1})]),p("div",we,[e(n,{label:"Device Location",prop:"deviceLocation",class:"left-column"},{default:s(()=>[e(C,{modelValue:o(l).deviceLocation,"onUpdate:modelValue":t[12]||(t[12]=a=>o(l).deviceLocation=a),placeholder:"Select",clearable:"",disabled:!0},{default:s(()=>[(m(!0),k(M,null,$(o(L),a=>(m(),y(g,{key:a.value,label:a.label,value:a.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),e(n,{label:"EYAsset No",prop:"eyAssetNo",class:"right-column"},{default:s(()=>[e(d,{modelValue:o(l).eyAssetNo,"onUpdate:modelValue":t[13]||(t[13]=a=>o(l).eyAssetNo=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1})]),p("div",Ee,[e(n,{label:"Serial No. (For OSS)",prop:"serialNo",class:"left-column"},{default:s(()=>[e(d,{modelValue:o(l).serialNo,"onUpdate:modelValue":t[14]||(t[14]=a=>o(l).serialNo=a),disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),e(n,{label:"Remark",prop:"remark",class:"right-column"},{default:s(()=>[e(C,{modelValue:o(l).remark,"onUpdate:modelValue":t[15]||(t[15]=a=>o(l).remark=a),disabled:!0,placeholder:"Select",clearable:""},{default:s(()=>[(m(!0),k(M,null,$(o(w),a=>(m(),y(g,{key:a.value,label:a.label,value:a.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1})]),p("div",Ce,[e(n,{label:"Engagement Code",prop:"engagementCode",class:"left-column"},{default:s(()=>[e(d,{modelValue:o(l).engagementCode,"onUpdate:modelValue":t[16]||(t[16]=a=>o(l).engagementCode=a),maxlength:"8",disabled:o(l).status!="Rejected",placeholder:""},null,8,["modelValue","disabled"])]),_:1}),e(n,{class:"center-column"},{default:s(()=>[e(J,{icon:o(ae),circle:"",onClick:t[17]||(t[17]=a=>D(2)),disabled:o(l).status!="Rejected"},null,8,["icon","disabled"])]),_:1}),e(n,{label:"Engagement Name",prop:"engagementName",class:"right-column"},{default:s(()=>[e(d,{modelValue:o(l).engagementName,"onUpdate:modelValue":t[18]||(t[18]=a=>o(l).engagementName=a),disabled:o(l).status!="Rejected",placeholder:"",type:"textarea",rows:1},null,8,["modelValue","disabled"])]),_:1})]),p("div",Ue,[e(n,{label:"Received By",prop:"receivedBy",class:"left-column"},{default:s(()=>[e(d,{modelValue:o(l).receivedBy,"onUpdate:modelValue":t[19]||(t[19]=a=>o(l).receivedBy=a),modelModifiers:{trim:!0},disabled:!0,placeholder:""},null,8,["modelValue"])]),_:1}),e(n,{class:"center-column"}),o(l).status=="Rejected"?(m(),y(n,{key:0,label:"Reject Reason",prop:"rejectReason",class:"right-column"},{default:s(()=>[e(d,{modelValue:o(l).rejectReason,"onUpdate:modelValue":t[20]||(t[20]=a=>o(l).rejectReason=a),placeholder:"",type:"textarea",rows:2},null,8,["modelValue"])]),_:1})):o(l).status=="Cancelled by IT"?(m(),y(n,{key:1,label:"Cancel Reason",prop:"cancelReason",class:"right-column"},{default:s(()=>[e(d,{modelValue:o(l).cancelReason,"onUpdate:modelValue":t[21]||(t[21]=a=>o(l).cancelReason=a),placeholder:"",type:"textarea",rows:2},null,8,["modelValue"])]),_:1})):(m(),y(n,{key:2,class:"right-column"}))])]),_:1},8,["model","rules"])),[[de,E.value]])]),_:1},8,["title","modelValue"]),e(o(Ve),{ref_key:"selectEngagementRef",ref:j,onGetSelectedEngagement:u},null,512)])}}},Le=X(xe,[["__scopeId","data-v-d8f5398a"]]);export{Le as E,Ie as a,qe as b,Fe as f,pe as g,Be as l};
