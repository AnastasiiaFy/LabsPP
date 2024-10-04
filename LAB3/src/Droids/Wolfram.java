package Droids;

import DROIDBattleGame.Constants;

public class Wolfram extends Droid {

    // Конструктор класу
    public Wolfram(String name, int health, int damage, int regeneration) {
        super(name, health, damage, regeneration);
    }

    @Override
    public void heal(int amount) {
        health += amount;
        if (health >= Constants.WOLFRAM_MAX_HEALTH) {
            health = Constants.WOLFRAM_MAX_HEALTH;
        }
    }

    @Override
    public void displayHealthBar() {
        int barLength = 30;
        int healthUnits = (health * barLength) / Constants.WOLFRAM_MAX_HEALTH;

        System.out.print("|||\n|||\t\t" + name + " health [");
        for (int i = 0; i < barLength; i++) {
            if (i < healthUnits) {
                System.out.print(Constants.ANSI_GREEN + "/" + Constants.ANSI_RESET); // Відображення заповнених одиниць здоров'я
            } else {
                System.out.print(" ");
            }
        }
        System.out.println("] " + health + "/" + Constants.WOLFRAM_MAX_HEALTH);
    }

    @Override
    public String toString() {
        return "Droid Type --- Wolfram ---\n" +
                super.toString() +
                "\n|||\t\t\tSpecial Ability - Vigour\n|||";
    }

    public void displayDroid() {
        System.out.print(
                        "|||\n|||" + Constants.ANSI_BLUE + "               ||==|| \n" + Constants.ANSI_RESET +
                             "|||" + Constants.ANSI_BLUE + "            ===||==||=== \n" + Constants.ANSI_RESET +
                             "|||" + Constants.ANSI_BLUE + "           ====||||||==== \n" + Constants.ANSI_RESET +
                             "|||" + Constants.ANSI_BLUE + "         ====<+>=||=<+>==== \n" + Constants.ANSI_RESET +
                             "|||" + Constants.ANSI_BLUE + "        =======&||||&======= \n" + Constants.ANSI_RESET +
                             "|||" + Constants.ANSI_BLUE + "           ===&||||||&=== \n" + Constants.ANSI_RESET +
                             "|||" + Constants.ANSI_BLUE + "           ===&||||||&=== \n" + Constants.ANSI_RESET +
                             "|||" + Constants.ANSI_BLUE + "             ===&||&=== \n" + Constants.ANSI_RESET );
    }
}
