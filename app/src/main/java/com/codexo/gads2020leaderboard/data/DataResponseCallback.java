package com.codexo.gads2020leaderboard.data;

public interface DataResponseCallback<T> {
    void onResponse(T response);
    void onError(Throwable error);
}
