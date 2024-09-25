package me.afarrukh.miniproject.entities.actors;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.gfx.Animation;
import me.afarrukh.miniproject.gfx.Visuals;

class Archer extends Player {

    public Archer(Manager manager, float x, float y) {
        super(manager, x, y);

        // Setting basic attributes

        this.setType("Archer");
        this.setAttackAccuracy(Constants.ARCHER_ATTACK_ACC + this.getCharLuck());
        this.setAttackPower(Constants.ARCHER_ATTACK_POWER);
        this.setResistance(Constants.ARCHER_RESISTANCE);
        this.setMaxHp(Constants.ARCHER_MAXHP);
        this.setHp(this.getMaxHp());
        this.setHpRegen(Constants.ARCHER_HPREGEN);
        this.setEnergy(Constants.ARCHER_ENERGY);

        animDown = new Animation(250, Visuals.archer_down);
        animUp = new Animation(250, Visuals.archer_up);
        animLeft = new Animation(250, Visuals.archer_left);
        animRight = new Animation(250, Visuals.archer_right);
        animStill = new Animation(250, Visuals.archer_still);
        animAtkRight = new Animation(93, Visuals.mage_attack_right);
        animAtkLeft = new Animation(93, Visuals.mage_attack_left);
    }
}
