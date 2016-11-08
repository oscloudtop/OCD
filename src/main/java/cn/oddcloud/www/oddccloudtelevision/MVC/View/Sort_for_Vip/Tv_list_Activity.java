package cn.oddcloud.www.oddccloudtelevision.MVC.View.Sort_for_Vip;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.oddcloud.www.oddccloudtelevision.R;

public class Tv_list_Activity extends Activity {
  private ImageView tv_current_img;
    private TextView tv_current_title;
    private LinearLayout container_tvlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_list);
        InitView();


        for (int i = 0; i < 4; i++) {

            LinearLayout linearLayout=new LinearLayout(this);
            for (int j = 1; j < 4; j++) {
                Button button=new Button(this);
                button.setText(i*3+j+"");
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10, 5, 0, 10);
                layoutParams.gravity = Gravity.CENTER;
                button.setLayoutParams(layoutParams);
                button.setTextColor(getResources().getColor(R.color.black));
                button.setBackground(getResources().getDrawable(R.drawable.yuanjiao));
                linearLayout.addView(button);
            }

            container_tvlist.addView(linearLayout);

        }



    }

    private void InitView() {
        tv_current_img= (ImageView) findViewById(R.id.tv_current_img);
        tv_current_title= (TextView) findViewById(R.id.tv_current_title);
        container_tvlist= (LinearLayout) findViewById(R.id.container_tvlist);
    }
}
