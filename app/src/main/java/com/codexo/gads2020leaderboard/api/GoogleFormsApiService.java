package com.codexo.gads2020leaderboard.api;

import androidx.annotation.NonNull;

import com.codexo.gads2020leaderboard.model.Submission;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class GoogleFormsApiService {

    public static interface RetrofitInterface {
        @FormUrlEncoded
        @POST("d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
        Call<Void> submitProject(
                @Field("entry.1877115667") String firstName,
                @Field("entry.2006916086") String lastName,
                @Field("entry.1824927963") String email,
                @Field("entry.284483984") String projectUrl
        );
    }

    private static RetrofitInterface retrofitInterface = new Retrofit.Builder()
            .baseUrl("https://docs.google.com/forms/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface.class);

    public static void submitProject(Submission submission, @NonNull final ApiResponseCallback<Void> callback) {
        retrofitInterface.submitProject(submission.getFirstName(), submission.getLastName(), submission.getEmail(), submission.getProjectUrl())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful())
                            callback.onResponse(null);
                        else
                            callback.onError(new ApiResponseError(response));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }

}
