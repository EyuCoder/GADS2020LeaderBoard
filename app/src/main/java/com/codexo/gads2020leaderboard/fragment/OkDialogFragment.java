package com.codexo.gads2020leaderboard.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class OkDialogFragment extends DialogFragment {
    public static final String ARG_MESSAGE = "message";

    public OkDialogFragment() {
        Bundle defaultArgs = new Bundle();
        defaultArgs.putString(ARG_MESSAGE, "");
        setArguments(defaultArgs);
    }

    public static OkDialogFragment newInstance(String message) {
        OkDialogFragment dialog = new OkDialogFragment();
        dialog.getArguments().putString(ARG_MESSAGE, message);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setMessage(getArguments().getString(ARG_MESSAGE))
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

}
