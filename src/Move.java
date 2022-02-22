/**
 * Class representation of a move.
 * A move contains the game state (board), the stick to move, given by its position (row,column) on the board and the
 * direction in which to move it.
 *
 */
public class Move {

    private final Board board;
    private final int row;
    private final int column;
    private final Direction direction;


    public Move(Board board, int row, int column, Direction direction){
        this.board = board;
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    /**
     * Checks whether two moves are identical.
     *
     * @param obj the other move to compare this one to.
     * @return true, if board, direction, row and column match, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        final Move other = (Move) obj;
        return this.getBoard() == other.getBoard() && this.getDirection() == other.getDirection() &&
                this.getRow() == other.getRow() && this.getColumn() == other.getColumn();
    }

    public Board getBoard() {
        return board;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * Prints a move to the console.
     * Row, column, direction.
     */
    public void printMove(){
        System.out.println("Move " + this.getRow() + "," + this.getColumn() + " " + this.getDirection());
    }
}
