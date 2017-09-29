package mx.edu.utng.mloza.apppuzzle1341;

/**
 * Created by Manuel on 27/09/2017.
 */

public class PuzzleyClasico implements PuzzleEstrategia {
    protected int[] tablero;
    private int disponible; //casilla libre

    public PuzzleyClasico(){
        tablero = new int[16];
        for(int i=0;i<16;i++){
            tablero[i]=i;
        }
        disponible = 15;
    }

    @Override
    public void moverPieza(int pos1, int pos2) {
        int tempo;
        if(verificarMov(pos1)){
            tempo = tablero[pos1];
            tablero[pos1] = tablero[disponible];
            tablero[disponible] = tempo;
            disponible = pos1;
        }
    }

    @Override
    public boolean verificarMov(int pos) {
        if((pos/4==disponible/4 && Math.abs(pos-disponible)==1)
            || (pos%4==disponible%4 && Math.abs(pos-disponible)==4)){
            return true;
        }else{
            return false;
        }
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

    public int getDisponible() {
        return disponible;
    }
}
