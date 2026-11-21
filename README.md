Trần Hoàng Đức Linh
mssv: 202418932

# Campus Safety Hub

Chương trình console mô phỏng trung tâm giám sát an toàn cho một phòng máy, viết bằng **Java**.
Bài thực hành áp dụng 3 mẫu thiết kế: **Adapter**, **Observer**, **Mediator**.

## Cấu trúc thư mục

```
CampusSafetyHub/
├── README.md
└── campus/
    ├── Main.java                         # Chạy demo toàn bộ luồng
    ├── model/
    │   └── AlertEvent.java               # Record bất biến mô tả cảnh báo
    ├── adapter/                          # Mẫu Adapter
    │   ├── ISensor.java                  # Giao diện chuẩn của module giám sát
    │   ├── LegacySmokeDetector.java      # Lớp cũ (không được sửa)
    │   └── SmokeSensorAdapter.java       # Đổi giao diện + thang đo 0..1000 -> 0..100 %
    ├── observer/                         # Mẫu Observer
    │   ├── IAlertObserver.java           # Hợp đồng nhận cảnh báo
    │   ├── SensorMonitor.java            # Subject: đọc sensor, so ngưỡng, phát cảnh báo
    │   ├── DashboardObserver.java        # In cảnh báo lên dashboard
    │   ├── EmailAlertObserver.java       # Giả lập gửi email (in ra console)
    │   ├── AuditLogObserver.java         # Ghi nhật ký cảnh báo
    │   └── EmergencyBridgeObserver.java  # Cầu nối: CRITICAL -> FIRE_DETECTED cho mediator
    └── mediator/                         # Mẫu Mediator
        ├── IEmergencyMediator.java       # Hợp đồng điều phối
        ├── EmergencyMediator.java        # Phối hợp alarm, door, ventilation
        ├── Alarm.java                    # Còi báo động
        ├── DoorController.java           # Mở cửa thoát hiểm
        └── VentilationController.java    # Bật quạt hút khói
```

## Ba mẫu thiết kế

| Vấn đề | Pattern | Thành phần chính |
|---|---|---|
| Thiết bị cũ không hỗ trợ giao diện chuẩn mới | Adapter | `SmokeSensorAdapter` |
| Nhiều bên cần nhận cảnh báo, thay đổi động | Observer | `SensorMonitor`, `IAlertObserver` |
| Thiết bị phối hợp nhưng không phụ thuộc trực tiếp nhau | Mediator | `EmergencyMediator` |

## Luồng hoạt động

```
LegacySmokeDetector -> SmokeSensorAdapter -(ISensor)-> SensorMonitor
SensorMonitor -(AlertEvent)-> DashboardObserver
                           -> EmailAlertObserver
                           -> AuditLogObserver
                           -> EmergencyBridgeObserver -(FIRE_DETECTED)-> EmergencyMediator
EmergencyMediator -> Alarm, DoorController, VentilationController
```

## Quy ước mức độ

| Giá trị khói sau chuyển đổi | Mức độ |
|---|---|
| Dưới 50% | Bình thường, không cảnh báo |
| Từ 50% đến dưới 70% | `WARNING` |
| Từ 70% trở lên | `CRITICAL` |

## Kết quả mong đợi

- 30% khói: không phát cảnh báo.
- 60% khói: `WARNING` gửi tới Dashboard, Email, Audit.
- 85% khói: `CRITICAL`, bật còi, mở cửa thoát hiểm, bật quạt hút khói.

