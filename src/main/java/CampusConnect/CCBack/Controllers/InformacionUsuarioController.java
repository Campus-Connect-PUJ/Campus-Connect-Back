package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Service.InformacionUsuarioService;

@RestController
@RequestMapping("/informacion_usuario")
class InformacionUsuarioController {

    @Autowired
    private InformacionUsuarioService iuService;

    @GetMapping("all")
    public Iterable<InformacionUsuario> findAllForos() {
        return iuService.findAll();
    }

}
