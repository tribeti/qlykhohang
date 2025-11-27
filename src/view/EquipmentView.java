package view;

import controller.EquipmentController;
import model.Equipment;
import model.Transaction;
import util.ReportGenerator;
import java.util.List;
import java.util.Scanner;

public class EquipmentView {
    private EquipmentController controller;
    private Scanner scanner;

    public EquipmentView(EquipmentController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n========== QUẢN LÝ THIẾT BỊ ==========");
            System.out.println("1. Quản lý danh mục thiết bị");
            System.out.println("2. Ghi nhận nhập - xuất tồn");
            System.out.println("3. Thống kê thiết bị");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng (1-4): ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    displayEquipmentMenu();
                    break;
                case 2:
                    displayTransactionMenu();
                    break;
                case 3:
                    displayStatisticsMenu();
                    break;
                case 4:
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void displayEquipmentMenu() {
        while (true) {
            System.out.println("\n===== QUẢN LÝ DANH MỤC THIẾT BỊ =====");
            System.out.println("1. Xem danh sách thiết bị");
            System.out.println("2. Thêm thiết bị");
            System.out.println("3. Cập nhật thiết bị");
            System.out.println("4. Xóa thiết bị");
            System.out.println("5. Quay lại menu chính");
            System.out.print("Chọn chức năng (1-5): ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewAllEquipment();
                    break;
                case 2:
                    addNewEquipment();
                    break;
                case 3:
                    updateEquipment();
                    break;
                case 4:
                    deleteEquipment();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void displayTransactionMenu() {
        while (true) {
            System.out.println("\n===== GHI NHẬN NHẬP - XUẤT TỒN =====");
            System.out.println("1. Nhập thiết bị");
            System.out.println("2. Xuất thiết bị");
            System.out.println("3. Xem lịch sử giao dịch");
            System.out.println("4. Quay lại menu chính");
            System.out.print("Chọn chức năng (1-4): ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    importEquipment();
                    break;
                case 2:
                    exportEquipment();
                    break;
                case 3:
                    viewTransactions();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void displayStatisticsMenu() {
        while (true) {
            System.out.println("\n===== THỐNG KÊ THIẾT BỊ =====");
            System.out.println("1. Xem thống kê tổng quát");
            System.out.println("2. Xem danh sách thiết bị đang dùng");
            System.out.println("3. Xem danh sách thiết bị hỏng");
            System.out.println("4. Xem báo cáo chi tiết");
            System.out.println("5. Quay lại menu chính");
            System.out.print("Chọn chức năng (1-5): ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewStatistics();
                    break;
                case 2:
                    viewInUseEquipment();
                    break;
                case 3:
                    viewBrokenEquipment();
                    break;
                case 4:
                    viewDetailedReport();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void viewAllEquipment() {
        List<Equipment> list = controller.getAllEquipment();
        if (list.isEmpty()) {
            System.out.println("Không có thiết bị nào!");
            return;
        }
        
        System.out.println("\n===== DANH SÁCH THIẾT BỊ =====");
        System.out.printf("%-5s %-20s %-15s %-10s %-10s %-12s\n", "ID", "Tên", "Danh mục", "Số lượng", "Trạng thái", "Ngày nhập");
        System.out.println("-".repeat(82));
        
        for (Equipment e : list) {
            System.out.printf("%-5d %-20s %-15s %-10d %-10s %-12s\n",
                e.getId(),
                truncate(e.getName(), 20),
                truncate(e.getCategory(), 15),
                e.getQuantity(),
                e.getStatus(),
                e.getDateImported()
            );
        }
    }

    private void addNewEquipment() {
        System.out.println("\n===== THÊM THIẾT BỊ MỚI =====");
        System.out.print("Tên thiết bị: ");
        String name = scanner.nextLine();
        System.out.print("Danh mục: ");
        String category = scanner.nextLine();
        System.out.print("Số lượng: ");
        int quantity = getIntInput();
        System.out.print("Mô tả: ");
        String description = scanner.nextLine();
        
        controller.addEquipment(name, category, quantity, description);
        System.out.println("✓ Thêm thiết bị thành công!");
    }

    private void updateEquipment() {
        System.out.println("\n===== CẬP NHẬT THIẾT BỊ =====");
        System.out.print("ID thiết bị cần cập nhật: ");
        int id = getIntInput();
        
        Equipment equipment = controller.getEquipmentById(id);
        if (equipment == null) {
            System.out.println("✗ Không tìm thấy thiết bị!");
            return;
        }
        
        System.out.print("Tên mới (hiện tại: " + equipment.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Danh mục mới (hiện tại: " + equipment.getCategory() + "): ");
        String category = scanner.nextLine();
        System.out.print("Mô tả mới (hiện tại: " + equipment.getDescription() + "): ");
        String description = scanner.nextLine();
        
        controller.updateEquipment(id, name, category, description);
        System.out.println("✓ Cập nhật thiết bị thành công!");
    }

    private void deleteEquipment() {
        System.out.println("\n===== XÓA THIẾT BỊ =====");
        System.out.print("ID thiết bị cần xóa: ");
        int id = getIntInput();
        
        Equipment equipment = controller.getEquipmentById(id);
        if (equipment == null) {
            System.out.println("✗ Không tìm thấy thiết bị!");
            return;
        }
        
        System.out.print("Bạn chắc chắn muốn xóa thiết bị '" + equipment.getName() + "'? (Y/N): ");
        String confirm = scanner.nextLine();
        
        if ("Y".equalsIgnoreCase(confirm)) {
            controller.deleteEquipment(id);
            System.out.println("✓ Xóa thiết bị thành công!");
        } else {
            System.out.println("✗ Hủy bỏ xóa thiết bị!");
        }
    }

    private void importEquipment() {
        System.out.println("\n===== NHẬP THIẾT BỊ =====");
        viewAllEquipment();
        System.out.print("ID thiết bị cần nhập: ");
        int id = getIntInput();
        
        Equipment equipment = controller.getEquipmentById(id);
        if (equipment == null) {
            System.out.println("✗ Không tìm thấy thiết bị!");
            return;
        }
        
        System.out.print("Số lượng nhập: ");
        int quantity = getIntInput();
        System.out.print("Lý do nhập: ");
        String reason = scanner.nextLine();
        
        controller.importEquipment(id, quantity, reason);
        System.out.println("✓ Nhập thiết bị thành công! Số lượng hiện tại: " + equipment.getQuantity());
    }

    private void exportEquipment() {
        System.out.println("\n===== XUẤT THIẾT BỊ =====");
        viewAllEquipment();
        System.out.print("ID thiết bị cần xuất: ");
        int id = getIntInput();
        
        Equipment equipment = controller.getEquipmentById(id);
        if (equipment == null) {
            System.out.println("✗ Không tìm thấy thiết bị!");
            return;
        }
        
        System.out.print("Số lượng xuất: ");
        int quantity = getIntInput();
        
        if (quantity > equipment.getQuantity()) {
            System.out.println("✗ Số lượng xuất vượt quá tồn kho!");
            return;
        }
        
        System.out.print("Lý do xuất: ");
        String reason = scanner.nextLine();
        
        controller.exportEquipment(id, quantity, reason);
        System.out.println("✓ Xuất thiết bị thành công! Số lượng còn lại: " + equipment.getQuantity());
    }

    private void viewTransactions() {
        System.out.println("\n===== LỊCH SỬ GIAO DỊCH =====");
        System.out.println("1. Xem tất cả giao dịch");
        System.out.println("2. Xem giao dịch theo thiết bị");
        System.out.print("Chọn (1-2): ");
        
        int choice = getIntInput();
        if (choice == 1) {
            List<Transaction> transactions = controller.getAllTransactions();
            displayTransactions(transactions);
        } else if (choice == 2) {
            viewAllEquipment();
            System.out.print("ID thiết bị: ");
            int id = getIntInput();
            List<Transaction> transactions = controller.getTransactionsByEquipment(id);
            displayTransactions(transactions);
        }
    }

    private void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("Không có giao dịch nào!");
            return;
        }
        
        System.out.printf("%-5s %-5s %-10s %-10s %-12s %-15s\n", "ID", "Equip", "Loại", "Số lượng", "Ngày", "Lý do");
        System.out.println("-".repeat(72));
        
        for (Transaction t : transactions) {
            System.out.printf("%-5d %-5d %-10s %-10d %-12s %-15s\n",
                t.getId(),
                t.getEquipmentId(),
                t.getType(),
                t.getQuantity(),
                t.getDate(),
                truncate(t.getReason(), 15)
            );
        }
    }

    private void viewStatistics() {
        System.out.println("\n===== THỐNG KÊ TỔNG QUÁT =====");
        System.out.println("Tổng số thiết bị: " + controller.getAllEquipment().size());
        System.out.println("Tổng số lượng tồn kho: " + controller.getTotalQuantity());
        System.out.println("Thiết bị đang dùng: " + controller.countInUseEquipment());
        System.out.println("Thiết bị mới: " + controller.countNewEquipment());
        System.out.println("Thiết bị hỏng: " + controller.countBrokenEquipment());
    }

    private void viewInUseEquipment() {
        List<Equipment> list = controller.getInUseEquipment();
        if (list.isEmpty()) {
            System.out.println("\nKhông có thiết bị nào đang dùng!");
            return;
        }
        
        System.out.println("\n===== THIẾT BỊ ĐANG DÙNG =====");
        System.out.printf("%-5s %-20s %-15s %-10s %-12s\n", "ID", "Tên", "Danh mục", "Số lượng", "Ngày nhập");
        System.out.println("-".repeat(72));
        
        for (Equipment e : list) {
            System.out.printf("%-5d %-20s %-15s %-10d %-12s\n",
                e.getId(),
                truncate(e.getName(), 20),
                truncate(e.getCategory(), 15),
                e.getQuantity(),
                e.getDateImported()
            );
        }
    }

    private void viewBrokenEquipment() {
        List<Equipment> list = controller.getBrokenEquipment();
        if (list.isEmpty()) {
            System.out.println("\nKhông có thiết bị nào hỏng!");
            return;
        }
        
        System.out.println("\n===== THIẾT BỊ HỎ HỎNG =====");
        System.out.printf("%-5s %-20s %-15s %-10s %-12s\n", "ID", "Tên", "Danh mục", "Số lượng", "Ngày nhập");
        System.out.println("-".repeat(72));
        
        for (Equipment e : list) {
            System.out.printf("%-5d %-20s %-15s %-10d %-12s\n",
                e.getId(),
                truncate(e.getName(), 20),
                truncate(e.getCategory(), 15),
                e.getQuantity(),
                e.getDateImported()
            );
        }
    }

    private void viewDetailedReport() {
        ReportGenerator.printDetailedReport(controller.getAllEquipment(), controller.getAllTransactions());
    }

    // ===== HELPER METHODS =====
    private int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String truncate(String str, int length) {
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length - 1);
    }
}
