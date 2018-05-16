package com.marcus.umengshare;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.Toast;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class ShareDialog extends Dialog {

    private GridView gridView;
    private ShareAdapter shareAdapter;

    public ShareDialog(@NonNull Context context) {
        this(context, R.style.ShareDialogTheme);
    }

    public ShareDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    private void initView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_share_dialog, null);
        setContentView(view);
        gridView = view.findViewById(R.id.share_grid);
        shareAdapter = new ShareAdapter(context);
        shareAdapter.setUmShareListener(shareListener);
        gridView.setAdapter(shareAdapter);
        setDialogWindowParams(context);
    }

    private void setDialogWindowParams(Context context) {
        Window dialogWindow = this.getWindow();
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.height = (int) (display.getHeight() * 0.6);
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getContext(), "成功了", Toast.LENGTH_LONG).show();
            Log.e("UMShare","onResult : "+platform.toString());
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getContext(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("UMShare","onError : "+platform.toString());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getContext(), "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
