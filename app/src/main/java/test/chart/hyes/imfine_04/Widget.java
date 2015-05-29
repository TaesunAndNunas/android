package test.chart.hyes.imfine_04;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyes on 2015. 5. 13..
 */
public class Widget extends AppWidgetProvider {

    public static final String ACTION_A ="com.record.widget.A";
    public static final String ACTION_B ="com.record.widget.B";
    public static final String ACTION_C ="com.record.widget.C";
    public static final String ACTION_D ="com.record.widget.D";

    private static final String TAG = "ImFineWidget";
    private Context context;
    private ComponentName recordWidget;
    private RemoteViews views = null;
    private String time_changed;

    private int count_m, count_d, count_f, count_s;


    @Override
    public void onEnabled(Context context) {
        Log.i(TAG, "======================= onEnabled() =======================");
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


//        final int N = appWidgetIds.length;
//
//        for (int i=0; i<N; i++) {
//            int appWidgetId = appWidgetIds[i];
       // RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        recordWidget = new ComponentName(context, Widget.class);
        views = new RemoteViews(context.getPackageName(), R.layout.widget);

        //Medicine Intent
        Intent medicineIntent = new Intent(ACTION_A);
        PendingIntent medicinePendingIntent = PendingIntent.getBroadcast(context, 0, medicineIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_medicine, medicinePendingIntent);

        //Sleep Intent
        Intent sleepIntent = new Intent(ACTION_B);
        PendingIntent sleepPendingIntent = PendingIntent.getBroadcast(context, 0, sleepIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_sleep, sleepPendingIntent);

        //Diaper Intent
        Intent diaperIntent = new Intent(ACTION_C);
        PendingIntent diaperPendingIntent = PendingIntent.getBroadcast(context, 0, diaperIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_diaper, diaperPendingIntent);


        //Feed Intent
        Intent feedIntent = new Intent(ACTION_D);
        PendingIntent feedPendingIntent = diaperPendingIntent.getBroadcast(context, 0, feedIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_milk, feedPendingIntent);

        appWidgetManager.updateAppWidget(recordWidget, views);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);

        int minWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int maxWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int minHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        int maxHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);
        RemoteViews rv = null;

        //크기 변경에 따른 레이아웃 변경코드
//        if(minWidth == 152 && maxWidth == 196 && minHeight == 58 && maxHeight == 84){
//            rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout3x3);
//        } else {
//            rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//        }
//        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.i(TAG, "======================= onDeleted() =======================");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        Log.i(TAG, "======================= onDisabled() =======================");
        super.onDisabled(context);
    }

    /**
     * UI 설정 이벤트 설정
     */
    public void initUI(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i(TAG, "======================= initUI() =======================");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

//            Intent eventIntent              = new Intent(Const.ACTION_EVENT);
//            Intent activityIntent           = new Intent(Const.ACTION_CALL_ACTIVITY);
//            Intent dialogIntent             = new Intent(Const.ACTION_DIALOG);
//
//            PendingIntent eventPIntent          = PendingIntent.getBroadcast(context, 0, eventIntent        , 0);
//            PendingIntent activityPIntent       = PendingIntent.getBroadcast(context, 0, activityIntent     , 0);
//            PendingIntent dialogPIntent         = PendingIntent.getBroadcast(context, 0, dialogIntent       , 0);
//
//            views.setOnClickPendingIntent(R.id.btn_event            , eventPIntent);
//            views.setOnClickPendingIntent(R.id.btn_call_activity    , activityPIntent);
//            views.setOnClickPendingIntent(R.id.btn_set_alram        , dialogPIntent);

        for(int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


    /**
     * Receiver 수신
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget);
        String action = intent.getAction();
        Log.d(TAG, "onReceive() action = " + action);


        if(action.equals(ACTION_A)){
            Log.e("widget","aaaa");


            try{
                time_changed = timestamp();
                Log.i("test", count_m+"");
                count_m += 1;
                Log.i("test", count_m+"");

                Dao dao = new Dao(context);
                dao.eventInsert("medicine", count_m, time_changed);
            //    rv.setTextViewText(R.id.medicine_count, count_m + "");
                rv.setTextViewText(R.id.medicine_time, time_changed);
            }catch (Exception e) {
                // TODO: handle exception
            }
        }else if(action.equals(ACTION_B)){
            Log.e("widget","bbb");
            try{
                time_changed = timestamp();
                count_s += 1;

                Dao dao = new Dao(context);
                dao.eventInsert("sleep", count_m, time_changed);
              //  rv.setTextViewText(R.id.sleep_count, count_s + "");
                rv.setTextViewText(R.id.sleep_time, time_changed);
            }catch (Exception e) {
                // TODO: handle exception
            }
        }else if(action.equals(ACTION_C)){

            try{
                time_changed = timestamp();
                count_d += 1;

                Dao dao = new Dao(context);
                dao.eventInsert("diaper", count_d, time_changed);
             //   rv.setTextViewText(R.id.diaper_count, count_d + "");
                rv.setTextViewText(R.id.diaper_time, time_changed);
            }catch (Exception e) {
                // TODO: handle exception
            }
        }else if(action.equals(ACTION_D)){

            try{
                time_changed = timestamp();
                count_f += 1;

                Dao dao = new Dao(context);
                dao.eventInsert("feed", count_f, time_changed);
            //    rv.setTextViewText(R.id.milk_count, count_f + "");
                rv.setTextViewText(R.id.milk_time, time_changed);
            }catch (Exception e) {
                // TODO: handle exception
            }
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName cpName = new ComponentName(context, Widget.class);
        appWidgetManager.updateAppWidget(cpName, rv);


//        // Default Recevier
//        if(AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)){
//
//        }
//        else if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)){
//            AppWidgetManager manager = AppWidgetManager.getInstance(context);
//            initUI(context, manager, manager.getAppWidgetIds(new ComponentName(context, getClass())));
//        }
//        else if(AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)){
//
//        }
//        else if(AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action)){
//
//        }

        // Custom Recevier
//        else if(Const.ACTION_EVENT.equals(action)){
//            Toast.makeText(context, "Receiver 수신 완료!!.", Toast.LENGTH_SHORT).show();
//        }
//        else if(Const.ACTION_CALL_ACTIVITY.equals(action)){
//            callActivity(context);
//        }
//        else if(Const.ACTION_DIALOG.equals(action)){
//            createDialog(context);
//        }
    }

    /**
     * Activity 호출 (Intent.FLAG_ACTIVITY_NEW_TASK)
     */
    private void callActivity(Context context){
        Log.d(TAG, "callActivity()");
        Intent intent = new Intent("arabiannight.tistory.com.widget.CALL_ACTIVITY");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * Dialog Activity 호출 (PendingIntent)
     */
    private void createDialog(Context context){
        Log.d(TAG, "createDialog()");
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent Intent = new Intent("arabiannight.tistory.com.widget.CALL_PROGRESSDIALOG");
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, Intent, 0);

        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis(), pIntent);
    }

    private String timestamp(){

        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat curTime = new SimpleDateFormat("a hh:mm");
        return time_changed = curTime.format(date);

    }

}
