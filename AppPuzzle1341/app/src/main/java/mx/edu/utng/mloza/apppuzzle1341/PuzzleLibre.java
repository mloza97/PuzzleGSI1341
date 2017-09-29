package mx.edu.utng.mloza.apppuzzle1341;

/**
 * Created by Manuel on 27/09/2017.
 */

public class PuzzleLibre implements PuzzleEstrategia {
    protected int[] tablero;

    public PuzzleLibre(){
        tablero = new int[16];
        for(int i=0;i<16;i++){
            tablero[i]=i;
        }
    }
    @Override
    public void moverPieza(int pos1, int pos2) {
        int temporal;
        temporal = tablero[pos1];
        tablero[pos1]=tablero[pos2];
        tablero[pos2]=temporal;
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
