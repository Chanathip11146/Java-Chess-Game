package Pieces;

public abstract class Piece { //แม่แบบหมาก
    private String color,name;  //ฝั่ง
    private int atRow, atColumn; //ตำแหน่ง
    

    public Piece(String Player_color, String n, int row, int column) {
        this.color = Player_color;
        this.atRow = row;
        this.atColumn = column;
        this.name = n;
    }

    //คืนค่า
    public String getColor() {return this.color;} 
    public int getRow() {return this.atRow;}
    public int getColumn() {return this.atColumn;}
    public String getName() {return this.name;}

    public void setRow(int row) { //แก้แถว
        if(row >= 0 && row <= 7) this.atRow = row;
        else System.out.println("Please try again using numbers 0-7.");
    }

    public void setColumn(int column) { //แก้ตอน
        if(column >= 0 && column <= 7) this.atColumn = column;
        else System.out.println("Please try again using numbers 0-7.");
    }

    public void setName(String n) { //แก้ชื่อ
        this.name = n;
    }
    
    public void pieceMove(int targetRow, int targetCol) { //หลังเข็กแล้วเดิน
        this.setRow(targetRow);
        this.setColumn(targetCol);
    }

    public String getSymbol() {
        if(color.equals("White")) return "W" + this.name;
        else return "B" + this.name;
    }



    public abstract boolean isValidMove(int targetRow, int targetCol); //ทุกตัวต้องเช็ก

}
