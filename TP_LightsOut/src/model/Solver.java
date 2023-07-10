package model;

public class Solver {
    public static boolean hasSolution(boolean[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        
        // Crear una copia del tablero original
        boolean[][] tempBoard = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, cols);
        }
        
        // Realizar todas las posibles combinaciones de encendido y apagado
        for (int i = 0; i < (1 << cols); i++) {
            for (int j = 0; j < cols; j++) {
                if ((i & (1 << j)) != 0) {
                    toggleCell(0, j, tempBoard); // Encender/apagar la celda
                }
            }
            
            // Aplicar las operaciones de encendido/apagado a las celdas adyacentes
            for (int row = 1; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (tempBoard[row - 1][col]) {
                        toggleCell(row, col, tempBoard);
                    }
                }
            }
            
            // Verificar si todas las luces están apagadas en la última fila
            if (allLightsOff(tempBoard)) {
                return true; // Tiene solución
            }
            
            // Restaurar el tablero original
            for (int row = 0; row < rows; row++) {
                System.arraycopy(board[row], 0, tempBoard[row], 0, cols);
            }
        }
        
        return false; // No tiene solución
    }
    
    private static void toggleCell(int row, int col, boolean[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        
        board[row][col] = !board[row][col]; // Encender/apagar la celda
        
        if (row > 0) {
            board[row - 1][col] = !board[row - 1][col]; // Encender/apagar la celda superior
        }
        
        if (row < rows - 1) {
            board[row + 1][col] = !board[row + 1][col]; // Encender/apagar la celda inferior
        }
        
        if (col > 0) {
            board[row][col - 1] = !board[row][col - 1]; // Encender/apagar la celda izquierda
        }
        
        if (col < cols - 1) {
            board[row][col + 1] = !board[row][col + 1]; // Encender/apagar la celda derecha
        }
    }
    
    private static boolean allLightsOff(boolean[][] board) {
        for (boolean cell : board[board.length - 1]) {
            if (cell) {
                return false;
            }
        }
        return true;
    }
}

