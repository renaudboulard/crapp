package fr.o80.sample.timesheet.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import fr.o80.sample.lib.core.ui.BaseFragment;
import fr.o80.sample.timesheet.R;
import fr.o80.sample.timesheet.R2;
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesPresenter;
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesView;

/**
 * @author Olivier Perez
 */
public class TimesheetEntriesFragment extends BaseFragment implements TimesheetEntriesView {

    @Inject
    protected TimesheetEntriesPresenter presenter;

    @BindView(R2.id.recyclerView)
    protected RecyclerView recyclerView;

    public static TimesheetEntriesFragment newInstance() {
        return new TimesheetEntriesFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_timesheet_entries;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TimesheetActivity) getActivity()).component().inject(this);

        presenter.init();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        activity.setSupportActionBar(toolbar);

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }
}