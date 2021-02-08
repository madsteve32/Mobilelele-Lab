package mobilelele.app.services;

import org.springframework.stereotype.Service;

@Service
public interface ModelService {
    String readModelFileContent();

    String importModels();
}
