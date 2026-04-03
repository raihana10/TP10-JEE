package com.tp10jee.bean;

import com.tp10jee.model.Produit;
import com.tp10jee.service.ProduitService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProduitBean implements Serializable {

    @Inject
    private ProduitService service;

    private Produit produit = new Produit();
    private List<Produit> produits;
    private Produit selected; // produit sélectionné avant confirmation de suppression

    // Chargement une seule fois
    public List<Produit> getProduits() {
        if (produits == null) {
            produits = service.getProduits();
        }
        return produits;
    }

    // Ajout d'un produit
    public void save() {
        service.createProduit(produit);
        if (produits != null) {
            produits.add(produit); // mise à jour de la liste sans rechargement
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Article ajouté !"));
        produit = new Produit(); // reset du formulaire
    }

    // Suppression d'un produit
    public void delete(Long id) {
        service.deleteProduit(id);
        if (produits != null) {
            produits.removeIf(p -> p.getId().equals(id)); // retirer de la liste locale
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Supprimé", "Article supprimé !"));
    }

    public void prepareEdit(Produit p) {
        this.produit = p; // charger le produit dans le formulaire
    }

    public void update() {
        service.updateProduit(produit);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifié", "Article mis à jour !"));
        produit = new Produit();
        produits = null; // forcer le rechargement
    }

    // Getters & Setters
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
    public void setProduits(List<Produit> produits) { this.produits = produits; }
    public Produit getSelected() { return selected; }
    public void setSelected(Produit selected) { this.selected = selected; }
}