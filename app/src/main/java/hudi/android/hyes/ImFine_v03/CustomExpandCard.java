package hudi.android.hyes.ImFine_v03;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.CardExpand;

/**
 * Created by hyes on 2015. 4. 2..
 */
public class CustomExpandCard extends CardExpand {

        //Use your resource ID for your inner layout
        public CustomExpandCard(Context context) {
            super(context, R.layout.inner_expand);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            if (view == null) return;

            //Retrieve TextView elements
            TextView tx1 = (TextView) view.findViewById(R.id.carddemo_expand_text1);
            TextView tx2 = (TextView) view.findViewById(R.id.carddemo_expand_text2);
            TextView tx3 = (TextView) view.findViewById(R.id.carddemo_expand_text3);
            TextView tx4 = (TextView) view.findViewById(R.id.carddemo_expand_text4);

            //Set value in text views
            if (tx1 != null) {
                tx1.setText(getContext().getString(R.string.demo_expand_customtitle1));
            }

            if (tx2 != null) {
                tx2.setText(getContext().getString(R.string.demo_expand_customtitle2));
            }
        }
    }