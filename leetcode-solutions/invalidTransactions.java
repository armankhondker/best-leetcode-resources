
// A transaction is possibly invalid if:

// 1. the amount exceeds $1000, or;
// 2. if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
// Each transaction string transactions[i] consists of comma separated values representing the name, time 
// (in minutes), amount, and city of the transaction.

// Given a list of transactions, return a list of transactions that are possibly invalid.  You may return 
// the answer in any order.

 
// Example 1:

// Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
// Output: ["alice,20,800,mtv","alice,50,100,beijing"]
// Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
// Example 2:

// Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
// Output: ["alice,50,1200,mtv"]


1. create a data structure to hold the transcations
2. create a map to store each person and all their transcations
3. CREATE node class to hold all data of a transaction

//TC: O(N^2) to loop over all the combinations of the transcations 
//SC: O(N) where n is number of transcations to store in the map and boolean array of all transcations size
    public List<String> invalidTransactions(String[] transactions) {

        HashMap<String, List<Node>> map=new HashMap<>();  //to hold name of person, to all transcation data
        boolean[] invalid=new boolean[transactions.length]; //array to hold all the invalid transcations
        for(int i=0;i<transactions.length;i++) {
            String[] cur=transactions[i].split(",");
            if(!map.containsKey(cur[0])) map.put(cur[0], new ArrayList<>());
            map.get(cur[0]).add(new Node(cur[1], cur[2], cur[3], i));
        }
        for(List<Node> l : map.values()) {
            Collections.sort(l, new Comparator<Node>() {
                public int compare(Node a, Node b) {
                    return a.time-b.time;
                }
            });
            for(int i=0;i<l.size();i++) {
                for(int j=i+1;j<l.size()&&l.get(j).time<=l.get(i).time+60;j++) {
                    if(!l.get(i).city.equals(l.get(j).city)) {
                        invalid[l.get(i).index]=true;
                        invalid[l.get(j).index]=true;
                    } 
                }
                if(l.get(i).amount>1000) invalid[l.get(i).index]=true;
            }
        }
        List<String> res=new ArrayList<>();
        for(int i=0;i<invalid.length;i++) {
            if(invalid[i]) res.add(transactions[i]);
        }
        return res;
    }

public class Node {
    int time;
    int amount;
    String city;
    int index;
    Node(String time, String amount, String city, int index) {
        this.time=Integer.parseInt(time);
        this.amount=Integer.parseInt(amount);
        this.city=city;
        this.index=index;
    }
}