package com.anncode.aplicacioncontactos.model;

/**
 * Created by anahisalgado on 19/04/16.
 */
public class Contacto {

    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private int likes = 0;

     //   private String telefono;
     //   private String email;
    //    private int foto;


    public Contacto(String urlFoto, String nombreCompleto, String telefono, String email, int likes) {
      //  this.foto = foto;
        this.nombreCompleto = nombreCompleto;
        this.urlFoto=urlFoto;
        this.likes = likes;
    }

    public Contacto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}