package campus.mediator;

public final class DoorController {
    private boolean unlocked;

    public void unlockForEvacuation() {
        unlocked = true;
        System.out.println("[DOOR] Emergency exits UNLOCKED");
    }

    public boolean isUnlocked() { return unlocked; }
}
