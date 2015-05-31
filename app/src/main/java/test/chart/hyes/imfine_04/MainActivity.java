package test.chart.hyes.imfine_04;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ListActivity {

    private ImageButton medicine, sleep, feed, diaper;
    public List<Item> items, reverse_item;
    public String time_changed, m_t, s_t, d_t, f_t;
    public int idx;
    private int m_count, d_count, f_count, s_count;
 //   private EventAdapter adapter;

    public TextView m_cnt_tv, d_cnt_tv, s_cnt_tv, f_cnt_tv, m_time, d_time, s_time, f_time;


    public List<Item> getItems() {
        return items;
    }

    public void addHeaderItems(Item item) {
        items.add(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicine = (ImageButton) findViewById(R.id.imageButton);
        sleep = (ImageButton) findViewById(R.id.imageButton2);
        feed = (ImageButton) findViewById(R.id.imageButton4);
        diaper = (ImageButton) findViewById(R.id.imageButton3);

        m_cnt_tv = (TextView)findViewById(R.id.medicine_count);
        d_cnt_tv = (TextView)findViewById(R.id.diaper_count);
        s_cnt_tv = (TextView)findViewById(R.id.sleep_count);
        f_cnt_tv = (TextView)findViewById(R.id.feed_count);

        m_time = (TextView)findViewById(R.id.m_time);
        d_time = (TextView) findViewById(R.id.d_time);
        s_time = (TextView)findViewById(R.id.s_time);
        f_time = (TextView)findViewById(R.id.f_time);

        Dao dao = new Dao(getApplicationContext());
        m_count = dao.getDailyCount("medicine");
        d_count = dao.getDailyCount("diaper");
        s_count = dao.getDailyCount("sleep");
        f_count = dao.getDailyCount("feed");

        m_cnt_tv.setText(m_count + "");
        s_cnt_tv.setText(s_count + "");
        d_cnt_tv.setText(d_count + "");
        f_cnt_tv.setText(f_count + "");


        items = new ArrayList<Item>();

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard(0);

            }
        });
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard(1);
            }
        });
        diaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard(2);
            }
        });
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard(3);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



    public void createCard(int num){

        Proxy proxy = new Proxy();

        Dao dao = new Dao(getApplicationContext());


        if(num == 0) {

            String temp_time = timestamp();

            checkSeparator();
            m_count++;
            m_cnt_tv.setText(m_count + "");
            m_time.setText(temp_time);

            Event event = new Event("medicine", m_count, temp_time);
            dao.eventInsert("medicine", m_count, temp_time);
            items.add(event);
            int id = dao.getRecentID("medicine", m_count, temp_time);

            Log.i("test", "recent: "+id+"");

            proxy.send(id, "Ksksk", event,

                    new Callback<Response>(){
                        @Override
                        public void success(Response response, Response response2) {
                            BufferedReader reader = null;
                            StringBuilder sb = new StringBuilder();
                            try{
                                reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                                String line;
                                try{
                                    while((line = reader.readLine()) != null){
                                        sb.append(line);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            String ok = sb.toString();
                            Log.i("test", ok);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("error",error.toString());

                        }
                    }
                    );


        }else  if(num == 1) {

            String temp_time = timestamp();

            checkSeparator();
            s_count++;
            s_cnt_tv.setText(s_count + "");
            s_time.setText(temp_time);

            Event event = new Event("sleep", s_count, temp_time);
            dao.eventInsert("sleep", s_count, temp_time);
            items.add(event);
            int id = dao.getRecentID("sleep", s_count, temp_time);

            proxy.send(id, "Ksksk", event,
                    new Callback<Response>(){
                        @Override
                        public void success(Response response, Response response2) {
                            BufferedReader reader = null;
                            StringBuilder sb = new StringBuilder();
                            try{
                                reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                                String line;
                                try{
                                    while((line = reader.readLine()) != null){
                                        sb.append(line);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            String ok = sb.toString();
                            Log.i("test", ok);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("error",error.toString());

                        }
                    }
            );


        }else  if(num == 2) {


            String temp_time = timestamp();

            checkSeparator();
            d_count++;
            d_cnt_tv.setText(d_count + "");
            d_time.setText(temp_time);

            Event event = new Event("diaper", d_count, temp_time);
            dao.eventInsert("diaper", d_count, temp_time);
            items.add(event);
            int id = dao.getRecentID("sleep", s_count, temp_time);

            proxy.send(id, "Ksksk", event,
                    new Callback<Response>(){
                        @Override
                        public void success(Response response, Response response2) {
                            BufferedReader reader = null;
                            StringBuilder sb = new StringBuilder();
                            try{
                                reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                                String line;
                                try{
                                    while((line = reader.readLine()) != null){
                                        sb.append(line);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            String ok = sb.toString();
                            Log.i("test", ok);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("error",error.toString());

                        }
                    }
            );


        }else if(num == 3){
            String temp_time = timestamp();
            checkSeparator();
            f_count++;
            f_cnt_tv.setText(f_count + "");
            f_time.setText(temp_time);
            Event event = new Event("feed", f_count, temp_time);
            dao.eventInsert("feed", f_count, temp_time);
            items.add(event);

            int id = dao.getRecentID("sleep", s_count, temp_time);

            proxy.send(id, "Ksksk", event,
                    new Callback<Response>(){
                        @Override
                        public void success(Response response, Response response2) {
                            BufferedReader reader = null;
                            StringBuilder sb = new StringBuilder();
                            try{
                                reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                                String line;
                                try{
                                    while((line = reader.readLine()) != null){
                                        sb.append(line);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            String ok = sb.toString();
                            Log.i("test", ok);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("error",error.toString());

                        }
                    }
            );

        }

        listView();

    }

    private void checkSeparator() {

        Dao dao = new Dao(getApplicationContext());
        SimpleDateFormat timestampDate = new SimpleDateFormat("yyyyMMdd");
        String date = timestampDate.format(new Date()).toString();

        Log.i("test", "now date: " + date);
        String temp_comparingDate =  dao.getRecentDate();
        String comparingDate =null;

        if(temp_comparingDate != null) {
            comparingDate = temp_comparingDate.substring(0, 8);
            Log.i("test", "comparing: "+comparingDate);
        }

        if(temp_comparingDate == null){
            Header header = new Header(date);
            items.add(header);
            dao.eventInsert("separator");

        }else if(!comparingDate.equals(date)){


            Header header = new Header(date);
            items.add(header);
            dao.eventInsert("separator");

            m_count = 0;
            d_count = 0;
            s_count = 0;
            f_count = 0;

            m_cnt_tv.setText(m_count+"");
            d_cnt_tv.setText(d_count+"");
            s_cnt_tv.setText(s_count+"");
            f_cnt_tv.setText(f_count+"");

            m_time.setText("");
            d_time.setText("");
            s_time.setText("");
            f_time.setText("");


            listView();
            Log.i("test", "new Header and zero set");
        }else{

        }
    }

    private String timestamp(){

        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat curTime = new SimpleDateFormat("a hh:mm");
        return time_changed = curTime.format(date);

    }

    private void listView(){
        Dao dao = new Dao(getApplicationContext());
        items = dao.getEvents();

        reverse_item = new ArrayList<Item>();

        for(int i=items.size() -1; i >= 0; i--){
            reverse_item.add(items.get(i));
        }

        EventAdapter adapter = new EventAdapter(this, reverse_item);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Dao dao = new Dao(getApplicationContext());

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (((Item) parent.getAdapter().getItem(position)).getViewType() == EventAdapter.RowType.HEADER_ITEM.ordinal()) {
                   // Toast.makeText(getApplicationContext(), "graph? maybe..", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(MainActivity.this, DailyGraph.class);
                    startActivity(intent);

                }
                if (((Item) parent.getAdapter().getItem(position)).getViewType() == EventAdapter.RowType.LIST_ITEM.ordinal()) {

                    if (((Event) parent.getAdapter().getItem(position)).getType().equals("medicine")) {
                        Intent intent = new Intent(MainActivity.this, Medicine.class);

                        idx = dao.getId("medicine", ((Event) parent.getAdapter().getItem(position)).getDate());
                        Log.i("test", "id: " + idx);
                        intent.putExtra("id", idx);
                        startActivityForResult(intent, RESULT_OK);
                    } else if (((Event) parent.getAdapter().getItem(position)).getType().equals("feed")) {
                        Intent intent = new Intent(MainActivity.this, Feed.class);
                        idx = dao.getId("feed", ((Event) parent.getAdapter().getItem(position)).getDate());
                        Log.i("test", "id: " + idx);
                        intent.putExtra("id", idx);
                        startActivityForResult(intent, RESULT_OK);
                    } else if (((Event) parent.getAdapter().getItem(position)).getType().equals("sleep")) {
                        Intent intent = new Intent(MainActivity.this, Sleep.class);
                        idx = dao.getId("sleep", ((Event) parent.getAdapter().getItem(position)).getDate());
                        Log.i("test", "id: " + idx);
                        intent.putExtra("id", idx);
                        startActivityForResult(intent, RESULT_OK);
                    } else if (((Event) parent.getAdapter().getItem(position)).getType().equals("diaper")) {
                        Intent intent = new Intent(MainActivity.this, Diaper.class);
                        idx = dao.getId("diaper", ((Event) parent.getAdapter().getItem(position)).getDate());
                        Log.i("test", "id: " + idx);
                        intent.putExtra("id", idx);
                        startActivityForResult(intent, RESULT_OK);
                    }

                }
            }
        });


    }

    private final Handler handler = new Handler();

    private void refreshData(){
        new Thread(){
            public void run(){
//                Proxy proxy = new Proxy();
//                  String jsonData = proxy.getJson();

                Dao dao = new Dao(getApplicationContext());


//                dao.insertJsonData(jsonData);
//                Log.i("test", jsonData);

                handler.post(new Runnable(){
                    public void run(){

                        Log.i("test", "handler ON!!!");
                        listView();

                    }
                });
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();


        Dao dao = new Dao(getApplicationContext());
        m_count = dao.getDailyCount("medicine");
        d_count = dao.getDailyCount("diaper");
        s_count = dao.getDailyCount("sleep");
        f_count = dao.getDailyCount("feed");

        m_cnt_tv.setText(m_count + "");
        s_cnt_tv.setText(s_count + "");
        d_cnt_tv.setText(d_count + "");
        f_cnt_tv.setText(f_count + "");

        m_t = dao.getRecentTime("medicine");
        s_t = dao.getRecentTime("sleep");
        d_t = dao.getRecentTime("diaper");
        f_t = dao.getRecentTime("feed");

        m_time.setText(m_t);
        d_time.setText(d_t);
        s_time.setText(s_t);
        f_time.setText(f_t);

        refreshData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Dao dao = new Dao(getApplicationContext());
        dao.stop();
    }
}
