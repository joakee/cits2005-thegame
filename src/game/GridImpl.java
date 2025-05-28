package game;

public class GridImpl implements Grid{
    private final int size;
    PieceColour[][] gameBoard;

    public GridImpl(int size){
        this.size = size;
        this.gameBoard = new PieceColour[size][size]; // asssumption as per project sheet, board always square

        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                gameBoard[row][col] = PieceColour.NONE;
            }
        }
    }

    @Override public int getSize(){ return size; }
    @Override public void setPiece(int row, int col, PieceColour piece){
        if (row < 0 || col < 0 || row >= size || col >= size){
            throw new IllegalArgumentException(String.format(
                "Yo! The coordinates (%d, %d) you provided are outside of the game board my friend!"));
        }

        if (piece != PieceColour.BLACK && piece != PieceColour.WHITE) {
            System.out.println(piece.toString());
            throw new IllegalArgumentException(String.format(
                "Hey! where'd you get that weird game piece? Thats not from this game!"));
        }

        gameBoard[row][col] = piece; 
    }
    @Override public PieceColour getPiece(int row, int col){ 
        if (row < 0 || col < 0 || row >= size || col >= size){
            throw new IllegalArgumentException(String.format(
                "Yo! The coordinates (%d, %d) you provided are outside of the game board my friend!"));
        }
        
        return gameBoard[row][col]; 
    }
    
    //TODO:  double check the items in da 
    //       iterables are also not copies 
    //       ie. not referential
    @Override public Grid copy() {
        GridImpl copy = new GridImpl(size);

        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                copy.gameBoard[row][col] = gameBoard[row][col];
            }
        }
        return copy;
    }
    @Override
    public String toString(){
        String stringOut = "";

        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                PieceColour currentPiece = gameBoard[row][col];
                char cell = '.';

                if      (currentPiece == PieceColour.WHITE){ cell = 'W'; }
                else if (currentPiece == PieceColour.BLACK){ cell = 'B'; }
                // else throw new IllegalStateException(String.format("Uninitialised Cell at (%d,%d)", row, col));
                stringOut += cell;
            }
            stringOut += '\n';  
        }
        return stringOut;
    }
}