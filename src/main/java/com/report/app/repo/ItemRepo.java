package com.report.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.report.app.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{

}
