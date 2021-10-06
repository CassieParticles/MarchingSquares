package main.generation;

public class indexArray {

    private static int[][] indicesArray=new int[][]{
            new int[]{},
            new int[]{0,1,7},
            new int[]{1,3,2},
            new int[]{0,2,3, 0,3,7},
            new int[]{5,7,6},
            new int[]{0,1,5, 0,5,6},
            new int[]{1,2,3, 5,6,7},
            new int[]{0,2,3, 0,3,5, 0,5,6},
            new int[]{3,4,5},
            new int[]{0,1,7, 3,4,5},
            new int[]{1,2,4, 1,4,5},
            new int[]{0,2,7, 2,5,7, 2,4,5},
            new int[]{3,4,7, 4,6,7},
            new int[]{0,1,6, 1,3,6, 3,4,6},
            new int[]{1,2,4, 1,4,7, 4,6,7},
            new int[]{0,2,4, 0,4,6}
    };
    
    
    private static int[] edgeTable=new int[]{
    	0x00,	0x83,	0x0e,	0x8d,
    	0xe0,	0x63,	0xee,  	0x6d,
    	0x38,	0xbb,	0x36,  	0xb5,
    	0xd8,  	0x5b,  	0xd6,  	0x55
    };

    public static int[] getIndices(int indexVal){
        return indicesArray[indexVal];
    }
    
    public static int getEdges(int indexVal){
    	return edgeTable[indexVal];
    }
}
