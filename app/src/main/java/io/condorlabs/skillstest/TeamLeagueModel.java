package io.condorlabs.skillstest;

import android.content.res.Resources;
import android.net.ConnectivityManager;

import java.io.IOException;

import io.condorlabs.skillstest.interfaces.RetrofitRestClient;
import io.condorlabs.skillstest.interfaces.TeamLeague;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamLeagueModel implements TeamLeague.Model {

    private TeamLeague.Presenter presenter;
    private Resources resources;

    // TODO: CONTRUCTOR
    public TeamLeagueModel(TeamLeague.Presenter presenter, Resources resources) {
        this.presenter = presenter;
        this.resources = resources;
    }


    @Override
    public void getTeamsOfLeague(final String teamLeaguesName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitRestClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitRestClient retrofitRestClient = retrofit.create(RetrofitRestClient.class);

        Call<ResponseBody> call = retrofitRestClient.getTeamsOfLeague(teamLeaguesName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String teamsOfLeagues = null;
                try {
                    teamsOfLeagues = response.body().string();
                    presenter.showTeamsOfLeague(teamLeaguesName, teamsOfLeagues);
                } catch (IOException e) {
                    e.printStackTrace();
                    presenter.showTeamsOfLeagueError(resources.getString(R.string.txt_error_response));
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                presenter.showTeamsOfLeagueError(resources.getString(R.string.txt_error_retrofit));
            }
        });

    }

}
