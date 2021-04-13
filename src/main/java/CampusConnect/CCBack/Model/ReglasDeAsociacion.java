package CampusConnect.CCBack.Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ReglasDeAsociacion {

    @Id
    @GeneratedValue
    private Long id;

    private String antecedentes;

    private String consecuencias;

    private long soporte;

    private long confianza;

    private long lift;


    public ReglasDeAsociacion () {

    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getAntecedentes() {
        return antecedentes;
    }


    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }


    public String getConsecuencias() {
        return consecuencias;
    }


    public void setConsecuencias(String consecuencias) {
        this.consecuencias = consecuencias;
    }


    public long getSoporte() {
        return soporte;
    }


    public void setSoporte(long soporte) {
        this.soporte = soporte;
    }


    public long getConfianza() {
        return confianza;
    }


    public void setConfianza(long confianza) {
        this.confianza = confianza;
    }


    public long getLift() {
        return lift;
    }


    public void setLift(long lift) {
        this.lift = lift;
    }

    

}
