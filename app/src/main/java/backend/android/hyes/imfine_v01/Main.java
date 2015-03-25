package backend.android.hyes.imfine_v01;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class Main extends ActionBarActivity {

    private ArrayList<Item> temp = new ArrayList<Item>();
    ListView listView;

    String[] color_idx = {"#B2FF59", "#FFC107", "#3D5AFE", "#F36C60", "#B2FF59"};
    String[] times = {"05:30", "06:40", "07:10", "09:40", "11:00"};
    String[] info ={"수유 어쩌구저쩌구", "기저귀 갈아줬고", "약 잘 먹었고~~", " ", "수유 잘 먹었고~"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_list_row, temp);
        adapter.add(new Item(color_idx[0], times[0], info[0]));
        adapter.add(new Item(color_idx[1], times[1], info[1]));
        adapter.add(new Item(color_idx[2], times[2], info[2]));
        adapter.add(new Item(color_idx[3], times[3], info[3]));
        adapter.add(new Item(color_idx[4], times[4], info[4]));

        listView.setAdapter(adapter);


//        Button register_btn = (Button)findViewById(R.id.register_btn);
//        register_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Register.class);
//                startActivity(intent);
//            }
//        });
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
