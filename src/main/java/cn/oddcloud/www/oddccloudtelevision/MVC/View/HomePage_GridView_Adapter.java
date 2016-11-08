package cn.oddcloud.www.oddccloudtelevision.MVC.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.oddcloud.www.oddccloudtelevision.R;
import cn.oddcloud.www.oddccloudtelevision.connectlistview.TvShowActivity;


/**
 * Created by wangyujie on 2016/11/3.
 */
public class HomePage_GridView_Adapter extends BaseAdapter {
    //上下文对象
    private Context context;
    //图片数组
    private Integer[] imgs = {
            R.drawable.category1, R.drawable.category2, R.drawable.category3,
            R.drawable.category4, R.drawable.category5, R.drawable.category6,
            R.drawable.category7, R.drawable.category8, R.drawable.category9,
            R.drawable.category10
    };

    private String[] titles={
      "电视剧","电影","综艺","动漫","少儿","资讯","娱乐","游戏","科技","搜索"
    };
    public HomePage_GridView_Adapter(Context context){
        this.context = context;
    }
    public int getCount() {
        return imgs.length;
    }

    public Object getItem(int item) {
        return item;
    }

    public long getItemId(int id) {
        return id;
    }

    //创建View方法
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView textView;
        View view= LayoutInflater.from(context).inflate(R.layout.homepage_grid_item_layout,null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, TvShowActivity.class));

            }
        });
        imageView= (ImageView) view.findViewById(R.id.homepagegriditem_image);
        textView= (TextView) view.findViewById(R.id.homepagegriditem_title);
        textView.setText(titles[position]);
        imageView.setImageResource(imgs[position]);//为ImageView设置图片资源
        return view;
    }
}
