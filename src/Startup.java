import com.company.oop.task.management.system.core.TaskManagementSystemEngineImpl;
import com.company.oop.task.management.system.core.TaskManagementSystemEngineImpl;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemEngine;

public class Startup {
    public static void main(String[] args) {
        TaskManagementSystemEngine engine = new TaskManagementSystemEngineImpl();
        engine.start();
    }
}