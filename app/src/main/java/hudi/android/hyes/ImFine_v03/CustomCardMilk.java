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
public class CustomCardMilk extends Card {

    protected ImageView colorBar;
    protected TextView time;
    private Context mContext;

    public CustomCardMilk(Context context) {
        super(context);
    }

    public CustomCardMilk(Context context, int innerLayout) {
        super(context, innerLayout);
        mContext= context;
        init();

    }


    private void init(){

        //No Header

        //Set a OnClickListener listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {

                Toast.makeText(getContext(), "Click Listener card", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Milk.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);


            }
        });
    }

//    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        colorBar = (ImageView) parent.findViewById(R.id.colorBar);
        time = (TextView) parent.findViewById(R.id.timeTextView);

        colorBar.setBackgroundColor(Color.parseColor("#FF6D4E"));

        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);

        SimpleDateFormat curTime = new SimpleDateFormat("a hh:mm:ss");
        String time_now = curTime.format(date);

        if (time!=null)
            time.setText(time_now);

//        if (mSecondaryTitle!=null)
//            mSecondaryTitle.setText(R.string.demo_custom_card_googleinc);


    }

}
