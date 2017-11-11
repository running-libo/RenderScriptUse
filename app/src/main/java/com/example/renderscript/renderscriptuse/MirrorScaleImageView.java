package com.example.renderscript.renderscriptuse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.AppCompatImageView;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.RenderScript;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.uniquestudio.renderscript.ScriptC_magnifier;

/**
 * Created by libo on 2017/11/11.
 */

public class MirrorScaleImageView extends AppCompatImageView{
    private Bitmap bitmap;


    public MirrorScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.mipmap.test);
        setImageBitmap(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.mipmap.test);
                Bitmap newBitmap = scaleMirror(bitmap,x,y,200,2,getContext());
                setImageBitmap(newBitmap);
                break;
        }
        return true;
    }

    /**
     *
     * @param bitmap
     * @param x  圆心x
     * @param y  圆心y
     * @param radius  圆半径
     * @param scale  放大倍数
     * @param context
     * @return
     */
    private Bitmap scaleMirror(Bitmap bitmap, int x, int y, int radius, int scale, Context context){
        RenderScript rs = RenderScript.create(context);

        Allocation in = Allocation.createFromBitmap(rs, bitmap);
        Allocation out = Allocation.createTyped(rs,in.getType());


        ScriptC_magnifier magnifier = new ScriptC_magnifier(rs);

        magnifier.set_inputAllocation(in);
        magnifier.set_atX(x);
        magnifier.set_atY(y);
        magnifier.set_radius(radius);
        magnifier.set_scale(scale);


        magnifier.forEach_magnify(in,out);

        out.copyTo(bitmap);

        rs.destroy();
        magnifier.destroy();
        in.destroy();
        out.destroy();

        return bitmap;
    }
}
