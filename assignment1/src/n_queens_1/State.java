package n_queens_1;
import java.util.ArrayList;

// 상태를 표현하는 객체.
public class State {
	public ArrayList <ArrayList<Boolean>> map;	//퀸의 배치 상태.
	public int index_x;	// 새로 놓인 퀸의 가로 위치.
	public int index_y;   // 새로 놓인 퀸의 세로 위치.
	
	public State(ArrayList <ArrayList<Boolean>> _map, int _index_x, int _index_y){
		this.map = new ArrayList <ArrayList<Boolean>>();
		this.map = copyMap(_map);	// 위치정보 지도 copy
		this.index_x = _index_x;	// 퀸이 놓일 자리의 x좌표.
		this.index_y = _index_y;	// 퀸이 놓일 자리의 y좌표.
	}
	
	public boolean goalCheck(){
		// 새로 놓인 퀸의 위치에서만 검사
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
				// 유효 범위 안에 있다면 해당 위치에 퀸이 있는지 검사
				// 퀸이 있으면 안된다.
				return false;
			}
			// 검사하고자 하는 중위 위치
			int check_middle_index_y = this.index_y;
			if(map.get(i).get(check_middle_index_y) == true){
				// 유효 범위 안에 있다면 해당 위치에 퀸이 있는지 검사
				// 퀸이 있으면 안된다.
				return false;
			}
			
			// 검사하고자 하는 하위 위치
			int check_below_index_y = this.index_y + offset;
			if((check_below_index_y >= 0 && check_below_index_y < map.get(i).size()) && map.get(i).get(check_below_index_y) == true){
				// 유효 범위 안에 있다면 해당 위치에 퀸이 있는지 검사
				// 퀸이 있으면 안된다.
				return false;
			}
		}
		return true;
	}
	
	// 인자로 받은 map 변수를 해당 state의 map에 카피하는 메소드.
	// 그냥 대입하면 레퍼런스복사가 이루어지므로 안된다.
	// 값을 새로 생성하여 대입해주어야 한다.
	public ArrayList <ArrayList<Boolean>> copyMap(ArrayList <ArrayList<Boolean>> _map){
		// 리턴될 map정보를 담을 공간 생성.
		ArrayList <ArrayList<Boolean>> returnMap = new ArrayList <ArrayList<Boolean>>();
		
		// x축 길이를 받아온다.
		int x_size  = _map.size();
		
		// y축 길이를 받아온다.
		int y_size = _map.get(0).size();
		
		// 생성한 returnMap변수에 매개변수로 들어온 _map값을 복사해준다.
		for(int i = 0 ; i < x_size; i++){
			returnMap.add(new ArrayList<Boolean>());
			for(int j = 0 ; j < y_size ; j++){
				returnMap.get(i).add(_map.get(i).get(j)); 
			}
		}
		return returnMap;
	}
}