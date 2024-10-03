package DROIDBattleGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Droid> droids = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("___________________________________________________________________________________________________________________");
        System.out.println("___|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|___");
        System.out.println("|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
        System.out.println("___|_____|_____|                                  GAME RULES                                  _____|_____|_____|___");
        System.out.println("|_____|_____|___     We are glad to welcome you to the game, where everyone can try on the    __|_____|_____|_____|");
        System.out.println("___|_____|_____|    role of a droid, the films about which we loved to watch as a child.      _____|_____|_____|___");
        System.out.println("|_____|_____|___     There are two types of droids:                                           __|_____|_____|_____|");
        System.out.println("___|_____|_____|    Brave Wolfram captures the world with his power                           _____|_____|_____|___");
        System.out.println("|_____|_____|___      Health - 1500                                                           __|_____|_____|_____|");
        System.out.println("___|_____|_____|      Damage - 500                                                            _____|_____|_____|___");
        System.out.println("|_____|_____|___      Regeneration - 30                                                       __|_____|_____|_____|");
        System.out.println("___|_____|_____|    Persistent Livermorium will surprise you with endurance                   _____|_____|_____|___");
        System.out.println("|_____|_____|___      Health - 2500                                                           __|_____|_____|_____|");
        System.out.println("___|_____|_____|      Damage - 300                                                            _____|_____|_____|___");
        System.out.println("|_____|_____|___      Regeneration - 50                                                       __|_____|_____|_____|");
        System.out.println("___|_____|_____|    Players create droids to choose from, and then two of them participate    _____|_____|_____|___");
        System.out.println("|_____|_____|___    in a duel. Each player gets hit in turn.                                  __|_____|_____|_____|");
        System.out.println("___|_____|_____|      Each player gets hit in turn. Each droid has two types of impacts:      _____|_____|_____|___");
        System.out.println("|_____|_____|___   1. Regular attack                                                          __|_____|_____|_____|");
        System.out.println("___|_____|_____|   2. Twice as weak attack with the regeneration of part of the droid health  _____|_____|_____|___");
        System.out.println("|_____|_____|___                Good game and let the mind and luck be with you!              __|_____|_____|_____|");
        System.out.println("___|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|___");
        System.out.println("|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
        System.out.println("___|_____|_____|                              ----- MENU -----                                _____|_____|_____|___");
        System.out.println("|_____|_____|___                      1. Create droid.                                        __|_____|_____|_____|");
        System.out.println("___|_____|_____|                      2. Show the list of droids.                             _____|_____|_____|___");
        System.out.println("|_____|_____|___                      3. Start a 1-on-1 duel.                                 __|_____|_____|_____|");
        System.out.println("___|_____|_____|                      4. Print duels results.                                 _____|_____|_____|___");
        System.out.println("|_____|_____|___                      5. Exit the game.                                       __|_____|_____|_____|");
        System.out.println("___|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|___");
        System.out.println("|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
        System.out.println("___|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|___");
        System.out.println("|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
        System.out.println("___|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
        System.out.println("|_____|_____|_____|_____|_____|_____|");
        System.out.println("___|_____|_____|");
        while (true) {
            System.out.print("|||\n|||\t\t" + Constants.ANSI_PURPLE + "Enter an option - " + Constants.ANSI_RESET);
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startDuel();
                    break;
                case 4:
                    displayDuelResultsFromFile();
                    break;
                case 5:
                    scanner.close();
                    System.out.println("___|_____|_____|" + Constants.ANSI_RED + "                                  GAME OVER!                                  " + Constants.ANSI_RESET + "_____|_____|_____|___");
                    System.out.println("|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
                    System.out.println("___|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|___");
                    System.exit(0);
                default:
                    System.out.println("|||\t\tInvalid choice. Please try again.");
                    break;
            }

        }
    }

    //Метод для створення обєкта-дроїда вказаного типу
    private static void createDroid() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("|||\n|||\t\tEnter the droid type (Wolfram/Livermorium) - ");
        String droidType = scanner.nextLine();

        System.out.print("|||\t\tEnter the name of the droid - ");
        String name = scanner.nextLine();

        Droid droid = null;

        try {
            switch (droidType.toLowerCase()) {
                case "wolfram":
                    droid = new Wolfram(name, Constants.WOLFRAM_MAX_HEALTH, Constants.WOLFRAM_MAX_DAMAGE, Constants.WOLFRAM_REGENERATION );
                    break;
                case "livermorium":
                    droid = new Livermorium(name, Constants.LIVERMORIUM_MAX_HEALTH, Constants.LIVERMORIUM_MAX_DAMAGE, Constants.LIVERMORIUM_REGENERATION );
                    break;
                default:
                    throw new IllegalArgumentException("|||\t\tUnknown droid type: " + droidType);
            }

            droids.add(droid);
            System.out.println("|||\t\tDroid " + droid.getName() + " created!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    //Метод для виведення списку дроїдів на екран
    private static void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("|||\n|||\t\tThe list of droids is empty.");
        } else {
            System.out.println("|||\n|||\t\t\t----- List of droids -----");
            for (int i = 0; i < droids.size(); i++) {
                Droid droid = droids.get(i);
                System.out.println("|||\t\t" + (i + 1) + ". " + droid);
            }
        }
    }

    //Метод для вибору дроїдів для початку дуелі
    private static void startDuel() {
        if (droids.size() < 2) {
            System.out.println("|||\t\tYou need at least two droids to start a duel.");
            return;
        }

        System.out.println("|||\t\tChoose droids for the duel: ");

        showDroids();

        Scanner scanner = new Scanner(System.in);
        System.out.print("|||\t\tSelect the first droid (enter index) - ");
        int index1 = scanner.nextInt();
        scanner.nextLine();

        System.out.print("|||\t\tSelect the second droid (enter index) - ");
        int index2 = scanner.nextInt();
        scanner.nextLine();

        index1--;
        index2--;

        if (index1 < 0 || index1 >= droids.size() || index2 < 0 || index2 >= droids.size()) {
            System.out.println("|||\t\tInvalid droid index.");
            return;
        }

        Droid droid1 = droids.get(index1);
        Droid droid2 = droids.get(index2);
        System.out.println("|||\n|||==============================================================================================================|||");
        System.out.println("|||" + Constants.ANSI_YELLOW + "                                            THE DUEL BEGINS!                                                  " + Constants.ANSI_RESET + "|||");
        System.out.println("|||==============================================================================================================|||");
        duel(droid1, droid2);
    }

    //Метод для реалізації дуелі
    private static void duel(Droid droid1, Droid droid2) {
        Scanner scanner = new Scanner(System.in);
        try {
            //Відкриття файлу для запису результатів дуелі
            FileWriter writer = new FileWriter("duel_results.txt", true);
            writer.write("\t" + droid1.getName() + " vs " + droid2.getName() + "\n");

            droid1.displayDroid();
            droid1.displayHealthBar();
            droid2.displayDroid();
            droid2.displayHealthBar();

            while (droid1.getIsAlive() && droid2.getIsAlive()) {
                int strike = 0;
                do {
                    System.out.print("|||\n|||\t\tPLAYER 1, make a strike (1 or 2) - ");
                    strike = scanner.nextInt();
                    scanner.nextLine();

                    if (strike == 1) {
                        droid1.regularAttack(droid2);
                        break;
                    } else if (strike == 2) {
                        droid1.specialAttack(droid2);
                        break;
                    } else {
                        System.out.println("|||\t\tInvalid strike. Please try again.");
                    }
                } while (true);

                if (!droid2.getIsAlive()) {
                    break;
                }

                do {
                    System.out.print("|||\t\tPLAYER 2, make a strike (1 or 2) - ");
                    strike = scanner.nextInt();
                    scanner.nextLine();

                    if (strike == 1) {
                        droid2.regularAttack(droid1);
                        break;
                    } else if (strike == 2) {
                        droid2.specialAttack(droid1);
                        break;
                    } else {
                        System.out.println("|||\t\tInvalid strike. Please try again.");
                    }
                } while (true);
                droid1.displayHealthBar();
                droid2.displayHealthBar();
            }

            if (droid1.getIsAlive()) {
                System.out.println("|||\n|||\t\tDroid " + droid1.getName() + " wins!");
                writer.write("Droid " + droid1.getName() + " wins!\n");
                droids.remove(droid2);
            } else {
                System.out.println("|||\n|||\t\tDroid " + droid2.getName() + " wins!");
                writer.write("Droid " + droid2.getName() + " wins!\n");
                droids.remove(droid1);
            }

            writer.write("\n");
            writer.close();
            System.out.println("|||\n|||\t\tDuel result has been written to the duel_results.txt file.");
        } catch (IOException e){
            System.out.println("|||\t\tError writing duel results to the file.");
        }
        return;
    }

    //Метод для виведення результатіі дуелей з файлу
    private static void displayDuelResultsFromFile() {
        try {
            File file = new File("duel_results.txt");

            if (!file.exists()) {
                System.out.println("|||\t\tThe duel result file was not found.");
                return;
            }

            Scanner fileScanner = new Scanner(file);
            System.out.println("|||\n|||\t\t----- Duel Results -----");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("|||\t\t" + line);
            }

            fileScanner.close();
        } catch (IOException e) {
            System.out.println("|||\t\tError reading duel results from file.");
        }
    }

}
