package assignment3;

import java.util.ArrayList;

public class Node {
	// 노드가 가질 수 있는 값의 도메인.
	public ArrayList<Integer> domain;

	// 할당된 값.
	public int assigned;
	
	// 부모 노드.
	public Node parent;
	
	// 트리에서의 깊이
	
	
	public Node(int size, ArrayList<Integer> _domain){
		// 도메인 ArrayList타입 초기화.
		this.domain = _domain;

		// 아직 할당되지 않은 경우 -1로 초기화.
		this.assigned = -1;	
		
		// 체스판 사이즈만큼 0부터 N까지 연속된 정수값으로 배열값을 만든다.
		for(int i = 0 ; i < size ; i++){
			domain.add(i);
		}
	}
	public void assign(int var){
		this.assigned = var;
	}
	
}
