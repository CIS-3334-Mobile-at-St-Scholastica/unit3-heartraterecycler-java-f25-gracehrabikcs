package cis3334.java_heartrate_start;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HeartrateViewHolder extends RecyclerView.ViewHolder {
    TextView textViewPulse;
    TextView textViewRange;
    TextView textViewDescription;

    public HeartrateViewHolder(View itemView) {
        super(itemView);
        textViewPulse = itemView.findViewById(R.id.textViewPulse);
        textViewRange = itemView.findViewById(R.id.textViewRange);
        textViewDescription = itemView.findViewById(R.id.textViewDescription);
    }
}
