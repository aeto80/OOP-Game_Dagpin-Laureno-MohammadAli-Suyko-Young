package com.mycompany.main;

public class Player {
	private String name;
	private int hp;
	private int maxHp;
	private int attackDamage;
	private String imagePath;
	
	/*
		this is where we assign all the stats for the player
		the format for instantiating the player is

		playerName = new Player ("name", 100, 20, "file path of image");

		where "name" is the name, 100 is the initial hp, 20 is the dmg they can deal, and "file path of image" will be replaced with the actual file path of the image
	*/
	public Player(String name, int maxHp, int attackDamage, String imagePath) {
		this.name = name; // name of the player
		this.maxHp = maxHp; // starting hp of the player
		this.hp = maxHp; // current hp of the player
		this.attackDamage = attackDamage; // how much dmg the player can deal
		this.imagePath = imagePath; // this is for the file path of the image we will use for the player
	}

	/*
		these are your standard getters. use as needed
	*/
	public String getName() {
		return name;
	}

	public int getHP() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	/*
		call this method whenever the player takes dmg from the enemy
		the format for this method is

		playerName.takeDamage(enemyName.getAttackDamage());

		where playerName.takeDamage() is the method call, and enemyName.getAttackDamage() is the enemy object's attack stat
	*/
	public void takeDamage(int damage) {
		hp = hp - damage;

		if (hp < 0 ) {
			hp = 0;
		}
	}

	/*
		resets the hp of the player to its original. use as needed/
	*/
	public void resetHp() {
		hp = getMaxHp();
	}


	/*
		checks if the player is still alive. 
		use this everytime the player takes dmg.
		call gameOver (it will be in another class) when true.
	*/
	public boolean isAlive() {
	
		if (hp > 0) {
			return true;
		}

		return false;
	}
}