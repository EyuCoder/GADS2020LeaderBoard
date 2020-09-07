package com.codexo.gads2020leaderboard.viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codexo.gads2020leaderboard.data.DataResponseCallback;
import com.codexo.gads2020leaderboard.data.DataService;
import com.codexo.gads2020leaderboard.model.LearningLeader;

import java.util.List;

public class LearningLeadersViewModel extends ViewModel {
    private Handler handler = new Handler();
    private MutableLiveData<List<LearningLeader>> learningLeaders;
    private MutableLiveData<Boolean> error = new MutableLiveData<>(false);

    public LiveData<List<LearningLeader>> getLearningLeaders() {
        if (learningLeaders == null) {
            learningLeaders = new MutableLiveData<>();
            refreshList();
        }
        return learningLeaders;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public void refreshList() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DataService.getLearningLeaders(new DataResponseCallback<List<LearningLeader>>() {
                    @Override
                    public void onResponse(List<LearningLeader> response) {
                        learningLeaders.setValue(response);
                        error.setValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        error.setValue(true);
                    }
                });
            }
        }, 1000);
    }
}
