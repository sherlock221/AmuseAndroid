package amuse.sherlock.com.amuseandroid;

import android.app.Application;
import android.content.Context;

/**
 * Created by sherlock on 15/11/21.
 */
public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getContext(){
        return appContext;
    }


}
