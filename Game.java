package com.mycompany.main;

public class Game {
    private Player player;      //Stores the player object, which allows the Game class to access player's health, and damaage
    private Enemy currentEnemy;     //Stores the enemy object that the player is fighting 
    private int currentLevel;       //Keeps track of the current level in the game
    private boolean playerTurn;     //Seeks out whose turn it is (true = player turn || false = enemy turn)
    private boolean gameOver;   //Determines whether the games has already ended 
    private boolean victory;    //Determines whether the player has won the game

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
        this.player = new Player("Player", 100, 20, "/Images/player.png");      //Assign the Player object to the player's field 
        this.currentLevel = 1;      //Starts at level 1
        this.playerTurn = true;     //Allows player to take turn first 
        this.gameOver = false;      //Game not finished 
        this.victory = false;   //Player is still not the winner
        
        startGame();    //Call the method that initializes the game
    }

    public void startGame() {
        player.resetHp();   //Resets the hp
        currentLevel = 1;   //Reset game to lvl 1 
        gameOver = false;   //Makes sure that game is not marked
        victory = false;    //Reset victory status
        playerTurn = true;  //Gives the first turn to the player
        
        startLevel();   //Creates the enemy for lvl 1
    }

    public void startLevel() {
        int enemyHp = 30 + (currentLevel * 10);     //Adds the enemy's hp per level
        int enemyDamage = 10 + (currentLevel * 2);  //Damages the enemy's hp per level
        
        String imagePath = enemyImages[currentLevel - 1];   //shows the current enemy that you are currently fighting for that level
        
        currentEnemy = new Enemy("Level " + currentLevel + " Bug", enemyHp, enemyDamage, imagePath);    //Creates an Enemy object using the current level
        playerTurn = true;  //Give first turn to the player
        gameOver = false;   //If player dies, game over
    }

    public void answerQuestion(boolean correct) {
        if (gameOver || !playerTurn) {  //Stops the method if game is over || if it is not currently the player's turn
            return;
        }

        if (correct) {
            currentEnemy.takeDamage(player.getAttackDamage());  //Correct answer, enemy takes damage
            if (!currentEnemy.isAlive()) {  //If current enemy is not alive, then proceed to next level
                nextLevel();
                return;
            }
        } else {
            playerTurn = false;
            enemyTurn();    //If not player turn, then enemy has their turn to attack
        }
    }

    public void enemyTurn() {
        if (gameOver) {
            return;     //Enemy wins
        }

        player.takeDamage(currentEnemy.getAttackDamage());  //Wrong answer, player takes damage

        if (!player.isAlive()) {
            gameOver = true;    //If player is dead, game over 
            victory = false;    //Player took the L
            return;
        }

        playerTurn = true;  
    }

    public void nextLevel() {
        currentLevel++;     //Increments the level, when the current enemy has been defeated
        if (currentLevel > 6) {     //6 levels
            gameOver = true;    //Game finished
            victory = true;     //Player wins
            return;
        }
        startLevel();   //If there are still remaining levels, create a new level 
    }

    public Player getPlayer() { return player; }    //Returns the player object
    public Enemy getCurrentEnemy() { return currentEnemy; }     //Returns the enemy object
    public int getCurrentLevel() { return currentLevel; }   //Returns the current level number 
    public boolean isPlayerTurn() { return playerTurn; }    //Returns true if the player's turn
    public boolean isGameOver() { return gameOver; }    //Return true if game has ended
    public boolean isVictory() { return victory; }      //Returns true if player has won the game
}