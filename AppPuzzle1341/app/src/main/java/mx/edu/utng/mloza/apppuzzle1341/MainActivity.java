package mx.edu.utng.mloza.apppuzzle1341;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnTouchListener {
    private Button btnMezclar;
    private Button btnSalir;
    private Button btnCreditos;
    private Spinner spnTipos; //Tipo de rompecabezas
    private Spinner spnOpciones; //Imagen seleccionada
    private int tipo;
    private int imagen;
    private PuzzleEstrategia juego;
    private ImageButton[] imagenes;
   // ArrayAdapter<String> adapter;
    private int disponible=15;//Modelo clasico, casilla disponible
    private int pos1;//Modelo Libre,  primera pieza seleccionada
    private int pos2;//Modo Libre,  segunda pieza seleccionada
    private boolean banPar;//Se pulsaron 2 piezas
    private int x1;//Circula, coordenadas donde inicia el touch
    private int y1;//Circula
    private int x2;//Circula, coordenadas donde finaliza el touch
    private int y2;//Circular
    private int dxy;//direccion de desplazamiento
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // adapter = new ArrayAdapter<String>(this,R.layout.spinner_item);
        //spnOpciones.setAdapter(adapter);
        //spnTipos.setAdapter(adapter);


        inicializarControles();

        spnTipos.setOnItemSelectedListener(this);
        spnOpciones.setOnItemSelectedListener(this);

        for(int i=0;i<16;i++){
            imagenes[i].setOnTouchListener(this);
        }
        btnMezclar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mezclar();
            }
        });

        btnCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreditosActivity.class);
                startActivity(intent);
            }
        });

    }

    public void inicializarControles(){
        imagenes = new ImageButton[16];
        imagenes[0] = (ImageButton)findViewById(R.id.button1);
        imagenes[1] = (ImageButton)findViewById(R.id.button2);
        imagenes[2] = (ImageButton)findViewById(R.id.button3);
        imagenes[3] = (ImageButton)findViewById(R.id.button4);
        imagenes[4] = (ImageButton)findViewById(R.id.button5);
        imagenes[5] = (ImageButton)findViewById(R.id.button6);
        imagenes[6] = (ImageButton)findViewById(R.id.button7);
        imagenes[7] = (ImageButton)findViewById(R.id.button8);
        imagenes[8] = (ImageButton)findViewById(R.id.button9);
        imagenes[9] = (ImageButton)findViewById(R.id.button10);
        imagenes[10] = (ImageButton)findViewById(R.id.button11);
        imagenes[11] = (ImageButton)findViewById(R.id.button12);
        imagenes[12] = (ImageButton)findViewById(R.id.button13);
        imagenes[13] = (ImageButton)findViewById(R.id.button14);
        imagenes[14] = (ImageButton)findViewById(R.id.button15);
        imagenes[15] = (ImageButton)findViewById(R.id.button16);

        btnMezclar = (Button)findViewById(R.id.btn_mezclar);
        btnSalir = (Button)findViewById(R.id.btn_salir);
        spnOpciones = (Spinner)findViewById(R.id.spn_opciones);
        spnTipos = (Spinner)findViewById(R.id.spn_tipos);
        btnCreditos = (Button)findViewById(R.id.btn_mostrar);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(adapterView.getId()==R.id.spn_tipos){
            tipo = (int) spnTipos.getSelectedItemId();
            switch (tipo){
                case 1://Libre
                    juego = new PuzzleLibre();
                    break;
                case 2://Clasico
                    juego = new PuzzleyClasico();
                    break;
                case 3://Circular
                    juego = new PuzzleCircular();
                    break;
            }
        }else if(adapterView.getId()==R.id.spn_opciones){
            imagen =(int) spnOpciones.getSelectedItemId();
            cargarImagenes(imagen);
        }
    }




    private void cargarImagenes(int imagen){
        switch (imagen){
            case 1://Goku
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.g1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.g2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.g3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.g4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.g5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.g6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.g7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.g8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.g9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.g10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.g11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.g12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.g13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.g14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.g15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.g16));
                break;
            case 2://Mario
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.m1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.m2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.m3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.m4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.m5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.m6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.m7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.m8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.m9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.m10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.m11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.m12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.m13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.m14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.m15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.m16));
                break;
            case 3://Dragon Ball Z
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.go1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.go2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.go3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.go4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.go5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.go6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.go7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.go8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.go9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.go10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.go11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.go12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.go13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.go14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.go15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.go16));
                break;
            case 4://Payaso
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.p1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.p2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.p3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.p4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.p5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.p6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.p7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.p8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.p9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.p10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.p11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.p12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.p13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.p14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.p15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.p16));
                break;
            case 5://Sombrerero
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.s2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.s3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.s4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.s5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.s6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.s7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.s8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.s9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.s10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.s11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.s12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.s13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.s14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.s15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.s16));
                break;
            case 6://Android
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.a1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.a2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.a3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.a4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.a5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.a6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.a7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.a8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.a9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.a10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.a11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.a12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.a13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.a14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.a15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.a16));
                break;
            case 7://Numeros
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.n1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.n2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.n3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.n4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.n5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.n6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.n7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.n8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.n9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.n10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.n11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.n12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.n13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.n14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.n15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.n16));
                break;
        }
        if(tipo==2){//tipo clasica
            imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.vacio));
        }
    }

    private void mezclar(){
        int x;
        int y;
        Random r = new Random();

        for(int i=1;i<100;i++){
            x = r.nextInt(16);
            y = r.nextInt(16);
            if(juego.verificarMov(x)){
                switch (tipo){
                    case 1://Libre
                        juego.moverPieza(x,y);
                        moverImagenes(x,y);
                        break;
                    case 2://Clasico
                        juego.moverPieza(x,disponible);
                        moverImagenes(x,disponible);
                        break;
                    case 3://Circular
                        juego.moverPieza(x,y%4);
                        moverImagenes(x,y%4);
                        break;
                }

            }
        }
    }

    public void moverImagenes(int x,int y){
        ImageButton tempo = new ImageButton(this);//<<--
        switch (tipo){
            case 2://Clasico
                disponible=x;
            case 1://Libre
                tempo.setBackgroundDrawable(imagenes[x].getBackground());
                imagenes[x].setBackgroundDrawable(imagenes[y].getBackground());
                imagenes[y].setBackgroundDrawable(tempo.getBackground());
                break;
            case 3:
                int i=x/4; //Fila
                int j=x%4; //Columna
                switch (y){
                    case 1://Arriba
                        tempo.setBackgroundDrawable(imagenes[j].getBackground());
                        imagenes[j].setBackgroundDrawable(imagenes[j+4].getBackground());
                        imagenes[j+4].setBackgroundDrawable(imagenes[j+8].getBackground());
                        imagenes[j+8].setBackgroundDrawable(imagenes[j+12].getBackground());
                        imagenes[j+12].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 3://Abajo
                        tempo.setBackgroundDrawable(imagenes[j+12].getBackground());
                        imagenes[j+12].setBackgroundDrawable(imagenes[j+8].getBackground());
                        imagenes[j+8].setBackgroundDrawable(imagenes[j+4].getBackground());
                        imagenes[j+4].setBackgroundDrawable(imagenes[j].getBackground());
                        imagenes[j].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 2://Derecha
                        tempo.setBackgroundDrawable(imagenes[i*4+3].getBackground());
                        imagenes[i*4+3].setBackgroundDrawable(imagenes[i*4+2].getBackground());
                        imagenes[i*4+2].setBackgroundDrawable(imagenes[i*4+1].getBackground());
                        imagenes[i*4+1].setBackgroundDrawable(imagenes[i*4].getBackground());
                        imagenes[i*4].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 4://Izquierda
                        tempo.setBackgroundDrawable(imagenes[i*4].getBackground());
                        imagenes[i*4].setBackgroundDrawable(imagenes[i*4+1].getBackground());
                        imagenes[i*4+1].setBackgroundDrawable(imagenes[i*4+2].getBackground());
                        imagenes[i*4+2].setBackgroundDrawable(imagenes[i*4+3].getBackground());
                        imagenes[i*4+3].setBackgroundDrawable(tempo.getBackground());
                        break;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:

                break;
        }
    }
*/
    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        int dx=0;//desplazamiento horizontal
        int dy=0;//desplazamiento vertical
        switch (v.getId()){
            case R.id.button1: pos1=0; break;
            case R.id.button2: pos1=1; break;
            case R.id.button3: pos1=2; break;
            case R.id.button4: pos1=3; break;
            case R.id.button5: pos1=4; break;
            case R.id.button6: pos1=5; break;
            case R.id.button7: pos1=6; break;
            case R.id.button8: pos1=7; break;
            case R.id.button9: pos1=8; break;
            case R.id.button10: pos1=9; break;
            case R.id.button11: pos1=10; break;
            case R.id.button12: pos1=11; break;
            case R.id.button13: pos1=12; break;
            case R.id.button14: pos1=13; break;
            case R.id.button15: pos1=14; break;
            case R.id.button16: pos1=15; break;
        }
        switch (tipo){
            case 1: //Libre
                banPar = !banPar;
                if(!banPar){//si es falsa
                    pos2=pos1;
                }else{//Se seleccionaron 2 piezas
                    juego.moverPieza(pos1,pos2);
                    moverImagenes(pos1,pos2);
                    if(juego.yaGano()){
                        Toast.makeText(getApplicationContext(),"*** Ganaste ***",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case 2://Clasico
                if(juego.verificarMov(pos1)){
                    juego.moverPieza(pos1,disponible);
                    moverImagenes(pos1,disponible);
                    if(juego.yaGano()){
                        Toast.makeText(getApplicationContext(),"*** Ganaste ***",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case 3://Circular
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    x1=(int) motionEvent.getX();//Obtener las coordenadas en x, inicio del touch
                    y1=(int) motionEvent.getY();//Obtener las coordenadas en y, inicio del touch
                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    x2 = (int) motionEvent.getX();//Obtener las coordenadas en x, fin del touch
                    y2 = (int) motionEvent.getY();//Obtener las coordenadas en y, fin del touch
                    dx = Math.abs(x2 - x1);//desplazamiento en x
                    dy = Math.abs(y2 - y1);//desplazamiento en y
                    if (dx > dy) {//Movimiento horizontal
                        if (x2 > x1) {//Desplazameinto hacia la derecha
                            dxy = 2;//derecha
                        } else {
                            dxy = 4;//izquierda
                        }
                    } else {//Movimiento vertical
                        if (y2 > y1) {//Desplazamiento hacia abajo
                            dxy = 3;//abajo
                        } else {//desplazamiento hacia arriba
                            dxy = 1;//arriba
                        }
                    }
                    juego.moverPieza(pos1, dxy);
                    moverImagenes(pos1, dxy);
                    if (juego.yaGano()) {
                        Toast.makeText(getApplicationContext(), "*** Ganaste ***", Toast.LENGTH_LONG).show();
                    }
                }
        }
        return false;
    }
}
