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
            new int[]{0,1,2},
            new int[]{0,2,1},
            new int[]{0,1,2, 0,2,3},
            new int[]{0,2,1},
            new int[]{0,2,5, 1,4,3},
            new int[]{0,1,2, 1,3,2},
            new int[]{0,1,4, 1,3,4, 1,2,3},
            new int[]{0,2,1},
            new int[]{0,2,3, 0,1,3},
            new int[]{0,3,2, 1,5,4},
            new int[]{0,1,3, 0,3,4, 0,4,2},
            new int[]{0,1,3, 0,3,2},
            new int[]{0,3,2, 2,3,4, 2,4,1},
            new int[]{0,1,3, 1,4,3, 1,2,4},
            new int[]{0,1,2, 0,2,3}
    };

    public static int[] getIndices(int indexVal){
        return indicesArray[indexVal];
    }
}
