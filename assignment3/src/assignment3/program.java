package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;


public class program {

	public static void main(String[] args) {
		// 하이퍼 파라미터
		int map_size = 5;
		
		// 스택 초기화
		Stack<State> st = new Stack();
		
		for(int i = 0 ; i < map_size; i++){
			State new_s = new State(map_size,i);
			new_s.update_col_domain();
			st.push(new_s);
		}
		
		State current_state = st.pop();
		
		while(!current_state.isGoalState() && !st.isEmpty()){
			ArrayList<Integer> successor_locations = (ArrayList<Integer>) current_state.getSucessorDomains().clone();
			
			System.out.println("===================");
			System.out.print("Expand할수 있는 노드");
			for(int i = 0 ; i < successor_locations.size() ; i++){
				System.out.print(successor_locations.get(i) + " ");
			}
			
			System.out.println();
			current_state.printInfo();
			if(!current_state.canExpand()){
				System.out.println("공집합 도메인이 있어서 expand불가.");
			}
			
			
			for(int i = 0 ; i < successor_locations.size() ; i++){
				int successor_location = successor_locations.get(i).intValue();
				System.out.println(successor_location + "expand!");
				System.out.println("-----");
				System.out.println("부모 state 상태");
				current_state.printInfo();
				System.out.println("-----");
				State new_s = new State(current_state, successor_location);

				// 여기서 current_state의 col_list가 바뀜..ㅠㅠ.
				new_s.update_col_domain();
				st.push(new_s);
				System.out.println("푸시");
			}
			System.out.println("===================");
//			while(current_state.canExpand() && successor_iter.hasNext()){
//				int successor_location = successor_iter.next();
//				System.out.println(successor_location + "expand!");
//				State s = new State(current_state, successor_location);
//				st.push(s);
//			}
			System.out.println("----------------");
			current_state = st.pop();
		}
		
		if(current_state.isGoalState()){
			for(int i = 0 ; i < current_state.col_list.size() ; i++){
				System.out.println(current_state.col_list.get(i).assigned);
			}
		} else {
			System.out.println("스택이 비었습니다.");
		}
	}

}
