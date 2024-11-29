var doc = document.documentElement
doc.style.fontSize = ''

function rem() {
  var width = Math.min(doc.getBoundingClientRect().width, 1920)
  doc.style.fontSize = width / 16 + 'px'
}

rem()
window.addEventListener('resize', rem)
