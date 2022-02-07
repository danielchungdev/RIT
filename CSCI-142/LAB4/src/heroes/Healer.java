package heroes;

import game.HeroParty;
import game.Team;

import java.util.ArrayList;


public class Healer extends Hero{
    private static final int MAX_HEALER_HIT_POINTS = 35;
    private static int HEALER_HIT_POINTS = 35;
    protected static final int DAMAGE_AMOUNT = 10;
    protected static final int HEAL_AMOUNT = 10;
    private Party party;


    protected Healer(Team team, Party party){
        super(Heroes.getName(team, Heroes.Role.HEALER), HEALER_HIT_POINTS);
        this.party = party;
    }

    public Heroes.Role getRole(){
        return Heroes.Role.HEALER;
    }

    public void attack(Hero enemy){
        /**
         * HEALER'S IMPLEMENTATION OF ATTACK,
         * SHE HEALS HERSELF FIRST, THEN GOES
         */
        if (HEALER_HIT_POINTS > 0){
            heal(HEAL_AMOUNT);
            java.util.List<Hero> heroes_party = party.getHeroes();
            for (int i = 0; i < heroes_party.size(); i++){
                heroes_party.get(i).heal(HEAL_AMOUNT);
            }
            enemy.takeDamage(DAMAGE_AMOUNT);
        }
    }


}
