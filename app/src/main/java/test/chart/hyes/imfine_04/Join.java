package test.chart.hyes.imfine_04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hyes on 2015. 5. 30..
 */
public class Join extends ActionBarActivity {

    private static final int TAKE_PICTURE = 1;
    private static final int PICK_PICTURE = 2;
    private Uri outputFileUri;
    private ImageView imageView;
    private RadioButton male, female;
    private EditText id_et, name, pwd1, pwd2, birth;
    private Button join;
    private String profile_gender, profile_name, profile_pwd, profile_id, profile_birth, profile_image;
    private UserProfile profile = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        imageView = (ImageView) findViewById(R.id.profile_picture);
        male= (RadioButton)findViewById(R.id.male_radio_btn);
        female = (RadioButton)findViewById(R.id.female_radio_btn);
        id_et = (EditText) findViewById(R.id.id_tv);
        name =(EditText) findViewById(R.id.name_tv);
        pwd1 = (EditText) findViewById(R.id.pwd1_tv);
        pwd2 = (EditText) findViewById(R.id.pwd2_tv);
        birth = (EditText) findViewById(R.id.birth_tv);
        join = (Button) findViewById(R.id.register_btn);


//        join.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getApplicationContext(), "BTNonClick", Toast.LENGTH_SHORT).show();
//
//                profile_name = name.getText().toString();
//                profile_pwd = pwd1.getText().toString();
//                profile_id = id_et.getText().toString();
//                profile_birth = birth.getText().toString();
//
//                if (male.isChecked()) {
//                    profile_gender = "male";
//                }else {
//                    profile_gender = "female";
//                }
//
//                UserProfile profile = new UserProfile(profile_name, profile_id, profile_pwd, profile_gender, profile_birth, outputFileUri.toString());
//
//                Proxy proxy = new Proxy();
//
//                proxy.sendProfile(profile,
//                        new Callback<Response>() {
//                            @Override
//                            public void success(Response response, Response response2) {
//                                BufferedReader reader = null;
//                                StringBuilder sb = new StringBuilder();
//                                try {
//                                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
//                                    String line;
//                                    try {
//                                        while ((line = reader.readLine()) != null) {
//                                            sb.append(line);
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                String ok = sb.toString();
//                                Log.i("test", ok);
//                            }
//
//                            @Override
//                            public void failure(RetrofitError error) {
//                                Log.e("error", error.toString());
//
//                            }
//                        }
//                );
//            }
//        });


        ImageView camera_btn = (ImageView)findViewById(R.id.profile_camera_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_camera_selection();
            }
        });


        Button register_btn = (Button)findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                profile_name = name.getText().toString();
                Log.i("test", profile_name);
                profile_pwd = pwd1.getText().toString();
                profile_id = id_et.getText().toString();
                profile_birth = birth.getText().toString();

                if (male.isChecked()) {
                    profile_gender = "male";
                }else {
                    profile_gender = "female";
                }

                profile = new UserProfile(profile_name, profile_id, profile_pwd, profile_gender, profile_birth);

                Proxy proxy = new Proxy();

                proxy.sendProfile(profile,
                        new Callback<Response>() {
                            @Override
                            public void success(Response response, Response response2) {
                                BufferedReader reader = null;
                                StringBuilder sb = new StringBuilder();
                                try {
                                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                                    String line;
                                    try {
                                        while ((line = reader.readLine()) != null) {
                                            sb.append(line);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                String ok = sb.toString();
                                Log.i("test", ok);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.e("error", error.toString());

                            }
                        }
                );

                AlertDialog.Builder register_alert = new AlertDialog.Builder(Join.this);


                register_alert.setMessage("가입이 완료되었습니다.").setCancelable(false).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'YES'
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert = register_alert.create();
                alert.show();

            }
        });

    }

    private void profile_camera_selection(){

        final CharSequence[] items = {"새로 사진 찍기", "사진 보관함에서 첨부하기"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
        builder.setTitle("프로필사진 첨부하기");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {

                    String file = DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString()+".jpg";

                    final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    String url = "ImFine_" + String.valueOf(System.currentTimeMillis()) + file;
                    final File photo = new File(Environment.getExternalStorageDirectory(),  url);
                    outputFileUri = Uri.fromFile(photo);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                    //imageUri = Uri.fromFile(photo);
                    startActivityForResult(intent, TAKE_PICTURE);
                    //
                } else if (item == 1){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                    //intent.setType("image/*");
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
                        imageView.setImageBitmap(rotated);

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
                        Uri uri = data.getData();
                        imageView.setImageURI(uri);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

        }
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

