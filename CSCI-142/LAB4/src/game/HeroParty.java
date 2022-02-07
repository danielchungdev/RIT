package game;

import heroes.Heroes;
import heroes.Party;
import heroes.Hero;
import heroes.Tank;

import java.util.*;

import java.util.*;

public class HeroParty implements Party {
    public Team team;
    ArrayList<Hero> heroes_list = new ArrayList<>();

    /**
     * Constructs the heroparty.
     * @param team
     * @param seed
     */

    public HeroParty(Team team, int seed) {
        this.team = team;
        Collections.shuffle(heroes_list, new Random(seed));
    }


    /**
     * Adds a hero to the party.
     * @param hero the new hero
     */
    public void addHero(Hero hero) {
        heroes_list.add(hero);
    }

    /**
     * removes a hero from the party.
     * @return
     */
    public Hero removeHero() {
        Hero temp_hero = heroes_list.get(0);
        heroes_list.remove(0);
        return temp_hero;
    }

    public int numHeroes() {
        return heroes_list.size();
    }

    public Team getTeam() {
        return this.team;
    }

    public java.util.List<Hero> getHeroes(){
        return heroes_list;
    }

    public String toString() {
        if (team == Team.DRAGON){
            return Heroes.DRAGON_BERSERKER + Heroes.DRAGON_HEALER + Heroes.DRAGON_TANK;
        }
        else {
            return Heroes.LION_BERSERKER + Heroes.LION_HEALER + Heroes.LION_TANK;
        }
    }

}
