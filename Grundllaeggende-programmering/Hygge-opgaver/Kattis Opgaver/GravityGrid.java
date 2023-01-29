import java.lang.reflect.Array;
import java.util.*;

class GravityGrid {

    static boolean vicObtained(int[][] board, int indexX, int indexY, int vicCond) {

        int checkSum = board[indexX][indexY];

        // Højre & venstre

        int currentX = indexX;
        int currentY = indexY;

        int boardLengthX = board.length;
        int boardLengthY = board[currentX].length;

        int pointSum = 0;

        if (boardLengthX >= vicCond) {
            while (board[currentX][currentY] == checkSum) {
                if (currentX > 0) {
                    currentX--;
                } else {
                    break;
                }
            }

            for (int i = currentX; i < boardLengthX; i++) {
                if (board[i][currentY] == checkSum) {
                    pointSum++;
                } else {
                    break;
                }
            }

            if (pointSum == vicCond) {
                return true;
            }
        }

        // Op & ned;
        pointSum = 0;
        currentX = indexX;
        currentY = indexY;

        if (boardLengthY >= vicCond) {

            for (int i = currentY; i < boardLengthY; i++) {
                if (board[currentX][i] == checkSum) {
                    pointSum++;
                } else {
                    break;
                }
            }

            if (pointSum == vicCond) {
                return true;
            }
        }

        // Diagonalt
        pointSum = 0;
        currentX = indexX;
        currentY = indexY;

        if (boardLengthX >= vicCond) {
            while (board[currentX][currentY] == checkSum) {
                if (currentY > boardLengthY) {
                    currentX--;
                    currentY++;
                } else {
                    break;
                }
            }

            for (int i = currentX, j = currentY; i < boardLengthX && j < boardLengthY; i++, j++) {
                if (board[i][j] == checkSum) {
                    pointSum++;
                } else {
                    break;
                }
            }

            if (pointSum == vicCond) {
                return true;
            }
        }

        pointSum = 0;
        currentX = indexX;
        currentY = indexY;

        if (boardLengthX >= vicCond) {
            while (board[currentX][currentY] == checkSum) {
                if (currentY > boardLengthY) {
                    currentX++;
                    currentY++;
                } else {
                    break;
                }
            }

            for (int i = currentX, j = currentY; i > 0 && j < boardLengthY; i--, j++) {
                if (board[i][j] == checkSum) {
                    pointSum++;
                } else {
                    break;
                }
            }

            if (pointSum == vicCond) {
                return true;
            }
        }

        return false;

    }

    public static void main(String[] args) {

        boolean gameWon = false;

        Scanner sc = new Scanner(System.in);

        int h = sc.nextInt();
        int w = sc.nextInt();
        int vicCon = sc.nextInt();

        HashMap<Integer, Integer> filledIndexes = new HashMap<>();

        for (int i = 0; i < w; i++) {
            filledIndexes.put(i, h - 1);
        }

        int amountTurns = 0;
        int[][] board = new int[w][h];

        for (int i = 1; i <= h * w; i++) {

            if (!gameWon) {

                int turn = sc.nextInt() - 1;
                amountTurns += 1;

                int yToOccupy = filledIndexes.get(turn);

                board[turn][yToOccupy] = (amountTurns % 2) + 1;

                if (amountTurns >= vicCon * 2 - 1) {
                    if (vicObtained(board, turn, yToOccupy, vicCon)) {
                        gameWon = true;
                        break;
                    }
                }

                filledIndexes.put(turn, yToOccupy + -1);

            }
        }

        if (gameWon) {
            if (amountTurns % 2 == 0) {
                System.out.println("B " + amountTurns);
            } else {
                System.out.println("A " + amountTurns);
            }
        } else {
            System.out.println("D");
        }

    }

}