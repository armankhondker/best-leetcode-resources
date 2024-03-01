// // Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

// If two nodes are in the same row and column, the order should be from left to right.

// Examples 1:

// Input: [3,9,20,null,null,15,7]

//    3
//   /\
//  /  \
//  9  20
//     /\
//    /  \
//   15   7 

// Output:

// [
//   [9],
//   [3,15],
//   [20],
//   [7]
// ]


//TC: O(n) since peforming BFS over all nodes in the tree
//SC: O(n) queue could grow max size of number of nodes in tree

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res; 
        
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();  // (col , list of nodes in col)
        
        int min=0;
        int max=0; 

        q.add(root);
        cols.add(0);
        
        while(!q.isEmpty())
        {
            TreeNode node = q.poll();
            int col = cols.poll();
            
            if(!map.containsKey(col))
            {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val); 
            
            if(node.left!=null)
            {
                q.add(node.left);
                cols.add(col-1);
                min=Math.min(min, col-1);
                
            }
            if(node.right!=null)
            {
                q.add(node.right);
                cols.add(col+1);
                max=Math.max(max, col+1); 
            }
            
        }
        
        for(int i=min; i<=max; i++)
        {
            res.add(map.get(i)); 
        }
        
        return res; 
    }
}

IF solution requires them to be sorted if in same level

class Pair{
        TreeNode node;
        int x;  //horizontal
        int y;  //depth
        Pair(TreeNode n, int x, int y)
        {
            node = n;
            this.x = x;
            this.y = y;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Map<Integer, List<Pair>> map = new HashMap<>(); //x -> list (some nodes might have same y in the list)
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0, 0));
        int min = 0, max = 0;
        while(!q.isEmpty())
        {
            Pair p = q.remove(); 
            
            min = Math.min(min, p.x);
            max = Math.max(max, p.x);
            
            if(!map.containsKey(p.x)) 
                map.put(p.x, new ArrayList<>());
            map.get(p.x).add(p);
            
            if(p.node.left!=null) q.add(new Pair(p.node.left, p.x-1, p.y+1));
            if(p.node.right!=null) q.add(new Pair(p.node.right, p.x+1, p.y+1));
        }        

        for(int i=min; i<=max; i++)
        {
            Collections.sort(map.get(i), new Comparator<Pair>(){
                public int compare(Pair a, Pair b)
                {
                    if(a.y==b.y) //when y is equal, sort it by value
                        return a.node.val - b.node.val;
                    return 0; //otherwise don't change the order as BFS ganrantees that top nodes are visited first
                }
            });
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<map.get(i).size(); j++)
            {
                list.add(map.get(i).get(j).node.val);
            }
            lists.add(list);
        }
        return lists;   
    }