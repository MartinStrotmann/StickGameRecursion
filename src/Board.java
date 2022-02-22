/**
 * Class representation of the board.
 * A board contains a 2D int array with 1s and 0s, as well as the number of sticks present on the board.
 */
public class Board implements Comparable<Board>{
    private int[][] array;
    private int value;

    public Board(int[][] board){
        this.array = board;
        this.value = calculateValue();
    }

    public int[][] getArray() {
        return array;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Calculates the number of sticks (1s) present in the board.
     *
     * @return the number of sticks on the board.
     */
    public int calculateValue(){
        int result = 0;
        for(int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                if(this.getArray()[row][col] == 1){
                    ++result;
                }
            }
        }
        return result;
    }

    /**
     * Checks whether two Board objects are identical.
     *
     * @param obj the object to compare this one to.
     * @return true, if all entries match, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj == null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        final Board other = (Board) obj;
        if(this.getArray().length != other.getArray().length){
            return false;
        }
        for(int row = 0; row < this.getArray().length; row++) {
            for (int col = 0; col < this.getArray()[row].length; col++) {
                if(this.getArray()[row].length != other.getArray()[row].length){
                    return false;
                }
                if(this.getArray()[row][col] == other.getArray()[row][col]){
                    result = true;
                }else{
                    return false;
                }
            }
        }
        return result;
    }

    /**
     * Compare method for two Boards. A Board is bigger than another, if it's value is bigger.
     *
     * @param o the other Board to compare this one to.
     * @return negative int, if this board is smaller, 0 if equal, positive int if bigger.
     */
    @Override
    public int compareTo(Board o) {
        return this.getValue() - o.getValue();
    }
}
