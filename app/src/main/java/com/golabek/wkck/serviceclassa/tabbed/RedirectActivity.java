package com.golabek.wkck.serviceclassa.tabbed;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.golabek.wkck.serviceclassa.Group1Activity;
import com.golabek.wkck.serviceclassa.R;

public class RedirectActivity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_group2, container, false);
        Button button = (Button) view.findViewById(R.id.buttonToGr1FromGr2);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Group1Activity.class));
                getActivity().finishAfterTransition();
            }
        });

        return  view;
    }
}
