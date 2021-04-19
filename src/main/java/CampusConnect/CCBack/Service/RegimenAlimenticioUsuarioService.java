package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.RegimenAlimenticioUsuario;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.RegimenAlimenticioUsuarioRepository;

@Service
public class RegimenAlimenticioUsuarioService {

    @Autowired
    private RegimenAlimenticioUsuarioRepository repository;

    public Iterable<RegimenAlimenticioUsuario> findAll() {
        return repository.findAll();
    }

    public RegimenAlimenticioUsuario findById(Long id) {
        return repository.findById(id).get();
    }

    public RegimenAlimenticioUsuario create(
        final RegimenAlimenticio dato,
        final int exigencia,
        final UsuarioGeneral ug) {

        RegimenAlimenticioUsuario c = new RegimenAlimenticioUsuario();
        c.setRegimenAlimenticio(dato);
        c.setExigencia(exigencia);
        c.setUsuario(ug);
        return repository.save(c);
    }

}
