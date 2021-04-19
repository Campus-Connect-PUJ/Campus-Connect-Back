package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Repository.RegimenAlimenticioRepository;

@Service
public class RegimenAlimenticioService {

    @Autowired
    private RegimenAlimenticioRepository repository;

    public Iterable<RegimenAlimenticio> findAll() {
        return repository.findAll();
    }

    public RegimenAlimenticio findById(Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public RegimenAlimenticio create(@RequestBody final RegimenAlimenticio dato) {
        RegimenAlimenticio c = new RegimenAlimenticio();
        c.setTipo(dato.getTipo());
        return repository.save(c);
    }

}
