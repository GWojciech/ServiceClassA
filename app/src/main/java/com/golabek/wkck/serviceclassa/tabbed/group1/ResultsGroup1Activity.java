package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.golabek.wkck.serviceclassa.R;

public class ResultsGroup1Activity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_group1, container, false);

        return  view;
    }
}
