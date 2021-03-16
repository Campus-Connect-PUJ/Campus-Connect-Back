package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UsuarioGeneralService {
    @Autowired
    private UsuarioGeneralRepository repository;

    // esto probablemente sea mejor quitarlo, pero puede ser util para pruebas
    @GetMapping("/usuarios")
    public Iterable<UsuarioGeneral> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioGeneral findForoById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/prueba")
    public String prueba() {
        return "hola";
    }

    @GetMapping("/pruebaUsuario")
    public UsuarioGeneral pruebaCreacionUsuario() {
        UsuarioGeneral ug = new UsuarioGeneral(
            "pruebaDaniel",
            "correo_prueba@prueba.com",
            11,
            null,
            null
            );

        // ug.setCaracteristicas();
        // ug.setCarrera("sistemas");
        // ug.setCorreo("estoEsUnCorreo@hola.com");
        // List<TipoAprendizaje> ta = new ArrayList<>();
        // ta.add(new TipoAprendizaje());
        // ug.setEstiloAprendizaje(ta);
        // ug.setNombre("pruebaDaniel");
        // ug.setSemestre(11);

        return repository.save(ug);
    }
}
