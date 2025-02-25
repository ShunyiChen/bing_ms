import Cookies from 'js-cookie'
const cookieExpiresDays = 128;

const useAppStore = defineStore(
  'app',
  {
    state: () => ({
      sidebar: {
        opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
        withoutAnimation: false,
        hide: false
      },
      device: 'desktop',
      size: Cookies.get('size') || 'default',
      dark: Cookies.get('dark') || 'false',
      language: Cookies.get('language') || 'zhcn',
    }),
    actions: {
      toggleSideBar(withoutAnimation) {
        if (this.sidebar.hide) {
          return false;
        }
        this.sidebar.opened = !this.sidebar.opened
        this.sidebar.withoutAnimation = withoutAnimation
        if (this.sidebar.opened) {
          Cookies.set('sidebarStatus', 1)
        } else {
          Cookies.set('sidebarStatus', 0)
        }
      },
      closeSideBar({ withoutAnimation }) {
        Cookies.set('sidebarStatus', 0)
        this.sidebar.opened = false
        this.sidebar.withoutAnimation = withoutAnimation
      },
      toggleDevice(device) {
        this.device = device
      },
      setSize(size) {
        this.size = size;
        Cookies.set('size', size)
      },
      toggleSideBarHide(status) {
        this.sidebar.hide = status
      },
      setDark(dark) {
        this.dark = dark;
        Cookies.set('dark', dark, { expires: cookieExpiresDays })
      },
      setLanguage(language) {
        this.language = language;
        Cookies.set('language', language, { expires: cookieExpiresDays })
      },
    }
  })

export default useAppStore
