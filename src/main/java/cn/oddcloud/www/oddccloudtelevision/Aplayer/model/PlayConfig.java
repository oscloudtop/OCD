package cn.oddcloud.www.oddccloudtelevision.Aplayer.model;

import com.aplayer.aplayerandroid.APlayerAndroid;

import cn.oddcloud.www.oddccloudtelevision.Aplayer.play_interface.IConfigAudio;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.play_interface.IConfigSubtitle;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.play_interface.IConfigVideo;


/**
 * Created by admin on 2016/7/14.
 */
public class PlayConfig {
    public static class PlayerListener {
        public APlayerAndroid.OnPlayCompleteListener playCompleteListener;
    }

    private IConfigVideo mIConfigVideo;
    private IConfigAudio mIConfigAudio;
    private IConfigSubtitle mISubtitle;

    public PlayConfig(IConfigVideo mIConfigVideo, IConfigAudio mIConfigAudio, IConfigSubtitle mISubtitle) {
        this.mIConfigVideo = mIConfigVideo;
        this.mIConfigAudio = mIConfigAudio;
        this.mISubtitle = mISubtitle;
    }

    public IConfigVideo getConfigVideo() {
        return mIConfigVideo;
    }

    public IConfigAudio getConfigAudio() {
        return mIConfigAudio;
    }

    public IConfigSubtitle getSubtitle() {
        return mISubtitle;
    }
}
