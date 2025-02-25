import variables from '@/assets/styles/variables.module.scss'

// 初始化侧边栏背景颜色
export function changeSidebarBgColor(dark, sideTheme) {
	//修改Sidebar背景颜色
	const sidebarBgColor = dark == 'true'? 'none': (sideTheme === 'theme-dark'?variables.menuBackground:variables.menuLightBackground);
	document.documentElement.style.setProperty('--ey-menu-bg-color', sidebarBgColor);
	
	//修改SubMenu背景颜色
	const subMenuBgColor = dark.toString() == 'true'? 'none': (sideTheme === 'theme-dark'?variables.subMenuBackground:variables.subMenuLightBackground);
	document.documentElement.style.setProperty('--ey-sub-menu-background', subMenuBgColor);
	document.documentElement.style.setProperty('--el-menu-bg-color', subMenuBgColor);
}

// 处理主题颜色样式
export function handleThemeStyle(theme) {
	document.documentElement.style.setProperty('--el-color-primary', theme)
	document.documentElement.style.setProperty('--el-text-color-primary', theme)
	for (let i = 1; i <= 9; i++) {
		document.documentElement.style.setProperty(`--el-color-primary-light-${i}`, `${getLightColor(theme, i / 10)}`)
	}
	for (let i = 1; i <= 9; i++) {
		document.documentElement.style.setProperty(`--el-color-primary-dark-${i}`, `${getDarkColor(theme, i / 10)}`)
	}
	//特殊处理：如果是EY主题颜色则设置文字为黑色，否则文字为白色
	if(theme == '#FFE600') {
		document.documentElement.style.setProperty('--el-color-white', '#2E2E38')
		document.documentElement.style.setProperty('--ey-off-black', '#2E2E38')
	} else {
		document.documentElement.style.setProperty('--el-color-white', '#FFF')
		document.documentElement.style.setProperty('--ey-off-black', '#FFF')
	}
}

// hex颜色转rgb颜色
export function hexToRgb(str) {
	str = str.replace('#', '')
	let hexs = str.match(/../g)
	for (let i = 0; i < 3; i++) {
		hexs[i] = parseInt(hexs[i], 16)
	}
	return hexs
}

// rgb颜色转Hex颜色
export function rgbToHex(r, g, b) {
	let hexs = [r.toString(16), g.toString(16), b.toString(16)]
	for (let i = 0; i < 3; i++) {
		if (hexs[i].length == 1) {
			hexs[i] = `0${hexs[i]}`
		}
	}
	return `#${hexs.join('')}`
}

// 变浅颜色值
export function getLightColor(color, level) {
	let rgb = hexToRgb(color)
	for (let i = 0; i < 3; i++) {
		rgb[i] = Math.floor((255 - rgb[i]) * level + rgb[i])
	}
	return rgbToHex(rgb[0], rgb[1], rgb[2])
}

// 变深颜色值
export function getDarkColor(color, level) {
	let rgb = hexToRgb(color)
	for (let i = 0; i < 3; i++) {
		rgb[i] = Math.floor(rgb[i] * (1 - level))
	}
	return rgbToHex(rgb[0], rgb[1], rgb[2])
}
