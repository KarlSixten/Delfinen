import domain.Controller;
import ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        UserInterface ui = new UserInterface(controller);

        ui.startProgram();
    }
}
