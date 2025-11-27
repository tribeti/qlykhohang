package model;

public class Equipment {
    private int id;
    private String name;
    private String category;
    private String status; // "new", "in_use", "broken", "stored"
    private int quantity;
    private String dateImported;
    private String description;

    public Equipment() {
    }

    public Equipment(int id, String name, String category, String status, int quantity, String dateImported, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
        this.dateImported = dateImported;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateImported() {
        return dateImported;
    }

    public void setDateImported(String dateImported) {
        this.dateImported = dateImported;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", quantity=" + quantity +
                ", dateImported='" + dateImported + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
