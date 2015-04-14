package hudi.android.hyes.ImFine_v03;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by hyes on 2015. 4. 6..
 */
public class CustomCardDiaper extends Card {

    private long currentTimeMillis;
    private Date date;
    protected ImageView colorBar;
    protected TextView time;
    private Context mContext;

    public CustomCardDiaper(Context context) {
        super(context);
    }

    public CustomCardDiaper(Context context, int innerLayout) {
        super(context, innerLayout);
        mContext= context;
        currentTimeMillis = System.currentTimeMillis();
        date = new Date(currentTimeMillis);
        init();
    }


    private void init(){

        //No Header

        //Set a OnClickListener listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {

                Toast.makeText(getContext(), "Click Listener card", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Diaper.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

//    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {


        SimpleDateFormat curTime = new SimpleDateFormat("a hh:mm:ss");
        String time_now = curTime.format(date);

        //Retrieve elements
        colorBar = (ImageView) parent.findViewById(R.id.colorBar);
        time = (TextView) parent.findViewById(R.id.timeTextView);

        colorBar.setBackgroundColor(Color.parseColor("#FFC965"));

        if (time!=null)
            time.setText(time_now);

//        if (mSecondaryTitle!=null)
//            mSecondaryTitle.setText(R.string.demo_custom_card_googleinc);


    }

}
