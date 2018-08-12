package io.condorlabs.skillstest;

import android.os.Parcel;
import android.os.Parcelable;

public class TeamOfLeague implements Parcelable {

    private int idTeam;
    private String strTeam;
    private String strStadium;
    private String strTeamBadge;
    private String strTeamJersey;
    private int intFormedYear;
    private String strDescriptionEN;
    private String strDescriptionES;
    private String strWebsite;
    private String strTwitter;
    private String strFacebook;
    private String strInstagram;
    private String strYoutube;

    public TeamOfLeague(int idTeam, String strTeam, String strStadium, String strTeamBadge,
                        String strTeamJersey, int intFormedYear, String strDescriptionEN,
                        String strDescriptionES, String strWebsite, String strTwitter,
                        String strFacebook, String strInstagram, String strYoutube) {
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.strStadium = strStadium;
        this.strTeamBadge = strTeamBadge;
        this.strTeamJersey = strTeamJersey;
        this.intFormedYear = intFormedYear;
        this.strDescriptionEN = strDescriptionEN;
        this.strDescriptionES = strDescriptionES;
        this.strWebsite = strWebsite;
        this.strTwitter = strTwitter;
        this.strFacebook = strFacebook;
        this.strInstagram = strInstagram;
        this.strYoutube = strYoutube;
    }

    protected TeamOfLeague(Parcel in) {
        idTeam = in.readInt();
        strTeam = in.readString();
        strStadium = in.readString();
        strTeamBadge = in.readString();
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getStrStadium() {
        return strStadium;
    }

    public void setStrStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public void setStrTeamBadge(String strTeamBadge) {
        this.strTeamBadge = strTeamBadge;
    }

    public String getStrTeamJersey() {
        return strTeamJersey;
    }

    public void setStrTeamJersey(String strTeamJersey) {
        this.strTeamJersey = strTeamJersey;
    }

    public int getIntFormedYear() {
        return intFormedYear;
    }

    public void setIntFormedYear(int intFormedYear) {
        this.intFormedYear = intFormedYear;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStrDescriptionES() {
        return strDescriptionES;
    }

    public void setStrDescriptionES(String strDescriptionES) {
        this.strDescriptionES = strDescriptionES;
    }

    public String getStrWebsite() {
        return strWebsite;
    }

    public void setStrWebsite(String strWebsite) {
        this.strWebsite = strWebsite;
    }

    public String getStrTwitter() {
        return strTwitter;
    }

    public void setStrTwitter(String strTwitter) {
        this.strTwitter = strTwitter;
    }

    public String getStrFacebook() {
        return strFacebook;
    }

    public void setStrFacebook(String strFacebook) {
        this.strFacebook = strFacebook;
    }

    public String getStrInstagram() {
        return strInstagram;
    }

    public void setStrInstagram(String strInstagram) {
        this.strInstagram = strInstagram;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public static final Creator<TeamOfLeague> CREATOR = new Creator<TeamOfLeague>() {
        @Override
        public TeamOfLeague createFromParcel(Parcel in) {
            return new TeamOfLeague(in);
        }

        @Override
        public TeamOfLeague[] newArray(int size) {
            return new TeamOfLeague[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idTeam);
        dest.writeString(strTeam);
        dest.writeString(strStadium);
        dest.writeString(strTeamBadge);
        dest.writeString(strTeamJersey);
        dest.writeInt(intFormedYear);
        dest.writeString(strDescriptionEN);
        dest.writeString(strDescriptionES);
        dest.writeString(strWebsite);
        dest.writeString(strTwitter);
        dest.writeString(strFacebook);
        dest.writeString(strInstagram);
        dest.writeString(strYoutube);
    }
}
