Pair<TreeNode, Integer> p = new Pair<>();
key = p.getKey();
value = p.getValue();

Collections.sort(toSort, (a,b)->b-a); 
Collections.reverse(array);

double total = 10; 
Math.random()*total 

HashMap<Integer, Integer> map = new HashMap<>();
map.getOrDefault(i, 0);
for(int x: map.keySet()){
}

String s = "arman khondker"
String [] sa = s.split("a") //[rm, n khondker]

Deque<Integer> q = new ArrayDeque<>(); //can be used as both a stack and a queue
q.removeFirst() //removes the first element
q.removeLast(); removes the  last element
q.pollFirst();
q.pollLast();
*** q.poll() //it treats as queue (not a stack) and polls the first element!

String Concatenation
one line concatenation with "+" and StringBuilder.append() generate exactly the same bytecode.
exceptions :
multithreaded environment : StringBuffer
concatenation inside of loops : StringBuilder/StringBuffer

int[][] dirs = new int[]{{-1,0}, {1, 0}, {0, 1}, {0,-1}}       

throw new IllegalArgumentException("Invalid input as p and q are guaranteed to exist");

String [] split = path.split("/")


if(arr[1].equals("start")) //STRING COMPARISON USE .equals

Integer.parseInt(s); //to convert from STRING to int. valueOf(s) is used to convert to Integer



Algorithms

QuickSort (uses QuickSelect method, Java uses under the hood)
TC: O(N) in average case, O(N^2) worst case when we select the pivot in bad position
SC: O(logN) 

Morris Traversal
can traverse a tree in O(N) time

Union Find
an algorithm for finding connected components in a graph