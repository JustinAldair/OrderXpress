package com.example.orderxpress.Herramientas.Conversor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.orderxpress.R;

import java.text.DecimalFormat;

public class ConversorFragment extends Fragment {

    private String[] opcionesConversion = {"Longitud", "Temperatura", "Masa", "Volumen"};
    private String[] opcionesMedida = {"Pulgadas (in)", "Pies (ft)", "Centímetros (cm)", "Metros (m)"};
    private String[] opcionesTemperatura = {"Fahrenheit (°F)", "Celsius (°C)", "Kelvin (°K)"};
    private String[] opcionesVolumen = {"Litros (l)", "Galones (gal)", "Mililitros (ml)"};
    private String[] opcionesPeso = {"Kilogramos (Kg)", "Onza (Oz)", "Libra (L)"};


    private Spinner spinnerConversion;
    private Spinner spinnerConvertirDe;
    private Spinner spinnerConvertirA;
    private EditText etDatoIngresado;
    private TextView tvResultado;
    private Button btnConvertir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversor, container, false);

        spinnerConversion = view.findViewById(R.id.elegirConversion);
        spinnerConvertirDe = view.findViewById(R.id.convertirDe);
        spinnerConvertirA = view.findViewById(R.id.convertirA);
        etDatoIngresado = view.findViewById(R.id.EtdatoIngresado);
        tvResultado = view.findViewById(R.id.tvresultado);
        btnConvertir = view.findViewById(R.id.btnConvertir);

        ArrayAdapter<String> adapterConversion = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, opcionesConversion);
        spinnerConversion.setAdapter(adapterConversion);

        ArrayAdapter<String> adapterMedida = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, opcionesMedida);
        ArrayAdapter<String> adapterTemperatura = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, opcionesTemperatura);
        ArrayAdapter<String> adapterVolumen = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, opcionesVolumen);
        ArrayAdapter<String> adapterPeso = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, opcionesPeso);

        spinnerConversion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = parent.getItemAtPosition(position).toString();

                if (seleccion.equals("Longitud")) {
                    spinnerConvertirDe.setAdapter(adapterMedida);
                    spinnerConvertirA.setAdapter(adapterMedida);
                } else if (seleccion.equals("Temperatura")) {
                    spinnerConvertirDe.setAdapter(adapterTemperatura);
                    spinnerConvertirA.setAdapter(adapterTemperatura);
                } else if (seleccion.equals("Masa")) {
                    spinnerConvertirDe.setAdapter(adapterPeso);
                    spinnerConvertirA.setAdapter(adapterPeso);
                } else if (seleccion.equals("Volumen")) {
                    spinnerConvertirDe.setAdapter(adapterVolumen);
                    spinnerConvertirA.setAdapter(adapterVolumen);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datoString = etDatoIngresado.getText().toString();
                if (datoString.isEmpty()) {
                    Toast.makeText(getContext(), "Ingrese un valor", Toast.LENGTH_SHORT).show();
                    return;
                }
                double dato = Double.parseDouble(datoString);
                String unidadDesde = spinnerConvertirDe.getSelectedItem().toString();
                String unidadHacia = spinnerConvertirA.getSelectedItem().toString();

                double resultado;

                if (spinnerConversion.getSelectedItem().toString().equals("Masa")) {
                    resultado = convertirPeso(dato, unidadDesde, unidadHacia);
                } else if (spinnerConversion.getSelectedItem().toString().equals("Temperatura")) {
                    resultado = convertirTemperatura(dato, unidadDesde, unidadHacia);
                }else if (spinnerConversion.getSelectedItem().toString().equals("Volumen")) {
                    resultado = convertirVolumen(dato, unidadDesde, unidadHacia);
                }else if (spinnerConversion.getSelectedItem().toString().equals("Logitud")) {
                    resultado = convertirMedida(dato, unidadDesde, unidadHacia);
                }else {
                    Toast.makeText(getContext(), "Seleccione un tipo de conversión", Toast.LENGTH_SHORT).show();
                    return;
                }

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String resultadoFormateado = decimalFormat.format(resultado);

                String mensaje = datoString + " " + unidadDesde + " son " + resultadoFormateado + " " + unidadHacia;
                tvResultado.setText(mensaje);
            }

        });

        return view;
    }


    private double convertirTemperatura(double temperatura, String unidadDesde, String unidadHacia) {
        if (unidadDesde.equals("Celsius (°C)")) {
            if (unidadHacia.equals("Fahrenheit (°F)")) {
                return temperatura * 1.8 + 32;
            } else if (unidadHacia.equals("Kelvin (°K)")) {
                return temperatura + 273.15;
            }
        } else if (unidadDesde.equals("Fahrenheit (°F)")) {
            if (unidadHacia.equals("Celsius (°C)")) {
                return (temperatura - 32) / 1.8;
            } else if (unidadHacia.equals("Kelvin (°K)")) {
                return (temperatura + 459.67) * 5 / 9;
            }
        } else if (unidadDesde.equals("Kelvin (°K)")) {
            if (unidadHacia.equals("Celsius (°C)")) {
                return temperatura - 273.15;
            } else if (unidadHacia.equals("Fahrenheit (°F)")) {
                return temperatura * 1.8 - 459.67;
            }
        }
        return temperatura;
    }
    private double convertirPeso(double peso, String unidadDesde, String unidadHacia) {
        if (unidadDesde.equals("Kilogramos (Kg)")) {
            if (unidadHacia.equals("Onza (Oz)")) {
                return peso * 35.2739619;
            } else if (unidadHacia.equals("Libra (L)")) {
                return peso * 2.20462262;
            }
        } else if (unidadDesde.equals("Onza (Oz)")) {
            if (unidadHacia.equals("Kilogramos (Kg)")) {
                return peso / 35.2739619;
            } else if (unidadHacia.equals("Libra (L)")) {
                return peso / 16;
            }
        } else if (unidadDesde.equals("Libra (L)")) {
            if (unidadHacia.equals("Kilogramos (Kg)")) {
                return peso / 2.20462262;
            } else if (unidadHacia.equals("Onza (Oz)")) {
                return peso * 16;
            }
        }
        return peso;
    }

    private double convertirVolumen(double volumen, String unidadDesde, String unidadHacia) {
        if (unidadDesde.equals("Litros (l)")) {
            if (unidadHacia.equals("Galones (gal)")) {
                return volumen / 3.78541;
            } else if (unidadHacia.equals("Mililitros (ml)")) {
                return volumen * 1000;
            }
        } else if (unidadDesde.equals("Galones (gal)")) {
            if (unidadHacia.equals("Litros (l)")) {
                return volumen * 3.78541;
            } else if (unidadHacia.equals("Mililitros (ml)")) {
                return volumen * 3785.41;
            }
        } else if (unidadDesde.equals("Mililitros (ml)")) {
            if (unidadHacia.equals("Litros (l)")) {
                return volumen / 1000;
            } else if (unidadHacia.equals("Galones (gal)")) {
                return volumen / 3785.41;
            }
        }
        return volumen;
    }

    private double convertirMedida(double medida, String unidadDesde, String unidadHacia) {
        if (unidadDesde.equals("Pulgadas")) {
            if (unidadHacia.equals("Pies (ft)")) {
                return medida / 12;
            } else if (unidadHacia.equals("Centímetros")) {
                return medida * 2.54;
            } else if (unidadHacia.equals("Metros")) {
                return medida * 0.0254;
            }
        } else if (unidadDesde.equals("Pies (ft)")) {
            if (unidadHacia.equals("Pulgadas")) {
                return medida * 12;
            } else if (unidadHacia.equals("Centímetros")) {
                return medida * 30.48;
            } else if (unidadHacia.equals("Metros")) {
                return medida * 0.3048;
            }
        } else if (unidadDesde.equals("Centímetros")) {
            if (unidadHacia.equals("Pulgadas")) {
                return medida / 2.54;
            } else if (unidadHacia.equals("Pies (ft)")) {
                return medida / 30.48;
            } else if (unidadHacia.equals("Metros")) {
                return medida / 100;
            }
        } else if (unidadDesde.equals("Metros")) {
            if (unidadHacia.equals("Pulgadas")) {
                return medida / 0.0254;
            } else if (unidadHacia.equals("Pies (ft)")) {
                return medida / 0.3048;
            } else if (unidadHacia.equals("Centímetros")) {
                return medida * 100;
            }
        }
        return medida;
    }
}