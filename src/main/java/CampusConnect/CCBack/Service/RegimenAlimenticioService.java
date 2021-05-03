package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Repository.RegimenAlimenticioRepository;

@Service
public class RegimenAlimenticioService {

    @Autowired
    private RegimenAlimenticioRepository repository;

    public Iterable<RegimenAlimenticio> findAll() {
        return GenericService.findAll(repository);
    }

    public RegimenAlimenticio findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public RegimenAlimenticio create(final RegimenAlimenticio dato) {
        RegimenAlimenticio c = new RegimenAlimenticio();
        c.setTipo(dato.getTipo());
        return GenericService.create(repository, c);
    }

}
