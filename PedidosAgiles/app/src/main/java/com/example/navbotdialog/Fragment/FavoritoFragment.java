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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FavoritoFragment extends Fragment {
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

    public FavoritoFragment() {
        // Required empty public constructor
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

    // Resto del código aquí...

    private void buscarRuta() {
        String idRuta = editTextIdBusqueda.getText().toString();

        String url = baseUrl + "ruta/" + idRuta;
        Log.d("URL", url);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            if (jsonArray.length() > 0) {
                                JSONObject ruta = jsonArray.getJSONObject(0);

                                String seccion = ruta.getString("Seccion");
                                String destino = ruta.getString("Destino");
                                String direccion = ruta.getString("Direccion");
                                String longitud = ruta.getString("Longitud");
                                String latitud = ruta.getString("Latitud");
                                String encargado = ruta.getString("Encargado");

                                spinnerSeccion.setSelection(getIndex(spinnerSeccion, seccion));
                                editTextDestino.setText(destino);
                                editTextDireccion.setText(direccion);
                                editTextLongitud.setText(longitud);
                                editTextLatitud.setText(latitud);
                                editTextEncargado.setText(encargado);
                            } else {
                                Toast.makeText(requireContext(), "Ruta no encontrada", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(requireContext(), "Error al extraer los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                        Toast.makeText(requireContext(), "Error al obtener la ruta", Toast.LENGTH_SHORT).show();
                    }
                });

        jsonRequest.setTag(this);
        requestQueue.add(jsonRequest);
    }

    private int getIndex(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
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

        Log.d("Request Body", requestBody.toString());

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

    private void modificarRuta(String idRuta, String seccion, String destino, String direccion, String longitud, String latitud, String encargado) {
        String url = baseUrl + "ruta/" + idRuta;

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

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(requireContext(), "Ruta actualizada correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                        Toast.makeText(requireContext(), "Error al actualizar la ruta", Toast.LENGTH_SHORT).show();
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
                                Log.e("Error", error.toString());
                                Toast.makeText(requireContext(), "Error al eliminar la ruta", Toast.LENGTH_SHORT).show();
                            }
                        });

                stringRequest.setTag(this);
                requestQueue.add(stringRequest);
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }


}
