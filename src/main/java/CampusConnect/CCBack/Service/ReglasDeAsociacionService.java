package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.APrueba;
import CampusConnect.CCBack.Model.ReglasDeAsociacion;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ReglasDeAsociacionRepository;
import CampusConnect.CCBack.Wrappers.WrapperPrueba;

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
    public ReglasDeAsociacion crearReglaDeAsociacion(@RequestBody final WrapperPrueba reglaData) {
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
        long algo = 1;
        UsuarioGeneral ug = servicioUsuarios.findById(algo);
        List<Tip> tipsGustadosUsuario = ug.getTipsGustados();
        List<TipoAprendizaje> estilosAprendizaje = new ArrayList<TipoAprendizaje>();
        List<ReglasDeAsociacion> reglas = (List<ReglasDeAsociacion>) this.findAll();
        Tip tipRecomendado = new Tip();

        long a = 5;
        estilosAprendizaje.add(servicioTipoAprendizaje.findById(a));
        a = 6;
        estilosAprendizaje.add(servicioTipoAprendizaje.findById(a));
        ug.setEstilosAprendizaje(estilosAprendizaje);

        if(reglas.size()>0){
            //Cuando haya reglas
            System.out.println("a");
            List<Tip> tipsUsuario = ug.getTipsGustados();

            List<Tip> tipsRegla = new ArrayList<Tip>();
            List<ReglasDeAsociacion> reglasUtiles = new ArrayList<ReglasDeAsociacion>();
            ReglasDeAsociacion regla = new ReglasDeAsociacion();

            List<APrueba> reglasConPuntaje = new ArrayList<APrueba>();
            for(int i=0; i<reglas.size(); i++){
                regla = reglas.get(i);
                if( this.contieneTodos(tipsUsuario, regla.getAntecedentes()) > 0){
                    APrueba nuevo = new APrueba();
                    nuevo.setPuntaje(this.contieneTodos(tipsUsuario, regla.getAntecedentes()));
                    nuevo.setRegla(regla);
                    reglasConPuntaje.add(nuevo);
                    
                }
            }
            reglasConPuntaje = ordenarReglasPorPuntaje(reglasConPuntaje);
            System.out.println("Cantidad de reglas "+reglasConPuntaje.size());


            for(int i=0; i<reglasConPuntaje.size(); i++){
                for(int j=0; j<reglasConPuntaje.get(i).getRegla().getConsecuencias().size(); j++){
                    //if(
                        //!ug.getTipsNoGustados().contains(reglasConPuntaje.get(0).getRegla().getConsecuencias().get(j)) && 
                        //!ug.getTipsGustados().contains(reglasConPuntaje.get(0).getRegla().getConsecuencias().get(j)) && 
                        //!tipsGustadosUsuario.contains(reglasConPuntaje.get(0).getRegla().getConsecuencias().get(j))
                    //){
                        //tipsGustadosUsuario.add(reglasConPuntaje.get(0).getRegla().getConsecuencias().get(j));
                    //}
                }

                


                
                System.out.print("-> "+i+" ->");
                for(int j=0; j<reglasConPuntaje.get(i).getRegla().getAntecedentes().size(); j++){
                    System.out.print(" "+reglasConPuntaje.get(i).getRegla().getAntecedentes().get(j).getId()+",");
                }
                System.out.print("(");
                for(int j=0; j<reglasConPuntaje.get(i).getRegla().getConsecuencias().size(); j++){
                    System.out.print(" "+reglasConPuntaje.get(i).getRegla().getConsecuencias().get(j).getId()+",");
                    if(!tipsRegla.contains(reglasConPuntaje.get(i).getRegla().getConsecuencias().get(j)) && !ug.getTipsGustados().contains(reglasConPuntaje.get(i).getRegla().getConsecuencias().get(j)) && !ug.getTipsNoGustados().contains(reglasConPuntaje.get(i).getRegla().getConsecuencias().get(j))){
                        tipsRegla.add(reglasConPuntaje.get(i).getRegla().getConsecuencias().get(j));
                    }
                }
                System.out.print(")");
                System.out.println(" ");
                
            }
            
            
            
            /*
            int iguales = obtenerReglas(tipsUsuario, tipsRegla, tipsUsuario.size());
            if(iguales > 0){
                System.out.println("iguales" + iguales);
                tipRecomendado = servicioTips.findTipById(idTip);
            }
            */
            tipRecomendado = tipsRegla.get(0);
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

/*
    public int obtenerReglas(List<Tip> tipsUsuario, List<Tip> tipsRegla, int iguales){
        
        if(contieneTodos(tipsUsuario, tipsRegla) && iguales>=0){
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
*/
    public int contieneTodos(List<Tip> tipsUsuario, List<Tip> tipsRegla){
        int iguales = 0;
        
        System.out.println("Usuarios "+tipsUsuario.size() + " Regla "+tipsRegla.size());
        for(int i=0; i<tipsUsuario.size(); i++){
            if(tipsRegla.contains(tipsUsuario.get(i)) && tipsUsuario.size() == tipsRegla.size() ){
                iguales++;
            }
        }

        return iguales;
    }

    /*
    public List<String> crearListaTipsGustados(List<Tip> tips){  
        List<String> tipsString = new ArrayList<String>();
        for(int i=0; i<tips.size(); i++){
            tipsString.add(String.valueOf(tips.get(i).getId()));
        }
        return tipsString;
    }
    */

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
