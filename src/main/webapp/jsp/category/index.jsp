<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>分类页面</title>
    <meta charset="UTF-8">
    <script src="../iframe.js"></script>
    <script src="../../js/jquery/jquery.min.js"></script>
    <script src="../../init.js"></script>
    <link rel="stylesheet" href="../../index.css">
    <link rel="stylesheet" href="./index.css">
</head>
<body>
<div id="app">
    <div class="edit-form">
        <div class="edit-info"></div>
        <div class="edit-icon">
            <input type="file" id="fileInput"/>
            <input type="submit" value="上传文件"/>
        </div>
    </div>
    <div class="category-list">
        <table>
            <tr>
                <th>分类图标</th>
                <th>分类编号</th>
                <th>分类标题</th>
                <th>操作</th>
            </tr>
        </table>
    </div>
</div>
<script type="module">
    import {getListItem} from './index.js'
    import transformFileToBuffer from "../../js/utils/transformFileToBuffer.js";
    import {uploadFile} from "../../js/apis/file.js";
    import {getAllCategory, createCategory} from "../../js/apis/category.js";

    const contextPath = '${pageContext.request.contextPath}'

    $(document).ready(async function () {
        const editInfo = $('#app .edit-info')
        const editIcon = $('#app .edit-icon')
        const categoryList = $('#app .category-list table')

        const res = await getAllCategory(contextPath)
        if (res.data) {
            const listContent = res.data.map(item => '<tr>' + getListItem(contextPath, item) + '</tr>')
            categoryList.append(listContent)
        }
    });
</script>
</body>
</html>
