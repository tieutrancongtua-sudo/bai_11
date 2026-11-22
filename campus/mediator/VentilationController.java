package campus.mediator;

public final class VentilationController {
    private boolean smokeExtraction;

    public void startSmokeExtraction() {
        smokeExtraction = true;
        System.out.println("[VENT] Smoke extraction fans ON");
    }

    public boolean isSmokeExtraction() { return smokeExtraction; }
}
