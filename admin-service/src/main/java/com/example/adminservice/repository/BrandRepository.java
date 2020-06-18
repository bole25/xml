package com.example.adminservice.repository;

import com.example.adminservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query(value = "select * from brand where name = ?1", nativeQuery = true)
    Brand findBrand(String name);

    @Query(value = "delete from brand where name = ?1", nativeQuery = true)
    void deleteBrand(String name);

    @Query(value = "select * from brand where id =" +
            " (select brand_id from brand_models where models_id = " +
            "(select id from model where name = ?1))", nativeQuery = true)
    Brand findBrandForModelDelete(String asd);

    @Query(value = "select * from brand", nativeQuery = true)
    Set<Brand> getAll();
}
