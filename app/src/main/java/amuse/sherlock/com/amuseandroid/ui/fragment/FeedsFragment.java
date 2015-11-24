package amuse.sherlock.com.amuseandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import amuse.sherlock.com.amuseandroid.R;
import amuse.sherlock.com.amuseandroid.api.GagApi;
import amuse.sherlock.com.amuseandroid.data.GsonRequest;
import amuse.sherlock.com.amuseandroid.model.Category;
import amuse.sherlock.com.amuseandroid.model.Feed;
import amuse.sherlock.com.amuseandroid.ui.common.BaseFragment;
import amuse.sherlock.com.amuseandroid.util.ToastUtils;


/**
 * Created by sherlock on 15/11/23.
 */
public class FeedsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String EXTRA_CATEGORY = "extra_category";


    //下拉刷新
    private SwipeRefreshLayout mSwipeLayout;

    private Category mCategory;

    private String mPage = "0";

    private MenuItem mRefreshItem;

    private View view;


    public static FeedsFragment newInstance(Category category) {
        FeedsFragment fragment = new FeedsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CATEGORY, category.name());
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        mRefreshItem = menu.findItem(R.id.search_menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeds, container, false);

        parseArgument();
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeLayout.setOnRefreshListener(this);
        return view;
    }


    private void loadData(String next) {
        if (!mSwipeLayout.isRefreshing() && ("0".equals(next))) {
            setRefreshing(true);
        }

        String url = String.format(GagApi.LIST, mCategory.name(), next);
        executeRequest(new GsonRequest(url, Feed.FeedRequestData.class, responseListener(), errorListener()));
    }


    public void loadFirstAndScrollToTop() {
        loadData(mPage);
    }

    private Response.Listener<Feed.FeedRequestData> responseListener() {
        return new Response.Listener<Feed.FeedRequestData>() {
            @Override
            public void onResponse(Feed.FeedRequestData feedRequestData) {
                ToastUtils.showShort(new Gson().toJson(feedRequestData));
                setRefreshing(false);

            }
        };
    }

    private void parseArgument() {
        Bundle bundle = getArguments();
        mCategory = Category.valueOf(bundle.getString(EXTRA_CATEGORY));
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.showShort(R.string.loading_failed);
                setRefreshing(false);

            }
        };
    }

    private void setRefreshing(boolean refreshing) {
        mSwipeLayout.setRefreshing(refreshing);
        if (mRefreshItem == null) return;

        if (refreshing) {
            view = mRefreshItem.getActionView();
            mRefreshItem.setActionView(R.layout.actionbar_refresh_progress);
        } else
            mRefreshItem.setActionView(view);
    }

    @Override
    public void onRefresh() {
        loadData(mPage);
    }
}
