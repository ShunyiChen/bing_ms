import request from '@/utils/request'

// 查询Pending列表
export function listPending(data) {
  return request({
    url: '/legaldatabase/pending/list',
    method: 'post',
    data: data
  })
}

// 查询Pending详细
export function getPending(id) {
  return request({
    url: '/legaldatabase/pending/' + id,
    method: 'get'
  })
}

// 新增Pending
export function addPending(data) {
  return request({
    url: '/legaldatabase/pending',
    method: 'post',
    data: data
  })
}

// 修改Pending
export function updatePending(data) {
  return request({
    url: '/legaldatabase/pending/update',
    method: 'post',
    data: data
  })
}

// BulkImport批量导入Pending
export function bulkImport(data) {
  return request({
    url: '/legaldatabase/pending/bulkImport',
    method: 'post',
    data: data
  })
}

// 删除Pending
// export function delPending(id) {
//   return request({
//     url: '/legaldatabase/pending/' + id,
//     method: 'delete'
//   })
// }
