package cis3334.java_heartrate_start;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private HeartrateRepository heartrateRepository;

    private LiveData<List<Heartrate>> heartrateList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        try {
            heartrateRepository = new HeartrateRepository(application);
            heartrateList = heartrateRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insert(Integer heartrate, Integer age) {
        Heartrate hr = new Heartrate(heartrate, age);
        heartrateRepository.insert(hr);
    }

    public LiveData<List<Heartrate>> getAllHeartrates () {
        heartrateList = heartrateRepository.getAll();
        return heartrateList;
    }
}