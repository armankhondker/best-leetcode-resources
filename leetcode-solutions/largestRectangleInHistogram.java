
// Given n non-negative integers representing the histogram's bar height 
// where the width of each bar is 1, find the area of largest rectangle in
// the histogram.

 

// Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

// The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

// Example:

// Input: [2,1,5,6,2,3]
// Output: 10


Firstly, we need to take into account the fact that 
the height of the rectangle formed between any two bars will always be 
limited by the height of the shortest bar lying between them 


BRUTE FORCE: 

Consider every possible pair of bars and finding the area of the 
rectangle formed between them using the height of the shortest bar lying between them as the 
height and the spacing between them as the width of the rectangle. We can thus, find the required
rectangle with the maximum area.

TC: O(N^3)
SC: O(1)

public int largestRectangleArea(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                int minheight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++)   //k loop to determine the min height between i and j
                    minheight = Math.min(minheight, heights[k]);
                maxarea = Math.max(maxarea, minheight * (j - i + 1));
            }
        }
        return maxarea;
    }


Better Brute FORCE
Instead of taking every possible pair and then finding the bar of minimum height 
lying between them everytime, we can find the bar of minimum height for current pair by 
using the minimum height bar of the previous pair.

TC: O(N^2)
SC: O(1)

public int largestRectangleArea(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minheight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minheight = Math.min(minheight, heights[j]);
                maxarea = Math.max(maxarea, minheight * (j - i + 1));
            }
        }
        return maxarea;
    }



BEST SOLUTION - one pass using a stack (stricly increasing stack, MONTONIC STACK)

MAIN IDEA IS TO MAINTAIN A STRICLY INCREASING STACK

Idea is, we will consider every element a[i] to be a candidate for the area calculation. 
That is, if a[i] is the minimum element then what is the maximum area possible for all such rectangles?  
We can easily figure out that it's a[i]*(R-L+1-2) or a[i] * (R-L-1), where a[R] is first subsequent                                   
element(R>i) in the array just smaller than a[i], similarly a[L] is first previous element just 
smaller than a[i]. makes sense? (or take a[i] as a center and expand it to left and right and stop
 when first just smaller elements are found on both the sides). But how to implement it efficiently?

We add the element a[i] directly to the stack if it's greater than the peak element (or a[i-1] ),                                       
because we are yet to find R for this. Can you tell what's L for this? Exactly, it's just the previous
 element in stack. (We will use this information later when we will pop it out).

What if we get an element a[i] which is smaller than the peak value, it is the R value for 
all the elements present in stack which are greater than a[i]. Pop out the elements greater 
than a[i], we have their R value and L value(point 2). and now push a[i] and repeat..


There are 3 critical points to understand the stack approach.

1. the stack is used to stored the index, because (1. there could be duplicate heights)

2. when we pop the stack, the heights[stack.pop()] is monotonically decreasing, and therefore we
 can treat the original index (i - 1) as one anchor point to calculate the width.

3. after we loop thru each and every item in the array (i.e. heights), if the stack.peek() != -1, 
then we can treat the last item in the array as one anchor point, and treat st.Peek() -1 
(after popping once) as the other anchor point to calculate the width.



TC: O(N) to go through the heights array one time
SC: O(N) for the stack

public int largestRectangleArea(int[] heights) {
        Stack < Integer > stack = new Stack <>(); //stack of indexes 
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1)); //for width calculation
            stack.push(i);
        }
        //WE NOW HAVE A STRICTLY INCREASING STACK, and can build the areas 
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return maxarea;
    }