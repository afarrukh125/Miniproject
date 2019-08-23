package me.afarrukh.miniproject.entities.actors;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.gfx.Animation;
import me.afarrukh.miniproject.gfx.Visuals;

import java.io.IOException;

public class Mage extends Player {
	
	public Mage(Manager manager, float x, float y) throws IOException {
		super(manager, x, y);
		
		//Setting basic attributes
		this.setType("Mage");
        this.setAttackAccuracy(Constants.MAGE_ATTACK_ACC + this.getCharLuck());
        this.setAttackPower(Constants.MAGE_ATTACK_POWER);
        this.setResistance(Constants.MAGE_RESISTANCE);
        this.setMaxHp(Constants.MAGE_MAXHP);
        this.setHp(this.getMaxHp());
        this.setHpRegen(Constants.MAGE_HPREGEN);
        this.setEnergy(Constants.MAGE_ENERGY);
        this.setSpeed(Constants.MAGE_SPEED);
		
		animDown = new Animation(250, Visuals.mage_down);
		animUp = new Animation(250, Visuals.mage_up);
		animLeft = new Animation(250, Visuals.mage_left);
		animRight = new Animation(250, Visuals.mage_right);
		animStill = new Animation(250, Visuals.mage_still);
		animAtkRight = new Animation(93, Visuals.mage_attack_right);
		animAtkLeft = new Animation(93, Visuals.mage_attack_left);
	}

}
