package n_queens_1;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import n_queens_1.*;

public class Program {

	public static void main(String[] args) {
		// 트리 expanding
		// expanding한 트리의 루트노드를 리턴받는다.
		TreeNode root = TreeNode.generateTree(5);
		
		// DFS탐색을 시작.
		System.out.println(">DFS");
		
		// fringe를 저장하기 위한 스택 인스턴스 생성.
		Stack DFS_fringes = new Stack();
		
		// 초기에 구성한 트리의 루트를 일단 스택에 넣는다.
		DFS_fringes.push(root);
		
		// 솔루션을 찾았는지 판별하는 boolean타입의 변수.
		boolean DFS_solution_found = false;
		
		// 시간측정 시작.
		long startTime = System.currentTimeMillis();   
		
		// 스택이 빌때까지 탐색.
		// 스택이 비어있다는 것은 솔루션이 없다는 의미.
		while(!DFS_fringes.empty()){
			// fringe에 들어있는 노드를 pop.
			TreeNode pop_node = (TreeNode)DFS_fringes.pop();
			
			// pop한 노드가 자식을 가지고 있는 경우
			if(pop_node.children.size() != 0){
				// 자식노드의 크기를 받아온다.
				int size = pop_node.children.size();
				
				// 자식노드를 하나씩 검사.
				for(int i = 0 ; i < size ; i++){
					// 자식노드를 가리키는 TreeNode변수.
					TreeNode expanding_node = pop_node.children.get(i);
					// child노드의 goalcheck통과한 것들만 스택에 추가.
					if(expanding_node.state.goalCheck()){
						// goalcheck를 통과한 노드를 스택에 push한다.
						DFS_fringes.push(expanding_node);
						
						// 해당 노드가 최하위 노드이면 솔루션 탐색 성공
						// 탐색을 goalcheck에 성공한 노드들만 따라 왔기 때문에 
						// leaf노드까지 왔다면 최종적인 목표를 달성했다고 볼 수 있다.
						if(expanding_node.isLeafNode()){
							System.out.print("Location : ");

							// 해당 노드의 퀸 위치를 출력하는 함수 호출.
							expanding_node.showQueenPosition();
							System.out.println("");
							
							// 솔루션 탐색 여부 플래그를 true로 변경.
							DFS_solution_found = true;
							break;
						}
					}
					
				}
			}
			// 솔루션을 찾았다면 더이상 진행하지 않고 나온다.
			if(DFS_solution_found) break;
		}
		// 시간측정 끝.
		long stopTime = System.currentTimeMillis();
		
		// 솔루션을 탐색하지 못한 경우 No solution출력.
		if(DFS_solution_found == false) System.out.println("No solution");
		
		// 경과시간.
		long collapsedTime = (stopTime - startTime);
		
		// 경과시간 출력.
		System.out.println("Time: " + collapsedTime + "ms");
		
		///////////////////////////////////////////////////////
		
		// BFS탐색 시작
		System.out.println(">BFS");
		
		// fringe를 담을 큐 인스턴스 생성.
		Queue<TreeNode> BFS_fringes = new LinkedList<TreeNode>();
		
		// 큐에 루트 노드를 넣는다.
		BFS_fringes.add(root);
		
		// 솔루션 탐색 여부 플래그를 false로 설정.
		boolean BFS_solution_found = false;
		
		// 시간측정 시작.
		startTime = System.currentTimeMillis();    
		
		// 큐가 비어있지 않는 한 루프문을 계속 수행
		// 큐가 빈다는 것은 솔루션을 찾지 못했다는 의미.
		while(BFS_fringes.size() != 0){
			// 큐에서 노드를 하나 꺼낸다.
			TreeNode removed_node = (TreeNode)BFS_fringes.remove();
			
			// 빠져나온 노드의 자식노드가 있으면 스택에 push
			if(removed_node.children.size() != 0){
				// 빠져나온 노드의 자식노드 갯수.
				int size = removed_node.children.size();
				
				// child노드의 갯수만큼 각 childe노드를 돌면서 체크.
				for(int i = 0 ; i < size ; i++){
					// child노드의 goalcheck통과한 것들만 스택에 추가.
					TreeNode expanding_node = removed_node.children.get(i);
					
					// child노드가 가지고 있는 state변수를 검사.
					if(expanding_node.state.goalCheck()){
						// goalcheck를 통과한 노드를 큐에 추가.
						BFS_fringes.add(expanding_node);
						
						// 해당 노드가 최하위 노드이면 솔루션 탐색 성공
						if(expanding_node.isLeafNode()){
							System.out.print("Location : ");

							// 솔루션에 해당하는 leaf노드 퀸 위치를 출력.
							expanding_node.showQueenPosition();
							System.out.println("");
							
							// 솔루션 탐색 여부 플래그를 true로 설정.
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
		
		// 솔루션 탐색 여부 플래그가 true가 아닌 경우 No solution 출력.
		if(BFS_solution_found == false) System.out.println("No solution");
		collapsedTime = (stopTime - startTime);

		// 경과시간 출력.
		System.out.println("Time: " + collapsedTime + "ms");
		
		///////////////////////////////////////////////////////
		
		// DFIS탐색을 시작.
		System.out.println(">DFIS");
		
		// DFIS탐색 결과를 담을 플래그 변수.
		boolean DFIS_solution_found = false;
		
		// 시간측정 시작.
		startTime = System.currentTimeMillis();
		
		// 제한 level을 설정하여 그 이상으로 tree탐색을 하지 못하게 한다.
		for(int level = 0 ; level < root.state.map.size(); level++){
			// fringe들을 담을 스택변수 선언.
			Stack DFIS_fringes = new Stack();
			
			// 스택에 root노드를 넣는다.
			DFIS_fringes.push(root);
			
			// 솔루션 탐색 여부 플래그를 false로 설정.
			DFIS_solution_found = false;
			
			// 스택이 비어있지 않는 동안 탐색을 계속 한다.
			while(!DFIS_fringes.empty()){
				// 스택에서 노드를 하나 뺀다.
				TreeNode pop_node = (TreeNode)DFIS_fringes.pop();

				// 빠져나온 노드의 자식노드가 있으면 스택에 push
				if(pop_node.children.size() != 0){
					// 자식노드의 사이즈를 받는다.
					int size = pop_node.children.size();
					
					// 자식노드 하나하나씩 돌면서 체크.
					for(int i = 0 ; i < size ; i++){
						// child노드의 goalcheck통과한 것들만 스택에 추가.
						TreeNode expanding_node = pop_node.children.get(i);
						
						// level제약조건을 걸어서 일정 level이상으로 search하지 않는다.
						if(expanding_node.state.goalCheck() && expanding_node.state.index_x <= level){
							// 조건을 통과한 노드를 스택에 추가한다.
							DFIS_fringes.push(pop_node.children.get(i));
							
							// 해당 노드가 최하위 노드이면 솔루션 탐색 성공
							if(expanding_node.isLeafNode()){
								System.out.print("Location : ");

								// 해당 노드의 퀸 위치를 출력.
								expanding_node.showQueenPosition();
								System.out.println("");
								
								// 솔루션 탐색 여부 플래그를 true로 설정.
								DFIS_solution_found = true;
								break;
							}
						}
						
					}
				}
				if(DFIS_solution_found) break;
			}
			if(DFIS_solution_found) break;
		}
		
		// 시간측정 끝.
		stopTime = System.currentTimeMillis();
		
		// 솔루션 탐색 여부 플래그가 false인 경우 No solution 출력.
		if(DFIS_solution_found == false) System.out.println("No solution");
		collapsedTime = (stopTime - startTime);

		// 경과 시간을 출력.
		System.out.println("Time: " + collapsedTime + "ms");
	}

}
