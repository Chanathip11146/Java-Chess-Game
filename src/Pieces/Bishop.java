package Pieces;

public class Bishop extends Piece {

    public Bishop(String Player_color, int row, int column) {
        super(Player_color,"B",  row, column);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol) {
        int row = this.getRow(), column = this.getColumn(); //ดึงค่ามาใช้
        boolean rowValid,colValid;
        
        // หาผลต่าง
        int diffRow = Math.abs(row - targetRow);
        int diffCol = Math.abs(column - targetCol);

        // เช็กว่าเดินถูกไหม
        if(diffRow == diffCol ) rowValid = colValid = true; // เช็กแถว
        else rowValid = colValid = false;
        if(diffRow == 0 && diffCol == 0) rowValid = colValid = false; // เช็กว่าอยู่กับที่
        
        // ทั้งสองต้องเป็นจริง
        return rowValid && colValid;
    }    
}
