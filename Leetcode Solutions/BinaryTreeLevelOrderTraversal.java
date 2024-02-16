

//TC: O(N) to visit every node in the tree
//SC: O(N) because in worst case q will have to hold up to |n| nodes 
 public List<List<Integer>> levelOrder(TreeNode root) {
 	List<List<Integer>> res = new ArrayList<>();
 	if(root == null) return res; 

 	Queue<TreeNode> q = new LinkedList<>(); 
 	q.add(root);
 	while(!q.isEmpty())
 	{
 	
 		int size = q.size();
 		ArrayList<Integer> currentLevel = new ArrayList<>(); 
 		for(int i=0; i<size; i++)
 		{
 		    TreeNode curr = q.poll();
 		    currentLevel.add(curr.val);
 		    if(curr.left!=null){
 		    	q.add(curr.left);
 		    }
 		    if(curr.right!=null)
 		    {
 		    	q.add(curr.right); 
 		    }
 		}
 		res.add(currentLevel)

 	}

      
    return res; 
 
 }