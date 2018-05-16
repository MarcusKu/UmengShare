package com.marcus.umengshare;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class UmengShareApp extends Application {

    public UmengShareApp() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化友盟分享
//        UMConfigure.init(this, "", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //以前申请的
        UMConfigure.init(this, "", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //设置友盟的Log开启
        UMConfigure.setLogEnabled(true);
        //设置友盟的Log加密
        UMConfigure.setEncryptEnabled(true);

        PlatformConfig.setSinaWeibo("", "", "http://sns.whalecloud.com/sina2/callback");
//        PlatformConfig.setSinaWeibo("1360405150", "8d163c907d27a6d84cc0f13845aba9a5", "http://sns.whalecloud.com/sina2/callback");
        PlatformConfig.setQQZone("", "");
        PlatformConfig.setWeixin("", "");
    }

}
