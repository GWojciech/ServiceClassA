package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.R;

public class TableGroup1Activity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_group1, container, false);
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tableLayout1);
        TableRow tableRow = (TableRow) view.findViewById(R.id.tableTableRow3);
        TableRow newTableRow = new TableRow(this.getContext());
        TextView textView = new TextView(tableRow.getContext());
        textView.setText("1");
        newTableRow.addView(textView);
        textView = new TextView(tableRow.getContext());
        textView.setText("Korona");
        newTableRow.addView(textView);
//        textView.setText("43");
//        newTableRow.addView(textView);
        tableLayout.addView(newTableRow);

        return  view;
    }

    public void setTeamsInTheView(){

    }
}
