package com.example.porfoliotest.repository;

import com.example.porfoliotest.model.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface FonctionRepository extends JpaRepository<Fonction,Long> {


}
