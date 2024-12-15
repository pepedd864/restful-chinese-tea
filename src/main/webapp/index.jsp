<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>中国茶文化</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./init.css"/>
    <link rel="stylesheet" href="./index.css">
    <script src="./js/jquery/jquery.min.js"></script>
    <script type="module" src="./init.js"></script>
    <script src="index.js"></script>
    <script type="module">
      import {getAllCategory} from "./js/apis/category.js";

      window.contextPath = '${pageContext.request.contextPath}'
      console.log('contextPath:', contextPath)
      const res = await getAllCategory()
      if (res.data) {
        generateMenus(res.data)
      }
      console.log(res)
    </script>
</head>
<body>
<div id="app">
    <div id="loading">加载中...</div>
    <div class="title">
        <div class="icon"></div>
        <div class="text">中国茶文化展</div>
    </div>
    <div class="wrapper">
        <div class="side">
        </div>
        <div class="content"></div>
    </div>
</div>
<script>
  $('#app .title').click(function () {
    changePage('home')
  })
</script>
</body>
</html>
