package yuvi.com.fancycalcy.utils;

import android.content.Context;
import android.graphics.Typeface;

public class CodeSnippet {

    private Context mContext;

    public CodeSnippet(Context mContext) {
        this.mContext = mContext;
    }


    public Typeface pacificoFont(){
        try {
            return Typeface.createFromAsset(mContext.getAssets(), "fonts/Pacifico.ttf");
        }
        catch (Exception e){
            return  Typeface.defaultFromStyle(Typeface.NORMAL);
        }
    }

    public String getString(int pStringRefference) {
        return mContext.getString(pStringRefference);
    }
}
