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
		"eol-last": [0],
		// 缩进严格,一个tab四个空格
		"indent": [2, 4],
		// 严格对比,判断相等必须用 === !==
		"eqeqeq": [2, 'always'],
		"semi": [2, 'never'],
		// 不允许new对象之后不使用
		"no-new": [0],
		'generator-star-spacing': 'off',
		// 测试环境不允许使用 debugger
		'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off'
	}
}
