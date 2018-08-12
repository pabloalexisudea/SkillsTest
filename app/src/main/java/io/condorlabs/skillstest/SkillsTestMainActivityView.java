package io.condorlabs.skillstest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import io.condorlabs.skillstest.fragments.TeamsLegueFragment;
import io.condorlabs.skillstest.interfaces.TeamLeague;

public class SkillsTestMainActivityView extends AppCompatActivity implements TeamLeague.View, TeamsLegueFragment.OnItemListFragmentClickListener {

    private ProgressBar pbTeamsLeague;

    private TextView tvResponseOfServer;
    private TeamLeague.Presenter presenter;
    String[] leaguesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_test_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leaguesArray = getResources().getStringArray(R.array.string_leagues_array);

        //tvResponseOfServer = findViewById(R.id.tvResponse)
        pbTeamsLeague = findViewById(R.id.progressBarLoadTeamsLeague);
        presenter = new TeamLeaguePresenter(this, this);

        if (savedInstanceState == null) {
            presenter.getTeamsOfLeague(leaguesArray[0]);
        } else {
            pbTeamsLeague.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        for (int i = 0; i < leaguesArray.length; i++)
            menu.add(Menu.NONE, i, Menu.NONE, leaguesArray[i]);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String league = (String) item.getTitle();
        if (league != null) {
            presenter.getTeamsOfLeague(league);
            pbTeamsLeague.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTeamsOfLeague(String leagueName, ArrayList<TeamOfLeague> teamsOfLeagues) {
        TeamsLegueFragment teamsLegueFragment = new TeamsLegueFragment(leagueName, teamsOfLeagues);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.teamsLeaguePane, teamsLegueFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        pbTeamsLeague.setVisibility(View.GONE);
    }

    @Override
    public void showTeamsOfLeagueError(String error) {
        pbTeamsLeague.setVisibility(View.GONE);

        showMessageAlertDialog(getString(R.string.txt_error), error);
    }

    @Override
    public void onItemListFragmentClick(int position) {
        // Pendiente por implementación
    }

    private void showMessageAlertDialog(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        dialog.setMessage(message);
        dialog.setPositiveButton(getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (pbTeamsLeague.getVisibility() == View.VISIBLE)
                    pbTeamsLeague.setVisibility(View.GONE);
            }
        });
        dialog.show();
    }
}
