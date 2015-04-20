package hudi.android.hyes.ImFine_v03;

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


/**
 * Created by hyes on 2015. 4. 8..
 */
public class Widget extends AppWidgetProvider {

    private static final String TAG = "ImFineWidget";
    private Context context;

    @Override
    public void onEnabled(Context context) {
        Log.i(TAG, "======================= onEnabled() =======================");
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_1st);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
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
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_1st);

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

        String action = intent.getAction();
        Log.d(TAG, "onReceive() action = " + action);

        // Default Recevier
        if(AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)){

        }
        else if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)){
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            initUI(context, manager, manager.getAppWidgetIds(new ComponentName(context, getClass())));
        }
        else if(AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)){

        }
        else if(AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action)){

        }

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

    }

