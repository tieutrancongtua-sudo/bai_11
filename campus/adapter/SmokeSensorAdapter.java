package campus.adapter;

import java.util.Objects;

/** Object Adapter: CHỨA (composition) LegacySmokeDetector, không sao chép trạng thái. */
public final class SmokeSensorAdapter implements ISensor {
    private final LegacySmokeDetector legacy;

    public SmokeSensorAdapter(LegacySmokeDetector legacy) {
        this.legacy = Objects.requireNonNull(legacy, "legacy");
    }

    @Override
    public double readValue() {
        return legacy.getSmokeLevel() / 10.0; // 0..1000 -> 0..100 %
    }

    @Override
    public String getUnit() { return "% smoke"; }
}
