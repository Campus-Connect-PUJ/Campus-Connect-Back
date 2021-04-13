package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.ReglasDeAsociacion;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ReglasDeAsociacionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reglas")
class ReglasDeAsociacionService {
    @Autowired
    private ReglasDeAsociacionRepository repository;

    @Autowired
    private TipoAprendizajeService servicioTipoAprendizaje;

    @Autowired
    private UsuarioGeneralService servicioUsuarios;

    @Autowired
    private TipsService servicioTips;

    @GetMapping("all")
    public Iterable<ReglasDeAsociacion> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ReglasDeAsociacion findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public ReglasDeAsociacion crearReglaDeAsociacion(@RequestBody final ReglasDeAsociacion reglaData) {
        ReglasDeAsociacion regla = new ReglasDeAsociacion();
        
        regla.setAntecedentes(reglaData.getAntecedentes());
        regla.setConsecuencias(reglaData.getConsecuencias());
        regla.setSoporte(reglaData.getSoporte());
        regla.setConfianza(reglaData.getConfianza());
        regla.setLift(reglaData.getLift());
  
        return repository.save(regla);
    }

    @GetMapping("usuario/{id}")
    public Tip obtenerRecomendacionTip(@PathVariable("id") final Long id){
        
        long idTip = 11;
        UsuarioGeneral ug = servicioUsuarios.findById(id);
        List<Tip> tipsGustadosUsuario = ug.getTipsGustados();
        List<TipoAprendizaje> estilosAprendizaje = new ArrayList<TipoAprendizaje>();
        List<ReglasDeAsociacion> reglas = (List<ReglasDeAsociacion>) this.findAll();
        Tip tipRecomendado = new Tip();

        long a = 4;
        estilosAprendizaje.add(servicioTipoAprendizaje.findById(a));
        a = 3;
        estilosAprendizaje.add(servicioTipoAprendizaje.findById(a));
        ug.setEstilosAprendizaje(estilosAprendizaje);

        if(reglas.size()>0){
            //Cuando haya reglas
            System.out.println("a");
                    //crearListaTipsGustados(tipsGustadosUsuario);
            List<String> tipsUsuario = new ArrayList<String>();
            tipsUsuario.add("2");
            tipsUsuario.add("4");
            tipsUsuario.add("6");
            tipsUsuario.add("8");

            List<String> tipsRegla = new ArrayList<String>();
            tipsRegla.add("1");
            tipsRegla.add("2");
            tipsRegla.add("3");
            tipsRegla.add("4");
            tipsRegla.add("5");

            int iguales = obtenerReglas(tipsUsuario, tipsRegla, tipsUsuario.size());
            if(iguales > 0){
                System.out.println("iguales" + iguales);
                tipRecomendado = servicioTips.findTipById(idTip);
            }
            tipRecomendado = servicioTips.findTipById(idTip);
            return tipRecomendado;


        }
        else{
            //Cuando no
            System.out.println("b");
            tipsGustadosUsuario = obtenerTipsPorTipo(ug);
            try {
                for(int i=0; i<tipsGustadosUsuario.size(); i++){
                    System.out.println(tipsGustadosUsuario.get(i).getDescripcion()+" "+tipsGustadosUsuario.get(i).getPuntaje());
                }
                tipRecomendado = tipsGustadosUsuario.get(0);
            } catch (Exception e) {
                //TODO: handle exception
                tipRecomendado = servicioTips.findTipById(idTip);
            }
            return tipRecomendado;
        }


    }




//Adicionales----------------------------------------------------------------

    public int obtenerReglas(List<String> tipsUsuario, List<String> tipsRegla, int iguales){
        
        if(contieneTodos(tipsUsuario, tipsRegla) && iguales>0){
            return iguales;
        }
        else{
            List<String> nuevosTips = new ArrayList<String>();
            for(int i=0; i<tipsUsuario.size()-1; i++){
                nuevosTips.add(tipsUsuario.get(i));
            }
            iguales = obtenerReglas(nuevosTips, tipsRegla, iguales-1);
        }
        return iguales;
    }

    public boolean contieneTodos(List<String> tipsUsuario, List<String> tipsRegla){
        int iguales = 0;
        for(int i=0; i<tipsUsuario.size(); i++){
            for(int j=0; j<tipsRegla.size(); j++){
                if(tipsUsuario.get(i) == tipsRegla.get(j)){
                    iguales++;
                }
            }
        }
        if(iguales == tipsUsuario.size()){
            return true;
        }
        return false;
    }

    public List<String> crearListaTipsGustados(List<Tip> tips){  
        List<String> tipsString = new ArrayList<String>();
        for(int i=0; i<tips.size(); i++){
            tipsString.add(String.valueOf(tips.get(i).getId()));
        }
        return tipsString;
    }

    public List<Tip> ordenarLista(List<Tip> tips){
        List<Tip> tipsOrdenados = new ArrayList<Tip>();
        Tip[] miarray = new Tip[tips.size()];
        miarray = tips.toArray(miarray);
        Arrays.sort(miarray);
        tipsOrdenados = Arrays.asList(miarray);

        return tipsOrdenados;
    }

//Cuando no haya reglas 

    public List<Tip> obtenerTipsPorTipo(UsuarioGeneral ug){
        List<Tip> tipsRecomendar = new ArrayList<Tip>();
        List<Tip> tipsSistema = (List<Tip>) this.servicioTips.findAllTips();

        for(int i=0; i<ug.getEstilosAprendizaje().size(); i++){
            for(int j=0; j<tipsSistema.size(); j++){
                for(int k=0; k<tipsSistema.get(j).getTiposAprendizaje().size(); k++){
                    if(tipsSistema.get(j).getTiposAprendizaje().get(k).getId() == ug.getEstilosAprendizaje().get(i).getId() && !tipsRecomendar.contains(tipsSistema.get(j)) && !ug.getTipsGustados().contains(tipsSistema.get(j)) && !ug.getTipsNoGustados().contains(tipsSistema.get(j))){
                        tipsRecomendar.add(tipsSistema.get(j));
                    }
                }
            }
        }

        tipsRecomendar = ordenarLista(tipsRecomendar);
        
        return tipsRecomendar;
    }

}
