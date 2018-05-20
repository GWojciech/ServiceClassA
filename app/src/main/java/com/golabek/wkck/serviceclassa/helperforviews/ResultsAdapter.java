package com.golabek.wkck.serviceclassa.helperforviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.models.mock.Matches;

import java.util.ArrayList;
import java.util.Date;

public class ResultsAdapter extends ArrayAdapter<Matches> {

    TransactionFilter filter;
    private ArrayList<Matches> originalList;
    private ArrayList<Matches> matchesList;

    public ResultsAdapter(Context context, int textViewResourceId, ArrayList<Matches> matchesList) {
        super(context, textViewResourceId, matchesList);
        this.matchesList = new ArrayList<Matches>();
        this.matchesList.addAll(matchesList);
        this.originalList = new ArrayList<Matches>();
        this.originalList.addAll(matchesList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater playerInflater = LayoutInflater.from(getContext());
        View matchesView = playerInflater.inflate(R.layout.result_info, parent, false);

        TextView homeTeamName = matchesView.findViewById(R.id.homeTeamNameResultTextView);
        TextView awayTeamName = matchesView.findViewById(R.id.awayTeamNameResultTextView);
        TextView resultHome = matchesView.findViewById(R.id.homeTeamGoalsResult);
        TextView resultAway = matchesView.findViewById(R.id.awayTeamGoalsResult);


        Matches currentMatch = matchesList.get(position);
        homeTeamName.setText(currentMatch.getHomeTeam());
        awayTeamName.setText(currentMatch.getAwayTeam());
        resultHome.setText(currentMatch.getHomeTeamGoals().toString());
        resultAway.setText(currentMatch.getAwayTeamGoals().toString());


        return matchesView;


    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new TransactionFilter();
        }
        return filter;
    }

    private class TransactionFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<Matches> filteredItems = new ArrayList<Matches>();

                for(int i = 0, l = originalList.size(); i < l; i++)
                {
                    Matches country = originalList.get(i);
                    if(country.toString().toLowerCase().contains(constraint))
                        filteredItems.add(country);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = originalList;
                    result.count = originalList.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            matchesList = (ArrayList<Matches>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = matchesList.size(); i < l; i++)
                add(matchesList.get(i));
            notifyDataSetInvalidated();
        }
    }
}
