package Droids;

public abstract class Droid {
    protected final String name;
    protected final int damage;
    protected final int min_damage;
    protected int health;
    protected final int regeneration;
    protected boolean isAlive;

    //Конструктор класу
    public Droid(String name, int health, int damage, int regeneration) {
        this.name = name;
        this.damage = damage;
        this.min_damage = this.damage / 2;
        this.health = health;
        this.regeneration = regeneration;
        this.isAlive = true;
    }

    //Методи доступу до полів класу
    public String getName() {
        return name;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    //Метод для зменшення здоров'я
    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            health = 0;
            isAlive = false;
        }
    }

    //Абстрактий метод для відновлення здоров'я
    public abstract void heal(int amount);

    //Абстрактий метод для показу рівня здоровʼя
    public abstract void displayHealthBar();

    //Абстрактий метод для візуалізації дроїда
    public abstract void displayDroid();

    //Стандартний удар
    public void regularAttack(Droid opponent) {
        System.out.println("|||\t\t" + this.name + " strikes " + opponent.getName() + " for " + this.damage + " damage!");
        opponent.takeDamage(this.damage);
    }

    //Слабший удар з відновлення частини здоровʼя
    public void specialAttack(Droid opponent) {

        System.out.println("|||\t\t" + this.name + " strikes " + opponent.getName() + " for " + this.min_damage + " damage and restores " + this.regeneration + " health!");
        opponent.takeDamage(this.min_damage);
        this.heal(this.regeneration);
    }

    //Виведення характеристик дроїда
    @Override
    public String toString() {
        return "|||\t\t\tDroid Name -  " +  name  +
                "\n|||\t\t\tHealth - " + health +
                "\n|||\t\t\tDamage - " + damage;
    }
}

