package cn.oddcloud.www.oddccloudtelevision;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import cn.oddcloud.www.oddccloudtelevision.Aplayer.PlayerActivity;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.APlayerParam;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.model.Content;
import cn.oddcloud.www.oddccloudtelevision.Aplayer.util.PreferencesAttribute;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private   List<Object> contents;
    private Context context;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public TestRecyclerViewAdapter(Context context, List<Object> contents) {
        this.contents = contents;
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, ?> peferences = sharedPreferences.getAll();
                        Map<String, String> pramMap  = PreferencesAttribute.getPreferences(peferences);
                        APlayerParam aPlayerParam = new APlayerParam("http://live.gslb.letv.com/gslb?tag=live&stream_id=bjwsHD_1300&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1", pramMap);

                        Intent intent = new Intent(context, PlayerActivity.class);
                        intent.putExtra(Content.PLAYER_PARAM, aPlayerParam);
                        context.startActivity(intent);
                    }
                });
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }
}