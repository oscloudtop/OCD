package cn.oddcloud.www.oddccloudtelevision;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.fragment.HomePageFragment;
import cn.oddcloud.www.oddccloudtelevision.fragment.Index_SortFragment;
import cn.oddcloud.www.oddccloudtelevision.fragment.Mine_Fragment;
import cn.oddcloud.www.oddccloudtelevision.fragment.TVLiveFragment;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class NavMainActivity extends FragmentActivity  implements HomePageFragment.OnFragmentInteractionListener,TVLiveFragment.OnFragmentInteractionListener,Index_SortFragment.OnFragmentInteractionListener,Mine_Fragment.OnFragmentInteractionListener{
    private TextView toolbar_tv_title;
    private ImageView search_movie;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ntb);
        initUI();
    }
    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        toolbar_tv_title= (TextView) findViewById(R.id.toolbar_tv_title);
        search_movie= (ImageView) findViewById(R.id.search_movie);
        final List<Fragment> indexlist_fragment=new ArrayList<>();
        Fragment homepage_fg= HomePageFragment.newInstance("page0","0");
        Fragment TVLive_fg=TVLiveFragment.newInstance("page1","1");
        Fragment Index_Sort_fg=Index_SortFragment.newInstance("page2","2");
        Fragment Mine_fg=Mine_Fragment.newInstance("page3","3");
        indexlist_fragment.add(homepage_fg);
        indexlist_fragment.add(TVLive_fg);
        indexlist_fragment.add(Index_Sort_fg);
        indexlist_fragment.add(Mine_fg);


        //跳转搜索
        search_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NavMainActivity.this, cn.oddcloud.www.oddccloudtelevision.Floatingsearch.SearchActivity.class);
                startActivity(intent);
            }
        });


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return indexlist_fragment.size();
            }


            @Override
            public Fragment getItem(int position) {
                return indexlist_fragment.get(position);
            }


        });
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);
        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        navigationTabBar.setBgColor(R.color.maincolor);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("首页")
                        .badgeTitle("有更新哦")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("直播")
                        .badgeTitle("还没看哦")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("分类")
                        .badgeTitle("有最新哦")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("我的")
                        .badgeTitle("个人中心")
                        .build()
        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_fifth),
//                        Color.parseColor(colors[4]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
//                        .title("云播中心")
//                        .badgeTitle("777")
//                        .build()
//        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();

                if(position==0)
                {
                    toolbar_tv_title.setText("首页");
                }else  if(position==1){
                    toolbar_tv_title.setText("直播");
                }else  if(position==2){
                    toolbar_tv_title.setText("分类");
                }else  if(position==3){
                    toolbar_tv_title.setText("我的");
                }

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
