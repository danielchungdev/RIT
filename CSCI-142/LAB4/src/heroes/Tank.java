package heroes;

import game.Team;

public class Tank extends Hero {
    protected static  int TANK_HIT_POINTS = 40;
    protected static final double SHIELD_DMG_MULTIPLIER = 0.9;
    protected static final int DAMAGE_AMOUNT = 15;

    protected Tank(Team team){
        super(Heroes.getName(team, Heroes.Role.TANK), TANK_HIT_POINTS);
    }

    public Heroes.Role getRole(){
        return Heroes.Role.TANK;
    }

    @Override
    public void takeDamage(int amount){
        int new_damage_taken = (int)(amount * SHIELD_DMG_MULTIPLIER);
        super.takeDamage(new_damage_taken);
        if (TANK_HIT_POINTS <= 0){
            TANK_HIT_POINTS = 0;
        }
    }

    public void attack(Hero enemy){
        enemy.takeDamage(DAMAGE_AMOUNT);
    }
}
