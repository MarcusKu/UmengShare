package com.marcus.umengshare;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

public class ShareAdapter extends BaseAdapter {

    private List<ShareItem> shareItems = new ArrayList<>();
    private UMShareListener umShareListener;
    private Context context;

    public ShareAdapter(Context context) {
        this.context = context;
        createItem();
    }

    @Override
    public int getCount() {
        return shareItems.isEmpty() ? 0 : shareItems.size();
    }

    @Override
    public Object getItem(int position) {
        return shareItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = new LinearLayout(parent.getContext());
            ImageView imageView = new ImageView(parent.getContext());
            ((LinearLayout) convertView).addView(imageView);
            ((LinearLayout) convertView).setGravity(Gravity.CENTER);
            viewHolder = new ViewHolder(imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(shareItems.get(position).getResId());
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UmShareHelper.shareLink(ShareAdapter.this.context, umShareListener,"友盟分享测试数据", shareItems.get(position));
            }
        });
        return convertView;
    }

    public void setUmShareListener(UMShareListener umShareListener) {
        this.umShareListener = umShareListener;
    }

    private class ViewHolder {
        private ImageView imageView;

        public ViewHolder(ImageView imageView) {
            this.imageView = imageView;
        }
    }

    private void createItem() {

        int icons[] = new int[]{R.drawable.umeng_socialize_sina, R.drawable.umeng_socialize_qq,
                R.drawable.umeng_socialize_qzone, R.drawable.umeng_socialize_wechat,
                R.drawable.umeng_socialize_wxcircle};

        SHARE_MEDIA shareMedia[] = new SHARE_MEDIA[]{SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE};

        for (int i = 0; i < icons.length; i++) {
            shareItems.add(new ShareItem(icons[i], shareMedia[i]));
        }
    }

    class ShareItem {
        private int resId;
        private SHARE_MEDIA shareMedia;

        public ShareItem() {
        }

        public ShareItem(int resId, SHARE_MEDIA shareMedia) {
            this.resId = resId;
            this.shareMedia = shareMedia;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public SHARE_MEDIA getShareMedia() {
            return shareMedia;
        }

        public void setShareMedia(SHARE_MEDIA shareMedia) {
            this.shareMedia = shareMedia;
        }
    }
}
