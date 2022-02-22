# Stick Game Recursion
A recursive backtracking algorithm to solve a board game.

The rules:
There is a triangular board with 5 rows. The top row has 5 slots, each following row has 1 fewer slot than the row above. Each slot, except for the one on the bottom, is occupied by a stick. You can remove stick A by jumping over adjacent stick B and plugging A into an empty slot. Doing so will remove stick B. There are 6 different movement directions: top left, top right, left, right, bottom left, bottom right.
The goal is to have only 1 stick remaining. 

My code representation is as follows:
```
    private static final int[] row1 = {1,1,1,1,1};
    private static final int[] row2 =  {1,1,1,1};
    private static final int[] row3 =   {1,1,1};
    private static final int[] row4 =    {1,1};
    private static final int[] row5 =     {0};
    private static final int[][] all = {row1,row2,row3,row4,row5};
```

Where 1 is an occupied slot, 0 is an empty one. There are 2 possible moves in this starting configuration. You could either move the 1st stick in the 3rd row to the bottom, taking out the 1st stick in the 4th row, or you could take the last stick in the 3rd row and place it in the empty slot. In my code representation, these moves are printed as:
```
Move 2,0 BOTTOMRIGHT // for the first move
Move 2,2 BOTTOMLEFT // for the second move
```

Use the code to your disgretion.
