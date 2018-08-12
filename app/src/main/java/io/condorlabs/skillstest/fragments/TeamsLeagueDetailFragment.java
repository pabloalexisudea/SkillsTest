package io.condorlabs.skillstest.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.condorlabs.skillstest.R;
import io.condorlabs.skillstest.TeamOfLeague;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsLeagueDetailFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView tvTeamLeagueNameDetail,
            tvTeamLeagueDescriptionDetail,
            tvTeamLeagueFoundationYearDetail,
            tvTeamLeagueWebsiteDetail;
    private ImageView imgTeamLeagueBadge, imgTeamLeagueJersey;
    private ImageView imgTeamTwitterSocial, imgTeamFacebookSocial,
            imgTeamInstagramSocial, imgTeamYoutubeSocial;
    private LinearLayout layoutTeamBadge, layoutTeamJersey, layoutFoundationYear, layoutWebsite;
    private TeamOfLeague teamOfLeague;

    public TeamsLeagueDetailFragment() {
    }

    @SuppressLint("ValidFragment")
    public TeamsLeagueDetailFragment(TeamOfLeague teamOfLeague) {
        this.teamOfLeague = teamOfLeague;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teams_league_detail, container, false);

        initViewControls();

        if (teamOfLeague != null) {
            if (teamOfLeague.getStrTeam() != null)
                tvTeamLeagueNameDetail.setText(teamOfLeague.getStrTeam());

            if (teamOfLeague.getStrDescriptionEN() == null || teamOfLeague.getStrDescriptionEN().equals("") || teamOfLeague.getStrDescriptionEN().equals("null")) {
                if (teamOfLeague.getStrDescriptionES() == null || teamOfLeague.getStrDescriptionES().equals("") || teamOfLeague.getStrDescriptionES().equals("null")) {
                    tvTeamLeagueDescriptionDetail.setText(getActivity().getString(R.string.txt_no_desc));
                } else {
                    tvTeamLeagueDescriptionDetail.setText(teamOfLeague.getStrDescriptionES());
                }
            } else {
                tvTeamLeagueDescriptionDetail.setText(teamOfLeague.getStrDescriptionEN());
            }

            if (teamOfLeague.getStrWebsite() != null && !teamOfLeague.getStrWebsite().equals("")) {
                tvTeamLeagueWebsiteDetail.setText(teamOfLeague.getStrWebsite());
                tvTeamLeagueWebsiteDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openSocialWebSite(teamOfLeague.getStrWebsite());
                    }
                });
            } else
                layoutWebsite.setVisibility(View.GONE);

            if (teamOfLeague.getIntFormedYear() != 0)
                tvTeamLeagueFoundationYearDetail.setText(String.valueOf(teamOfLeague.getIntFormedYear()));
            else
                layoutFoundationYear.setVisibility(View.GONE);

            if (teamOfLeague.getStrTeamBadge() != null && !(teamOfLeague.getStrTeamBadge().equals("") || teamOfLeague.getStrTeamBadge().equals("null")))
                Glide.with(getActivity()).load(teamOfLeague.getStrTeamBadge()).into(imgTeamLeagueBadge);
            else
                layoutTeamBadge.setVisibility(View.GONE);

            if (teamOfLeague.getStrTeamJersey() != null && !(teamOfLeague.getStrTeamJersey().equals("") || teamOfLeague.getStrTeamJersey().equals("null")))
                Glide.with(getActivity()).load(teamOfLeague.getStrTeamJersey()).into(imgTeamLeagueJersey);
            else
                layoutTeamJersey.setVisibility(View.GONE);

            if (teamOfLeague.getStrTwitter() != null && !teamOfLeague.getStrTwitter().equals(""))
                imgTeamTwitterSocial.setOnClickListener(this);
            else
                imgTeamTwitterSocial.setVisibility(View.GONE);

            if (teamOfLeague.getStrFacebook() != null && !teamOfLeague.getStrFacebook().equals(""))
                imgTeamFacebookSocial.setOnClickListener(this);
            else
                imgTeamFacebookSocial.setVisibility(View.GONE);

            if (teamOfLeague.getStrInstagram() != null && !teamOfLeague.getStrInstagram().equals(""))
                imgTeamInstagramSocial.setOnClickListener(this);
            else
                imgTeamInstagramSocial.setVisibility(View.GONE);

            if (teamOfLeague.getStrYoutube() != null && !teamOfLeague.getStrYoutube().equals(""))
                imgTeamYoutubeSocial.setOnClickListener(this);
            else
                imgTeamYoutubeSocial.setVisibility(View.GONE);
        }

        return view;
    }

    private void initViewControls() {
        tvTeamLeagueNameDetail = view.findViewById(R.id.teamLeagueNameDetail);
        tvTeamLeagueDescriptionDetail = view.findViewById(R.id.teamLeagueDescriptionDetail);
        tvTeamLeagueFoundationYearDetail = view.findViewById(R.id.teamLeagueFoundationYearDetail);
        tvTeamLeagueWebsiteDetail = view.findViewById(R.id.teamLeagueWebsiteDetail);

        layoutTeamBadge = view.findViewById(R.id.layoutTeamBadge);
        layoutTeamJersey = view.findViewById(R.id.layoutTeamJersey);
        layoutFoundationYear = view.findViewById(R.id.layoutFoundationYear);
        layoutWebsite = view.findViewById(R.id.layoutWebsite);

        imgTeamTwitterSocial = view.findViewById(R.id.imgTeamTwitterSocial);
        imgTeamFacebookSocial = view.findViewById(R.id.imgTeamFacebookSocial);
        imgTeamInstagramSocial = view.findViewById(R.id.imgTeamInstagramSocial);
        imgTeamYoutubeSocial = view.findViewById(R.id.imgTeamYoutubeSocial);

        imgTeamLeagueBadge = view.findViewById(R.id.imgTeamLeagueBadge);
        imgTeamLeagueJersey = view.findViewById(R.id.imgTeamLeagueJersey);
        imgTeamLeagueBadge.setBackgroundColor(Color.TRANSPARENT);
        imgTeamLeagueJersey.setBackgroundColor(Color.TRANSPARENT);
    }

    public static TeamsLeagueDetailFragment newInstance(TeamOfLeague teamOfLeague, int index) {
        TeamsLeagueDetailFragment f = new TeamsLeagueDetailFragment(teamOfLeague);

        Bundle args = new Bundle();
        args.putInt("idTeam", index);
        args.putString("leagueName", teamOfLeague.getStrTeam());
        f.setArguments(args);

        return f;
    }

    public int getShownIdTeam() {
        if (getArguments() != null)
            return getArguments().getInt("idTeam", 0);
        else
            return 0;
    }

    public String getShownLeagueName() {
        if (getArguments() != null)
            return getArguments().getString("leagueName", "");
        else
            return "";
    }

    @Override
    public void onClick(View v) {
        ImageView img = (ImageView) v;

        switch (img.getId()) {
            case R.id.imgTeamTwitterSocial:
                openSocialWebSite(teamOfLeague.getStrTwitter());
                break;
            case R.id.imgTeamFacebookSocial:
                openSocialWebSite(teamOfLeague.getStrFacebook());
                break;
            case R.id.imgTeamInstagramSocial:
                openSocialWebSite(teamOfLeague.getStrInstagram());
                break;
            case R.id.imgTeamYoutubeSocial:
                openSocialWebSite(teamOfLeague.getStrYoutube());
                break;
        }
    }

    private void openSocialWebSite(String socialLink) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + socialLink));
        getActivity().startActivity(i);
    }
}
