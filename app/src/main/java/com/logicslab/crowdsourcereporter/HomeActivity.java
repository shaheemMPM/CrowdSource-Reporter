package com.logicslab.crowdsourcereporter;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.gms.maps.MapView;

import java.io.File;

public class HomeActivity extends AppCompatActivity {

    Button subm, imageA, imageB, imageC, imageD;
    private Uri imageCapture;
    private static final int PICK_FROM_CAMERA = 1, PICK_FROM_FILE = 2;
    MapView mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        subm = findViewById(R.id.btnSubmit);
        imageA = findViewById(R.id.btnImg1);
        imageB = findViewById(R.id.btnImg2);
        imageC = findViewById(R.id.btnImg3);
        imageD = findViewById(R.id.btnImg4);

        mMap = findViewById(R.id.mapCurrent);




        final String [] items = new String[] {"From Camera", "From Storage"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = new File(Environment.getExternalStorageDirectory(), "tmp"+String.valueOf(System.currentTimeMillis())+".jpg");
                    imageCapture = Uri.fromFile(file);
                    try {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCapture);
                        intent.putExtra("return data", true);
                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    dialog.cancel();
                }else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Complete Action Using"), PICK_FROM_FILE);
                }
            }
        });

        final AlertDialog dialog = builder.create();

        imageA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        imageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        imageC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        imageD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent successPage = new Intent(getApplicationContext(), SuccessSubmit.class);
                startActivity(successPage);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RESULT_OK){
            return;
        }
        Bitmap bitmap = null;
        String path = "";
        if (requestCode == PICK_FROM_FILE){
            imageCapture = data.getData();
            path = getRealPathFromURI(imageCapture);
            if (path == null)
                path = imageCapture.getPath();
            if (path != null)
                bitmap = BitmapFactory.decodeFile(path);
        }else {
            path = imageCapture.getPath();
            bitmap = BitmapFactory.decodeFile(path);
        }
        BitmapDrawable ima = new BitmapDrawable(getApplicationContext().getResources(),bitmap);
        imageA.setBackground(ima);

    }

    public String getRealPathFromURI(Uri contentURI){
        String [] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentURI, proj, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent aboutPage = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(aboutPage);
        return super.onOptionsItemSelected(item);
    }
}
