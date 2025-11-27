package util;

import model.Equipment;
import model.Transaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    
    public static class EquipmentStatistics {
        public int totalItems;
        public int totalQuantity;
        public int inUseCount;
        public int brokenCount;
        public int newCount;
        public int storedCount;
    }

    public static EquipmentStatistics generateStatistics(List<Equipment> equipmentList) {
        EquipmentStatistics stats = new EquipmentStatistics();
        
        stats.totalItems = equipmentList.size();
        
        for (Equipment e : equipmentList) {
            stats.totalQuantity += e.getQuantity();
            
            switch (e.getStatus()) {
                case "in_use":
                    stats.inUseCount++;
                    break;
                case "broken":
                    stats.brokenCount++;
                    break;
                case "new":
                    stats.newCount++;
                    break;
                case "stored":
                    stats.storedCount++;
                    break;
            }
        }
        
        return stats;
    }

    public static Map<String, Integer> getCategoryStatistics(List<Equipment> equipmentList) {
        Map<String, Integer> categoryStats = new HashMap<>();
        
        for (Equipment e : equipmentList) {
            categoryStats.put(e.getCategory(),
                    categoryStats.getOrDefault(e.getCategory(), 0) + e.getQuantity());
        }
        
        return categoryStats;
    }

    public static void printDetailedReport(List<Equipment> equipmentList, List<Transaction> transactionList) {
        EquipmentStatistics stats = generateStatistics(equipmentList);
        Map<String, Integer> categoryStats = getCategoryStatistics(equipmentList);
        
        System.out.println("\n");
        System.out.println("╔" + "═".repeat(50) + "╗");
        System.out.println("║" + String.format("%49s", "BÁO CÁO CHI TIẾT THIẾT BỊ").substring(0, 49) + "║");
        System.out.println("╚" + "═".repeat(50) + "╝");
        
        System.out.println("\n[THỐNG KÊ CHUNG]");
        System.out.println("├─ Tổng số loại thiết bị: " + stats.totalItems);
        System.out.println("├─ Tổng số lượng tồn kho: " + stats.totalQuantity);
        System.out.println("├─ Thiết bị đang sử dụng: " + stats.inUseCount);
        System.out.println("├─ Thiết bị hỏng: " + stats.brokenCount);
        System.out.println("├─ Thiết bị mới: " + stats.newCount);
        System.out.println("└─ Thiết bị lưu trữ: " + stats.storedCount);
        
        System.out.println("\n[THỐNG KÊ THEO DANH MỤC]");
        categoryStats.forEach((category, quantity) ->
                System.out.println("├─ " + category + ": " + quantity + " đơn vị")
        );
        
        System.out.println("\n[GIAO DỊCH GẦN ĐÂY]");
        int count = 0;
        for (int i = transactionList.size() - 1; i >= 0 && count < 5; i--, count++) {
            Transaction t = transactionList.get(i);
            System.out.println("├─ [" + t.getDate() + "] " + t.getType().toUpperCase() +
                    " " + t.getQuantity() + " đơn vị (ID: " + t.getEquipmentId() + ")");
        }
        
        if (transactionList.isEmpty()) {
            System.out.println("└─ Chưa có giao dịch nào");
        }
        System.out.println();
    }
}
