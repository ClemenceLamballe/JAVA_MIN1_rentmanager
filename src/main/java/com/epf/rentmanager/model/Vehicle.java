package com.epf.rentmanager.model;

public class Vehicle {

    private String constructeur;
    private String modele;
    private long id;
    private int nb_places;

    public Vehicle(){
    }

    public Vehicle(long id, String constructeur,String modele, int nb_places){
        this.constructeur = constructeur;
        this.id = id;
        this.nb_places = nb_places;
        this.modele=modele;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "constructeur=" + constructeur +
                "modele="+modele +
                ", id=" + id +
                ", nb_places=" + nb_places +
                '}';
    }
}
