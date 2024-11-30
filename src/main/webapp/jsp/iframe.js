window.addEventListener("message", function (e) {
	this.document.documentElement.style.fontSize = e.data;
});
