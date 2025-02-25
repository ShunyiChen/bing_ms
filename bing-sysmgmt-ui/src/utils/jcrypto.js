// var CryptoJS = require("crypto-js");
import CryptoJS from "crypto-js"

// 加密
export function aesEncrypt(txt) {
  // Encrypt
  var ciphertext = CryptoJS.AES.encrypt(txt, import.meta.env.VITE_APP_SECRET_KEY).toString();
  return ciphertext
}

// 解密
export function aesDecrypt(encrypted) {
  // Decrypt
  var bytes = CryptoJS.AES.decrypt(encrypted, import.meta.env.VITE_APP_SECRET_KEY);
  var originalText = bytes.toString(CryptoJS.enc.Utf8);
  return originalText
}

export function complexAESEncrypt(txt) {
  var pkey = CryptoJS.enc.Utf8.parse(import.meta.env.VITE_APP_AES_KEY)
  var piv = CryptoJS.enc.Utf8.parse(import.meta.env.VITE_APP_AES_IV)
  var ptxt = CryptoJS.enc.Utf8.parse(txt)
  var encrypted = CryptoJS.AES.encrypt(ptxt, pkey, {
    iv: piv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.Pkcs7
  })
  return encrypted.toString()
}

export function complexAESDecrypt(txt) {
  var pkey = CryptoJS.enc.Utf8.parse(import.meta.env.VITE_APP_AES_KEY)
  var piv = CryptoJS.enc.Utf8.parse(import.meta.env.VITE_APP_AES_IV)
  var decrypted = CryptoJS.AES.decrypt(txt, pkey, {
    iv: piv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.Pkcs7
  })
  return decrypted.toString(CryptoJS.enc.Utf8)
}

export function base64Encode(txt) {
  var srcs = CryptoJS.enc.Utf8.parse(txt)
  var encode = CryptoJS.enc.Base64.stringify(srcs)
  return encode
}

export function md5Encrypt(txt) {
  // Encrypt
  var md5Hash = CryptoJS.MD5(txt).toString();
  return md5Hash
}
