package test.chart.hyes.imfine_04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by hyes on 2015. 5. 20..
 */
public class Medicine extends ActionBarActivity{

    private Button cancel_btn, save_btn;
    private TextView ml, memo;
    private CheckBox fever, cough, snot, vomit;
    private int id;
    private String detail, ml_val, cough_val, vomit_val, snot_val, fever_val, memo_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_detail);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        final Dao dao = new Dao(getApplicationContext());
        detail = dao.getEventsById(id);


        cancel_btn = (Button) findViewById(R.id.cancel_btn);
        save_btn = (Button) findViewById(R.id.save_btn);
        ml = (TextView) findViewById(R.id.ml_tv);
        memo = (TextView) findViewById(R.id.memo);
        fever = (CheckBox)findViewById(R.id.checkBox);
        cough = (CheckBox)findViewById(R.id.checkBox2);
        snot = (CheckBox)findViewById(R.id.checkBox3);
        vomit = (CheckBox)findViewById(R.id.checkBox4);


        JSONParser parser = new JSONParser();
        try {
            Object obj_j = parser.parse(detail);

            JSONObject jsonObject = (JSONObject) obj_j;

            ml_val = jsonObject.get("ml").toString();
            memo_val = jsonObject.get("memo").toString();
            cough_val = jsonObject.get("기침").toString();
            fever_val = jsonObject.get("열").toString();
            vomit_val = jsonObject.get("구토").toString();
            snot_val = jsonObject.get("콧물").toString();

            Log.i("test", "ml length: " + ml_val.length());
            Log.i("test", "cough length: "+cough_val.length());


        } catch (Exception e) {
            e.printStackTrace();
        }


        if(ml_val == null || ml_val.length() == 0) {
            Log.i("test", "nothing");

        }else{
            int temp_length = ml_val.length() - 1;
            String temp_ml = ml_val.substring(1, temp_length);
            ml.setText(temp_ml);
        }

        if(memo_val == null || memo_val.length() == 0) {
            Log.i("test", "nothing");
        }else{
            int temp_length2 = memo_val.length() - 1;
            String temp_memo = memo_val.substring(1, temp_length2);
            memo.setText(temp_memo);
        }

        try{
            if (cough_val.equals("기침")) {
                cough.setChecked(!cough.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        try{
            if (fever_val.equals("열")) {
                fever.setChecked(!fever.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        try{
            if (vomit_val.equals("구토")) {
                vomit.setChecked(!vomit.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        try{
            if (snot_val.equals("콧물")) {
                snot.setChecked(!snot.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject jsonObj = new JSONObject();

                if(memo.getText() == null) {
                    jsonObj.put("memo", "none");
                }else if(!memo.getText().equals("")) {
                    jsonObj.put("memo", "\"" + memo.getText() + "\"");
                }else{
                    jsonObj.put("memo", "");
                }

                if( ml.getText() == null){
                    jsonObj.put("ml", "none");
                }else if(!ml.getText().equals("")) {
                    jsonObj.put("ml", "\"" + ml.getText() + "\"");
                }else{
                    jsonObj.put("ml", "");
                }

                if (fever.isChecked()) {
                    jsonObj.put("열", "열");
                } else {
                    jsonObj.put("열", "");
                }

                if (cough.isChecked()) {
                    jsonObj.put("기침", "기침");
                }else {
                    jsonObj.put("기침", "");
                }
                if (snot.isChecked()) {
                    jsonObj.put("콧물", "콧물");
                }else{
                    jsonObj.put("콧물", "");
                }
                if (vomit.isChecked()) {
                    jsonObj.put("구토", "구토");
                }else{
                    jsonObj.put("구토", "");
                }
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
