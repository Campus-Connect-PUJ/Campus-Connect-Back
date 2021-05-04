package CampusConnect.CCBack.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Horario;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Model.UsuarioMonitor;
import CampusConnect.CCBack.Repository.HorarioRepository;
import CampusConnect.CCBack.Wrappers.WrapperHorario;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository repository;

    @Autowired
    private UsuarioGeneralService ugService;

    @Autowired
    private UsuarioMonitorService umService;

    public Iterable<Horario> findAll() {
        return GenericService.findAll(repository);
    }

    public Horario findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public Horario agregarHorariosMonitoria(UsuarioGeneral ug, WrapperHorario wpH){
        Horario horario = new Horario();
        List<UsuarioMonitor> anterioresMonitorias = ug.getMonitorDe();
        UsuarioMonitor monitoria = new UsuarioMonitor();
        boolean yaexiste = false;
        System.out.println("->"+wpH.fechaInicio + " - "+ wpH.fechaFin);
        horario.setFechaInicial(wpH.getFechaInicial());
        horario.setFechaFinal(wpH.getFechaFinal());
        horario.setfechaInicio(wpH.fechaInicio);
        horario.setfechaFin(wpH.fechaFin);
        horario.lugar = wpH.lugar;

        for(int i=0; i<anterioresMonitorias.size(); i++){

            for(int j=0; j<anterioresMonitorias.get(i).getHorarios().size(); j++){


                //String str = "2016-03-04 11:30";
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                //LocalDateTime dateTime = LocalDateTime.parse(str, formatter);


                LocalDate localDateGuardadoDia = anterioresMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalDate();
                LocalTime tiempoGuardado = anterioresMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalTime();


                LocalDate localDate = horario.getFechaInicial().toLocalDate();
                LocalTime tiempoNuevo = horario.getFechaInicial().toLocalTime();

                if(localDateGuardadoDia.isEqual(localDate) && (tiempoGuardado.getHour() == tiempoNuevo.getHour() && tiempoGuardado.getMinute() == tiempoNuevo.getMinute())){
                    yaexiste = true;
                }
            }

            if(anterioresMonitorias.get(i).getAsignatura().getId() == wpH.getIdAsignatura()){
                monitoria = anterioresMonitorias.get(i);
            }
        }

        if(!yaexiste){
            monitoria.addHorario(horario);
            ug.addMonitorDe(monitoria);
            horario.setMonitor(monitoria);

            ugService.guardarUsuario(ug);
            umService.guardar(monitoria);
            return GenericService.save(repository, horario);
        }

        return horario;
    }

    public void borrarHorarioMonitoria(UsuarioGeneral ug, WrapperHorario wpH){
        Horario horario = new Horario();
        List<UsuarioMonitor> anterioresMonitorias = ug.getMonitorDe();
        UsuarioMonitor monitoria = new UsuarioMonitor();
        boolean yaexiste = false;
        System.out.println("->"+wpH.fechaInicio + " - "+ wpH.fechaFin);
        horario.setFechaInicial(wpH.getFechaInicial());
        horario.setFechaFinal(wpH.getFechaFinal());
        horario.setfechaInicio(wpH.fechaInicio);
        horario.setfechaFin(wpH.fechaFin);
        
        for(int i=0; i<anterioresMonitorias.size(); i++){
            for(int j=0; j<anterioresMonitorias.get(i).getHorarios().size(); j++){
                LocalDate localDateGuardadoDia = anterioresMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalDate();
                LocalTime tiempoGuardado = anterioresMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalTime();
                LocalDate localDate = horario.getFechaInicial().toLocalDate();
                LocalTime tiempoNuevo = horario.getFechaInicial().toLocalTime();
                if(localDateGuardadoDia.isEqual(localDate) && (tiempoGuardado.getHour() == tiempoNuevo.getHour() && tiempoGuardado.getMinute() == tiempoNuevo.getMinute())){
                    yaexiste = true;
                    horario = anterioresMonitorias.get(i).getHorarios().get(j);
                }
            }   
            if(anterioresMonitorias.get(i).getAsignatura().getId() == wpH.getIdAsignatura()){
                monitoria = anterioresMonitorias.get(i);
            }
        }
        if(yaexiste){
            monitoria.quitarHorario(horario);
            ug.quitarMonitorDe(monitoria);
            repository.delete(horario);

            if(monitoria.getHorarios().size()==0){
                umService.borrar(monitoria);
                //monitorRepository.delete(monitoria);
            }
            else{
                umService.guardar(monitoria);
                //monitorRepository.save(monitoria);
            }

            ugService.guardarUsuario(ug);
            //repository.save(ug);
            
        }

    }






}
