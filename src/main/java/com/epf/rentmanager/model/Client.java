package com.epf.rentmanager.model;


import java.time.LocalDate;

public class Client {
    //attributs
// AUTOINCREMENT???
    private String nom;
    private String prenom;
    private String email;
    private LocalDate naissance;
    private long id;
    //constructeur vide
    public Client() {
        //valeurs par défaut?
    }

    // Constructeur avec paramètres
    public Client(long id, String nom, String prenom, String email, LocalDate naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }

    //méthodes

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom=" + nom +
                ", prenom=" + prenom +
                ", email=" + email +
                ", naissance=" + naissance +
                ", id=" + id +
                '}';
    }
}
