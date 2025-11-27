# 🎯 HƯỚNG DẪN BẮTĐẦU NHANH

## 🚀 CHẠY CHƯƠNG TRÌNH NGAY (30 GIÂY)

### Windows:
```batch
run.bat
```

### Linux/Mac:
```bash
./run.sh
```

---

## 📚 DANH SÁCH TÀI LIỆU

### 🔥 **BẮTĐẦU ĐỌC NGAY**
| # | Tài Liệu | Nội Dung | Thời Gian |
|---|----------|---------|----------|
| 1️⃣ | 📖 [CHUONG_TRINH_CHAY.md](CHUONG_TRINH_CHAY.md) | **Hướng dẫn chạy chương trình** | 5 phút |
| 2️⃣ | 📖 [HUONG_DAN.md](HUONG_DAN.md) | **Hướng dẫn chi tiết từng tính năng** | 20 phút |
| 3️⃣ | 📖 [TINH_NANG.md](TINH_NANG.md) | **Chi tiết tính năng + ví dụ** | 15 phút |

### 📚 **HIỂU KIẾN TRÚC**
| # | Tài Liệu | Nội Dung | Thời Gian |
|---|----------|---------|----------|
| 4️⃣ | 📖 [CAU_TRUC.md](CAU_TRUC.md) | **Giải thích mô hình MVC** | 30 phút |
| 5️⃣ | 📖 [TONG_QUAN_DU_AN.md](TONG_QUAN_DU_AN.md) | **Tổng quan toàn bộ dự án** | 15 phút |

### 📄 **THAM KHẢO**
| # | Tài Liệu | Nội Dung |
|---|----------|---------|
| 📋 | [README.md](README.md) | Giới thiệu dự án |

---

## 🎯 LỘ TRÌNH ĐỀ XUẤT

### 👶 Người Mới Bắt Đầu
1. **Chạy chương trình** → `run.bat` (5 phút)
2. **Tìm hiểu tính năng** → [HUONG_DAN.md](HUONG_DAN.md) (20 phút)
3. **Thử nghiệm các chức năng** → Chạy chương trình (15 phút)
4. **Hiểu kiến trúc** → [CAU_TRUC.md](CAU_TRUC.md) (30 phút)

### 👨‍💻 Lập Trình Viên
1. **Xem tổng quan** → [TONG_QUAN_DU_AN.md](TONG_QUAN_DU_AN.md) (15 phút)
2. **Hiểu kiến trúc** → [CAU_TRUC.md](CAU_TRUC.md) (30 phút)
3. **Đọc mã nguồn** → File trong `src/` (60 phút)
4. **Mở rộng ứng dụng** → Thêm tính năng mới

---

## 💾 CẤU TRÚC THƯ MỤC

```
qlykhohang/
├── 📖 CHUONG_TRINH_CHAY.md    ← START HERE! 🔥
├── 📖 HUONG_DAN.md            ← Hướng dẫn sử dụng
├── 📖 TINH_NANG.md            ← Chi tiết tính năng
├── 📖 CAU_TRUC.md             ← Kiến trúc MVC
├── 📖 TONG_QUAN_DU_AN.md      ← Tổng quan
├── 📖 README.md               ← Giới thiệu
├── 💻 src/                    ← Mã nguồn Java
│   ├── App.java
│   ├── model/
│   │   ├── Equipment.java
│   │   └── Transaction.java
│   ├── controller/
│   │   └── EquipmentController.java
│   ├── view/
│   │   └── EquipmentView.java
│   └── util/
│       ├── DataPersistence.java
│       └── ReportGenerator.java
├── ⚙️ bin/                     ← Tệp biên dịch (8 .class)
├── 🚀 run.bat                 ← Script Windows
└── 🚀 run.sh                  ← Script Linux/Mac
```

---

## ✨ TÍNH NĂNG CHÍNH

### 1️⃣ Quản Lý Danh Mục Thiết Bị
- ✅ Xem danh sách
- ✅ Thêm mới
- ✅ Cập nhật
- ✅ Xóa

### 2️⃣ Ghi Nhận Nhập – Xuất – Tồn
- ✅ Nhập thiết bị
- ✅ Xuất thiết bị
- ✅ Xem lịch sử giao dịch

### 3️⃣ Thống Kê Thiết Bị
- ✅ Thống kê tổng quát
- ✅ Thiết bị đang dùng
- ✅ Thiết bị hỏng
- ✅ Báo cáo chi tiết

---

## 🎓 MỞ RỘNG KIẾN THỨC

### Mô Hình MVC Là Gì?
**MVC = Model - View - Controller**

```
┌────────┐
│ Model  │ ← Dữ liệu (Equipment, Transaction)
├────────┤
│ View   │ ← Giao diện (Menu, Display)
├────────┤
│Control │ ← Logic (Xử lý, Tính toán)
└────────┘
```

### Lợi Ích
- 🔄 Tách biệt trách nhiệm
- 🛡️ Dễ bảo trì
- 📈 Dễ mở rộng
- 🧪 Dễ kiểm thử

---

## ❓ CÂU HỎI THƯỜNG GẶP

### Q1: Làm sao để chạy chương trình?
**A:** Xem [CHUONG_TRINH_CHAY.md](CHUONG_TRINH_CHAY.md)

### Q2: Dữ liệu có được lưu không?
**A:** Không, dữ liệu được lưu trong RAM. Khi tắt chương trình, dữ liệu mất.

### Q3: Cách thêm thiết bị?
**A:** Xem [HUONG_DAN.md](HUONG_DAN.md) - Phần 1.2

### Q4: Làm sao để hiểu mô hình MVC?
**A:** Xem [CAU_TRUC.md](CAU_TRUC.md)

### Q5: Làm sao để mở rộng ứng dụng?
**A:** Xem [CAU_TRUC.md](CAU_TRUC.md) - Phần "Mở Rộng Ứng Dụng"

---

## 🆘 GẶP LỖI?

### Lỗi 1: "The Main class not found"
→ Xem [CHUONG_TRINH_CHAY.md](CHUONG_TRINH_CHAY.md) - Phần "Khắc Phục Sự Cố"

### Lỗi 2: "java: command not found"
→ Cài Java JDK từ java.com

### Lỗi 3: Chương trình không phản hồi
→ Nhập số hợp lệ hoặc Ctrl+C để thoát

---

## 🎯 MỤC TIÊU HỌC TẬP

Sau khi hoàn thành, bạn sẽ biết:
- ✅ Mô hình MVC là gì
- ✅ Cách thiết kế ứng dụng Java
- ✅ Cách tổ chức code thành các package
- ✅ Cách tương tác với người dùng
- ✅ Cách xử lý dữ liệu
- ✅ Cách viết tài liệu code

---

## 📊 THỐNG KÊ

| Yếu Tố | Giá Trị |
|--------|--------|
| Ngôn ngữ | Java ☕ |
| Mô hình | MVC |
| Tệp Java | 7 |
| Dòng code | 1500+ |
| Tính năng | 3 chính + nhiều tính năng phụ |
| Trạng thái | ✅ Hoàn thành |

---

## 🚀 BẮT ĐẦU NGAY

### Bước 1: Chạy
```bash
run.bat
```

### Bước 2: Đọc
📖 [CHUONG_TRINH_CHAY.md](CHUONG_TRINH_CHAY.md)

### Bước 3: Thử
Chọn các tính năng trong menu

### Bước 4: Học
📖 [CAU_TRUC.md](CAU_TRUC.md)

### Bước 5: Mở Rộng
Thêm tính năng mới

---

## ✉️ PHẢN HỒI

Nếu bạn có bất kỳ câu hỏi nào:
1. Đọc các tài liệu tương ứng
2. Kiểm tra phần FAQ
3. Xem phần khắc phục sự cố

---

## 📅 THÔNG TIN PHIÊN BẢN

- **Phiên bản**: 1.0
- **Ngày cập nhật**: 27/11/2025
- **Trạng thái**: ✅ Sẵn sàng sử dụng
- **Java**: JDK 8+

---

**🎉 Chúc bạn sử dụng vui vẻ!**

👉 [**BẮT ĐẦU NGAY - Nhấp đây**](CHUONG_TRINH_CHAY.md)
