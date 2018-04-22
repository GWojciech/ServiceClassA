package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;



import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.operations.TableWithResults;


public class TableGroup1Activity extends Fragment {

    private static int order=1;
    private static int oldPage=1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_group1, container, false);
        setTeamsInTheView(view);
        Button button = (Button) view.findViewById(R.id.buttonPoints);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableWithResults tableWithResults = new TableWithResults(getContext());
                TableLayout table = (TableLayout) view.findViewById(R.id.tableLayout1);
                if(order==1){
                   order--;
                }
                else {
                    order++;
                }
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        Intent intent = getActivity().getIntent();
                        startActivity(intent);
                    }
                });

            }
        });


        return view;
    }



    public void setTeamsInTheView(View view) {
        TableWithResults tableWithResults = new TableWithResults(getContext());
        tableWithResults.createSeasonTable(view, order, 1);

    }


}
