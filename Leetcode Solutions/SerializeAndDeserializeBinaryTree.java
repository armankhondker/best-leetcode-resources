//Design an algorithm to serialize and deserialize a binary search tree. 
//There is no restriction on how your serialization/deserialization algorithm should work. 
//You just need to ensure that a binary search tree can be serialized to a string 
//and this string can be deserialized to the original tree structure.


//Intuiton, use BFS/DFS

//TO SERIALIZE, USE PREORDER TRAVERSAL aka root-left-right, and put the tree into a STRING!!
//TO DESERIALIZE, use a queue and do bfs traversal 

//TC: O(N) to serialize and deseralze by going through all of the tree, N is number of nodes in tree 
//SC: O(N) to store the tree as a string 

https://leetcode.com/problems/serialize-and-deserialize-bst/discuss/177617/the-General-Solution-for-Serialize-and-Deserialize-BST-and-Serialize-and-Deserialize-BT


NOTE, difference between this one and serialze BST is that we have to use "NULL" to check whether to 
add null, rather than the BST property! 


//TC: O(N) for both serialize/deserialize, where n is number of nodes, because we visit them all
//SC: O(N) for recusrive stack, for both serialize/deserialize, in worst case with unbalanced tree

public class Codec {

  private static final String SPLITER = ",";
    private static final String NULL = "N";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder st = new StringBuilder();
        serialize(root, st);
        return st.toString();
    }  
    public void serialize(TreeNode root, StringBuilder st) {
        if (root == null) st.append(NULL + SPLITER);
        else {
            st.append(root.val + SPLITER);  //need to add the node we are on to answer!
            serialize(root.left, st);  //then recursively call this function onto the left and right subtree 
            serialize(root.right, st);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();    //need to use a Queue because we need to derailzie in order 
        queue.addAll(Arrays.asList(data.split(SPLITER)));  //populate queue 
        return deseralize(queue);
    }
    public TreeNode deseralize(Queue<String> queue) {
        String cur = queue.poll();
        if (cur.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.left = deseralize(queue);
        root.right = deseralize(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));