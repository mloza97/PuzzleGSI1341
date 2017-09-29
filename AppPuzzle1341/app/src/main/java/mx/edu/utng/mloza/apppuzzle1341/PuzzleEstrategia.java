package mx.edu.utng.mloza.apppuzzle1341;

/**
 * Created by Manuel on 27/09/2017.
 */

public interface PuzzleEstrategia {
    public void moverPieza(int pos1, int pos2);
    public boolean verificarMov(int pos);
    public boolean yaGano();
}
