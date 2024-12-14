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
                <label>
                    <span>ID:</span>
                    <input type="text">
                </label>
            </div>
            <div class="item">
                <label>
                    <span>分类编号:</span>
                    <input type="text">
                </label>
            </div>
            <div class="item">
                <span></span>
                <label>
                    <span>分类标题:</span>
                    <input type="text">
                </label>
            </div>
            <button class="btn flash-btn">刷新列表</button>
            <button class="btn add-btn">添加记录</button>
            <button class="btn edit-btn">修改记录</button>
        </div>
        <div class="edit-icon">
            <div class="left">
                <div class="item">
                    <label>
                        <span>ID:</span>
                        <input type="text">
                    </label>
                </div>
                <input type="file" id="fileInput"/>
            </div>
            <div class="right">
                <button class="btn">上传</button>
                <button class="btn">取消</button>
            </div>
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
  import {getListItem, listItemClick, formInfoClick} from './index.js'
  import transformFileToBuffer from "../../js/utils/transformFileToBuffer.js";
  import {uploadFile} from "../../js/apis/file.js";
  import {getAllCategory, createCategory} from "../../js/apis/category.js";

  const contextPath = '${pageContext.request.contextPath}'

  $(document).ready(async function () {
    const categoryList = $('#app .category-list table')

    const res = await getAllCategory(contextPath)
    if (res.data) {
      const listContent = res.data.map(item => getListItem(contextPath, item))
      categoryList.append(listContent)
    }

    listItemClick(contextPath)
    formInfoClick(contextPath)

  });
</script>
</body>
</html>
