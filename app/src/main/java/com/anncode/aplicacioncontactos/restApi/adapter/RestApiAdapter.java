package com.anncode.aplicacioncontactos.restApi.adapter;

import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;
import com.anncode.aplicacioncontactos.restApi.EndpointsApi;
import com.anncode.aplicacioncontactos.restApi.deserializador.ContactoDeserializador;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase adaptadora
 * Deserializacion: Traer los datos de cada dato
 */
public class RestApiAdapter {


    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        //Generar la llamada al root_url. A partir de que genere esa llamada, iras a la clase EndpointsApi
        return retrofit.create(EndpointsApi.class);

    }


    public Gson construyeGsonDeserializadorMediaRecent(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ContactoResponse.class, new ContactoDeserializador());

       return gsonBuilder.create();


    }

}
