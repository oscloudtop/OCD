package cn.oddcloud.www.oddccloudtelevision.MVC.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.MVC.Mode.MovieItemEntity;
import cn.oddcloud.www.oddccloudtelevision.R;

/**
 * Created by wangyujie on 2016/11/3.
 */
public class HomePage_Recommend_CardView_Adapter extends RecyclerView.Adapter<HomePage_Recommend_CardView_Adapter.RecVH> implements View.OnClickListener {
    List<MovieItemEntity> products=new ArrayList<MovieItemEntity>();
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    //构造方法传入数据
    public HomePage_Recommend_CardView_Adapter(List<MovieItemEntity>products){
        this.products=products;
    }

    //创造ViewHolder
    @Override
    public RecVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //把item的Layout转化成View传给ViewHolder
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_recommend_cardview_item,parent,false);
        view.setOnClickListener(this);
        return new RecVH(view);
    }

    @Override
    public void onBindViewHolder(RecVH holder, int position) {
        holder.tvTitle.setText(products.get(position).getTitle());
        holder.ivPic.setImageResource(products.get(position).getImg());
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    //ViewHolder绑定控件
    public class RecVH extends RecyclerView.ViewHolder{
        ImageView ivPic;
        TextView tvTitle;
        public RecVH(View itemView) {
            super(itemView);
            ivPic= (ImageView) itemView.findViewById(R.id.ivPic);
            tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
