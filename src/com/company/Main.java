package com.company;

import java.util.Stack;

public class Main {
    //array for start, skip and end of each legal move
    //a "move" is just identified by a number from 1 to 72
    static int[] start = {
            0, 0, 1, 2, 2, 3, 3, 4, 5, 5,
            6, 6, 7, 7, 8, 8, 8, 8, 9, 9,
            9, 9, 10, 10, 10, 10, 11, 11, 12, 12,
            13, 14, 15, 15, 15, 15, 16, 16, 16, 16,
            17, 17, 17, 17, 18, 19, 20, 20, 21, 21,
            22, 22, 22, 22, 23, 23, 23, 23, 24, 24,
            24, 24, 25, 25, 26, 26, 27, 27, 28, 29,
            29, 30, 30, 31, 32, 32};

    static int[] skip = {
            1, 3, 4, 1, 5, 4, 8, 9, 4, 10,
            7, 13, 8, 14, 3, 7, 9, 15, 4, 8,
            10, 16, 5, 9, 11, 17, 10, 18, 11, 19,
            14, 15, 8, 14, 16, 22, 9, 15, 17, 23,
            10, 16, 18, 24, 17, 18, 13, 21, 14, 22,
            15, 21, 23, 27, 16, 22, 24, 28, 17, 23,
            25, 29, 18, 24, 19, 25, 22, 28, 23, 24,
            28, 27, 31, 28, 29, 31};

    static int[] end = {
            2, 8, 9, 0, 10, 5, 15, 16, 3, 17,
            8, 20, 9, 21, 0, 6, 10, 22, 1, 7,
            11, 23, 2, 8, 12, 24, 9, 25, 10, 26,
            15, 16, 3, 13, 17, 27, 4, 14, 18, 28,
            5, 15, 19, 29, 16, 17, 6, 22, 7, 23,
            8, 20, 24, 30, 9, 21, 25, 31, 10, 22,
            26, 32, 11, 23, 12, 24, 15, 29, 16, 17,
            27, 22, 32, 23, 24, 30
    };

    static Stack<Integer> moves = new Stack<>();

    public static Board board = new Board();

    public static void main(String[] args) {
        search();
    }

    public static Boolean makeMove(int move){

        if (!(board.board[start[move]] && board.board[skip[move]] && !board.board[end[move]])){
            return false;
        }

        //change states
        board.board[start[move]] = false;
        board.board[skip[move]] = false;
        board.board[end[move]] = true;

        return true;
    }

    //simple print to visualise moves
    public static void printBoard(){
        System.out.println("         " + board.board[0]+ " " + board.board[1] + " " + board.board[2]);
        System.out.println("         " +board.board[3]+ " " + board.board[4] + " " + board.board[5]);
        System.out.println(board.board[6]+ " " + board.board[7] + " " + board.board[8] + " " + board.board[9]+ " " + board.board[10] + " " + board.board[11] + " " + board.board[12]);
        System.out.println(board.board[13]+ " " + board.board[14] + " " + board.board[15] + " " + board.board[16]+ " " + board.board[17] + " " + board.board[18] + " " + board.board[19]);
        System.out.println(board.board[20]+ " " + board.board[21] + " " + board.board[22] + " " + board.board[23]+ " " + board.board[24] + " " + board.board[25]+ " " + board.board[26]);
        System.out.println("         " + board.board[27]+ " " + board.board[28] + " " + board.board[29]);
        System.out.println("         " + board.board[30]+ " " + board.board[31] + " " + board.board[32]);
    }

    //method for solving recursively
    public static void search(){

        //checks game over
        if (board.numPieces == 1 && board.board[16]){
            printBoard();
            System.out.println(moves);
            convertResults();
            System.exit(0);
        }

        //iterates all possible moves
        for (int i = 0; i < start.length; i++){
            if (!makeMove(i)){
                continue;
            }
            moves.push(i);
            board.numPieces--;

            search();

            //resets the move to backtrack
            moves.pop();
            board.board[start[i]] = true;
            board.board[skip[i]] = true;
            board.board[end[i]] = false;
            board.numPieces++;
        }
    }
    //just prints a readable version of the results
    public static void convertResults(){
        for (Integer move : moves) {
            System.out.println(start[move] + " to " + end[move]);
        }
    }
}
