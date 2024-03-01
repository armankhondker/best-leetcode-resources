//Design an algorithm to serialize and deserialize a binary search tree. 
//There is no restriction on how your serialization/deserialization algorithm should work. 
//You just need to ensure that a binary search tree can be serialized to a string 
//and this string can be deserialized to the original tree structure.


//Intuiton, use BFS/DFS

//TO SERIALIZE, USE PREORDER TRAVERSAL aka root-left-right, and put the tree into a STRING!!
//TO DESERIALIZE, use a queue and do bfs traversal 

//TC: O(N) to serialize and deseralze by going through all of the tree, N is number of nodes in tree 
//SC: O(N) to store the tree as a string 


NOTE, USE PROPERTY OF BST to check whether we need to add null!!!, thats the difference between regular bt serialization

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode deserialize(Queue<String> q, int lower, int upper) {
        if (q.isEmpty()) return null;
        String s = q.peek();
        int val = Integer.parseInt(s);
        if (val < lower || val > upper) return null;
        q.poll();
        TreeNode root = new TreeNode(val);
        root.left = deserialize(q, lower, val);
        root.right = deserialize(q, val, upper);
        return root;
    }
}
