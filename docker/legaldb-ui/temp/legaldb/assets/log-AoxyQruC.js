import{A as R,r as b,a as ie,I as pe,M as ce,h as s,O as z,o as i,c as k,N as w,R as F,f as o,d as e,e as l,i as me,F as O,C as Q,j as g,D as T,n as r,k as E,x as v,l as K,H as _e,t as fe}from"./index-CAgRbO-3.js";import{g as be}from"./job-DLJJD8IR.js";function ge(V){return R({url:"/schedule/job/log/list",method:"get",params:V})}function ve(V){return R({url:"/schedule/job/log/"+V,method:"delete"})}function he(){return R({url:"/schedule/job/log/clean",method:"delete"})}const we={class:"app-container"},je={key:0},ye={key:1},ke={class:"dialog-footer"},Ve=_e({name:"JobLog"}),De=Object.assign(Ve,{setup(V){const{proxy:m}=fe(),{sys_common_status:G,sys_job_group:I}=m.useDict("sys_common_status","sys_job_group"),U=b([]),j=b(!1),N=b(!0),C=b(!0),S=b([]),$=b(!0),D=b(0),y=b([]),P=ie(),A=pe({form:{},queryParams:{pageNum:1,pageSize:10,dictName:void 0,dictType:void 0,status:void 0}}),{queryParams:n,form:p,rules:Ce}=ce(A);function h(){N.value=!0,ge(m.addDateRange(n.value,y.value)).then(u=>{U.value=u.rows,D.value=u.total,N.value=!1})}function H(){const u={path:"/monitor/job"};m.$tab.closeOpenPage(u)}function L(){n.value.pageNum=1,h()}function W(){y.value=[],m.resetForm("queryRef"),L()}function X(u){S.value=u.map(a=>a.jobLogId),$.value=!u.length}function Z(u){j.value=!0,p.value=u}function ee(u){m.$modal.confirm('是否确认删除调度日志编号为"'+S.value+'"的数据项?').then(function(){return ve(S.value)}).then(()=>{h(),m.$modal.msgSuccess("删除成功")}).catch(()=>{})}function le(){m.$modal.confirm("是否确认清空所有调度日志数据项?").then(function(){return he()}).then(()=>{h(),m.$modal.msgSuccess("清空成功")}).catch(()=>{})}function oe(){m.download("monitor/jobLog/export",{...n.value},`job_log_${new Date().getTime()}.xlsx`)}return(()=>{const u=P.params&&P.params.jobId;u!==void 0&&u!=0?be(u).then(a=>{n.value.jobName=a.data.jobName,n.value.jobGroup=a.data.jobGroup,h()}):h()})(),(u,a)=>{const te=s("el-input"),d=s("el-form-item"),q=s("el-option"),J=s("el-select"),ae=s("el-date-picker"),f=s("el-button"),M=s("el-form"),c=s("el-col"),ne=s("right-toolbar"),B=s("el-row"),_=s("el-table-column"),Y=s("dict-tag"),ue=s("el-table"),se=s("pagination"),re=s("el-dialog"),x=z("hasPermi"),de=z("loading");return i(),k("div",we,[w(e(M,{model:o(n),ref:"queryRef",inline:!0,"label-width":"68px"},{default:l(()=>[e(d,{label:"任务名称",prop:"jobName"},{default:l(()=>[e(te,{modelValue:o(n).jobName,"onUpdate:modelValue":a[0]||(a[0]=t=>o(n).jobName=t),placeholder:"请输入任务名称",clearable:"",style:{width:"240px"},onKeyup:me(L,["enter"])},null,8,["modelValue"])]),_:1}),e(d,{label:"任务组名",prop:"jobGroup"},{default:l(()=>[e(J,{modelValue:o(n).jobGroup,"onUpdate:modelValue":a[1]||(a[1]=t=>o(n).jobGroup=t),placeholder:"请选择任务组名",clearable:"",style:{width:"240px"}},{default:l(()=>[(i(!0),k(O,null,Q(o(I),t=>(i(),g(q,{key:t.value,label:t.label,value:t.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1}),e(d,{label:"执行状态",prop:"status"},{default:l(()=>[e(J,{modelValue:o(n).status,"onUpdate:modelValue":a[2]||(a[2]=t=>o(n).status=t),placeholder:"请选择执行状态",clearable:"",style:{width:"240px"}},{default:l(()=>[(i(!0),k(O,null,Q(o(G),t=>(i(),g(q,{key:t.value,label:t.label,value:t.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1}),e(d,{label:"执行时间",style:{width:"308px"}},{default:l(()=>[e(ae,{modelValue:o(y),"onUpdate:modelValue":a[3]||(a[3]=t=>T(y)?y.value=t:null),"value-format":"YYYY-MM-DD",type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},null,8,["modelValue"])]),_:1}),e(d,null,{default:l(()=>[e(f,{type:"primary",icon:"Search",onClick:L},{default:l(()=>[r("搜索")]),_:1}),e(f,{icon:"Refresh",onClick:W},{default:l(()=>[r("重置")]),_:1})]),_:1})]),_:1},8,["model"]),[[F,o(C)]]),e(B,{gutter:10,class:"mb8"},{default:l(()=>[e(c,{span:1.5},{default:l(()=>[w((i(),g(f,{type:"danger",plain:"",icon:"Delete",disabled:o($),onClick:ee},{default:l(()=>[r("删除")]),_:1},8,["disabled"])),[[x,["monitor:job:remove"]]])]),_:1}),e(c,{span:1.5},{default:l(()=>[w((i(),g(f,{type:"danger",plain:"",icon:"Delete",onClick:le},{default:l(()=>[r("清空")]),_:1})),[[x,["monitor:job:remove"]]])]),_:1}),e(c,{span:1.5},{default:l(()=>[w((i(),g(f,{type:"warning",plain:"",icon:"Download",onClick:oe},{default:l(()=>[r("导出")]),_:1})),[[x,["monitor:job:export"]]])]),_:1}),e(c,{span:1.5},{default:l(()=>[e(f,{type:"warning",plain:"",icon:"Close",onClick:H},{default:l(()=>[r("关闭")]),_:1})]),_:1}),e(ne,{showSearch:o(C),"onUpdate:showSearch":a[4]||(a[4]=t=>T(C)?C.value=t:null),onQueryTable:h},null,8,["showSearch"])]),_:1}),w((i(),g(ue,{data:o(U),onSelectionChange:X},{default:l(()=>[e(_,{type:"selection",width:"55",align:"center"}),e(_,{label:"日志编号",width:"80",align:"center",prop:"jobLogId"}),e(_,{label:"任务名称",align:"center",prop:"jobName","show-overflow-tooltip":!0}),e(_,{label:"任务组名",align:"center",prop:"jobGroup","show-overflow-tooltip":!0},{default:l(t=>[e(Y,{options:o(I),value:t.row.jobGroup},null,8,["options","value"])]),_:1}),e(_,{label:"调用目标字符串",align:"center",prop:"invokeTarget","show-overflow-tooltip":!0}),e(_,{label:"日志信息",align:"center",prop:"jobMessage","show-overflow-tooltip":!0}),e(_,{label:"执行状态",align:"center",prop:"status"},{default:l(t=>[e(Y,{options:o(G),value:t.row.status},null,8,["options","value"])]),_:1}),e(_,{label:"执行时间",align:"center",prop:"createTime",width:"180"},{default:l(t=>[E("span",null,v(u.parseTime(t.row.createTime)),1)]),_:1}),e(_,{label:"操作",align:"center","class-name":"small-padding fixed-width"},{default:l(t=>[w((i(),g(f,{link:"",type:"primary",icon:"View",onClick:xe=>Z(t.row)},{default:l(()=>[r("详细")]),_:2},1032,["onClick"])),[[x,["monitor:job:query"]]])]),_:1})]),_:1},8,["data"])),[[de,o(N)]]),w(e(se,{total:o(D),page:o(n).pageNum,"onUpdate:page":a[5]||(a[5]=t=>o(n).pageNum=t),limit:o(n).pageSize,"onUpdate:limit":a[6]||(a[6]=t=>o(n).pageSize=t),onPagination:h},null,8,["total","page","limit"]),[[F,o(D)>0]]),e(re,{title:"调度日志详细",modelValue:o(j),"onUpdate:modelValue":a[8]||(a[8]=t=>T(j)?j.value=t:null),width:"700px","append-to-body":""},{footer:l(()=>[E("div",ke,[e(f,{onClick:a[7]||(a[7]=t=>j.value=!1)},{default:l(()=>[r("关 闭")]),_:1})])]),default:l(()=>[e(M,{model:o(p),"label-width":"100px"},{default:l(()=>[e(B,null,{default:l(()=>[e(c,{span:12},{default:l(()=>[e(d,{label:"日志序号："},{default:l(()=>[r(v(o(p).jobLogId),1)]),_:1}),e(d,{label:"任务名称："},{default:l(()=>[r(v(o(p).jobName),1)]),_:1})]),_:1}),e(c,{span:12},{default:l(()=>[e(d,{label:"任务分组："},{default:l(()=>[r(v(o(p).jobGroup),1)]),_:1}),e(d,{label:"执行时间："},{default:l(()=>[r(v(o(p).createTime),1)]),_:1})]),_:1}),e(c,{span:24},{default:l(()=>[e(d,{label:"调用方法："},{default:l(()=>[r(v(o(p).invokeTarget),1)]),_:1})]),_:1}),e(c,{span:24},{default:l(()=>[e(d,{label:"日志信息："},{default:l(()=>[r(v(o(p).jobMessage),1)]),_:1})]),_:1}),e(c,{span:24},{default:l(()=>[e(d,{label:"执行状态："},{default:l(()=>[o(p).status==0?(i(),k("div",je,"正常")):o(p).status==1?(i(),k("div",ye,"失败")):K("",!0)]),_:1})]),_:1}),e(c,{span:24},{default:l(()=>[o(p).status==1?(i(),g(d,{key:0,label:"异常信息："},{default:l(()=>[r(v(o(p).exceptionInfo),1)]),_:1})):K("",!0)]),_:1})]),_:1})]),_:1},8,["model"])]),_:1},8,["modelValue"])])}}});export{De as default};
