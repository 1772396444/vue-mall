// https://eslint.org/docs/user-guide/configuring

module.exports = {
	root: true,
	parserOptions: {
		parser: 'babel-eslint'
	},
	env: {
		es6: true,
		node: true,
		browser: true,
	},
	extends: [
		"plugin:vue/essential",
		"standard"
	],
	plugins: [
		'vue'
	],
	rules: {
		// 规则在非空文件的末尾至少执行一个换行符
		// "eol-last": [0],
		// 不允许使用 alert conirm
		"no-alert": "error",
		// 缩进严格,一个tab四个空格
		"indent": [2, 4],
		// 严格对比,判断相等必须用 === !==
		"eqeqeq": [2, 'always'],
		// 是否必须以分号结尾
		"semi": [2, 'always'],
		// 不允许new对象之后不使用
		"no-new": [0],
		// 允许定义但没有使用的变量 回调函数中的实参也可以不使用
		"no-unused-vars": ["off", {"args": "none"}],
		// 操作符空格规范 如 a + b 不能是 a+b
		"space-infix-ops": "error",
		// 允许空号(代码规范)
		'generator-star-spacing': 'off',
		// 测试环境不允许使用 debugger
		'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off'
	}
}
