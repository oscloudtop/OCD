package cn.oddcloud.www.oddccloudtelevision.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.MVC.Mode.AdvertiseEntity;
import cn.oddcloud.www.oddccloudtelevision.MVC.Mode.MovieItemEntity;
import cn.oddcloud.www.oddccloudtelevision.MVC.View.HomePage_GridView_Adapter;
import cn.oddcloud.www.oddccloudtelevision.MVC.View.HomePage_Recommend_CardView_Adapter;
import cn.oddcloud.www.oddccloudtelevision.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomePageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment implements XBanner.XBannerAdapter  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private XBanner mXBanner;
    private List<AdvertiseEntity.OthersBean> mOthersList;

    private GridView gridView;

    //推荐电影item
    private RecyclerView recommend_movie_recyclerView;
    private List<MovieItemEntity> products_movie=new ArrayList<MovieItemEntity>();
    private RecyclerView recommend_tv_recyclerView;
    private List<MovieItemEntity> products_tv=new ArrayList<MovieItemEntity>();


    private OnFragmentInteractionListener mListener;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mXBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mXBanner.stopAutoPlay();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_page, container, false);

       // 轮播图片
        mXBanner = (XBanner) view.findViewById(R.id.banner_1);
        //设置图片切换速度
        mXBanner.setPageChangeDuration(1000);
        mXBanner.setPageTransformer(Transformer.Default);

        mXBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(getContext(), "点击了第" + (position+1) + "图片", Toast.LENGTH_SHORT).show();
            }
        });
        RequestData();
        setAdapter();
        //宫格
        gridView= (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new HomePage_GridView_Adapter(getContext()));
       //推荐电影电视

        recommend_movie_recyclerView= (RecyclerView) view.findViewById(R.id.recommend_movie_recyclerView);
        StaggeredGridLayoutManager movie_staggeredGridLayoutManager=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        movie_staggeredGridLayoutManager.setAutoMeasureEnabled(true);

        recommend_movie_recyclerView.setLayoutManager(movie_staggeredGridLayoutManager);
        recommend_movie_recyclerView.setHasFixedSize(true);
        recommend_movie_recyclerView.setNestedScrollingEnabled(false);

        StaggeredGridLayoutManager tv_staggeredGridLayoutManager=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        tv_staggeredGridLayoutManager.setAutoMeasureEnabled(true);

        recommend_tv_recyclerView= (RecyclerView) view.findViewById(R.id.recommend_tv_recyclerView);
        recommend_tv_recyclerView.setLayoutManager(tv_staggeredGridLayoutManager);
        recommend_tv_recyclerView.setHasFixedSize(true);
        recommend_tv_recyclerView.setNestedScrollingEnabled(false);
        initVar();


        HomePage_Recommend_CardView_Adapter movie_adapter=new HomePage_Recommend_CardView_Adapter(products_movie);
        recommend_movie_recyclerView.setAdapter(movie_adapter);

        HomePage_Recommend_CardView_Adapter tv_adapter=new HomePage_Recommend_CardView_Adapter(products_tv);
        recommend_tv_recyclerView.setAdapter(tv_adapter);




        return view;
    }
    //推荐电视电影的数据源
    private void initVar() {
        products_movie.add(new MovieItemEntity(R.drawable.testimage1,"青云志"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage2,"不良人"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage3,"幻城"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage4,"戒魔人"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage5,"诛仙"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage6,"灵主"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage7,"武庚纪"));
        products_movie.add(new MovieItemEntity(R.drawable.testimage8,"秦时明月"));

        products_tv.add(new MovieItemEntity(R.drawable.testimage9,"灵主"));
        products_tv.add(new MovieItemEntity(R.drawable.testimage10,"武庚纪"));
        products_tv.add(new MovieItemEntity(R.drawable.testimage11,"秦时明月"));
        products_tv.add(new MovieItemEntity(R.drawable.testimage12,"青云志"));
        products_tv.add(new MovieItemEntity(R.drawable.testimage13,"不良人"));
        products_tv.add(new MovieItemEntity(R.drawable.testimage14,"幻城"));
        products_tv.add(new MovieItemEntity(R.drawable.testimage15,"戒魔人"));
        products_tv.add(new MovieItemEntity(R.drawable.testimage1,"诛仙"));
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


    private void setAdapter() {
        //加载广告图片
        mXBanner.setmAdapter(this);
    }

    private void RequestData() {
        //加载网络图片资源
        String url = "http://news-at.zhihu.com/api/4/themes";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        Toast.makeText(getContext(), "加载广告数据失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        AdvertiseEntity advertiseEntity = new Gson().fromJson(response, AdvertiseEntity.class);
                        mOthersList = advertiseEntity.getOthers();
                        List<String> tips = new ArrayList<String>();
                        for (int i = 0; i < mOthersList.size(); i++) {
                            tips.add(mOthersList.get(i).getDescription());
                        }
                        mXBanner.setData(mOthersList, tips);
                    }
                });
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(this).load(mOthersList.get(position).getThumbnail()).placeholder(R.drawable.default_image).error(R.drawable.default_image).into((ImageView) view);
    }
}
