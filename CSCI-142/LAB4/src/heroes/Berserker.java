package heroes;

import game.Team;

public class Berserker extends Hero {
    protected static final int BERSERKER_HIT_POINTS = 30;
    protected static final int DAMAGE_AMOUNT = 20;


    protected Berserker(Team team){
        super(Heroes.getName(team, Heroes.Role.BERSERKER), BERSERKER_HIT_POINTS);
    }

    public void attack(Hero enemy){

        enemy.takeDamage(DAMAGE_AMOUNT);
    }

    public Heroes.Role getRole(){
        return Heroes.Role.BERSERKER;
    }
}
