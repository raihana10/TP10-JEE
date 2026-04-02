package com.tp10jee.service;

import com.tp10jee.dao.ProduitDAO;
import com.tp10jee.model.Produit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProduitService {

    @Inject
    private ProduitDAO dao;

    public List<Produit> getProduits() {
        return dao.findAll();
    }

    public void createProduit(Produit p) {
        dao.save(p);
    }

    public void deleteProduit(Long id) {
        dao.delete(id);
    }
}