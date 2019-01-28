package com.mexpedition.dao;

import com.mexpedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpeditionDao extends JpaRepository<Expedition, Integer> {
}
