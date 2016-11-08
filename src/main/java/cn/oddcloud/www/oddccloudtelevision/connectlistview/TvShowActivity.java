package cn.oddcloud.www.oddccloudtelevision.connectlistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.R;

public class TvShowActivity extends AppCompatActivity {

    private ListView listViewFirst;
    private ListView listViewSecond;


    private List<String> firstList;
    private List<List> secondList;


    private SearchAreaFirstAdapter firstAdapter;
    private SearchAreaSecondAdapter secondAdapter;

    /**
     * 二级菜单名称数组
     **/
    String[][] secondNameArray = {
            {"不限", "东城", "西城", "朝阳", "海淀", "丰台", "石景山", "通州", "昌平", "大兴", "亦庄开发区", "顺义", "房山", "门头沟", "平谷", "怀柔", "密云", "延庆"},
            {"1号线", "2号线", "4号大兴线", "5号线", "6号线", "7号线", "8号线", "9号线", "10号线", "13号线", "14号线", "15号线", "八通线", "亦庄线", "昌平线", "房山线", "机场线"},
            {"不限", "1千米内", "2千米内", "3千米内"}
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewactivity_main);

        initView();

        initData();

        initFirstListView();

    }

    private void initData() {
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();


        firstList.add("央视");
        firstList.add("卫视");
        firstList.add("地方");
        firstList.add("体育");
        firstList.add("影视");

        for (int i = 0; i < secondNameArray.length; i++) {
            List<String> list = new ArrayList<>();
            String[] strings = secondNameArray[i];
            for (int j = 0; j < strings.length; j++) {
                String name = secondNameArray[i][j];
                list.add(name);
            }
            secondList.add(list);
        }

    }


    private void initView() {

        listViewFirst = (ListView) findViewById(R.id.listViewFirst);
        listViewSecond = (ListView) findViewById(R.id.listViewSecond);


    }

    private void initFirstListView() {
        firstAdapter = new SearchAreaFirstAdapter(this, firstList);
        firstAdapter.setSelectPosition(0);
        listViewFirst.setAdapter(firstAdapter);
        initSecondListView(0);

        listViewFirst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updataListView(position);
            }
        });
    }


    private void initSecondListView(int position) {
        secondAdapter = new SearchAreaSecondAdapter(this, secondList.get(position));
        secondAdapter.setSelectPosition(position);
        listViewSecond.setAdapter(secondAdapter);

        listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updataListView2(position);
            }
        });


    }



    private void updataListView(int position) {
        firstAdapter.setSelectPosition(position);
        firstAdapter.notifyDataSetChanged();
        //更新第二ListView
        secondAdapter.setDatas(secondList.get(position));
        updataListView2(0);
    }

    private void updataListView2(int position) {
        secondAdapter.setSelectPosition(position);
        secondAdapter.notifyDataSetChanged();

    }



}
