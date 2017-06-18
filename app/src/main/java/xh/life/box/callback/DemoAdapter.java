package xh.life.box.callback;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import xh.life.box.R;
import xh.life.box.model.MZModel;

/**
 * Created by Finder丶畅畅 on 2017/6/18 09:54
 * QQ群481606175
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.BaseViewHolder> {
    private ArrayList<MZModel> dataList = new ArrayList<>();

    public void replaceAll(List<MZModel> list) {
        dataList.clear();
        if (list != null && list.size() > 0) {
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public DemoAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mz, parent, false));
    }

    @Override
    public void onBindViewHolder(DemoAdapter.BaseViewHolder holder, int position) {
        holder.setData(dataList.get(position).getSthumbUrl(), dataList.get(position).getSthumb_height(), dataList.get(position).getSthumb_width());
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        void setData(Object data, int height, int width) {
        }
    }

    private class OneViewHolder extends BaseViewHolder {
        private ImageView ivImage;

        public OneViewHolder(View view) {
            super(view);
            ivImage = (ImageView) view.findViewById(R.id.iv);
        }

        @Override
        void setData(Object data, int hh, int ww) {
            if (data != null) {
                String text = (String) data;
                Glide.with(itemView.getContext()).load(text).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.item_error).crossFade().into(ivImage);
                int width = ((Activity) ivImage.getContext()).getWindowManager().getDefaultDisplay().getWidth();
                ViewGroup.LayoutParams params = ivImage.getLayoutParams();
                //设置图片的相对于屏幕的宽高比
                params.width = width / 2;
                if (ww != 0) {
                    params.height = hh * width / 2 / ww;
                }
                ivImage.setLayoutParams(params);
            }
        }
    }
}
