<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>分类页面</title>
    <meta charset="UTF-8">
    <script src="../iframeInit.js"></script>
    <script src="../../js/jquery/jquery.min.js"></script>
    <script type="module" src="../../init.js"></script>
    <link rel="stylesheet" href="../../index.css">
    <link rel="stylesheet" href="./index.css">
</head>
<body>
<div id="app">
    <div class="edit-form">
        <div class="edit-info">
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
        <div class="edit-icon" style="display: none">
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
                <button class="btn upload-btn">上传</button>
                <button class="btn cancel-btn">取消</button>
            </div>
        </div>
    </div>
    <div class="category-list">
        <table>

        </table>
    </div>
</div>
<script type="module">
  import {generateCategoryList, listItemClick, formInfoClick, formIconClick} from './index.js'

  $(document).ready(async function () {
    await generateCategoryList()

    listItemClick()
    formInfoClick()
    formIconClick()

  });
</script>
</body>
</html>
