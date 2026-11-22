package campus.observer;

import campus.model.AlertEvent;
import campus.mediator.IEmergencyMediator;

import java.util.Objects;

public final class EmergencyBridgeObserver implements IAlertObserver {
    private final IEmergencyMediator mediator;

    public EmergencyBridgeObserver(IEmergencyMediator mediator) {
        this.mediator = Objects.requireNonNull(mediator);
    }

    @Override
    public void update(AlertEvent e) {
        if ("CRITICAL".equals(e.severity())) {
            System.out.println("[BRIDGE] CRITICAL -> FIRE_DETECTED");
            mediator.notify(this, "FIRE_DETECTED");
        }
    }
}
