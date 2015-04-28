package hudi.android.hyes.ImFine_v03;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by hyes on 2015. 4. 28..
 */

public class Dao {
    private Context context;
    private SQLiteDatabase database;

    public Dao(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("Card.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);


        try{
            String sql = "CREATE TABLE IF NOT EXISTS Card(ID integer primary key autoincrement,"
                    + " cardNumber integer,"
                    + " Type integer,"
                    + " Time text);";

            database.execSQL(sql);
        }catch(Exception e){
            Log.e("test", "CREATE TABLE FAILED! - " + e);
            e.printStackTrace();
        }
    }

    public void insertCard(Card card) {

        String sql = "INSERT INTO Card(Type)"
                + "VALUES(" + card.getType() +  ");";
        // + "VALUES(" + card.getType() + ",'" + card.getTimetime + "');";

        Log.i("test", sql);

        try{
            database.execSQL(sql);
        }catch(Exception e){
            //Log.e("test", "DB error! - "+e);
            //e.printStackTrace();
        }

    }
    public void insertCard(String jsonData) {
       // int articleNumber;
        int type;
        String time;


      //  FileDownloader fileDownloader = new FileDownloader(context);

        try{
            JSONArray jArr = new JSONArray(jsonData);

            for(int i=0; i <jArr.length(); ++i){
                JSONObject jObj = jArr.getJSONObject(i);

                type = jObj.getInt("Type");
                time =jObj.getString("Time");


                Log.i("test", "Type: " + type + ", Time: " + time);

                String sql = "INSERT INTO Card(Type, Time)"
                        + "VALUES(" + type + ",'" + time + "');";

                try{
                    database.execSQL(sql);
                }catch(Exception e){
                    //Log.e("test", "DB error! - "+e);
                    //e.printStackTrace();
                }

               // fileDownloader.downFile("http://192.168.56.1:5009/image/"+ imgName, imgName);

            }
        }catch(JSONException e){
            Log.e("test", "JSON ERROR! - " + e);
            e.printStackTrace();
        }
    }

    public ArrayList<Card> getArticleList() throws ParseException {

        ArrayList<Card> cardList = new ArrayList<Card>();
        int type;
        String time;


        String sql = "SELECT*FROM Card;";
        Cursor cursor = database.rawQuery(sql, null);


        while(cursor.moveToNext()){
            type = cursor.getInt(1);
            time = cursor.getString(2);


            if(type == 1) {
                cardList.add(new CustomCardMedicine(context, R.layout.inner_content));
            }else if(type == 2){
                cardList.add(new CustomCardSleep(context, R.layout.inner_content));
            }else if(type == 3){
                cardList.add(new CustomCardDiaper(context, R.layout.inner_content));
            }else if(type == 4){
                cardList.add(new CustomCardMilk(context, R.layout.inner_content, time));
            }
        }
        cursor.close();

        return cardList;
    }

}
