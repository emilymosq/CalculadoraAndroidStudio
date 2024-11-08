package com.emilymosq.calculadoraproyecto;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    double num1,num2,result;
    String operador;
    boolean operadorPresionado = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toast mensajeError = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);

        TextView resultado =(TextView) findViewById(R.id.results);

        Button uno = (Button) findViewById(R.id.button1);
        Button dos = (Button) findViewById(R.id.button2);
        Button tres = (Button) findViewById(R.id.button3);
        Button cuatro = (Button) findViewById(R.id.button4);
        Button cinco = (Button) findViewById(R.id.button5);
        Button seis = (Button) findViewById(R.id.button6);
        Button siete = (Button) findViewById(R.id.button7);
        Button ocho = (Button) findViewById(R.id.button8);
        Button nueve = (Button) findViewById(R.id.button9);
        Button cero = (Button) findViewById(R.id.button0);

        ArrayList<Button> botonNumeros = new ArrayList<>();

        botonNumeros.add(cero);
        botonNumeros.add(uno);
        botonNumeros.add(dos);
        botonNumeros.add(tres);
        botonNumeros.add(cuatro);
        botonNumeros.add(cinco);
        botonNumeros.add(seis);
        botonNumeros.add(siete);
        botonNumeros.add(ocho);
        botonNumeros.add(nueve);

        Button suma  = (Button) findViewById(R.id.buttonAdd);
        Button resta  = (Button) findViewById(R.id.buttonSubtract);
        Button multiplicacion  = (Button) findViewById(R.id.buttonMultiply);
        Button division  = (Button) findViewById(R.id.buttonDivide);
        Button igual  = (Button) findViewById(R.id.buttonEquals);
        Button coma = (Button) findViewById(R.id.buttonComma);
        Button borrar = (Button) findViewById(R.id.buttonDelete);
        Button resetear =(Button) findViewById(R.id.buttonClear);

        ArrayList<Button> botonOperaciones = new ArrayList<>();

        botonOperaciones.add(suma);
        botonOperaciones.add(resta);
        botonOperaciones.add(multiplicacion);
        botonOperaciones.add(division);
        botonOperaciones.add(igual);

        for(Button boton : botonOperaciones){
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(resultado.getText().toString().equals("0")){
                        mensajeError.show();
                    } else{
                        resultado.append(boton.getText().toString());
                    }
                }
            });
        }

        for(Button boton : botonNumeros){
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String codBoton = getResources().getResourceEntryName(view.getId());
                    String digito = codBoton.substring(codBoton.length() - 1);
                    if (resultado.getText().toString().equals("0")) {
                        resultado.setText(digito);
                        operadorPresionado = false;
                    } else {
                        resultado.append(digito);
                    }
                }
            });
        }
        coma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText(resultado.getText().toString() + ",");
            }
        });

        resetear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText("0");
                operadorPresionado = false;
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = resultado.getText().toString();
                if (textoActual.length() > 1) {
                    resultado.setText(textoActual.substring(0, textoActual.length() - 1));
                } else {
                    resultado.setText("0");
                }
            }
        });


        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "+";
                num1 = Double.parseDouble(resultado.getText().toString());
                operadorPresionado = true;
                resultado.append(" + ");
            }
        });

        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "-";
                num1 = Double.parseDouble(resultado.getText().toString());
                operadorPresionado = true;
                resultado.append(" - ");
            }
        });

        multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "*";
                num1 = Double.parseDouble(resultado.getText().toString());
                operadorPresionado = true;
                resultado.append(" * ");
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "/";
                num1 = Double.parseDouble(resultado.getText().toString());
                operadorPresionado = true;
                resultado.append(" / ");
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String[] partes = resultado.getText().toString().split(" ");
                    if (partes.length < 2) {
                        resultado.setText("Error");
                        return;
                    }
                    num2 = Double.parseDouble(partes[partes.length - 1]);

                    switch (operador) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                resultado.setText("Error");
                                return;
                            }
                            break;
                        default:
                            resultado.setText("Error");
                            return;
                    }
                    resultado.setText(String.valueOf(result));
                } catch (NumberFormatException e) {
                    resultado.setText("Error");
                }
            }
        });
    }
}