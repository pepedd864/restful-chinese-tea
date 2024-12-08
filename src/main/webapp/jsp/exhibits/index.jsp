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
            <div class="info"></div>
            <div class="btn"></div>
            <div class="img"></div>
            <div class="title"></div>
            <div class="desc"></div>
        </div>
        <div class="side">
            <div class="btn"><span>后一项</span></div>
        </div>
    </div>
</div>
<script type="module">
    const url = new URL(window.location);
    const categoryId = url.searchParams.get('num')
    // $('#app').html('categoryId' + categoryId)
</script>
</body>
</html>
