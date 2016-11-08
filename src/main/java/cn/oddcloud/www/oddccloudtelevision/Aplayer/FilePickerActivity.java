package cn.oddcloud.www.oddccloudtelevision.Aplayer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Map;

import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.APlayerParam;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.Content;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.util.PreferencesAttribute;
import cn.oddcloud.www.oddccloudtelevision.R;

public class FilePickerActivity extends AppCompatActivity {
	private static final String DEBUG_TAG = "FilePickerActivity";
	private static final String CUR_DIR_PATH = "CurDirPath";
	private String mCurDirPath;
	private MediaFilePickListener mMediaFilePickListener;
	private FilePickerView mFilePickerView;
	private ActionBar mActionBar;
	public FilePickerActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(DEBUG_TAG, "onCreate");
		super.onCreate(savedInstanceState);

		mActionBar = getSupportActionBar();
		mCurDirPath = getDefaultDir(savedInstanceState);
		mMediaFilePickListener = new MediaFilePickListener();
		mFilePickerView = new FilePickerView(this, mMediaFilePickListener, mCurDirPath);
		setContentView(mFilePickerView);

		Log.e("mCurDirPath = ",mCurDirPath);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    savedInstanceState.putString(CUR_DIR_PATH, mCurDirPath);
	    super.onSaveInstanceState(savedInstanceState);
		Log.e(DEBUG_TAG, "Save State");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_activy_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.file_refresh:
				mFilePickerView.updateFileList(mCurDirPath);
				Toast.makeText(this, "Update:" + mCurDirPath, Toast.LENGTH_SHORT).show();
				break;
			case R.id.player_setting:
				//Intent intent = new Intent(this, SettingsActivity.class);
				Intent intent = new Intent(this, Setting.class);
				startActivity(intent);
				Toast.makeText(this, "Setting:", Toast.LENGTH_SHORT).show();
				break;
			case R.id.open_url:
				inputUrl();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
	}

	private void showCrrentDirPath(String path)
	{
		mActionBar.setTitle(path);
	}

	private void startPlayer(String mediaFilePath)
	{
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FilePickerActivity.this);
		Map<String, ?> peferences = sharedPreferences.getAll();
		Map<String, String> pramMap  = PreferencesAttribute.getPreferences(peferences);
		APlayerParam aPlayerParam = new APlayerParam(mediaFilePath, pramMap);

		Intent intent = new Intent(this, PlayerActivity.class);
		intent.putExtra(Content.PLAYER_PARAM, aPlayerParam);
		startActivity(intent);
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

	public void inputUrl()
	{
		//装载/res/layout/login.xml界面布局
		final LinearLayout urlInputForm = (LinearLayout)getLayoutInflater()
				.inflate( R.layout.url_input_layout, null);
		new AlertDialog.Builder(this)
				.setIcon(R.drawable.url_address_24px)
				.setTitle("URL")
				.setView(urlInputForm)
				.setPositiveButton("OK" , new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText urlEditText = (EditText) urlInputForm.findViewById(R.id.alter_dlg_url);
						String urlPath = urlEditText.getText().toString();
						if(null != urlPath && !urlPath.isEmpty())
							startPlayer(urlPath);
					}
				})
				.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// do nothing
					}
				})
				.create()
				.show();
	}

	private class MediaFilePickListener implements FilePickerView.FilePickListener
	{

		@Override
		public void onSelected(String filePath, boolean isFile) {
			if(isFile)
			{
				startPlayer(filePath);
			}
			else
			{
				mCurDirPath = filePath;
				showCrrentDirPath(filePath);
			}
		}
	}

}
