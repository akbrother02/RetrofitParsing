package aasheesh.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import aasheesh.com.myapplication.adapter.DataAdapter;
import aasheesh.com.myapplication.manager.api.ApiManger;
import aasheesh.com.myapplication.models.ApiDataModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ApiManger.ResponseListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiManger.getInstance().setResponseListener(this);
        ApiManger.getInstance().callGetList();
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void onSuccessResponse(Object object) {
        if (object instanceof ApiDataModel) {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            ApiDataModel apiDataModel = (ApiDataModel) object;
            if (recyclerView.getAdapter() == null) {
                DataAdapter dataAdapter = new DataAdapter(apiDataModel.results);
                recyclerView.setAdapter(dataAdapter);
            } else {
                DataAdapter dataAdapter = (DataAdapter) recyclerView.getAdapter();
                dataAdapter.updateData(apiDataModel.results);
            }
        }
    }

    @Override
    public void onFailureResponse() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Could not get Response", Toast.LENGTH_SHORT).show();
    }
}
