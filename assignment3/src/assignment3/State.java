package assignment3;

import java.util.ArrayList;
import java.util.Iterator;


public class State {
	// Col 인스턴스 배열, 사이즈->체스판의 크기.
	public ArrayList<Col> col_list;
	// 현재 state의 깊이. -> expand되는 state노드는 현재 노드 depth + 1 
	public int depth;
	// 체스판의 사이즈.
	public int size;
	// 부모 노드.
	public State parent;
	// 현재 state에 할당된 값.
	public int assigned;
	
	State(int _size, int _assigned){
		this.col_list = new ArrayList<Col>();
		this.size = _size;
		this.depth = 0;
		this.assigned = _assigned;
		this.parent = null;
		col_list.add(new Col(_size, 0, _assigned));
		for(int i = 1 ; i < _size ; i++){
			col_list.add(new Col(_size, i, -1));
		}
		// 자신의 depth이후의 모든 Col 인스턴스에 대해 도메인을 업데이트.
		for(int i = this.depth + 1 ; i < this.col_list.size(); i++){
			this.update_col_domain(i);
		}
	}
	
	State(State parent, int _assgined){
		this.col_list = new ArrayList<Col>();
		System.out.println("자식 state 생성.");
		this.depth = parent.depth + 1;
		this.size = parent.size;
		this.assigned = _assgined;
		// parent 노드에서 받은 col_list를 받아옴.
		this.col_list.addAll(parent.col_list);
		// state에 해당하는 깊이 index에 위치한 col_list의 Col인스턴스 assigned값을 갱신.
		this.col_list.get(this.depth).assigned = _assgined;
		
		System.out.println("도메인 업데이트 이전.");
		this.printInfo();
		
		// 자신의 depth이후의 모든 Col 인스턴스에 대해 도메인을 업데이트.
		for(int i = this.depth + 1 ; i < this.col_list.size(); i++){
			this.update_col_domain(i);
		}
		System.out.println("도메인 업데이트 이후 .");
		this.printInfo();
		
	}
	
	// expand가능한지 알아보고 안되는 경우 null반환
	// 불가능한 경우 1.도메인이 공집합인 변수가 발생
	// 가능한 경우 가능한 위치들을 배열값으로 리턴.
	public ArrayList<Integer> getSucessorDomains(){
		return this.col_list.get(this.depth + 1).domain;
	}
	
	public void update_col_domain(int i){
		Col destination = this.col_list.get(i);
		// source에 할당된 값을 이용하여 destination의 도메인을 갱신. 
		// C = {i!=j, |Xi - Xj| != |i - j|
		int source_index = this.depth;
		int source_assigned = this.assigned;
		
		int destination_index = destination.depth;
		
		Iterator<Integer> iter = destination.domain.iterator();
		int index = 0;
		while(iter.hasNext()){
			int loc = iter.next().intValue();
			if(source_assigned == loc || Math.abs(source_index - destination_index) == Math.abs(source_assigned - loc)){
				iter.remove();
			}
		}
		
	}
	
	public Boolean isGoalState(){
		if(this.depth == this.size - 1){
			return true;
		} else {
			return false;
		}
	}
	
	public void printInfo(){
		Iterator<Col> iter = this.col_list.iterator();
		int index = 0;
		System.out.println("Depth:" + this.depth);
		System.out.println("Assigned: " + this.assigned);
		System.out.println("도메인");
		while(iter.hasNext()){
			Col current_col = iter.next();
			System.out.print("["+index+"]" + ":");
			Iterator<Integer> int_iter = current_col.domain.iterator();
			while(int_iter.hasNext()){
				Integer domain_element = int_iter.next();
				System.out.print(domain_element.toString()+" ");
			}
			if(current_col.assigned != -1){
				System.out.print("(" + current_col.assigned + ")");
			}
			System.out.println("");
			index++;
		}
		
	}
	
	public boolean canExpand(){
		Iterator<Col> iter = col_list.iterator();
		
		while(iter.hasNext()){
			Col current_col = iter.next();
			if(current_col.domain.size() == 0){
				return false;
			}
		}
		return true;
	}
}
