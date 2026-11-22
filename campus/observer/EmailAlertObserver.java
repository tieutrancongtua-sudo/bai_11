package campus.observer;

import campus.model.AlertEvent;

public final class EmailAlertObserver implements IAlertObserver {
    private final String recipient;

    public EmailAlertObserver(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void update(AlertEvent e) {
        System.out.printf("[EMAIL to %s] %s alert on %s (%.1f%s)%n",
                recipient, e.severity(), e.sensorName(), e.value(), e.unit());
    }
}
