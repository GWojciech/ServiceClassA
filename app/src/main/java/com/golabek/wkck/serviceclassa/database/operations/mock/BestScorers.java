package com.golabek.wkck.serviceclassa.database.operations.mock;


import android.util.Log;
import com.golabek.wkck.serviceclassa.database.forQueries.mock.ScorersRank;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestScorers {


    private String [] names = {"Jan", "Mateusz", "Wojciech", "Piotr", "Marian", "Paweł", "Ignacy", "Walerian","Krzysztof", "Andrzej", "Bartłomiej", "Szymon"};
    private String [] surnames = {"Kowalski", "Jansda", "Skowronek", "Kowal", "Kot", "Igos", "Łatas", "Warki", "Mapkli", "Zizia", "Łozia", "Kici"};
    private String [] teamName ={"PŚk Kielce", "Orlęta Kielce", "MSS-Klonówka Masłów", "Astra Piekoszów", "Górnik Rykoszyn", "Wicher Miedziana Góra", "Radiator$ Stąporków", "Top-Spin Promnik", "Victoria Mniów" , "Piast Chęciny", "ŁKS Łopuszno", "Tęcza Gowarczów"};
    private Integer [] goals ={1,3,6,4,2,7,9,2,5,7,6,4,1};
    private Integer [] assists = {5,7,3,1,6,8,2,7,4,2,1,4};

    public List<ScorersRank> getScorersList (){

        List <ScorersRank> listOfScorers = new ArrayList<>();

        ScorersRank scorersRank;
        for(Integer i =0; i<12; i++){
            scorersRank = new ScorersRank();
            scorersRank.setName(names[i]);
            scorersRank.setSurname(surnames[i]);
            scorersRank.setTeamName(teamName[i]);
            scorersRank.setGoals(goals[i]);
            scorersRank.setAssists(assists[i]);
            listOfScorers.add(scorersRank);
        }
        Log.d("LISTA:", listOfScorers.toString());
        Collections.sort(listOfScorers);
        Log.d("LISTA:", listOfScorers.toString());
        return listOfScorers;
}

}
