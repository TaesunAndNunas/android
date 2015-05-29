package test.chart.hyes.imfine_04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hyes on 2015. 5. 20..
 */
public class Diaper extends ActionBarActivity{

    private static final int TAKE_PICTURE = 1;
    private static final int PICK_PICTURE = 2;
    private Uri outputFileUri;
    private Button cancel_btn, save_btn;
    private TextView memo;
    private RadioButton good, soso, bad;
    private Switch diaper_switch;
    private ImageView pic, cam;
    private int id;
    private String detail, good_val, soso_val, bad_val, memo_val, pic_val, diaper_switch_val, url, filePath, fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaper_detail);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        Log.i("test", "id" + id);

        Dao dao = new Dao(getApplicationContext());
        detail = dao.getEventsById(id);


        cancel_btn = (Button) findViewById(R.id.cancel_btn);
        save_btn = (Button) findViewById(R.id.save_btn);
        memo = (TextView) findViewById(R.id.memo);
        bad = (RadioButton)findViewById(R.id.bad);
        soso = (RadioButton)findViewById(R.id.soso);
        good = (RadioButton)findViewById(R.id.good);
        diaper_switch = (Switch) findViewById(R.id.diaper_switch);
        pic = (ImageView) findViewById(R.id.diaper_picture);
        cam = (ImageView) findViewById(R.id.diaper_camera_btn);


        JSONParser parser = new JSONParser();
        try {
            Object obj_j = parser.parse(detail);

            JSONObject jsonObject = (JSONObject) obj_j;

            memo_val = jsonObject.get("memo").toString();
            good_val = jsonObject.get("good").toString();
            soso_val = jsonObject.get("soso").toString();
            bad_val = jsonObject.get("bad").toString();
            diaper_switch_val = jsonObject.get("pee").toString();
            pic_val = jsonObject.get("pic").toString();

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


        try{
            if (good_val.equals("good")) {
                good.setChecked(!good.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        try{
            if (soso_val.equals("soso")) {
                soso.setChecked(!soso.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        try{
            if (bad_val.equals("bad")) {
                bad.setChecked(!bad.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        try{
            if (diaper_switch_val.equals("pee")) {
                diaper_switch.setChecked(!diaper_switch.isChecked());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        try {
            //pic.setImageURI(Uri.parse(pic_val));
            pic.setImageBitmap(loadCaptureView(pic_val));

        } catch (Exception e) {
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

                if (good.isChecked()) {
                    jsonObj.put("good", "good");
                } else {
                    jsonObj.put("good", "");
                }

                if (soso.isChecked()) {
                    jsonObj.put("soso", "soso");
                }else {
                    jsonObj.put("soso", "");
                }
                if (bad.isChecked()) {
                    jsonObj.put("bad", "bad");
                }else{
                    jsonObj.put("bad", "");
                }

                if (diaper_switch.isChecked()) {
                    jsonObj.put("pee", "pee");
                } else {
                    jsonObj.put("pee", "");
                }



                if(filePath == null) {
                    jsonObj.put("pic", pic_val);
                }else {
                    jsonObj.put("pic", filePath);
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

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaper_camera_selection();
            }
        });

    }

    private void diaper_camera_selection(){

        final CharSequence[] items = {"새로 사진 찍기", "사진 보관함에서 첨부하기"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Diaper.this);
        builder.setTitle("배변 사진 첨부");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {

                    String file = DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString()+".jpg";

                    final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    url = "ImFine_" + file;
                    final File photo = new File(Environment.getExternalStorageDirectory(),  url);
                    outputFileUri = Uri.fromFile(photo);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                    int temp = outputFileUri.toString().length();
                    filePath = outputFileUri.toString().substring(7, temp);
                    Log.i("test", filePath);
                    startActivityForResult(intent, TAKE_PICTURE);
//
                } else if (item == 1){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                    startActivityForResult(intent, PICK_PICTURE);
                }

            }
        });

        builder.create().show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK && requestCode == 1) {

                    Uri uri= outputFileUri;
                    Bitmap bitmap=null;
                    try {
                        AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(uri, "r");
                        BitmapFactory.Options opt = new BitmapFactory.Options();
                        opt.inSampleSize = 4;
                        bitmap = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor(), null, opt);

                        Matrix matrix = new Matrix();
                        matrix.postRotate(90);
                        Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                                matrix, true);
                        pic.setImageBitmap(rotated);

                    } catch (OutOfMemoryError e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
//                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//                        imageView.setImageDrawable(drawable);
                }

            case PICK_PICTURE:
                if (resultCode == Activity.RESULT_OK && requestCode == 2) {
                    try {
                        Uri uri = getRealPathUri(data.getData());
                        filePath = uri.toString();
                       // fileName = uri.getLastPathSegment();

                        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                        Log.e("image original path: ", filePath);
                        pic.setImageBitmap(bitmap);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

        }
    }


    private Bitmap loadCaptureView(String uri) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither=false;
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(uri, options);
        return bitmap;
    }

    private Uri getRealPathUri(Uri uri) {
        Log.e("content provider uri", uri.toString());
        Uri filePathUri = uri;
        if (uri.getScheme().compareTo("content") == 0) {
            Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                filePathUri = Uri.parse(cursor.getString(column_index));
            }
            cursor.close();
        }
        return filePathUri;
    }
}
