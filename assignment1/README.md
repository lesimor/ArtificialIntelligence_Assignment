# Assignment1 :: DFS, BFS, DFID

### 알고리즘 개요

- **DFS(Depth First Search)**
  - 스택을 이용하여 Fringe 저장 공간을 관리
    - 노드를 스택으로부터 pop
    - pop을 통해 나온 노드의 자식 노드가 있다면 스택에 push
    - 스택에 노드가 존재하지 않을 때까지 1~2번 과정을 반복
  - 더이상 길이 보이지 않을 때까지 파고 든다
- **BFS(Breadth First Search)**
  - 큐를 이용하여 Fringe 저장 공간을 관리
    - 큐의 head 노드를 꺼낸다
    - 큐에서 나온 노드의 자식노드가 있다면 자식 노드 모두를 큐의 rear에 추가
    - 큐에 노드가 존재하지 않을 때까지 1~2번 과정을 반복
  - 동등한 레벨에 있는 노드를 차례로 expand한다.
- **DFID(Depth First Iterative Deepening)**
  - 기본적으로 깊이우선탐색(DFS) 알고리즘 기반
  - 깊이를 하나씩 차례로 늘려가면서 탐색한다
  - Goal의 깊이를 알지 못하기 때문에 탐색 비용을 줄이고자 하는 목적.
    - 만약 왼쪽 노드를 우선으로 탐색하는데 Goal이 오른쪽 끝의 얕은 곳에 있다면 비효율적

------

### 클래스 구조

- State
  - 설명
    - 퀸이 각 column에 놓일 때마다의 상태를 나타내는 클래스
  - 인스턴스 변수
    - **ArrayList map**
      - 퀸의 배치상태를 나타내는 2차원 boolean타입 행렬
      - true는 퀸이 놓여있음을
      - false는 아무것도 놓여있지 않음을 의미
    - **int index_x**
      - 방금 막 놓여진 퀸의 x좌표
    - **int index_y**
      - 방금 막 놓여진 퀸의 y좌표
  - 메소드
    - **boolean goalCheck()**
      - 해당 state에서 놓여진 퀸이 놓일 수 있는지만 고려하는 메소드
      - 탐색 시 해당 state의 유효성만 체크하면 되기 때문에 전체를 종합적으로 고려할 필요 없다.
    - **ArrayList copyMap(ArrayList _map)**
      - 인자로 받은 _map변수를 복사하기 위한 메소드
      - 그대로 대입하면 레퍼런스가 복사되기 때문에 따로 copy하는 과정이 필요
- **TreeNode**
  - 설명
    - 트리를 구성하는 노드
  - 인스턴스 변수
    - **TreeNode parent**
      - 부모 노드를 가리키는 변수
    - **ArrayList children**
      - 자식 노드를 가리키는 변수
      - 여러 개가 올수 있기 때문에 ArrayList로 구성
    - **public State state**
      - state 인스턴스를 담는 변수
      - 해당 노드의 상태 정보를 나타낸다.
  - 메소드
    - **public void expand()**
      - 트리를 generate하는 함수
      - 재귀적으로 호출되어 leaf 노드까지 생성된다.
    - **public boolean isLeafNode()**
      - 해당 노드가 leaf 노드인지 판별
      - 탐색시 solution을 발견했는지 판별할 때 사용된다
    - **public void showQueenPosition()**
      - 솔루션에 해당하는 state의 map에 배치된 퀸의 위치정보를 print하는 함수
  - 정적 함수
    - **static TreeNode generateTree(int size)**
      - 클래스 내부에 정의된 정적변수 rootNode를 생성하고 트리를 구성한다.