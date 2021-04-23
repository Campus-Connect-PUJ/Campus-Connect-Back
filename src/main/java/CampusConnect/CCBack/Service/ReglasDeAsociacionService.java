package CampusConnect.CCBack.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.ReglaAsociacionConPuntaje;
import CampusConnect.CCBack.Model.ReglasDeAsociacion;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ReglasDeAsociacionRepository;
import CampusConnect.CCBack.Wrappers.WrapperReglaAsociacion;

@Service
public class ReglasDeAsociacionService {

    @Autowired
    private ReglasDeAsociacionRepository repository;

    @Autowired
    private UsuarioGeneralService servicioUsuarios;

    @Autowired
    private TipsService servicioTips;

    public Iterable<ReglasDeAsociacion> findAll() {
        return repository.findAll();
    }

    public ReglasDeAsociacion findById(final Long id) {
        return repository.findById(id).get();
    }

    public ReglasDeAsociacion crearReglaDeAsociacion(
        final WrapperReglaAsociacion reglaData) {
        ReglasDeAsociacion regla = new ReglasDeAsociacion();
        
        regla.setSoporte(reglaData.getSoporte());
        regla.setConfianza(reglaData.getConfianza());
        regla.setLift(reglaData.getLift());
        
        for(int i=0; i<reglaData.getAntecedentes().size(); i++){
            Tip a = this.servicioTips.findById(reglaData.getAntecedentes().get(i));
            regla.agregarAntecedentes(a);
        }

        for(int i=0; i<reglaData.getConsequents().size(); i++){
            Tip b = this.servicioTips.findById(reglaData.getConsequents().get(i));
            regla.agregarConsecuentes(b);
        }

        return repository.save(regla);
    }

    public Tip obtenerRecomendacionTip(final long email){
        // long idTip = 15;
        //UsuarioGeneral ug = servicioUsuarios.findByEmail(email);
        System.out.println("usuario "+ email);
        UsuarioGeneral ug = servicioUsuarios.findById(email);
        List<Tip> tipsGustadosUsuario = ug.getTipsGustados();
        List<ReglasDeAsociacion> reglas = (List<ReglasDeAsociacion>) this.findAll();
        Tip tipRecomendado = new Tip();



        if(ug.getTipsGustados().size()>0){
            //Cuando haya reglas
            List<Tip> tipsUsuario = ug.getTipsGustados();
            List<Tip> tipsRegla = new ArrayList<Tip>();
            
            List<ReglaAsociacionConPuntaje> reglasConPuntaje = new ArrayList<ReglaAsociacionConPuntaje>();
            ReglasDeAsociacion regla = new ReglasDeAsociacion();

            for(int i=0; i<reglas.size(); i++){
                regla = reglas.get(i);
                int cantidadIguales = this.contieneTodos(tipsUsuario, regla.getAntecedentes());
                if(cantidadIguales > 0){
                    ReglaAsociacionConPuntaje nuevo = new ReglaAsociacionConPuntaje();
                    nuevo.setPuntaje(cantidadIguales * regla.getConfianza()); //Se tiene encuenta no solo la cantidad de 
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
                    System.out.println("2entra a lo del tipo");
                    tipRecomendado = tipsGustadosUsuario.get(0);
                }
                catch (Exception e2){
                    //Intento #3: El siguiente tip al ultimo
                    System.out.println("3entra a lo del tipo");
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
        List<Tip> tipsSistema = (List<Tip>) this.servicioTips.findAll();
        List<Tip> tipsRecomendar = new ArrayList<Tip>();
        Tip buscarSiExiste = new Tip();
        Tip tipRecomendar = new Tip();
        boolean sale = false;
        int i = 0;
        while(!sale){
            tipRecomendar = this.ordenarLista(tipsSistema).get(i);
            if(!ug.getTipsGustados().contains(tipRecomendar) && !ug.getTipsNoGustados().contains(tipRecomendar)){
                sale = true;
            } 
            i++;
        }
        


        return tipRecomendar;
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

    public List<ReglaAsociacionConPuntaje> ordenarReglasPorPuntaje(List<ReglaAsociacionConPuntaje> reglas){
        List<ReglaAsociacionConPuntaje> reglasOrdenadas = new ArrayList<ReglaAsociacionConPuntaje>();
        ReglaAsociacionConPuntaje[] miarray = new ReglaAsociacionConPuntaje[reglas.size()];
        miarray = reglas.toArray(miarray);
        Arrays.sort(miarray);
        reglasOrdenadas = Arrays.asList(miarray);

        for(int i=0; i<reglasOrdenadas.size(); i++){
            System.out.println("----------------------------- " + reglasOrdenadas.get(i).getRegla().getId());
        }

        return reglasOrdenadas;
    }

//Cuando no haya reglas

    public List<Tip> obtenerTipsPorTipo(UsuarioGeneral ug){
        List<Tip> tipsRecomendar = new ArrayList<Tip>();
        List<Tip> tipsSistema = (List<Tip>) this.servicioTips.findAll();

        for(int i=0; i<ug.getEstilosAprendizaje().size(); i++){
            for(int j=0; j<tipsSistema.size(); j++){
                for(int k=0; k<tipsSistema.get(j).getTiposAprendizaje().size(); k++){
                    if(tipsSistema.get(j).getTiposAprendizaje().get(k).getId() == ug.getEstilosAprendizaje().get(i).getId() && !tipsRecomendar.contains(tipsSistema.get(j)) && !ug.getTipsGustados().contains(tipsSistema.get(j)) && !ug.getTipsNoGustados().contains(tipsSistema.get(j))){
                        tipsRecomendar.add(tipsSistema.get(j));
                        System.out.println(tipsSistema.get(j));
                    }
                }
            }
        }

        tipsRecomendar = ordenarLista(tipsRecomendar);
        
        return tipsRecomendar;
    }

    public Long borrarReglas(){
        repository.deleteAll();
        List<ReglasDeAsociacion> reglas = (List<ReglasDeAsociacion>) repository.findAll();
        Long cantidad = (long) reglas.size();
        return cantidad;
    }

}
