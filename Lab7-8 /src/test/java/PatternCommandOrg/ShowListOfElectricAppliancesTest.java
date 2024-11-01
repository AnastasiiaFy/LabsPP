package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowListOfElectricAppliancesTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private ArrayList<ElectricalAppliance> appliances;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        scanner = new Scanner(System.in);

        appliances = new ArrayList<>();
        appliances.add(new ElectricalAppliance("Праска", "PHILIPS",
                "DST5040", 2600, "23-11-19", "23-11-21",
                2300, false));
        appliances.add(new ElectricalAppliance("Холодильник", "SAMSUNG",
                "RB33L3200SA", 700, "12-01-20", "12-01-23",
                22500, false));
    }

    @Test
    public void testExecute() {
        ShowListOfElectricAppliances command = new ShowListOfElectricAppliances(appliances, scanner);
        command.execute();

        String expectedOutput = "---- List of electrical appliances ----" +
                "\n\n1: Name:  Праска" +
                "\n   Brand: PHILIPS" +
                "\n   Model: DST5040" +
                "\n   Power: 2600" +
                "\n   Date of purchase: 23-11-19" +
                "\n   End date of waranty: 23-11-21" +
                "\n   Price: 2300" +
                "\n   IsPluggedIn: false" +
                "\n\n2: Name:  Холодильник" +
                "\n   Brand: SAMSUNG" +
                "\n   Model: RB33L3200SA" +
                "\n   Power: 700" +
                "\n   Date of purchase: 12-01-20" +
                "\n   End date of waranty: 12-01-23" +
                "\n   Price: 22500" +
                "\n   IsPluggedIn: false";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
