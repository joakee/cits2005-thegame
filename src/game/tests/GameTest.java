package game.tests;

import java.util.Collection;
import game.*;

public class GameTest extends Test {
    private static final String testIndicator = "[\u001B[36mTEST\u001B[0m]";
    public static void main(String[] args) {
        testInvalidSize();
        testValidSize();
        testInitialPlayer();
        testInitialMoves();
        testMakeMove();
        testMoveReflection();
        testInitialGameState();
        testInvalidMoves();
        testFullGame();
        testCopy();
        testEdgeCases();
 
        checkAllTestsPassed();
    }



    /**
     * Check if the GameImpl correcrly throws an exception
     * when an invalid game size is give.
     */
    private static void testInvalidSize() {
        System.out.println(testIndicator + " invalid game size throws exception");
        boolean caught = false;

        try { new GameImpl(0); }
        catch (IllegalArgumentException e){ caught = true; }
        expect(true, caught);
        
        caught = false;
        try { new GameImpl(-5); }
        catch (IllegalArgumentException e){ caught = true; }
        expect(true, caught);

        System.out.println("\n");
    }



    /**
     * Check if GameImpl correcrly take a valid game size.
     */
    private static void testValidSize() {
        System.out.println(testIndicator + " valid game size doesn't throw");
        boolean caught = false;

        try {
            for (int i = 1; i <= 1000; i++) {       // a bit crude, but surely covers enough bases righr?
                System.out.print("\r" + testIndicator + " Testing board size: " + i);
                new GameImpl(i);
            }
        } 
        catch (IllegalArgumentException e){ caught = true; }
        expect(false, caught);

        System.out.println("\n");
    }


    /** 
     * Verify that the initial player is WHUTE and 
     * that the winner is NONE
     */
    private static void testInitialPlayer() {
        System.out.println(testIndicator + " initial player is WHITE");

        Game g = new GameImpl(5);
        expect(PieceColour.WHITE, g.currentPlayer());
        expect(g.winner(), PieceColour.NONE);

        System.out.println("\n");
    }



    /**
     * Check that the amount of possible moves
     * is size^2 at the start, and that the amount
     * decrements by 1 with each move.
     */
    private static void testInitialMoves() {
        System.out.println(testIndicator + " initial moves available");

        Game g = new GameImpl(5);
        expect(25, g.getMoves().size());
        
        // check move decreases on turn
        g.makeMove(new MoveImpl(0,0));
        expect(24, g.getMoves().size());

        System.out.println("\n");
    }



    /**
     * Check that makeMove switches the
     * current player each turn. 
     */
    private static void testMakeMove() {
        System.out.println(testIndicator + " makeMove functionality");

        Game g = new GameImpl(3);
        g.makeMove(new MoveImpl(1,1));
        expect(PieceColour.BLACK, g.currentPlayer());
        
        // check current player changes on each turn
        g.makeMove(new MoveImpl(0,0));
        expect(PieceColour.WHITE, g.currentPlayer());

        System.out.println("\n");
    }



    /** 
     * Check that a player's move is accurately reflected on the board
     */
    private static void testMoveReflection() {
        System.out.println(testIndicator + " move representation on grid");
        Game g = new GameImpl(3);
        g.makeMove(new MoveImpl(1,1));
        expect(PieceColour.WHITE, g.getGrid().getPiece(1,1));
        expect(PieceColour.NONE, g.getGrid().getPiece(0,0));

        System.out.println("\n");
    }



    /**
     * Check that copy does not create a referential copy
     * but rather a new Game object with the same state.
     */
    private static void testInitialGameState() {
        System.out.println(testIndicator + " initial game state");
        Game g = new GameImpl(3);
        expect(false, g.isOver());
        expect(PieceColour.NONE, g.winner());

        System.out.println("\n");
    }



    /**
     * Check that an invalid move throws an exception.
     */
    private static void testInvalidMoves() {
        System.out.println(testIndicator + " invalid moves");
        Game g = new GameImpl(3);
        g.makeMove(new MoveImpl(1,1));
        
        // cell not vacant
        boolean caught = false;
        try { g.makeMove(new MoveImpl(1,1)); } 
        catch (IllegalArgumentException e) { caught = true; }
        expect(true, caught);
        
        // cell out of bounds
        caught = false;
        try { g.makeMove(new MoveImpl(3,3)); } 
        catch (IllegalArgumentException e) { caught = true; }
        expect(true, caught);

        System.out.println("\n");
    }

    /**
     * "Simulate" a full game by iterating over prefined move sequences
     * for 3 games in whcich white wins, black wins or the game is drawn
     * respectively.
     */
    private static void testFullGame() {
        System.out.println(testIndicator + " full game simulation");

        // predefined move sequences for 3 games in whuch the winner 
        // is white, black or drawn respectively
        int[][] movesWhiteWin = { {0,0}, {1,0}, {0,1}, {1,1}, {0,2} };
        int[][] movesBlackWin = { {0,1}, {0,0}, {1,2}, {1,0}, {2,1}, {2,0}};
        int[][] movesDraw =     { {0,0}, {0,1}, {1,1}, {1,0} };

        Game g = new GameImpl(3);
        for (int[] move : movesWhiteWin) {                  // iterate over white-winning game move seq
            if (!g.isOver()) {
                g.makeMove(new MoveImpl(move[0], move[1]));
            }
        }
        expect(true, g.isOver());
        expect(PieceColour.WHITE, g.winner());               // WHITE should win
        System.out.println("\n");

        Game g1 = new GameImpl(3);                           // reset game for next test
        for (int[] move : movesBlackWin) {                   // iterate over black-winning game move seq
            if (!g1.isOver()) {
                g1.makeMove(new MoveImpl(move[0], move[1]));
            }
        }
        expect(true, g1.isOver());
        expect(PieceColour.BLACK, g1.winner());             // BLACK should win 
        System.out.println("\n");

        Game g2 = new GameImpl(3);                          // reset game for next test
        for (int[] move : movesDraw) {                      // iterate over draw game move seq
            if (!g2.isOver()) {
                g2.makeMove(new MoveImpl(move[0], move[1]));
            }
        }
        expect(true, g1.isOver());
        expect(PieceColour.NONE, g2.winner());              // NONE should win - i.e. draw
        System.out.println("\n");
    }

    /** Check that copy creates a new Game object
     *  with the same state as the original and is
     *  not referential.
     */
    private static void testCopy() {
        System.out.println(testIndicator + " copy functionality");
        Game original = new GameImpl(3);
        original.makeMove(new MoveImpl(0,0)); 
        original.makeMove(new MoveImpl(1,1));

        Game copy = original.copy();
        original.makeMove(new MoveImpl(0,1));

        // grid copy
        expect(PieceColour.NONE, copy.getGrid().getPiece(0,1));        // untouched copy
        expect(PieceColour.WHITE, original.getGrid().getPiece(0,1));   // altered original

        // player copy
        expect(PieceColour.BLACK, original.currentPlayer());
        expect(PieceColour.WHITE, copy.currentPlayer());

        System.out.println("\n");
    }

    /**
     * Test against various edge cases, details in method
     */
    private static void testEdgeCases() {
        System.out.println(testIndicator + " edge cases");

        // Test against 1x1 grid size 
        System.out.println("1x1 Board:");
        Game g = new GameImpl(1);
        expect(1, g.getMoves().size());
        g.makeMove(new MoveImpl(0,0));

        expect(true, g.isOver());
        expect(PieceColour.WHITE, g.winner()); // white should always win (see GameImpl.winner())
        
        // move that "fills" the board, i.e. the last possible move, is also the winning move, 
        // reflecting this in the game by updating the winner. 
        Game g1 = new GameImpl(2);
        g1.makeMove(new MoveImpl(0,0)); 
        g1.makeMove(new MoveImpl(1,1));
        g1.makeMove(new MoveImpl(0,1)); 
        expect(true, g1.isOver());
        expect(PieceColour.WHITE, g1.winner());

        Game g2 = new GameImpl(2);
        g2.makeMove(new MoveImpl(0, 0)); 
        Game copy = g2.copy();
        expect(g2.currentPlayer(), copy.currentPlayer());

        System.out.println("\n");
    }



}