package edu.sabanciuniv.operatingsystemsexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.OpsysViewHolder>{

    private Context ctx;
    private List<News> data;

    public NewsAdapter(Context ctx, List<News> data){
        this.ctx = ctx;
        this.data = data;
    }


    @NonNull
    @Override
    public OpsysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View root= LayoutInflater.from(ctx).inflate(R.layout.opsys_row_layout,parent,false);

       OpsysViewHolder holder = new OpsysViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OpsysViewHolder holder, int position) {


        //burada rowun textini değiştirdik
        holder.txtListName.setText(data.get(holder.getAdapterPosition()).getTitle());
        holder.txtDate.setText(data.get(holder.getAdapterPosition()).getDate());
        NewsApp app = (NewsApp)((AppCompatActivity)ctx).getApplication();

        holder.downloadImage(app.srv,data.get(holder.getAdapterPosition()).getImagePath());

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx,ActivityDetails.class);
                i.putExtra("id",data.get(holder.getAdapterPosition()).getId());
                ctx.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OpsysViewHolder extends RecyclerView.ViewHolder{

        TextView txtListName;
        TextView txtDate;
        ImageView imgList;
        ConstraintLayout row;
        boolean imageDownloaded;

        Handler imgHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                Bitmap img = (Bitmap)msg.obj;
                imgList.setImageBitmap(img);
                imageDownloaded = true;
                return true;
            }
        });


        public OpsysViewHolder(@NonNull View itemView) {
            super(itemView);

            txtListName = itemView.findViewById(R.id.txtListName);
            txtDate = itemView.findViewById(R.id.txtDate);
            imgList = itemView.findViewById(R.id.imgList);
            row = itemView.findViewById(R.id.row_list);

        }

       public void downloadImage(ExecutorService srv, String path){

            if (!imageDownloaded){

                NewsRepo repo = new NewsRepo();
                repo.downloadImage(srv,imgHandler,path);


            }



       }

    }



}
