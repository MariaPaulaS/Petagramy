package com.anncode.aplicacioncontactos.restApi;

import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Lista de endpoints o métodos definidos para implementar una petición.
 */
public interface EndpointsApi {

    /**
     * getRecentMedia tiene asignado ese método GET que tiene definido la constante con la llamada a la API.
     * @return Una clase que tenga una respuesta ContactoResponse
     */
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
   Call<ContactoResponse> getRecentMedia();


}
