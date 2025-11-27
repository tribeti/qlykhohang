# GIẢI THÍCH KIẾN TRÚC DỰ ÁN MVC

## Tổng Quan
Ứng dụng Quản Lý Thiết Bị được thiết kế theo mô hình **MVC (Model-View-Controller)** để tách biệt logic, dữ liệu và giao diện.

## Cấu Trúc Thư Mục

```
qlykhohang/
├── src/                              # Thư mục mã nguồn
│   ├── App.java                      # Điểm vào chương trình
│   ├── model/                        # Lớp dữ liệu
│   │   ├── Equipment.java            # Lớp đối tượng thiết bị
│   │   └── Transaction.java          # Lớp đối tượng giao dịch
│   ├── controller/                   # Lớp logic xử lý
│   │   └── EquipmentController.java  # Lớp điều khiển
│   ├── view/                         # Lớp giao diện
│   │   └── EquipmentView.java        # Lớp menu người dùng
│   └── util/                         # Lớp tiện ích
│       ├── DataPersistence.java      # Lưu trữ/đọc dữ liệu
│       └── ReportGenerator.java      # Tạo báo cáo
├── bin/                              # Thư mục các file .class (sau khi compile)
├── run.bat                           # Script chạy trên Windows
├── run.sh                            # Script chạy trên Linux/Mac
├── README.md                         # Tài liệu tổng quan
├── HUONG_DAN.md                      # Hướng dẫn chi tiết
└── CAU_TRUC.md                       # File này
```

## Mô Hình MVC Chi Tiết

### 1. MODEL (model/)
Chứa các lớp đại diện cho dữ liệu

#### Equipment.java
```
Công dụng: Đại diện cho một thiết bị
Thuộc tính:
  - id: int                (ID duy nhất)
  - name: String          (Tên thiết bị)
  - category: String      (Danh mục)
  - status: String        (Trạng thái: new, in_use, broken, stored)
  - quantity: int         (Số lượng)
  - dateImported: String  (Ngày nhập)
  - description: String   (Mô tả)

Phương thức:
  - Getter/Setter cho tất cả thuộc tính
  - toString(): Trả về chuỗi đại diện đối tượng
```

#### Transaction.java
```
Công dụng: Đại diện cho một giao dịch nhập/xuất
Thuộc tính:
  - id: int              (ID giao dịch)
  - equipmentId: int     (ID thiết bị)
  - type: String        (Loại: "import" hoặc "export")
  - quantity: int        (Số lượng)
  - date: String         (Ngày giao dịch)
  - reason: String       (Lý do nhập/xuất)
  - notes: String        (Ghi chú)

Phương thức:
  - Getter/Setter cho tất cả thuộc tính
  - toString(): Trả về chuỗi đại diện đối tượng
```

### 2. CONTROLLER (controller/)
Chứa logic xử lý nghiệp vụ

#### EquipmentController.java
```
Công dụng: Quản lý logic toàn bộ ứng dụng

Dữ liệu thành viên:
  - equipmentList: List<Equipment>      (Danh sách thiết bị)
  - transactionList: List<Transaction>  (Danh sách giao dịch)
  - equipmentIdCounter: int             (Bộ đếm ID thiết bị)
  - transactionIdCounter: int           (Bộ đếm ID giao dịch)

Phương thức chính:

A. QUẢN LÝ DANH MỤC THIẾT BỊ:
  - addEquipment(): Thêm thiết bị mới
  - updateEquipment(): Cập nhật thông tin thiết bị
  - deleteEquipment(): Xóa thiết bị
  - getEquipmentById(): Tìm thiết bị theo ID
  - getAllEquipment(): Lấy danh sách toàn bộ thiết bị

B. QUẢN LÝ NHẬP – XUẤT:
  - importEquipment(): Ghi nhận nhập thiết bị
  - exportEquipment(): Ghi nhận xuất thiết bị
  - getAllTransactions(): Lấy danh sách giao dịch
  - getTransactionsByEquipment(): Lấy giao dịch của một thiết bị

C. THỐNG KÊ:
  - countBrokenEquipment(): Đếm thiết bị hỏng
  - countInUseEquipment(): Đếm thiết bị đang dùng
  - countNewEquipment(): Đếm thiết bị mới
  - getTotalQuantity(): Tính tổng số lượng
  - getBrokenEquipment(): Lấy danh sách thiết bị hỏng
  - getInUseEquipment(): Lấy danh sách thiết bị đang dùng

D. QUẢN LÝ TRẠNG THÁI:
  - markEquipmentAsBroken(): Đánh dấu hỏng
  - markEquipmentAsInUse(): Đánh dấu đang dùng
  - markEquipmentAsStored(): Đánh dấu lưu trữ
```

### 3. VIEW (view/)
Chứa giao diện người dùng

#### EquipmentView.java
```
Công dụng: Giao diện dòng lệnh tương tác với người dùng

Dữ liệu thành viên:
  - controller: EquipmentController  (Tham chiếu đến controller)
  - scanner: Scanner                 (Đầu vào từ bàn phím)

Phương thức chính:

A. MENU:
  - displayMenu(): Menu chính
  - displayEquipmentMenu(): Menu quản lý thiết bị
  - displayTransactionMenu(): Menu ghi nhận nhập/xuất
  - displayStatisticsMenu(): Menu thống kê

B. QUẢN LÝ THIẾT BỊ:
  - viewAllEquipment(): Xem danh sách
  - addNewEquipment(): Thêm thiết bị
  - updateEquipment(): Cập nhật
  - deleteEquipment(): Xóa

C. NHẬP/XUẤT:
  - importEquipment(): Nhập thiết bị
  - exportEquipment(): Xuất thiết bị
  - viewTransactions(): Xem lịch sử

D. THỐNG KÊ:
  - viewStatistics(): Thống kê tổng quát
  - viewInUseEquipment(): Xem đang dùng
  - viewBrokenEquipment(): Xem hỏng
  - viewDetailedReport(): Báo cáo chi tiết

E. TIỆN ÍCH:
  - getIntInput(): Nhận input số
  - truncate(): Cắt chuỗi dài
```

### 4. UTILITY (util/)
Chứa các lớp hỗ trợ

#### DataPersistence.java
```
Công dụng: Lưu trữ và đọc dữ liệu từ file

Phương thức:
  - saveEquipmentToFile(): Lưu thiết bị vào file
  - saveTransactionsToFile(): Lưu giao dịch vào file
  - exportReport(): Xuất báo cáo ra file
```

#### ReportGenerator.java
```
Công dụng: Tạo báo cáo và thống kê

Lớp nội bộ:
  - EquipmentStatistics: Chứa dữ liệu thống kê

Phương thức:
  - generateStatistics(): Tính toán thống kê
  - getCategoryStatistics(): Thống kê theo danh mục
  - printDetailedReport(): In báo cáo chi tiết
```

## Luồng Dữ Liệu

```
┌─────────────┐
│   User      │
│(Keyboard)   │
└──────┬──────┘
       │
       ▼
┌──────────────────┐
│  VIEW            │  ← Giao diện (nhận input, in output)
│EquipmentView    │
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│  CONTROLLER      │  ← Logic (xử lý data, tính toán)
│EquipmentCtrlr   │
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│  MODEL           │  ← Dữ liệu (lưu trữ thông tin)
│Equipment,        │
│Transaction       │
└──────────────────┘
```

## Quy Trình Thêm Thiết Bị (Ví Dụ)

```
1. User chọn "1. Quản lý danh mục" từ View
2. View hiển thị menu con
3. User chọn "2. Thêm thiết bị"
4. View nhận các input: tên, danh mục, số lượng, mô tả
5. View gọi controller.addEquipment()
6. Controller:
   - Tạo object Equipment mới
   - Gán ID (từ counter)
   - Đặt status = "new"
   - Đặt ngày nhập = ngày hiện tại
   - Thêm vào equipmentList
7. View hiển thị "✓ Thêm thiết bị thành công!"
```

## Lợi Ích Của Mô Hình MVC

1. **Tách biệt trách nhiệm**
   - Model: Chỉ lo dữ liệu
   - View: Chỉ lo hiển thị
   - Controller: Chỉ lo logic

2. **Dễ bảo trì**
   - Thay đổi giao diện không ảnh hưởng logic
   - Thay đổi logic không ảnh hưởng dữ liệu

3. **Dễ mở rộng**
   - Thêm chức năng mới dễ dàng
   - Có thể thay View bằng GUI mà không thay đổi Model/Controller

4. **Dễ kiểm thử**
   - Có thể test Controller độc lập với View

## Mở Rộng Ứng Dụng

### Để thêm chức năng mới:

1. **Thêm dữ liệu**
   - Tạo class mới trong `model/`

2. **Thêm logic**
   - Thêm phương thức vào `EquipmentController`

3. **Thêm giao diện**
   - Thêm menu trong `EquipmentView`

### Ví dụ: Thêm chức năng "Xuất báo cáo PDF"

```java
// 1. Trong model/ - không cần thay đổi

// 2. Trong controller/ - thêm phương thức:
public void exportPDF(String filename) {
    // Logic tạo PDF
}

// 3. Trong view/ - thêm menu item:
System.out.println("5. Xuất báo cáo PDF");
// ...
case 5:
    exportPDF();
    break;

// 4. Thêm phương thức trong view/:
private void exportPDF() {
    System.out.print("Tên file: ");
    String filename = scanner.nextLine();
    controller.exportPDF(filename);
}
```

---

**Phiên bản**: 1.0  
**Cập nhật**: 27/11/2025
