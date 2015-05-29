package test.chart.hyes.imfine_04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by hyes on 2015. 5. 20..
 */
public class Sleep extends ActionBarActivity{

    private Button cancel_btn, save_btn;
    private TextView memo;
    private int id;
    private String detail, memo_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleep_detail);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        Log.i("test", "id" + id);

        Dao dao = new Dao(getApplicationContext());
        detail = dao.getEventsById(id);


        cancel_btn = (Button) findViewById(R.id.cancel_btn);
        save_btn = (Button) findViewById(R.id.save_btn);
        memo = (TextView) findViewById(R.id.memo);

        JSONParser parser = new JSONParser();
        try {
            Object obj_j = parser.parse(detail);

            JSONObject jsonObject = (JSONObject) obj_j;

            memo_val = jsonObject.get("memo").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }


        if(memo_val == null || memo_val.length() == 0) {
            Log.i("test", "nothing2");
        }else{
            int temp_length2 = memo_val.length() - 1;
            String temp_memo = memo_val.substring(1, temp_length2);
            memo.setText(temp_memo);
        }


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject jsonObj = new JSONObject();

                if (memo.getText() == null) {
                    jsonObj.put("memo", "none");
                } else if (!memo.getText().equals("")) {
                    jsonObj.put("memo", "\"" + memo.getText() + "\"");
                } else {
                    jsonObj.put("memo", "");
                }


                Dao dao = new Dao(getApplicationContext());
                dao.updateDetail(String.valueOf(jsonObj), id);

                Log.i("test",String.valueOf(jsonObj) );

                finish();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
