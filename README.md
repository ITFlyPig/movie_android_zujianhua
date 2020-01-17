工程使用AppJoint实现组件化，方便工程的组装。

#### 1、module的命名规则和调用规则
**命名规则：**
为了一眼能看出module是业务module还是技术或者组件，这里对module的命名做了一点规定。

业务module：以后缀（business的缩写）biz结尾


技术module：以后缀（technology的缩写）tech结尾


公用组件module（公用的LitView、图片显示等组件）：以后缀（component的缩写）com结尾

common模块：存放公用的资源和代码

**调用规则：**


业务module -> 技术module, 业务module -> 公用组件module,


公用组件module -> 技术module


技术module -> common模块

调用规则形象显示：

<img src="http://yanxuan.nosdn.127.net/ebd400b3f3dd27b1493cf8db424aa3e7.png" alt="UTOOLS1578905788866.png" title="UTOOLS1578905788866.png" />

#### 2、模块说明
aars：存放所有aar依赖


app：APP模块

commons：通用模块，存放和业务无关的，但是每个模块都可能用到的资源（暂定这里主要还是存资源）

core：AppJoint框架的核心依赖包

plugin：AppJoint插件

libraries：存放module依赖的第三方提供的module

router：路由

standalone：里面包含可以单独运行的模块的app入口

subBiz：谷歌订阅模块

adBiz：广告业务模块

uiCom：常用的ui组件

uImageLoaderTech：图片下载模块

baseBiz：封装一些公用的base

****

凡是用到butterknife的module，都需要添加依赖，不然view会为空
```gradle
 implementation rootProject.ext.dependencies["butterknife"]
 annotationProcessor rootProject.ext.dependencies["butterknife-compiler"]
```





