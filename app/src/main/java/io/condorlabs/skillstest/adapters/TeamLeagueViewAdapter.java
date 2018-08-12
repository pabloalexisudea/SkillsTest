package io.condorlabs.skillstest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.condorlabs.skillstest.R;
import io.condorlabs.skillstest.TeamOfLeague;

/**
 * Created by edale on 5/08/2018.
 */

public class TeamLeagueViewAdapter extends RecyclerView.Adapter<TeamLeagueViewAdapter.TeamLeagueViewHolder> implements View.OnClickListener {

    private Context context;
    private View.OnClickListener listener;
    private ArrayList<TeamOfLeague> teamOfLeagues;

    // TODO: CONSTRUCTOR
    public TeamLeagueViewAdapter(Context context, ArrayList<TeamOfLeague> teamOfLeagues) {
        this.context = context;
        this.teamOfLeagues = teamOfLeagues;
    }

    @NonNull
    @Override
    public TeamLeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View plantilla = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_league_template_item, parent, false);

        plantilla.setOnClickListener(this);

        return new TeamLeagueViewHolder(context, plantilla);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamLeagueViewHolder holder, int position) {
        holder.bindCurso(teamOfLeagues.get(position));
    }

    @Override
    public int getItemCount() {
        return teamOfLeagues == null ? 0 : teamOfLeagues.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

    static class TeamLeagueViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTeamName, tvTeamStadium;
        private ImageView imgTeamBadge;
        private Context context;

        public TeamLeagueViewHolder(Context context, View itemView) {
            super(itemView);

            this.context = context;
            tvTeamName = itemView.findViewById(R.id.teamName);
            tvTeamStadium = itemView.findViewById(R.id.teamStadium);
            imgTeamBadge = itemView.findViewById(R.id.imageViewTeamBadge);
        }

        public void bindCurso(TeamOfLeague teamOfLeague) {
            if (teamOfLeague.getStrTeam() != null)
                tvTeamName.setText(teamOfLeague.getStrTeam());

            if (teamOfLeague.getStrStadium() == null || teamOfLeague.getStrStadium().equals(""))
                tvTeamStadium.setVisibility(View.INVISIBLE);
            else
                tvTeamStadium.setText(this.context.getString(R.string.txt_stadium) + ": " + teamOfLeague.getStrStadium());

            if (teamOfLeague.getStrTeamBadge() != null)
                Glide.with(this.context).load(teamOfLeague.getStrTeamBadge()).into(imgTeamBadge);
        }
    }
}
