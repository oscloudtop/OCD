package cn.oddcloud.www.oddccloudtelevision.Aplayer.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import cn.oddcloud.www.oddccloudtelevision.Aplayer.AudioSetView;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.SubtitleView;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.VideoSetView;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.Content;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.PlayConfig;
import cn.oddcloud.www.oddccloudtelevision.R;


/**
 * Created by admin on 2016/7/13.
 */
public class PlayerPopupWindow extends PopupWindow {

    private static final String ERROR_TAG = Content.APLAYER_DEMO_LOG_PREF_TAG + PlayerPopupWindow.class.getSimpleName();
    String[] mCatagoryArray = null;
    private View mVideoSetting = null;
    private View mAudioSetting = null;
    private View mSubtitleSetting = null;
    private View mCurrentShow = null;
    private FrameLayout mDetailsViewRoot = null;
    View mRootView = null;
    View mParentView = null;
    ListView mCategoryListView = null;
    PlayConfig mPlayConfig = null;

    private class ResouseIndex
    {
        public static final int INDEX_VIDEO = 0;
        public static final int INDEX_AUDIO = 1;
        public static final int INDEX_SUBTITLE = 2;
        public static final int INDEX_VR = 3;
    }

    public PlayerPopupWindow(PlayConfig playConfig, View parentView, int width, int height) {
        super(width, height);
        mRootView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.popup, null);
        setContentView(mRootView);
        mPlayConfig = playConfig;
        mParentView = parentView;
        mCategoryListView = (ListView)mRootView.findViewById(R.id.categoryListView);
        mDetailsViewRoot = (FrameLayout)mRootView.findViewById(R.id.set_detail_container);
        //mCatagoryArray = contentView.getResources().getStringArray(R.array.play_setting_category);



//        //mCategoryListView.setFocusable(true);
//        //setFocusable(true);
        mCategoryListView.setFocusable(true);
        //mCategoryListView.setItemsCanFocus(false);
        mCategoryListView.setItemsCanFocus(true);
//        //mCategoryListView.setFocusableInTouchMode(true);
//        mCategoryListView.setSelected(false);

        initCategoryArray();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mRootView.getContext(),
//                android.R.spacer_medium.simple_list_item_1, mCatagoryArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mRootView.getContext(),
                R.layout.popwnd_category_item, mCatagoryArray);
        mCategoryListView.setAdapter(adapter);


        //mCategoryListView.setChoiceMode();

        initSetView();
        registerListener();

        //默认选中第一个
        if(mCategoryListView.getAdapter().getCount() > 0)
        {
            //mCategoryListView.
            //mCategoryListView.setSelection(0);
            mCategoryListView.performItemClick(this.getContentView(), 0, 0);
            mCategoryListView.setSelection(0);
            showVideoSetDetails();
        }

    }


    private void initCategoryArray()
    {
        mCatagoryArray = mRootView.getResources().getStringArray(R.array.play_setting_category);
    }
    private void initSetView()
    {
        mVideoSetting = new VideoSetView(mRootView.getContext(), mPlayConfig.getConfigVideo());
        mAudioSetting = new AudioSetView(mRootView.getContext(), mPlayConfig.getConfigAudio());
        mSubtitleSetting = new SubtitleView(mRootView.getContext(), mPlayConfig.getSubtitle(), mParentView);
    }

    private void registerListener()
    {
        mCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int postion = i;
                switch (postion)
                {
                    case ResouseIndex.INDEX_VIDEO:
                        showVideoSetDetails();
                        break;
                    case ResouseIndex.INDEX_AUDIO:
                        showAudeoSetDetails();
                        break;
                    case ResouseIndex.INDEX_SUBTITLE:
                        showSubtitleSetDetails();
                        break;
                    case ResouseIndex.INDEX_VR:
                        break;
                    default:
                        Log.e(ERROR_TAG, "invalidate category index!");
                        break;
                }
            }
        });
    }

    private void showVideoSetDetails()
    {
        showSetView(mVideoSetting);
    }

    private void showAudeoSetDetails()
    {
        showSetView(mAudioSetting);
    }

    private void showSubtitleSetDetails()
    {
        showSetView(mSubtitleSetting);
    }

    private void showSetView(View setingView)
    {
        if(mCurrentShow == setingView)
        {
            return;
        }

        mDetailsViewRoot.removeAllViews();
        mDetailsViewRoot.addView(setingView);
    }
}
