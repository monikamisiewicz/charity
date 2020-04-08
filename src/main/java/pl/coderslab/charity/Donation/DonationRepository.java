package pl.coderslab.charity.Donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.Institution.Institution;
import pl.coderslab.charity.User.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

@Query("SELECT SUM(d.quantity) FROM Donation d ")
public Long sumBags();

List<Donation> findAllByUser(User user);

}
