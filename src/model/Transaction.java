package model;

public class Transaction {
    private int id;
    private int equipmentId;
    private String type; // "import", "export"
    private int quantity;
    private String date;
    private String reason;
    private String notes;

    public Transaction() {
    }

    public Transaction(int id, int equipmentId, String type, int quantity, String date, String reason, String notes) {
        this.id = id;
        this.equipmentId = equipmentId;
        this.type = type;
        this.quantity = quantity;
        this.date = date;
        this.reason = reason;
        this.notes = notes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", equipmentId=" + equipmentId +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
