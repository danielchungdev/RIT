package heroes;

import game.Team;

import java.util.TreeMap;

public abstract class Hero extends Object {
    private int hitPoints;
    private String name;
    private final int maxHP;


    protected Hero(String name, int hitPoints) {
        this.name = name;
        this.maxHP = hitPoints;
        this.hitPoints = hitPoints;
    }

    public static Hero create(Heroes.Role role, Team team, Party party){
        if (role == Heroes.Role.BERSERKER){
            return new Berserker(team);
        }

        if (role == Heroes.Role.HEALER)
            return new Healer(team, party);

        if (role == Heroes.Role.TANK){
            return new Tank(team);
        }
        return null;
    }

    public abstract Heroes.Role getRole();

    public abstract void  attack(Hero enemy);

    public String getName(){
        return this.name;
    }

    public void heal(int amount){
        System.out.println(this.name + " heals " + amount + " points");
        this.hitPoints += amount;
        if (this.hitPoints >= this.maxHP){
            this.hitPoints = this.maxHP;
        }
    }

    public void takeDamage(int amount){
        System.out.println(this.name + " takes " + amount + " damage");
        this.hitPoints -= amount;
        if (this.hitPoints <= 0){
            this.hitPoints = 0;
        }
    }

    public boolean hasFallen(){
        System.out.println(this.name + " has fallen");
        if (this.hitPoints == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString(){
        return this.name + ", " + getRole() + ", " + this.hitPoints + "/" + this.maxHP;
    }
}
