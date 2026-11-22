package campus.adapter;

import java.util.Objects;

public final class SmokeSensorAdapter implements ISensor {
    private final LegacySmokeDetector legacy;

    public SmokeSensorAdapter(LegacySmokeDetector legacy) {
        this.legacy = Objects.requireNonNull(legacy, "legacy");
    }

    @Override
    public double readValue() {
        return legacy.getSmokeLevel() / 10.0; 
    }

    @Override
    public String getUnit() { return "% smoke"; }
}
