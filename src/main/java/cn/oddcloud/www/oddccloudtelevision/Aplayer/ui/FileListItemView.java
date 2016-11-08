package cn.oddcloud.www.oddccloudtelevision.Aplayer.ui;

import android.content.Context;
import android.widget.RelativeLayout;

import cn.oddcloud.www.oddccloudtelevision.R;


public class FileListItemView extends RelativeLayout {
	public FileListItemView(Context context) {
		super(context);
		super.inflate(context, R.layout.file_list_item, this);
	}

}
