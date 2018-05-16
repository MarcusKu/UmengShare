package com.marcus.umengshare;

import android.app.Activity;
import android.content.Context;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class UmShareHelper {

    public static void shareSingleImage(Context context, UMShareListener shareListener, String text, ShareAdapter.ShareItem shareItem) {
        UMImage image = new UMImage(context, shareItem.getResId());
        new ShareAction((Activity) context)
                .setPlatform(shareItem.getShareMedia())
                .withText(text)
                .withMedia(image)
                .setCallback(shareListener)
                .share();

    }

    public static void shareMultipleImage(Context context, UMShareListener shareListener, String text, ShareAdapter.ShareItem shareItem) {
        UMImage image = new UMImage(context, shareItem.getResId());
        new ShareAction((Activity) context)
                .setPlatform(shareItem.getShareMedia())
                .withText(text)
                .withMedia(image)
                .setCallback(shareListener)
                .share();
    }

    public static void shareLink(Context context, UMShareListener shareListener, String text, ShareAdapter.ShareItem shareItem) {
        UMImage image = new UMImage(context, shareItem.getResId());
        UMWeb web = new UMWeb("https://img.iplaysoft.com/a3-mzstatic/us/r30/Purple111/v4/15/bf/b8/15bfb8a2-b83e-2585-7cab-de3726659287/screen696x696.jpeg");
        web.setTitle(text);
        web.setThumb(image);
        web.setDescription(text);
        new ShareAction((Activity) context)
                .setPlatform(shareItem.getShareMedia())
                .withMedia(web)
                .setCallback(shareListener)
                .share();
    }


}
