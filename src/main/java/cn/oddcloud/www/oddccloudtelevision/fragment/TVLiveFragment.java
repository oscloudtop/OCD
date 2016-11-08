package cn.oddcloud.www.oddccloudtelevision.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.oddcloud.www.oddccloudtelevision.Aplayer.FilePickerActivity;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.PlayerActivity;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.APlayerParam;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.Content;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.util.PreferencesAttribute;
import cn.oddcloud.www.oddccloudtelevision.NavMainActivity;
import cn.oddcloud.www.oddccloudtelevision.R;
import cn.oddcloud.www.oddccloudtelevision.connectlistview.SearchAreaFirstAdapter;
import cn.oddcloud.www.oddccloudtelevision.connectlistview.SearchAreaSecondAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TVLiveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TVLiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TVLiveFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listViewFirst;
    private ListView listViewSecond;


    private List<String> firstList;
    private List<List> secondList;

    private static final String CUR_DIR_PATH = "CurDirPath";
    private static final String DEBUG_TAG = "mainActivity";
    private String mCurDirPath;


    private SearchAreaFirstAdapter firstAdapter;
    private SearchAreaSecondAdapter secondAdapter;

    /**
     * 二级菜单名称数组
     **/
    String[][] secondNameArray = {
            {"CCTV-1", "CCTV-2", "CCTV-3", "CCTV-4", "CCTV-5", "CCTV-6", "CCTV-7", "CCTV-8", "CCTV-9", "CCTV-10", "CCTV-11", "CCTV-12", "CCTV-13", "CCTV-14", "CCTV-15"},
            {"北京卫视", "山东卫视", "东方卫视", "黑龙江卫视", "辽宁卫视", "天津卫视", "深圳卫视", "广东卫视", "浙江卫视", "深圳卫视", "广东卫视", "兵团卫视"},
            {"精品体育", "健康有约", "军事评论", "金牌综艺","精品记录","精品综艺","精品电影","精品大剧","古装剧场","家庭剧场"}
    };


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TVLiveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TVLiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TVLiveFragment newInstance(String param1, String param2) {
        TVLiveFragment fragment = new TVLiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurDirPath = getDefaultDir(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.listviewactivity_main, container, false);

        initView(view);

        initData();

        initFirstListView();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void initView(View view) {

        listViewFirst = (ListView) view.findViewById(R.id.listViewFirst);
        listViewSecond = (ListView)view. findViewById(R.id.listViewSecond);


    }

    private void initData() {
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();


        firstList.add("央视");
        firstList.add("卫视");
        firstList.add("综合");


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


    private void initFirstListView() {
        firstAdapter = new SearchAreaFirstAdapter(getContext(), firstList);
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
        secondAdapter = new SearchAreaSecondAdapter(getContext(), secondList.get(position));
        secondAdapter.setSelectPosition(position);
        listViewSecond.setAdapter(secondAdapter);

        listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                Map<String, ?> peferences = sharedPreferences.getAll();
                Map<String, String> pramMap  = PreferencesAttribute.getPreferences(peferences);
                APlayerParam aPlayerParam = new APlayerParam("http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdcctv01/.m3u8", pramMap);

                Intent intent = new Intent(getContext(), PlayerActivity.class);
                intent.putExtra(Content.PLAYER_PARAM, aPlayerParam);
                startActivity(intent);
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


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(CUR_DIR_PATH, mCurDirPath);
        super.onSaveInstanceState(savedInstanceState);
        Log.e(DEBUG_TAG, "Save State");
    }
    private String getDefaultDir(Bundle savedInstanceState)
    {
        String defaultPath;
        if (savedInstanceState != null) {
            defaultPath = savedInstanceState.getString(CUR_DIR_PATH, Content.PATH_ROOT);
        }
        else
        {
            defaultPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        return defaultPath;
    }


}


