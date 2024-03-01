


public List<List<Integer>> pathSumII(Node root, int sum)
{
	List<List<Integer>> paths = new ArrayList<>();
	findPaths(root, sum, new ArrayList<Integer>(), paths);
	return paths; 
}

public void findPaths(Node root, int sum, List<Integer> current, List<List<Integer>> paths)
{
	if(root == null) return; 
	current.add(root.val);
	if(sum-root.val ==0 && root.right == null && root.left ==null){
		paths.add(current);
		return; 
	}
	findPaths(root.left, sum-root.val, new ArrayList<Integer>(current), paths);
	findPaths(root.right, sum-root.val, new ArrayList<Integer>(current), paths);

}