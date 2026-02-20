package Game;

import Board.BoardGame;

public class Main {
    public static void main(String[] args) {
        BoardGame start = new BoardGame();
        start.displayBoard();
        start.movePiece(7, 4, 6, 3);
        start.displayBoard();
        start.movePiece(0, 0, 7, 0);
        start.displayBoard();
        start.movePiece(7, 0, 7, 4);
        start.displayBoard();
    }
}
