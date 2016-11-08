package cn.oddcloud.www.oddccloudtelevision.MVC.View.Sort_for_Vip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.MVC.Mode.MovieItemEntity;
import cn.oddcloud.www.oddccloudtelevision.MVC.View.HomePage_Recommend_CardView_Adapter;
import cn.oddcloud.www.oddccloudtelevision.R;

public class Show_for_vip_Activity extends AppCompatActivity {
  private Button movie_item,tv_item;
private  int falg=0;
    private RecyclerView show_for_vip_recyclerview;
    private List<MovieItemEntity> products_movie=new ArrayList<MovieItemEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_for_vip);
       int position=getIntent().getIntExtra("position",7);


        movie_item= (Button) findViewById(R.id.movie_item);
        tv_item= (Button) findViewById(R.id.tv_item);
        show_for_vip_recyclerview= (RecyclerView) findViewById(R.id.show_for_vip_recyclerview);
        StaggeredGridLayoutManager movie_staggeredGridLayoutManager=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        movie_staggeredGridLayoutManager.setAutoMeasureEnabled(true);

        show_for_vip_recyclerview.setLayoutManager(movie_staggeredGridLayoutManager);
        show_for_vip_recyclerview.setHasFixedSize(true);
        show_for_vip_recyclerview.setNestedScrollingEnabled(false);


        initData();
        Log.d("Position","@@"+position);

        tv_item.setBackgroundColor(getResources().getColor(R.color.background));
        tv_item.setTextColor(getResources().getColor(R.color.black));
        movie_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
        movie_item.setTextColor(getResources().getColor(R.color.background));


        if (position==0)//爱奇艺
        {
            DoIqiyi();
        }else if (position==1)//芒果tv
        {
           DoMgtv();
        }
        else if (position==2)//腾讯视频
        {

            DoQQ();

        }
        else if (position==3)//搜狐
        {

            DoSohu();
        }else if (position==4)//土豆
        {

            DoToudu();
        }
        else {
            Log.d("position is error","选择位置出现错误");
        }
    }

    private void DoToudu() {
        Collections.shuffle(products_movie);
        final HomePage_Recommend_CardView_Adapter movie_adapter=new HomePage_Recommend_CardView_Adapter(products_movie);
        movie_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                tv_item.setBackgroundColor(getResources().getColor(R.color.background));
                tv_item.setTextColor(getResources().getColor(R.color.black));
                movie_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                movie_item.setTextColor(getResources().getColor(R.color.background));
                movie_adapter.notifyDataSetChanged();
            }
        });
        tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                movie_item.setBackgroundColor(getResources().getColor(R.color.background));
                movie_item.setTextColor(getResources().getColor(R.color.black));
                tv_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                tv_item.setTextColor(getResources().getColor(R.color.background));
                movie_adapter.notifyDataSetChanged();
            }
        });

        movie_adapter.notifyDataSetChanged();
        show_for_vip_recyclerview.setAdapter(movie_adapter);
    }

    private void DoSohu() {
        Collections.shuffle(products_movie);
        final HomePage_Recommend_CardView_Adapter movie_adapter=new HomePage_Recommend_CardView_Adapter(products_movie);
        movie_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                tv_item.setBackgroundColor(getResources().getColor(R.color.background));
                tv_item.setTextColor(getResources().getColor(R.color.black));
                movie_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                movie_item.setTextColor(getResources().getColor(R.color.background));
                movie_adapter.notifyDataSetChanged();
            }
        });
        tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                movie_item.setBackgroundColor(getResources().getColor(R.color.background));
                movie_item.setTextColor(getResources().getColor(R.color.black));
                tv_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                tv_item.setTextColor(getResources().getColor(R.color.background));
                movie_adapter.notifyDataSetChanged();
            }
        });

        movie_adapter.notifyDataSetChanged();
        show_for_vip_recyclerview.setAdapter(movie_adapter);
    }

    private void DoQQ() {
        Collections.shuffle(products_movie);
        final HomePage_Recommend_CardView_Adapter movie_adapter=new HomePage_Recommend_CardView_Adapter(products_movie);
        movie_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                tv_item.setBackgroundColor(getResources().getColor(R.color.background));
                tv_item.setTextColor(getResources().getColor(R.color.black));
                movie_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                movie_item.setTextColor(getResources().getColor(R.color.background));
                movie_adapter.notifyDataSetChanged();
            }
        });
        tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                movie_item.setBackgroundColor(getResources().getColor(R.color.background));
                movie_item.setTextColor(getResources().getColor(R.color.black));
                tv_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                tv_item.setTextColor(getResources().getColor(R.color.background));
                movie_adapter.notifyDataSetChanged();
            }
        });

        movie_adapter.notifyDataSetChanged();
        show_for_vip_recyclerview.setAdapter(movie_adapter);
    }

    private void DoMgtv() {
        Collections.shuffle(products_movie);
        final HomePage_Recommend_CardView_Adapter movie_adapter=new HomePage_Recommend_CardView_Adapter(products_movie);
        movie_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                tv_item.setBackgroundColor(getResources().getColor(R.color.background));
                tv_item.setTextColor(getResources().getColor(R.color.black));
                movie_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                movie_item.setTextColor(getResources().getColor(R.color.background));
                movie_adapter.notifyDataSetChanged();
            }
        });
        tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                movie_item.setBackgroundColor(getResources().getColor(R.color.background));
                movie_item.setTextColor(getResources().getColor(R.color.black));
                tv_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                tv_item.setTextColor(getResources().getColor(R.color.background));
                movie_adapter.notifyDataSetChanged();
            }
        });

        movie_adapter.notifyDataSetChanged();
        show_for_vip_recyclerview.setAdapter(movie_adapter);
    }

    private void DoIqiyi() {

        Collections.shuffle(products_movie);

        final HomePage_Recommend_CardView_Adapter movie_adapter=new HomePage_Recommend_CardView_Adapter(products_movie);
        movie_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                tv_item.setBackgroundColor(getResources().getColor(R.color.background));
                tv_item.setTextColor(getResources().getColor(R.color.black));
                movie_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                movie_item.setTextColor(getResources().getColor(R.color.background));
                falg=0;
                movie_adapter.notifyDataSetChanged();

            }
        });




        tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(products_movie);
                movie_item.setBackgroundColor(getResources().getColor(R.color.background));
                movie_item.setTextColor(getResources().getColor(R.color.black));
                tv_item.setBackgroundColor(getResources().getColor(R.color.switch_color));
                tv_item.setTextColor(getResources().getColor(R.color.background));
                falg=1;
                movie_adapter.notifyDataSetChanged();

            }
        });

        movie_adapter.notifyDataSetChanged();
        movie_adapter.setOnItemClickListener(new HomePage_Recommend_CardView_Adapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {

                if (falg==0)
                startActivity(new Intent(Show_for_vip_Activity.this,VIP_Play_Activity.class));
                else {
                    startActivity(new Intent(Show_for_vip_Activity.this,Tv_list_Activity.class));
                }
            }
        });
        show_for_vip_recyclerview.setAdapter(movie_adapter);

    }

    private void initData() {
        products_movie.add(new MovieItemEntity(R.drawable.testimage1,"青云志"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage2,"不良人"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage3,"幻城"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage4,"戒魔人"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage5,"诛仙"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage6,"灵主"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage7,"武庚纪"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage8,"秦时明月"));

    }
}
