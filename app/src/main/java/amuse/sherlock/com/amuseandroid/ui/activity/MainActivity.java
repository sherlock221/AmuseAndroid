package amuse.sherlock.com.amuseandroid.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;

import amuse.sherlock.com.amuseandroid.R;
import amuse.sherlock.com.amuseandroid.model.Category;
import amuse.sherlock.com.amuseandroid.ui.common.BaseActivity;
import amuse.sherlock.com.amuseandroid.ui.common.BaseFragment;
import amuse.sherlock.com.amuseandroid.ui.fragment.FeedsFragment;
import amuse.sherlock.com.amuseandroid.view.BlurFoldingActionBarToggle;
import amuse.sherlock.com.amuseandroid.view.FoldingDrawerLayout;
import amuse.sherlock.com.amuseandroid.view.TitleLayout;

public class MainActivity extends BaseActivity {


    private  FoldingDrawerLayout foldingDrawerLayout;
    private  FrameLayout contentLayout;
    private ImageView imageView;

    private Category mCategory;

    private BlurFoldingActionBarToggle mDrawerToggle;

    private FeedsFragment mContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foldingDrawerLayout = (FoldingDrawerLayout) findViewById(R.id.drawer_layout);
        contentLayout = (FrameLayout) findViewById(R.id.content_frame);
        imageView = (ImageView) findViewById(R.id.blur_image);


        foldingDrawerLayout.setScrimColor(Color.argb(100, 255, 255, 255));


        mDrawerToggle = new BlurFoldingActionBarToggle(this,foldingDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }
        };

        mDrawerToggle.setBlurImageAndView(imageView, contentLayout);
        foldingDrawerLayout.setDrawerListener(mDrawerToggle);

        setCategory(Category.hot);

    }

    private void setCategory(Category category) {
        foldingDrawerLayout.closeDrawer(GravityCompat.START);
        if (mCategory == category) {
            return;
        }
        mCategory = category;
        setTitle(mCategory.getDisplayName());

        mContentFragment = FeedsFragment.newInstance(category);

        replaceFragment(R.id.content_frame, mContentFragment);
    }

    protected void replaceFragment(int viewId, BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        foldingDrawerLayout.

//        MenuItem item = menu.findItem(R.id.search_menu);
//        SearchView searchView  = (SearchView) item.getActionView();

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if( id == R.id.search_menu){
            mContentFragment.loadFirstAndScrollToTop();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
