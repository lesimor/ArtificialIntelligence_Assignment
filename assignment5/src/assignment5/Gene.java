package assignment5;

import java.util.ArrayList;
import java.util.List;

public class Gene implements Comparable<Gene>{
	public ArrayList<Integer> location_list = new ArrayList<Integer>();
	public int size;
	public int heuristic_value;
	
	public Gene(ArrayList<Integer> location_list){
		this.location_list = location_list;
		this.size = location_list.size();
		State state = State.getInstance();
		state.setQueenLocation(location_list);
		this.heuristic_value = state.getHeuristicIndexSum();
	}
	
	// 오름차순 정렬.
	public int compareTo(Gene g){
		return (heuristic_value - g.heuristic_value);
	}
	
	// 행 중복 체크.
	public boolean checkRowDuplication(){
		int sum = 0;
		int check = (size)*(size-1) / 2;
		for(Integer i: location_list){
			sum+=i;
		}
		if (sum == check){
			return true;
		} else {
			return false;
		}
	}
	
	public void printQueenPosition(){
		int col_size = location_list.size();
		for(int i = 0 ; i < col_size ; i++){
			System.out.print(location_list.get(i) + " ");
		}
		// 개행.
		System.out.println("");
	}
}
