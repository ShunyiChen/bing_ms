import{A as e}from"./index-CAgRbO-3.js";function r(t){return e({url:"/schedule/job/list",method:"get",params:t})}function d(t){return e({url:"/schedule/job/"+t,method:"get"})}function n(t){return e({url:"/schedule/job",method:"post",data:t})}function s(t){return e({url:"/schedule/job",method:"put",data:t})}function c(t){return e({url:"/schedule/job/"+t,method:"delete"})}function l(t,u){return e({url:"/schedule/job/changeStatus",method:"put",data:{jobId:t,status:u}})}function h(t,u){return e({url:"/schedule/job/run",method:"put",data:{jobId:t,jobGroup:u}})}export{n as a,l as c,c as d,d as g,r as l,h as r,s as u};
