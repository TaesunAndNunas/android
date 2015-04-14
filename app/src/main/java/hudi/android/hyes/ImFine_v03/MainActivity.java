package hudi.android.hyes.ImFine_v03;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private ArrayAdapter<String> navigationDrawerAdapter;
    private String[] leftSliderData = {"현재 접속중인 아이 계정", "OOO(남 2개월 | 만0세)", "계정 변경하기", "신규가입하기", "계정추가하기"};
    private String date_now;
    private String time_now;
    private LinearLayout innerLayout;
    private LinearLayout mainLayout;
    private RelativeLayout contentLayout;
    private ArrayList<Card> cards;
    private CardArrayAdapter mCardArrayAdapter;
    private CardListView listView;
    private TextView medicineTime;
    private TextView sleepTime;
    private TextView diaperTime;
    private TextView milkTime;
    private int medicine_count_num = 1, sleep_count_num = 1, diaper_count_num = 1, milk_count_num =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window win = getWindow();
        win.setContentView(R.layout.demo_fragment_native_list_expand);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear = (LinearLayout)inflater.inflate(R.layout.main_buttons_img, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        win.addContentView(linear, layoutParams);

        LayoutInflater inflater2 = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear2 = (LinearLayout)inflater2.inflate(R.layout.main_text, null);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        win.addContentView(linear2, layoutParams2);



        cards = new ArrayList<Card>();
//        for (int i=0;i<50;i++){
//
//            getCurrentTime();
//            Card card = CardTest(date_now, i);
//            cards.add(card);
//        }


       ImageButton img_btn =  (ImageButton)findViewById(R.id.imageButton);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard(1);
                medicine_count_num += 1;
                Toast.makeText(getApplicationContext(), "수유 새 카드 생성", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton img_btn2 =  (ImageButton)findViewById(R.id.imageButton2);
        img_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard(2);
                sleep_count_num += 1;
                Toast.makeText(getApplicationContext(), "새 카드 생성", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton img_btn3 =  (ImageButton)findViewById(R.id.imageButton3);
        img_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard(3);
                diaper_count_num += 1;
                Toast.makeText(getApplicationContext(), "새 카드 생성", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton img_btn4 =  (ImageButton)findViewById(R.id.imageButton4);
        img_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard(4);
                milk_count_num += 1;
                Toast.makeText(getApplicationContext(), "새 카드 생성", Toast.LENGTH_SHORT).show();
            }
        });

        nitView();
        if (toolbar != null) {
            toolbar.setTitle("I'm Fine");
            setSupportActionBar(toolbar);
        }
        initDrawer();

    }


    private void createCard(int num){

        getCurrentTime();
        Card card = CardTest(date_now, num);
        cards.add(card);

        mCardArrayAdapter = new CardArrayAdapter(getApplicationContext(),cards);

        listView = (CardListView) findViewById(R.id.cardlist_expand);
            if (listView!=null){
                listView.setAdapter(mCardArrayAdapter);
            }
        }


    private void getCurrentTime(){

        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat curDate = new SimpleDateFormat("yy년MM월dd일");
        SimpleDateFormat curTime = new SimpleDateFormat("a hh:mm:ss");

        date_now = curDate.format(date);
        time_now = curTime.format(date);

    }


    private void nitView() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationDrawerAdapter = new ArrayAdapter<String>( MainActivity.this,
                android.R.layout.simple_list_item_1, leftSliderData);
        leftDrawerList.setAdapter(navigationDrawerAdapter);
    }

    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    /**
     * This method builds a standard header with a custom expand/collpase
     */
    private Card CardTest(String titleHeader, int i) {

        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat textTime = new SimpleDateFormat("hh:mm");
        String now_text = textTime.format(date);

        medicineTime = (TextView)findViewById(R.id.medicineTime);
        sleepTime = (TextView)findViewById(R.id.sleepTime);
        diaperTime = (TextView)findViewById(R.id.diaperTime);
        milkTime = (TextView)findViewById(R.id.milkTime);

        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        innerLayout = (LinearLayout) findViewById(R.id.inner_layout);
        contentLayout = (RelativeLayout) findViewById(R.id.contentLayout);

        Card card;

        if(i == 1) {

            card = new CustomCardMedicine(getApplicationContext(), R.layout.inner_content);
            medicineTime.setText(now_text);
            TextView count = (TextView)findViewById(R.id.medicineCount);
            String count_total = Integer.toString(medicine_count_num);
            count.setText(count_total);

        }else if(i == 2) {
            card = new CustomCardSleep(getApplicationContext(), R.layout.inner_content);
            sleepTime.setText(now_text);
            TextView count = (TextView)findViewById(R.id.sleepCount);
            String count_total = Integer.toString(sleep_count_num);
            count.setText(count_total);


        }else if(i == 3){
            card = new CustomCardDiaper(getApplicationContext(), R.layout.inner_content);
            diaperTime.setText(now_text);
            TextView count = (TextView)findViewById(R.id.diaperCount);
            String count_total = Integer.toString(diaper_count_num);
            count.setText(count_total);


        }else {
            card = new CustomCardMilk(getApplicationContext(), R.layout.inner_content);
            milkTime.setText(now_text);
            TextView count = (TextView)findViewById(R.id.milkCount);
            String count_total = Integer.toString(milk_count_num);
            count.setText(count_total);
        }

        CardHeader header = new CardHeader((getApplicationContext()));
        //card.setupInnerViewElements(mainLayout, contentLayout);

        header.setTitle(titleHeader);


        header.setButtonExpandVisible(true);

        card.addCardHeader(header);

        CardExpand cardExpand1 = new CustomExpandCard(getApplicationContext());

        cardExpand1.setupInnerViewElements(mainLayout, innerLayout);
        card.addCardExpand(cardExpand1);

        //CardThumbnail thumbnail = new CardThumbnail(getApplicationContext());
        //thumbnail.setDrawableResource(R.drawable.diaper);
        //card.addCardThumbnail(thumbnail);

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

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
