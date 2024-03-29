package CampusConnect.CCBack.Controllers;

import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Service.TipsService;
import CampusConnect.CCBack.Wrappers.WrapperTip;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tip")
class TipsController {

    @Autowired
    private TipsService tService;

    @GetMapping("all")
    public Iterable<Tip> findAll() {
        return tService.findAll();
    }

    @GetMapping("{id}")
    public Tip findTipById(@PathVariable("id") Long id) {
        return tService.findById(id);
    }

    @GetMapping("{id}/tipos_aprendizaje")
    public List<TipoAprendizaje> conseguirTiposAprendizajeTip(@PathVariable("id") Long id) {
        return tService.findById(id).getTiposAprendizaje();
    }

    @GetMapping("{id}/usuarios_gustaron")
    public List<UsuarioGeneral> conseguirUsuariosGustaronTip(@PathVariable("id") Long id) {
        return tService.findById(id).getUsuariosGustaron();
    }

    @PostMapping
    public Tip crear(
        @RequestBody final WrapperTip data
    ) {
        return tService.create(data);
    }

    @PutMapping("borrarTip/{idTip}")
    public void borrarTip(
        @PathVariable("idTip") final Long idTip,
        @AuthenticationPrincipal String email
    ){
        tService.borrarTip(idTip, email);
    }


    @PutMapping("tipsGustados/{idTip}")
    public UsuarioGeneral agregarTipGustado(
        @AuthenticationPrincipal String email,
        @PathVariable("idTip") final Long idTip
    ){
        return tService.agregarTipGustado(email, idTip);

    }

    @PutMapping("tipsNoGustados/{idTip}")
    public UsuarioGeneral agregarTipNoGustado(
        @AuthenticationPrincipal String email,
        @PathVariable("idTip") final Long idTip
    ){
        return tService.agregarTipNoGustado(email, idTip);

    }

    @PutMapping("sumar/{id}")
    public Tip sumarVotoAForo(
        @PathVariable("id") final Long idTip
    ){
        return tService.sumarVotoAForo(idTip);
    }

    @PutMapping("restar/{id}")
    public Tip restarVotoAForo(
        @PathVariable("id") final Long idForo
    ){
        return tService.restarVotoAForo(idForo);
    }

}
