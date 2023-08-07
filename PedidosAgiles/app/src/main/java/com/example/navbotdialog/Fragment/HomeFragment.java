package com.example.navbotdialog.Fragment;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navbotdialog.APIUtils;
import com.example.navbotdialog.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    // Variables y elementos de interfaz de usuario
    private EditText editTextIdBusqueda;
    private EditText editTextNombre;
    private EditText editTextTelefono;
    private EditText editTextEmail;
    private EditText editTextDireccion;
    private Button buttonBuscar;
    private Button buttonGuardar;
    private Button buttonEliminar;

    private RequestQueue requestQueue;
    private String baseUrl = APIUtils.getFullUrl("");

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        requestQueue = Volley.newRequestQueue(requireContext());

        editTextIdBusqueda = view.findViewById(R.id.editTextIdBusqueda);
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextTelefono = view.findViewById(R.id.editTextTelefono);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextDireccion = view.findViewById(R.id.editTextDireccion);
        buttonBuscar = view.findViewById(R.id.buttonBuscar);
        buttonGuardar = view.findViewById(R.id.buttonGuardar);
        buttonEliminar = view.findViewById(R.id.buttonEliminar);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarContacto();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContacto();
            }
        });

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarContacto();
            }
        });

        if(editTextTelefono.length()>10){
            Toast.makeText(requireContext(), "El telefono debe tener maximo 10", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll(this);
    }
    private void buscarContacto() {
        String idContacto = editTextIdBusqueda.getText().toString();

        String url = baseUrl + "contacGet/" + idContacto;
        Log.d("URL", url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response.length() > 0) {
                                // Obtenemos el primer objeto dentro del arreglo
                                JSONObject contacto = response.getJSONObject(0);

                                String nombre = contacto.getString("Nombre");
                                String telefono = contacto.getString("Telefono");
                                String email = contacto.getString("Correo");
                                String direccion = contacto.getString("Direccion");

                                editTextNombre.setText(nombre);
                                editTextTelefono.setText(telefono);
                                editTextEmail.setText(email);
                                editTextDireccion.setText(direccion);
                            } else {
                                Toast.makeText(requireContext(), "Contacto no encontrado", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(requireContext(), "Error al obtener el contacto", Toast.LENGTH_SHORT).show();
                    }
                });

        jsonArrayRequest.setTag(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void guardarContacto() {
        String idContacto = editTextIdBusqueda.getText().toString();
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String email = editTextEmail.getText().toString();
        String direccion = editTextDireccion.getText().toString();



        // Crear un objeto JSON con los datos del contacto
        JSONObject contactoJSON = new JSONObject();
        try {
            contactoJSON.put("Nombre", nombre);
            contactoJSON.put("Telefono", telefono);
            contactoJSON.put("Correo", email);
            contactoJSON.put("Direccion", direccion);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error al crear el objeto JSON", Toast.LENGTH_SHORT).show();
            return;
        }

        // Si se ingresó un ID, realizar la modificación, de lo contrario, insertar un nuevo contacto
        if (!idContacto.isEmpty()) {
            modificarContacto(idContacto, contactoJSON);
        } else {
            insertarContacto(contactoJSON);
        }
    }

    private void modificarContacto(String idContacto, JSONObject contactoJSON) {
        // URL de la API REST para actualizar un contacto existente
        String url = baseUrl + "contacUp/" + idContacto;
        System.out.println("ID CONTACTO:"+idContacto);
        // Crear una nueva solicitud PUT con el objeto JSON como cuerpo
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url, contactoJSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // La solicitud fue exitosa, el contacto se actualizó correctamente
                        Toast.makeText(requireContext(), "Error al actualizar el contacto", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error al realizar la solicitud PUT
                        Log.e("Error", error.toString());
                        Toast.makeText(requireContext(), "Contacto actualizado correctamente", Toast.LENGTH_SHORT).show();
                    }
                });

        jsonRequest.setTag(this);
        requestQueue.add(jsonRequest);
    }

    private void insertarContacto(JSONObject contactoJSON) {
        // URL de la API REST para crear un nuevo contacto
        String url = baseUrl + "contacCreate";
        // Crear una nueva solicitud POST con el objeto JSON como cuerpo
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, contactoJSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // La solicitud fue exitosa, el contacto se guardó correctamente
                        Toast.makeText(requireContext(), "Contacto guardado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error al realizar la solicitud POST
                        Log.e("Error", error.toString());
                        Toast.makeText(requireContext(), "Error al guardar el contacto", Toast.LENGTH_SHORT).show();
                    }
                });

        jsonRequest.setTag(this);
        requestQueue.add(jsonRequest);
    }

    private void eliminarContacto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmación de eliminación");
        builder.setMessage("¿Estás seguro de que deseas eliminar el contacto?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idContacto = editTextIdBusqueda.getText().toString();
                String url = baseUrl + "contac/" + idContacto;

                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(requireContext(), "Contacto eliminado correctamente", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(requireContext(), "Error al eliminar el contacto", Toast.LENGTH_SHORT).show();
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
}