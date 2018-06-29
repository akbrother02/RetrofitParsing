package aasheesh.com.myapplication.manager.api;

import android.support.annotation.NonNull;

import aasheesh.com.myapplication.models.ApiDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 6/21/18.
 */

public class ApiManger {
    private static ApiManger instance;
    private ApiService apiService;
    private ResponseListener responseListener;

    private ApiManger() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiManger getInstance() {
        if (instance == null)
            instance = new ApiManger();
        return instance;
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void callGetList() {
        Call<ApiDataModel> call = apiService.listData();
        call.enqueue(new Callback<ApiDataModel>() {
            @Override
            public void onResponse(@NonNull Call<ApiDataModel> call, @NonNull Response<ApiDataModel> response) {
                responseListener.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(Call<ApiDataModel> call, Throwable t) {
                responseListener.onFailureResponse();
            }
        });
    }

    public interface ResponseListener {
        void onSuccessResponse(Object object);
        void onFailureResponse();
    }
}
