package com.superdroid.viewfipperdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    /**
     * 轮播图片的ViewFlipper
     */
    private ViewFlipper fipper;
    /**
     * 轮播文字的ViewFlipper
     */
    private ViewFlipper vifper;
    private TextView text;
    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fipper = (ViewFlipper) findViewById(R.id.flipper);
        vifper = (ViewFlipper) findViewById(R.id.viper);


        //fipper.startFlipping();

        datas.add("滚滚长江东逝水");
        datas.add("浪花淘尽英雄");
        datas.add("是非成败转头空");
        datas.add("青山依旧在");
        datas.add("几度夕阳红");

        addView();
        //也可以直接调用该方法实现循环滚动，但是不能添加动画效果
        // vifper.startFlipping();

        //发送一个延时消息，两秒之后才开始滚动，否则一开启页面就开始滚动，就看不到第一行信息了
        new Handler() {
        }.postDelayed(new Runnable() {
            @Override
            public void run() {

                scroll();
            }
        }, 2000);
    }

    private void addView() {

        for (int i = 0; i < datas.size(); i++) {

            final String info = datas.get(i);
            View view = View.inflate(this, R.layout.item_view, null);
            TextView tv = (TextView) view.findViewById(R.id.text);
            tv.setText(info);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();
                }
            });

            vifper.addView(view);
        }
    }


    private void scroll() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        moveTonext();
                    }
                });
            }
        }, 0, 3000);
    }

    /**
     * 设置出现和消失时的动画效果
     * viewfper执行下一个
     */
    private void moveTonext() {
        vifper.setInAnimation(this, R.anim.in_bottomtop);
        vifper.setOutAnimation(this, R.anim.out_bottomtop);
        vifper.showNext();//切换到下一个文字

//        fipper.setInAnimation(this, R.anim.in_bottomtop);
//        fipper.setOutAnimation(this, R.anim.out_bottomtop);
        fipper.showNext();//切换到下一个图片
    }

}
