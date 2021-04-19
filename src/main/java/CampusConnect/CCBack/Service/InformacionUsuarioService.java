package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.InformacionUsuarioRepository;
import CampusConnect.CCBack.Wrappers.WrapperInformacionUsuario;

@Service
public class InformacionUsuarioService {

    @Autowired
    private InformacionUsuarioRepository repository;

    @Autowired
    private UsuarioGeneralService uService;

    @Autowired
    private CarreraService cService;

    public Iterable<InformacionUsuario> findAll() {
        return repository.findAll();
    }

    public InformacionUsuario findById(Long id) {
        return repository.findById(id).get();
    }

    // public InformacionUsuario create(
    //     final InformacionUsuario data,
    //     Long id
    //     ) {
    //     InformacionUsuario iu = new InformacionUsuario();
    //     UsuarioGeneral ug = uService.findById(id);

    //     iu.setFechaNacimiento(data.getFechaNacimiento());
    //     iu.setIdentidadGenero(data.getIdentidadGenero());
    //     iu.setLugarOrigen(data.getLugarOrigen());
    //     iu.setRaza(data.getRaza());
    //     iu.setUsuario(ug);

    //     repository.save(iu);

    //     ug.setInformacionUsuario(iu);
    //     return repository.save(iu);
    // }

    public InformacionUsuario cargarInformacionUsuario(
        final WrapperInformacionUsuario data,
        Long id
        ) {
        UsuarioGeneral ug = uService.findById(id);

        InformacionUsuario iu = new InformacionUsuario();

        iu.setUsuario(ug);
        iu.setFechaNacimiento(data.getFechaNacimiento());

        iu.setIdentidadReligiosa(data.getReligion());
        iu.setLugarOrigen(data.getLocal());
        iu.setIdentidadEtnica(data.getGrupoEtnico());
        iu.setIdentidadSexo(data.getSexo());
        iu.setIdentidadGenero(data.getGenero());

        for(Long idCar: data.getCarreras()) {
            cService.agregarCarrera(idCar, ug);
        }

        return repository.save(iu);
    }
}
