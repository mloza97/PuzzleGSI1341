package mx.edu.utng.mloza.apppuzzle1341;

/**
 * Created by Manuel on 28/09/2017.
 */

public class PuzzleCircular implements PuzzleEstrategia {
    protected int[] tablero;
    private int disponible; //casilla libre

    public PuzzleCircular(){
        tablero = new int[16];
        for(int i=0;i<16;i++){
            tablero[i]=i;
        }
        disponible = 15;
    }

    @Override
    public void moverPieza(int pos1, int dxy) {
        int x = pos1/4; //numero de fila
        int y = pos1%4; //numero de columna
        int tempo;
        switch (dxy){
            case 1://Arriba
                tempo = tablero[y];
                tablero[y] = tablero[y+4];
                tablero[y+4] = tablero[y+8];
                tablero[y+8] = tablero[y+12];
                tablero[y+12] = tempo;
                break;
            case 3://Abajo
                tempo = tablero[y+12];
                tablero[y+12] = tablero[y+8];
                tablero[y+8] = tablero[y+4];
                tablero[y+4] = tablero[y];
                tablero[y] = tempo;
                break;
            case 2://Derecha
                tempo = tablero[x*4+3];
                tablero[x*4+3] = tablero[x*4+2];
                tablero[x*4+2] = tablero[x*4+1];
                tablero[x*4+1] = tablero[x*4];
                tablero[x*4] = tempo;
                break;
            case 4://izquierda
                tempo = tablero[x*4];
                tablero[x*4] = tablero[x*4+1];
                tablero[x*4+1] = tablero[x*4+2];
                tablero[x*4+2] = tablero[x*4+3];
                tablero[x*4+3] = tempo;
                break;
        }
    }

    @Override
    public boolean verificarMov(int pos) {
        return true;
    }

    @Override
    public boolean yaGano() {
        for(int i=0;i<16;i++){
            if(tablero[i]!=i){
                return false;
            }
        }
        return true;
    }
}
