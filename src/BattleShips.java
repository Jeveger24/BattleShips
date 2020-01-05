import java.util.*;

public class BattleShips {
    public static int[][] board = new int[10][10];
    public static int playerNumberShips = 5;
    public static int computerNumberShips = 5;

    public static void main(String[] args) {
        createBoard();
        placeShips();
        createBoard();
        computerShips();
        createBoard();
        battle();
    }

    public static void createBoard() {
        System.out.println("  0123456789");
        for (int r = 0; r < board.length; r++) {
            System.out.print(r + "|");//prints the row number and pipe symbol
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 0){
                    System.out.print(" ");// leaves space in the middle of the array
                } else if (board[r][c] == 1) {
                    System.out.print("@");// player's ship
                } else if (board[r][c] == 5) {
                    System.out.print("!");// wreck of one of our own ships
                } else if (board[r][c] == 6) {
                    System.out.print("x");// wreck of a computer ship
                } else if (board[r][c] == 7) {
                    System.out.print("-");// the player guessed this cell
                } else if (board[r][c] == 8) {
                    System.out.print("^");// the computer guessed this cell
                } else if (board[r][c] == 9) {
                    System.out.print("#");// the player and computer both guessed this cell
                } else if (board[r][c] == 2) {
                    System.out.print("*");// computer's ship
                }
            }
            System.out.println("|" + r);//prints the row number and pipe symbol
        }
        System.out.println("  0123456789");
    }

    public static void placeShips() {
        System.out.println();
        System.out.println("Where should we deploy our ships? ");
        for (int i = 1; i <= 5; i++) {
            placeShip(i);
        }
    }

    public static void placeShip(int i) {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter X coordinate for your " + i + " ship: ");
        int x = input.nextInt();
        System.out.print("Enter Y coordinate for your " + i + " ship: ");
        int y = input.nextInt();
        if (x < 10 && y < 10 && board[x][y] == 0) {
            board[x][y] = 1;
        } else {
            System.out.println("You already have a ship there, choose different coordinates! ");
            placeShip(i);
        }
    }

    public static void computerShips() {
        System.out.println();
        System.out.println("The computer is deploying its ships! ");
        System.out.println();
        for (int i = 1; i <= 5; i++) {
            computerShip(i);
        }
    }

    public static void computerShip(int i) {
        Random rand = new Random();
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        if (board[x][y] == 0) {
            board[x][y] = 2;
            System.out.println("Ship " + i + " deployed! ");
            System.out.println();
        } else {
            System.out.println("Enemy ship failed to deploy and is returning to base to try again! ");
            computerShip(i);
        }
    }

    public static void battle() {
        while (playerNumberShips > 0 || computerNumberShips > 0) {
            playerTurn();
            computerTurn();
            createBoard();
            System.out.println("Player ships: " + playerNumberShips + " | Computer ships: " + computerNumberShips);
            if (playerNumberShips == 0) {
                System.out.println("You lost the battle!");
                System.exit(0);
            } else if (computerNumberShips == 0) {
                System.out.println("You've won the battle!");
                System.exit(0);
            }
        }
    }

    public static void playerTurn() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter X coordinate of target: ");
        int x = input.nextInt();
        System.out.print("Enter Y coordinate of target: ");
        int y = input.nextInt();
        if (x < 10 && y < 10 && board[x][y] == 1) {
            board[x][y] = 5;
            System.out.println("You sunk your own ship. You fool! ");
            System.out.println();
            playerNumberShips = playerNumberShips - 1;
        } else if (x < 10 && y < 10 && board[x][y] == 2) {
            board[x][y] = 6;
            System.out.println("Boom! You sunk an enemy ship! ");
            System.out.println();
            computerNumberShips = computerNumberShips - 1;
        } else if (x < 10 && y < 10 && board[x][y] == 0) {
            board[x][y] = 7;
            System.out.println("You have missed! ");
            System.out.println();
        } else if (x < 10 && y < 10 && board[x][y] == 8) {
            board[x][y] = 9;
            System.out.println("The computer has tried that spot! ");
            System.out.println();
        } else {
            System.out.println("That's outside of the battlefield, choose coordinates of less than 10 each.");
            playerTurn();
        }
    }

    public static void computerTurn() {
        Random rand = new Random();
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        if (board[x][y] == 2) {
            board[x][y] = 6;
            System.out.println("The computer sank its own ship. Idiot! ");
            System.out.println();
            computerNumberShips = computerNumberShips - 1;
        } else if (board[x][y] == 1) {
            board[x][y] = 5;
            System.out.println("The computer sank one of your ships! ");
            System.out.println();
            playerNumberShips = playerNumberShips - 1;
        } else if (board[x][y] == 0) {
            board[x][y] = 8;
            System.out.println("The computer missed! ");
        } else if (board[x][y] == 7) {
            board[x][y] = 9;
            System.out.println("You have tried this spot!");
        } else {
            computerTurn();
        }
    }
}