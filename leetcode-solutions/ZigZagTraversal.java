
//TC: O(N)
//SC: O(N)

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
         	List<List<Integer>> res = new ArrayList<>();
  	if(root==null) return res; 
  	Queue<TreeNode> q = new LinkedList<>();
  	q.add(root);
  	boolean leftToRight = true; 
  	while(!q.isEmpty())
  	{
  		int size = q.size();
  		ArrayList<Integer> currLevel = new ArrayList<>();
  		for(int i=0; i<size; i++)
  		{
  			TreeNode curr = q.poll();
  			if(leftToRight)
  			{
  				currLevel.add(curr.val);
  			}
  			else if(!leftToRight)
  			{
  				currLevel.add(0, curr.val);
  			}
            if(curr.left!=null)
            {
                q.add(curr.left);
            }
            if(curr.right!=null)
            {
                q.add(curr.right);
            }
  		}
  		res.add(currLevel);
  		leftToRight = !leftToRight; 
    }
    return res;  	
    }