package com.sample.mysamples.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.NetworkImageView;
import com.sample.mysamples.R;
import com.sample.mysamples.data.entity.ListData;
import com.sample.mysamples.model.app.AppController;

import java.util.ArrayList;

/**
 * Created by truong.tam on 15/06/25.
 */
public class MyBaseAdapter extends BaseAdapter {
    private static final String TAG = "MyAdapter";

    private ArrayList<ListData> mMyList = new ArrayList<ListData>();
    private LayoutInflater mInflater;
    private Context mContext;
    private ImageLoader mImageLoader = AppController.getInstance().getImageLoader();

    public MyBaseAdapter(Context context, ArrayList<ListData> object) {
        this.mMyList = object;
        this.mContext = context;
        mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: " + mMyList.size());
        return mMyList.size();
    }

    @Override
    public ListData getItem(int position) {
        Log.d(TAG, "getItem(position): " + String.valueOf(position));
        return mMyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder mViewHolder;

        if (mImageLoader == null) mImageLoader = AppController.getInstance().getImageLoader();

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_view_item, parent, false);

            NetworkImageView imageView = (NetworkImageView)convertView.findViewById(R.id.image);
            TextView textView = (TextView)convertView.findViewById(R.id.message);

            mViewHolder = new MyViewHolder();
            mViewHolder.ivImg = imageView;
            mViewHolder.tvMessage = textView;

            convertView.setTag(mViewHolder);
            Log.d(TAG, "convertView == null");
        } else {
            Log.d(TAG, "convertView != null");
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListData currentListData = getItem(position);

        mViewHolder.tvMessage.setText(currentListData.getMessage());
        String imageUrl = currentListData.getImgUrl();
        Log.d(TAG, "imageUrl: " + imageUrl);
        Log.d(TAG, "Message: " + currentListData.getMessage());

        // リクエストのキャンセル処理
        ImageContainer imageContainer = (ImageContainer)mViewHolder.ivImg.getTag();
        if (imageContainer != null) {
            Log.d(TAG, "imageContainer != null");
            imageContainer.cancelRequest();
        }

        mViewHolder.ivImg.setImageUrl(imageUrl, mImageLoader);

        return convertView;
    }

    public static class MyViewHolder {
        TextView tvMessage;
        NetworkImageView ivImg;
    }

}
