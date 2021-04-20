package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ResenhaGrupoEstudiantilRepository;

@RestController
public class ResenhaGrupoEstudiantilService {

    @Autowired
    private ResenhaGrupoEstudiantilRepository repository;

    @Autowired
    private GruposEstudiantilesService geService;

    public Iterable<ResenhaGrupoEstudiantil> findAll() {
        return repository.findAll();
    }

    public ResenhaGrupoEstudiantil findById(Long id) {
        return repository.findById(id).get();
    }

    public ResenhaGrupoEstudiantil create(
        final UsuarioGeneral ug,
        final ResenhaGrupoEstudiantil foroData,
        final Long idGrupoEstudiantil
        ) {
        ResenhaGrupoEstudiantil rr = new ResenhaGrupoEstudiantil();
        GrupoEstudiantil grupoEstudiantil = geService.findById(idGrupoEstudiantil);
        rr.setEstrellas(foroData.getEstrellas());
        rr.setGrupoEstudiantil(grupoEstudiantil);
        rr.setUsuario(ug);
        return repository.save(rr);
    }

}
