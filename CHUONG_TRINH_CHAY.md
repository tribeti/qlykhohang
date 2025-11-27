# HƯỚNG DẪN CHẠY CHƯƠNG TRÌNH

## ✅ BIÊN DỊCH THÀNH CÔNG
Dự án đã được biên dịch với **8 file .class**:
- App.class
- Equipment.class
- Transaction.class
- EquipmentController.class
- EquipmentView.class
- DataPersistence.class
- ReportGenerator.class
- ReportGenerator$EquipmentStatistics.class

## 🚀 CÁCH CHẠY

### **CÁCH 1: Sử Dụng Script (Khuyên Dùng)**

#### Trên Windows:
```batch
run.bat
```

#### Trên Linux/Mac:
```bash
chmod +x run.sh
./run.sh
```

### **CÁCH 2: Chạy Bằng Command Line**

#### Trên Windows (Command Prompt):
```batch
cd d:\JAVA\qlykhohang
java -cp bin App
```

#### Trên Linux/Mac (Terminal):
```bash
cd /path/to/qlykhohang
java -cp bin App
```

### **CÁCH 3: Biên Dịch Lại (Nếu Cần)**

#### Trên Windows:
```batch
cd d:\JAVA\qlykhohang
javac -d bin src\model\*.java src\controller\*.java src\view\*.java src\util\*.java src\App.java
java -cp bin App
```

#### Trên Linux/Mac:
```bash
cd /path/to/qlykhohang
javac -d bin src/model/*.java src/controller/*.java src/view/*.java src/util/*.java src/App.java
java -cp bin App
```

## 📋 MÀN HÌNH KỲ VỌNG

Khi chạy thành công, bạn sẽ thấy:

```
========== QUẢN LÝ THIẾT BỊ ==========
1. Quản lý danh mục thiết bị
2. Ghi nhận nhập - xuất tồn
3. Thống kê thiết bị
4. Thoát
Chọn chức năng (1-4): 
```

## 🧪 TEST NHANH

### Test 1: Xem Danh Sách Thiết Bị
```
Nhập: 1
Nhập: 1
Kết quả: Hiển thị 5 thiết bị mẫu
```

### Test 2: Thêm Thiết Bị Mới
```
Nhập: 1
Nhập: 2
Tên: Máy Nước Nóng
Danh mục: Thiết Bị Sưởi
Số lượng: 3
Mô tả: Máy nước nóng 30L
Kết quả: ✓ Thêm thiết bị thành công!
```

### Test 3: Nhập Thiết Bị
```
Nhập: 2
Nhập: 1
ID: 1
Số lượng: 5
Lý do: Mua mới
Kết quả: ✓ Nhập thiết bị thành công! Số lượng hiện tại: 15
```

### Test 4: Thống Kê
```
Nhập: 3
Nhập: 1
Kết quả: Hiển thị thống kê tổng hợp
```

## ⚠️ KHẮC PHỤC SỰ CỐ

### Lỗi: "The Main class not found"
**Nguyên nhân**: File App.class không có trong thư mục bin
**Giải pháp**: 
- Biên dịch lại dự án
- Kiểm tra đường dẫn đúng chưa

### Lỗi: "java: command not found"
**Nguyên nhân**: Java chưa được cài đặt hoặc chưa thêm vào PATH
**Giải pháp**:
- Cài đặt Java JDK
- Thêm Java vào PATH của hệ thống

### Lỗi: "Compilation failed"
**Nguyên nhân**: Mã nguồn có lỗi
**Giải pháp**:
- Kiểm tra lại các file .java
- Đảm bảo không có typo

### Chương trình không phản hồi khi nhập
**Nguyên nhân**: Input không hợp lệ
**Giải pháp**:
- Nhập một số hợp lệ
- Hoặc nhấn Ctrl+C để thoát

## 📁 CẤU TRÚC THƯ MỤC CẦN THIẾT

```
qlykhohang/
├── bin/                 ← Chứa .class (biên dịch tự động)
│   ├── App.class
│   ├── model/
│   ├── controller/
│   ├── view/
│   └── util/
├── src/                 ← Chứa .java (mã nguồn)
│   ├── App.java
│   ├── model/
│   ├── controller/
│   ├── view/
│   └── util/
├── run.bat              ← Script chạy Windows
└── run.sh               ← Script chạy Linux/Mac
```

## 🔍 KIỂM TRA BIÊN DỊCH

Để kiểm tra xem có bao nhiêu file .class:

**Trên Windows (PowerShell)**:
```powershell
Get-ChildItem -Path bin -Recurse -Filter "*.class" | Measure-Object
```

**Trên Linux/Mac**:
```bash
find bin -name "*.class" | wc -l
```

Kết quả mong đợi: **8 files**

## 📝 CHÚ THÍCH VỀ DỮ LIỆU

- ✅ Dữ liệu được lưu trong **bộ nhớ RAM**
- ❌ Dữ liệu **KHÔNG** được lưu vĩnh viễn vào file
- ℹ️ Khi tắt chương trình, dữ liệu sẽ mất
- 💾 Để lưu trữ, có thể:
  - Sử dụng các hàm trong `util.DataPersistence`
  - Kết nối với database (MySQL, PostgreSQL, v.v.)

## 🎯 TIẾP THEO

Sau khi chạy thành công, bạn có thể:

1. **Tìm hiểu thêm**: Xem file `CAU_TRUC.md`
2. **Chi tiết tính năng**: Xem file `TINH_NANG.md`
3. **Hướng dẫn chi tiết**: Xem file `HUONG_DAN.md`
4. **Mở rộng ứng dụng**: Thêm chức năng mới vào hệ thống

---

**Phiên bản**: 1.0  
**Cập nhật**: 27/11/2025
