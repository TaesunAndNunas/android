package test.chart.hyes.imfine_04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by hyes on 2015. 5. 20..
 */
public class Feed extends ActionBarActivity{

    private Button cancel_btn, save_btn;
    private TextView ml, memo, min;
    private RadioButton left, right, both;
    private int id;
    private String detail, ml_val, left_val, right_val, both_val, memo_val, min_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milk_detail);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        Dao dao = new Dao(getApplicationContext());
        detail = dao.getEventsById(id);


        cancel_btn = (Button) findViewById(R.id.cancel_btn);
        save_btn = (Button) findViewById(R.id.save_btn);
        ml = (TextView) findViewById(R.id.ml);
        min = (TextView) findViewById(R.id.min);
        memo = (TextView) findViewById(R.id.memo);
        left = (RadioButton)findViewById(R.id.left);
        right = (RadioButton)findViewById(R.id.right);
        both = (RadioButton)findViewById(R.id.both);

        JSONParser parser = new JSONParser();
        try {
            Object obj_j = parser.parse(detail);

            JSONObject jsonObject = (JSONObject) obj_j;

            ml_val = jsonObject.get("ml").toString();
            memo_val = jsonObject.get("memo").toString();
            left_val = jsonObject.get("left").toString();
            right_val = jsonObject.get("right").toString();
            both_val = jsonObject.get("both").toString();
            min_val = jsonObject.get("min").toString();

          //  Log.i("test", "ml length: " + ml_val.length());


        } catch (Exception e) {
            e.printStackTrace();
        }


        if(ml_val == null || ml_val.length() == 0) {
            Log.i("test", "nothing1");

        }else{
            int temp_length = ml_val.length() - 1;
            String temp_ml = ml_val.substring(1, temp_length);
            ml.setText(temp_ml);
        }

        if(memo_val == null || memo_val.length() == 0) {
            Log.i("test", "nothing2");
        }else{
            int temp_length2 = memo_val.length() - 1;
            String temp_memo = memo_val.substring(1, temp_length2);
            memo.setText(temp_memo);
        }

        if(min_val == null || min_val.length() == 0) {
            Log.i("test", "nothing3");
        }else{
            int temp_length3 = min_val.length() - 1;
            String temp_memo = min_val.substring(1, temp_length3);
            min.setText(temp_memo);
        }
        try{
            if (left_val.equals("left")) {
                left.setChecked(!left.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        try{
            if (right_val.equals("right")) {
                right.setChecked(!right.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        try{
            if (both_val.equals("both")) {
                both.setChecked(!both.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject jsonObj = new JSONObject();


                if( ml.getText() == null){
                    jsonObj.put("ml", "");
                }else
                if(!ml.getText().equals("")) {
                    jsonObj.put("ml", "\"" + ml.getText() + "\"");
                }else{
                    jsonObj.put("ml", "");
                }

                if(min.getText() == null){
                    jsonObj.put("min", "none");
                }else if(!min.getText().equals("")) {
                    jsonObj.put("min", "\"" + min.getText() + "\"");
                }else{
                    jsonObj.put("min", "");
                }

                if(memo.getText() == null) {
                    jsonObj.put("memo", "none");
                }else if(!memo.getText().equals("")) {
                    jsonObj.put("memo", "\"" + memo.getText() + "\"");
                }else{
                    jsonObj.put("memo", "");
                }

                if (left.isChecked()) {
                    jsonObj.put("left", "left");
                } else {
                    jsonObj.put("left", "");
                }

                if (right.isChecked()) {
                    jsonObj.put("right", "right");
                }else {
                    jsonObj.put("right", "");
                }
                if (both.isChecked()) {
                    jsonObj.put("both", "both");
                }else{
                    jsonObj.put("both", "");
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
