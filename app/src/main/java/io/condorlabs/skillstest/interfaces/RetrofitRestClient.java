package io.condorlabs.skillstest.interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitRestClient {

    String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    @GET("search_all_teams.php")
    Call<ResponseBody> getTeamsOfLeague(@Query("l") String leaguesName);

}
