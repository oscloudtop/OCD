package cn.oddcloud.www.oddccloudtelevision.Aplayer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.Aplayer.adapter.PopWndChoseListAdapter;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.Content;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.PopWndOptionsCategory;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.PopWndOptionsIteamParam;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.play_interface.IConfigAudio;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.util.ListViewUtility;
import cn.oddcloud.www.oddccloudtelevision.R;

public class AudioSetView extends LinearLayout {

    private static final String ERROR_TAG = Content.APLAYER_DEMO_LOG_PREF_TAG + AudioSetView.class.getSimpleName();
    private static final String WARE_TAG = ERROR_TAG;

    private Context mContext;
    private View mRootView;
    private IConfigAudio mConfigAudio;
    private TextView mAudioCategory = null;
    private ListView mAudioListView = null;
    private PopWndOptionsCategory mAudioParam = new PopWndOptionsCategory();

    private static class AudioTrackSet
    {
        public static String CATEGORY_NAME;
    }

    public AudioSetView(Context context, IConfigAudio configAudio) {
        super(context);
        mContext = context;
        mConfigAudio = configAudio;
        mRootView = LayoutInflater.from(context).inflate(R.layout.audio_set_view, this);
        setOrientation(VERTICAL);

        getAudioTrackSet();
        init();
        updateParam();
        registerListener();
    }

    private void getAudioTrackSet()
    {
        AudioTrackSet.CATEGORY_NAME = mRootView.getResources().getString(R.string.POPWND_AUDIO);
    }
    private void init()
    {
        mAudioCategory = (TextView) mRootView.findViewById(R.id.popwnd_audio_track);
        mAudioListView = (ListView)mRootView.findViewById(R.id.popwnd_audio_track_list);
    }

    private PopWndOptionsCategory getDeocderCategory()
    {
        List<String> audioTrackList = mConfigAudio.getAudioTrack();
        int crrentPos = mConfigAudio.getCurrentAudioTrackPos();

        List<PopWndOptionsIteamParam> IteamList = new ArrayList<PopWndOptionsIteamParam>();
        if(null != audioTrackList)
        {
            for(int i = 0; i < audioTrackList.size(); ++i)
            {
                boolean isSelect = (i == crrentPos);
                PopWndOptionsIteamParam iteam = new PopWndOptionsIteamParam(isSelect, true, audioTrackList.get(i));
                IteamList.add(iteam);
            }
        }


        PopWndOptionsCategory popWndOptionsCategory = new PopWndOptionsCategory(AudioTrackSet.CATEGORY_NAME, IteamList);
        return popWndOptionsCategory;
    }

    private void registerListener() {
        mAudioListView.setOnItemClickListener(new PopWndChoseListAdapter.IteamListener(mAudioParam,
                new PopWndChoseListAdapter.IteamListener.IteamClickListener() {
                    @Override
                    public void onItemClick(int pos, String strIteamTitle) {
                        setAudioTrack(strIteamTitle);
                    }
                }));
    }

    private void updateParam()
    {
        PopWndOptionsCategory audioParam = getDeocderCategory();
        if(null != audioParam)
            mAudioParam.setCategory(audioParam);

        mAudioCategory.setText(mAudioParam.getCategoryTitle());
        mAudioListView.setAdapter(new PopWndChoseListAdapter(mContext, mAudioParam));
        ListViewUtility.setListViewHeightBasedOnChildren(mAudioListView);
    }

    private void setAudioTrack(String chose)
    {
        List<PopWndOptionsIteamParam> iteamsList = mAudioParam.getmIteamList();
        if(null == iteamsList)
        {
            return;
        }

        int pos = 0;
        for (; pos < iteamsList.size(); ++pos)
        {
            if(chose.compareToIgnoreCase(iteamsList.get(pos).getTitle()) == 0)
            {
                break;
            }
        }

        if(pos >= iteamsList.size())
        {
            Log.e(ERROR_TAG, "Invalidate Audio Track, Pos = " + pos);
            return;
        }

        if(!mConfigAudio.setCurrentAudioTrack(pos))
        {
            Log.e(ERROR_TAG, "Audio Track Set Failed, Pos = " + pos);
        }

        updateParam();
    }
}
