package Game;

import javax.swing.*;
import Board.BoardGame;
import Pieces.Piece;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChessGUI {
    private JButton[][] squares = new JButton[8][8];
    private JFrame frame;
    private BoardGame boardGame = new BoardGame();
    private Map<String, String> symbols = new HashMap<>();
    private int firstRow = -1, firstCol = -1;

    public ChessGUI() {
        frame = new JFrame("Chess");//สร้างหัวชื่อ
        frame.setSize(600, 600);//
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ปิดแล้วหยุดโปรแกรม
        frame.setLayout(new GridLayout(8, 8));//สร้างตาราง
        
        initSymbols();
       
        refreshBoard();
        frame.setVisible(true); //เปิดค่าการมองเห็นหลังทำทุกอย่างเสร็จแล้ว
    }

    public void initSymbols() {
        //แทนที่ด้วยสัญลักษณ์
        symbols.put("WK", "♔");
        symbols.put("BK", "♚");
        symbols.put("WQ", "♕");
        symbols.put("BQ", "♛");
        symbols.put("WR", "♖");
        symbols.put("BR", "♜");
        symbols.put("WB", "♗");
        symbols.put("BB", "♝");
        symbols.put("WN", "♘");
        symbols.put("BN", "♞");
        symbols.put("WP", "♙");
        symbols.put("BP", "♟");
      

        for (int row = 0; row < 8; row++) {//ใส่ปุ่มให้ตาราง
            for (int col = 0; col < 8; col++) {
                JButton btn = new JButton();
                int r = row, c = col; //แทนค่าไว้ก่อนเพราะจาวาไม่ให้ใช้ค่าจากลูป
                btn.addActionListener(e -> handleClick(r,c));
                btn.setFont(new Font("Serif", Font.PLAIN, 30));
                if ((row + col) % 2 == 0) { //ใส่สีให้ตาราง
                    btn.setBackground(new Color(240, 217, 181)); // สีอ่อน
                } else {
                    btn.setBackground(new Color(181, 136, 99));  // สีเข้ม
                    }
                squares[row][col] = btn;
                frame.add(btn);
                }
            }
    }

    public void refreshBoard(){
        Piece[][] board = boardGame.getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == null) {
                        squares[row][col].setText("");
                } else {
                        squares[row][col].setText(symbols.get(board[row][col].getSymbol()));
                }

                }
            }
    } 

    public void handleClick(int row, int col) {  
        if (firstRow == -1 && boardGame.getBoard()[row][col] != null) {
            firstRow = row ;
            firstCol = col;
            squares[row][col].setBackground(new Color(106, 168, 79));
            showValidMoves(row, col);
        }else if(firstRow != -1){       
            boardGame.movePiece(firstRow, firstCol, row, col);
            squares[firstRow][firstCol].setBackground(((firstRow + firstCol) % 2 == 0) ? new Color(240, 217, 181) : new Color(181, 136, 99));
            firstRow = -1;
            firstCol = -1;
            resetColors();
            refreshBoard();
        }
    }   
    
    public void showValidMoves(int row, int col) {     
        for (int r = 0 ; r < 8 ; r++) {
            for (int c = 0 ; c < 8 ; c++) {
                if(boardGame.getBoard()[row][col].isValidMove(r, c))
                    squares[r][c].setBackground(new Color(186, 214, 159)); 
            }
        }

    }

    public void resetColors() {
        for (int r = 0 ; r < 8 ; r++) {
                for (int c = 0 ; c < 8 ; c++) {
                    squares[r][c].setBackground(((r + c) % 2 == 0) ? new Color(240, 217, 181) : new Color(181, 136, 99));
                }
            }
    }
}

