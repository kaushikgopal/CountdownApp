package com.cmuse13.countdownapp.countdownmodule.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class MyBaseAdapter<T>
        extends BaseAdapter {

    private final static int ITEM_VIEW_TYPE_COUNT = 2;
    private final static int ITEM_VIEW_TYPE_HEADER = 0;
    private final static int ITEM_VIEW_TYPE_ROW = 1;

    private List<T> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public MyBaseAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size() + ITEM_VIEW_TYPE_COUNT - 1;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_COUNT;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return ITEM_VIEW_TYPE_HEADER;
            default:
                return ITEM_VIEW_TYPE_ROW;
        }
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position - ITEM_VIEW_TYPE_COUNT - 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        switch (getItemViewType(position)) {
            case ITEM_VIEW_TYPE_HEADER:
                return getHeaderRow(convertView, parent);
            default:
                return getListRow(convertView, parent);
        }
    }

    private View getHeaderRow(View convertView, ViewGroup parent) {
//        if (convertView == null)
//            convertView = getInflater().inflate(R.layout.some_item, parent, false);

        return convertView;
    }

    private View getListRow(View convertView, ViewGroup parent) {
        //        if (convertView == null)
        //            convertView = getInflater().inflate(R.layout.some_item, parent, false);

        return convertView;
    }


    private LayoutInflater getInflater() {
        if (mInflater == null)
            mInflater = LayoutInflater.from(mContext);

        return mInflater;
    }
}
