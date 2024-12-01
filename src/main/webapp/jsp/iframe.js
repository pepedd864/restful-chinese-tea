var url = new URL(window.location);
var fontSize = url.searchParams.get("fontSize");
if (fontSize) {
	document.documentElement.style.fontSize = fontSize;
}
window.addEventListener("message", function (e) {
	this.document.documentElement.style.fontSize = e.data;
});
