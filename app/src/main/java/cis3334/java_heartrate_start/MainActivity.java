package cis3334.java_heartrate_start;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextPulse;
    EditText editTextAge;
    EditText editTextDisplay;   // still here but optional once RecyclerView is in place
    Button buttonInsert;
    MainViewModel mainViewModel;
    private RecyclerView recyclerViewHeartrate;
    private HeartrateAdapter heartrateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // EditText fields
        editTextAge = findViewById(R.id.editTextAge);
        editTextPulse = findViewById(R.id.editTextPulse);
        //editTextDisplay = findViewById(R.id.editTextDisplay);

        // RecyclerView setup
        recyclerViewHeartrate = findViewById(R.id.recyclerViewHeartrate);
        heartrateAdapter = new HeartrateAdapter(getApplication(), mainViewModel);
        recyclerViewHeartrate.setAdapter(heartrateAdapter);
        recyclerViewHeartrate.setLayoutManager(new LinearLayoutManager(this));

        setupInsertButton();
        setupLiveDataObserver();
    }

    private void setupLiveDataObserver() {
        mainViewModel.getAllHeartrates().observe(this, new Observer<List<Heartrate>>() {
            @Override
            public void onChanged(@Nullable List<Heartrate> allHeartrates) {
                Log.d("CIS 3334", "MainActivity -- LiveData Observer -- Number of Heartrates = " + allHeartrates.size());

                // optional: still show count in the editText
                editTextDisplay.setText("Number of heartrates = " + allHeartrates.size());

                // Update RecyclerView list
                heartrateAdapter.setHeartrateList(allHeartrates);
                heartrateAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     *  Set up the Insert Heartrate button so it adds a new heart rate reading to the database
     */
    private void setupInsertButton() {
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pulse = Integer.parseInt(editTextPulse.getText().toString());
                Integer age = Integer.parseInt(editTextAge.getText().toString());
                mainViewModel.insert(pulse, age);
            }
        });
    }
}
