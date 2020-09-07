package com.codexo.gads2020leaderboard.api;

import androidx.annotation.NonNull;

import com.codexo.gads2020leaderboard.model.LearningLeader;
import com.codexo.gads2020leaderboard.model.SkillLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class GadsApiService {

    public static interface RetrofitInterface {
        @GET("/api/hours")
        Call<List<LearningLeader>> getLearningLeaders();

        @GET("/api/skilliq")
        Call<List<SkillLeader>> getSkillLeaders();
    }

    private static RetrofitInterface retrofitInterface = new Retrofit.Builder()
            .baseUrl("https://gadsapi.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface.class);

    public static void getLearningLeaders(@NonNull final ApiResponseCallback<List<LearningLeader>> callback) {
        retrofitInterface.getLearningLeaders()
                .enqueue(new Callback<List<LearningLeader>>() {
                    @Override
                    public void onResponse(Call<List<LearningLeader>> call, Response<List<LearningLeader>> response) {
                        if (response.isSuccessful())
                            callback.onResponse(response.body());
                        else
                            callback.onError(new ApiResponseError(response));
                    }

                    @Override
                    public void onFailure(Call<List<LearningLeader>> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }

    public static void getSkillLeaders(@NonNull final ApiResponseCallback<List<SkillLeader>> callback) {
        retrofitInterface.getSkillLeaders()
                .enqueue(new Callback<List<SkillLeader>>() {
                    @Override
                    public void onResponse(Call<List<SkillLeader>> call, Response<List<SkillLeader>> response) {
                        if (response.isSuccessful())
                            callback.onResponse(response.body());
                        else
                            callback.onError(new ApiResponseError(response));
                    }

                    @Override
                    public void onFailure(Call<List<SkillLeader>> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }
}
