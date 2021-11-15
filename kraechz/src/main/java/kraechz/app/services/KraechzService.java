package kraechz.app.services;

import java.util.List;

import kraechz.app.models.Kraechz;
import kraechz.app.repositories.KraechzRepository;
import org.springframework.stereotype.Service;

@Service
public class KraechzService {
  private final KraechzRepository repository;

  public KraechzService(KraechzRepository repository) {
    this.repository = repository;
  }

  public List<Kraechz> kraechzeAnzeigen(String filter) {
    if (filter == null || filter.equals("all")) {
      return repository.alle();
    }
    return repository.filtereNachHandle(filter);
  }

  public void kraechze(Kraechz kraechz) {
    repository.save(kraechz);
  }
}
