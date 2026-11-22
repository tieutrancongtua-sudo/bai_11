package campus;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import campus.adapter.ISensor;
import campus.adapter.LegacySmokeDetector;
import campus.adapter.SmokeSensorAdapter;
import campus.mediator.Alarm;
import campus.mediator.DoorController;
import campus.mediator.EmergencyMediator;
import campus.mediator.VentilationController;
import campus.observer.AuditLogObserver;
import campus.observer.DashboardObserver;
import campus.observer.EmailAlertObserver;
import campus.observer.EmergencyBridgeObserver;
import campus.observer.IAlertObserver;
import campus.observer.SensorMonitor;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        LegacySmokeDetector legacy = new LegacySmokeDetector(735);
        ISensor sensor = new SmokeSensorAdapter(legacy);
        System.out.println(sensor.readValue()); 
        System.out.println(sensor.getUnit());   
        System.out.println("-----");

        Alarm alarm = new Alarm();
        EmergencyMediator mediator = new EmergencyMediator(
                alarm, new DoorController(), new VentilationController());

        SensorMonitor monitor = new SensorMonitor();
        IAlertObserver email = new EmailAlertObserver("security@campus.edu");
        IAlertObserver faulty = e -> { throw new IllegalStateException("simulated observer error"); };
        monitor.attach(new DashboardObserver());
        monitor.attach(email);
        monitor.attach(email);              
        monitor.attach(faulty);             
        monitor.attach(new AuditLogObserver());
        monitor.attach(new EmergencyBridgeObserver(mediator));

        System.out.println("== 30% (NORMAL) ==");
        legacy.setSmokeLevel(300);
        monitor.checkSensor("Room-A smoke", sensor);

        System.out.println("== 60% (WARNING) ==");
        legacy.setSmokeLevel(600);
        monitor.checkSensor("Room-A smoke", sensor);

        System.out.println("== Detach email, then 85% (CRITICAL) ==");
        monitor.detach(email);
        legacy.setSmokeLevel(850);
        monitor.checkSensor("Room-A smoke", sensor);
    }
}
