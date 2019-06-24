package com.anncode.aplicacioncontactos.restApi.deserializador;

import com.anncode.aplicacioncontactos.model.Contacto;

import com.anncode.aplicacioncontactos.restApi.JsonKeys;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ContactoDeserializador implements JsonDeserializer<ContactoResponse> {

    @Override
    public ContactoResponse deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson= new Gson();
        ContactoResponse contactoResponse = gson.fromJson(json, ContactoResponse.class);

       //Como el objeto que obtiene a todas las fotos es un arreglo, por eso declaramos un arreglo Json.
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        contactoResponse.setContactos(deserializarContactoDeJson(contactoResponseData));
        return contactoResponse;
    }

    public ArrayList<Contacto> deserializarContactoDeJson(JsonArray contactoResponseData){

        ArrayList<Contacto> contactos= new ArrayList<>();

        /** En este for estará sacando los datos de cada elemento
         *
         */
        for( int i=0; i<contactoResponseData.size(); i++){

            //Dentro del arreglo declarado anteriormente hay objetos Json, así que por eso declaramos un objeto Json
            JsonObject contactoResponseDataObject =  contactoResponseData.get(i).getAsJsonObject();

            JsonObject userJson =                    contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id=                               userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto=                   userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson= contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGE);
            JsonObject standardResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String url = standardResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.LIKES_COUNT).getAsInt();

            Contacto contactoActual=new Contacto();
            contactoActual.setId(id);
            contactoActual.setNombreCompleto(nombreCompleto);
            contactoActual.setUrlFoto(url);
            contactoActual.setLikes(likes);

            contactos.add(contactoActual);

        }

        return contactos;

    }
}
