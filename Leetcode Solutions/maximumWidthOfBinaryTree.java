// Given a binary tree, write a function to get the maximum width of the given tree. 
// The width of a tree is the maximum width among all levels. The binary tree has the
//  same structure as a full binary tree, but some nodes are null.

// The width of one level is defined as the length between the end-nodes (the leftmost 
// 	and right most non-null nodes in the level, where the null nodes between the end-nodes
// 	 are also counted into the length calculation.

// Example 1:

// Input: 

//            1
//          /   \
//         3     2
//        / \     \  
//       5   3     9 

// Output: 4
// Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).


USE BFS, and the positions of a nodes children to compute widths as we traverse tree
in level order fashion. 
The max width of a level will be difference between start and end node in level
Update the global maxWidth at end of each level.

If parent node is at position N
left child ---> 2*N    right child -----> 2*N+1

TC: O(N) to perform level order bfs and iterate on all ndoes in tree
SC: O(N) for the queue which can grow up to size n

public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Map<TreeNode, Integer> m = new HashMap<TreeNode, Integer>();
        //map used to store, (node---->position)
        q.offer(root);
        m.put(root, 1); //first node is at position one
        int curW = 0;
        int maxW = 0;
        while(!q.isEmpty()){
            int size = q.size();
            int start = 0;
            int end = 0;
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(i == 0) start = m.get(node);
                if(i == size - 1) end = m.get(node);
                if(node.left != null){
                    m.put(node.left, m.get(node) * 2);
                    q.offer(node.left);
                }
                if(node.right != null){
                    m.put(node.right, m.get(node) * 2 + 1);
                    q.offer(node.right);
                }
            }
            curW = end - start + 1;
            maxW = Math.max(curW, maxW);
        }
        return maxW;
    }