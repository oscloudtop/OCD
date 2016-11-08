package cn.oddcloud.www.oddccloudtelevision.Aplayer;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cn.oddcloud.www.oddccloudtelevision.Aplayer.adapter.FilePickerListViewAdapter;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.Content;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.FileItemInfo;
import cn.oddcloud.www.oddccloudtelevision.R;

/**
 * Created by admin on 2016/7/19.
 */
public class FilePickerView extends LinearLayout {

    private static final String ERROR_TAG = Content.APLAYER_DEMO_LOG_PREF_TAG + FilePickerView.class.getSimpleName();
    private static final String WARE_TAG = ERROR_TAG;

    private Context mContext;
    private View mRootView;
    private View mParentFolderText;
    private FilePickListener mFilePickListener;
    private ListView mFileListView;
    private static final String ROOT = Content.PATH_ROOT;
    private String mCurDirPath = ROOT;

    public FilePickerView(Context context, FilePickListener filePickListener, String currentDir) {
        super(context);

        mContext = context;
        mFilePickListener = filePickListener;
        mRootView =  LayoutInflater.from(context).inflate(R.layout.file_picker, this);
        mFileListView = (ListView) mRootView.findViewById(R.id.fileListView);

        mParentFolderText = findViewById(R.id.parentFolderText);
        mParentFolderText.setOnClickListener(mParentFolderTextOnClickListener);
        mParentFolderText.setVisibility(View.GONE);
        mFileListView = (ListView)findViewById(R.id.fileListView);
        mFileListView.setOnItemClickListener(mFileListViewOnItemClickListener);
        String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        mCurDirPath = (null == currentDir) ? rootPath : currentDir;

        updateFileList(mCurDirPath);
    }

    public void updateFileList(String path) {
        mCurDirPath = path;
        notifySlectedChange(mCurDirPath, false);
        File dir = new File(path);
        if ((!dir.exists() || !dir.isDirectory()) && !path.equals(ROOT)) {
            updateFileList(ROOT);
            return;
        }
        if (dir.getParentFile() != null) {
            mParentFolderText.setVisibility(View.VISIBLE);
        }
        else {
            mParentFolderText.setVisibility(View.GONE);
        }
        ArrayList<FileItemInfo> fileList = new ArrayList<FileItemInfo>();
        ArrayList<FileItemInfo> dirList = new ArrayList<FileItemInfo>();
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : dir.listFiles()) {
                if (file.isHidden()) {
                    continue;
                }
                if (file.isDirectory()) {
                    FileItemInfo fileItem = new FileItemInfo();
                    fileItem.isDirectory = true;
                    fileItem.name = file.getName();
                    fileItem.path = file.getPath();
                    dirList.add(fileItem);
                }
                else {
                    FileItemInfo fileItem = new FileItemInfo();
                    fileItem.isDirectory = false;
                    fileItem.name = file.getName();
                    fileItem.path = file.getPath();
                    fileList.add(fileItem);
                }
            }
        }

        Comparator comp = new FileItemInfo.SortComparator();
        Collections.sort(dirList,comp);
        Collections.sort(fileList, comp);
        //fileList.addAll(0, dirList);
        fileList.addAll(dirList);

        FilePickerListViewAdapter adapter = new FilePickerListViewAdapter(mContext, fileList);
        mFileListView.setAdapter(adapter);
    }

    private final OnClickListener mParentFolderTextOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            File curPathDir = new File(mCurDirPath);
            if (!curPathDir.exists() || !curPathDir.isDirectory()) {
                updateFileList(ROOT);
                return;
            }
            File parentFile = curPathDir.getParentFile();
            if (parentFile != null) {
                updateFileList(parentFile.toString());
            }
        }
    };

    private final ListView.OnItemClickListener mFileListViewOnItemClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FilePickerListViewAdapter adapter = (FilePickerListViewAdapter)parent.getAdapter();
            ArrayList<FileItemInfo> fileList = adapter.getVideoFileList();
            FileItemInfo fileItemInfo = fileList.get(position);
            if (fileItemInfo.isDirectory) {
                updateFileList(fileItemInfo.path);
            }
            else {
                String videoFilePath = fileList.get(position).path;
                String videoFileName = fileList.get(position).name;

                notifySlectedChange(videoFilePath, true);
            }
        }
    };

    public interface FilePickListener
    {
        void onSelected(String filePath, boolean isFile);
    }

    public void notifySlectedChange(String filePath, boolean isFile)
    {
        if(null == mFilePickListener)
            return;

        mFilePickListener.onSelected(filePath, isFile);
    }
}
