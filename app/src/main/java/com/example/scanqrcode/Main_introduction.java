package com.example.scanqrcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scanqrcode.R;
import com.example.scanqrcode.fragment.setting;

public class Main_introduction extends AppCompatActivity {

    private ViewPager slide_Viewpaper;
    private LinearLayout mDolayout;
    private TextView[] mDots;
    private  Slide_layout slide_layout;
    private ImageView back,next;
    private  int mcurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
         getSupportActionBar().hide(); //hide the title bar

        setContentView(R.layout.activity_main_introduction);
        slide_Viewpaper = findViewById(R.id.view_paper);
        mDolayout = findViewById(R.id.dotshot);
        back =findViewById(R.id.button2);
        next=findViewById(R.id.button3);
        slide_layout = new Slide_layout(this);
        slide_Viewpaper.setAdapter(slide_layout);
        addDot(0);
        slide_Viewpaper.addOnPageChangeListener(viewListner);

    }
    public void addDot(int position){
        mDots = new TextView[3];
        mDolayout.removeAllViews();
        for (int i=0;i<3;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.white));
            mDolayout.addView(mDots[i]);
        }
        if(mDots.length>0)
                mDots[position].setTextColor(getResources().getColor(R.color.primay_icon));
    }
    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDot(position);
            mcurrent=position;
            if(mcurrent==0) {
                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);
                next.setImageResource(R.drawable.check2);
            }else if(mcurrent==2){
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                next.setImageResource(R.drawable.check3);
                back.setImageResource(R.drawable.check1);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Main_introduction.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

            }else {
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                next.setImageResource(R.drawable.check2);

                back.setImageResource(R.drawable.check1);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}