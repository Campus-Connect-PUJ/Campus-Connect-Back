package CampusConnect.CCBack.Model;

import java.util.Date;
import java.util.List;
import java.util.Date;

public class UserCreationDetails {
    private String email;
    private String name;
    private String last_name;
    private Date myDate;
    private int semestre_seleccionado;
    private List<String> carreras_seleccionadas;
    private String religion;
    private String etnico;
    private String nacimiento;
    private String genero;
    private String sexo;
    

    public String getReligion() {
        return this.religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEtnico() {
        return this.etnico;
    }

    public void setEtnico(String etnico) {
        this.etnico = etnico;
    }

    public String getNacimiento() {
        return this.nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getMyDate() {
        return this.myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }

    public int getSemestre_seleccionado() {
        return this.semestre_seleccionado;
    }

    public void setSemestre_seleccionado(int semestre_seleccionado) {
        this.semestre_seleccionado = semestre_seleccionado;
    }

    public List<String> getCarreras_seleccionadas() {
        return this.carreras_seleccionadas;
    }

    public void setCarreras_seleccionadas(List<String> carreras_seleccionadas) {
        this.carreras_seleccionadas = carreras_seleccionadas;
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", name='" + getName() + "'" +
            ", last_name='" + getLast_name() + "'" +
            ", myDate='" + getMyDate() + "'" +
            ", semestre_seleccionado='" + getSemestre_seleccionado() + "'" +
            ", carreras_seleccionadas='" + getCarreras_seleccionadas() + "'" +
            ", religion='" + getReligion() + "'" +
            ", etnico='" + getEtnico() + "'" +
            ", nacimiento='" + getNacimiento() + "'" +
            ", genero='" + getGenero() + "'" +
            ", sexo='" + getSexo() + "'" +
            "}";
    }
    
}