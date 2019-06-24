package com.anncode.aplicacioncontactos.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.anncode.aplicacioncontactos.model.ConstructorContactos;
import com.anncode.aplicacioncontactos.model.Contacto;
import com.anncode.aplicacioncontactos.restApi.EndpointsApi;
import com.anncode.aplicacioncontactos.restApi.adapter.RestApiAdapter;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.anncode.aplicacioncontactos.vista.fragment.IRecyclerViewFragmentView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anahisalgado on 21/04/16.
 */
public class RecyclerViewFragmentPresenter implements IRecylerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;

    private Context context;

    private ConstructorContactos constructorContactos;

    private ArrayList<Contacto> contactos;

    public  RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        //   obtenerContactosBaseDatos();
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerContactosBaseDatos() {
        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter= new RestApiAdapter();
        Gson gsonMediaRecents = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecents);


        Call<ContactoResponse> contactoResponseCall= endpointsApi.getRecentMedia();

        //enqueue - Método para estar controlando el resultado de la respuesta.
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            //Mas arriba del data en el json hay un body, asi que trae lo que hay dentro del body
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {

                ContactoResponse contactoResponse = response.body();

                contactos = contactoResponse.getContactos();

                mostrarContactosRV();

            }

            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable throwable) {
                Toast.makeText(context, "Ups! Algo falló en la conexión. Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLÓ LA CONEXION", throwable.toString());
            }
        });
    }


    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));
        iRecyclerViewFragmentView.generarGridLayout();
    }
}


