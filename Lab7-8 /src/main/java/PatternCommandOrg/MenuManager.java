package PatternCommandOrg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class MenuManager {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");

    public void runCommand(Command command, Scanner scanner) {
        fileLogger.info("Menu manager was called.");
        command.execute();
    }
}
