package n_queens_2;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		State singletonState = State.getInstance();
		singletonState.print();
		
		singletonState.setSize(10);
		
		singletonState.showBoard();
	}

}
