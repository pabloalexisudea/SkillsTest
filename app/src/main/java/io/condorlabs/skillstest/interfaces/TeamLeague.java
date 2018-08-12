package io.condorlabs.skillstest.interfaces;

import java.util.ArrayList;

import io.condorlabs.skillstest.TeamOfLeague;

public interface TeamLeague {

    interface View {
        void showTeamsOfLeague(String leagueName, ArrayList<TeamOfLeague> teamsLeague);

        void showTeamsOfLeagueError(String error);
    }

    interface Presenter {
        void showTeamsOfLeague(String leagueName, String response);

        void showTeamsOfLeagueError(String error);

        void getTeamsOfLeague(String teamLeaguesName);
    }

    interface Model {
        void getTeamsOfLeague(String teamLeaguesName);
    }
}
