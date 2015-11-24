package amuse.sherlock.com.amuseandroid.ui.common;

import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import amuse.sherlock.com.amuseandroid.data.RequestManager;
import amuse.sherlock.com.amuseandroid.util.ToastUtils;


/**
 * Created by storm on 14-3-25.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RequestManager.cancelAll(this);
    }

    protected void executeRequest(Request request) {
        RequestManager.addRequest(request, this);
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.showLong(error.getMessage());
            }
        };
    }
}
