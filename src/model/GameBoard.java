package model;

public class GameBoard {
    //fields
    private String[][] board;
    private int rows = 9;  // Número de filas
    private int cols = 73;  // Número de columnas
    private int contSword = 12;
    private int contClub = 12;
    private int contGold = 12;
    private int contCup = 12;
    //constructor
    public GameBoard() {

        board = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i % 2 == 0) {
                    // Filas impares para asteriscos y guiones
                    if (j == 2) {
                        board[i][j] = "     ";
                    } else if (j % 8 == 0 & j > 0) {
                        board[i][j] = "\u001B[95m*\u001B[0m";  // Asterisco en el borde
                    } else if (j > 8) {
                        board[i][j] = "\u001B[94m─\u001B[0m";  // Línea horizontal
                    } else {
                        board[i][j] = " ";
                    }
                } else {
                    // Filas de celdas para nombres y
                    if (i == 1 && j == 3) {
                        board[i][j] = "\u001B[96mSWORD\u001B[0m";  // Caballo cian
                    } else if (i == 3 && j == 3) {
                        board[i][j] = "\u001B[92mCLUB \u001B[0m";  // Caballo verde
                    } else if (i == 5 && j == 3) {
                        board[i][j] = "\u001B[93mGOLD \u001B[0m";  // Caballo amarillo
                    } else if (i == 7 && j == 3) {
                        board[i][j] = "\u001B[91mCUP  \u001B[0m";  // Caballo rojo
                    } else if (j % 8 == 0 && j > 0) {
                        board[i][j] = "\u001B[94m│\u001B[0m";  // Separador vertical
                    } else if (j % 4 == 0 && j % 8 != 0 && j != 4) {
                        board[i][j] = "\u001B[97m█\u001B[0m";  // luz
                    } else {
                        board[i][j] = " ";  // Espacio vacío
                    }
                }
            }
        }
    }

    /**
     * "\u001B[36m" lightblue (sword)
     * "\u001B[32m" green ( club)
     * "\u001B[33m" yeloow ( gold)
     * "\u001B[31m" red (cups)
     */
    public void horseJump(String cardSuit) {
        switch (cardSuit.toLowerCase()) {
            case "sword":
                if (contSword < 68) {
                    board[1][contSword] = "\u001B[96m█\u001B[0m";
                    contSword += 8;
                }
                break;
            case "club":
                if (contClub < 68) {
                    board[3][contClub] = "\u001B[92m█\u001B[0m";
                    contClub += 8;
                }
                break;
            case "gold":
                if (contGold < 68) {
                    board[5][contGold] = "\u001B[93m█\u001B[0m";
                    contGold += 8;
                }
                break;
            case "cup":
                if (contCup < 68) {
                    board[7][contCup] = "\u001B[91m█\u001B[0m";
                    contCup += 8;
                }
                break;
            default:
                System.out.println("ERROR");
        }
    }

    public void printGameboard() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }

}
