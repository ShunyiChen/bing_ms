import{r as T,b as R,w as x,h as u,o as f,j as N,e as l,d as e,n as o,f as w,l as U,c as y,F as v,N as $,C as _,R as G,k as I,t as H}from"./index-CAgRbO-3.js";import{l as J}from"./menu-CXv3m4-0.js";const K=I("i",{class:"el-icon-arrow-down el-icon--right"},null,-1),Q=I("h4",{class:"form-header"},"其他信息",-1),X=I("h4",{class:"form-header"},"关联信息",-1),ee={__name:"genInfoForm",props:{info:{type:Object,default:null},tables:{type:Array,default:null}},setup(n){const F=T([]),q=T([]),{proxy:W}=H(),c=n,M=T({tplCategory:[{required:!0,message:"请选择生成模板",trigger:"blur"}],packageName:[{required:!0,message:"请输入生成包路径",trigger:"blur"}],moduleName:[{required:!0,message:"请输入生成模块名",trigger:"blur"}],businessName:[{required:!0,message:"请输入生成业务名",trigger:"blur"}],functionName:[{required:!0,message:"请输入生成功能名",trigger:"blur"}]});function B(b){c.info.subTableFkName=""}function S(b){b!=="sub"&&(c.info.subTableName="",c.info.subTableFkName="")}function j(b){for(var a in c.tables){const r=c.tables[a].tableName;if(b===r){F.value=c.tables[a].columns;break}}}function E(){J().then(b=>{q.value=W.handleTree(b.data,"menuId")})}return R(()=>{E()}),x(()=>c.info.subTableName,b=>{j(b)}),x(()=>c.info.tplWebType,b=>{b===""&&(c.info.tplWebType="element-plus")}),(b,a)=>{const r=u("el-option"),g=u("el-select"),d=u("el-form-item"),m=u("el-col"),i=u("question-filled"),s=u("el-icon"),p=u("el-tooltip"),C=u("el-input"),P=u("el-radio"),h=u("el-tree-select"),z=u("el-button"),O=u("el-dropdown-item"),A=u("el-dropdown-menu"),D=u("el-dropdown"),k=u("el-row"),L=u("el-form");return f(),N(L,{ref:"genInfoForm",model:n.info,rules:w(M),"label-width":"150px"},{default:l(()=>[e(k,null,{default:l(()=>[e(m,{span:12},{default:l(()=>[e(d,{prop:"tplCategory"},{label:l(()=>[o("生成模板")]),default:l(()=>[e(g,{modelValue:n.info.tplCategory,"onUpdate:modelValue":a[0]||(a[0]=t=>n.info.tplCategory=t),onChange:S},{default:l(()=>[e(r,{label:"单表（增删改查）",value:"crud"}),e(r,{label:"树表（增删改查）",value:"tree"}),e(r,{label:"主子表（增删改查）",value:"sub"})]),_:1},8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,{prop:"tplWebType"},{label:l(()=>[o("前端类型")]),default:l(()=>[e(g,{modelValue:n.info.tplWebType,"onUpdate:modelValue":a[1]||(a[1]=t=>n.info.tplWebType=t)},{default:l(()=>[e(r,{label:"Vue2 Element UI 模版",value:"element-ui"}),e(r,{label:"Vue3 Element Plus 模版",value:"element-plus"})]),_:1},8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,{prop:"packageName"},{label:l(()=>[o(" 生成包路径 "),e(p,{content:"生成在哪个java包下，例如 com.ruoyi.system",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(C,{modelValue:n.info.packageName,"onUpdate:modelValue":a[2]||(a[2]=t=>n.info.packageName=t)},null,8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,{prop:"moduleName"},{label:l(()=>[o(" 生成模块名 "),e(p,{content:"可理解为子系统名，例如 system",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(C,{modelValue:n.info.moduleName,"onUpdate:modelValue":a[3]||(a[3]=t=>n.info.moduleName=t)},null,8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,{prop:"businessName"},{label:l(()=>[o(" 生成业务名 "),e(p,{content:"可理解为功能英文名，例如 user",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(C,{modelValue:n.info.businessName,"onUpdate:modelValue":a[4]||(a[4]=t=>n.info.businessName=t)},null,8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,{prop:"functionName"},{label:l(()=>[o(" 生成功能名 "),e(p,{content:"用作类描述，例如 用户",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(C,{modelValue:n.info.functionName,"onUpdate:modelValue":a[5]||(a[5]=t=>n.info.functionName=t)},null,8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,{prop:"genType"},{label:l(()=>[o(" 生成代码方式 "),e(p,{content:"默认为zip压缩包下载，也可以自定义生成路径",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(P,{modelValue:n.info.genType,"onUpdate:modelValue":a[6]||(a[6]=t=>n.info.genType=t),value:"0"},{default:l(()=>[o("zip压缩包")]),_:1},8,["modelValue"]),e(P,{modelValue:n.info.genType,"onUpdate:modelValue":a[7]||(a[7]=t=>n.info.genType=t),value:"1"},{default:l(()=>[o("自定义路径")]),_:1},8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,null,{label:l(()=>[o(" 上级菜单 "),e(p,{content:"分配到指定菜单下，例如 系统管理",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(h,{modelValue:n.info.parentMenuId,"onUpdate:modelValue":a[8]||(a[8]=t=>n.info.parentMenuId=t),data:w(q),props:{value:"menuId",label:"menuName",children:"children"},"value-key":"menuId",placeholder:"请选择系统菜单","check-strictly":""},null,8,["modelValue","data"])]),_:1})]),_:1}),n.info.genType=="1"?(f(),N(m,{key:0,span:24},{default:l(()=>[e(d,{prop:"genPath"},{label:l(()=>[o(" 自定义路径 "),e(p,{content:"填写磁盘绝对路径，若不填写，则生成到当前Web项目下",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(C,{modelValue:n.info.genPath,"onUpdate:modelValue":a[10]||(a[10]=t=>n.info.genPath=t)},{append:l(()=>[e(D,null,{dropdown:l(()=>[e(A,null,{default:l(()=>[e(O,{onClick:a[9]||(a[9]=t=>n.info.genPath="/")},{default:l(()=>[o("恢复默认的生成基础路径")]),_:1})]),_:1})]),default:l(()=>[e(z,{type:"primary"},{default:l(()=>[o(" 最近路径快速选择 "),K]),_:1})]),_:1})]),_:1},8,["modelValue"])]),_:1})]),_:1})):U("",!0)]),_:1}),n.info.tplCategory=="tree"?(f(),y(v,{key:0},[Q,$(e(k,null,{default:l(()=>[e(m,{span:12},{default:l(()=>[e(d,null,{label:l(()=>[o(" 树编码字段 "),e(p,{content:"树显示的编码字段名， 如：dept_id",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(g,{modelValue:n.info.treeCode,"onUpdate:modelValue":a[11]||(a[11]=t=>n.info.treeCode=t),placeholder:"请选择"},{default:l(()=>[(f(!0),y(v,null,_(n.info.columns,(t,V)=>(f(),N(r,{key:V,label:t.columnName+"："+t.columnComment,value:t.columnName},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,null,{label:l(()=>[o(" 树父编码字段 "),e(p,{content:"树显示的父编码字段名， 如：parent_Id",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(g,{modelValue:n.info.treeParentCode,"onUpdate:modelValue":a[12]||(a[12]=t=>n.info.treeParentCode=t),placeholder:"请选择"},{default:l(()=>[(f(!0),y(v,null,_(n.info.columns,(t,V)=>(f(),N(r,{key:V,label:t.columnName+"："+t.columnComment,value:t.columnName},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,null,{label:l(()=>[o(" 树名称字段 "),e(p,{content:"树节点的显示名称字段名， 如：dept_name",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(g,{modelValue:n.info.treeName,"onUpdate:modelValue":a[13]||(a[13]=t=>n.info.treeName=t),placeholder:"请选择"},{default:l(()=>[(f(!0),y(v,null,_(n.info.columns,(t,V)=>(f(),N(r,{key:V,label:t.columnName+"："+t.columnComment,value:t.columnName},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1})]),_:1})]),_:1},512),[[G,n.info.tplCategory=="tree"]])],64)):U("",!0),n.info.tplCategory=="sub"?(f(),y(v,{key:1},[X,e(k,null,{default:l(()=>[e(m,{span:12},{default:l(()=>[e(d,null,{label:l(()=>[o(" 关联子表的表名 "),e(p,{content:"关联子表的表名， 如：sys_user",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(g,{modelValue:n.info.subTableName,"onUpdate:modelValue":a[14]||(a[14]=t=>n.info.subTableName=t),placeholder:"请选择",onChange:B},{default:l(()=>[(f(!0),y(v,null,_(n.tables,(t,V)=>(f(),N(r,{key:V,label:t.tableName+"："+t.tableComment,value:t.tableName},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1})]),_:1}),e(m,{span:12},{default:l(()=>[e(d,null,{label:l(()=>[o(" 子表关联的外键名 "),e(p,{content:"子表关联的外键名， 如：user_id",placement:"top"},{default:l(()=>[e(s,null,{default:l(()=>[e(i)]),_:1})]),_:1})]),default:l(()=>[e(g,{modelValue:n.info.subTableFkName,"onUpdate:modelValue":a[15]||(a[15]=t=>n.info.subTableFkName=t),placeholder:"请选择"},{default:l(()=>[(f(!0),y(v,null,_(w(F),(t,V)=>(f(),N(r,{key:V,label:t.columnName+"："+t.columnComment,value:t.columnName},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1})]),_:1})]),_:1})],64)):U("",!0)]),_:1},8,["model","rules"])}}};export{ee as default};
