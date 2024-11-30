<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024/11/29
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>中国茶文化</title>
    <link rel="stylesheet" href="./index.css">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 100vw;
            height: 100vh;
            background: #000;
        }
        #app {
            position: relative;
            flex-direction: column;
            width:  120rem; /* 1280px */
            height: 67.5rem; /* 720px */
            overflow: hidden;
        }
        .title {
            display: flex;
            align-items: center;
            color: #fff;
            background: green;
            font-size: 2rem;
            height: 7rem;
            width: 100%;
        }
        .wrapper {
            display: flex;
            position: relative;
            height: calc(100% - 7rem);
        }
        .side {
            color: #fff;
            background: greenyellow;
            width: 10rem;
            height: 100%;
        }
        .content {
            position: relative;
            width: calc(100% - 7rem);
            height: 100%;
            background: #ffffff;
        }
        .content iframe {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <div id="app">
        <div class="title">
            <%--中国茶文化展--%>
        </div>
        <div class="wrapper">
            <div class="side"></div>
            <div class="content">
                <iframe src="./category/index.jsp" frameborder="0"></iframe>
            </div>
        </div>
    </div>
</body>
</html>
