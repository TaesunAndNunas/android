package test.chart.hyes.imfine_04;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hyes on 2015. 5. 18..
 */
public class Dao {
    private Context context;
    private SQLiteDatabase database;

    public Dao(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("ImFine.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);


        try{
            String sql = "CREATE TABLE IF NOT EXISTS Events(ID integer primary key autoincrement,"
                    + "type text not null,"
                    + "count integer,"
                    + "time text,"
                    + "date text,"
                    + "detail text);";

            database.execSQL(sql);

        }catch(Exception e){
            Log.e("test", "CREATE TABLE FAILED! - " + e);
            e.printStackTrace();
        }
    }

    public void eventInsert(String type, int count, String time) {

        SimpleDateFormat timestampDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = timestampDate.format(new Date()).toString();

        String sql = "INSERT INTO Events (type, count, time, date)"
                + "VALUES('" + type +  "'," + count + ",'" + time + "','" +  date + "');";

        try {
            database.execSQL(sql);
            Log.i("test", sql);
        } catch (Exception e) {
            Log.e("test", "DB error! - " + e);
            e.printStackTrace();
        }
    }

    public void eventInsert(String type) {

        SimpleDateFormat timestampDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = timestampDate.format(new Date()).toString();

        String sql = "INSERT INTO Events (type, date)"
                + "VALUES('" + type +  "','" + date + "');";

        try {
            database.execSQL(sql);
            Log.i("test", sql);
        } catch (Exception e) {
            Log.e("test", "DB error! - " + e);
            e.printStackTrace();
        }
    }


//    public void insertJsonData(String jsonData) {
//
//        int id;
//        String type;
//        int count;
//        String time;
//        String date;
//
//        FileDownloader fileDownloader = new FileDownloader(context);
//
//        try{
//            JSONArray jArr = new JSONArray(jsonData);
//
//            for(int i=0; i <jArr.length(); ++i){
//                JSONObject jObj = jArr.getJSONObject(i);
//
//                id =jObj.getInt("ID");
//                type = jObj.getString("type");
//                time = jObj.getString("time");
//                date = jObj.getString("date");
//                count =jObj.getInt("count");
////                detail = jObj.getString("detail");
//
//
//                String sql = "INSERT INTO Events (ID, type, count, time, date)"
//                        + "VALUES('" + id +"','" + type +  "','" + count + "','" + time + "','" + date + "');";
//
//                try{
//                    database.execSQL(sql);
//                    Log.i("test", sql);
//                }catch(Exception e){
//                    Log.e("test", "DB error! - "+e);
//                    e.printStackTrace();
//                }
//
////                fileDownloader.downFile("http://192.168.56.1:8899/uploads/"+ audio1, audio1);
//
//            }
//        }catch(JSONException e){
//            Log.e("test", "JSON ERROR! - " + e);
//            e.printStackTrace();
//        }
//    }


    public List<Item> getEvents() {

        List<Item> items = new ArrayList<Item>();

        int id;
        String type;
        int count;
        String time;
        String date;
        String detail;


        String sql = "SELECT * FROM Events order by id asc;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            id = cursor.getInt(0);
            type = cursor.getString(1);
            count = cursor.getInt(2);
            time = cursor.getString(3);
            date = cursor.getString(4);
            detail = cursor.getString(5);

            if(type.equals("separator")){
                Header header = new Header(date);
                items.add(header);
                Log.i("test", "seperator enter!!!");
            }else{
                Event event = new Event(type, count, time, date, detail);
                items.add(event);
                Log.i("test", "event enter!!!");
            }
        }

        cursor.close();

        return items;
    }

    public int getId(String type, String date){

        String sql = "SELECT id FROM Events where type = '" + type + "' and date = '" + date + "';";
        int id = 0;

        Cursor cursor = database.rawQuery(sql, null);

        while(cursor.moveToNext()){

            id = cursor.getInt(0);

        }
        cursor.close();

        return id;
    }


    public String getEventsById(int id) {

        String detail = null;


        String sql = "SELECT * FROM Events where id = " + id+ ";";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            detail = cursor.getString(5);
        }

        cursor.close();

        return detail;
    }

    public void updateDetail(String detail, int id) {


        String sql = "UPDATE Events SET detail='" +  detail +  "' where id = " + id +";";

        try {
            database.execSQL(sql);
            Log.i("test", sql);
        } catch (Exception e) {
            Log.e("test", "DB error! - " + e);
            e.printStackTrace();
        }
    }

    public String getRecentDate(){

        String sql = "SELECT * FROM Events ORDER BY id desc limit 1;";

        String date = null;

        Cursor cursor = database.rawQuery(sql, null);


        while(cursor.moveToNext()){

            date = cursor.getString(4);

        }
        cursor.close();

        return date;
    }

    public int getDailyCount(String type) {

        int count = 0;

        String sql = "SELECT * FROM Events where type = '" + type + "' ORDER BY id desc limit 1;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            count = cursor.getInt(2);
        }

        cursor.close();

        return count;
    }

    public int getRecentID(String type, int count, String time){

        String sql = "SELECT id FROM Events where type = '" + type + "' and count =  '" + count + "' and time = '" + time + "';";
        Log.i("test", sql);
        int id = 0;

        Cursor cursor = null;
        try {
            cursor = database.rawQuery(sql, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while(cursor.moveToNext()){

            id = cursor.getInt(0);

        }
        cursor.close();

        return id;
    }


    public void stop(){
        database.close();
    }
}
