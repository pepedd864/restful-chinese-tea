## 1. 技术路线

Java+RESTEasy+jQuery+Hibernate (ORM)+MSSQLServer+jsp

开发方式使用基础项目包和虚拟机进行开发

(后面使用在线服务器，直接在实体机开发了)

## 2. 系统设计

### 2.1 后端

#### 1. 表设计

**分类表**

| 字段    | 名称    | 类型  |        |
| ----- | ----- | ------ | ------ |
| id    | 自增 id | int |        |
| title | 分类标题  | str | unique |
| num   | 分类编号  | str | unique |
| icon  | 分类图标  | str |        |

 **展品表**

| 字段        | 名称     | 类型 |        |
| ----------- | -------- | ---- | ------ |
| id          | 自增 id  | int  |        |
| tittle      | 展品标题 | str  | unique |
| num         | 展品编号 | str  | unique |
| category_id | 分类 id  | int  |        |
| description | 描述     | str  |        |
| img         | 图片     | str  |        |
#### 2. 接口设计

1. 分类的增删改查
   1. 增、删、改
   2. 查询全部、分页查询(考虑实现)

2. 展品的增删改查
   1. 增、删、改

3. 图片接口(文件上传接口)

### 2.2 前端

#### 1.自适应页面

本项目实现了页面固定宽高比任意窗口大小缩放

实现原理是通过rem+动态调整html font-size实现，相比于在css中调整font-size，js调整font-size有更好的灵活性，比如根据最大的是窗口宽度还是窗口高度返回对应的字体大小实现无论什么情况，页面内容都可以在浏览器中占满。

```js
function getFontSize() {
    const wfs = (wWidth / 1920) * 16; // -> 100vw / 1920 * 16
    const hfs = (wHeight / 1080) * 16; // -> 100vh / 1080 * 16
    if (wfs * 67.5 <= wHeight) return wfs + "px";
    if (hfs * 120 <= wWidth) return hfs + "px";
}
```

![](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/1bb7ab9ad55012e7a613d46b2520d7d6.png)

#### 2.通过iframe实现多页面切换

相比于默认的在单个页面上进行开发，使用iframe可以拆分页面，拆分任务，同时也更利于维护项目

目录规划
```bash
webapp
	/assets
	/js
		/apis				# 接口
		/utils				# 一些工具
    /jsp					# 页面
        /exhibits			# 展品页
            style.css
            style.scss
            index.jsp
            index.js
        /category			# 分类页
            style.css
            style.scss
            index.jsp
            index.js
        /home				# 首页
            style.css
            style.scss
            index.jsp
            index.js
    index.jsp				# 父窗口
    index.css
    index.js
    init.js
```



#### 3.利用原生js的能力

##### 1.全局变量和方法

jquery是js的子集，在使用jquery操作元素的同时，尽可能利用js的能力

浏览器环境中，js可以是全局引入的，或是模块化的

默认项目中引入了jquery，就会在window对象上挂载jquery对象，因此，你可以在任何地方使用，他是全局生效的。

同样的，如果引入js不是模块化的，那js中所有方法和变量都会挂载到window对象上，可以在html中任意使用。

同时iframe还可以使用父窗口的变量和方法`window.parent.xxx`

同时因为使用jsp，调用接口使用会有个contextPath问题，需要在jsp中使用`${pageContext.request.contextPath}`获取，可以在父窗口中获取放到window对象上，父窗口和子窗口就都不用关注contextPath获取的问题了

##### 2.使用Promise化ajax

默认使用ajax时，请求结果的处理需要放到回调中处理，即显得累赘，又增加开发成本。因此项目统一采用promise化ajax，可以使用	async await关键字

比如

```js
export function getAllCategory() {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: contextPath + '/api/category/get/All',
      type: 'get',
      success: function (response) {
        resolve(response)
      },
      error: function (xhr, status, error) {
        reject(error)
      }
    })
  })
}
```

同时在页面初始化时设置ajax的请求前拦截器和请求后拦截器，实现类型axios一类请求库的效果

```js
$.ajaxSetup({
  beforeSend: function (xhr, settings) {
    return true;
  },
  complete: function (xhr, textStatus) {
    xhr.then(function (e) {
      if (e.code === 0) return
      alert(e.description)
    })
    xhr.fail(function (e) {
      alert('网络错误，请稍后再试')
    })
  }
});
```

##### 3.searchParams的使用

如果是按照常规路线做的项目，即单jsp，那一定会有个问题，就是如何在刷新页面后保持原来的页面

在这个项目中，我使用searchParams保存了当前页面、当前分类这些值，可以保证在刷新后，页面还是对应的，并且数据也是对应的



#### 4.使用File Wachers实时编译sass

用原生css进行开发实在令人烦恼，不过借助预编译器和idea插件的能力，在这个项目中可以做到实时编译sass文件成css，再通过配置idea的运行配置，实现几乎实时预览

![image-20241215044643071](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/1632afefc49e40f1cf4a1bf80b7f04b5.png)

![image-20241215044622455](https://picgo-img-repo.oss-cn-beijing.aliyuncs.com/img/a356a9d54074cc8846fed1026d03ec11.png)

## 3. 协作开发

综合考虑，前端工作量更大，考虑前端两人，后端一人，产品&设计&文档一人兼前端

开发方式，使用基础项目包，在 github 上托管，一条分支开发即可

github地址：https://github.com/pepedd864/restful-chinese-tea

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

如果发生冲突，另外一个人开发进度更快，可以使用merge或者rebase命令合并他的commit

```bash
git merge origin/master # 通过创建一个单独的commit实现合并, 如：Merge remote-tracking branch 'origin/master'
git rebase origin/master # 没有单独的commit
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



## 5. 资源

待补充

