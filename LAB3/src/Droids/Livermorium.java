package Droids;

import DROIDBattleGame.Constants;

public class Livermorium extends Droid {

    // Конструктор класу
    public Livermorium(String name, int health, int damage, int regeneration) {
        super(name, health, damage, regeneration);
    }

    @Override
    public void heal(int amount) {
        health += amount;
        if (health >= Constants.LIVERMORIUM_MAX_HEALTH) {
            health = Constants.LIVERMORIUM_MAX_HEALTH;
        }
    }

    @Override
    public void displayHealthBar() {
        int barLength = 30;
        int healthUnits = (health * barLength) / Constants.LIVERMORIUM_MAX_HEALTH;

        System.out.print("|||\n|||\t\t" + name + " health [");
        for (int i = 0; i < barLength; i++) {
            if (i < healthUnits) {
                System.out.print(Constants.ANSI_GREEN + "/" + Constants.ANSI_RESET);
            } else {
                System.out.print(" ");
            }
        }
        System.out.println("] " + health + "/" + Constants.LIVERMORIUM_MAX_HEALTH);
    }

    @Override
    public String toString() {
        return "Droid Type --- Livermorium ---\n" +
                super.toString() +
                "\n|||\t\t\tSpecial Ability - Regeneration\n|||";
    }

    @Override
    public void displayDroid() {
        System.out.print(
             "|||\n|||" + Constants.ANSI_CYAN + "             ||[=-=]|| \n" + Constants.ANSI_RESET +
                  "|||" + Constants.ANSI_CYAN + "            ===|[=]|=== \n" + Constants.ANSI_RESET +
                  "|||" + Constants.ANSI_CYAN + "          =====|[-]|===== \n" + Constants.ANSI_RESET +
                  "|||" + Constants.ANSI_CYAN + "         ===<$>=|||=<$>=== \n" + Constants.ANSI_RESET +
                  "|||" + Constants.ANSI_CYAN + "        =====&||===||&===== \n" + Constants.ANSI_RESET +
                  "|||" + Constants.ANSI_CYAN + "        ====&||=====||&==== \n" + Constants.ANSI_RESET +
                  "|||" + Constants.ANSI_CYAN + "          ===&||===||&=== \n" + Constants.ANSI_RESET +
                  "|||" + Constants.ANSI_CYAN + "           ====&|||&==== \n" + Constants.ANSI_RESET );
    }
}
