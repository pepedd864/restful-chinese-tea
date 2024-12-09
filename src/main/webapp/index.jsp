<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>中国茶文化</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="index.css"/>
    <script src="./js/jquery/jquery.min.js"></script>
    <script src="./init.js"></script>
    <script src="index.js"></script>
    <script type="module">
        import {getAllCategory, createCategory} from "./js/apis/category.js";

        const contextPath = '${pageContext.request.contextPath}'
        console.log('contextPath:', contextPath)
        const res = await getAllCategory(contextPath)
        if (res.data) {
            res.data.push()
            menus.push({
                title: '分类管理',
                num: 'manage',
                icon: '/api/file/display/default-icon.png'
            }, ...res.data)
            console.log({menus})
            const btns = menus.map((item) => {
                return '<div class="btn" data-page="' + (item.num === 'manage' ? 'category' : 'exhibits?num=' + item?.num) + '">' +
                    '<img src="' + contextPath + item.icon + '"/>' +
                    '<div class="text">' + item.title + '</div>' +
                    '</div>'
            })
            $('.wrapper .side').html(btns)
            const elems = $(".wrapper .side .btn")
            console.log(elems)
            elems.each(function (index, elem) {
                const toPage = $(elem).data("page");
                $(elem).click(() => {
                    setMenuActive(elems, index)
                    changePage(toPage);
                });
            });
        }
        console.log(res)
    </script>
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
            width: 120rem;
            height: 67.5rem;
            overflow: hidden;
        }

        .title {
            display: flex;
            align-items: center;
            background: url("./assets/bg/背景-上.jpg") no-repeat;
            background-size: cover;
            height: 7rem;
            width: 100%;
            padding-left: 1rem;
        }

        .title .icon {
            height: 6rem;
            width: 7rem;
            background: url("./assets/logo.png") no-repeat;
            background-size: cover;
            opacity: 0.5;
        }

        .title .text {
            margin-left: 2rem;
            font-size: 3.5rem;
            color: #000000;
            opacity: 0.5;
        }

        .wrapper {
            display: flex;
            position: relative;
            height: calc(100% - 7rem);
        }

        .side {
            display: flex;
            align-items: center;
            flex-direction: column;
            padding-top: 1rem;
            gap: 1rem;
            color: #fff;
            background-size: cover;
            background: url("./assets/bg/背景-左.jpg") no-repeat;
            width: 10rem;
            height: 100%;
        }

        .side .btn {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 8rem;
            height: 8rem;
            cursor: pointer;
        }

        .side .btn img {
            object-fit: cover;
            height: 6.5rem;
            width: 6.5rem;
            transition: filter 0.3s;
        }

        .side .btn.active img {
            filter: drop-shadow(0 0 1rem white) drop-shadow(0 0 1rem white);
        }

        .side .btn .text {
            font-size: 1.5rem;
        }

        .content {
            position: relative;
            width: calc(100% - 7rem);
            height: 100%;
            background: url("./assets/bg/背景-中.jpg") no-repeat center center;
            background-size: cover;
            overflow: hidden;
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
        <div class="icon"></div>
        <div class="text">中国茶文化展</div>
    </div>
    <div class="wrapper">
        <div class="side">
        </div>
        <div class="content"></div>
    </div>
</div>
</body>
</html>
