package PatternCommandOrg;

import Appliance.ElectricalAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CalculatePriceOfWorkTest {

    private ArrayList<ElectricalAppliance> appliances;
    private CalculatePriceOfWork calculatePriceOfWork;
    private Scanner scanner;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        appliances = new ArrayList<>();

        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        scanner = new Scanner(System.in);

        appliances = new ArrayList<>();
        appliances.add(new ElectricalAppliance("Праска", "PHILIPS",
                "DST5040", 2600, "23-11-19", "23-11-21",
                2300, true));
        appliances.add(new ElectricalAppliance("Холодильник", "SAMSUNG",
                "RB33L3200SA", 700, "12-01-20", "12-01-23",
                22500, false));
        appliances.add(new ElectricalAppliance("Телевізор", "LG",
                "55UR78006LK", 500, "03-04-23", "03-04-26",
                20500, true));

        scanner = mock(Scanner.class);
        calculatePriceOfWork = new CalculatePriceOfWork(appliances, scanner);
    }

    @Test
    public void CalculatesCorrectWorkPriceTurnedOnAppliances() {
        when(scanner.nextInt()).thenReturn(1, 5);
        when(scanner.nextLine()).thenReturn("","4.5", "");

        calculatePriceOfWork.execute();

        // Очікувана ціна: (2600 + 500) * 5 годин /1000 * 4.5 = 69.75
        assertTrue(outContent.toString().contains("Price of work of turned on electrical appliances - 69.75"));
        outContent.reset();
    }

    @Test
    public void CalculatesCorrectWorkPriceForSelectedAppliances() {
        when(scanner.nextInt()).thenReturn(2, 5);//Варіант 2, 5 годин
        when(scanner.nextLine()).thenReturn("","1 2", "4.5", ""); //Прилади 1 і 2, ціна 4.5 за кВт

        calculatePriceOfWork.execute();

        // Очікувана ціна: (2600 + 700) * 5 годин /1000 * 4.5 = 74.25
        assertTrue(outContent.toString().contains("Price of work of selected electrical appliances - 74.25"));
        outContent.reset();
    }

    @Test
    public void OverlargeInvalidVariant() {
        when(scanner.nextInt()).thenReturn(6);

        calculatePriceOfWork.execute();

        assertTrue(outContent.toString().contains("Invalid input."));

        outContent.reset();
    }

    @Test
    public void NegativeInvalidVariant() {
        when(scanner.nextInt()).thenReturn(-1);

        calculatePriceOfWork.execute();

        assertTrue(outContent.toString().contains("Invalid input."));

        outContent.reset();
    }

    @Test
    public void OverlargeInvalidIndex() {
        when(scanner.nextInt()).thenReturn(2, 5);
        when(scanner.nextLine()).thenReturn("", "1 7", "4.5", "");

        calculatePriceOfWork.execute();

        assertTrue(outContent.toString().contains("Index 7 is out of range."));

        outContent.reset();
    }

    @Test
    public void NonNumericInvalidIndex() {
        when(scanner.nextInt()).thenReturn(2, 5);
        when(scanner.nextLine()).thenReturn("", "1 a", "4.5", "");

        calculatePriceOfWork.execute();

        assertTrue(outContent.toString().contains("Invalid input: 'a' is not a valid number."));

        outContent.reset();
    }

    @Test
    public void NegativeInvalidIndex() {
        when(scanner.nextInt()).thenReturn(2, 5);
        when(scanner.nextLine()).thenReturn("", "1 -1", "4.5", "");

        calculatePriceOfWork.execute();

        assertTrue(outContent.toString().contains("Index -1 is out of range."));

        outContent.reset();
    }

    @Test
    public void ZeroInvalidIndex() {
        when(scanner.nextInt()).thenReturn(2, 5);
        when(scanner.nextLine()).thenReturn("", "1 0", "4.5", "");

        calculatePriceOfWork.execute();

        assertTrue(outContent.toString().contains("Index 0 is out of range."));

        outContent.reset();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
