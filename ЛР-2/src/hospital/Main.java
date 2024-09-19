package hospital;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість пацієнтів: ");
        int patientNum = scanner.nextInt();

        //Створення масиву для зберігання обʼєктів класу
        Patient[] patients = new Patient[patientNum];

        for (int i = 0; i < patientNum; i++) {
            System.out.println("\n\t----- Введіть дані про пацієнта " + (i + 1) + " -----");
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();       //Очищення буфера після nextInt()
            System.out.print("Прізвище: ");
            String lName = scanner.nextLine();
            System.out.print("Імʼя: ");
            String fName = scanner.nextLine();
            System.out.print("По батькові: ");
            String mName = scanner.nextLine();
            System.out.print("Адреса: ");
            String address = scanner.nextLine();
            System.out.print("Номер телефону: ");
            String phone = scanner.nextLine();
            System.out.print("Номер медичної карти (4 цифри): ");
            String medCardNum = scanner.nextLine();
            System.out.print("Діагноз: ");
            String diagnosis = scanner.nextLine();

            //Додавання обʼєкту класу до масиву
            patients[i] = new Patient(id, lName, fName, mName, address, phone, medCardNum, diagnosis);
        }

        System.out.println("\n\t----- Варіанти пошуку -----");
        System.out.println("а) Вивести список пацієнтів, які мають вказаний діагноз");
        System.out.println("b) Вивести список пацієнтів, номер медичної карти у яких знаходиться в заданому інтервалі");
        System.out.println("c) Вивести кількість та список пацієнтів, номер телефону яких починається з вказаної цифри");
        System.out.println("d) Вихід");
        char chose;

        do {
            do {
                System.out.print("\n\nОберіть варіант (a, b, c або d) - ");
                chose = scanner.next().charAt(0);
                scanner.nextLine();
            } while (chose != 'a' && chose != 'b' && chose != 'c' && chose != 'd');

            switch (chose) {
                case 'a':
                    System.out.print("\nВведіть діагноз для пошуку - ");
                    String diagnForSearch = scanner.nextLine();

                    System.out.println("\n\t-----Результати пошуку-----");
                    for (Patient patient : patients) {
                        if (patient.getDiagnosis().equals(diagnForSearch)) {
                            patient.displayInfo();
                        }
                    }
                    break;
                case 'b':
                    System.out.print("\nВведіть мінімальний номер медичної карти (4 цифри) - ");
                    String minCardNum = scanner.next();
                    System.out.print("Введіть максимальний номер медичної карти (4 цифри) - ");
                    String maxCardNum = scanner.next();

                    System.out.println("\n\t-----Результати пошуку-----");
                    for (Patient patient : patients) {
                        String medCardNumber = patient.getMedCardNumber();

                        if (medCardNumber.compareTo(minCardNum) >= 0 && medCardNumber.compareTo(maxCardNum) <= 0) {
                            patient.displayInfo();
                        }
                    }
                    break;
                case 'c':
                    System.out.print("\nВведіть одну цифру для пошуку - ");
                    String digit = scanner.next();
                    int count = 0;

                    System.out.println("\n\t-----Результати пошуку-----");
                    for (Patient patient : patients) {
                        if (patient.getPhoneNum().charAt(0) == digit.charAt(0)) {
                            patient.displayInfo();
                            count++;
                        }
                    }
                    System.out.println("\t\tКількість знайдених пацієнтів - " + count);
                    break;
                case 'd':
                    System.exit(0);
            }
        } while (true);
    }
}