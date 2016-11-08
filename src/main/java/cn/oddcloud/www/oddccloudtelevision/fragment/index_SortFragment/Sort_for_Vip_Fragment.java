package cn.oddcloud.www.oddccloudtelevision.fragment.index_SortFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.MVC.Mode.MovieItemEntity;
import cn.oddcloud.www.oddccloudtelevision.MVC.View.For_Vip_ListView_Adapter;
import cn.oddcloud.www.oddccloudtelevision.MVC.View.Sort_for_Vip.Show_for_vip_Activity;
import cn.oddcloud.www.oddccloudtelevision.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sort_for_Vip_Fragment extends Fragment {

    private ListView for_vip_listview;
    private List<MovieItemEntity> movieItemEntities;
    public Sort_for_Vip_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort_for__vip, container, false);
        for_vip_listview = (ListView) view.findViewById(R.id.for_vip_listview);
        initData();
        For_Vip_ListView_Adapter for_vip_listView_adapter=new For_Vip_ListView_Adapter(getContext(),movieItemEntities);
        for_vip_listview.setAdapter(for_vip_listView_adapter);
        for_vip_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent=new Intent(getContext(), Show_for_vip_Activity.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });

        return view;
    }

    private void initData() {
        movieItemEntities=new ArrayList<>();
        MovieItemEntity movieItemEntity_foriqiyi=new MovieItemEntity(R.drawable.iqiyi_logo,"爱奇艺vip免费看");
        movieItemEntities.add(movieItemEntity_foriqiyi);
        MovieItemEntity movieItemEntity_formgtv=new MovieItemEntity(R.drawable.mgtv_logo,"芒果vip免费看");
        movieItemEntities.add(movieItemEntity_formgtv);
        MovieItemEntity movieItemEntity_forqq=new MovieItemEntity(R.drawable.qq_logo,"腾讯vip免费看");
        movieItemEntities.add(movieItemEntity_forqq);
        MovieItemEntity movieItemEntity_forsohu=new MovieItemEntity(R.drawable.sohu_logo,"搜狐vip免费看");
        movieItemEntities.add(movieItemEntity_forsohu);
        MovieItemEntity movieItemEntity_fortudou=new MovieItemEntity(R.drawable.tudou_logo,"土豆vip免费看");
        movieItemEntities.add(movieItemEntity_fortudou);
    }

}
