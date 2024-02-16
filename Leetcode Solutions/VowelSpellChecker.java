// Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.

// For a given query word, the spell checker handles two categories of spelling mistakes:


// Capitalization: If the query matches a word in the wordlist (case-insensitive), 
// then the query word is returned with the same case as the case in the wordlist.

// Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel 
// individually, it matches a word in the wordlist (case-insensitive), then the query word is 
// returned with the same case as the match in the wordlist.


// For each word in the wordlist,
// get its the lower pattern and devowel pattern, (lowercase and without vowels)

// For each lower pattern, record the first such match to hashmap cap.
// For each vowel pattern, record the first such match to hashmap vowel.

// For each query,
// check if it's in the words set,
// check if there is a match in cap,
// check if there is a match in vowel,
// otherwise return "".

   public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> words = new HashSet<>(Arrays.asList(wordlist));
        HashMap<String, String> cap = new HashMap<>();
        HashMap<String, String> vowel = new HashMap<>();
        for (String w : wordlist) {
            String lower = w.toLowerCase(), devowel = lower.replaceAll("[aeiou]", "#");
            cap.putIfAbsent(lower, w);
            vowel.putIfAbsent(devowel, w);
        }
        for (int i = 0; i < queries.length; ++i) {
            if (words.contains(queries[i])) continue;
            String lower = queries[i].toLowerCase(), devowel = lower.replaceAll("[aeiou]", "#");
            if (cap.containsKey(lower)) {
                queries[i] = cap.get(lower);
            } else if (vowel.containsKey(devowel)) {
                queries[i] = vowel.get(devowel);
            } else {
                queries[i] = "";
            }
        }
        return queries;
    }