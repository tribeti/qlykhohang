# TÍNH NĂNG CHI TIẾT ỨNG DỤNG QUẢN LÝ THIẾT BỊ

## 📊 TÍNH NĂNG 1: QUẢN LÝ DANH MỤC THIẾT BỊ

### 1.1. Xem Danh Sách Thiết Bị
**Mô tả**: Hiển thị bảng toàn bộ thiết bị với thông tin chi tiết

**Thông tin hiển thị**:
- ID: Mã định danh duy nhất của thiết bị
- Tên: Tên thiết bị
- Danh mục: Phân loại (Điện tử, Nội thất, v.v.)
- Số lượng: Tổng số lượng tồn kho
- Trạng thái: new, in_use, broken, stored
- Ngày nhập: Ngày thiết bị được nhập vào hệ thống

**Định dạng hiển thị**:
```
ID    Tên                  Danh mục        Số lượng   Trạng thái  Ngày nhập
────────────────────────────────────────────────────────────────────────
1     Máy tính            Điện tử         10        new        2025-11-27
2     Máy in              Thiết bị văn phòng  3     new        2025-11-27
```

**Trường hợp sử dụng**:
- Kiểm tra tổng thể kho thiết bị
- Xem trạng thái từng thiết bị
- Tra cứu thông tin trước khi thực hiện hành động

---

### 1.2. Thêm Thiết Bị Mới
**Mô tả**: Thêm một thiết bị mới vào hệ thống

**Thông tin cần nhập**:
1. Tên thiết bị (bắt buộc)
   - Ví dụ: "Máy chiếu", "Tủ lạnh", "Quạt"
   
2. Danh mục (bắt buộc)
   - Ví dụ: "Thiết bị hội họp", "Đồ gia dụng", "Điện tử"
   
3. Số lượng (bắt buộc)
   - Số nguyên dương
   - Ví dụ: 5, 10, 100
   
4. Mô tả (bắt buộc)
   - Chi tiết về thiết bị
   - Ví dụ: "Máy chiếu 3LCD 1080p cho phòng hội họp"

**Quy trình**:
```
1. Chọn "1. Quản lý danh mục thiết bị" → "2. Thêm thiết bị"
2. Nhập các thông tin
3. Hệ thống tự động:
   - Gán ID mới (tự tăng)
   - Đặt trạng thái = "new"
   - Ghi ngày nhập = ngày hiện tại
   - Lưu vào danh sách
4. Hiển thị "✓ Thêm thiết bị thành công!"
```

**Ví dụ cụ thể**:
```
Tên thiết bị: Máy Nước Nóng
Danh mục: Thiết bị sưởi ấm
Số lượng: 2
Mô tả: Máy nước nóng 30 lít cho nhà vệ sinh
✓ Thêm thiết bị thành công!
```

---

### 1.3. Cập Nhật Thông Tin Thiết Bị
**Mô tả**: Chỉnh sửa thông tin của thiết bị đã tồn tại

**Thông tin có thể cập nhật**:
- Tên thiết bị
- Danh mục
- Mô tả
- Trạng thái (nếu cần)

**Thông tin KHÔNG thể cập nhật**:
- ID (sinh tự động, duy nhất)
- Ngày nhập (lịch sử)
- Số lượng (cập nhật qua nhập/xuất)

**Quy trình**:
```
1. Chọn "1. Quản lý danh mục thiết bị" → "3. Cập nhật thiết bị"
2. Nhập ID thiết bị cần cập nhật
3. Hệ thống hiển thị thông tin hiện tại
4. Nhập thông tin mới (hoặc bỏ qua bằng Enter)
5. Cập nhật thành công
```

**Ví dụ cụ thể**:
```
ID thiết bị cần cập nhật: 1
Tên mới (hiện tại: Máy tính): Máy Tính Desktop Gaming
Danh mục mới (hiện tại: Điện tử): Thiết bị IT
Mô tả mới (hiện tại: Máy tính bàn phòng IT): Máy tính gaming RTX 4090
✓ Cập nhật thiết bị thành công!
```

---

### 1.4. Xóa Thiết Bị
**Mô tả**: Xóa thiết bị khỏi hệ thống

**Điều kiện**:
- Thiết bị tồn tại trong hệ thống
- Cần xác nhận trước khi xóa (bảo vệ dữ liệu)

**Quy trình**:
```
1. Chọn "1. Quản lý danh mục thiết bị" → "4. Xóa thiết bị"
2. Nhập ID thiết bị cần xóa
3. Hệ thống hiển thị tên thiết bị
4. Xác nhận (nhập Y hoặc N)
5. Xóa hoặc hủy bỏ
```

**Ví dụ cụ thể**:
```
ID thiết bị cần xóa: 2
Bạn chắc chắn muốn xóa thiết bị 'Máy in'? (Y/N): Y
✓ Xóa thiết bị thành công!
```

⚠️ **LƯU Ý**: Xóa thiết bị sẽ xóa luôn tất cả lịch sử giao dịch liên quan

---

## 📥📤 TÍNH NĂNG 2: GHI NHẬN NHẬP – XUẤT – TỒN

### 2.1. Nhập Thiết Bị
**Mô tả**: Ghi nhận quá trình nhập thiết bị vào kho

**Lý do nhập thường gặp**:
- "Mua mới từ nhà cung cấp"
- "Tái sử dụng từ dự án cũ"
- "Trả từ phòng khác"
- "Sửa chữa xong"
- "Cho mượn lại"

**Thông tin cần nhập**:
1. ID thiết bị
2. Số lượng nhập
3. Lý do nhập

**Quy trình**:
```
1. Chọn "2. Ghi nhận nhập - xuất tồn" → "1. Nhập thiết bị"
2. Xem danh sách thiết bị
3. Chọn ID thiết bị
4. Nhập số lượng và lý do
5. Hệ thống:
   - Tăng số lượng tồn kho
   - Cập nhật trạng thái = "in_use"
   - Ghi nhận giao dịch với ngày giờ
6. Hiển thị số lượng mới
```

**Tác động dữ liệu**:
- Số lượng thiết bị: Tăng
- Trạng thái: Chuyển thành "in_use"
- Lịch sử giao dịch: Thêm bản ghi IMPORT

**Ví dụ cụ thể**:
```
ID thiết bị cần nhập: 3
Số lượng nhập: 5
Lý do nhập: Mua mới từ nhà cung cấp
✓ Nhập thiết bị thành công! Số lượng hiện tại: 25
```

---

### 2.2. Xuất Thiết Bị
**Mô tả**: Ghi nhận quá trình xuất thiết bị ra khỏi kho

**Lý do xuất thường gặp**:
- "Sử dụng cho dự án"
- "Cho mượn"
- "Hỏng cần sửa"
- "Trả cho phòng khác"
- "Thanh lý"

**Thông tin cần nhập**:
1. ID thiết bị
2. Số lượng xuất
3. Lý do xuất

**Kiểm tra tự động**:
- Hệ thống kiểm tra số lượng tồn kho
- Nếu không đủ: Cảnh báo "Số lượng xuất vượt quá tồn kho!"
- Nếu đủ: Thực hiện xuất

**Quy trình**:
```
1. Chọn "2. Ghi nhận nhập - xuất tồn" → "2. Xuất thiết bị"
2. Xem danh sách thiết bị (kiểm tra số lượng)
3. Chọn ID thiết bị
4. Nhập số lượng xuất (phải ≤ số lượng hiện tại)
5. Nhập lý do
6. Hệ thống:
   - Giảm số lượng tồn kho
   - Ghi nhận giao dịch EXPORT
   - Hiển thị số lượng còn lại
```

**Tác động dữ liệu**:
- Số lượng thiết bị: Giảm
- Trạng thái: Vẫn không thay đổi
- Lịch sử giao dịch: Thêm bản ghi EXPORT

**Ví dụ cụ thể**:
```
ID thiết bị cần xuất: 1
Số lượng xuất: 2
Lý do xuất: Sử dụng cho dự án A
✓ Xuất thiết bị thành công! Số lượng còn lại: 8
```

---

### 2.3. Xem Lịch Sử Giao Dịch
**Mô tả**: Xem toàn bộ lịch sử nhập/xuất thiết bị

**Hai chế độ xem**:
1. Xem tất cả giao dịch
2. Xem giao dịch của một thiết bị cụ thể

**Thông tin hiển thị**:
- ID giao dịch
- ID thiết bị
- Loại giao dịch (IMPORT/EXPORT)
- Số lượng
- Ngày giao dịch
- Lý do

**Định dạng hiển thị**:
```
ID    Equip   Loại       Số lượng   Ngày         Lý do
────────────────────────────────────────────────────
1     1       IMPORT     5          2025-11-27   Mua mới
2     2       EXPORT     2          2025-11-27   Cho mượn
3     1       IMPORT     3          2025-11-27   Tái sử dụng
```

**Trường hợp sử dụng**:
- Kiểm tra lịch sử nhập/xuất
- Truy tìm lý do thay đổi số lượng
- Báo cáo chi tiết cho quản lý

---

## 📊 TÍNH NĂNG 3: THỐNG KÊ THIẾT BỊ

### 3.1. Thống Kê Tổng Quát
**Mô tả**: Xem các chỉ số chính về thiết bị

**Các chỉ số**:
1. **Tổng số thiết bị**: Số loại thiết bị (bao gồm tất cả trạng thái)
2. **Tổng số lượng tồn kho**: Tổng số cái/chiếc tất cả thiết bị
3. **Thiết bị đang dùng**: Số loại có trạng thái "in_use"
4. **Thiết bị mới**: Số loại có trạng thái "new"
5. **Thiết bị hỏng**: Số loại có trạng thái "broken"

**Ví dụ hiển thị**:
```
===== THỐNG KÊ TỔNG QUÁT =====
Tổng số thiết bị: 5
Tổng số lượng tồn kho: 68
Thiết bị đang dùng: 2
Thiết bị mới: 3
Thiết bị hỏng: 0
```

**Diễn giải**:
- 5 loại thiết bị khác nhau
- 68 cái thiết bị tất cả
- 2 loại thiết bị đang được sử dụng
- 3 loại thiết bị chưa dùng
- Không có thiết bị hỏng

---

### 3.2. Danh Sách Thiết Bị Đang Dùng
**Mô tả**: Xem chi tiết các thiết bị có trạng thái "in_use"

**Thông tin hiển thị**:
- ID, Tên, Danh mục, Số lượng, Ngày nhập

**Sử dụng**:
- Kiểm tra thiết bị nào đang hoạt động
- Biết bao nhiêu thiết bị đang được sử dụng
- Kế hoạch bảo trì/sửa chữa

---

### 3.3. Danh Sách Thiết Bị Hỏng
**Mô tả**: Xem chi tiết các thiết bị có trạng thái "broken"

**Thông tin hiển thị**:
- ID, Tên, Danh mục, Số lượng, Ngày nhập

**Sử dụng**:
- Theo dõi thiết bị cần sửa chữa
- Lập kế hoạch bảo hành/thay thế
- Đánh giá độ bền

---

### 3.4. Báo Cáo Chi Tiết
**Mô tả**: Xem báo cáo tổng hợp đầy đủ

**Nội dung báo cáo**:

**1. THỐNG KÊ CHUNG**
- Tổng số thiết bị
- Tổng số lượng
- Thiết bị đang dùng
- Thiết bị mới
- Thiết bị hỏng
- Thiết bị lưu trữ

**2. THỐNG KÊ THEO DANH MỤC**
Danh mục 1: X đơn vị
Danh mục 2: Y đơn vị
...

**3. GIAO DỊCH GẦN ĐÂY**
5 giao dịch mới nhất với:
- Ngày giao dịch
- Loại (IMPORT/EXPORT)
- Số lượng
- ID thiết bị

**Ví dụ báo cáo**:
```
╔══════════════════════════════════════════════════════╗
║            BÁO CÁO CHI TIẾT THIẾT BỊ               ║
╚══════════════════════════════════════════════════════╝

[THỐNG KÊ CHUNG]
├─ Tổng số loại thiết bị: 5
├─ Tổng số lượng tồn kho: 68
├─ Thiết bị đang sử dụng: 2
├─ Thiết bị hỏng: 0
├─ Thiết bị mới: 3
└─ Thiết bị lưu trữ: 0

[THỐNG KÊ THEO DANH MỤC]
├─ Điện tử: 15 đơn vị
├─ Nội thất: 50 đơn vị
└─ Thiết bị văn phòng: 3 đơn vị

[GIAO DỊCH GẦN ĐÂY]
├─ [2025-11-27] IMPORT 5 đơn vị (ID: 1)
├─ [2025-11-27] EXPORT 2 đơn vị (ID: 2)
├─ [2025-11-27] IMPORT 3 đơn vị (ID: 1)
└─ Không có giao dịch thêm
```

---

## 🔄 QUY TRÌNH VÀ LUỒNG DỮ LIỆU

### Quy Trình Nhập - Xuất - Tồn

```
NHẬP THIẾT BỊ
├─ Chọn thiết bị
├─ Nhập số lượng
├─ Nhập lý do
└─ Kết quả:
   ├─ Số lượng ↑
   ├─ Trạng thái → "in_use"
   └─ Ghi nhận IMPORT transaction

XUẤT THIẾT BỊ
├─ Chọn thiết bị
├─ Nhập số lượng
├─ Kiểm tra tồn kho
├─ Nếu đủ:
│  ├─ Số lượng ↓
│  └─ Ghi nhận EXPORT transaction
└─ Nếu không đủ:
   └─ Hiển thị lỗi
```

---

## 📈 DỮ LIỆU MẪU BAN ĐẦU

```
ID │ Tên         │ Danh mục            │ Số lượng │ Trạng thái
─────┼─────────────┼─────────────────────┼──────────┼──────────
 1  │ Máy tính    │ Điện tử              │ 10       │ new
 2  │ Máy in      │ Thiết bị văn phòng   │ 3        │ new
 3  │ Bàn làm việc│ Nội thất            │ 20       │ new
 4  │ Ghế xoay    │ Nội thất            │ 30       │ new
 5  │ Điều hòa    │ Điện tử              │ 5        │ new
```

---

**Phiên bản**: 1.0  
**Cập nhật**: 27/11/2025
