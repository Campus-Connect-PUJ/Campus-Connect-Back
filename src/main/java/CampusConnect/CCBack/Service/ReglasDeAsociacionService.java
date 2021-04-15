package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.APrueba;
import CampusConnect.CCBack.Model.ReglasDeAsociacion;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ReglasDeAsociacionRepository;
import CampusConnect.CCBack.Wrappers.WrapperReglaAsociacion;

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
    public ReglasDeAsociacion crearReglaDeAsociacion(@RequestBody final WrapperReglaAsociacion reglaData) {
        ReglasDeAsociacion regla = new ReglasDeAsociacion();
        
        regla.setSoporte(reglaData.getSoporte());
        regla.setConfianza(reglaData.getConfianza());
        regla.setLift(reglaData.getLift());
        
        for(int i=0; i<reglaData.getAntecedentes().size(); i++){
            Tip a = this.servicioTips.findTipById(reglaData.getAntecedentes().get(i));
            regla.agregarAntecedentes(a);
        }

        for(int i=0; i<reglaData.getConsequents().size(); i++){
            Tip b = this.servicioTips.findTipById(reglaData.getConsequents().get(i));
            regla.agregarConsecuentes(b);
        }

        return repository.save(regla);
    }

    @GetMapping("usuario/{id}")
    public Tip obtenerRecomendacionTip(@PathVariable("id") final Long id){
        long idTip = 15;
        UsuarioGeneral ug = servicioUsuarios.findById(id);
        List<Tip> tipsGustadosUsuario = ug.getTipsGustados();
        List<TipoAprendizaje> estilosAprendizaje = new ArrayList<TipoAprendizaje>();
        List<ReglasDeAsociacion> reglas = (List<ReglasDeAsociacion>) this.findAll();
        Tip tipRecomendado = new Tip();



        long a = 5;
        estilosAprendizaje.add(servicioTipoAprendizaje.findById(a));
        a = 6;
        estilosAprendizaje.add(servicioTipoAprendizaje.findById(a));
        ug.setEstilosAprendizaje(estilosAprendizaje);



        if(ug.getTipsGustados().size()>0){
            //Cuando haya reglas
            List<Tip> tipsUsuario = ug.getTipsGustados();
            List<Tip> tipsRegla = new ArrayList<Tip>();
            
            List<APrueba> reglasConPuntaje = new ArrayList<APrueba>();
            ReglasDeAsociacion regla = new ReglasDeAsociacion();

            for(int i=0; i<reglas.size(); i++){
                regla = reglas.get(i);
                int cantidadIguales = this.contieneTodos(tipsUsuario, regla.getAntecedentes());
                if(cantidadIguales > 0){
                    APrueba nuevo = new APrueba();
                    nuevo.setPuntaje(cantidadIguales * regla.getLift()); //Se tiene encuenta no solo la cantidad de 
                    nuevo.setRegla(regla);
                    reglasConPuntaje.add(nuevo);
                    
                }
            }
            reglasConPuntaje = ordenarReglasPorPuntaje(reglasConPuntaje);

            for(int i=0; i<reglasConPuntaje.size(); i++){
                for(int j=0; j<reglasConPuntaje.get(i).getRegla().getConsecuencias().size(); j++){
                    Tip agregar = reglasConPuntaje.get(i).getRegla().getConsecuencias().get(j);
                    if(!tipsRegla.contains(agregar) && !ug.getTipsGustados().contains(agregar) && !ug.getTipsNoGustados().contains(agregar)){
                        tipsRegla.add(agregar);
                    }
                }
            }
            
            try {
                //Intento #1: Por las reglas
                tipRecomendado = tipsRegla.get(0);

            } catch (Exception e) {
                try{
                    //Intento #2: Por los estilos de aprendizaje
                    tipsGustadosUsuario = obtenerTipsPorTipo(ug);
                    tipRecomendado = tipsGustadosUsuario.get(0);
                }
                catch (Exception e2){
                    //Intento #3: El siguiente tip al ultimo
                    tipRecomendado = ultimoRecurso(ug);
                }
            }

            
            return tipRecomendado;
        }
        else{
            //Cuando no
            tipsGustadosUsuario = obtenerTipsPorTipo(ug);
            try {
                tipRecomendado = tipsGustadosUsuario.get(0);
            } catch (Exception e) {
                //TODO: handle exception
                tipRecomendado = ultimoRecurso(ug);
            }
            return tipRecomendado;
        }


    }


//Adicionales----------------------------------------------------------------

    public Tip ultimoRecurso(UsuarioGeneral ug){
        long idTipParaRecomendar = 0;
        List<Tip> tipsSistema = (List<Tip>) this.servicioTips.findAllTips();
        boolean sale = false;
        Tip buscarSiExiste = new Tip();
        for(Long i= tipsSistema.get(0).getId(); i < tipsSistema.size() && !sale ; i++){
            idTipParaRecomendar = i;
            buscarSiExiste = servicioTips.findTipById(idTipParaRecomendar);
            if(!ug.getTipsNoGustados().contains(buscarSiExiste) && !ug.getTipsGustados().contains(buscarSiExiste)){
                sale = true;
            }
        }
        return buscarSiExiste;
    }

    public int contieneTodos(List<Tip> tipsUsuario, List<Tip> tipsRegla){
        int iguales = 0;

        for(int i=0; i<tipsUsuario.size(); i++){
            if(tipsRegla.contains(tipsUsuario.get(i))){
                iguales++;
            }
        }

        if(tipsUsuario.size() == tipsRegla.size()){
            iguales++;
        }

        return iguales;
    }

    public List<Tip> ordenarLista(List<Tip> tips){
        List<Tip> tipsOrdenados = new ArrayList<Tip>();
        Tip[] miarray = new Tip[tips.size()];
        miarray = tips.toArray(miarray);
        Arrays.sort(miarray);
        tipsOrdenados = Arrays.asList(miarray);

        return tipsOrdenados;
    }

    public List<APrueba> ordenarReglasPorPuntaje(List<APrueba> reglas){
        List<APrueba> reglasOrdenadas = new ArrayList<APrueba>();
        APrueba[] miarray = new APrueba[reglas.size()];
        miarray = reglas.toArray(miarray);
        Arrays.sort(miarray);
        reglasOrdenadas = Arrays.asList(miarray);

        return reglasOrdenadas;
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
