package n_queens_2;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		State singletonState = State.getInstance();
		singletonState.print();
		
		singletonState.setSize(7);
		
		singletonState.showBoard();
		
		System.out.println("---------");
		while(singletonState.getHeuristicIndexSum() != 0) {
			singletonState.setSize(5);
		}
		
//		singletonState.moveQueen(2, 1);
		
		singletonState.showBoard();
		
		System.out.println("(0,0)에서의 휴리스틱 인덱스는 " + singletonState.getHeuristicIndexPerColumn(0 ,0));
		System.out.println("휴리스틱 인덱스 총합은 " + singletonState.getHeuristicIndexSum());
	}

}
