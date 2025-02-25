import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  return request({
    url: '/auth/login',
    headers: {
      isToken: false,
      repeatSubmit: false
    },
    method: 'post',
    data: { username, password, code, uuid }
  })
}

// 注册方法
export function register(data) {
  return request({
    url: '/auth/register',
    headers: {
      isToken: false
    },
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/system/user/getInfo',
    method: 'get'
  })
  // 用Promise.resolve()
  // return Promise.resolve({
  //   code: 200,
  //   data: {
  //     name: 'admin'
  //   }
  // })
}

// 退出方法
export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
  // return Promise.resolve({
  //   code: 200,
  //   data: {
  //     name: 'admin'
  //   }
  // })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/code',
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}

// SSO登录方法
export function sso_login(d) {
  return request({
    url: '/auth/aad/login',
    headers: {
      isToken: false,
      repeatSubmit: false,
      'Content-Type': 'text/plain'//避免后端收参多了一层双引号
    },
    method: 'post',
    data: d
  })
}