package cn.oddcloud.www.oddccloudtelevision.MVC.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.MVC.Mode.MovieItemEntity;
import cn.oddcloud.www.oddccloudtelevision.R;

/**
 * Created by wangyujie on 2016/11/7.
 */
public class For_Vip_ListView_Adapter extends BaseAdapter {
    private Context context;
    private List<MovieItemEntity> strings;
    public For_Vip_ListView_Adapter(Context context, List<MovieItemEntity> strings) {
        this.context=context;
        this.strings=strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.for_vip_card_item_layout,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.forvipgriditem_image);
        TextView textView= (TextView) view.findViewById(R.id.forvipgriditem_title);
        textView.setText(strings.get(position).getTitle());
        imageView.setImageDrawable(context.getResources().getDrawable(strings.get(position).getImg()));
        return view;
    }
}
