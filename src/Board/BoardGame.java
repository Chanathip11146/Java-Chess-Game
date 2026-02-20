package Board;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;


public class BoardGame {
    private Piece[][] board = new Piece[8][8];
    private boolean isWhiteTurn = true; // เริ้มเกมด้วยสีขาวก่อน
    

    public BoardGame() { //สร้างกระดานเริ่มต้น
        //วางคิง
        board[7][4] = new King("White", 7, 4);
        board[0][4] = new King("Black", 0, 4);
        
        //วางควีน
        board[7][3] = new Queen("White", 7, 3);
        board[0][3] = new Queen("Black", 0, 3);
        
        //วางบิชอบ
        board[0][2] = new Bishop("Black", 0, 2);
        board[0][5] = new Bishop("Black", 0, 5);
        board[7][2] = new Bishop("White", 7, 2);
        board[7][5] = new Bishop("White", 7, 5);
        
        //วางเรือ
        board[0][0] = new Rook("Black", 0, 0);
        board[0][7] = new Rook("Black", 0, 7);
        board[7][0] = new Rook("White", 7, 0);
        board[7][7] = new Rook("White", 7, 7);
        
        //วางม้า
        board[0][1] = new Knight("Black", 0, 1);
        board[0][6] = new Knight("Black", 0, 6);
        board[7][1] = new Knight("White", 7, 1);
        board[7][6] = new Knight("White", 7, 6);
        


    }


    public void displayBoard(){
        StringBuilder sb = new StringBuilder();
        sb.append("     0    1    2    3    4    5    6    7\n"); //เลขตอน
        for(int row = 0; row < 8; row++){
            sb.append("  " + row); //เลขแถว
            for(int col = 0; col < 8; col++){
                if(board[row][col] == null) sb.append(" [  ]"); //ถ้า null ให้ใส่เป็นว่าง
                else sb.append(" [" + board[row][col].getSymbol() + "]"); //ใส่ตัวย่อ
            }
            sb.append("\n\n"); //ขึ้นบรรทัดสองรอบ
        }
        System.out.println(sb.toString());
    }


    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) { //ย้ายหมาก
        
        if (startRow < 0 || startRow > 7 || endRow < 0 || endRow > 7) { //เช็กว่าเกินกระดานหรือป่าว
            System.out.println("Please try again using numbers 0-7.");
            return false;
            }

        if (board[startRow][startCol] == null) { //เช็กต้นทางว่ามีหมากไหม
            System.out.println("Error,There are no pieces here.");
            return false;
            }
        
        Piece selectedPiece = board[startRow][startCol]; //เลือกออกมาก่อน

        if(!isTurn(selectedPiece)) return false;
            
            //เช็กปลายทางว่ามีหมากไหมและใช่ฝ่ายเดียวกันหรือป่าว
        if (board[endRow][endCol] != null && selectedPiece.getColor().equals(board[endRow][endCol].getColor())) { 
            System.out.println("Error,You can't go there");
            return false;
            }
        
        //เริ่มย้ายหมาก
        if (selectedPiece.isValidMove(endRow, endCol)) {
             
            //เข็กตัวที่ขวางทางอยู่ ยกเว้นม้าเพราะมันกระโดดได้
            if (!selectedPiece.getName().equals("N")) { 
                if (!isPathClear(startRow, startCol, endRow, endCol)) {
                    System.out.println("Error, มีตัวหมากขวางทางเดิน!");
                    return false;
                    }
            }

            //วางปลายทาง
            board[endRow][endCol] = selectedPiece; 

            //ลบต้นทาง
            board[startRow][startCol] = null;
        
            //อัพเดทพิกัดในคลาสแม่
            selectedPiece.pieceMove(endRow, endCol);
            
            isWhiteTurn = !isWhiteTurn;
            return true; // ย้ายสำเร็จ
        } 
    
        return false; // ย้ายไม่สำเร็จ
    }


    public boolean isTurn(Piece thisPiece) {
        if(isWhiteTurn && thisPiece.getColor().equals("White")) return true;
        if(!isWhiteTurn && thisPiece.getColor().equals("Black")) return true;
        else return false;
    }


    public boolean isPathClear(int startRow, int startCol, int endRow, int endCol) {
        // เทียบค่าเพื่อหาทิศในการเดิน
        int rowStep = Integer.compare(endRow, startRow); 
        int colStep = Integer.compare(endCol, startCol); 
        //เริ่มก้าวแรกก่อนเข้าลูปเพื่อเช็ก
        int currentRow = startRow + rowStep;
        int currentCol = startCol + colStep;

        // เดินสแกนไปทีละช่อง จนกว่าจะถึงปลายทาง
        while (currentRow != endRow || currentCol != endCol) {
            if (board[currentRow][currentCol] != null) {
                return false; // เจอตัวกั้น
            }
            currentRow += rowStep;
            currentCol += colStep;
        }
        return true; // ไม่มีอะไรกั้น
    }
}