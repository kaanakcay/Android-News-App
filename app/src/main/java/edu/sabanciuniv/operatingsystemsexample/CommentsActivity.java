package edu.sabanciuniv.operatingsystemsexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {


    ProgressBar prg;
    int id_intent;
    RecyclerView recView;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<Comments> data = (List<Comments>)msg.obj;
            CommentsAdapter adp = new CommentsAdapter(CommentsActivity.this,data);
            recView.setAdapter(adp);
            recView.setVisibility(View.VISIBLE);
            prg.setVisibility(View.INVISIBLE);


            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        recView = findViewById(R.id.recyclerViewList_comments);
        recView.setLayoutManager(new LinearLayoutManager(this));
        prg = findViewById(R.id.progressBar2);
        prg.setVisibility(View.VISIBLE);
        recView.setVisibility(View.INVISIBLE);

        NewsRepo repo = new NewsRepo();
        int id_from = getIntent().getIntExtra("id", 1);
        id_intent = id_from;
        repo.getComments(((NewsApp) getApplication()).srv, dataHandler, id_from);


        setTitle("Comments");

        //geri butonu için ilk olarak application bar a erişmemiz gerekiyor
        ActionBar currentBar = getSupportActionBar();

        currentBar.setHomeButtonEnabled(true);
        currentBar.setDisplayHomeAsUpEnabled(true);
        currentBar.setHomeAsUpIndicator(androidx.constraintlayout.widget.R.drawable.abc_ic_ab_back_material);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.comments_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //eğer menü tuşu geri ise
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        //eğer menü tuşu show comments ise
        if(item.getItemId() == R.id.mn_post_comment){
            Intent i = new Intent(this, PostCommentActivity.class);
            i.putExtra("id", id_intent);
            startActivity(i);
        }


        return true;
    }
}