package edu.sabanciuniv.operatingsystemsexample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;


public class FragmentE extends Fragment {


    ProgressBar prg;
    RecyclerView recView;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<News> data = (List<News>)msg.obj;
            NewsAdapter adp = new NewsAdapter(getActivity(), data);
            recView.setAdapter(adp);
            recView.setVisibility(View.VISIBLE);
            prg.setVisibility(View.INVISIBLE);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_e, container, false);

        recView = v.findViewById(R.id.recyclerViewList_E);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        prg = v.findViewById(R.id.progressBar);
        prg.setVisibility(View.VISIBLE);
        recView.setVisibility(View.INVISIBLE);


        NewsRepo repo = new NewsRepo();
        repo.getDataByCatagoryId(((NewsApp) getActivity().getApplication()).srv, dataHandler, 1);

        return v;
    }
}