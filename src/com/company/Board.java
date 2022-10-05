package com.company;

import java.util.Arrays;

public class Board {
    Boolean[] board = new Boolean[33];
    public int numPieces = 32;

    public Board(){
        Arrays.fill(board, true); //instead of for loop
        board[16] = false; //empty middle square
    }
}
