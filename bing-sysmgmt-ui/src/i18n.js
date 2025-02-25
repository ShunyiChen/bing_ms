// src/i18n.js
import { createI18n } from 'vue-i18n'
import {messages} from '@/utils/i18n'
import Cookies from 'js-cookie'

const i18n = createI18n({
  locale: Cookies.get('language') || 'zhcn',
  fallbackLocale: 'en',
  messages: messages
})

export default i18n;
