package edu.sabanciuniv.operatingsystemsexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;

public class PostCommentActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    EditText postTxtName;
    EditText postTxt;
    Button btnPost;
    String retVal = "FAIL";
    TextView textViewPost;

    Handler sayHelloHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            retVal = msg.obj.toString();

            progressDialog.hide();

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending data, please wait...");

        textViewPost = findViewById(R.id.textViewPost);
        postTxtName = findViewById(R.id.postTxtName);
        postTxt = findViewById(R.id.postTxt);
        btnPost = findViewById(R.id.btnPost);

        progressDialog.hide();




        setTitle("Post Comment");

        //geri butonu için ilk olarak application bar a erişmemiz gerekiyor
        ActionBar currentBar = getSupportActionBar();

        currentBar.setHomeButtonEnabled(true);
        currentBar.setDisplayHomeAsUpEnabled(true);
        currentBar.setHomeAsUpIndicator(androidx.constraintlayout.widget.R.drawable.abc_ic_ab_back_material);

        int id = getIntent().getIntExtra("id", 0);


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String name = postTxtName.getText().toString();
                String text = postTxt.getText().toString();
                String news_id = Integer.toString(id);
                ExecutorService srv = ((NewsApp)getApplication()).srv;
                NewsRepo repo = new NewsRepo();
                if(name.equals("") || text.equals("")){

                    textViewPost.setText("Please enter a name and a message!");

                }else{
                    textViewPost.setText("");
                    progressDialog.show();
                    repo.postComment(srv, sayHelloHandler, name, text, news_id);
                    Intent i = new Intent(PostCommentActivity.this, CommentsActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);


                }




            }
        });

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //eğer menü tuşu geri ise
        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return true;
    }



}