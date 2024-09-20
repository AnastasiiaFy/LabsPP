package hospital;

public class Patient  {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String phoneNum;
    private String medCardNumber;
    private String diagnosis;


    //Констуктор обʼєктів класу
    public Patient (int id, String lName, String fName,String mName,
                    String address, String phone, String medCardNum, String diagnosis){

        this.id = id;
        this.lastName = lName;
        this.firstName = fName;
        this.middleName = mName;
        this.address = address;
        this.phoneNum = phone;
        this.medCardNumber = medCardNum;
        this.diagnosis = diagnosis;
    }

    //Методи доступу до полів класу - Геттери
    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getMedCardNumber() {
        return medCardNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    //Виведення інформації про пацієнта
    public String toString() {
        return "\nID: " + id +
                "\nПрізвище: " + lastName +
                "\nІмʼя: " + firstName +
                "\nПо батькові: " + middleName +
                "\nАдреса: " + address +
                "\nНомер телефону: " + phoneNum +
                "\nНомер медичної карти: " + medCardNumber +
                "\nДіагноз: " + diagnosis;
    }
}
