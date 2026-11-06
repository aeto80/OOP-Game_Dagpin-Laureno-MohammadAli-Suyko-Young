package com.mycompany.main;

public class Enemy {
    private String name;
    private int hp;
    private int maxHp;
    private int attackDamage;
    private String imagePath;

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

    public String getImagePath() {
        return imagePath;
    }

    public void takeDamage(int damage) {
        hp = hp - damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void resetHp() {
        hp = getMaxHp();
    }

    public boolean isAlive() {
        return hp > 0;
    }
}