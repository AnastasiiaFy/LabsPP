package PatternCommandOrg;

import java.util.Scanner;

public class MenuManager {
    public void runCommand(Command command, Scanner scanner) {
        command.execute();
    }
}
