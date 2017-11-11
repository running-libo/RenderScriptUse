package com.example.renderscript.renderscriptuse;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.RenderScript;
import com.uniquestudio.renderscript.ScriptC_sketch;

/**
 * Created by libo on 2017/11/11.
 */

public class BlackHandleUtil {

    public static Bitmap blackHandle(Bitmap bitmap, Context context){
        RenderScript renderScript = RenderScript.create(context);
        ScriptC_sketch sketchScript = new ScriptC_sketch(renderScript);

        Allocation in = Allocation.createFromBitmap(renderScript,bitmap);
        Allocation out = Allocation.createTyped(renderScript,in.getType());

        sketchScript.forEach_invert(in,out);

        out.copyTo(bitmap);

        renderScript.destroy();
        sketchScript.destroy();
        in.destroy();
        out.destroy();

        return bitmap;
    }

}
