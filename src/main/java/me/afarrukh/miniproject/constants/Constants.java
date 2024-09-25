package me.afarrukh.miniproject.constants;

public class Constants {

    public static final int FPS = 30;

    public static final int playerAtkCd = 200; // How long the player has to wait before attacking again in milliseconds

    // Class specific constants
    // Battle constants
    public static final int ATTACK_COST = 10; // How much energy is used when a character attacks another character
    public static final int RETALIATION_RESIST = 2; // How much to divide the targets armor by during retaliation attack

    // Character specific constants
    public static final float ACTORMS = 6.0f;

    public static final int DEFAULT_TILE_REFRESH_SPEED = 300; // Speed in milliseconds before a tile changes frames

    // Archer class constants
    public static final int ARCHER_ATTACK_ACC = 85;
    public static final int ARCHER_ATTACK_POWER = 28;
    public static final int ARCHER_RESISTANCE = 19;
    public static final int ARCHER_MAXHP = 280;
    public static final int ARCHER_HPREGEN = 10;
    public static final int ARCHER_ENERGY = 300;

    // Fighter class constants
    public static final int FIGHTER_ATTACK_ACC = 90;
    public static final int FIGHTER_ATTACK_POWER = 35;
    public static final int FIGHTER_RESISTANCE = 22;
    public static final int FIGHTER_MAXHP = 500;
    public static final int FIGHTER_HPREGEN = 10;
    public static final int FIGHTER_ENERGY = 300;

    // Mage class constants
    public static final int MAGE_ATTACK_ACC = 85;
    public static final int MAGE_ATTACK_POWER = 25;
    public static final int MAGE_RESISTANCE = 12;
    public static final int MAGE_MAXHP = 500;
    public static final int MAGE_HPREGEN = 8;
    public static final int MAGE_ENERGY = 400;
    public static final int MAGE_SPEED = 7;

    // Priest class constants
    public static final int PRIEST_ATTACK_ACC = 90;
    public static final int PRIEST_ATTACK_POWER = 10;
    public static final int PRIEST_RESISTANCE = 30;
    public static final int PRIEST_MAXHP = 400;
    public static final int PRIEST_HPREGEN = 25;
    public static final int PRIEST_ENERGY = 500;
    public static final int PRIEST_HEALAMOUNT = 50;

    // Tank class constants
    public static final int TANK_ATTACK_ACC = 40;
    public static final int TANK_ATTACK_POWER = 15;
    public static final int TANK_RESISTANCE = 35;
    public static final int TANK_MAXHP = 750;
    public static final int TANK_HPREGEN = 14;
    public static final int TANK_ENERGY = 300;

    // Map related constants
    public static final int MAP_WIDTH =
            125; // Both this and the height are NOT in terms of pixels. These are actually how many tiles there are in
    // a newly generated map.
    public static final int MAP_HEIGHT = 125;
    public static final int numTiles = 5; // This is how many types of tiles there are
    public static final int GRASS_PERCENTAGE =
            93; // This is the percentage of terrain that is generated as a grass tile.
    public static final int TILEWIDTH = 64;
    public static final int TILEHEIGHT = 64;

    // Entity related constants
    public static final int MAX_TREES = 500;
    public static final int TREE_PERCENTAGE = 50;
    public static final int TREE_HEALTH = 95;

    public static final int MAX_ROCKS = 225;
    public static final int ROCK_PERCENTAGE = 35;
    public static final int ROCK_HEALTH = 132;
    public static final int GEM_CHANCE = 10;
}
