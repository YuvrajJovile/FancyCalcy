package yuvi.com.fancycalcy.utils;

public class Log {

    public static void d(String pName,String pValue){
       android.util.Log.d(pName, pValue);
    }

    public static void i(String pName,String pValue){
        android.util.Log.i(pName, pValue);
    }

    public static void e(String pName,String pValue){
        android.util.Log.e(pName, pValue);
    }

    public static void w(String pName,String pValue){
        android.util.Log.w(pName, pValue);
    }


}
