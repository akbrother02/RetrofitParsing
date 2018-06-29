package aasheesh.com.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import aasheesh.com.myapplication.R;
import aasheesh.com.myapplication.models.ApiDataModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 6/21/18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<ApiDataModel.DataModel> dataModels;
    private Context context;

    public DataAdapter(List<ApiDataModel.DataModel> dataModels) {
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_data_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiDataModel.DataModel dataModel = dataModels.get(position);
        if (dataModel != null) {
            holder.artistName.setText(dataModel.artistName);
            holder.trackName.setText(dataModel.trackName);
            long minutes = (dataModel.trackTimeMillis / 1000) / 60;
            long seconds = (dataModel.trackTimeMillis / 1000) % 60;
            String durationText = minutes + "m " + seconds + "s";
            holder.trackDuration.setText(durationText);

            Glide.with(context).load(dataModel.trackViewUrl).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataModels == null ? 0 : dataModels.size();
    }

    public void updateData(List<ApiDataModel.DataModel> results) {
        this.dataModels = results;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.track_name_text_view)
        TextView trackName;
        @BindView(R.id.track_duration_text_view)
        TextView trackDuration;
        @BindView(R.id.artist_name_text_view)
        TextView artistName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
