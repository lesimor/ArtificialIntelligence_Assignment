package n_queens_1;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import n_queens_1.*;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 트리 expanding
		// expanding한 트리의 루트노드를 리턴받는다.
		TreeNode root = TreeNode.generateTree(7);
		
		// DFS탐색을 시작.
		System.out.println(">DFS");
		Stack DFS_fringes = new Stack();
		DFS_fringes.push(root);
		boolean DFS_solution_found = false;
		// 시간측정 시작.
		long startTime = System.currentTimeMillis();    
		while(!DFS_fringes.empty()){
			TreeNode pop_node = (TreeNode)DFS_fringes.pop();
			// 빠져나온 노드의 자식노드가 있으면 스택에 push
			if(pop_node.children.size() != 0){
				int size = pop_node.children.size();
				for(int i = 0 ; i < size ; i++){
					// child노드의 goalcheck통과한 것들만 스택에 추가.
					if(pop_node.children.get(i).state.goalCheck()){
						DFS_fringes.push(pop_node.children.get(i));
						// 해당 노드가 최하위 노드이면 솔루션 탐색 성공
						if(pop_node.children.get(i).isLeafNode()){
							System.out.print("Location : ");
							pop_node.children.get(i).showQueenPosition();
							System.out.println("");
							DFS_solution_found = true;
							break;
						}
					}
					
				}
			}
			if(DFS_solution_found) break;
		}
		// 시간측정 끝.
		long stopTime = System.currentTimeMillis();
		if(DFS_solution_found == false) System.out.println("No solution");
		long collapsedTime = (stopTime - startTime);
		System.out.println("Time: " + collapsedTime + "ms");
		
		
		// BFS탐색 시작
		System.out.println(">BFS");
		Queue<TreeNode> BFS_fringes = new LinkedList<TreeNode>();
		BFS_fringes.add(root);
		boolean BFS_solution_found = false;
		// 시간측정 시작.
		startTime = System.currentTimeMillis();    
		while(BFS_fringes.size() != 0){
			TreeNode removed_node = (TreeNode)BFS_fringes.remove();
			// 빠져나온 노드의 자식노드가 있으면 스택에 push
			if(removed_node.children.size() != 0){
				int size = removed_node.children.size();
				for(int i = 0 ; i < size ; i++){
					// child노드의 goalcheck통과한 것들만 스택에 추가.
					if(removed_node.children.get(i).state.goalCheck()){
						BFS_fringes.add(removed_node.children.get(i));
//						System.out.println("삽입!");
						// 해당 노드가 최하위 노드이면 솔루션 탐색 성공
						if(removed_node.children.get(i).isLeafNode()){
//							pop_node.children.get(i).showStatus();
//							System.out.println("솔루션 존재!!");
							System.out.print("Location : ");
							removed_node.children.get(i).showQueenPosition();
							System.out.println("");
							BFS_solution_found = true;
							break;
						}
					}
					
				}
			}
			if(BFS_solution_found) break;
		}
		// 시간측정 끝.
		stopTime = System.currentTimeMillis();
		if(BFS_solution_found == false) System.out.println("No solution");
		collapsedTime = (stopTime - startTime);
		System.out.println("Time: " + collapsedTime + "ms");
	}

}
