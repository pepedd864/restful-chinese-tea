## 1. 技术路线

按照学习通即可，不要使用任何其他框架如 spring boot、vue 等的代码

Java+RESTEasy+jQuery+Hibernate (ORM)+MSSQLServer

开发方式最好使用他给的基础项目包和虚拟机进行开发

## 2. 系统设计

### 2.1 后端

#### 1. 表设计

**分类表**

| 字段    | 名称    |        |
| ----- | ----- | ------ |
| id    | 自增 id |        |
| title | 分类标题  | unique |
| num   | 分类编号  | unique |
| icon  | 分类图标  |        |
**展品表**

| 字段          | 名称    |        |
| ----------- | ----- | ------ |
| id          | 自增 id |        |
| tittle      | 展品标题  | unique |
| num         | 展品编号  | unique |
| category_id | 分类 id |        |
| description | 描述    |        |
| img         | 图片    |        |
#### 2. 接口设计

1. 分类的增删改查
2. 展品的增删改查
3. 图片接口

### 2.2 前端

使用设计稿 `1920*1080 ` 宽度，用于设计此类横屏网页比较合适
使用自适应布局实现
```css
html {
	font-size: calc(100vw / 1920  * 16);
}
div {
	width: 10rem;
	height: 10rem;
	background: #000;
}
```

页面方面，考虑使用 iframe，使用 jquery 操作 iframe src 实现切换页面

目录规划
```
/jsp
	/exhibits
		style.css
		index.jsp
	/category
		style.css
		index.jsp
	index.jsp
```

其他无需注意的点
## 3. 协作开发

综合考虑，前端工作量更大，考虑前端两人，后端一人，产品&设计&文档一人兼前端

开发方式，使用基础项目包，在 github 上托管，一条分支开发即可，不需要处理冲突

关联远程仓库
 ```
 git remote add origin https://github.com/xxx/xxx.git
 ```
提交代码到暂存区并提交 commit
 ```
 git add .
 git commit -m <提交信息>
 ```
推送代码到远端仓库
 ```
 git push --set-upstream origin master
 ```
拉取代码至本地
 ```
 git pull origin
 ```

### 1. 后端

根据“系统设计”建表，sql 文件放到根目录下，方便前端建表调试

写出根据“分类”和“展品”的 CRUD，需要多一个修改图片的接口

### 2. 前端

分工
1. 整体框架的编写，即：从接口加载分类按钮，绑定事件，jquery 操作 iframe src 的实现，背景，首页第一屏
2. 分类页面
3. 展品页面

## 4. 内容

主要根据以下分类，对应的图片和说明之后会给出

**分类->展品：**
1. 茶种类
    1. 绿茶
    2. 红茶
    3. 乌龙茶
    4. 白茶
    5. 黄茶
    6. 黑茶
    7. 等等
2. 茶产地
    1. 西湖区：西湖龙井
    2. 湄潭县：湄潭翠芽
    3. 普洱市：普洱茶
    4. 武夷山市：武夷岩茶
    5. 安溪县：安溪铁观音
    6. 福鼎市：福鼎白茶
    7. 南投县：台湾红茶
3. 茶具
    1. 茶壶
    2. 茶杯
    3. 盖碗
    4. 茶盘
    5. 公道杯
    6. 茶海
    7. 茶漏
    8. 茶针
    9. 茶荷
    10. 茶叶罐
    11. 茶巾
    12. 茶挟
    13. 茶匙
    14. 煮水器
    15. 茶船
    16. 茶筒
    17. 养壶笔
    18. 茶桶
    19. 闻香杯
    20. 品茗杯
4. 茶艺
    1. 备器
    2. 煮水
    3. 洗茶
    4. 泡茶
    5. 倒茶
    6. 奉茶
    7. 品茶
5. 茶的成分 (功效)
    1. 茶多酚
    2. 茶多糖
    3. 生物碱
    4. 茶皂素



## 5. 资源

1. 背景图
    1. 首页背景图
        1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/a66e921ac75de7f9d1f606d47933c00f.png)
    2. 次要背景图
        1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/1ac791d09efa93753c9725e544f56d7c.png)
2. 页头
    1. 字体：任选
    2. 背景图
        1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/54c006b1790824d1463f9f6b6d0ed1dd.png)
3. 侧边栏
    1. 分类图片
    2. 字体：楷体
    3. 背景图
        1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/a397b974b96328c985a8d86c4d69a0b8.png)
4. 容器背景图：共用页头背景图
5. 花边
    1. 花边1
        1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/fac2bf10bd2cf3d4c336f6aceb67578b.png)
    2. 花边2
        1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/5aeebda53b6c1d1fb75fd9bd841655e9.png)
    3. 花边3
        1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/7f3a767e16f9aa6247a0b3f8d11d6d2c.png)
    4. 花边4
        1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/e19654bd53b3668ae18d102e03299481.png)
6. 增删改查 按钮
    1. ![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/537b4e8acfe667e7e9d8763bc901052c.png)
7. 分类图片
8. 展品图片
9. 字体
    1. 其他字体
    2. 楷体

