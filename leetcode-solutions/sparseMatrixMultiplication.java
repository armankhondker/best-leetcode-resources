// Given two sparse matrices A and B, return the result of AB.

// You may assume that A's column number is equal to B's row number.

// Example:

// Input:

// A = [
//   [ 1, 0, 0],
//   [-1, 0, 3]
// ]

// B = [
//   [ 7, 0, 0 ],
//   [ 0, 0, 0 ],
//   [ 0, 0, 1 ]
// ]

// Output:

//      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
// AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                   | 0 0 1 |


Note, sparse matrix means, msot of the elements are zero 

TO multiply matrices, mutliply first row in first matrix, by first column in second matrix, REPEAT 


BRUTE FORCE,

//TC: O(n^3)
//SC: O(n^2) for output, but O(1) for actual algorithm 
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
	int m = A.length, n = A[0].length;
	int nB = B[0].length;
	int [][] C = new int[m][nB];
	for (int i = 0; i<m; i++) {
		for (int j = 0; j<nB; j++){
			for( int k = 0; k<n; k++)
				C[i][j] += A[i][k]*B[k][j];
		}
	}
	return C;
}
}

OPTIMIZED, flip the outer most loop, with the second loop!!! Then, dont execut if we have * 0

For brute force solution, for each C[ i ] [ j ], it uses C[ i ] [ j ] += A[ i ] [ k ] * B[ k ] [ j ] where k = [ 0, n].Note: even A[ i ] [ k ] or B[ k ] [ j ] is 0, the multiplication is still executed.

For the above smart solution, if A[ i ] [ k ] == 0 or B[ k ] [ j ] == 0, it just skip the multiplication . 
This is achieved by moving for-loop" for ( k = 0; k < n; k++ ) " from inner-most loop to middle loop, so 
that we can use if-statement to tell whether A[ i ] [ k ] == 0 or B[ k ] [ j ] == 0. 


//TC: O(n^3)
//SC: O(n^2) for output array O(1) for algorithm ß

public class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int k = 0; k < n; k++) {
                if (A[i][k] != 0) {.  //dont waste time calculating, every other A[i][k] will be calcuated nB times
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j]; //dont waste time with them equaling 0
                    }
                }
            }
        }
        return C;   
    }
}


IF THEY WANT DATA STRUCTURE TO REPRESENT SPARSE matrix

// A sparse matrix can be represented as a sequence of rows, each of which is a 
// sequence of (column-number, value) pairs of the nonzero values in the row.

// So let's create a non-zero array for A, and do multiplication on B.

public int[][] multiply(int[][] A, int[][] B) {
    int m = A.length, n = A[0].length, nB = B[0].length;
    int[][] result = new int[m][nB];

    List[] indexA = new List[m];
    for(int i = 0; i < m; i++) {
        List<Integer> numsA = new ArrayList<>();
        for(int j = 0; j < n; j++) {
            if(A[i][j] != 0){
                numsA.add(j); 
                numsA.add(A[i][j]);
            }
        }
        indexA[i] = numsA;
    } //PROB TO SAME FOR B

    for(int i = 0; i < m; i++) {
        List<Integer> numsA = indexA[i];
        for(int p = 0; p < numsA.size() - 1; p += 2) //to iterate on the data strcture{
            int colA = numsA.get(p);
            int valA = numsA.get(p + 1);
            for(int j = 0; j < nB; j ++) {
                int valB = B[colA][j];
                result[i][j] += valA * valB;
            }
        }
    }

    return result;   
}



BEST SOLUTION  EASY CODE, use node class

  class Node {
        int x,y;
        Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        List<Node> listA = new ArrayList<>();
        List<Node> listB = new ArrayList<>();


        //O(NxM) A dimmensions
        for (int i=0;i<A.length;i++) {
            for (int j=0; j<A[0].length; j++) {
                if (A[i][j]!=0) listA.add(new Node(i,j));
            }
        }

        //O(MxO) B dimmesnsions
        for (int i=0;i<B.length;i++) {
            for (int j=0;j<B[0].length;j++) {
                if (B[i][j]!=0) listB.add(new Node(i,j));
            }
        }   

        //O(NxM x MxO) in worst case where not a sparse matrix 
        for (Node nodeA : listA) {
            for (Node nodeB: listB) {
                if (nodeA.y==nodeB.x) { //need this to be true to compute dot product
                    result[nodeA.x][nodeB.y] += A[nodeA.x][nodeA.y] * B[nodeB.x][nodeB.y];
                }
            }
        }

        return result;

    }






