package amuse.sherlock.com.amuseandroid.ui.common;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import amuse.sherlock.com.amuseandroid.R;
import amuse.sherlock.com.amuseandroid.data.RequestManager;

/**
 * Created by sherlock on 15/11/21.
 */
public class BaseActivity extends AppCompatActivity {


    private ActionBar actionBar;
    private   ShimmerTextView mActionBarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
    }


    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        //获得actionbar
        actionBar = getSupportActionBar();

        //左上角添加返回图标
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //设置logo
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);

        actionBar.setHomeButtonEnabled(true);
        //显示title
        actionBar.setDisplayShowTitleEnabled(false);
        //自定义视图
        actionBar.setDisplayShowCustomEnabled(true);

        View view = View.inflate(this, R.layout.title_view, null);

        mActionBarTitle = (ShimmerTextView) view.findViewById(R.id.tv_shimmer);
        new Shimmer().start(mActionBarTitle);
        actionBar.setCustomView(view);

        //隐藏actionbar
        //actionBar.hide();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity销毁时候取消请求
        RequestManager.cancelAll(this);
    }


    /**
     * 点击actionbar图标
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

//                Intent intent = NavUtils.getParentActivityIntent(this);

                //新创建task
//                if (NavUtils.shouldUpRecreateTask(this, intent)) {
//                    TaskStackBuilder.create(this)
//                            .addNextIntentWithParentStack(intent)
//                            .startActivities();
//                }
//                //在同一task之中
//                else {
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    NavUtils.navigateUpTo(this, intent);
//                }
//
//                finish();

                  onBackPressed();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //设置标题
    public void  setTitle(CharSequence text){
        this.mActionBarTitle.setText(text);
    }
    public void  setTitle(int resId){
        this.mActionBarTitle.setText(resId);
    }

    //发起请求
    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }

    //取消所有请求
    protected void cancelRequest() {
        RequestManager.cancelAll(this);
    }


}
