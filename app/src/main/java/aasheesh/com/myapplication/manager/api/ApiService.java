package aasheesh.com.myapplication.manager.api;

import aasheesh.com.myapplication.models.ApiDataModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 6/21/18.
 */

public interface ApiService {

    @GET("search?term=Michael+jackson")
    Call<ApiDataModel> listData();
}
