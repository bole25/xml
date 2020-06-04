package com.example.adminservice.repository;

import com.example.adminservice.model.Brand;
import com.example.adminservice.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    @Query(value = "select * from model where name = ?1", nativeQuery = true)
    Model findModel(String name);

    void deleteByName(String name);
}
