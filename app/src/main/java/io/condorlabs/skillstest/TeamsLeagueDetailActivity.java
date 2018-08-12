package io.condorlabs.skillstest;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.condorlabs.skillstest.fragments.TeamsLeagueDetailFragment;

public class TeamsLeagueDetailActivity extends AppCompatActivity {

    private TeamOfLeague teamOfLeague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_league_detail);

        teamOfLeague = (TeamOfLeague) getIntent().getParcelableExtra("teamOfLeague");

        if (teamOfLeague == null) {
            finish();
            return;
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            TeamsLeagueDetailFragment details = new TeamsLeagueDetailFragment(teamOfLeague);
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}
