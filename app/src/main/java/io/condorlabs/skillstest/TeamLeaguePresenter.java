package io.condorlabs.skillstest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.condorlabs.skillstest.interfaces.TeamLeague;

public class TeamLeaguePresenter implements TeamLeague.Presenter {

    private Context context;
    private TeamLeague.View view;
    private TeamLeague.Model model;

    public TeamLeaguePresenter(Context context, TeamLeague.View view) {
        this.context = context;
        this.view = view;
        model = new TeamLeagueModel(this, context.getResources());
    }

    @Override
    public void getTeamsOfLeague(String teamLeaguesName) {
        if (hasInternetConecction())
            model.getTeamsOfLeague(teamLeaguesName);
        else
            view.showTeamsOfLeagueError("Sin conexión");
    }

    @Override
    public void showTeamsOfLeague(String leageName, String response) {
        if (view != null) {
            try {
                JSONObject teamsOfLeaguesObject = new JSONObject(response);
                JSONArray teamsOfLeaguesArray = teamsOfLeaguesObject.getJSONArray("teams");
                ArrayList<TeamOfLeague> teamOfLeagues = new ArrayList<TeamOfLeague>(teamsOfLeaguesArray.length());

                for (int i = 0; i < teamsOfLeaguesArray.length(); i++) {
                    JSONObject jsonObjectTeamLeague = teamsOfLeaguesArray.getJSONObject(i);

                    teamOfLeagues.add(new TeamOfLeague(
                            jsonObjectTeamLeague.getInt("idTeam"),
                            jsonObjectTeamLeague.getString("strTeam"),
                            jsonObjectTeamLeague.getString("strStadium"),
                            jsonObjectTeamLeague.getString("strTeamBadge"),
                            jsonObjectTeamLeague.getString("strTeamJersey"),
                            jsonObjectTeamLeague.getInt("intFormedYear"),
                            jsonObjectTeamLeague.getString("strDescriptionEN"),
                            jsonObjectTeamLeague.getString("strDescriptionES"),
                            jsonObjectTeamLeague.getString("strWebsite"),
                            jsonObjectTeamLeague.getString("strTwitter"),
                            jsonObjectTeamLeague.getString("strFacebook"),
                            jsonObjectTeamLeague.getString("strInstagram"),
                            jsonObjectTeamLeague.getString("strYoutube"))
                    );
                }

                view.showTeamsOfLeague(leageName, teamOfLeagues);
            } catch (JSONException e) {
                e.printStackTrace();
                view.showTeamsOfLeagueError(context.getString(R.string.txt_error_with_json_parsing));
            }

        }
    }

    @Override
    public void showTeamsOfLeagueError(String error) {
        if (view != null) {
            view.showTeamsOfLeagueError(error);
        }
    }


    public boolean hasInternetConecction() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
