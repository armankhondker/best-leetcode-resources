// Given an array of strings products and a string searchWord. We want to design a system that suggests at most 
// three product names from products after each character of searchWord is typed. Suggested products should have 
// common prefix with the searchWord. If there are more than three products with a common prefix return the three 
// lexicographically minimums products.

// Return list of lists of the suggested products after each character of searchWord is typed


//Implement a trie to do prefix search
//use a heap to keep the top 3 most popular elements 

// Complexity depends on the process of building Trie and the length of searchWord. For each Trie Node, 
// sorting suggestion List involving comparing String, hence cost time O(m), but space cost only O(1) due to
//  suggestion List save only String referrence. Therefore,
// Time C: O(m * m * n + L), 
// Space C: O(m * n + L), where m = average length of products, n = products.length, L = searchWord.length().

class Solution {
   class Trie {
        Trie[] sub = new Trie[26];
        LinkedList<String> suggestion = new LinkedList<>();
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        for (String p : products) { // build Trie.
            Trie t = root;
            for (char c : p.toCharArray()) { // insert current product into Trie.
                if (t.sub[c - 'a'] == null)
                    t.sub[c - 'a'] = new Trie();
                t = t.sub[c - 'a'];
                t.suggestion.offer(p); // put products with same prefix into suggestion list.
                Collections.sort(t.suggestion); // sort products.
                if (t.suggestion.size() > 3) // maintain 3 lexicographically minimum strings.
                    t.suggestion.pollLast();
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (char c : searchWord.toCharArray()) { // search product.
            if (root != null) // if current Trie is NOT null.
                root = root.sub[c - 'a'];  //'a' - 'a' would give you 0. allows us to check index in array 
            ans.add(root == null ? Arrays.asList() : root.suggestion); // add it if there exist products with current prefix.
        }
        return ans;
    }   
}