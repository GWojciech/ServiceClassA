package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.database.models.ConcretePlayer;
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
import java.util.Random;

public class PlayerInformationActivity extends AppCompatActivity {
    public static Integer number;
    public static boolean returnToClassification = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_information);

        Button button = (Button) findViewById(R.id.buttonReturnPlayer);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(returnToClassification) {
                    startActivity(new Intent(PlayerInformationActivity.this, Group1Activity.class));
                }
                else {
                    startActivity(new Intent(PlayerInformationActivity.this, OtherActivity.class));
                }
                finishAfterTransition();
            }
        });
        new PlayerTask().execute((Void) null);

    }

    private void fillView(ConcretePlayer concretePlayer){
        TextView textView = (TextView) findViewById(R.id.nameOfPlayer);
        textView.setText(concretePlayer.getName());
        textView = (TextView) findViewById(R.id.surnameOfPlayer);
        textView.setText(concretePlayer.getSurname());
        textView = (TextView) findViewById(R.id.surnameOfPlayer);
        textView.setText(concretePlayer.getSurname());
        textView = (TextView) findViewById(R.id.nameOfPlayersTeam);
        textView.setText(concretePlayer.getTeamName());
        textView = (TextView) findViewById(R.id.goalsPlayer);
        textView.setText(textView.getText()+" "+concretePlayer.getGoals().toString());
        textView = (TextView) findViewById(R.id.assistPlayer);
        textView.setText(textView.getText()+" "+ concretePlayer.getAssists().toString());
        Random generator = new Random();
        textView = (TextView) findViewById(R.id.dateOfBirth);
        textView.setText(textView.getText()+" "+concretePlayer.getBirthDate());
        textView = (TextView) findViewById(R.id.playerInfoWebsite);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href="+concretePlayer.getWebsite()+"> Więcej informacji </a>";
        textView.setText(Html.fromHtml(text));

    }


    public class PlayerTask extends AsyncTask<Void, Void, Boolean> {

        private ConcretePlayer concretePlayer;

        JSONObject getTeamJSONObject(){
            URL url = null;
            try {
                url = new URL(getString(R.string.URL_address)+"/player/get/"+number.toString());
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
            JSONObject jsonObject = getTeamJSONObject();
            Integer id;
            String name;
            String surname;
            String birthDate;
            Integer goals;
            Integer assists;
            String website;
            String teamName;
            try {
                id = jsonObject.optInt("id");
                name = jsonObject.getString("name");
                surname = jsonObject.getString("surname");
                birthDate = jsonObject.getString("birthDate");
                goals = jsonObject.optInt("goals");
                assists = jsonObject.optInt("assists");
                website = jsonObject.getString("website");
                teamName = jsonObject.getString("teamName");


                concretePlayer = new ConcretePlayer(id, name, surname, birthDate, goals, assists, website, teamName);
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
                fillView(concretePlayer);
            } else {
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
