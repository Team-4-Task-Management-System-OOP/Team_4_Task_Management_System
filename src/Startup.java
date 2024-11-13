import com.company.oop.task.management.system.core.TaskManagementSystemEngineImpl;

public class Startup {
    public static void main(String[] args) {
        TaskManagementSystemEngineImpl engine = new TaskManagementSystemEngineImpl();
        engine.start();
    }
}