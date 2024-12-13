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
        <div class="edit-info" style="display: none">
            <div class="item">
                <span>ID:</span>
                <input type="text">
            </div>
            <div class="item">
                <span>分类编号:</span>
                <input type="text">
            </div>
            <div class="item">
                <span>分类标题:</span>
                <input type="text">
            </div>
            <div class="btn">刷新列表</div>
            <div class="btn">添加记录</div>
            <div class="btn">修改记录</div>
        </div>
        <div class="edit-icon">
            <div class="item">
                <span>ID:</span>
                <input type="text">
            </div>
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

        // Add click event listeners to the "上传" and "编辑" buttons
        $(document).on('click', '.upload-btn', function () {
            const row = $(this).closest('tr');
            const id = row.find('td:eq(0)').text();
            const num = row.find('td:eq(1)').text();
            const title = row.find('td:eq(2)').text();

            // Populate the edit form with the item data
            const editIcon = $('#app .edit-icon');
            editIcon.find('input[type="text"]').val(id);
            editIcon.show();
            $('#app .edit-info').hide();
        });

        $(document).on('click', '.edit-btn', function () {
            const row = $(this).closest('tr');
            const id = row.find('td:eq(0)').text();
            const num = row.find('td:eq(1)').text();
            const title = row.find('td:eq(2)').text();

            // Populate the edit form with the item data
            const editInfo = $('#app .edit-info');
            editInfo.find('input:eq(0)').val(id);
            editInfo.find('input:eq(1)').val(num);
            editInfo.find('input:eq(2)').val(title);
            editInfo.show();
            $('#app .edit-icon').hide();
        });
    });
</script>
</body>
</html>
