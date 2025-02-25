import { aesEncrypt, aesDecrypt, md5Encrypt } from '@/utils/jcrypto'

const TokenKey = 'Admin-Token'

const ExpiresInKey = 'Admin-Expires-In'

export function getToken() {
  const k = localStorage.getItem(md5Encrypt(TokenKey))
  if(!k) {
    return ''
  }
  return aesDecrypt(k)
}

export function setToken(token) {
  return localStorage.setItem(md5Encrypt(TokenKey), aesEncrypt(token))
}

export function removeToken() {
  return localStorage.removeItem(md5Encrypt(TokenKey))
}
