package com.example.franks.tempcalc.fragments;

import com.example.franks.tempcalc.models.TipRecord;


public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
