package hudi.android.hyes.ImFine_v03;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hyes on 2015. 4. 2..
 */
public class Diaper extends ActionBarActivity {

    private static final int TAKE_PICTURE = 1;
    private static final int PICK_PICTURE = 2;
    private Uri outputFileUri;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaper_detail);

        imageView = (ImageView) findViewById(R.id.diaper_picture);

        ImageView camera_btn = (ImageView)findViewById(R.id.diaper_camera_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {

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
