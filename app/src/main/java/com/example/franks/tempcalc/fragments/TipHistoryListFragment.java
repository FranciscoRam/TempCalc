package com.example.franks.tempcalc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;



import com.example.franks.tempcalc.adapters.TipAdapter;
import com.example.franks.tempcalc.models.TipRecord;

import com.example.franks.tempcalc.R;


public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    TipAdapter adapter;

    public TipHistoryListFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;

    }


    private void initAdapter() {
        if(adapter == null) {
            adapter = new TipAdapter(getActivity().getApplicationContext(), new ArrayList<TipRecord>());
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);
    }

    @Override
        public void clearList() {
        adapter.clear();
    }
}
