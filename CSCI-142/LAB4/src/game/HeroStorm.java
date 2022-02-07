/**
 * NAME: DANIEL CHUNG
 * DATE: 2/13/2018
 */
package game;

import java.util.*;
import heroes.Hero;

public class HeroStorm extends Object {
    HeroParty dragon;
    HeroParty lion;
    int round;

    /**
     * Constructor for the class.
     * @param dragonseed
     * @param lionseed
     */

    public HeroStorm(int dragonseed, int lionseed){
        dragon = new HeroParty(Team.DRAGON, dragonseed);
        lion = new HeroParty(Team.LION, lionseed);
        this.round = 1;
    }

    public void play() {
        /**
         * This function plays the game
         * and implements the turns and
         * the switching in attacks.
         */
        HeroStorm init = new HeroStorm((int) (Math.random()), (int) (Math.random()));
        while (dragon.getHeroes().size() != 0 || lion.getHeroes().size() != 0) {
            if (round % 2 == 0) {
                Hero lion_hero = init.lion.removeHero();
                Hero dragon_hero = init.dragon.removeHero();
                System.out.println("Battle #" + this.round);
                System.out.println("==========");
                System.out.println("DRAGON:");
                init.dragon.toString();
                System.out.println("LION:");
                init.lion.toString();
                System.out.println(lion_hero.getName() + " VS " + dragon_hero.getName() + "!");
                lion_hero.attack(dragon_hero);
                this.round++;
                if (dragon_hero.hasFallen() == false) {
                    init.dragon.addHero(dragon_hero);
                }
                if (lion_hero.hasFallen() == false) {
                    init.lion.addHero(lion_hero);
                }
            } else {
                Hero lion_hero = init.lion.removeHero();
                Hero dragon_hero = init.dragon.removeHero();
                System.out.println("Battle #" + this.round);
                System.out.println("==========");
                System.out.println("DRAGON:");
                init.dragon.toString();
                System.out.println("LION:");
                init.lion.toString();
                System.out.println(dragon_hero.getName() + " VS " + lion_hero.getName());
                dragon_hero.attack(lion_hero);
                this.round++;
                if (dragon_hero.hasFallen() == false) {
                    init.dragon.addHero(dragon_hero);
                }
                if (lion_hero.hasFallen() == false) {
                    init.lion.addHero(lion_hero);
                }
            }

        }

    }

    public void main(String[] args){
        play();
    }
}
