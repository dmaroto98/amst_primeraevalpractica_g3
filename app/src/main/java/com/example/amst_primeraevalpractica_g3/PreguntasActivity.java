package com.example.amst_primeraevalpractica_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PreguntasActivity extends AppCompatActivity {


    public List<Trivia> trivias;
    public int errores;
    public int contestadas;

    public Trivia actualTrivia;

    public List<Integer> idContestadas;

    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        context=getApplicationContext();
        this.errores=0;
        this.contestadas=0;
        idContestadas=new ArrayList<>();

        this.trivias = new ArrayList<>();

        ////////////////////////////
        List<Pregunta> preguntas1=new ArrayList<>();
        preguntas1.add(new Pregunta("En 1965",false));
        preguntas1.add(new Pregunta("En 1968",false));
        preguntas1.add(new Pregunta("En 1977",true));
        preguntas1.add(new Pregunta("En 1979",false));

        Trivia trivia1=new Trivia(1,R.drawable.p1,"¿En qué año se estrenó la primera película de la saga?",preguntas1);

        this.trivias.add(trivia1);
        ///////////////////////////

        List<Pregunta> preguntas2=new ArrayList<>();
        preguntas2.add(new Pregunta("Su falta de fe me resulta perturbadora",true));
        preguntas2.add(new Pregunta("Me ha fallado por última vez, almirante",false));
        preguntas2.add(new Pregunta("Ese nombre ya no significa nada para mí",false));
        preguntas2.add(new Pregunta("No conoces el poder del lado oscuro",false));

        Trivia trivia2=new Trivia(2,R.drawable.p2,"¿Cuál es la frase más famosa del personaje de Darth Vader?",preguntas2);

        this.trivias.add(trivia2);
        ///////////////////////////

        List<Pregunta> preguntas3=new ArrayList<>();
        preguntas3.add(new Pregunta("Hoth",false));
        preguntas3.add(new Pregunta("Dagobah",true));
        preguntas3.add(new Pregunta("Tatooine",false));
        preguntas3.add(new Pregunta("Naboo",false));

        Trivia trivia3=new Trivia(3,R.drawable.p3,"¿Cómo se llama el planeta de origen del maestro Yoda?",preguntas3);

        this.trivias.add(trivia3);

        ///////////////////////////

        List<Pregunta> preguntas4=new ArrayList<>();
        preguntas4.add(new Pregunta("BB-8",false));
        preguntas4.add(new Pregunta("C-3PO",false));
        preguntas4.add(new Pregunta("D-0",false));
        preguntas4.add(new Pregunta("T-800",true));

        Trivia trivia4=new Trivia(4,R.drawable.p4,"Uno de estos robots no pertenece a la saga de Star Wars. ¿Cuál?",preguntas4);

        this.trivias.add(trivia4);
        ///////////////////////////

        List<Pregunta> preguntas5=new ArrayList<>();
        preguntas5.add(new Pregunta("Los enemigos de Yoda",false));
        preguntas5.add(new Pregunta("Los principales villanos de Star Wars",true));
        preguntas5.add(new Pregunta("Los héroes de Coruscant",false));
        preguntas5.add(new Pregunta("Los máximos representantes de los Rebeldes",false));


        Trivia trivia5=new Trivia(5,R.drawable.p5,"¿Quiénes son los Sith?",preguntas5);

        this.trivias.add(trivia5);
        ///////////////////////////
        loadPregunta();


    }

    private void loadPregunta(){
        Random rand = new Random();
        actualTrivia= trivias.get(rand.nextInt(trivias.size()));
        if(idContestadas.contains(actualTrivia.getId())){
            loadPregunta();
        }
        idContestadas.add(actualTrivia.getId());
        loadTriva();
    }

    private void loadTriva(){

        TextView textView= (TextView)findViewById(R.id.text_pregunta);
        textView.setText(actualTrivia.getPregunta());

        ImageView imageView=(ImageView)findViewById(R.id.trivia_imagen);
       imageView.setImageResource(actualTrivia.getImagen());



        //////////////////////
        Button button1=(Button)findViewById(R.id.btn_respuesta1);
        button1.setText(actualTrivia.getPreguntas().get(0).getContenido());
        button1.setOnClickListener(view -> {
            btnClick(actualTrivia.getPreguntas().get(0).isCorrecta());
        });

        //////////////////////
        Button button2=(Button)findViewById(R.id.btn_respuesta2);
        button2.setText(actualTrivia.getPreguntas().get(1).getContenido());
        button2.setOnClickListener(view -> {
            btnClick(actualTrivia.getPreguntas().get(1).isCorrecta());
        });

        //////////////////////
        Button button3=(Button)findViewById(R.id.btn_respuesta3);
        button3.setText(actualTrivia.getPreguntas().get(2).getContenido());
        button3.setOnClickListener(view -> {
            btnClick(actualTrivia.getPreguntas().get(2).isCorrecta());
        });

        //////////////////////
        Button button4=(Button)findViewById(R.id.btn_respuesta4);
        button4.setText(actualTrivia.getPreguntas().get(3).getContenido());
        button4.setOnClickListener(view -> {
            btnClick(actualTrivia.getPreguntas().get(3).isCorrecta());
        });

    }

    private void btnClick(boolean correcta){




        if(!correcta){
            errores++;
            Toast.makeText(context,"Incorrecta",Toast.LENGTH_SHORT).show();
            //Sorry
            Intent intent=new Intent(context,perdedor.class);
            intent.putExtra("contestadas",contestadas);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(context,"Correcta",Toast.LENGTH_SHORT).show();
        }
        contestadas++;
        if(contestadas>=5){

                Intent intent=new Intent(context,ganador.class);
                startActivity(intent);
                finish();

        }
        else {
            loadPregunta();
        }

    }

}