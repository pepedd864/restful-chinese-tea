<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>展品页面</title>
    <meta charset="UTF-8">
    <script src="../iframeInit.js"></script>
    <script src="../../js/jquery/jquery.min.js"></script>
    <script type="module" src="../../init.js"></script>
    <link rel="stylesheet" href="../../init.css">
    <link rel="stylesheet" href="./index.css">
</head>
<body>
<div id="app">
    <div class="wrapper">
        <div class="side">
            <div class="btn prev-btn"><span>前一项</span></div>
        </div>
        <div class="content">
            <div class="exhibits">
                <div class="info">
                    <div class="id">ID：0</div>
                    <div class="num">编号：001</div>
                    <div class="category">新的分类</div>
                </div>
                <div class="btn-group">
                    <div class="btn add-btn">增</div>
                    <div class="btn del-btn">删</div>
                    <div class="btn edit-btn">改</div>
                    <div class="btn img-btn">图</div>
                </div>
                <img class="img" src="../../assets/side/default-menu.png"/>
                <div class="title">请先添加数据</div>
                <div class="desc"></div>
            </div>
            <div class="edit-form">
                <div class="line">
                    <label for="num">
                        <span>展品编号:</span>
                        <input id="num" type="text">
                    </label>
                    <label for="title">
                        <span>展品标题:</span>
                        <input id="title" type="text">
                    </label>
                    <label for="category">
                        <span>展品分类:</span>
                        <span id="category">新的分类</span>
                    </label>
                    <div class="btn-group">
                        <button class="btn edit-btn">添加记录</button>
                        <button class="btn cancel-btn">取消</button>
                    </div>
                </div>
                <fieldset>
                    <legend>展品描述:</legend>
                    <textarea id="desc" rows="10"></textarea>
                </fieldset>
            </div>
            <div class="edit-image">
                <img src="" alt="">
                <div class="bottom">
                    <input type="file">
                    <div class="btn-group">
                        <button class="btn upload-btn">上传</button>
                        <button class="btn cancel-btn">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="side">
            <div class="btn next-btn"><span>后一项</span></div>
        </div>
    </div>
</div>
<script type="module">
  import {initExhibitsList} from './index.js'

  $(document).ready(async function () {
    await initExhibitsList()
  })
</script>
</body>
</html>
