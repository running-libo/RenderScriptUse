package com.example.renderscript.renderscriptuse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageviewBlur;
    private ImageView imageviewScale;
    private ImageView imageviewBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blur();
        scale();
        black();
    }

    private void blur() {
        imageviewBlur = (ImageView) findViewById(R.id.imageview);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.test);
        Bitmap blurBitmap = HandleUtils.handleGlassblur(getApplicationContext(),bitmap,15);
        imageviewBlur.setImageBitmap(blurBitmap);
    }

    private void scale(){
        imageviewScale = (ImageView) findViewById(R.id.imageview2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.test);
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 8, bitmap.getHeight() / 8, false);
        Bitmap blurBitmap = HandleUtils.handleGlassblur(getApplicationContext(),newBitmap,15);
        imageviewScale.setImageBitmap(blurBitmap);
    }

    private void black(){
        imageviewBlack = (ImageView) findViewById(R.id.imageview4);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.test);
        Bitmap newBitmap = BlackHandleUtil.blackHandle(bitmap,getApplicationContext());
        imageviewBlack.setImageBitmap(newBitmap);
    }


}
