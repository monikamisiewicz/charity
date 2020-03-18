package pl.coderslab.charity.Institution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public List<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }

    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    public Institution findById(long id) {
        return institutionRepository.findById(id).get();
    }

    public void deleteById(long id) {
        institutionRepository.deleteById(id);
    }

    public List<Institution> findByName(String name) {
        return institutionRepository.findByNameStartsWith(name);
    }

}
