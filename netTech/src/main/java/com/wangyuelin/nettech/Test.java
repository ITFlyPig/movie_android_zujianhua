package com.wangyuelin.nettech;


import io.reactivex.Observable;

/**
 * author : yuelinwang
 * time   : 2020-01-14 10:13
 * desc   : 测试使用
 */
public class Test {

    public static Observable<HttpConf> get() {
        HttpConf httpConf = new HttpConf();
        httpConf.requestType = 1;
        return Observable.just(httpConf);
    }

    
    

    private void test() {

    }
}
