import java.util.*;
public class BattleShips {

    public static void main(String [] args) {

        char [] [] grid = new char [12] [14];

        playGame(grid);

    }

    public static void playGame(char [][] grid){

        System.out.println("****Welcome to Battle Ships game ****");
        System.out.println("\nRight now, the sea is empty.\n");

        boolean gameOn = true;

        while (gameOn){

            initializeGrid(grid);

            printGrid(grid);

            initialDeployment(grid);

            printGrid(grid);

            toBattle(grid);

            gameOn = false;

        }
    }

    public static void initializeGrid(char [][] grid){

        for (int row = 0; row < grid.length; row++){

            Arrays.fill(grid[row], ' ');

            for (int column = 0; column < grid[row].length; column++) {

                if (row == 0 || row == grid.length - 1) {

                    if (column - 2 >= 0 && column - 2 < 10) {

                        grid[row][column] = (char) ((column - 2) + '0');

                    }

                } else {

                    if (column == 0 || column == grid[row].length - 1) {

                        grid[row][column] = (char) ((row - 1) + '0');

                    } else if (column == 1 || column == grid[row].length - 2) {

                        grid[row][column] = '|';

                    }
                }
            }
        }
    }

    public static void printGrid(char [][] grid){

        for (int x = 0; x < grid.length; x++){

            for (int y = 0; y < grid[x].length; y++){

                //System.out.print(grid[x][y]);

                if (x > 0 && x < grid.length - 1) { //this one is correct!

                    if (y > 1 || y <= grid[x].length - 2){

                        if (grid[x][y] == '1') {

                            System.out.print('@');

                        } else if (grid[x][y] == '2'){

                            System.out.print(' ');

                        } else {

                            System.out.print(grid[x][y]);

                        }
                    }
                } else {

                    System.out.print(grid[x][y]);

                }

            }

            System.out.println();

        }
    }

    public static void initialDeployment(char [][] grid){

        Scanner input = new Scanner(System.in);

        boolean shipsNotDeployed = true;

        while (shipsNotDeployed) {

            boolean coordinatesNotValid;

            System.out.println("Enter the coordinates of your ships:");

            for (int i = 0; i < 5; i++) {

                int x = -1, y = -1;

                coordinatesNotValid = true;

                while (coordinatesNotValid) {

                    System.out.print("Enter the x coordinate of ship #" + (i + 1) + ": ");

                    x = input.nextInt();

                    if (x > -1 && x < 10) {

                        System.out.print("\nEnter the y coordinate of ship #" + (i + 1) + ": ");

                        y = input.nextInt();

                        if (y > -1 && y < 10) {

                            if (grid[x + 1][y + 2] == ' ') {

                                grid[x + 1][y + 2] = '1';

                                coordinatesNotValid = false;

                            }
                        }
                    }
                }
            }

            System.out.println();

            System.out.println("Computer is deploying ships");

            for (int j = 0; j < 5; j++) {

                coordinatesNotValid = true;

                while (coordinatesNotValid){

                    Random r = new Random();

                    int x = r.nextInt(10), y = r.nextInt(10);

                    if (grid[x + 1][y + 2] == ' '){

                        grid[x + 1][y + 2] = '2';

                        System.out.println((j + 1) + ". ship DEPLOYED");

                        coordinatesNotValid = false;

                    }

                }
            }

            shipsNotDeployed = false;

        }

    }

    public static void toBattle(char [][] grid){

        int remainingPlayerShips = 5, remainingComputerShips = 5;

        boolean playerTurn = true, computerTurn = false;

        while (remainingPlayerShips > 0 && remainingComputerShips > 0){

            Scanner input = new Scanner(System.in);

            if (playerTurn == true && remainingPlayerShips > 0){

                System.out.println("\nYOUR TURN");

                int x = - 1;

                while (x < 0 || x > 9){

                    System.out.print("\nEnter x coordinate: ");

                    x = input.nextInt();

                    System.out.println();

                }

                int y = - 1;

                while (y < 0 || y > 9){

                    System.out.print("\nEnter y coordinate: ");

                    y = input.nextInt();

                    System.out.println();

                }

                if (grid[x + 1][y + 2] != '2' && grid[x + 1][y + 2] != '1'){

                    if (grid[x + 1][y + 2] != '!' && grid[x + 1][y + 2] != 'x'){

                        grid[x + 1][y + 2] = '*';

                    }

                    System.out.println("\nYou missed!");

                } else if (grid[x + 1][y + 2] == '2'){

                    grid[x + 1][y + 2] = '!';

                    System.out.println("\nBoom! You sunk the ship!");

                    remainingComputerShips--;

                    System.out.println((remainingPlayerShips) + " Of your own ships left");

                    System.out.println((remainingComputerShips) + " Computer ships left");

                } else {

                    grid[x + 1][y + 2] = 'x';

                    System.out.println("\nOh no, you sunk your own ship :(");

                    remainingPlayerShips--;

                    System.out.println((remainingPlayerShips) + " Of your own ships left");

                    System.out.println((remainingComputerShips) + " Computer ships left");

                }

                playerTurn = false;

                computerTurn = true;

                printGrid(grid);

            }

            if (computerTurn == true && remainingComputerShips > 0){

                Random r = new Random();

                int x = r.nextInt(10), y = r.nextInt(10);

                if (grid[x + 1][y + 2] != '1'){

                    if (grid[x + 1][y + 2] != '!' || grid[x + 1][y + 2] != 'x'){

                        grid[x + 1][y + 2] = '*';

                    }

                    System.out.println("\nComputer missed!\n");

                } else if (grid[x + 1][y + 2] == '1'){

                    grid[x + 1][y + 2] = '!';

                    remainingPlayerShips--;

                    System.out.println("The Computer sunk one of your ships!");

                    System.out.println((remainingPlayerShips) + " Of your own ships left");

                    System.out.println((remainingComputerShips) + " Computer ships left");

                } else {

                    grid[x + 1][y + 2] = '!';

                    remainingComputerShips--;

                    System.out.println("The Computer sunk one of its own ships");

                    System.out.println((remainingPlayerShips) + " Of your own ships left");

                    System.out.println((remainingComputerShips) + " Computer ships left");

                }

                computerTurn = false;

                playerTurn = true;

                System.out.println("--------------------------------------------------");

                printGrid(grid);

            }

        }

        if (remainingComputerShips == 0){

            System.out.println("\nPlayer Victory, Congratulations!");

        } else if (remainingPlayerShips == 0){

            System.out.println("\nComputer Victory, better luck next time.");

        }
    }
}