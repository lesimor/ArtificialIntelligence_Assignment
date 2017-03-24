package n_queens_1;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

import n_queens_1.*;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 트리 expanding
		// expanding한 트리의 루트노드를 리턴받는다.
		TreeNode root = TreeNode.generateTree(4);
		
		// DFS탐색을 시작.
		Stack DFS_fringes = new Stack();
		DFS_fringes.push(root);
		while(!DFS_fringes.empty()){
			TreeNode pop_node = (TreeNode)DFS_fringes.pop();
			// 빠져나온 노드의 자식노드가 있으면 스택에 push
			if(pop_node.children.size() != 0){
				int size = pop_node.children.size();
				for(int i = 0 ; i < size ; i++){
					// child노드의 goalcheck통과한 것들만 스택에 추가.
					if(pop_node.children.get(i).state.goalCheck()){
						DFS_fringes.push(pop_node.children.get(i));
						System.out.println("삽입!");
						// 해당 노드가 최하위 노드이면 솔루션 탐색 성공
						if(pop_node.children.get(i).isLeafNode()){
							pop_node.children.get(i).showStatus();
							System.out.println("솔루션 존재!!");
							break;
						}
					}
					
				}
			}
		}
		System.out.println("The end");
	}

}
