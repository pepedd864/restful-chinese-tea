<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>展品页面</title>
    <meta charset="UTF-8">
    <script src="../iframeInit.js"></script>
    <script src="../../js/jquery/jquery.min.js"></script>
    <script type="module" src="../../init.js"></script>
    <link rel="stylesheet" href="../../index.css">
    <link rel="stylesheet" href="./index.css">
</head>
<body>
<div id="app">
    <div class="wrapper">
        <div class="side">
            <div class="btn"><span>前一项</span></div>
        </div>
        <div class="content">
            <div class="info">
                <div class="id">ID：0</div>
                <div class="num">编号：001</div>
                <div class="category">新的分类</div>
            </div>
            <div class="btn-group">
                <div class="btn">增</div>
                <div class="btn">删</div>
                <div class="btn">改</div>
                <div class="btn">图</div>
            </div>
            <img class="img" src="../../assets/side/1-分类管理.png"/>
            <div class="title">请先添加数据</div>
            <div class="desc"></div>
        </div>
        <div class="edit-form">
            <label for="">
                <span>展品编号:</span>
                <input type="text">
            </label>
            <label>
                <span>展品标题:</span>
                <input type="text">
            </label>
            <label>
                <span>展品分类:</span>
                <span>新的分类</span>
            </label>
            <div class="btn-group">
                <button class="btn add-btn">添加记录</button>
                <button class="btn cancel-btn">取消</button>
            </div>
            <label>
                <span>展品描述:</span>
                <textarea name="" id="" cols="30" rows="10"></textarea>
            </label>
        </div>
        <div class="edit-image">
            <input type="file">
            <img src="" alt="">
            <div class="btn-group">
                <button class="btn upload-btn">上传</button>
                <button class="btn cancel-btn">取消</button>
            </div>
        </div>
        <div class="side">
            <div class="btn"><span>后一项</span></div>
        </div>
    </div>
</div>
<script type="module">
</script>
</body>
</html>
