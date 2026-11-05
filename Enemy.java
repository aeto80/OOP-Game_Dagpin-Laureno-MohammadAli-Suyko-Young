package com.mycompany.main;

public class Enemy {
	private String name;
	private int hp;
	private int maxHp;
	private int attackDamage;
	private String imagePath;
	
	/*
		this is where we assign all the stats for each enemy
		all enemies will use this same class throughout the game
		the methods for this class is pretty much the same the as player's class
		please read the comments in the player class file to see how they work
	*/

	public Enemy(String name, int maxHp, int attackDamage, String imagePath) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackDamage = attackDamage;
		this.imagePath = imagePath;
	}

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

	public void takeDamage(int damage) {
		hp = hp - damage;

		if (hp < 0 ) {
			hp = 0;
		}
	}

	public void resetHp() {
		hp = getMaxHp();
	}

	public boolean isAlive() {
	
		if (hp > 0) {
			return true;
		}

		return false;
	}
}