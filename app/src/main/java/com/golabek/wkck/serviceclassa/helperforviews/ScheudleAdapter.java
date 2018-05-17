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

import java.util.Date;
import java.util.ArrayList;

public class ScheudleAdapter extends ArrayAdapter<Matches> {
    private TransactionFilter filter;
    private ArrayList<Matches> originalList;
    private ArrayList<Matches> matchesList;

    public ScheudleAdapter(Context context, int textViewResourceId, ArrayList<Matches> matchesList) {
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
        View matchesView = playerInflater.inflate(R.layout.scheudle_info, parent, false);

        TextView firstLabel = (TextView) matchesView.findViewById(R.id.homeTeamScheuldeTextView);
        TextView secondLabel = (TextView) matchesView.findViewById(R.id.awayTeamScheuldeTextView);
        TextView thirdLabel = (TextView) matchesView.findViewById(R.id.dateOfMatchScheudleTextView);
        TextView VSLabel = (TextView) matchesView.findViewById(R.id.VSScheuldeTextView);
        VSLabel.setText("vs");
        Matches currentMatch = matchesList.get(position);
        firstLabel.setText(currentMatch.getHomeTeam());
        secondLabel.setText(currentMatch.getAwayTeam());
        Date date = currentMatch.getDateOfMatch();
        thirdLabel.setText("0"+date.getDay()+"."+"0"+date.getMonth()+"."+"202"+date.getDay() + ", godz. "+date.getHours()+ ":0"+ date.getMinutes());



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
