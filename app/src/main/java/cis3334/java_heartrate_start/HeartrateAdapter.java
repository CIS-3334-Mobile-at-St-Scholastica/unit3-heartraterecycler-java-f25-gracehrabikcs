package cis3334.java_heartrate_start;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class HeartrateAdapter extends RecyclerView.Adapter<HeartrateViewHolder> {
    private final Application application;
    private final MainViewModel mainViewModel;
    private List<Heartrate> heartrateList = new ArrayList<>();
    public HeartrateAdapter(Application application, MainViewModel mainViewModel) {
        this.application = application;
        this.mainViewModel = mainViewModel;
    }

    @NonNull
    @Override
    public HeartrateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.heartrate_row, parent, false);
        return new HeartrateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeartrateViewHolder holder, int position) {
        Heartrate hr = heartrateList.get(position);
        holder.textViewPulse.setText("Pulse: " + hr.getPulse());
        holder.textViewRange.setText("Range: " + hr.getRangeName());
        holder.textViewDescription.setText("Description: " + hr.getRangeDescription());
    }

    @Override
    public int getItemCount() {
        return heartrateList != null ? heartrateList.size() : 0;
    }

    public void setHeartrateList(List<Heartrate> heartrates) {
        this.heartrateList = heartrates;
        notifyDataSetChanged();
    }
}
