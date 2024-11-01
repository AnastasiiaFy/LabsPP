package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DownloadApplianceFromFileTest {
    private ArrayList<ElectricalAppliance> appliances;
    private Scanner scanner;
    private DownloadApplianceFromFile downloadFromFile;
    private final String filePath = "/Users/anastasiiafylypiv/Documents/ППelectrical_appliances.txt";

    @BeforeEach
    public void setUp() {
        appliances = new ArrayList<>();
        scanner = mock(Scanner.class);
    }

    @Test
    public void CorrectDownloadAppliancesFromFile() throws IOException {
        downloadFromFile = new DownloadApplianceFromFile(appliances, scanner);

        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("Праска,PHILIPS,DST5040,2600,23-11-19,23-11-21,2300,true\n");
            writer.write("Холодильник,SAMSUNG,RB33L3200SA,700,12-01-20,12-01-23,22500,false\n");
        }

        downloadFromFile.execute();

        Path path = Paths.get(filePath);
        assertTrue(Files.exists(path));

        assertEquals(2, appliances.size());

        ElectricalAppliance firstAppliance = appliances.get(0);
        assertEquals("Праска", firstAppliance.getName());
        assertEquals("PHILIPS", firstAppliance.getBrand());
        assertEquals("DST5040", firstAppliance.getModel());
        assertEquals(2600, firstAppliance.getPower());
        assertEquals("23-11-19", firstAppliance.getPurchaseDate());
        assertEquals("23-11-21", firstAppliance.getWarantyEndDate());
        assertEquals(2300, firstAppliance.getPrice());
        assertTrue(firstAppliance.isPluggedIn());

        ElectricalAppliance secondAppliance = appliances.get(1);
        assertEquals("Холодильник", secondAppliance.getName());
        assertEquals("SAMSUNG", secondAppliance.getBrand());
        assertEquals("RB33L3200SA", secondAppliance.getModel());
        assertEquals(700, secondAppliance.getPower());
        assertEquals("12-01-20", secondAppliance.getPurchaseDate());
        assertEquals("12-01-23", secondAppliance.getWarantyEndDate());
        assertEquals(22500, secondAppliance.getPrice());
        assertFalse(secondAppliance.isPluggedIn());
    }

    @Test
    public void NoDownloadFromFileWithIncorrectFormat() throws IOException {
        downloadFromFile = new DownloadApplianceFromFile(appliances, scanner);

        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("Некоректний рядок\n");
        }

        downloadFromFile.execute();

        assertEquals(0, appliances.size());
    }

    @Test
    public void NoDownloadFromFileWithInvalidNumberFormat() throws IOException {
        downloadFromFile = new DownloadApplianceFromFile(appliances, scanner);
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("Праска,PHILIPS,DST5040,досить,23-11-19,23-11-21,2300,true\n");
        }

        downloadFromFile.execute();

        assertEquals(0, appliances.size());
    }

    @Test
    public void IOExceptionHandling(){
        downloadFromFile = new DownloadApplianceFromFile(appliances, scanner);

        assertThrows(FileNotFoundException.class, () -> {
            FileWriter writer = new FileWriter(
                    "/invalid_path/electrical_appliances.txt", false);
        });
    }
}