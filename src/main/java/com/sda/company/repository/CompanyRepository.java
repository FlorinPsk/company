package com.sda.company.repository;

import com.sda.company.model.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer>,
        PagingAndSortingRepository<Company, Integer> {

    Optional<Company> findByName(String name);


    // We use this custom method when we don't have (for example) a method like findByName
    @Query("SELECT c FROM Company c WHERE c.name = :name AND c.registrationNumber = :registrationNumber")
    Optional<Company> findByNameAndRegistrationNumber(@Param("name") String name,
                                                      @Param("registrationNumber") Long registrationNumber);

}
