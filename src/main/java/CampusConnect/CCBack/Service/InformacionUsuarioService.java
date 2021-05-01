package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public InformacionUsuario findById(final Long id) {
        return repository.findById(id).get();
    }

    public InformacionUsuario cargarInformacionUsuario(
        final WrapperInformacionUsuario data,
        final String email
        ) {
        final UsuarioGeneral ug = uService.findByEmail(email);

        final InformacionUsuario iu = new InformacionUsuario();

        iu.setUsuario(ug);
        iu.setFechaNacimiento(data.getFechaNacimiento());

        iu.setIdentidadReligiosa(data.getReligion());
        iu.setLugarOrigen(data.getLocal());
        iu.setIdentidadEtnica(data.getGrupoEtnico());
        iu.setIdentidadSexo(data.getSexo());
        iu.setIdentidadGenero(data.getGenero());

        for(final Long idCar: data.getCarreras()) {
            cService.agregarCarrera(idCar, ug);
        }

        return repository.save(iu);
    }
}
