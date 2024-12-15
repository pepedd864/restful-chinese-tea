// iframe 初始化

/* 设置字体 */
document.documentElement.style.fontSize = window.parent.wFontSize
window.addEventListener('resize', function () {
  this.document.documentElement.style.fontSize = window.parent.wFontSize
})

/* 设置contextPath */
window.contextPath = window.parent.contextPath

