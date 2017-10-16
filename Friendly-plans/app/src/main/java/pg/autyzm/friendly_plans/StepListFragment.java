package pg.autyzm.friendly_plans;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import database.repository.StepTemplateRepository;
import javax.inject.Inject;

public class StepListFragment extends Fragment {

    public static final String TASK_ID = "task_id";

    @Inject
    StepTemplateRepository stepTemplateRepository;

    private StepListRecyclerViewAdapter stepListRecyclerViewAdapter;
    private long task_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getAppComponent().inject(this);

        Bundle args = getArguments();
        task_id = args.getLong(TASK_ID, 0);

        return inflater.inflate(R.layout.fragment_step_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setUpListContent(task_id);
    }

    private void setUpListContent(long taskId) {
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_step_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        stepListRecyclerViewAdapter = new StepListRecyclerViewAdapter();
        recyclerView.setAdapter(stepListRecyclerViewAdapter);

        stepListRecyclerViewAdapter.setStepItemListItems(stepTemplateRepository.getAll(taskId));
    }
}
