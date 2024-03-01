//The idea is to traverse all the node in the tree in level order(Here I use one Queue to store each level's nodes. The time I 
//traverse each level is the queue's size(the number of nodes from upper level)). Each time a node is traversed, the position of the node(as it is in a full binary tree) is stored in the HashMap.
// If the position of the parent node is 'n', then the left child is '2 * n' and the right child is '2 * n + 1'. The width of each level is the last node's position in this level subtracts the first node's position in this level plus 1.


// Time Complexity: O(N) where N is the number of nodes in the input tree. We traverse every node.

// Space Complexity: O(N), the size of the implicit call stack in our DFS.

    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Map<TreeNode, Integer> m = new HashMap<TreeNode, Integer>(); //hashmap to store positions of nodes in tree
        q.offer(root);
        m.put(root, 1);
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