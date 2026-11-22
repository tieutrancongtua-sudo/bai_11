package campus.observer;

import campus.model.AlertEvent;

public final class DashboardObserver implements IAlertObserver {
    @Override
    public void update(AlertEvent e) {
        System.out.printf("[DASHBOARD] %s: %.1f%s [%s]%n",
                e.sensorName(), e.value(), e.unit(), e.severity());
    }
}
