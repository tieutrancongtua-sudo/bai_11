package campus.mediator;

import java.util.Objects;

public final class EmergencyMediator implements IEmergencyMediator {
    private final Alarm alarm;
    private final DoorController door;
    private final VentilationController ventilation;

    public EmergencyMediator(Alarm alarm, DoorController door, VentilationController ventilation) {
        this.alarm = Objects.requireNonNull(alarm);
        this.door = Objects.requireNonNull(door);
        this.ventilation = Objects.requireNonNull(ventilation);
        this.alarm.setMediator(this);
    }

    @Override
    public void notify(Object sender, String eventName) {
        switch (eventName) {
            case "FIRE_DETECTED" -> {
                alarm.activate();
            }
            case "ALARM_ACTIVATED" -> {
                door.unlockForEvacuation();
                ventilation.startSmokeExtraction();
            }
            default -> System.out.println("[MEDIATOR] Ignored event: " + eventName);
        }
    }
}
