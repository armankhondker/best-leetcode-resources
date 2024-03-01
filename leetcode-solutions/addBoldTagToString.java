// Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to 
// wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together
//  by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need
//   to combine them.

// Example 1:
// Input: 
// s = "abcxyz123"
// dict = ["abc","123"]
// Output:
// "<b>abc</b>xyz<b>123</b>"
// Example 2:
// Input: 
// s = "aaabbcc"
// dict = ["aaa","aab","bc"]
// Output:
// "<b>aaabbc</b>c"


INTUTION, very similar to merge intervals!!!

Consider you have string
s = "aaabbcc"
dict = ["aaa","aab","bc"]

   you find the index of each string in dict, conver to an interval, you will get
   
   [[0, 3], [1, 4], [4, 6]]
     aaa     aab      bc
   then combine these intervals
   
   Ater merged, we got [0,6], so we know "aaabbc" needs to be surrounded by tag!



// Here's my attempt at determining the complexity.

// 1. Parsing S based on dict -> O( len(S) * size of word(dict) * dict.length) and is likely the most 
// expensive operation
// 2. merge -> Sorting intervals, when there are N intervals , (N = len(S)) -> O(n* log n)
// 3. Reading S back -> O(N)

	class Interval {
		int start, end;
		public Interval(int s, int e) {
			start = s;
			end = e;
		}
		
		public String toString() {
			return "[" + start + ", " + end + "]" ;
		}
	}


   public String addBoldTag(String s, String[] dict) {
        List<Interval> intervals = new ArrayList<>();
        for (String str : dict) {
        	int index = -1;
        	index = s.indexOf(str, index);
        	while (index != -1) {
        		intervals.add(new Interval(index, index + str.length()));
        		index +=1;
        		index = s.indexOf(str, index);
        	}
        }
        
        intervals = merge(intervals);
        int prev = 0;
        StringBuilder sb = new StringBuilder();
        for (Interval interval : intervals) {
        	sb.append(s.substring(prev, interval.start));
        	sb.append("<b>");
        	sb.append(s.substring(interval.start, interval.end));
        	sb.append("</b>");
        	prev = interval.end;
        }
        if (prev < s.length()) {
        	sb.append(s.substring(prev));
        }
        return sb.toString();
    }

	
	public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        List<Interval> res = new ArrayList<>();
        for (Interval i : intervals) {
            if (i.start <= end) {
                end = Math.max(end, i.end);
            } else {
                res.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }