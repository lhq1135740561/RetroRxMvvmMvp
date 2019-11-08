package com.yunge.myretrofitrxlmvp.ui.ac;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.meis.widget.photodrag.PhotoDragHelper;
import com.meis.widget.photodrag.PhotoDragRelativeLayout;
import com.yunge.myretrofitrxlmvp.R;



public class PhotoActivity extends FragmentActivity {

    Toolbar mToolbar;

    PhotoDragRelativeLayout mPdrLayout;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        mPdrLayout = findViewById(R.id.pdr_content);

        imageView = findViewById(R.id.photo_image);

        mPdrLayout.setDragListener(new PhotoDragHelper().setOnDragListener(new PhotoDragHelper.OnDragListener() {
            @Override
            public void onAlpha(float alpha) {
                mPdrLayout.setAlpha(alpha);
            }

            @Override
            public View getDragView() {

                return imageView;
            }

            @Override
            public void onAnimationEnd(boolean mSlop) {
                if(mSlop){
                    finish();
                    overridePendingTransition(0,0);
                }
            }
        }));
        mToolbar = findViewById(R.id.photo_tb);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
