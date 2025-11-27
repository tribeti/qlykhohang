# Ứng Dụng Quản Lý Thiết Bị

## Giới Thiệu
Ứng dụng quản lý thiết bị được xây dựng theo mô hình **MVC (Model-View-Controller)** với các tính năng chính:

### Chức Năng Chính
1. **Quản lý danh mục thiết bị**
   - Xem danh sách thiết bị
   - Thêm thiết bị mới
   - Cập nhật thông tin thiết bị
   - Xóa thiết bị

2. **Ghi nhận nhập – xuất – tồn**
   - Ghi nhận nhập thiết bị
   - Ghi nhận xuất thiết bị
   - Xem lịch sử giao dịch
   - Theo dõi số lượng tồn kho

3. **Thống kê thiết bị**
   - Thống kê tổng quát (tổng số lượng, trạng thái)
   - Danh sách thiết bị đang dùng
   - Danh sách thiết bị hỏng
   - Báo cáo chi tiết

## Cấu Trúc Dự Án

```
qlykhohang/
├── src/
│   ├── App.java                 # Lớp main
│   ├── model/
│   │   ├── Equipment.java       # Lớp mô hình thiết bị
│   │   └── Transaction.java     # Lớp mô hình giao dịch
│   ├── controller/
│   │   └── EquipmentController.java  # Lớp điều khiển logic
│   ├── view/
│   │   └── EquipmentView.java   # Lớp giao diện người dùng
│   └── util/
│       ├── DataPersistence.java # Lưu trữ dữ liệu
│       └── ReportGenerator.java # Tạo báo cáo
├── bin/                         # Thư mục output sau khi compile
└── README.md                    # Tài liệu hướng dẫn
```

## Mô Hình MVC

### Model (Mô hình)
- **Equipment.java**: Đại diện cho một thiết bị với các thuộc tính:
  - ID, Tên, Danh mục, Trạng thái, Số lượng, Ngày nhập, Mô tả

- **Transaction.java**: Đại diện cho một giao dịch với các thuộc tính:
  - ID, ID thiết bị, Loại (nhập/xuất), Số lượng, Ngày, Lý do

### Controller (Điều khiển)
- **EquipmentController.java**: Xử lý logic nghiệp vụ:
  - Quản lý danh mục thiết bị (CRUD)
  - Xử lý nhập/xuất thiết bị
  - Tính toán thống kê
  - Cập nhật trạng thái thiết bị

### View (Giao diện)
- **EquipmentView.java**: Giao diện người dùng dòng lệnh:
  - Menu chính
  - Menu quản lý thiết bị
  - Menu ghi nhận nhập/xuất
  - Menu thống kê

## Hướng Dẫn Sử Dụng

### Biên Dịch Dự Án
```bash
cd qlykhohang
javac -d bin src/model/*.java src/controller/*.java src/view/*.java src/util/*.java src/App.java
```

### Chạy Ứng Dụng
```bash
cd qlykhohang
java -cp bin App
```

### Các Tính Năng Trong Menu

#### 1. Quản Lý Danh Mục Thiết Bị
- Xem danh sách tất cả thiết bị
- Thêm thiết bị mới: nhập tên, danh mục, số lượng, mô tả
- Cập nhật thông tin thiết bị hiện có
- Xóa thiết bị (có xác nhận)

#### 2. Ghi Nhận Nhập – Xuất Tồn
- **Nhập thiết bị**: Chọn thiết bị, nhập số lượng và lý do nhập
- **Xuất thiết bị**: Chọn thiết bị, kiểm tra tồn kho, nhập lý do xuất
- **Xem lịch sử giao dịch**: Xem tất cả giao dịch hoặc theo thiết bị

#### 3. Thống Kê Thiết Bị
- Thống kê tổng quát: Tổng số thiết bị, tồn kho, trạng thái
- Danh sách thiết bị đang dùng
- Danh sách thiết bị hỏng
- Báo cáo chi tiết (thống kê chung, theo danh mục, giao dịch gần đây)

## Trạng Thái Thiết Bị

Ứng dụng quản lý các trạng thái thiết bị:
- **new**: Thiết bị mới
- **in_use**: Thiết bị đang sử dụng
- **broken**: Thiết bị hỏng
- **stored**: Thiết bị lưu trữ

## Dữ Liệu Mẫu

Ứng dụng được khởi tạo với dữ liệu mẫu:
1. Máy tính (10 cái) - Điện tử
2. Máy in (3 cái) - Thiết bị văn phòng
3. Bàn làm việc (20 cái) - Nội thất
4. Ghế xoay (30 cái) - Nội thất
5. Điều hòa (5 cái) - Điện tử

## Yêu Cầu Hệ Thống
- Java JDK 8 trở lên
- Terminal/Command Prompt

## Ghi Chú
- Dữ liệu được lưu trữ trong bộ nhớ (chương trình không lưu trữ vĩnh viễn vào file)
- Để lưu trữ dữ liệu vĩnh viễn, sử dụng các lớp trong `util.DataPersistence`
- Ứng dụng hỗ trợ nhập liệu tiếng Việt qua Terminal

