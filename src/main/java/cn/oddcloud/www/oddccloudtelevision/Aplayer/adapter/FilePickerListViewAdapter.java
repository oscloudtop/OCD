package cn.oddcloud.www.oddccloudtelevision.Aplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.FileItemInfo;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.ui.FileListItemView;
import cn.oddcloud.www.oddccloudtelevision.R;


public class FilePickerListViewAdapter extends BaseAdapter {
	private Context context;
	private List<FileItemInfo> fileList;

	public FilePickerListViewAdapter(Context context, List<FileItemInfo> fileList) {
		this.context = context;
		this.fileList = fileList;
	}

	@Override
	public int getCount() {
		return this.fileList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = new FileListItemView(context);
		}
		FileItemInfo fileInfo = fileList.get(position);
		FileListItemView fileListItemView = (FileListItemView)convertView;
		ImageView folderImageView = (ImageView)fileListItemView.findViewById(R.id.folderImageView);
		ImageView videoThumbnailImageView = (ImageView)fileListItemView.findViewById(R.id.videoThumbnailImageView);
		TextView folderNameTextView = (TextView)fileListItemView.findViewById(R.id.folderNameTextView);
		TextView fileNameTextView = (TextView)fileListItemView.findViewById(R.id.fileNameTextView);

		if (fileInfo.isDirectory) {
			folderImageView.setVisibility(View.VISIBLE);
			folderNameTextView.setVisibility(View.VISIBLE);
			videoThumbnailImageView.setVisibility(View.GONE);
			fileNameTextView.setVisibility(View.GONE);
			folderNameTextView.setText(fileInfo.name);
		}
		else {
			videoThumbnailImageView.setVisibility(View.VISIBLE);
			fileNameTextView.setVisibility(View.VISIBLE);
			folderImageView.setVisibility(View.GONE);
			folderNameTextView.setVisibility(View.GONE);
			fileNameTextView.setText(fileInfo.name);
			//videoThumbnailImageView.setImageResource(R.drawable.ic_document_file_48px);
		}
		return convertView;
	}

	public ArrayList<FileItemInfo> getVideoFileList() {
		return (ArrayList<FileItemInfo>)fileList;
	}
}
