package pl.coderslab.charity.Donation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

private final DonationRepository donationRepository;

public long countTotalBags() {
    return donationRepository.sumBags();
}

public long countAllDonations() {
    return donationRepository.count();
}


}
