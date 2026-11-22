package campus.observer;

import campus.model.AlertEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AuditLogObserver implements IAlertObserver {
    private final List<String> logs = new ArrayList<>();

    @Override
    public void update(AlertEvent e) {
        String line = String.format("%s | %s | %.1f%s | %s",
                e.occurredAt(), e.sensorName(), e.value(), e.unit(), e.severity());
        logs.add(line);
        System.out.println("[AUDIT] " + line);
    }

    public List<String> getLogs() { return Collections.unmodifiableList(logs); }
}
