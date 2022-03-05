public class SudokuSolver {
    private static final int SIZEOF_GRID =9;

    public static void main(String[] args) {
        int board[][] = {
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        printTheBoard(board);
        if(solveTheBoard(board)){
            System.out.println("solved ! ");
        }
        else{
            System.out.println("Can't solve");
        }
        System.out.println("");
        printTheBoard(board);

    }

    private static void printTheBoard(int[][] board) {
        for (int i=0;i<SIZEOF_GRID;i++){
            if(i%3==0&&i!=0){
                System.out.print("_________________\n");
            }
            for (int j=0;j<SIZEOF_GRID;j++){
               if(j%3==0 && j!=0){
                   System.out.print(" * ");
               }
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }

    private static boolean checkingRow(int[][] board, int number, int row) {
        for (int i=0;i<SIZEOF_GRID;i++){
            if(board[row][i]==number){
                return true;
            }
        }
        return false;
    }

    private static boolean checkingColumn(int[][] board, int number, int column) {
        for (int i=0;i<SIZEOF_GRID;i++){
            if(board[i][column]==number){
                return true;
            }
        }
        return false;
    }



    private static boolean checkingBox(int[][] board, int number, int column,int row) {
        int squareRowIndex=0,squareColIndex=0;
        squareRowIndex=row-row%3;
        squareColIndex=column-column%3;
        for (int i=squareRowIndex;i<squareRowIndex+3;i++){
            for(int j=squareColIndex;j<squareColIndex+3;j++){
                if(board[i][j]==number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkingPlacement(int[][]board, int number, int column, int row){
        return !checkingBox(board,number,column,row) && !checkingColumn(board,number,column)
                && !checkingRow(board,number,row);

    }
    private static boolean solveTheBoard(int[][] board){
        for (int row=0;row<SIZEOF_GRID;row++){
            for (int column=0;column<SIZEOF_GRID;column++){
                if(board[row][column]==0){
                    for(int tryingNumber=1;tryingNumber<=SIZEOF_GRID;tryingNumber++){
                        if(checkingPlacement(board,tryingNumber,column,row)){
                            board[row][column]=tryingNumber;
                            if(solveTheBoard(board)){
                                return true;
                            }
                            else{
                                board[row][column]=0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}


