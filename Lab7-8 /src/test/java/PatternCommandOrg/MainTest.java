package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainTest {
    private Home home;
    private ArrayList<ElectricalAppliance> appliances;
    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        originalIn = System.in;
        originalOut = System.out;

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        appliances = new ArrayList<>();
        appliances.add(new ElectricalAppliance("Праска", "PHILIPS",
                "DST5040", 2600, "23-11-19", "23-11-21",
                2300, true));
        appliances.add(new ElectricalAppliance("Холодильник", "SAMSUNG",
                "RB33L3200SA", 700, "12-01-20", "12-01-23",
                22500, false));

        home = mock(Home.class);
    }

    @Test
    public void MainAddAppliance() {
        String simulatedInput = "3\n" +
                "" +
                "Праска\n" +
                "PHILIPS\n" +
                "DST5040\n" +
                "2600\n" +
                "" +
                "23-11-19\n" +
                "23-11-21\n" +
                "2300\n" +
                "" +
                "11\n" +
                "";

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Main.main(new String[0]);
        assertTrue(outContent.toString().contains("Appliance added."));

    }

    @Test
    public void MainOverLargeInvalidChoice() {
        String simulatedInput = "99\n" +
                "" +
                "11\n" +
                "";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Main.main(new String[0]);
        assertTrue(outContent.toString().contains("Invalid choice. Please try again."));
    }

    @Test
    public void ZeroInvalidChoice() {
        String simulatedInput = "0\n" +
                "" +
                "11\n" +
                "";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Main.main(new String[0]);
        assertTrue(outContent.toString().contains("Invalid choice. Please try again."));
    }

    @Test
    public void NegativeInvalidChoice() {
        String simulatedInput = "-3\n" +
                "" +
                "11\n" +
                "";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Main.main(new String[0]);
        assertTrue(outContent.toString().contains("Invalid choice. Please try again."));
    }

    @Test
    public void testMainExit() {
        String simulatedInput = "11\n" + "";

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.main(new String[0]);
        assertTrue(outContent.toString().contains("Exit from the program."));
    }


    @AfterEach
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
