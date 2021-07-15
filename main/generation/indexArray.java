package MarchingSquares.main.generation;

import java.util.ArrayList;

public class indexArray {

    /*
    0--4--1
    |     |
    7     5
    |     |
    3--6--2

    1--2
    |  |
    8--4
     */

    private static int[][] indicesArray=new int[][]{
            new int[]{},
            new int[]{0,4,7},
            new int[]{1,5,4},
            new int[]{0,1,7, 1,5,7},
            new int[]{2,6,5},
            new int[]{0,4,7, 2,6,7},
            new int[]{1,6,4, 1,2,6},
            new int[]{0,1,7, 1,6,7, 1,2,6},
            new int[]{3,7,6},
            new int[]{0,4,3, 3,4,6},
            new int[]{1,5,4, 3,7,6},
            new int[]{0,1,5, 0,5,6, 0,6,7},
            new int[]{2,3,5, 3,7,5},
            new int[]{0,4,3, 3,4,5, 2,3,5},
            new int[]{1,2,4, 2,7,4, 2,3,7},
            new int[]{0,1,3, 1,2,3}
    };

    public static int[] getIndices(int indexVal){
        return indicesArray[indexVal];
    }
}
