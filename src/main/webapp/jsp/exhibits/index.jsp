<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>展品页面</title>
    <meta charset="UTF-8">
    <script src="../iframe.js"></script>
    <script src="../../js/jquery/jquery.min.js"></script>
    <script src="../../init.js"></script>
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
        <div class="edit-form"></div>
        <div class="edit-image"></div>
        <div class="side">
            <div class="btn"><span>后一项</span></div>
        </div>
    </div>
</div>
<script type="module">
</script>
</body>
</html>
