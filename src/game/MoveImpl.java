package game;

public class MoveImpl implements Move{
    private final int row;
    private final int col;

    public MoveImpl(int row, int col){
        //this.grid = grid;
        this.row = row;
        this.col = col;
    }

    @Override
    public int getRow(){
        return row;
    }

    @Override
    public int getCol(){
        return col; 
    }
    
    @Override
    public String toString(){
        String xyTuple = "(" + this.row + 
                           "," + this.col + 
                           ")"; 
        return xyTuple;
    }
}
