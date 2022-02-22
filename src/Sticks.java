import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sticks {

    public static void main(String[] args){
        solveBoard(new Board(all), getAllPossibleMoves(new Board(all)));
        System.out.println("Method called " + count + " times");
        printSolution();
    }

    // initialize the board
    private static final int[] row1 = {1,1,1,1,1};
    private static final int[] row2 =  {1,1,1,1};
    private static final int[] row3 =   {1,1,1};
    private static final int[] row4 =    {1,1};
    private static final int[] row5 =     {0};
    private static final int[][] all = {row1,row2,row3,row4,row5};

    // counter for the amount of recursive calls
    private static int count = 0;

    // move history to follow step by step
    private static List<Move> history = new ArrayList<>();

    /**
     *  Checks whether a given stick can move to the top left.
     *
     * @param board the state of the board
     * @param row the row position of the stick to move
     * @param column the column position of the stick to move
     * @return true if the stick given by row and column can move to the top left. False otherwise.
     */
    private static boolean canMoveTopLeft(int[][] board, int row, int column){
        if(row - 1 < 0 || row - 2 < 0){
            return false;
        }
        if(board[row][column] == 1){// field must not be empty
            if(board[row-1][column] == 1){// field top left not empty
                // 2nd top left field empty
                return board[row - 2][column] == 0;
            }
        }
        return false;
    }

    private static boolean canMoveTopRight(int[][] board, int row, int column){
        if(row - 1 < 0 || row - 2 < 0){
            return false;
        }
        if(board[row][column] == 1){
            if(board[row-1][column + 1] == 1){
                return board[row - 2][column + 2] == 0;
            }
        }
        return false;
    }

    private static boolean canMoveLeft(int[][] board, int row, int column){
        if(column - 1 < 0 || column - 2 < 0){
            return false;
        }
        if(board[row][column] == 1){
            if(board[row][column - 1] == 1){
                return board[row][column - 2] == 0;
            }
        }
        return false;
    }

    private static boolean canMoveRight(int[][] board, int row, int column){
        if(column + 1 >= board[row].length || column + 2 >= board[row].length){
            return false;
        }
        if(board[row][column] == 1){
            if(board[row][column + 1] == 1){
                return board[row][column + 2] == 0;
            }
        }
        return false;
    }

    private static boolean canMoveBottomLeft(int[][] board, int row, int column){
        if(row + 1 > board.length || row + 2 > board.length || column - 1 < 0 || column - 2 < 0){
            return false;
        }
        if(board[row][column] == 1){
            if(board[row+1][column-1] == 1){
                return board[row + 2][column - 2] == 0;
            }
        }
        return false;
    }

    private static boolean canMoveBottomRight(int[][] board, int row, int column){
        if(row + 1 >= board.length || row + 2 >= board.length || column >= board[row+1].length || column >= board[row+2].length){
            return false;
        }
        if(board[row][column] == 1){
            if(board[row+1][column] == 1){
                return board[row + 2][column] == 0;
            }
        }
        return false;
    }

    /**
     * Moves the stick according to the given move command.
     *
     * @param move the stick and direction in which to move it
     * @return the board after the change.
     */
    private static Board moveTopLeft(Move move){
        move.getBoard().getArray()[move.getRow() - 2][move.getColumn()] = 1;
        move.getBoard().getArray()[move.getRow() - 1][move.getColumn()] = 0;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 0;
        return new Board(move.getBoard().getArray());
    }

    /**
     * Undoes a move.
     *
     * @param move the move to undo
     * @return the restored board
     */
    private static Board undoMoveTopLeft(Move move){
        move.getBoard().getArray()[move.getRow() - 2][move.getColumn()] = 0;
        move.getBoard().getArray()[move.getRow() - 1][move.getColumn()] = 1;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 1;
        return new Board(move.getBoard().getArray());
    }

    private static Board moveTopRight(Move move){
        move.getBoard().getArray()[move.getRow() - 2][move.getColumn() + 2] = 1;
        move.getBoard().getArray()[move.getRow() - 1][move.getColumn() + 1] = 0;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 0;
        return new Board(move.getBoard().getArray());
    }

    private static Board undoMoveTopRight(Move move){
        move.getBoard().getArray()[move.getRow() - 2][move.getColumn() + 2] = 0;
        move.getBoard().getArray()[move.getRow() - 1][move.getColumn() + 1] = 1;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 1;
        return new Board(move.getBoard().getArray());
    }

    private static Board moveLeft(Move move){
        move.getBoard().getArray()[move.getRow()][move.getColumn() - 2] = 1;
        move.getBoard().getArray()[move.getRow()][move.getColumn() - 1] = 0;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 0;
        return new Board(move.getBoard().getArray());
    }

    private static Board undoMoveLeft(Move move){
        move.getBoard().getArray()[move.getRow()][move.getColumn() - 2] = 0;
        move.getBoard().getArray()[move.getRow()][move.getColumn() - 1] = 1;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 1;
        return new Board(move.getBoard().getArray());
    }

    private static Board moveRight(Move move){
        move.getBoard().getArray()[move.getRow()][move.getColumn() + 2] = 1;
        move.getBoard().getArray()[move.getRow()][move.getColumn() + 1] = 0;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 0;
        return new Board(move.getBoard().getArray());
    }

    private static Board undoMoveRight(Move move){
        move.getBoard().getArray()[move.getRow()][move.getColumn() + 2] = 0;
        move.getBoard().getArray()[move.getRow()][move.getColumn() + 1] = 1;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 1;
        return new Board(move.getBoard().getArray());
    }

    private static Board moveBottomLeft(Move move){
        move.getBoard().getArray()[move.getRow() + 2][move.getColumn() - 2] = 1;
        move.getBoard().getArray()[move.getRow() + 1][move.getColumn() - 1] = 0;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 0;
        return new Board(move.getBoard().getArray());
    }

    private static Board undoMoveBottomLeft(Move move){
        move.getBoard().getArray()[move.getRow() + 2][move.getColumn() - 2] = 0;
        move.getBoard().getArray()[move.getRow() + 1][move.getColumn() - 1] = 1;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 1;
        return new Board(move.getBoard().getArray());
    }

    private static Board moveBottomRight(Move move){
        move.getBoard().getArray()[move.getRow() + 2][move.getColumn()] = 1;
        move.getBoard().getArray()[move.getRow() + 1][move.getColumn()] = 0;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 0;
        return new Board(move.getBoard().getArray());
    }

    private static Board undoMoveBottomRight(Move move){
        move.getBoard().getArray()[move.getRow() + 2][move.getColumn()] = 0;
        move.getBoard().getArray()[move.getRow() + 1][move.getColumn()] = 1;
        move.getBoard().getArray()[move.getRow()][move.getColumn()] = 1;
        return new Board(move.getBoard().getArray());
    }


    /**
     * Makes a move
     *
     * @param move the move to execute
     * @return the changed board
     */
    private static Board makeMove(Move move){
        if(move.getDirection() == Direction.TOPLEFT &&
                canMoveTopLeft(move.getBoard().getArray(),move.getRow(),move.getColumn())){
            System.out.println(move.getDirection());
            move.getBoard().setValue(move.getBoard().getValue() -1);
            return moveTopLeft(move);
        }else if(move.getDirection() == Direction.TOPRIGHT &&
                canMoveTopRight(move.getBoard().getArray(),move.getRow(),move.getColumn())){
            System.out.println(move.getDirection());
            move.getBoard().setValue(move.getBoard().getValue() -1);
            return moveTopRight(move);
        }else if(move.getDirection() == Direction.LEFT &&
                canMoveLeft(move.getBoard().getArray(),move.getRow(),move.getColumn())){
            System.out.println(move.getDirection());
            move.getBoard().setValue(move.getBoard().getValue() -1);
            return moveLeft(move);
        }else if(move.getDirection() == Direction.RIGHT &&
                canMoveRight(move.getBoard().getArray(),move.getRow(),move.getColumn())){
            System.out.println(move.getDirection());
            move.getBoard().setValue(move.getBoard().getValue() -1);
            return moveRight(move);
        }else if(move.getDirection() == Direction.BOTTOMLEFT &&
                canMoveBottomLeft(move.getBoard().getArray(),move.getRow(),move.getColumn())){
            System.out.println(move.getDirection());
            move.getBoard().setValue(move.getBoard().getValue() -1);
            return moveBottomLeft(move);
        }else if(move.getDirection() == Direction.BOTTOMRIGHT &&
                canMoveBottomRight(move.getBoard().getArray(),move.getRow(),move.getColumn())){
            System.out.println(move.getDirection());
            move.getBoard().setValue(move.getBoard().getValue() -1);
            return moveBottomRight(move);
        }
        return move.getBoard();
    }

    /**
     * Undoes a move
     *
     * @param move the move to undo
     * @return the restored board
     */
    private static Board undoMove(Move move){
        if(move.getDirection() == Direction.TOPLEFT){
            move.getBoard().setValue(move.getBoard().getValue() +1);
            return undoMoveTopLeft(move);
        }else if(move.getDirection() == Direction.TOPRIGHT){
            move.getBoard().setValue(move.getBoard().getValue() +1);
            return undoMoveTopRight(move);
        }else if(move.getDirection() == Direction.LEFT){
            move.getBoard().setValue(move.getBoard().getValue() +1);
            return undoMoveLeft(move);
        }else if(move.getDirection() == Direction.RIGHT){
            move.getBoard().setValue(move.getBoard().getValue() +1);
            return undoMoveRight(move);
        }else if(move.getDirection() == Direction.BOTTOMLEFT){
            move.getBoard().setValue(move.getBoard().getValue() +1);
            return undoMoveBottomLeft(move);
        }else if(move.getDirection() == Direction.BOTTOMRIGHT){
            move.getBoard().setValue(move.getBoard().getValue() +1);
            return undoMoveBottomRight(move);
        }else{
            System.out.println("error: no moves to undo");
        }
        return move.getBoard();
    }

    /**
     * Loops through the board and saves all possible moves in a list.
     *
     * @param board the board to loop through
     * @return the list of all possible moves in that game state
     */
    private static List<Move> getAllPossibleMoves(Board board){
        List<Move> result = new ArrayList<>();
        for(int row = 0; row < board.getArray().length; row++) {
            for (int col = 0; col < board.getArray()[row].length; col++) {
                if(canMoveTopLeft(board.getArray(),row,col)){
                    result.add(new Move(board, row, col, Direction.TOPLEFT));
                }else if(canMoveTopRight(board.getArray(),row,col)){
                    result.add(new Move(board, row, col, Direction.TOPRIGHT));
                }else if(canMoveLeft(board.getArray(),row,col)){
                    result.add(new Move(board, row, col, Direction.LEFT));
                }else if(canMoveRight(board.getArray(),row,col)){
                    result.add(new Move(board, row, col, Direction.RIGHT));
                }else if(canMoveBottomLeft(board.getArray(),row,col)){
                    result.add(new Move(board, row, col, Direction.BOTTOMLEFT));
                }else if(canMoveBottomRight(board.getArray(),row,col)){
                    result.add(new Move(board, row, col, Direction.BOTTOMRIGHT));
                }
            }
        }
        return result;
    }

    /**
     * Solves the board with recursive back tracking. Prints every step to the console.
     * Increases the counter to see how many times this function was called.
     * Saves the game winning moves in a list.
     *
     * These are the steps:
     * Try the first possible move.
     * If this recursively solves the board, return true.
     * Else, undo the moves (backtrack) and try the next move.
     *
     * @param board the board to solve
     * @param possible the possible moves in the given state of the board
     * @return true if the board was solved, false otherwise
     */
    private static boolean solveBoard(Board board, List<Move> possible){
        ++count;
        System.out.println(board.getValue());
        printBoard(board);
        if(possible.size() == 0){
            return board.getValue() == 1;
        }
        for(Move move : possible){
            history.add(move);
            if(solveBoard(new Board(makeMove(move).getArray()),getAllPossibleMoves(new Board(makeMove(move).getArray())))){
                return true;
            }else{
                history.remove(move);
                undoMove(move);
            }
        }
        return board.getValue() == 1;
    }

    /**
     * Prints the winning set of moves to the console
     */
    private static void printSolution(){
        System.out.println("Solution: ");
        history.sort(Comparator.comparing(Move::getBoard));
        Collections.reverse(history);
        for(Move m : history){
            m.printMove();
            System.out.println("Sticks left: " + m.getBoard().getValue());
        }
        System.out.println("Solved!");
    }

    /**
     * Prints a board to the console
     *
     * @param board the board to print
     */
    private static void printBoard(Board board) {
        String blank = " ";
        for(int row = 0; row < board.getArray().length; row++) {
            System.out.print(blank.repeat(row));
            for (int col = 0; col < board.getArray()[row].length; col++) {
                System.out.print(board.getArray()[row][col] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("----------");
    }
}
