package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Facultad;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Requisito;
import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Repository.GrupoEstudiantilRepository;
import CampusConnect.CCBack.Wrappers.WrapperGrupoEstudiantil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupo_estudiantil")
class GruposEstudiantilesService {
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

    @GetMapping("all")
    public Iterable<GrupoEstudiantil> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public GrupoEstudiantil findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/caracteristicas")
    public List<Caracteristica> conseguirCaracteristicasGrupo(@PathVariable("id") Long id) {
        return repository.findById(id).get().getCaracteristicas();
    }

    @PostMapping
    public GrupoEstudiantil create(@RequestBody final WrapperGrupoEstudiantil dato) {
        GrupoEstudiantil ug = new GrupoEstudiantil();

        ug.setNombre(dato.getGrupoEstudiantil().getNombre());
        ug.setDescripcion(dato.getGrupoEstudiantil().getDescripcion());
        ug.setCalificacion(5);

        repository.save(ug);

        System.out.println("----------------------------");
        System.out.println(ug.getId());
        System.out.println("-------------");

        // listas

        // TODO: poner esto en funciones distintas
        for (Long a : dato.getCaracteristicas()) {
            // System.out.println(a);
            Caracteristica c = cService.findById(a);
            ug.agregarCaracteristica(c);
            c.agregarGrupoEstudiantil(ug);
        }

        for (Long a : dato.getTematicas()) {
            // System.out.println(a);
            Tematica c = tService.findById(a);
            ug.agregarTematica(c);
            c.agregarGrupoEstudiantil(ug);
        }

        for (Long a : dato.getFacultades()) {
            // System.out.println(a);
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

    @PostMapping("{idge}/requisito/{idr}")
    void agregarRequisito(@PathVariable("idge") Long idge, @PathVariable("idr") Long idr) {
        Requisito c = rService.findById(idr);
        GrupoEstudiantil ge = this.findById(idr);
        ge.agregarRequisito(c);
        c.agregarGrupoEstudiantil(ge);
    }
}
