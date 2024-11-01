package Appliance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricalApplianceTest {
    private ElectricalAppliance appliance;

    @BeforeEach
    void setUp() {
        appliance = new ElectricalAppliance("Fridge", "Samsung", "RB33J3200SA", 700,
                "12-01-2020", "12-01-2023", 22500, false);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Fridge", appliance.getName());
        assertEquals("Samsung", appliance.getBrand());
        assertEquals("RB33J3200SA", appliance.getModel());
        assertEquals(700, appliance.getPower());
        assertEquals("12-01-2020", appliance.getPurchaseDate());
        assertEquals("12-01-2023", appliance.getWarantyEndDate());
        assertEquals(22500, appliance.getPrice());
        assertFalse(appliance.isPluggedIn());
    }

    @Test
    void testTurnOn() {
        appliance.turnOn();
        assertTrue(appliance.isPluggedIn());
    }

    @Test
    void testTurnOff() {
        appliance.turnOn();
        appliance.turnOff();
        assertFalse(appliance.isPluggedIn());
    }

    @Test
    void testToString() {
        String expectedString = """
                Name:  Fridge
                   Brand: Samsung
                   Model: RB33J3200SA
                   Power: 700
                   Date of purchase: 12-01-2020
                   End date of waranty: 12-01-2023
                   Price: 22500
                   IsPluggedIn: false
                """;

        assertEquals(expectedString, appliance.toString());
    }
}