package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WriteAppliancesInFileTest {

    private ArrayList<ElectricalAppliance> appliances;
    private Scanner scanner;
    private final String filePath = "/Users/anastasiiafylypiv/Documents/ППelectrical_appliances.txt";
    private WriteAppliancesInFile writeCommand;

    @BeforeEach
    public void setUp() {
        appliances = new ArrayList<>();
        appliances.add(new ElectricalAppliance("Холодильник", "SAMSUNG",
                "RB33L3200SA", 700, "12-01-20", "12-01-23",
                22500, false));
        appliances.add(new ElectricalAppliance("Телевізор", "LG",
                "55UR78006LK", 500, "03-04-23", "03-04-26",
                20500, true));
        scanner = mock(Scanner.class);
    }

    @Test
    public void WritesAppliancesToFile() throws IOException {
        writeCommand = new WriteAppliancesInFile(appliances, scanner);

        writeCommand.execute();

        Path path = Paths.get(filePath);
        assertTrue(Files.exists(path));

        List<String> lines = Files.readAllLines(path);

        assertEquals(appliances.size(), lines.size());

        String expectedFirstLine = "Холодильник,SAMSUNG,RB33L3200SA,700,12-01-20,12-01-23,22500,false";
        assertEquals(expectedFirstLine, lines.get(0));

        String expectedSecondLine = "Телевізор,LG,55UR78006LK,500,03-04-23,03-04-26,20500,true";
        assertEquals(expectedSecondLine, lines.get(1));
    }

    @Test
    public void IOExceptionHandling(){
        writeCommand = new WriteAppliancesInFile(appliances, scanner);

        assertThrows(IOException.class, () -> {
            FileWriter writer = new FileWriter(
                    "/invalid_path/electrical_appliances.txt", false);
        });
    }
}
