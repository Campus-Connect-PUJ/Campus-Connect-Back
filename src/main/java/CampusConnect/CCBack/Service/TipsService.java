package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Repository.TipRepository;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TipsService {
    @Autowired
    private TipRepository repository;

    @GetMapping("/tips")
    public Iterable<Tip> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("/tips/{id}")
    public Tip findTipById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }
}
