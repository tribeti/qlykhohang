package util;

import model.Equipment;
import model.Transaction;
import java.io.*;
import java.util.List;

public class DataPersistence {
    private static final String EQUIPMENT_FILE = "data/equipment.txt";
    private static final String TRANSACTION_FILE = "data/transaction.txt";

    static {
        // Tạo thư mục data nếu chưa tồn tại
        new File("data").mkdirs();
    }

    public static void saveEquipmentToFile(List<Equipment> equipmentList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(EQUIPMENT_FILE))) {
            for (Equipment e : equipmentList) {
                writer.println(e.getId() + "|" + e.getName() + "|" + e.getCategory() + "|" +
                        e.getStatus() + "|" + e.getQuantity() + "|" + e.getDateImported() + "|" +
                        e.getDescription());
            }
            System.out.println("✓ Dữ liệu thiết bị đã được lưu!");
        } catch (IOException e) {
            System.out.println("✗ Lỗi khi lưu dữ liệu thiết bị: " + e.getMessage());
        }
    }

    public static void saveTransactionsToFile(List<Transaction> transactionList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTION_FILE))) {
            for (Transaction t : transactionList) {
                writer.println(t.getId() + "|" + t.getEquipmentId() + "|" + t.getType() + "|" +
                        t.getQuantity() + "|" + t.getDate() + "|" + t.getReason() + "|" +
                        t.getNotes());
            }
            System.out.println("✓ Dữ liệu giao dịch đã được lưu!");
        } catch (IOException e) {
            System.out.println("✗ Lỗi khi lưu dữ liệu giao dịch: " + e.getMessage());
        }
    }

    public static void exportReport(List<Equipment> equipmentList, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("========== BÁO CÁO THIẾT BỊ ==========");
            writer.println();
            writer.println("Ngày in: " + new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
            writer.println();
            writer.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Tên", "Danh mục", "Số lượng", "Trạng thái");
            writer.println("-".repeat(60));
            
            for (Equipment e : equipmentList) {
                writer.printf("%-5d %-20s %-15s %-10d %-10s%n",
                        e.getId(),
                        e.getName(),
                        e.getCategory(),
                        e.getQuantity(),
                        e.getStatus());
            }
            
            System.out.println("✓ Báo cáo đã được xuất ra file: " + filename);
        } catch (IOException e) {
            System.out.println("✗ Lỗi khi xuất báo cáo: " + e.getMessage());
        }
    }
}
