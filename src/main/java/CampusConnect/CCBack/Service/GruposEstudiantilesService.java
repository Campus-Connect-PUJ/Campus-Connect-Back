package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Repository.GrupoEstudiantilRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GruposEstudiantilesService {
    @Autowired
    private GrupoEstudiantilRepository repository;

    @GetMapping("/grupos_estudiantiles")
    public Iterable<GrupoEstudiantil> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("grupo_Estudiantil/{id}/caracteristicas")
    public List<Caracteristica> conseguirCaracteristicasGrupo(@PathVariable("id") Long id) {
        return repository.findById(id).get().getCaracteristicas();
    }

    @GetMapping("/grupos_estudiantiles/{id}")
    public GrupoEstudiantil findForoById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @PutMapping("/crear_grupo_estudiantil")
    public GrupoEstudiantil pruebaCreacionUsuario() {
        GrupoEstudiantil ug = new GrupoEstudiantil();

        return repository.save(ug);
    }
}
