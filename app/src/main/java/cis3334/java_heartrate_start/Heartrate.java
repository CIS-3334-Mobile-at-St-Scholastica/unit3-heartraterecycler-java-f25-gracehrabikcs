package cis3334.java_heartrate_start;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Heartrate {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private Integer pulse;       // actual rate in beats per minute
    private Integer age;         // age when heart rate measurement was taken
    @Ignore
    private Double maxHeartRate;

    @Ignore
    private Double percent;

    @Ignore
    private Integer range;

    // ---- The following values don't need to be stored in the database, so the SQL to ignore them
    @Ignore
    private final String[] rangeNames = {"Resting", "Moderate", "Endurance", "Aerobic", "Anaerobic", "Red zone"};
    @Ignore
    private final String[] rangeDescriptions = {"Inactive or resting", "Weight maintenance and warm up", "Fitness and fat burning", "Cardio training and endurance", "Hardcore interval training", "Maximum Effort"};
    @Ignore
    private final Double[] rangeBounds = {.50, .60, .70, .80, .90, 1.00};  // ascending order

    // Constructor
    public Heartrate(Integer pulse, Integer age) {
        this.pulse = pulse;
        this.age = age;
        calcHeartRange();
    }

    // --------- Getters & Setters ---------
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPulse() { return pulse; }
    public void setPulse(Integer pulse) { this.pulse = pulse; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getMaxHeartRate() { return maxHeartRate; }
    public Double getPercent() { return percent; }

    public Integer getRange() { return range; }

    // --------- Logic Methods ---------
    /**
     * Calculate the maximum heartrate and percent of max using CDC guidelines
     * Uses the age and pulse to do this calculation
     * @return range -- the range index (usually 0 - 5) used for the index into the arrays above
     */
    public Integer calcHeartRange() {
        maxHeartRate = 220.0 - age;        // CDC guideline: 220 - age
        percent = pulse / maxHeartRate;
        for (int i = 0; i < rangeNames.length; i++) {
            if (percent < rangeBounds[i]) {
                range = i;
                return range;          // break out of this loop
            }
        }
        return rangeNames.length - 1;   // fallback
    }

    /**
     * @return the name for this range such as Aerobic
     */
    public String getRangeName() {
        calcHeartRange();
        return rangeNames[range];
    }

    /**
     * @return the longer description for this range such as "Fitness and fat burning"
     */
    public String getRangeDescription() {
        calcHeartRange();
        return rangeDescriptions[range];
    }

    /**
     * @return the heartrate as a descriptive string that can be displayed
     */
    @Override
    public String toString() {
        return "Pulse of " + pulse + " - Cardio level: " + getRangeName();
    }
}
