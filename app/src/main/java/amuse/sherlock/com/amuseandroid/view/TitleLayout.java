package amuse.sherlock.com.amuseandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import amuse.sherlock.com.amuseandroid.R;

/**
 * Created by sherlock on 15/11/21.
 */
public class TitleLayout extends LinearLayout {


    private TextView title;
    private Button leftBtn;


    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_view,this);
    }



}
