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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navbotdialog.APIUtils;
import com.example.navbotdialog.R;
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

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll(this);
    }

    private void buscarContacto() {
        String idContacto = editTextIdBusqueda.getText().toString();

        String url = baseUrl + "contacto/" + idContacto;
        Log.d("URL", url);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String nombre = response.getString("Nombre");
                            String telefono = response.getString("Telefono");
                            String email = response.getString("Email");
                            String direccion = response.getString("Direccion");

                            editTextNombre.setText(nombre);
                            editTextTelefono.setText(telefono);
                            editTextEmail.setText(email);
                            editTextDireccion.setText(direccion);
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

        jsonRequest.setTag(this);
        requestQueue.add(jsonRequest);
    }

    private void guardarContacto() {
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String email = editTextEmail.getText().toString();
        String direccion = editTextDireccion.getText().toString();

        // Aquí implementa la lógica para enviar los datos del contacto al servidor con una solicitud POST o PUT

        Toast.makeText(requireContext(), "Guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void eliminarContacto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmación de eliminación");
        builder.setMessage("¿Estás seguro de que deseas eliminar el contacto?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idContacto = editTextIdBusqueda.getText().toString();
                String url = baseUrl + "contacto/" + idContacto;

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
