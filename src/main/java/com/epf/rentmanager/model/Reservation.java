package com.epf.rentmanager.model;
import java.time.LocalDate;
public class Reservation {
    private long client_id;
    private long vehicle_id;
    private LocalDate debut;
    private LocalDate fin;

    private long id;

  public Reservation() {
  }

  public Reservation(long id, long client_id, long vehicle_id, LocalDate debut, LocalDate fin) {
    this.client_id = client_id;
    this.vehicle_id = vehicle_id;
    this.debut = debut;
    this.fin = fin;
    this.id=id;
  }

  public long getClient_id() {
    return client_id;
  }

  public void setClient_id(long client_id) {
    this.client_id = client_id;
  }

  public long getVehicle_id() {
    return vehicle_id;
  }

  public void setVehicle_id(long vehicle_id) {
    this.vehicle_id = vehicle_id;
  }

  public LocalDate getDebut() {
    return debut;
  }

  public void setDebut(LocalDate debut) {
    this.debut = debut;
  }

  public LocalDate getFin() {
    return fin;
  }

  public void setFin(LocalDate fin) {
    this.fin = fin;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Reservation{" +
            "client_id=" + client_id +
            ", vehicle_id=" + vehicle_id +
            ", debut=" + debut +
            ", fin=" + fin +
            '}';
  }
}
