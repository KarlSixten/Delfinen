import domain.Controller;
import ui.UserInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller(args[0]);
    UserInterface ui = new UserInterface(controller);

        ui.startProgram();
                }
                }
