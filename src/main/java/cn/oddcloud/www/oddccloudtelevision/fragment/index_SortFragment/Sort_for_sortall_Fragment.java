package cn.oddcloud.www.oddccloudtelevision.fragment.index_SortFragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.MVC.Mode.MovieItemEntity;
import cn.oddcloud.www.oddccloudtelevision.MVC.View.HomePage_Recommend_CardView_Adapter;
import cn.oddcloud.www.oddccloudtelevision.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sort_for_sortall_Fragment extends Fragment implements View.OnClickListener {
    private LinearLayout for_noslip_linearlayout;
    private LinearLayout for_slip_linearlayout;
    private RecyclerView recyclerView;
    private TextView new_up, new_remen; //排序
    private TextView all_sort_type, juqing_sort_type, xiju_sort_type, kongbu_sort_type, kehuan_sort_type;//视频类型
    private TextView all_sort_country, meiguo_sort_country, ribeng_sort_country, dalu_sort_country, hanguo_sort_country;//视频产地
    private TextView all_sort_m, all_sort_movie, all_sort_tv;//视频是电视剧还是电影

   private    Drawable drawable;
    private   int drawable_select;
    private Boolean flag = false;
    private HomePage_Recommend_CardView_Adapter movie_adapter;

    private List<MovieItemEntity> products_movie = new ArrayList<MovieItemEntity>();



    private GestureDetector gestureDetector;

    public Sort_for_sortall_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort_for_sortall, container, false);
        initView(view);
        initData_for_sort_linearlayout(view);
        initEvent();
        initRecyclerViewEnvent();

        return view;
    }

    private void initRecyclerViewEnvent() {
        products_movie.add(new MovieItemEntity(R.drawable.testimage1, "青云志"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage2, "不良人"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage3, "幻城"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage4, "戒魔人"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage5, "诛仙"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage6, "灵主"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage7, "武庚纪"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage8, "秦时明月之君临天下"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage9, "不良人"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage10, "幻城"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage11, "戒魔人"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage12, "诛仙"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage13, "灵主"));
        movie_adapter = new HomePage_Recommend_CardView_Adapter(products_movie);
        recyclerView.setAdapter(movie_adapter);


    }


    private void initData_for_sort_linearlayout(View view) {
        new_up = (TextView) view.findViewById(R.id.new_up);
        new_remen = (TextView) view.findViewById(R.id.new_remen);
        all_sort_type = (TextView) view.findViewById(R.id.all_sort_type);
        juqing_sort_type = (TextView) view.findViewById(R.id.juqing_sort_type);
        xiju_sort_type = (TextView) view.findViewById(R.id.xiju_sort_type);
        kongbu_sort_type = (TextView) view.findViewById(R.id.kongbu_sort_type);
        kehuan_sort_type = (TextView) view.findViewById(R.id.kehuan_sort_type);
        all_sort_country = (TextView) view.findViewById(R.id.all_sort_country);
        meiguo_sort_country = (TextView) view.findViewById(R.id.meiguo_sort_country);
        ribeng_sort_country = (TextView) view.findViewById(R.id.ribeng_sort_country);
        dalu_sort_country = (TextView) view.findViewById(R.id.dalu_sort_country);
        hanguo_sort_country = (TextView) view.findViewById(R.id.hanguo_sort_country);
        all_sort_m = (TextView) view.findViewById(R.id.all_sort_m);
        all_sort_movie = (TextView) view.findViewById(R.id.all_sort_movie);
        all_sort_tv = (TextView) view.findViewById(R.id.all_sort_tv);
        new_up.setOnClickListener(this);
        new_remen.setOnClickListener(this);
        all_sort_type.setOnClickListener(this);
        juqing_sort_type.setOnClickListener(this);
        xiju_sort_type.setOnClickListener(this);
        kongbu_sort_type.setOnClickListener(this);
        kehuan_sort_type.setOnClickListener(this);
        all_sort_country.setOnClickListener(this);
        meiguo_sort_country.setOnClickListener(this);
        ribeng_sort_country.setOnClickListener(this);
        dalu_sort_country.setOnClickListener(this);
        hanguo_sort_country.setOnClickListener(this);
        all_sort_m.setOnClickListener(this);
        all_sort_movie.setOnClickListener(this);
        all_sort_tv.setOnClickListener(this);
        new_up.setTextColor(getResources().getColor(R.color.maincolor));
        all_sort_type.setTextColor(getResources().getColor(R.color.maincolor));
        all_sort_country.setTextColor(getResources().getColor(R.color.maincolor));
        all_sort_m.setTextColor(getResources().getColor(R.color.maincolor));

        new_up.setBackground(drawable);
        all_sort_type.setBackground(drawable);
        all_sort_country.setBackground(drawable);
        all_sort_m.setBackground(drawable);


    }

    private void initEvent() {
        for_noslip_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == false) {
                    for_slip_linearlayout.setVisibility(View.VISIBLE);
                    flag = true;
                } else {
                    for_slip_linearlayout.setVisibility(View.GONE);
                    flag = false;
                }


            }
        });


    }

    private void initView(View view) {
         drawable=getResources().getDrawable(R.drawable.yuanjiao);
        drawable_select=getResources().getColor(R.color.white);
        for_noslip_linearlayout = (LinearLayout) view.findViewById(R.id.for_no_slip_linearlayout);
        for_slip_linearlayout = (LinearLayout) view.findViewById(R.id.for_slip_linearlayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.sort_all_recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

    }

    private void initdefault1() {
        new_up.setTextColor(getResources().getColor(R.color.black));
        new_remen.setTextColor(getResources().getColor(R.color.black));
        new_up.setBackgroundColor(drawable_select);
        new_remen.setBackgroundColor(drawable_select);
    }

    private void initdefault2() {
        all_sort_type.setTextColor(getResources().getColor(R.color.black));
        juqing_sort_type.setTextColor(getResources().getColor(R.color.black));
        xiju_sort_type.setTextColor(getResources().getColor(R.color.black));
        kongbu_sort_type.setTextColor(getResources().getColor(R.color.black));
        kehuan_sort_type.setTextColor(getResources().getColor(R.color.black));

        all_sort_type.setBackgroundColor(drawable_select);
        juqing_sort_type.setBackgroundColor(drawable_select);
        xiju_sort_type.setBackgroundColor(drawable_select);
        kongbu_sort_type.setBackgroundColor(drawable_select);
        kehuan_sort_type.setBackgroundColor(drawable_select);
    }

    private void initdefault3() {
        all_sort_country.setTextColor(getResources().getColor(R.color.black));
        meiguo_sort_country.setTextColor(getResources().getColor(R.color.black));
        ribeng_sort_country.setTextColor(getResources().getColor(R.color.black));
        dalu_sort_country.setTextColor(getResources().getColor(R.color.black));
        hanguo_sort_country.setTextColor(getResources().getColor(R.color.black));


        all_sort_country.setBackgroundColor(drawable_select);
        meiguo_sort_country.setBackgroundColor(drawable_select);
        ribeng_sort_country.setBackgroundColor(drawable_select);
        dalu_sort_country.setBackgroundColor(drawable_select);
        hanguo_sort_country.setBackgroundColor(drawable_select);
    }

    private void initdefault4() {
        all_sort_m.setTextColor(getResources().getColor(R.color.black));
        all_sort_movie.setTextColor(getResources().getColor(R.color.black));
        all_sort_tv.setTextColor(getResources().getColor(R.color.black));

        all_sort_m.setBackgroundColor(drawable_select);
        all_sort_movie.setBackgroundColor(drawable_select);
        all_sort_tv.setBackgroundColor(drawable_select);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_up:
                initdefault1();
                new_up.setTextColor(getResources().getColor(R.color.maincolor));

                new_up.setBackground(drawable);
                break;
            case R.id.new_remen:
                initdefault1();
                new_remen.setTextColor(getResources().getColor(R.color.maincolor));
                new_remen.setBackground(drawable);
                break;
            case R.id.all_sort_type:
                initdefault2();
                all_sort_type.setTextColor(getResources().getColor(R.color.maincolor));
                all_sort_type.setBackground(drawable);
                break;
            case R.id.juqing_sort_type:
                initdefault2();
                juqing_sort_type.setTextColor(getResources().getColor(R.color.maincolor));
                juqing_sort_type.setBackground(drawable);
                break;
            case R.id.xiju_sort_type:
                initdefault2();
                xiju_sort_type.setTextColor(getResources().getColor(R.color.maincolor));
                xiju_sort_type.setBackground(drawable);
                break;
            case R.id.kongbu_sort_type:
                initdefault2();
                kongbu_sort_type.setTextColor(getResources().getColor(R.color.maincolor));
                kongbu_sort_type.setBackground(drawable);
                break;
            case R.id.kehuan_sort_type:
                initdefault2();
                kehuan_sort_type.setTextColor(getResources().getColor(R.color.maincolor));
                kehuan_sort_type.setBackground(drawable);
                break;
            case R.id.all_sort_country:
                initdefault3();
                all_sort_country.setTextColor(getResources().getColor(R.color.maincolor));
                all_sort_country.setBackground(drawable);
                break;
            case R.id.meiguo_sort_country:
                initdefault3();
                meiguo_sort_country.setTextColor(getResources().getColor(R.color.maincolor));
                meiguo_sort_country.setBackground(drawable);
                break;
            case R.id.ribeng_sort_country:
                initdefault3();
                ribeng_sort_country.setTextColor(getResources().getColor(R.color.maincolor));
                ribeng_sort_country.setBackground(drawable);
                break;
            case R.id.dalu_sort_country:
                initdefault3();
                dalu_sort_country.setTextColor(getResources().getColor(R.color.maincolor));
                dalu_sort_country.setBackground(drawable);
                break;

            case R.id.hanguo_sort_country:
                initdefault3();
                hanguo_sort_country.setTextColor(getResources().getColor(R.color.maincolor));
                hanguo_sort_country.setBackground(drawable);
                break;
            case R.id.all_sort_m:
                initdefault4();
                all_sort_m.setTextColor(getResources().getColor(R.color.maincolor));
                all_sort_m.setBackground(drawable);
                break;
            case R.id.all_sort_movie:
                initdefault4();
                all_sort_movie.setTextColor(getResources().getColor(R.color.maincolor));
                all_sort_movie.setBackground(drawable);
                break;
            case R.id.all_sort_tv:
                initdefault4();
                all_sort_tv.setTextColor(getResources().getColor(R.color.maincolor));
                all_sort_tv.setBackground(drawable);
                break;

        }
    }





}
