package Appliance;

public class ElectricalAppliance {
    protected final String name;
    protected final String brand;
    protected final String model;
    protected final int power;
    protected final String purchaseDate;
    protected final String warantyEndDate;
    protected final int price;
    protected boolean isPluggedIn;

    public ElectricalAppliance(String name, String brand, String model, int power,
                               String purchaseDate, String warantyEndDate, int price, boolean isPluggedIn) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.purchaseDate = purchaseDate;
        this.warantyEndDate = warantyEndDate;
        this.price = price;
        this.isPluggedIn = isPluggedIn;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getWarantyEndDate() {
        return warantyEndDate;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPluggedIn() {
        return isPluggedIn;
    }

    public void turnOn() {
        isPluggedIn = true;
    }

    public void turnOff() {
        isPluggedIn = false;
    }

    @Override
    public String toString() {
        return "Name:  " + name +
                "\n   Brand: " + brand +
                "\n   Model: " + model +
                "\n   Power: " + power +
                "\n   Date of purchase: " + purchaseDate +
                "\n   End date of waranty: " + warantyEndDate +
                "\n   Price: " + price +
                "\n   IsPluggedIn: " + isPluggedIn + "\n";
    }
}
