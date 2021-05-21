package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ResenhaGrupoEstudiantilRepository;

@RestController
public class ResenhaGrupoEstudiantilService {

    @Autowired
    private ResenhaGrupoEstudiantilRepository repository;

    @Autowired
    private GruposEstudiantilesService geService;

    public Iterable<ResenhaGrupoEstudiantil> findAll() {
        return GenericService.findAll(repository);
    }

    public ResenhaGrupoEstudiantil findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public ResenhaGrupoEstudiantil create(
        final UsuarioGeneral ug,
        final ResenhaGrupoEstudiantil resenha,
        final Long idGrupoEstudiantil
        ) {
        ResenhaGrupoEstudiantil rr = new ResenhaGrupoEstudiantil();
        GrupoEstudiantil grupoEstudiantil = geService.findById(idGrupoEstudiantil);
        rr.setEstrellas(resenha.getEstrellas());
        rr.setGrupoEstudiantil(grupoEstudiantil);
        rr.setUsuario(ug);
        return GenericService.create(repository, rr);
    }

}
