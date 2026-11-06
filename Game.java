package com.mycompany.main;

public class Game {
    private Player player;
    private Enemy currentEnemy;
    private int currentLevel;
    private boolean playerTurn;
    private boolean gameOver;
    private boolean victory;

    // Array of unique enemy image paths per level
    private String[] enemyImages = {
        "/Images/enemy_lvl1.png",
        "/Images/enemy_lvl2.png",
        "/Images/enemy_lvl3.png",
        "/Images/enemy_lvl4.png",
        "/Images/enemy_lvl5.png",
        "/Images/enemy_lvl6.png"
    };

    public Game() {
        this.player = new Player("Player", 100, 20, "/Images/player.png");
        this.currentLevel = 1;
        this.playerTurn = true;
        this.gameOver = false;
        this.victory = false;
        
        startGame();
    }

    public void startGame() {
        player.resetHp();
        currentLevel = 1;
        gameOver = false;
        victory = false;
        playerTurn = true;
        
        startLevel();
    }

    public void startLevel() {
        int enemyHp = 30 + (currentLevel * 10);
        int enemyDamage = 10 + (currentLevel * 2);
        
        String imagePath = enemyImages[currentLevel - 1];
        
        currentEnemy = new Enemy("Level " + currentLevel + " Bug", enemyHp, enemyDamage, imagePath);
        playerTurn = true;
        gameOver = false;
    }

    public void answerQuestion(boolean correct) {
        if (gameOver || !playerTurn) {
            return;
        }

        if (correct) {
            currentEnemy.takeDamage(player.getAttackDamage());
            if (!currentEnemy.isAlive()) {
                nextLevel();
                return;
            }
        } else {
            playerTurn = false;
            enemyTurn();
        }
    }

    public void enemyTurn() {
        if (gameOver) {
            return;
        }

        player.takeDamage(currentEnemy.getAttackDamage());

        if (!player.isAlive()) {
            gameOver = true;
            victory = false;
            return;
        }

        playerTurn = true;
    }

    public void nextLevel() {
        currentLevel++;
        if (currentLevel > 6) {
            gameOver = true;
            victory = true;
            return;
        }
        startLevel();
    }

    public Player getPlayer() { return player; }
    public Enemy getCurrentEnemy() { return currentEnemy; }
    public int getCurrentLevel() { return currentLevel; }
    public boolean isPlayerTurn() { return playerTurn; }
    public boolean isGameOver() { return gameOver; }
    public boolean isVictory() { return victory; }
}