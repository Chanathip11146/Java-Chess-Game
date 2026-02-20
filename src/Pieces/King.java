package Pieces;

public class King extends Piece {

    public King(String Player_color, int row, int column) {
        super(Player_color,"K",  row, column);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol) {
        int row = this.getRow(), column = this.getColumn(); //ดึงค่ามาใช้
        boolean rowValid,colValid;

        //หาผลต่าง
        int diffRow = Math.abs(row - targetRow);
        int diffCol = Math.abs(column - targetCol);
        
        //เช็กว่าเดินถูกไหม
        if(diffRow <= 1 ) rowValid = true; //เช็กแถว
        else rowValid = false;
        if(diffCol <= 1 ) colValid = true; //เช็กตอน
        else colValid = false;
        if(diffRow == 0 && diffCol == 0) rowValid = colValid = false; //เช็กว่าอยู่กับที่
        
        //เช็กว่าทั้งสองเดินถูก
        return rowValid&&colValid; 
    }
}