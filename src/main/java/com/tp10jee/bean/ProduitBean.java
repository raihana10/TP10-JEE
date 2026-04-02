package com.tp10jee.bean;

import com.tp10jee.model.Produit;
import com.tp10jee.service.ProduitService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProduitBean implements Serializable {

    @Inject
    ProduitService service;

    private Produit produit = new Produit();
    private List<Produit> produits;

    public List<Produit> getProduits() {
        produits = service.getProduits();
        return produits;
    }

    public Produit getProduit() { return produit; }

    public String save() {
        service.createProduit(produit);
        produit = new Produit();
        return "listeProduit?faces-redirect=true";
    }

    public void delete(Long id) {
        service.deleteProduit(id);
    }
}