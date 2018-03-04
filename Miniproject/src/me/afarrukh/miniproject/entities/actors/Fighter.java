package me.afarrukh.miniproject.entities.actors;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.gfx.Animation;
import me.afarrukh.miniproject.gfx.Visuals;

public class Fighter extends Player {

	public Fighter(Manager manager, float x, float y) {
		super(manager, x, y);
		
		this.setType("Fighter");
        this.setAttackAccuracy(Constants.FIGHTER_ATTACK_ACC + this.getCharLuck());
        this.setAttackPower(Constants.FIGHTER_ATTACK_POWER);
        this.setResistance(Constants.FIGHTER_RESISTANCE);
        this.setMaxHp(Constants.FIGHTER_MAXHP);
        this.setHp(this.getMaxHp());
        this.setHpRegen(Constants.FIGHTER_HPREGEN);
        this.setEnergy(Constants.FIGHTER_ENERGY);
        
        animDown = new Animation(250, Visuals.archer_down);
		animUp = new Animation(250, Visuals.archer_up);
		animLeft = new Animation(250, Visuals.archer_left);
		animRight = new Animation(250, Visuals.archer_right);
		animStill = new Animation(250, Visuals.archer_still);
		animAtk = new Animation(93, Visuals.mage_attack);
	}

}
