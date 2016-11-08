package cn.oddcloud.www.oddccloudtelevision.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.R;
import cn.oddcloud.www.oddccloudtelevision.TES.SamplesNtbActivity;
import cn.oddcloud.www.oddccloudtelevision.fragment.index_SortFragment.Sort_for_Vip_Fragment;
import cn.oddcloud.www.oddccloudtelevision.fragment.index_SortFragment.Sort_for_sortall_Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Index_SortFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Index_SortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Index_SortFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ViewPager viewPager_sort;

    public Index_SortFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Index_SortFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Index_SortFragment newInstance(String param1, String param2) {
        Index_SortFragment fragment = new Index_SortFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
       View view=inflater.inflate(R.layout.fragment_index__sort, container, false);

        InitViewPagerView(view);
        InitSlipView(view);


        return view;
    }

    private void InitViewPagerView(View view) {
        viewPager_sort= (ViewPager) view.findViewById(R.id.index_sort_viewpager);
       Fragment sort_for_sortall_Fragment=new Sort_for_sortall_Fragment();
       Fragment  sort_for_Vip_Fragment=new Sort_for_Vip_Fragment();

        final List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(sort_for_sortall_Fragment);
        fragmentList.add(sort_for_Vip_Fragment);

        viewPager_sort.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        viewPager_sort.setCurrentItem(0);

    }

    //fragment 切换
    private void InitSlipView(View view) {
        final NavigationTabBar ntbSample5 = (NavigationTabBar)view. findViewById(R.id.ntb_sample_5);
        final ArrayList<NavigationTabBar.Model> models5 = new ArrayList<>();
        models5.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fifth), Color.WHITE
                )
                        .title("分类大全")
                        .build()
        );
        models5.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first), Color.WHITE
                )
                        .title("视频Vip")
                        .build()
        );

        ntbSample5.setModels(models5);
        ntbSample5.setViewPager(viewPager_sort,0);
        ntbSample5.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {

            }
            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {

            }
        });

        //TODO 页面改变操作
        ntbSample5.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
}
