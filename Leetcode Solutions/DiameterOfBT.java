//key is the realize that the diameter will always pass through height left subtree + height right subtree + 1 being root node
//DOESNT necesarrily have to pass through the original root, just some root


// So although the longest path doesn't have to go through the root node, it has to pass the root node of 
// some subtree of the tree (because it has to be from one leaf node to another leaf node, otherwise 
// 	we can extend it for free). The longest path that passes a given node as the ROOT node 
// is T = left_height+right_height. So you just calculate T for all nodes and output the max T.

//RECURSIVE SOLUTION


//TC: O(N) to go through all the nodes 
//SC: O(N) for recursions stack 
class Solution {
int max = Integer.Mi;
public int diameterOfBT(Node root){
	maxDepth(root); 
	return max;  
}

//calculate total for each node, which is sum of max_lefet + max_right + 1  
private int maxDepth(Node root)
{
	if(root==null) return 0; 
	int left = maxDepth(root.left);
	int right = maxDepth(root.right);
	max=Math.max(max, left+right); //update max
	return Math.max(left,right) +1;   //THIS ONLY KEEPS TRACK OF HEIGHT OF NODE 
        //Let's calculate the depth of a node in the usual way: max(depth of node.left, depth of node.right) + 1
}
}


ANOTHER YOUTUBE SOLUTION, uses the following forumla, the diameter will be

 Diameter = Max ( leftHeight + rightHeight + 1, Max (leftDiameter, rightDiameter) )

 Either the max diameter passes through the root, or it is the max of left, right subtree!!

BAD PERFORMANCE DONT IMPLEMENT AS OPTIMAL!

//O(N*h) where h can be N in skewed tree so O(N^2) performance
public class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
       int dia = depth(root.left) + depth(root.right);
       int ldia = diameterOfBinaryTree(root.left);
       int rdia = diameterOfBinaryTree(root.right);
       return Math.max(dia,Math.max(ldia,rdia));
        
    }
    public int depth(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1+Math.max(depth(root.left), depth(root.right));
    }
    
}


ITERATIVE SOLUTION - EXACT SAME LOGIC, use POSTORDER traversal!!

//TC: O(N) to visit every node 
//SC: O(N)
class Solution {
    
   public int diameterOfBinaryTree(TreeNode root) {
        if( root == null){
            return 0;
        }
        if(root == null){
            return 0;
        }
        int overallNodeMax = 0;
        Stack<TreeNode> s = new Stack<>();
        Map<TreeNode,Integer> nodeCount = new HashMap<>(); //map to remember the nodeMax
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.peek();  //need to peek because we dont wana process node until left/right are processed
            if(node.left != null && !nodeCount.containsKey(node.left)){
              s.push(node.left);
            }else if(node.right!=null && !nodeCount.containsKey(node.right)){
                 s.push(node.right);
            }else {
                TreeNode curr = s.pop();
                int leftMax = nodeCount.getOrDefault(curr.left,0);
                int rightMax = nodeCount.getOrDefault(curr.right,0);
                int nodeMax = 1 + Math.max(leftMax,rightMax); 
                nodeCount.put(curr,nodeMax);
                
                overallNodeMax = Math.max(overallNodeMax,leftMax + rightMax );
            }
            
        }
        return overallNodeMax;
        
    } 
}