// In an infinite binary tree where every node has two children, the nodes are labelled in row order.

// In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, 
// while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
// 	Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.


// Zig Zag Binary Tree:
//              1
//            /   \
//          3       2  <- 3+2-3 = 2/2 = 1
//        /  \     /  \
//      4     5   6     7   <- 7+4-4 = 7/2 = 3
//    / |    /|   |\    | \
//  15 14  13 12 11 10  9  8   <- 15+8-14 = 9/2 = 4

// Input: label = 14
// Output: [1,3,4,14]

//TC: O(logn)
//SC: O(N) to store linkedlist 

public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> result = new LinkedList<>();
        int parent = label;
        result.addFirst(parent);

        while(parent != 1) {
            int d = (int)(Math.log(parent)/Math.log(2));
            int offset = (int)Math.pow(2, d+1) - 1 - parent;
            parent = ((int)Math.pow(2, d) + offset) / 2;
            result.addFirst(parent);   
        }
        
        return result;
    }