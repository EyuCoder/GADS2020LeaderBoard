package com.codexo.gads2020leaderboard.api;

public interface ApiResponseCallback<T> {
    void onResponse(T response);
    void onError(Throwable error);
}
