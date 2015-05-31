package test.chart.hyes.imfine_04;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

/**
 * Created by hyes on 2015. 5. 31..
 */
public class DailyGraph extends ActionBarActivity{

    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_graph);

//        image = (ImageView) findViewById(R.id.imageView);
//
//        Drawable drawable = getResources().getDrawable(R.drawable.daily1);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//// (int left, int top, int right, int bottom)
//        image.setImageDrawable(drawable);

    }
}