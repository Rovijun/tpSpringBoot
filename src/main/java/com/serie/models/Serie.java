package com.serie.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "serie")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    @NotNull(message = "Le champ nom ne peut être null")
    @NotBlank(message = "Le champ nom ne peut être vide")
    private String nom;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "dateSortie")
    @NotNull(message = "Le champ date de sortie ne peut être null")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateSortie;

    @OneToMany(mappedBy="serie", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Saison> saisons;

    public Serie () {

    }

    public Serie(String nom, Double prix, Date dateSortie, List<Saison> saisons) {
        this.nom = nom;
        this.prix = prix;
        this.dateSortie = dateSortie;
        this.saisons = saisons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public List<Saison> getSaisons() {
        return saisons;
    }

    public void setSaisons(List<Saison> saisons) {
        this.saisons = saisons;
    }
}
