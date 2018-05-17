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
import java.util.ArrayList;
import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.forQueries.mock.ScorersRank;

public class SearchPlayerAdapter extends ArrayAdapter<ScorersRank> {
    private TransactionFilter filter;
    private ArrayList<ScorersRank> originalList;
    private ArrayList<ScorersRank> playerList;

    public SearchPlayerAdapter(Context context, int textViewResourceId, ArrayList<ScorersRank> playerList) {
        super(context, textViewResourceId, playerList);
        this.playerList = new ArrayList<ScorersRank>();
        this.playerList.addAll(playerList);
        this.originalList = new ArrayList<ScorersRank>();
        this.originalList.addAll(playerList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater playerInflater = LayoutInflater.from(getContext());
        View searchPlayerView = playerInflater.inflate(R.layout.search_info, parent, false);

        TextView firstLabel = (TextView) searchPlayerView.findViewById(R.id.firstSearchOther);
        TextView secondLabel = (TextView) searchPlayerView.findViewById(R.id.secondSearchOther);
        TextView firstContent = searchPlayerView.findViewById(R.id.firstSearchOtherContent);
        TextView secondContent = searchPlayerView.findViewById(R.id.secondSearchOtherContent);

        ScorersRank currentPlayer = playerList.get(position);
        firstLabel.setText("Imię i nazwisko: ");
        firstContent.setText(currentPlayer.getName()+" " + currentPlayer.getSurname());
        secondLabel.setText("Drużyna: ");
        secondContent.setText(currentPlayer.getTeamName());


        return searchPlayerView;


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
                ArrayList<ScorersRank> filteredItems = new ArrayList<ScorersRank>();

                for(int i = 0, l = originalList.size(); i < l; i++)
                {
                    ScorersRank country = originalList.get(i);
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

            playerList = (ArrayList<ScorersRank>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = playerList.size(); i < l; i++)
                add(playerList.get(i));
            notifyDataSetInvalidated();
        }
    }


}

