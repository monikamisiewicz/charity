package pl.coderslab.charity.Institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query("select i from Institution i where i.name like ?1%")
    List<Institution> findByNameStartsWith(String name);
}
