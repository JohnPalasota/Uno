package com.improving;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many players? \n>");
        int playerAmount = scanner.nextInt();
        scanner.nextLine();
        Game game = new Game(playerAmount);
        game.play();


    }
}
