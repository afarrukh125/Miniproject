package me.afarrukh.miniproject.constants;

public class Constants {

	public static final int FPS = 30;
	
	public static final int playerAtkCd = 200; //How long the player has to wait before attacking again in milliseconds
	
	//Class specific constants
	//Battle constants
    public static final int ATTACK_COST = 10; //How much energy is used when a character attacks another character
    public static final int RETALIATION_RESIST = 2; //How much to divide the targets armor by during retaliation attack

    //Character specific constants
    public static final float ACTORMS = 4.0f;
    
    //Archer class constants
    public static final int ARCHER_ATTACK_ACC = 85;
    public static final int ARCHER_ATTACK_POWER = 28;
    public static final int ARCHER_RESISTANCE = 19;
    public static final int ARCHER_MAXHP = 280;
    public static final int ARCHER_HPREGEN = 10;
    public static final int ARCHER_ENERGY = 300;

    //Fighter class constants
    public static final int FIGHTER_ATTACK_ACC = 90;
    public static final int FIGHTER_ATTACK_POWER = 35;
    public static final int FIGHTER_RESISTANCE = 22;
    public static final int FIGHTER_MAXHP = 500;
    public static final int FIGHTER_HPREGEN = 10;
    public static final int FIGHTER_ENERGY = 300;

    //Mage class constants
    public static final int MAGE_ATTACK_ACC = 85;
    public static final int MAGE_ATTACK_POWER = 25;
    public static final int MAGE_RESISTANCE = 12;
    public static final int MAGE_MAXHP = 500;
    public static final int MAGE_HPREGEN = 8;
    public static final int MAGE_ENERGY = 400;

    //Priest class constants
    public static final int PRIEST_ATTACK_ACC = 90;
    public static final int PRIEST_ATTACK_POWER = 10;
    public static final int PRIEST_RESISTANCE = 30;
    public static final int PRIEST_MAXHP = 400;
    public static final int PRIEST_HPREGEN = 25;
    public static final int PRIEST_ENERGY = 500;
    public static final int PRIEST_HEALAMOUNT = 50;

    //Tank class constants
    public static final int TANK_ATTACK_ACC = 40;
    public static final int TANK_ATTACK_POWER = 15;
    public static final int TANK_RESISTANCE = 35;
    public static final int TANK_MAXHP = 750;
    public static final int TANK_HPREGEN = 14;
    public static final int TANK_ENERGY = 300;
}
