import request from '@/utils/request'

export function getReleaseDateList(data) {
    return request({
      url: '/system/releasenotes/getReleaseDateList',
      method: 'post',
      data: data
    })
}

export function getReleaseNotesByDate(data) {
    return request({
      url: '/system/releasenotes/getReleaseNotesByDate',
      method: 'post',
      data: data
    })
}