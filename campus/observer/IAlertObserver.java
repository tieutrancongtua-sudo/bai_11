package campus.observer;

import campus.model.AlertEvent;

public interface IAlertObserver {
    void update(AlertEvent alertEvent);
}
