package pl.coderslab.charity.DonationStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }
}
