let current = "home";
const pages = {
    home: "./home/index.jsp",
    category: "./category/index.jsp",
    exhibits: "./exhibits/index.jsp",
};
let wWidth = window.innerWidth;
let wHeight = window.innerHeight;
let iframe;

function rem() {
    wWidth = window.innerWidth;
    wHeight = window.innerHeight;
    const fontSize = getFontSize();
    $("html").css("font-size", fontSize);
    iframe && iframe.length && iframe[0].contentWindow.postMessage(fontSize);
}

function getFontSize() {
    // console.log({ wWidth, wHeight });
    const wfs = (wWidth / 1920) * 16; // -> 100vw / 1920 * 16
    const hfs = (wHeight / 1080) * 16; // -> 100vh / 1080 * 16
    if (wfs * 67.5 <= wHeight) return wfs + "px";
    if (hfs * 120 <= wWidth) return hfs + "px";
}

function getIframe() {
    iframe = $(
        `<iframe id="page-viewer" src="${
            pages[current]
        }?fontSize=${getFontSize()}" frameborder="0"></iframe>`
    );
    return iframe;
}

function mountPage() {
    $("#app .content").html(getIframe());
}

function initPage() {
    const url = new URL(window.location);
    const toPage = url.searchParams.get("page");
    console.log(toPage);
    if (!toPage || toPage === "null" || toPage === "undefined") {
        url.searchParams.set("page", current);
        history.pushState(null, null, url);
        mountPage();
        return;
    } else if (toPage !== current) {
        // const elems = $('.side .btn')
        // console.log(elems)
        // let index
        // elems.each(function (i, elem) {
        //     console.log($(this).data('page'), toPage)
        //     if ($(this).data('page') === toPage) {
        //         index = i
        //     }
        // })
        // console.log(index)
        // setMenuActive(elems, index)
        changePage(toPage);
        return;
    }
    mountPage();
}

function changePage(toPage = "home") {
    if (toPage === current) return;
    if (Object.keys(pages).includes(toPage)) current = toPage;
    const url = new URL(window.location);
    url.searchParams.set("page", current);
    history.replaceState(null, null, url);
    mountPage();
}

function setMenuActive(elems, index) {
    if (index === null || index === undefined) return
    const elem = elems[index];
    $(elem).siblings().removeClass('active');
    $(elem).addClass('active')
}

$(document).ready(function () {
    initPage();
    const elems = $(".side .btn")
    elems.each(function (index, elem) {
        const toPage = $(elem).data("page");
        $(elem).click(() => {
            setMenuActive(elems, index)
            changePage(toPage);
        });
    });
});
$(window).resize(rem).trigger("resize");
