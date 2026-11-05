/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author Jeremie Young
 */
public class Game {
    private Player player;      //Stores the player object, which allows the Game class to access player's health, and damaage
    private Enemy currentEnemy;     //Stores the enemy object that the player is fighting 
    private int currentLevel;      //Keeps track of the current level in the game
    private boolean playerTurn;     //Seeks out whose turn it is (true = player turn || false = enemy turn)
    private boolean gameOver;       //Determines whether the games has already ended (true = game is over || false = game is running)
    private boolean victory;        //Determine whether the player has won the game (true = victory || false = loser)
    
    // Constructor
    public Game(){
        this.player = new Player("player", 100, 20, "path");   //Assigns the received Player object to the player's field
        this.currentLevel = 1;      //Starting at lvl 1
        this.playerTurn = true;     //Allow player to take turn first
        this.gameOver = false;      //Game not yet finished 
        this.victory = false;       //player is still not the winner
        
        startGame();    //Call the method that initializes the game
    }
    
    //Start Game
    public void startGame(){
        player.resetHp();
        currentLevel = 1;      //Reset game to lvl 1
        gameOver = false;       //Makes sure that game is not marked as finish
        victory = false;    //Reset victory status
        playerTurn = true;      //Gives first turn to the player
        
        startLevel();      //Creates the enemy for lvl 1
    }
     
    //Start Level
    public void startLevel(){
        currentEnemy = new Enemy("enemy", 10, 10, "path");     //Cretes an Enemy obbject using current level
        playerTurn = true;          //Gives first turn to the player
    }
    
    //Player Turn
    public void playerTurn(){
        if(gameOver || !playerTurn){        //Stops the method if game is over || if it is currently not the player's turn
            return;
        }
        
        playerTurn = true;      //Confirming that it is the player's turn
    }
    
    //Answer Question
    public void answerQuestion(boolean correct){
        if(gameOver || !playerTurn){        //Prevents answering if the game is over || if it is not the player's turn
            return;
        }
        
        if (correct){
            currentEnemy.takeDamage(player.getAttackDamage());    // Correct answer, enemy takes damage
            
            if (currentEnemy.getHP() <= 0){     //Checking if enemy is defeated
                nextLevel();
                return;
            }
        } else{
            player.takeDamage(currentEnemy.getAttackDamage());    // Wrong answer, player takes damage
            
            if (player.getHP() <= 0){      // Chcecking if player is defeated
                gameOver = true;       //Ends the game because player took the L
                victory = false;       //Sets to false since player lost 
                return;
            }
        }
        
        playerTurn = false;     //Switch to enemy turn
        
        enemyTurn();    //Calls the enemy's turn method
    }
    
    // Enemy Turn
    public void enemyTurn(){
        if (gameOver || playerTurn){    //Prevents the enemy from attacking if the game is over || if it is still the player's turn
            return;
        }
        
        player.takeDamage(currentEnemy.getAttackDamage());    //Enemy Attacks
        
        if (player.getHP() <= 0){       // Checking if player is defeated
            gameOver = true;        //Ends the game because player took the L
            victory = false;        //Sets to false since player lost 
            return;
        }
        
        playerTurn = true;      // Return to a player for their turn again
    }
    
    // Next Level
    public void nextLevel(){
        currentLevel++;     //Increments the level, when current enemy has been defeated
        
        if (currentLevel > 6){      //Just an example of 5 levels.. (idk how many levels we will have)
            gameOver = true;        //Ends game after completing the final level
            victory = true;     //Declares the player as winner
            return;
        }
        
        startLevel();      //If there are still remaining levels, creates a new enely for the next level
    }
    
    //Getters (Allow other classes to access teh private variables of the Game class. They provide info w/o directly modifying the variables)
    public Player getPlayer(){      //Returns the Player object 
        return player;
    }
    public Enemy getCurrentEnemy(){     //Returns the current Enemy object
        return currentEnemy;
    }
    public int getCurrentLevel(){       //Returns the cuurent level number
        return currentLevel;
    }
    public boolean isPlayerTurn(){      //Returns true if it is the player's turn
        return playerTurn;
    }
    public boolean isGameOver(){        //Returns true if the game has ended
        return gameOver;
    }
    public boolean isVictory(){     //Returns true if player has won the game
        return victory;
    }
}