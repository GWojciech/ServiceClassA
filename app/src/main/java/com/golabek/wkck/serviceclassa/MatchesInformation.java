package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.database.models.Matches;
import com.golabek.wkck.serviceclassa.services.InputStreamStringConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class MatchesInformation extends AppCompatActivity {
    public static Matches matchToShow;
    private String description="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches_information);
        new MatchInfo().execute((Void) null);

    }

    private void fillNameAndResults(){
        TextView textView = (TextView) findViewById(R.id.homeTeamNameInfoTextView);
        textView.setText(matchToShow.getHomeTeam());
        textView = (TextView) findViewById(R.id.awayTeamNameInfoTextView);
        textView.setText(matchToShow.getAwayTeam());
        textView = (TextView) findViewById(R.id.homeTeamGoalsInfo);
        textView.setText(matchToShow.getHomeTeamGoals().toString());
        textView = (TextView) findViewById(R.id.awayTeamGoalsInfo);
        textView.setText(matchToShow.getAwayTeamGoals().toString());
        textView = (TextView) findViewById(R.id.matchEventsTextView);
        textView.setText("Przebieg spotkania:");

    }

    private void showInfo(){
        fillNameAndResults();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutToInfo);
        TextView textView = null;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 0);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        Scanner scanner = new Scanner(description);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            textView = new TextView(getApplicationContext());
            textView.setText( line);
            textView.setTextColor(Color.BLACK);
            textView.setLayoutParams(lp);
            linearLayout.addView(textView);
        }
        scanner.close();

        Button button = (Button) findViewById(R.id.returnToResultsFromInfo);
        initListener(button);
        textView =(TextView)findViewById(R.id.moreMatchesHyperLinkTextView);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://kieleckapilka.pl/competition.php?compid=806'> Więcej informacji </a>";
        textView.setText(Html.fromHtml(text));
    }

    private void initListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MatchesInformation.this, Group1Activity.class));
                finishAfterTransition();
            }
        });
    }

    public class MatchInfo extends AsyncTask<Void, Void, Boolean> {


        JSONObject getResults(){
            URL url = null;
            try {
                url = new URL(getString(R.string.URL_address)+"/results/get/one/"+matchToShow.getId().toString() );
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(false);
                connection.setDoInput(true);
                JSONObject jsonObject;
                InputStream in = new BufferedInputStream(connection.getInputStream());
                String text = InputStreamStringConverter.streamToString(in);
                in.close();

                if(connection.getResponseCode()==200) {

                    jsonObject = new JSONObject(text);
                    connection.disconnect();
                    return jsonObject;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            JSONObject match = getResults();

                try {
                    description = match.getString("description");

                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }


            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                showInfo();
            } else {
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
