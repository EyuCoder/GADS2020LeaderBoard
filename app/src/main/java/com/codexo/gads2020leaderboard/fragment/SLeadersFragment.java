package com.codexo.gads2020leaderboard.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codexo.gads2020leaderboard.R;
import com.codexo.gads2020leaderboard.adapter.SkillLeadersRvAdapter;
import com.codexo.gads2020leaderboard.model.SkillLeader;
import com.codexo.gads2020leaderboard.viewmodel.SLeadersViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SLeadersFragment extends Fragment {

    private SLeadersViewModel viewModel;
    private DialogFragment errorDialogFragment;

    public SLeadersFragment() {
        // Required empty public constructor
    }

    public static SLeadersFragment newInstance() {
        SLeadersFragment fragment = new SLeadersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SLeadersViewModel.class);
        errorDialogFragment = OkDialogFragment.newInstance(getString(R.string.network_error));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_leaders, container, false);
        final View emptyView = view.findViewById(R.id.emptyView);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refreshList();
            }
        });

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final SkillLeadersRvAdapter adapter = new SkillLeadersRvAdapter(getContext());
        recyclerView.setAdapter(adapter);
        viewModel.getSkillLeaders()
                .observe(this, new Observer<List<SkillLeader>>() {
                    @Override
                    public void onChanged(List<SkillLeader> skillLeaders) {
                        adapter.setItems(skillLeaders);
                        swipeRefreshLayout.setRefreshing(false);
                        if (skillLeaders.size() > 0) {
                            recyclerView.setVisibility(View.VISIBLE);
                            emptyView.setVisibility(View.GONE);
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            emptyView.setVisibility(View.VISIBLE);
                        }
                    }
                });
        viewModel.getError()
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean error) {
                        if (error) {
                            swipeRefreshLayout.setRefreshing(false);
                            if (adapter.getItemCount() > 0) {
                                Toast.makeText(getContext(), getString(R.string.network_error), Toast.LENGTH_SHORT)
                                        .show();
                            } else {
                                recyclerView.setVisibility(View.GONE);
                                emptyView.setVisibility(View.VISIBLE);
                                errorDialogFragment.show(getFragmentManager(), "LearningLeadersFragment_OkDialog");
                            }
                        }
                    }
                });

        return view;
    }
}