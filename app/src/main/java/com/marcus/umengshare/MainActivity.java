package com.marcus.umengshare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private List<String> permissionList = new ArrayList<>();

    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.um_share_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //权限请求
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    permissionList.clear();
                    for (String permission : permissions) {
                        //系统没有给定当前权限
                        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                            permissionList.add(permission);
                        }
                    }
                    if (!permissionList.isEmpty()) {
                        ActivityCompat.requestPermissions(MainActivity.this, permissionList.toArray(new String[permissionList.size()]), 123);
                    } else {
                        shareContent();
                    }
                } else {
                    shareContent();
                }
            }
        });
    }

    private ShareDialog shareDialog = null;

    private void shareContent() {
        if (null == shareDialog) {
            shareDialog = new ShareDialog(this);
        }
        shareDialog.show();

        //UMImage image = new UMImage(MainActivity.this, R.drawable.umeng_socialize_fav);
       /* new ShareAction(MainActivity.this)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .withText("hello")//分享内容
                .withMedia(image)
                .setCallback(shareListener)//回调监听器
                .share();*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Toast.makeText(this, "requestCode : " + requestCode, Toast.LENGTH_SHORT).show();
        if (requestCode == 123) {
            permissionList.clear();
            for (int i = 0; i < grantResults.length; i++) {
                int granResult = grantResults[i];
                if (granResult != PackageManager.PERMISSION_GRANTED) {
                    //如果一个应用之前请求过这个权限，然后用户给拒绝了，那么这个方法就会返回 true
                    //如果之前用户拒绝了这个权限，且在权限请求的系统对话框中勾选了 Don't ask again 选项，那么这个方法就会返回 false
                    //如果一个设备的机制不允许你的app有这个权限，那么此时这个方法也会返回 false
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[i])) {
                        permissionList.add(permissions[i]);
                    } else {
//                        Toast.makeText(MainActivity.this, "请前往系统设置，开启权限 : " + permissions[i], Toast.LENGTH_SHORT).show();
                    }
                }
            }
            //请求的权限中还有没有给权限的
            if (!permissionList.isEmpty()) {
                ActivityCompat.requestPermissions(MainActivity.this, permissionList.toArray(new String[permissionList.size()]), 123);
            } else {
                shareContent();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    //用于QQ分享
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
