package n_queens_1;
import java.util.ArrayList;

public class State {
	public ArrayList <ArrayList<Boolean>> map;	//���� ��ġ ����.
	public int index_x;	// ���� ���� ���� ���� ��ġ.
	public int index_y;   // ���� ���� ���� ���� ��ġ.
	
	public State(ArrayList <ArrayList<Boolean>> _map, int _index_x, int _index_y){
		this.map = new ArrayList <ArrayList<Boolean>>();
		copyMap(_map);	// ��ġ���� ���� copy
		this.index_x = _index_x;
		this.index_y = _index_y;
	}
	
	public boolean goalCheck(){
		// ���� ���� ���� ��ġ������ �˻��ϸ� �ȴ�!
		// ù��° col���� ���ʷ� �˻�
		for(int i = 0 ; i < this.map.size() ; i++){
			// i���� ���� x�ε������� ���ų� ũ�� break;
			if(i >= this.index_x) break;
			
			// �˻��� ��ġ -> �ε��� ������ ���밪
			int offset = Math.abs(this.index_x - i);
			
			// �˻��ϰ��� �ϴ� ���� ��ġ
			int check_upper_index_y = this.index_y - offset;
			
			// ������ �Ѿ���� �˻� & �ش� ��ġ�� ���� �ִ��� �˻�.
			if((check_upper_index_y >= 0 && check_upper_index_y < map.get(i).size()) && map.get(i).get(check_upper_index_y) == true){
				// ���� �ȿ� �ִٸ� �ش� ��ġ�� ���� �ִ��� �˻�
				// ���� ������ �ȵȴ�.
				return false;
			}
			// �˻��ϰ��� �ϴ� ���� ��ġ
			int check_middle_index_y = this.index_y;
			if(map.get(i).get(check_middle_index_y) == true){
				return false;
			}
			
			// �˻��ϰ��� �ϴ� ���� ��ġ
			int check_below_index_y = this.index_y + offset;
			if((check_below_index_y >= 0 && check_below_index_y < map.get(i).size()) && map.get(i).get(check_below_index_y) == true){
				// ���� �ȿ� �ִٸ� �ش� ��ġ�� ���� �ִ��� �˻�
				// ���� ������ �ȵȴ�.
				return false;
			}
		}
		return true;
	}
	
	// ���ڷ� ���� map ������ �ش� state�� map�� ī���ϴ� �޼ҵ�.
	private void copyMap(ArrayList <ArrayList<Boolean>> _map){
		int x_size  = _map.size();
		System.out.println("������ ���̴� "+x_size+"�Դϴ�..");
		int y_size = _map.get(0).size();
		System.out.println("������ ���̴� "+y_size+"�Դϴ�..");
		for(int i = 0 ; i < x_size; i++){
			this.map.add(new ArrayList<Boolean>());
			
			for(int j = 0 ; j < y_size ; j++){
				System.out.println("i���� " +i + "y���� " + j);
				this.map.get(i).add(_map.get(i).get(j)); 
			}
		}
	}
}
