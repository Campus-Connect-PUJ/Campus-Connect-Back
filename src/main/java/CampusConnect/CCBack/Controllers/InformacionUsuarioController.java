package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Service.InformacionUsuarioService;
import CampusConnect.CCBack.Wrappers.WrapperInformacionUsuario;

@RestController
@RequestMapping("/informacion_usuario")
class InformacionUsuarioController {

    @Autowired
    private InformacionUsuarioService iuService;

    @GetMapping("all")
    public Iterable<InformacionUsuario> findAllForos() {
        return iuService.findAll();
    }

    @GetMapping("{id}")
    public InformacionUsuario findById(@PathVariable("id") Long id) {
        return iuService.findById(id);
    }

    @PostMapping("{id}")
    public InformacionUsuario cargarInformacionUsuario(
        @RequestBody final WrapperInformacionUsuario data,
        @PathVariable("id") Long id
        ) {
            return iuService.cargarInformacionUsuario(data, id);
    }
}
