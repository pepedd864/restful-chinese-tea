let current = "home";
const pages = {
  home: "./jsp/home/index.jsp",
  category: "./jsp/category/index.jsp",
  exhibits: "./jsp/exhibits/index.jsp",
};
var wWidth = window.innerWidth;
var wHeight = window.innerHeight;
let iframe;
var wFontSize

function strToURL(str) {
  // path?param1=value1&param2=value2 -> {path,param:[{key,value}]
  if (!str && typeof str !== 'string') return
  const splits = str.split('?')
  if (splits.length === 1) return {path: splits[0]}
  if (splits.length !== 2) return;
  const params = splits[1].split('&').map(item => {
    return {
      key: item.split('=')[0],
      value: item.split('=')[1]
    }
  })
  return {
    path: splits[0],
    params
  }
}

function urlToStr(urlObject) {
  // {path,param:[{key,value}] -> path?param1=value1&param2=value2
  if (!urlObject || typeof urlObject !== 'object') return;
  let queryString = urlObject.path;

  if (urlObject.params && Array.isArray(urlObject.params) && urlObject.params.length > 0) {
    const params = urlObject.params.map(param => {
      return `${encodeURIComponent(param.key)}=${encodeURIComponent(param.value)}`;
    });
    queryString = queryString ? queryString += '?' + params.join('&') : params.join('&')
  }
  console.log('queryString', queryString)
  return queryString;
}

function rem() {
  wWidth = window.innerWidth;
  wHeight = window.innerHeight;
  wFontSize = getFontSize();
  $("html").css("font-size", wFontSize);
}

function getFontSize() {
  const wfs = (wWidth / 1920) * 16; // -> 100vw / 1920 * 16
  const hfs = (wHeight / 1080) * 16; // -> 100vh / 1080 * 16
  if (wfs * 67.5 <= wHeight) return wfs + "px";
  if (hfs * 120 <= wWidth) return hfs + "px";
}

function getIframe(paramStr) {
  console.log(paramStr)
  iframe = $(
    `<iframe id="page-viewer" src="${
      pages[current] + '?' + (paramStr ? paramStr + '&' : '')
    }" frameborder="0"></iframe>`
  );
  return iframe;
}

function mountPage(paramStr) {
  $("#app .content").html(getIframe(paramStr));
}

function initPage() {
  const url = new URL(window.location);
  const toPage = url.searchParams.get("page");
  console.log('初始化页面:', toPage);
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
    const num = url.searchParams.get('num')
    console.log('init', num)
    changePage(`${toPage + (num ? '?num=' + num : '')}`);
    return;
  }
  mountPage();
}

function changePage(toPage = "home") {
  console.log('change', toPage)
  const url = new URL(window.location);
  const toPageUrl = strToURL(toPage)
  toPage = toPageUrl.path
  const params = toPageUrl.params
  if (toPage === current && url.searchParams.get('num') === params?.[0].value) return;
  if (Object.keys(pages).includes(toPage)) current = toPage;
  url.searchParams.set("page", current);
  url.searchParams.delete('num')
  params?.[0].value && url.searchParams.set('num', params[0].value)
  history.replaceState(null, null, url);
  toPageUrl.path = ''
  mountPage(urlToStr(toPageUrl));
}

function setMenuActive(elems, index) {
  if (index === null || index === undefined) return
  const elem = elems[index];
  $(elem).siblings().removeClass('active');
  $(elem).addClass('active')
}


$(document).ready(function () {
  initPage();
});
$(window).resize(rem).trigger("resize");
