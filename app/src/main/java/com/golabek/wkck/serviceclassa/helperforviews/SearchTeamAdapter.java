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
import com.golabek.wkck.serviceclassa.database.models.ConcreteTeam;

import java.util.ArrayList;

public class SearchTeamAdapter extends ArrayAdapter<ConcreteTeam> {
    private TransactionFilter filter;
private ArrayList<ConcreteTeam> originalList;
private ArrayList<ConcreteTeam> teamList;

    public SearchTeamAdapter(Context context, int textViewResourceId, ArrayList<ConcreteTeam> teamList) {
        super(context, textViewResourceId, teamList);
        this.teamList = new ArrayList<ConcreteTeam>();
        this.teamList.addAll(teamList);
        this.originalList = new ArrayList<ConcreteTeam>();
        this.originalList.addAll(teamList);

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

        ConcreteTeam currentTeam = teamList.get(position);
        firstLabel.setText("Nazwa drużyny: ");
        firstContent.setText(currentTeam.getTeamName());
        secondLabel.setText("Grupa: ");
        secondContent.setText(currentTeam.getNameOfGroup());

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
            ArrayList<ConcreteTeam> filteredItems = new ArrayList<ConcreteTeam>();

            for(int i = 0, l = originalList.size(); i < l; i++)
            {
                ConcreteTeam country = originalList.get(i);
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

        teamList = (ArrayList<ConcreteTeam>)results.values;
        notifyDataSetChanged();
        clear();
        for(int i = 0, l = teamList.size(); i < l; i++)
            add(teamList.get(i));
        notifyDataSetInvalidated();
    }
}


}
