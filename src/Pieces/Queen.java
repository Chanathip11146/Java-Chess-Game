package Pieces;

public class Queen extends Piece {
    
    public Queen(String Player_color, int row, int column) {
        super(Player_color,"Q",  row, column);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol) {
        int row = this.getRow(), column = this.getColumn(); //ดึงค่ามาใช้
        boolean isRookMove,isBishopMove;
        
        // หาผลต่าง
        int diffRow = Math.abs(row - targetRow);
        int diffCol = Math.abs(column - targetCol);

        // เช็กว่าเดินถูกไหม
        isRookMove = (diffRow == 0 || diffCol == 0);
        isBishopMove = (diffRow == diffCol);
        
        //มีสักอันที่ถูกและไม่อยู่กับที่
        return (isRookMove || isBishopMove) && !(diffRow == 0 && diffCol == 0);
    } 
}
