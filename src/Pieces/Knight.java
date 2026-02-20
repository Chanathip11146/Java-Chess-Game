package Pieces;

public class Knight extends Piece {
    
    public Knight(String Player_color, int row, int column) {
        super(Player_color,"N",  row, column);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol) {
        int row = this.getRow(), column = this.getColumn(); //ดึงค่ามาใช้
        boolean N_1,N_2;
        
        //หาผลต่าง
        int diffRow = Math.abs(row - targetRow);
        int diffCol = Math.abs(column - targetCol);
        
        //ม้าเดินเป็นตัวแอลไหม
        N_1 = diffRow == 1 && diffCol == 2;
        N_2 = diffRow == 2 && diffCol == 1;
        
        //เช็กว่าอย่างใดอย่างหนึ่งเดินถูก
        return (N_1 || N_2) && !(diffRow == 0 && diffCol == 0);
    }
}