package com.example.orderxpress.Herramientas.Calculadora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.orderxpress.R;

public class CalculadoraFragment extends Fragment {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bdot,bpi,bequal,bplus,bmin,bmul,
            bdiv,binv,bsqrt,bsquare,bfact,bln,blog,btan,bcos,bsin,bb1,bb2,bc,bac;
    TextView tvmain,tvsec;
    String pi = "3.14159265";
    int i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculadora, container, false);

        b1 = view.findViewById(R.id.b1);
        b2 = view.findViewById(R.id.b2);
        b3 = view.findViewById(R.id.b3);
        b4 = view.findViewById(R.id.b4);
        b5 = view.findViewById(R.id.b5);
        b6 = view.findViewById(R.id.b6);
        b7 = view.findViewById(R.id.b7);
        b8 = view.findViewById(R.id.b8);
        b9 = view.findViewById(R.id.b9);
        b0 = view.findViewById(R.id.b0);
        bpi = view.findViewById(R.id.bsqrt);
        bdot = view.findViewById(R.id.bdot);
        bequal = view.findViewById(R.id.bequal);
        bplus = view.findViewById(R.id.bplus);
        bmin = view.findViewById(R.id.bmin);
        bmul = view.findViewById(R.id.bmul);
        bdiv = view.findViewById(R.id.bdiv);
        binv = view.findViewById(R.id.binv);
        bsqrt = view.findViewById(R.id.bsqrt);
        bsquare = view.findViewById(R.id.bsquare);
        bfact = view.findViewById(R.id.bfact);
        bln = view.findViewById(R.id.bln);
        blog = view.findViewById(R.id.blog);
        btan = view.findViewById(R.id.btan);
        bsin = view.findViewById(R.id.bsin);
        bcos = view.findViewById(R.id.bcos);
        bb1 = view.findViewById(R.id.bb1);
        bb2 = view.findViewById(R.id.bb2);
        bc = view.findViewById(R.id.bc);
        bac = view.findViewById(R.id.bac);

        tvmain = view.findViewById(R.id.tvmain);
        tvsec = view.findViewById(R.id.tvsec);

        //onclick listeners
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"0");
            }
        });
        bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+".");
            }
        });
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText("");
                tvsec.setText("");
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                val = val.substring(0, val.length() - 1);
                tvmain.setText(val);
            }
        });
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"+");
            }
        });
        bmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"-");
            }
        });
        bmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"×");
            }
        });
        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compara = tvmain.getText().toString();
                if (compara.matches("[0/]")==false) {
                    compara = tvmain.getText().toString();
                    tvmain.setText(tvmain.getText()+"÷");
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"(");
            }
        });
        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+")");
            }
        });




        bpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvsec.setText(bpi.getText());
                tvmain.setText(tvmain.getText()+pi);
            }
        });
        bsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"sin(");
            }
        });
        bcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"cos(");
            }
        });
        btan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"tan(");
            }
        });
        binv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"^"+"(-1)");
            }
        });

        bfact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = tvmain.getText().toString();
                if (!input.isEmpty()){
                    int val = Integer.parseInt(input);
                    int fact = factorial(val);
                    tvmain.setText(String.valueOf(fact));
                    tvsec.setText(val+"!");
                }
            }
        });
        bsqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = tvmain.getText().toString();
                if (!input.isEmpty()){
                    double num = Double.parseDouble(input);
                    if (num >= 0) {
                        double r = Math.sqrt(num);
                        tvmain.setText(String.valueOf(r));
                        tvsec.setText("√"+r);
                    } else {
                        tvmain.setText("Error: Valor negativo");
                    }
                }
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String val = tvmain.getText().toString();
                    val = val.substring(0, val.length() - 1);
                    tvmain.setText(val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"ln(");
            }
        });
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText(tvmain.getText()+"log(");
            }
        });
        bsquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = tvmain.getText().toString();
                if (!input.isEmpty()) {
                    double d = Double.parseDouble(input);
                    double square = d*d;
                    tvmain.setText(String.valueOf(square));
                    tvsec.setText(d+"²");
                }
            }
        });
        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                // Reemplazar ÷ y × con / y *
                String replacedstr = val.replace('÷', '/').replace('×', '*');
                // Agregar lógica para reemplazar paréntesis con * si es necesario
                replacedstr = replacedstr.replaceAll("(\\d+)(\\()", "$1*$2")
                        .replaceAll("(\\))(\\d+)", "$1*$2");

                if(!val.isEmpty()){
                    if (val.matches("[0]")==false && val.matches("[/]")==false ){
                        double result = eval(replacedstr);
                        tvmain.setText(String.valueOf(result));
                        tvsec.setText(val);
                    } else {
                        val = "Error de formato";
                        tvmain.setText(val);
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    val = tvmain.getText().toString();
                    if (val == "Infinity"){
                        val = "Error de formato";
                        tvmain.setText(val);
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        return view;
    }
    //factorial function
    int factorial(int n)
    {
        return (n==1 || n==0) ? 1 : n*factorial(n-1);
    }
    //eval function
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Inesperado: " + (char)ch);
                return x;
            }
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // operación suma
                    else if (eat('-')) x -= parseTerm(); // operación resta
                    else return x;
                }
            }
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplicación
                    else if (eat('/')) x /= parseFactor(); // división
                    else return x;
                }
            }
            double parseFactor() {
                if (eat('+')) return parseFactor(); // más unitario
                if (eat('-')) return -parseFactor(); // menos unitario
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentecis
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numeros
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // funciones
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) x = Math.log10(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Función Desconocida: " + func);
                } else {
                    throw new RuntimeException("Inesperado: " + (char)ch);
                }
                if (eat('^')) x = Math.pow(x, parseFactor()); // exponencial
                return x;
            }
        }.parse();
    }
}