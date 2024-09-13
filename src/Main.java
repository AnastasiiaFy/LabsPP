import java.util.Scanner;

class FibonacciNumber {
    private int number;
    private int value;
    private int additions;

    //Конструктор класу
    public FibonacciNumber(int number) {
        this.number = number;
        this.value = calculateFibonacci(number);
    }

    //Рекурсивне обчисчислення числа Фібоначчі
    private int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        else {
            additions++; //Підрахунок кількості операції додавання
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }

    //Методи доступу до полів класу
    public int getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }

    public int getAdditions() {
        return additions;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;

        //Введення користувачем даних через командний рядок або клавіатуру
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        else {
            System.out.print("Введіть номер числа Фібоначчі: ");
            n = scan.nextInt();
        }

        FibonacciNumber fibonacci = new FibonacciNumber(n);

        //Виведення вхідних даних і результатів обчислень
        System.out.println("\nНомер числа Фібоначчі - " + fibonacci.getNumber());
        System.out.println("Значення числа - " + fibonacci.getValue());
        System.out.println("Кількість операцій додавання для обчислення числа - " + fibonacci.getAdditions());
    }
}