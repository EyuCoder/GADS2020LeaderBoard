package com.codexo.gads2020leaderboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.DirectedAcyclicGraph;
import androidx.fragment.app.DialogFragment;

import com.codexo.gads2020leaderboard.R;

public class ConfirmDialogFragment extends DialogFragment {
    private static final String ARG_MESSAGE = "message";
    public static final int RESULT_OK = 1;

    public ConfirmDialogFragment() {
        Bundle defaultArgs = new Bundle();
        defaultArgs.putString(ARG_MESSAGE, "");
        setArguments(defaultArgs);
    }

    public static ConfirmDialogFragment newInstance(String message) {
        ConfirmDialogFragment dialog = new ConfirmDialogFragment();
        dialog.getArguments().putString(ARG_MESSAGE, message);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm, container, false);
        TextView txtMessage = view.findViewById(R.id.txtMessage);
        txtMessage.setText(getArguments().getString(ARG_MESSAGE));
        view.findViewById(R.id.btnOk)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnClickListener listener = (OnClickListener) getActivity();
                        if (listener != null)
                            listener.onConfirm(ConfirmDialogFragment.this);

                        dismiss();
                    }
                });
        view.findViewById(R.id.btnClose)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnClickListener listener = (OnClickListener) getActivity();
                        if (listener != null)
                            listener.onDismiss(ConfirmDialogFragment.this);

                        dismiss();
                    }
                });

        return view;
    }

    public static interface OnClickListener {
        void onConfirm(ConfirmDialogFragment confirmDialogFragment);

        void onDismiss(ConfirmDialogFragment confirmDialogFragment);
    }
}
