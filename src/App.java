import controller.EquipmentController;
import view.EquipmentView;

public class App {
    public static void main(String[] args) throws Exception {
        // Khởi tạo Controller
        EquipmentController controller = new EquipmentController();
        
        // Khởi tạo View
        EquipmentView view = new EquipmentView(controller);
        
        // Hiển thị menu chính
        view.displayMenu();
    }
}
