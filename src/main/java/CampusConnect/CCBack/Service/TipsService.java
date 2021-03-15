package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Repository.TipRepository;

@RestController
class TipsService {
    @Autowired
    private TipRepository repository;

    @GetMapping("/tips")
    public Iterable<Tip> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("/tip/{id}")
    public Tip findForoById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }
}
