package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Facultad;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Requisito;
import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Repository.GrupoEstudiantilRepository;
import CampusConnect.CCBack.Wrappers.WrapperGrupoEstudiantil;

@Service
public class GruposEstudiantilesService {

    @Autowired
    private GrupoEstudiantilRepository repository;

    @Autowired
    private CaracteristicasService cService;

    @Autowired
    private TematicasService tService;

    @Autowired
    private FacultadesService fService;

    @Autowired
    private RequisitoService rService;

    public Iterable<GrupoEstudiantil> findAll() {
        return repository.findAll();
    }

    public GrupoEstudiantil findById(final Long id) {
        return repository.findById(id).get();
    }

    public GrupoEstudiantil create(final WrapperGrupoEstudiantil dato) {
        GrupoEstudiantil ug = new GrupoEstudiantil();

        ug.setNombre(dato.getGrupoEstudiantil().getNombre());
        ug.setDescripcion(dato.getGrupoEstudiantil().getDescripcion());
        ug.setCalificacion(5);

        repository.save(ug);

        // listas

        // TODO: poner esto en funciones distintas
        for (Long a : dato.getCaracteristicas()) {
            Caracteristica c = cService.findById(a);
            ug.agregarCaracteristica(c);
            c.agregarGrupoEstudiantil(ug);
        }

        for (Long a : dato.getTematicas()) {
            Tematica c = tService.findById(a);
            ug.agregarTematica(c);
            c.agregarGrupoEstudiantil(ug);
        }

        for (Long a : dato.getFacultades()) {
            Facultad c = fService.findById(a);
            ug.agregarFacultad(c);
            c.agregarGrupoEstudiantil(ug);
        }

        for (Long a : dato.getRequisitos()) {
            Requisito c = rService.findById(a);
            ug.agregarRequisito(c);
            c.agregarGrupoEstudiantil(ug);
        }

        return repository.save(ug);
    }

    public void agregarRequisito(final Long idge, final Long idr) {
        Requisito c = rService.findById(idr);
        GrupoEstudiantil ge = this.findById(idr);
        ge.agregarRequisito(c);
        c.agregarGrupoEstudiantil(ge);
    }
}
