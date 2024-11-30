var doc = document.documentElement;
doc.style.fontSize = "";
var fontSize;
var iframe;

function rem() {
	var width = window.innerWidth;
	var height = window.innerHeight;
	fontSize = getFontSize(width, height);
	doc.style.fontSize = fontSize;
	setIframeFs(fontSize);
}

function getFontSize(width, height) {
	var wfs = (width / 1920) * 16; // -> 100vw / 1920 * 16
	var hfs = (height / 1080) * 16; // -> 100vh / 1080 * 16
	if (wfs * 67.5 <= height) return wfs + "px";
	if (hfs * 120 <= width) return hfs + "px";
}

function setIframeFs(fontSize) {
	iframe = document.getElementById("page-viewer");
	iframe.contentWindow.postMessage(fontSize);
}

rem();
window.addEventListener("resize", rem);
iframe?.addEventListener("load", function () {
	iframe.contentWindow.postMessage(fontSize);
});
