
BFS!!

import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;
public class MinOperationsToGetNum
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int target = in.nextInt();
		System.out.println(getMinOperations(target));
	}
	
	private static int getMinOperations(int target)
	{
		if(target == 1)
			return 0;
		Set<Integer> visited = new HashSet<>();
		Deque<Integer> q = new ArrayDeque<>();
		q.add(1); visited.add(1);
		int level = -1, size = 0, multiply = 0, divide = 0,curr = 0;
		while(!q.isEmpty())
		{
			++level;
			size = q.size();
			for(int i = 0 ; i < size ; ++i)
			{
				curr = q.remove();
				if(curr == target)
					return level;
				multiply = curr * 2;
				divide = curr / 3;
				if(visited.add(multiply))
					q.add(multiply);
				
				if(visited.add(divide))
					q.add(divide);
			}
		}	
		return -1;	
	}

}