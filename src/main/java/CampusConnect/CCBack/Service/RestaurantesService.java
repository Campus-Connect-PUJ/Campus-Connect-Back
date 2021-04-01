package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Lugar;
import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Model.TipoRestaurante;
import CampusConnect.CCBack.Repository.RestauranteRepository;
import CampusConnect.CCBack.Wrappers.WrapperRestaurante;

@RestController
@RequestMapping("/restaurante")
class RestaurantesService {
    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private LugarService lService;

    @Autowired
    private RegimenAlimenticioService raService;

    @Autowired
    private TipoComidaService tcService;

    @Autowired
    private TipoRestauranteService trService;

    @GetMapping("all")
    public Iterable<Restaurante> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Restaurante findForoById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/tipos")
    public List<TipoRestaurante> conseguirTiposRestaurante(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTiposRestaurante();
    }

    @GetMapping("{id}/tipos_comida")
    public List<TipoComida> conseguirTiposComidaRestaurante(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTiposComida();
    }

    @GetMapping("{id}/regimenes_alimenticios")
    public List<RegimenAlimenticio> conseguirRegimenesAlimenticiosRestaurante(
        @PathVariable("id") Long id) {
        return repository.findById(id).get().getRegimenesAlimenticios();
    }

    @GetMapping("{id}/resenhas")
    public List<ResenhaRestaurante> conseguirResenhasRestaurante(@PathVariable("id") Long id) {
        return repository.findById(id).get().getResenhas();
    }

    @PostMapping
    public Restaurante create(@RequestBody final WrapperRestaurante dato) {
        Restaurante res = new Restaurante();

        Restaurante rd = dato.getRestaurante();
        Lugar lugar = lService.findById(dato.getIdLugar());
        res.setLugar(lugar);

        res.setAmbientacion(rd.getAmbientacion());
        res.setDescripcion(rd.getDescripcion());
        res.setDescripcionLugar(rd.getDescripcionLugar());
        res.setFranquicia(rd.getFranquicia());
        res.setNombre(rd.getNombre());
        res.setPrecioMax(rd.getPrecioMax());
        res.setPrecioMin(rd.getPrecioMin());
        res.setTiempoEntrega(rd.getTiempoEntrega());

        repository.save(res);

        // listas

        // TODO: poner esto en funciones distintas
        for (Long a : dato.getRegimenesAlimenticios()) {
            System.out.println(a);
            RegimenAlimenticio c = raService.findById(a);
            res.agregarRegimenAlimenticio(c);
            // c.agregarGrupoEstudiantil(ug);
        }

        for (Long a : dato.getTiposComida()) {
            System.out.println(a);
            TipoComida c = tcService.findById(a);
            res.agregarTipoComida(c);
            // c.agregarGrupoEstudiantil(ug);
        }

        for (Long a : dato.getTiposRestaurante()) {
            System.out.println(a);
            TipoRestaurante c = trService.findById(a);
            res.agregarTipoRestaurante(c);
            // c.agregarGrupoEstudiantil(ug);
        }

        return repository.save(res);
    }

}
