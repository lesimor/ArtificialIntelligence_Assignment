package n_queens_1;
import java.util.ArrayList;
public class State {
	public ArrayList <ArrayList<Boolean>> map;	//퀸의 배치 상태.
	public int index_x;	// 새로 놓인 퀸의 가로 위치.
	public int index_y;   // 새로 놓인 퀸의 세로 위치.
	
	public State(ArrayList <ArrayList<Boolean>> _map, int _index_x, int _index_y){
		this.map = new ArrayList <ArrayList<Boolean>>();
		this.map = copyMap(_map);	// 위치정보 지도 copy
		this.index_x = _index_x;
		this.index_y = _index_y;
	}
	
	public boolean goalCheck(){
		// 새로 놓인 퀸의 위치에서만 검사하면 된다!
		// 첫번째 col부터 차례로 검사
		for(int i = 0 ; i < this.map.size() ; i++){
			// i값이 나의 x인덱스보다 같거나 크면 break;
			if(i >= this.index_x) break;
			
			// 검사할 위치 -> 인덱스 차이의 절대값
			int offset = Math.abs(this.index_x - i);
			
			// 검사하고자 하는 상위 위치
			int check_upper_index_y = this.index_y - offset;
			
			// 범위를 넘어가는지 검사 & 해당 위치에 퀸이 있는지 검사.
			if((check_upper_index_y >= 0 && check_upper_index_y < map.get(i).size()) && map.get(i).get(check_upper_index_y) == true){
				// 범위 안에 있다면 해당 위치에 퀸이 있는지 검사
				// 퀸이 있으면 안된다.
				return false;
			}
			// 검사하고자 하는 중위 위치
			int check_middle_index_y = this.index_y;
			if(map.get(i).get(check_middle_index_y) == true){
				return false;
			}
			
			// 검사하고자 하는 하위 위치
			int check_below_index_y = this.index_y + offset;
			if((check_below_index_y >= 0 && check_below_index_y < map.get(i).size()) && map.get(i).get(check_below_index_y) == true){
				// 범위 안에 있다면 해당 위치에 퀸이 있는지 검사
				// 퀸이 있으면 안된다.
				return false;
			}
		}
		return true;
	}
	
	// 인자로 받은 map 변수를 해당 state의 map에 카피하는 메소드.
	public ArrayList <ArrayList<Boolean>> copyMap(ArrayList <ArrayList<Boolean>> _map){
		ArrayList <ArrayList<Boolean>> returnMap = new ArrayList <ArrayList<Boolean>>();
		int x_size  = _map.size();
//		System.out.println("가로의 길이는 "+x_size+"입니다..");
		int y_size = _map.get(0).size();
//		System.out.println("세로의 길이는 "+y_size+"입니다..");
		for(int i = 0 ; i < x_size; i++){
			returnMap.add(new ArrayList<Boolean>());
			
			for(int j = 0 ; j < y_size ; j++){
//				System.out.println("i값은 " +i + "y값은 " + j);
				returnMap.get(i).add(_map.get(i).get(j)); 
			}
		}
		
		return returnMap;
	}
}