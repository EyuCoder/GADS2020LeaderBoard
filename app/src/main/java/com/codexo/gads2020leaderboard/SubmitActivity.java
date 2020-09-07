package com.codexo.gads2020leaderboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.codexo.gads2020leaderboard.fragment.ConfirmDialogFragment;
import com.codexo.gads2020leaderboard.fragment.OkDialogFragment;
import com.codexo.gads2020leaderboard.fragment.ProgressDialogFragment;
import com.codexo.gads2020leaderboard.fragment.SubmitResultDialogFragment;
import com.codexo.gads2020leaderboard.model.Submission;
import com.codexo.gads2020leaderboard.viewmodel.SubmitViewModel;

public class SubmitActivity extends AppCompatActivity implements ConfirmDialogFragment.OnClickListener {
    private SubmitViewModel viewModel;
    private Submission submission = new Submission();
    private ProgressDialogFragment progressDialogFragment;
    private SubmitResultDialogFragment submitResultDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        final OkDialogFragment okDialogFragment = OkDialogFragment.newInstance(getString(R.string.fill_fields));
        final ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance(getString(R.string.are_you_sure));
        progressDialogFragment = ProgressDialogFragment.newInstance(getString(R.string.submitting));
        submitResultDialogFragment = SubmitResultDialogFragment.newInstance();

        viewModel = new ViewModelProvider(this).get(SubmitViewModel.class);
        viewModel.getStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer status) {
                if (status != SubmitViewModel.STATUS_NEUTRAL) {
                    progressDialogFragment.dismiss();
                    submitResultDialogFragment.setSuccess(status == SubmitViewModel.STATUS_OK);
                    submitResultDialogFragment.show(getSupportFragmentManager(), "SubmitActivity_SubmitResultDialog");
                }
            }
        });

        findViewById(R.id.btn_back)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

        final TextView txtFirstName = findViewById(R.id.txtFirstName);
        final TextView txtLastName = findViewById(R.id.txtLastName);
        final TextView txtEmail = findViewById(R.id.txtEmail);
        final TextView txtProjectUrl = findViewById(R.id.txtProjectUrl);

        findViewById(R.id.button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        submission.setFirstName(txtFirstName.getText().toString().trim());
                        submission.setLastName(txtLastName.getText().toString().trim());
                        submission.setEmail(txtEmail.getText().toString().trim());
                        submission.setProjectUrl(txtProjectUrl.getText().toString().trim());
                        boolean filledForm = submission.getFirstName().length() > 0 && submission.getLastName().length() > 0 && submission.getEmail().length() > 0 && submission.getProjectUrl().length() > 0;
                        if (!filledForm)
                            okDialogFragment.show(getSupportFragmentManager(), "SubmitActivity_OkDialog");
                        else
                            confirmDialogFragment.show(getSupportFragmentManager(), "SubmitActivity_ConfirmDialog");
                    }
                });

    }

    @Override
    public void onConfirm(ConfirmDialogFragment confirmDialogFragment) {
        progressDialogFragment.show(getSupportFragmentManager(), "SubmitActivity_ProgressDialog");
        viewModel.submit(submission);
    }

    @Override
    public void onDismiss(ConfirmDialogFragment confirmDialogFragment) {
    }
}