package com.example.navbotdialog.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navbotdialog.APIUtils;
import com.example.navbotdialog.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritoFragment extends Fragment {
    //este va a ser para conexion de bd
    private Connection connection;

    private EditText editTextIdBusqueda;
    private Spinner spinnerSeccion;
    private EditText editTextDestino;
    private EditText editTextDireccion;
    private EditText editTextLongitud;
    private EditText editTextLatitud;
    private EditText editTextEncargado;
    private Button buttonBuscar;
    private Button buttonGuardar;
    private Button buttonEliminar;

    private RequestQueue requestQueue;
    private String baseUrl = APIUtils.getFullUrl("");



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritoFragment newInstance(String param1, String param2) {
        FavoritoFragment fragment = new FavoritoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorito, container, false);

        requestQueue = Volley.newRequestQueue(requireContext());

        editTextIdBusqueda = view.findViewById(R.id.editTextIdBusqueda);
        spinnerSeccion = view.findViewById(R.id.spinnerSeccion);
        editTextDestino = view.findViewById(R.id.editTextDestino);
        editTextDireccion = view.findViewById(R.id.editTextDireccion);
        editTextLongitud = view.findViewById(R.id.editTextLongitud);
        editTextLatitud = view.findViewById(R.id.editTextLatitud);
        editTextEncargado = view.findViewById(R.id.editTextEncargado);
        buttonBuscar = view.findViewById(R.id.buttonBuscar);
        buttonGuardar = view.findViewById(R.id.buttonGuardar);
        buttonEliminar = view.findViewById(R.id.buttonEliminar);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarRuta();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seccion = spinnerSeccion.getSelectedItem().toString();
                String destino = editTextDestino.getText().toString();
                String direccion = editTextDireccion.getText().toString();
                String longitud = editTextLongitud.getText().toString();
                String latitud = editTextLatitud.getText().toString();
                String encargado = editTextEncargado.getText().toString();

                postData(seccion, destino, direccion, longitud, latitud, encargado);
            }
        });

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarRuta();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll(this);
    }

    private void postData(String seccion, String destino, String direccion, String longitud, String latitud, String encargado) {
        String url = baseUrl + "rutaCreate";

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("Seccion", seccion);
            requestBody.put("Destino", destino);
            requestBody.put("Direccion", direccion);
            requestBody.put("Longitud", longitud);
            requestBody.put("Latitud", latitud);
            requestBody.put("Encargado", encargado);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("Request Body", requestBody.toString()); // Mover esta línea fuera del JsonObjectRequest

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(requireContext(), "Datos enviados correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(requireContext(), "Error al enviar los datos", Toast.LENGTH_SHORT).show();
                    }
                });

        jsonRequest.setTag(this);
        requestQueue.add(jsonRequest);
    }


    private void buscarRuta() {
        String idRuta = editTextIdBusqueda.getText().toString();

        String url = baseUrl + "ruta/" + idRuta;
        System.out.println(url);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String seccion = response.getString("Seccion");
                            String destino = response.getString("Destino");
                            String direccion = response.getString("Direccion");
                            String longitud = response.getString("Longitud");
                            String latitud = response.getString("Latitud");
                            String encargado = response.getString("Encargado");

                            spinnerSeccion.setSelection(getIndex(spinnerSeccion, seccion));
                            editTextDestino.setText(destino);
                            editTextDireccion.setText(direccion);
                            editTextLongitud.setText(longitud);
                            editTextLatitud.setText(latitud);
                            editTextEncargado.setText(encargado);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("error al extraer los datos");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        Toast.makeText(requireContext(), "Error al obtener la ruta", Toast.LENGTH_SHORT).show();
                    }
                });

        jsonRequest.setTag(this);
        requestQueue.add(jsonRequest);


    }


    private void eliminarRuta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmación de eliminación");
        builder.setMessage("¿Estás seguro de que deseas eliminar la ruta?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // El usuario ha confirmado eliminar, procedemos con la eliminación
                String idRuta = editTextIdBusqueda.getText().toString();
                String url = baseUrl + "ruta/" + idRuta;

                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(requireContext(), "Ruta eliminada correctamente", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(requireContext(), "Error al eliminar la ruta", Toast.LENGTH_SHORT).show();
                            }
                        });

                stringRequest.setTag(this);
                requestQueue.add(stringRequest);
            }
        });

        builder.setNegativeButton("Cancelar", null);

        // Mostrar el cuadro de diálogo
        builder.show();
    }


    private int getIndex(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
    }
}