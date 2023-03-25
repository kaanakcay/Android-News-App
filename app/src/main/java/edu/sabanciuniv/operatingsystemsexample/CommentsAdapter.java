package edu.sabanciuniv.operatingsystemsexample;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.OpsysViewHolder>{

    private Context ctx;
    private List<Comments> data;

    public CommentsAdapter(Context ctx, List<Comments> data){
        this.ctx = ctx;
        this.data = data;
    }


    @NonNull
    @Override
    public CommentsAdapter.OpsysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root= LayoutInflater.from(ctx).inflate(R.layout.comment_details,parent,false);

        CommentsAdapter.OpsysViewHolder holder = new CommentsAdapter.OpsysViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.OpsysViewHolder holder, int position) {


        //burada rowun textini değiştirdik
        holder.commentTxtName.setText(data.get(holder.getAdapterPosition()).getName());
        holder.commentTxt.setText(data.get(holder.getAdapterPosition()).getText());
        NewsApp app = (NewsApp)((AppCompatActivity)ctx).getApplication();




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OpsysViewHolder extends RecyclerView.ViewHolder{

        TextView commentTxtName;
        TextView commentTxt;
        ConstraintLayout row;


        public OpsysViewHolder(@NonNull View itemView) {
            super(itemView);

            commentTxtName = itemView.findViewById(R.id.commentTxtName);
            commentTxt = itemView.findViewById(R.id.commentTxt);
            row = itemView.findViewById(R.id.comment_row);

        }



    }



}


