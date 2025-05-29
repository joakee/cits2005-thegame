package game;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collection;

public class GameImpl implements Game {
    private GridImpl grid;
    private PieceColour currentPlayer;
    private int size;
    

    public GameImpl(int size){
        if (size <= 0){ throw new IllegalArgumentException("Physically impossible! Pick a size 1 or higher!"); } 
        this.grid = new GridImpl(size);
        this.size = grid.getSize();
        currentPlayer = PieceColour.WHITE;      // white player always moves first
    }


    @Override 
    public boolean isOver(){ 
        return getMoves().isEmpty() || winner() != PieceColour.NONE; 
    
    }


    @Override 
    public PieceColour winner(){
        if (PathFinder.leftToRight(grid, PieceColour.WHITE)){ return PieceColour.WHITE; }
        if (PathFinder.topToBottom(grid, PieceColour.BLACK)){ return PieceColour.BLACK; }
        return PieceColour.NONE;
    }


    @Override 
    public Collection<Move> getMoves(){
        Collection<Move> moves = new ArrayList<>();
        
        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                if (grid.getPiece(row, col) == PieceColour.NONE){
                    moves.add(new MoveImpl(row, col));
                }
            }
        }
        return moves;
    }



    @Override 
    public void makeMove(Move move){
        int row = move.getRow(); int col = move.getCol();

        // illegal state handling
        if (row < 0 || col < 0 || row >= size || col >= size)
            throw new IllegalArgumentException("You can't just move off the board!");
        if (grid.getPiece(row, col) != PieceColour.NONE) 
            throw new IllegalArgumentException("Cell taken. Get lost!");
        

        //TODO: maybe deprecate in favor of a turnary op
        grid.setPiece(row, col, currentPlayer);
        if (currentPlayer == PieceColour.WHITE){ currentPlayer = PieceColour.BLACK; }
        else                                   { currentPlayer = PieceColour.WHITE; }

        System.out.println("After move:");                                  ///
        System.out.println(this.grid); // uses GridImpl.toString()          ///
        System.out.println("Moves left: " + getMoves().size());     

    }



    @Override 
    public Game copy(){
        GameImpl dupeGame = new GameImpl(size);
        dupeGame.grid = (GridImpl) this.grid.copy();
        dupeGame.size = this.size;
        dupeGame.currentPlayer = this.currentPlayer;      // no need to deep copy as its referencing a constant anyways
        
        return dupeGame;
    }

    @Override public PieceColour currentPlayer(){ return currentPlayer; }
    @Override public Grid getGrid() { return grid.copy(); }
    



}
