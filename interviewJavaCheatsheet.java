/******************** String, char, Integer conversion ***************/
[String to int]:     Integer.parseInt(s);        // return int primitive
[String to Integer]: Integer.valueOf(s); 	     // return an Integer Object
[int to String]:     String.valueOf(int)
[char[] to String]:  String str = new String(chArray);
[list to array]:     String[] arr = list.toArray(new String[list.size()]);
[array to list]:     List<String> list = Arrays.asList(arr); 

/********************** String ***************************/
String s = “a*b*c”;
s.charAt(i);
s.length();
s.substring(0, 1);            // [0, 1)
s.substring(1);               //[1, s.length)
s.equals(“b”);
s.compareTo(“b*c*d”);         // return -1 because s comes first in lexicographical order
s.trim(); 	                  // remove tailing and padding spaces
s.indexOf(“a”);               // return first index of substring “a”   indexOf(substring)
s.indexOf(‘a’, 2);            // indexOf(int ch, fromIndex), indexOf(String str, fromIndex)
s.lastIndexOf(‘a’);           // also we can use s.lastIndexOf(String str)
s.replaceAll(substr, target); // replace all substr to target in s

char[] arr = s.toCharArray();
String[] arr = s.split("\\*") // when delimiter is '*'
String[] arr = s.split("\\.") // when delimiter is '.'
String res = String.join(String delimiter, List<String> data); // use the delimiter to concatenate the string in data.
Objects.equals(Object a, Object b); // (1)if both parameters are null return true
                                    // (2)if exactly one parameter is null return false
                                    // (3)return the result of invoking the equals() method of the first parameter passing it the second parameter
                                    // This behaviour means it is "null safe".
                                    
/********************** StringBuilder ***************************/
StringBuilder sb = new StringBuilder();
sb.append(“a”);
sb.insert(0, “a”);            // sb.insert(int offset, char c) or sb.insert(offset, str)
sb.deleteCharAt(int index);
sb.reverse();
sb.toString();
sb.length();                  // return the number of characters in sb, similar to str.length()

/********************** Array ***************************/
int[] arr = new int[10];
Arrays.sort(arr);
Arrays.fill(arr, -1);           // initialize all array elements with value -1
public void helper(int[] nums);
helper(new int[]{1, 2});        // initialize array in method

/********************** HashMap (TreeMap), HashSet (TreeSet)***********************/
HashMap<Character, Integer> map = new HashMap<Character, Integer>();
map.put('c', 1);
map.get('c');
map.getOrDefault(key, defaultValue);                         // if key exists return value, else return default value
map.remove(‘c’);                                             // remove key and its value
map.computeIfAbsent(key, mappingFunction);                   // if key exists return value, else create a value by mappingFunction
map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
map.computeIfAbsent(key, k -> new ArrayList<>()).add(val);   // RECOMMENDED !!!
if (map.containsKey('c')) {                                  // check if key exists
}
if (map.containsValue(1)) {                                  // check if value exists
}
for (Character d : map.keySet()) {                           // traverse key set
}
for (Integer i : map.values()) {                             // traverse value set
}
for(Map.Entry<Character, Integer> entry : map.entrySet()){   // traverse key-value pair
    entry.getKey();
    entry.getValue();
}
map.forEach((k,v) -> System.out.println("key: "+k+" value:"+v)); // traverse key-value pair using lamda expression to print out info

map.isEmpty();
map.size();
HashSet<Integer> set = new HashSet<Integer>();
set.add(10);
set.remove(10);
if(set.contains(10)){
}
set.size();
set.isEmpty();
setA.retainAll(setB); // setA keeps the intersection of original setA and setB;
setB.removeAll(setC); // Removes from this set all of its elements that are contained in the specified collection (setB - setC)
setC.addAll(setD);    // union two sets of setC and setD
setC.containsAll(setD); // Returns true if this set contains all of the elements of specified collection
Object[] arr = setA.toArray(); // Returns an array containing all of the elements in this set.

TreeMap<Integer, String> map = new TreeMap<>();             // key’s ascending order (default)
map.put(2, “b”);
map.put(1, “a”);
map.put(3, “c”);
for(String str : map.values())                              // traverse in “a” “b” “c” order
for(Integer num : map.keySet())			                    // traverse in  1, 2, 3 order

TreeMap<String, Integer> treeMap = new TreeMap<>();             // sorted in lexicographical order
TreeMap<Integer, Integer> treeMap = new TreeMap<>(Collections.reverseOrder()); // descending order

treeMap.lowerKey(k);                                        // return the max key that < k 
treeMap.floorKey(k);                                        // return the min key that >= k
treeMap.higherKey(k);                                       // return the min key that > k 
treeMap.ceilingKey(k);                                      // return the max key that <= k
treeMap.firstKey();                                         // returns the first (lowest) key currently in this map.
SortedMap<K,V> portionOfTreeMap = treeMap.headMap(K toKey); // Returns a view of the portion of this map whose keys are strictly less than toKey.
NavigableMap<K,V> map = treeMap.headMap(toKey, true);       // Returns a view of the portion of this map whose keys are less than or equal to toKey.

Set<Integer> treeSet = new TreeSet<>();                     // sort in ascending order by default
treeSet.lower(Integer e);	                                // return greatest element that is < e, or null if no such element
treeSet.floor(Integer e);	                                // return greatest element that is <= e, or null if no such element
treeSet.ceiling(Integer e);                                 // return smallest element that is >= e, or null if no such element
treeSet.higher(Integer e);                                  // return smallest element that is > e, or null if no such element
treeSet.first();                                            // return the first element in the treeset (if min set, return minimum element)
treeSet.last();                                             // return the last element in the treeset

/********************** LinkedHashMap, LinkedHashSet *************/
Map<Integer,String> map = new LinkedHashMap<>();
map.put(1, "first");
map.put(2, "second");
map.put(3, "third");           
for(Map.Entry<Integer,String> entry : map.entrySet())
    System.out.println(entry.getKey(), entry.getValue());   // print order: 1, 2, 3
Set<Integer> set = new LinkedHashSet<>();

/********************** List, ArrayList, LinkedList *************/
List<Integer> list = new ArrayList<>();
list.add(14);
list.add(0, 10);                			            // list.add(int index, int value);
list.get(int index);
list.remove(list.size() - 1);
list.set(int index, int val);                     // replaces element at index and returns original
list.indexOf(Object o);                           // return first index of occurrence of specified element in the list; -1 if not found
list.subList(int fromIndex, int toIndex);         // return a sublist within range [fromIndex, toIndex)
Collections.sort(list);				                    // ascending order by default
Collections.sort(list, Collections.reverseOrder());         // descending order
Collections.sort(list, new Comparator<Integer>() {
   @Override
   public int compare(Integer o1, Integer o2) {             // the Integer can be any Object instead
   return o1 ‐ o2;// 0‐>1
   // return o2‐o1; 1‐>0
   }
});
list.forEach(num -> system.out.println(num));   // traverse the list and print out by using lamda function

/********************** Stack, Queue, PriorityQueue, Deque ***********************/
Stack<Integer> stack = new Stack<Integer>();
stack.push(10);
stack.pop();
stack.peek();
stack.isEmpty();
stack.size();
Queue<Integer> q = new LinkedList<Integer>();
q.offer(10);    					                                          // q.add() is also acceptable
q.poll();
q.peek();
q.isEmpty();
q.size();
PriorityQueue<Integer> pq = new PriorityQueue<>();                            // minimum Heap by default
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  // change to maximum Heap
pq.add(10);
pq.poll();
pq.peek();
pq.isEmpty();
pq.size();
class Node implements Comparable<Node>{
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Node that){
        return this.x - that.x;                            // ascending order / minimum Heap
        // return that.x - this.x;			               // descending order / maximum Heap
    }
}
PriorityQueue<Node> pq = new PriorityQueue<>();

import java.util.Deque; 
Deque<Integer> dq = new LinkedList<Integer>();    // Deque is usually used to implement monotone queue
dq.addFirst();  //  dq.offerFirst();
dq.addLast();   //  dq.offerLast();
dq.peekFirst(); // 
dq.peekLast();
dq.pollFirst(); //  dq.removeFirst();
dq.pollLast();  //  dq.removeLast();

/************************** Random method *****************************/
Random rand =new Random();    // initialize Random object
int i = rand.nextInt(100);    // generate random number in [0, 100)
float f = rand.nextFloat();   // generate float value in [0, 1)
double d = rand.nextDouble(); // generate double value in [0.0, 1.0)

/************************** Math *****************************/
Math.pow(double x, double y); // return x^y 
Math.round(float a);          // returns the closest int to the argument
Math.abs(int/float/doubld val);
Math.sqrt();
Math.sin(double rad);         // input is rad not angle
Math.PI;
Math.E;

/************************** Collections/Object *****************************/
Collections.nCopies(100, new Object[]{true});// return an immutable list which contains n copies of given object
getClass()                                   // Returns the runtime class of this {@code Object}
Collections.singletonList()                  // use it to replace Arrays.asList() when there is only one element
Collections.unmodifiableSet(new HashSet<>()) // returns an unmodifiable view of the specified set. Note that, changes in specified set will be reflected in unmodifieable set. 
                                             // Also, any modification on unmodifiableSet is not allowed, which triggers exception.
Collections.swap(List, int i, int j);        // swap the ith and jth element in list 
   
/********************* std input/output  file read/write ************************/
import java.io.*;
import java.net.*;
Scanner in = new Scanner(System.in);
int n = in.nextInt();
while(in.hasNext()){
    String str = in.nextLine();
}

String inputfile="in.txt";
String outputfile="out.txt";
try
{
    BufferedReader in = new BufferedReader(new FileReader(inputfile));
    line = in.readLine();
    while (line!=null)
    {
        // do something with line
        line=in.readLine();
    }
    in.close();              // close the file
} catch (IOException e)
{
    e.printStackTrace();
}

try{
    BufferedWriter out = new BufferedWriter(new FileWriter(outputfile));
    for(String str : map.keySet()){
         out.write(str + " " + map.get(str));
         out.newLine();
    }
    out.close();           // close the file
}catch (IOException e)
{
    e.printStackTrace();
}

URL wordlist = new URL("http://foo.com/wordlist.txt");
BufferedReader in = new BufferedReader(new InputStreamReader(wordlist.OpenStream()));
String inputLine = null;
List<String> res = new ArrayList<>();
while((inputLine = in.readLine()) != null){
    res.add(inputLine);
}
