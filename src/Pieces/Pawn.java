package Pieces;

public class Pawn extends Piece {

    public Pawn(String Player_color, int row, int column) {
        super(Player_color, "P", row, column);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol) {
        int row = this.getRow(), column = this.getColumn();
        int firstdRow;
        boolean isForwardOne, isForwardTwo, isCapture;
       
       
        int direction; //ทิศทางการเดินของหมาก
        if (this.getColor().equals("White")) {
            direction = -1; // ขาวเดินขึ้น (ค่า Row ลดลง)
            firstdRow = 6;
        } else {
            direction = 1;  // ดำเดินลง (ค่า Row เพิ่มขึ้น)
            firstdRow = 1;
        }

        // หาผลต่าง
        int diffRow = targetRow - row; //ไม่ต้องใช้เพราะจะได้รู้ว่าตัวไหนเดินไปทางไหน
        int diffCol = Math.abs(targetCol - column); // ส่วนคอลัมน์ยังใช้ abs ได้ เพราะเฉียงซ้ายหรือขวาก็มีค่าเท่ากัน


        isForwardOne = (diffCol == 0 && diffRow == direction) ? true : false; //เช็กการเดินทั่วไป
        isForwardTwo = (diffCol == 0 && diffRow == direction * 2 && row == firstdRow) ? true : false; //เช็กเดินครั้งแรก
        isCapture = (diffCol == 1 && diffRow == direction) ? true : false; //เช็กการกิน      
               
        // สุดท้ายส่งค่ากลับว่าตรงกับท่าไหนสักท่าไหม
        return isForwardOne || isForwardTwo || isCapture;
    }
}
