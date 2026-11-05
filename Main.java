/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        
        game.startGame();
        
        System.out.println("game started");
        System.out.println("level " + game.getCurrentLevel());
        System.out.println("enemy: " + game.getCurrentEnemy().getName());
        System.out.println("enemy hp: " + game.getCurrentEnemy().getHP());
        System.out.println("player hp: " + game.getPlayer().getHP());
    }
}
