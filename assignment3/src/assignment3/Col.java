package assignment3;

import java.util.ArrayList;

public class Col {
	public int assigned;
	public ArrayList<Integer> domain = new ArrayList<Integer>();
	public int depth;
	public Col(int _size, int _depth, int _assigned){
		// 값은 낮은 값에서 높은 값 순으로 할당.
		this.assigned = _assigned;	// 값이 할당되어 있지 않은 경우 -1로 초기화
		this.depth = _depth;
		for( int i = 0 ; i < _size ; i++ ) {
            this.domain.add(i);
        }
	}
}
