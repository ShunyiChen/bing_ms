import request from '@/utils/request'

// 查询DPNRequest列表
export function listDPNRequest(data) {
  return request({
    url: '/legaldatabase/dpnrequest/list',
    method: 'post',
    data: data
  })
}

// 查询DPNRequest详细
export function getDPNRequest(id) {
  return request({
    url: '/legaldatabase/dpnrequest/' + id,
    method: 'get'
  })
}

// 新增DPNRequest
export function addDPNRequest(data) {
  return request({
    url: '/legaldatabase/dpnrequest',
    method: 'post',
    data: data
  })
}

// 修改DPNRequest
export function updateDPNRequest(data) {
  return request({
    url: '/legaldatabase/dpnrequest/update',
    method: 'post',
    data: data
  })
}

// 查询各个状态的Count
export function getDPNRequestCount(data) {
  return request({
    url: '/legaldatabase/dpnrequest/count',
    method: 'post',
    data: data
  })
}

// BulkApproval批量审批
export function bulkApproval(data) {
  return request({
    url: '/legaldatabase/dpnrequest/bulkApproval',
    method: 'post',
    data: data
  })
}

// 通过LPN获取员工信息
export function fetchEmployeeInfoByLPN(lpn) {
  return request({
    url: '/legaldatabase/dpnrequest/fetchEmployeeInfoByLPN',
    method: 'post',
    params: lpn
  })
}

// 通过Email获取员工信息
export function getOneEmployeeByEmail(email) {
  return request({
    url: '/legaldatabase/dpnrequest/getOneEmployeeByEmail',
    method: 'post',
    params: email
  })
}

// 通过EID获取Engagement信息
export function getEngagementByEID(eid) {
  return request({
    url: '/legaldatabase/dpnrequest/getEngagementByEID',
    method: 'post',
    params: eid
  })
}