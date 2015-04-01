package hudi.android.hyes.ImFine_v03;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;




public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_fragment_native_list_expand);


        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i=0;i<200;i++){
            Card card = init_standard_header_with_expandcollapse_button_custom_area("Header "+i,i);
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getApplicationContext(),cards);

        CardListView listView = (CardListView) findViewById(R.id.carddemo_list_expand);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
    }


    /**
     * This method builds a standard header with a custom expand/collpase
     */
    private Card init_standard_header_with_expandcollapse_button_custom_area(String titleHeader,int i) {

        //Create a Card
        Card card = new Card(getApplicationContext());

        //Create a CardHeader
        CardHeader header = new CardHeader((getApplicationContext()));

        //Set the header title
        header.setTitle("ImFine_!");

        //Set visible the expand/collapse button
        header.setButtonExpandVisible(true);

        //Add Header to card
        card.addCardHeader(header);



        //Swipe
        card.setSwipeable(true);

        //Animator listener
        card.setOnExpandAnimatorEndListener(new Card.OnExpandAnimatorEndListener() {
            @Override
            public void onExpandEnd(Card card) {
                Toast.makeText(getApplicationContext(), "Expand " + card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        card.setOnCollapseAnimatorEndListener(new Card.OnCollapseAnimatorEndListener() {
            @Override
            public void onCollapseEnd(Card card) {
                Toast.makeText(getApplicationContext(),"Collpase " +card.getCardHeader().getTitle(),Toast.LENGTH_SHORT).show();
            }
        });

        return card;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
