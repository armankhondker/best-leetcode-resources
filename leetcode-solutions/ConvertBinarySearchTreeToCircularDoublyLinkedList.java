Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-
linked list. For a circular doubly linked list, the predecessor of the first element is the last element, 
and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree node 
should point to its predecessor, and the right pointer should point to its successor. You should return 
the pointer to the smallest element of the linked list.


//TC: O(N) to visit every node exactly once for DFS
//SC: O(N) for stack space

USE A PREORDER traversal on the graph , through DFS, ITERATIVELY 
OUR PROCESSING STEP OF PREORDER TRAVERSAL, will be to connect the nodes like a doubly linked list 

class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        Node start = root;   //THE NODE WE WILL RETURN IN THE END 
        while (start.left != null) {
            start = start.left;
        }
        Node prev = null;
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (prev != null) {
                prev.right = cur;
                cur.left = prev;
            }
            prev = cur;
            cur = cur.right;
        }

        //connect head and tail 
        start.left = prev;
        prev.right = start;

        return start;
    }
}



//RECURSIVE SOLUTION!!! Dont implement unless asked 

Node prev = null;
public Node treeToDoublyList(Node root) {
    if (root == null) return null;
    Node dummy = new Node(0, null, null);
    prev = dummy;
    helper(root);
    //connect head and tail
    prev.right = dummy.right;
    dummy.right.left = prev;
    return dummy.right;
}

private void helper (Node cur) {
    if (cur == null) return;
    helper(cur.left);
    prev.right = cur;
    cur.left = prev;
    prev = cur;
    helper(cur.right);
}