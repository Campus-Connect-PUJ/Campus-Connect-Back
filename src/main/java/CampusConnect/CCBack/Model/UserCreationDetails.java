package CampusConnect.CCBack.Model;

import java.util.Date;

public class UserCreationDetails {
    private String email;
    private String name;
    private String last_name;
    private Date myDate;
    private int semestre_seleccionado;
    private String [] carreras_seleccionadas;
    

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

    public String[] getCarreras_seleccionadas() {
        return this.carreras_seleccionadas;
    }

    public void setCarreras_seleccionadas(String[] carreras_seleccionadas) {
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
            "}";
    }
    
}