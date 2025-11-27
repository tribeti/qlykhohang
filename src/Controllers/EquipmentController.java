package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Models.Equipment;
import Models.Transaction;

public class EquipmentController {
    private List<Equipment> equipmentList;
    private List<Transaction> transactionList;
    private int equipmentIdCounter = 1;
    private int transactionIdCounter = 1;

    public EquipmentController() {
        this.equipmentList = new ArrayList<>();
        this.transactionList = new ArrayList<>();
        initializeSampleData();
    }

    // ===== QUẢN LÝ DANH MỤC THIẾT BỊ =====
    public void addEquipment(String name, String category, int quantity, String description) {
        Equipment equipment = new Equipment(
                equipmentIdCounter++,
                name,
                category,
                "new",
                quantity,
                getCurrentDate(),
                description
        );
        equipmentList.add(equipment);
    }

    public void updateEquipment(int id, String name, String category, String description) {
        for (Equipment e : equipmentList) {
            if (e.getId() == id) {
                e.setName(name);
                e.setCategory(category);
                e.setDescription(description);
                break;
            }
        }
    }

    public void deleteEquipment(int id) {
        equipmentList.removeIf(e -> e.getId() == id);
    }

    public Equipment getEquipmentById(int id) {
        for (Equipment e : equipmentList) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Equipment> getAllEquipment() {
        return new ArrayList<>(equipmentList);
    }

    // ===== GHI NHẬN NHẬP – XUẤT – TỒN =====
    public void importEquipment(int equipmentId, int quantity, String reason) {
        Equipment equipment = getEquipmentById(equipmentId);
        if (equipment != null) {
            equipment.setQuantity(equipment.getQuantity() + quantity);
            equipment.setStatus("in_use");

            Transaction transaction = new Transaction(
                    transactionIdCounter++,
                    equipmentId,
                    "import",
                    quantity,
                    getCurrentDate(),
                    reason,
                    ""
            );
            transactionList.add(transaction);
        }
    }

    public void exportEquipment(int equipmentId, int quantity, String reason) {
        Equipment equipment = getEquipmentById(equipmentId);
        if (equipment != null && equipment.getQuantity() >= quantity) {
            equipment.setQuantity(equipment.getQuantity() - quantity);

            Transaction transaction = new Transaction(
                    transactionIdCounter++,
                    equipmentId,
                    "export",
                    quantity,
                    getCurrentDate(),
                    reason,
                    ""
            );
            transactionList.add(transaction);
        }
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactionList);
    }

    public List<Transaction> getTransactionsByEquipment(int equipmentId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactionList) {
            if (t.getEquipmentId() == equipmentId) {
                result.add(t);
            }
        }
        return result;
    }

    // ===== THỐNG KÊ THIẾT BỊ =====
    public int countBrokenEquipment() {
        int count = 0;
        for (Equipment e : equipmentList) {
            if ("broken".equals(e.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public int countInUseEquipment() {
        int count = 0;
        for (Equipment e : equipmentList) {
            if ("in_use".equals(e.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public int countNewEquipment() {
        int count = 0;
        for (Equipment e : equipmentList) {
            if ("new".equals(e.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public int getTotalQuantity() {
        int total = 0;
        for (Equipment e : equipmentList) {
            total += e.getQuantity();
        }
        return total;
    }

    public List<Equipment> getBrokenEquipment() {
        List<Equipment> result = new ArrayList<>();
        for (Equipment e : equipmentList) {
            if ("broken".equals(e.getStatus())) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Equipment> getInUseEquipment() {
        List<Equipment> result = new ArrayList<>();
        for (Equipment e : equipmentList) {
            if ("in_use".equals(e.getStatus())) {
                result.add(e);
            }
        }
        return result;
    }

    public void markEquipmentAsBroken(int id) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            equipment.setStatus("broken");
        }
    }

    public void markEquipmentAsInUse(int id) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            equipment.setStatus("in_use");
        }
    }

    public void markEquipmentAsStored(int id) {
        Equipment equipment = getEquipmentById(id);
        if (equipment != null) {
            equipment.setStatus("stored");
        }
    }

    // ===== HELPER METHODS =====
    private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private void initializeSampleData() {
        // Thêm dữ liệu mẫu
        addEquipment("Máy tính", "Điện tử", 10, "Máy tính bàn phòng IT");
        addEquipment("Máy in", "Thiết bị văn phòng", 3, "Máy in laser đen trắng");
        addEquipment("Bàn làm việc", "Nội thất", 20, "Bàn làm việc gỗ");
        addEquipment("Ghế xoay", "Nội thất", 30, "Ghế xoay văn phòng");
        addEquipment("Điều hòa", "Điện tử", 5, "Điều hòa 2 chiều");
    }
}