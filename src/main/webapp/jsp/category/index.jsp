<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>分类页面</title>
    <meta charset="UTF-8">
    <script src="../iframe.js"></script>
    <script src="../../js/jquery/jquery.min.js"></script>
    <link rel="stylesheet" href="../../index.css">
    <link rel="stylesheet" href="./index.css">
</head>
<body>
<div id="app">123</div>
<%--<img src="${pageContext.request.contextPath}/api/file/display/default-icon.png" >--%>
<input type="file" id="fileInput"/>
<input type="submit" value="上传文件"/>
<script type="module">
    import transformFileToBuffer from "../../js/utils/transformFileToBuffer.js";
    import {uploadFile} from "../../js/apis/file.js";
    import {getAllCategory, createCategory} from "../../js/apis/category.js";
    
    const contextPath = '${pageContext.request.contextPath}'
    $(document).ready(function () {
        $('input[type=submit]').click(async function () {
            var fileInput = $('#fileInput')[0].files[0];
            console.log(fileInput)
            const buffer = await transformFileToBuffer(fileInput)
            const res1 = await uploadFile(contextPath, fileInput.name, buffer)
            console.log(res1)
            const res3 = await createCategory(contextPath, {
                "title": "茶叶",
                "num": "CT001",
                "icon": "/api/file/display/default-icon.png"
            })
            console.log(res3)
            const res2 = await getAllCategory(contextPath)
            console.log(res2)
        })
    });
</script>
</body>
</html>
