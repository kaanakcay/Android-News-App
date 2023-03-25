package edu.sabanciuniv.operatingsystemsexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ActivityDetails extends AppCompatActivity {

    ImageView imgDetails;
    TextView txtNameDetail;
    TextView txtHistoryDetail;
    TextView txtDateDetail;
    int intent_int;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<News> data = (List<News>) msg.obj;

            News opSys = data.get(0);
                // TODO: Update the UI to display the information from the opSys object


            //OperatingSys opSys = (OperatingSys) msg.obj;

            txtNameDetail.setText(opSys.getTitle());
            txtHistoryDetail.setText(opSys.getText());
            txtDateDetail.setText(opSys.getDate());
            intent_int = opSys.getId();

            NewsRepo repo = new NewsRepo();
            repo.downloadImage(((NewsApp)getApplication()).srv,imgHandler,opSys.getImagePath());

            setTitle(opSys.getCategoryName());

            return true;
        }
    });

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Bitmap img = (Bitmap) msg.obj;
            imgDetails.setImageBitmap(img);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        int id = getIntent().getIntExtra("id",1);


        imgDetails =findViewById(R.id.imgDetails);
        txtNameDetail = findViewById(R.id.txtNameDetail);
        txtHistoryDetail = findViewById(R.id.textHist);
        txtDateDetail = findViewById(R.id.txtDateDetail);

        NewsRepo repo = new NewsRepo();
        repo.getDataById(((NewsApp)getApplication()).srv,dataHandler,id);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //eğer menü tuşu geri ise
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        //eğer menü tuşu show comments ise
        if(item.getItemId() == R.id.mn_comment){
            Intent i = new Intent(this, CommentsActivity.class);
            i.putExtra("id", intent_int);
            startActivity(i);
        }


        return true;
    }


}