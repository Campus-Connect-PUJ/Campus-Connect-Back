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

    @ManyToMany
    private List<Tip> antecedentes;

    @ManyToMany
    private List<Tip> consecuencias;

    private long soporte;

    private long confianza;

    private long lift;

    public ReglasDeAsociacion () {
        this.antecedentes = new ArrayList<Tip>();
        this.consecuencias = new ArrayList<Tip>();
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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

    public List<Tip> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<Tip> antecedentes) {
        this.antecedentes = antecedentes;
    }

    public List<Tip> getConsecuencias() {
        return consecuencias;
    }

    public void setConsecuencias(List<Tip> consecuencias) {
        this.consecuencias = consecuencias;
    }


    public void agregarAntecedentes(Tip tip) {
        this.antecedentes.add(tip);
    }

    public void agregarConsecuentes(Tip tip) {
        System.out.println("........................");
        this.consecuencias.add(tip);
    }
    

}
