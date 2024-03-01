// Given a set of points in the xy-plane, determine the minimum area of a rectangle formed 
// from these points, with sides parallel to the x and y axes.

// If there isn't any rectangle, return 0.


// I would like to solidify my understanding.
// Basically, in your approach, we are first of all finding two points which are possible diagonals of a rectangle.
// Then, we check if we are able to find the other two diagonal points. If they exist, we found a rectangle.

class Solution {
    public int minAreaRect(int[][] points) {
               Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new HashSet<>());
            }
            map.get(p[0]).add(p[1]);
        }
        int min = Integer.MAX_VALUE;
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                if (p1[0] == p2[0] || p1[1] == p2[1]) { // if have the same x or y
                    continue;
                }
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) { // find other two points
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}