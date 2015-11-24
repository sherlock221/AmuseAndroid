package amuse.sherlock.com.amuseandroid.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import amuse.sherlock.com.amuseandroid.App;

/**
 * 请求队列管理类
 * Created by sherlock on 15/11/21.
 */
public class RequestManager {

    private static RequestQueue requestQueue = Volley.newRequestQueue(App.getContext());

    private  RequestManager(){}


    /**
     * 添加一个请求
     * @param request
     * @param tag
     */
    public static void addRequest(Request<?> request, Object tag){
        if(tag != null){
            request.setTag(tag);
        }
        requestQueue.add(request);
    }


    /**
     * 根据tag 取消所有请求
     */
    public static void cancelAll(Object tag){
        requestQueue.cancelAll(tag);
    }

}
