广告分为Unity广告和谷歌广告

#### 1、Unity广告
##### 1.1 广告接入
1. 添加unity-ads.aar依赖：这里选择以module的形式添加aar
2. 在activity的oncreate中使用UnityAdHelper#init初始化， 要显示的时候使用UnityAdHelper#show显示广告

参考：https://github.com/unity-cn/unityads-help-cn/wiki/chinese_sdk_android_integration_guide

#### 2、谷歌广告