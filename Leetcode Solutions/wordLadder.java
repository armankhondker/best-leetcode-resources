// Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest 
// transformation sequence from beginWord to endWord, such that:

// 1. Only one letter can be changed at a time.
// 2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

// Input:
// beginWord = "hit",
// endWord = "cog",
// wordList = ["hot","dot","dog","lot","log","cog"]

// Output: 5

// Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.



//M is length of words input, N length of wordlist 
//TC: O(MxN) because finding out all transformations will take M iterations for each of the N words 
//SC: O(MxN) to store all M transformations of N words 

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int q=0; q < size; q++) {
                char[] cur = queue.poll().toCharArray();
                for (int i=0; i < cur.length; i++) {
                    char tmp = cur[i];
                    for (char chr='a'; chr <= 'z'; chr++) {
                        cur[i] = chr;  //replace current letter
                        String dest = new String(cur);
                        if (dict.contains(dest)) { //check if word is in dictionaru
                            if (dest.equals(endWord)) return level+1;  //we have reached end word 
                            queue.add(dest);   //add to queu
                            dict.remove(dest); //we dont want duplicates so remove this word 
                        }
                    }
                    cur[i] = tmp;
                }
            }
            level++; //onto next level in BFS traversal 
        }
        return 0; //cant find end word 
    }

//CAN IMPROVE ON THIS BY DOING BIDIRECTIONAL BFS!!!!
//Since we start with end word, we can run bfs from startWord and endWord
//we can check when the two meet at a word, and if they do return
//can improve runtime and space 