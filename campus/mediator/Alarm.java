package campus.mediator;

public final class Alarm {
    private IEmergencyMediator mediator;
    private boolean active;

    public void setMediator(IEmergencyMediator mediator) { this.mediator = mediator; }

    public void activate() {
        if (active) return;
        active = true;
        System.out.println("[ALARM] Siren ON");
        if (mediator != null) mediator.notify(this, "ALARM_ACTIVATED");
    }

    public void deactivate() {
        active = false;
        System.out.println("[ALARM] Siren OFF");
    }

    public boolean isActive() { return active; }
}
