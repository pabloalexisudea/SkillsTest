package io.condorlabs.skillstest.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.condorlabs.skillstest.R;
import io.condorlabs.skillstest.TeamOfLeague;
import io.condorlabs.skillstest.TeamsLeagueDetailActivity;
import io.condorlabs.skillstest.adapters.TeamLeagueViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsLegueFragment extends Fragment {
    //<editor-fold desc="Variables">
    private TextView txtTitleFragment;
    private boolean mDualPane;
    private View view;
    private int teamLeagueCheckPosition = 0;
    private OnItemListFragmentClickListener mListener;
    private String leagueName;
    private ArrayList<TeamOfLeague> teamsOfLeague;
    private RecyclerView recyclerViewTeamsLeague;
    private TeamLeagueViewAdapter teamLeagueViewAdapter;
    //</editor-fold>

    public TeamsLegueFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public TeamsLegueFragment(String leagueName, ArrayList<TeamOfLeague> teamsOfLeague) {
        this.leagueName = leagueName;
        this.teamsOfLeague = teamsOfLeague;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teams_legue, container, false);

        txtTitleFragment = view.findViewById(R.id.titleFragment);
        recyclerViewTeamsLeague = view.findViewById(R.id.recyclerViewTeamsLeague);

        if (teamsOfLeague == null) {
            if (savedInstanceState != null) {
                teamsOfLeague = savedInstanceState.getParcelableArrayList("teamsOfLeague");
                leagueName = savedInstanceState.getString("leagueName");
            }
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtTitleFragment.setText(leagueName);

        teamLeagueViewAdapter = new TeamLeagueViewAdapter(getActivity(), teamsOfLeague);

        recyclerViewTeamsLeague.setAdapter(teamLeagueViewAdapter);
        recyclerViewTeamsLeague.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        teamLeagueViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mListener.onItemListFragmentClick(recyclerViewTeamsLeague.getChildAdapterPosition(v) + 1);
                showDetails(teamsOfLeague.get(recyclerViewTeamsLeague.getChildAdapterPosition(v)),
                        recyclerViewTeamsLeague.getChildAdapterPosition(v));
            }
        });

        View panelDetalle = getActivity().findViewById(R.id.teamsLeagueDetailsPane);
        mDualPane = panelDetalle != null && panelDetalle.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            teamsOfLeague = savedInstanceState.getParcelableArrayList("teamsOfLeague");
            teamLeagueCheckPosition = savedInstanceState.getInt("teamLeagueCheckPosition", 0);
            leagueName = savedInstanceState.getString("leagueName");
        }

        if (mDualPane)
            showDetails(teamsOfLeague.get(teamLeagueCheckPosition), teamLeagueCheckPosition);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnItemListFragmentClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemListFragmentClickListener");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("teamsOfLeague", teamsOfLeague);
        outState.putInt("teamLeagueCheckPosition", teamLeagueCheckPosition);
        outState.putString("leagueName", leagueName);

        super.onSaveInstanceState(outState);
    }

    private void showDetails(TeamOfLeague teamOfLeague, int index) {
        teamLeagueCheckPosition = index;

        if (mDualPane) {
            TeamsLeagueDetailFragment detalleFragment = (TeamsLeagueDetailFragment) getFragmentManager().findFragmentById(R.id.teamsLeagueDetailsPane);

            if (detalleFragment == null || detalleFragment.getShownIdTeam() != index || !detalleFragment.getShownLeagueName().equals(leagueName)) {
                detalleFragment = TeamsLeagueDetailFragment.newInstance(teamOfLeague, index);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.teamsLeagueDetailsPane, detalleFragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), TeamsLeagueDetailActivity.class);
            intent.putExtra("teamOfLeague", teamOfLeague);
            startActivity(intent);
        }
    }

    public static interface OnItemListFragmentClickListener {
        void onItemListFragmentClick(int position);
    }

}
