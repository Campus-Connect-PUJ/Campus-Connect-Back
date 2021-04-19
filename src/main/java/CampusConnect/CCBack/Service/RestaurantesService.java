package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Lugar;
import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Model.TipoRestaurante;
import CampusConnect.CCBack.Repository.RestauranteRepository;
import CampusConnect.CCBack.Wrappers.WrapperRestaurante;

@Service
public class RestaurantesService {

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

    public Iterable<Restaurante> findAll() {
        return repository.findAll();
    }

    public Restaurante findById(Long id) {
        return repository.findById(id).get();
    }

    public List<TipoRestaurante> conseguirTiposRestaurante(Long id) {
        return repository.findById(id).get().getTiposRestaurante();
    }

    public List<TipoComida> conseguirTiposComidaRestaurante(Long id) {
        return repository.findById(id).get().getTiposComida();
    }

    public List<RegimenAlimenticio> conseguirRegimenesAlimenticiosRestaurante(Long id) {
        return repository.findById(id).get().getRegimenesAlimenticios();
    }

    public List<ResenhaRestaurante> conseguirResenhasRestaurante(Long id) {
        return repository.findById(id).get().getResenhas();
    }

    public Restaurante create(final WrapperRestaurante dato) {
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
            RegimenAlimenticio c = raService.findById(a);
            res.agregarRegimenAlimenticio(c);
            c.agregarRestaurante(res);
        }

        for (Long a : dato.getTiposComida()) {
            TipoComida c = tcService.findById(a);
            res.agregarTipoComida(c);
            c.agregarRestaurante(res);
        }

        for (Long a : dato.getTiposRestaurante()) {
            TipoRestaurante c = trService.findById(a);
            res.agregarTipoRestaurante(c);
            c.agregarRestaurante(res);
        }

        return repository.save(res);
    }

}
