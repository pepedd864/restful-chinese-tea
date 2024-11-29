let request = function (method, url, data, successCallBack, errorCallBack, async) {
    console.log("方法：", method);
    console.log("地址：", url);
    console.log("请求参数：", data);
    $.ajax({
        url: url,
        async: async,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data),
        method: method
    }).done(successCallBack).fail(errorCallBack);
}