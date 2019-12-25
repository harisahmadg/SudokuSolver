
/* 5 steps

1. Pick empty spot
2. Try all numbers
3. Find one that works
4. Repeat
5. Backtrack if by the end that num didnt work

 */

public class Application {

    private int[][] board = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
    };

    public boolean solve() {
        //base case when board is full

        int[] emptyPoint = find_empty();

        if (emptyPoint == null) {
            return true;
        }
        else {
            //add num i
            for (int i = 1; i < 10; i++) {
                if (isValid(board, i, emptyPoint)) {
                    board[emptyPoint[0]][emptyPoint[1]] = i;
                    if (solve())
                        return true;

                    board[emptyPoint[0]][emptyPoint[1]] = 0;
                }
            }
            return false;
        }
    }



    public boolean isValid(int[][] board, int num, int[] position) {

        /*Check row, using our pos[0] (col) and [x], we check THAT row
        to see if it equals num(wat were adding) && but dont check
        the position were adding it to cuz thats legal
         */
        for (int x = 0; x < board.length; x++) {
            if (board[position[0]][x] == num && position[1] != x)
                return false;
        }

        // Check column
        for (int y = 0; y < board.length; y++) {
            if (board[y][position[1]] == num && position[0] != y) {
                return false;
            }
        }

        /* Check box
            (0,0), (0,1), (0,2)
            (1,0), (1,1), (1,2)
            (2,0), (2,1), (2,2)
         */
        int x_box = position[1] / 3;    //column
        int y_box = position[0] / 3;    //row

        for (int i = y_box*3; i < y_box*3 + 3; i++){
            for (int j = x_box*3; j < x_box*3 + 3; j++){
                if (board[i][j] == num && i != position[0] && j != position[1]) {
                    return false;
                }
            }
        }
        return true;
    }


    public int[] find_empty() {

        int[] tuple = new int[2];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {

                if (board[y][x] == 0) {
                    tuple[0] = y; //row
                    tuple[1] = x; //column
                    return tuple;    //row, column
                }
            }
        }

        return null;
    }

    public void printBoard() {

        for (int y=0; y < board.length; y++) {

            if (y % 3 == 0 && y != 0) {
                System.out.print("- - - - - - - - - - - - \n");
            }

            for (int x=0; x<board.length; x++) {
                if (x % 3 == 0 && x != 0) {
                    System.out.print(" | ");
                }

                System.out.print(board[y][x] + " ");
            }
            System.out.println();
        }

    }

}
