# HƯỚNG DẪN SỬ DỤNG ỨNG DỤNG QUẢN LÝ THIẾT BỊ

## 1. CÀI ĐẶT VÀ KHỞI CHẠY

### Trên Windows:
```bash
# Cách 1: Chạy file batch
double-click run.bat

# Cách 2: Chạy bằng Command Prompt
cd qlykhohang
run.bat

# Cách 3: Biên dịch và chạy thủ công
javac -d bin src\model\*.java src\controller\*.java src\view\*.java src\util\*.java src\App.java
java -cp bin App
```

### Trên Linux/Mac:
```bash
# Cách 1: Chạy file shell script
chmod +x run.sh
./run.sh

# Cách 2: Biên dịch và chạy thủ công
javac -d bin src/model/*.java src/controller/*.java src/view/*.java src/util/*.java src/App.java
java -cp bin App
```

## 2. MENU CHÍNH

Khi khởi chạy ứng dụng, bạn sẽ thấy menu chính với 4 lựa chọn:

```
========== QUẢN LÝ THIẾT BỊ ==========
1. Quản lý danh mục thiết bị
2. Ghi nhận nhập - xuất tồn
3. Thống kê thiết bị
4. Thoát
```

## 3. CHỦ ĐỀ 1: QUẢN LÝ DANH MỤC THIẾT BỊ

### 1.1 Xem Danh Sách Thiết Bị
- Hiển thị bảng với tất cả thiết bị hiện có
- Thông tin hiển thị: ID, Tên, Danh mục, Số lượng, Trạng thái, Ngày nhập

**Ví dụ:**
```
ID    Tên                  Danh mục        Số lượng   Trạng thái  Ngày nhập
1     Máy tính            Điện tử         10        new        2025-11-27
2     Máy in              Thiết bị văn phòng  3      new        2025-11-27
```

### 1.2 Thêm Thiết Bị Mới
- Nhập thông tin:
  - Tên thiết bị (ví dụ: "Máy chiếu")
  - Danh mục (ví dụ: "Thiết bị hội họp")
  - Số lượng (ví dụ: 2)
  - Mô tả (ví dụ: "Máy chiếu 3LCD 1080p")

**Quy trình:**
```
Tên thiết bị: Máy chiếu
Danh mục: Thiết bị hội họp
Số lượng: 2
Mô tả: Máy chiếu 3LCD 1080p
✓ Thêm thiết bị thành công!
```

### 1.3 Cập Nhật Thiết Bị
- Nhập ID của thiết bị cần cập nhật
- Nhập thông tin mới (hoặc bỏ qua bằng Enter)
- Thông tin sẽ được cập nhật

**Quy trình:**
```
ID thiết bị cần cập nhật: 1
Tên mới (hiện tại: Máy tính): Máy tính Desktop
Danh mục mới (hiện tại: Điện tử): Máy tính
Mô tả mới (hiện tại: Máy tính bàn phòng IT): Máy tính để bàn phòng IT
✓ Cập nhật thiết bị thành công!
```

### 1.4 Xóa Thiết Bị
- Nhập ID thiết bị cần xóa
- Xác nhận lại trước khi xóa (nhập Y để xóa)

**Quy trình:**
```
ID thiết bị cần xóa: 1
Bạn chắc chắn muốn xóa thiết bị 'Máy tính'? (Y/N): Y
✓ Xóa thiết bị thành công!
```

## 4. CHỦ ĐỀ 2: GHI NHẬN NHẬP – XUẤT TỒN

### 2.1 Nhập Thiết Bị
- Xem danh sách thiết bị sẵn có
- Chọn ID thiết bị muốn nhập
- Nhập số lượng và lý do nhập

**Lý do nhập mẫu:**
- "Mua mới"
- "Tái sử dụng"
- "Trả từ phòng khác"
- "Sửa chữa xong"

**Quy trình:**
```
ID thiết bị cần nhập: 1
Số lượng nhập: 5
Lý do nhập: Mua mới
✓ Nhập thiết bị thành công! Số lượng hiện tại: 15
```

### 2.2 Xuất Thiết Bị
- Xem danh sách thiết bị
- Chọn ID thiết bị muốn xuất
- Ứng dụng kiểm tra số lượng tồn kho
- Nhập số lượng xuất và lý do xuất

**Lý do xuất mẫu:**
- "Sử dụng cho dự án"
- "Cho mượn"
- "Hỏng cần sửa"
- "Trả cho phòng khác"

**Quy trình:**
```
ID thiết bị cần xuất: 2
Số lượng xuất: 2
Lý do xuất: Sử dụng cho dự án
✓ Xuất thiết bị thành công! Số lượng còn lại: 1
```

### 2.3 Xem Lịch Sử Giao Dịch
- Xem tất cả giao dịch hoặc theo thiết bị
- Hiển thị: ID, ID thiết bị, Loại (IMPORT/EXPORT), Số lượng, Ngày, Lý do

**Hiển thị:**
```
ID    Equip   Loại       Số lượng   Ngày         Lý do
1     1       import     5          2025-11-27   Mua mới
2     2       export     2          2025-11-27   Sử dụng cho dự án
```

## 5. CHỦ ĐỀ 3: THỐNG KÊ THIẾT BỊ

### 5.1 Thống Kê Tổng Quát
Hiển thị:
- Tổng số thiết bị
- Tổng số lượng tồn kho
- Thiết bị đang dùng
- Thiết bị mới
- Thiết bị hỏng

**Ví dụ:**
```
===== THỐNG KÊ TỔNG QUÁT =====
Tổng số thiết bị: 5
Tổng số lượng tồn kho: 68
Thiết bị đang dùng: 2
Thiết bị mới: 3
Thiết bị hỏng: 0
```

### 5.2 Thiết Bị Đang Dùng
- Hiển thị danh sách tất cả thiết bị có trạng thái "in_use"
- Thông tin: ID, Tên, Danh mục, Số lượng, Ngày nhập

### 5.3 Thiết Bị Hỏng
- Hiển thị danh sách tất cả thiết bị có trạng thái "broken"
- Thông tin: ID, Tên, Danh mục, Số lượng, Ngày nhập

### 5.4 Báo Cáo Chi Tiết
Báo cáo gồm:
- **THỐNG KÊ CHUNG**: Các số liệu tổng hợp
- **THỐNG KÊ THEO DANH MỤC**: Số lượng thiết bị theo từng danh mục
- **GIAO DỊCH GẦN ĐÂY**: 5 giao dịch mới nhất

## 6. QUẢN LÝ TRẠNG THÁI THIẾT BỊ

Ứng dụng tự động quản lý trạng thái:
- **new**: Thiết bị mới (trạng thái ban đầu)
- **in_use**: Thiết bị đang sử dụng (khi nhập)
- **broken**: Thiết bị hỏng (có thể đánh dấu thủ công)
- **stored**: Thiết bị lưu trữ (có thể đánh dấu thủ công)

## 7. MẹO VÀ LƯỚI Ý

### Mẹo Sử Dụng:
1. **Kiểm tra trước khi xuất**: Ứng dụng sẽ cảnh báo nếu số lượng xuất vượt quá tồn kho
2. **Xem danh sách trước**: Luôn xem danh sách thiết bị trước khi chọn ID
3. **Ghi lý do rõ ràng**: Ghi lý do chi tiết giúp theo dõi nguồn gốc
4. **Định kỳ kiểm tra**: Xem báo cáo chi tiết để nắm tổng quan

### Lưu Ý:
- Dữ liệu được lưu trong bộ nhớ (chương trình không lưu vào file vĩnh viễn)
- Khi tắt chương trình, dữ liệu sẽ mất
- Để lưu trữ, có thể sử dụng các hàm trong `util.DataPersistence`
- Ứng dụng hỗ trợ tiếng Việt đầy đủ

## 8. TRỊ SỐ MẶC ĐỊNH

Ứng dụng được khởi tạo với dữ liệu mẫu:
| ID | Tên | Danh mục | Số lượng | Trạng thái |
|---|---|---|---|---|
| 1 | Máy tính | Điện tử | 10 | new |
| 2 | Máy in | Thiết bị văn phòng | 3 | new |
| 3 | Bàn làm việc | Nội thất | 20 | new |
| 4 | Ghế xoay | Nội thất | 30 | new |
| 5 | Điều hòa | Điện tử | 5 | new |

## 9. GIẢI QUYẾT SỰ CỐ

### Lỗi: "Lỗi biên dịch"
- Kiểm tra Java JDK đã cài đặt: `javac -version`
- Đảm bảo tất cả tệp .java nằm đúng vị trí

### Lỗi: "Cannot find main class App"
- Kiểm tra thư mục `bin` có tồn tại
- Tái biên dịch dự án

### Lỗi: "Số lượng xuất vượt quá tồn kho"
- Xem danh sách thiết bị để kiểm tra số lượng hiện tại
- Xuất số lượng nhỏ hơn hoặc bằng tồn kho

---

**Phiên bản**: 1.0  
**Cập nhật lần cuối**: 27/11/2025  
**Tác giả**: Hệ thống Quản lý Thiết Bị MVC
