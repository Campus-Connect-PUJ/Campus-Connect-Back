package CampusConnect.CCBack.Wrappers;

import java.util.List;

public class WrapperPersoRestaurantes {
    //Un regimenen alimenticio, nivel de exigencia, lista de comidas favoritas, una ambientación
    
    private Long regimenAlimenticio;
    private Long nivelExigencia;
    private List<Long> comidas;
    private String ambientacion;

    public Long getRegimenAlimenticio(){
        return this.regimenAlimenticio;
    }

    public void setRegimenAlimenticio(Long regimenAlimenticio){
        this.regimenAlimenticio=regimenAlimenticio;
    }

    public Long getNivelExigencia(){
        return this.nivelExigencia;
    }

    public void setNivelExigencia(Long nivelExigencia){
        this.nivelExigencia=nivelExigencia;
    }

    public String getAmbientacion(){
        return this.ambientacion;
    }

    public void setAmbientacion(String ambientacion){
        this.ambientacion=ambientacion;
    }

    public List<Long> getComidas(){
        return this.comidas;
    }

    public void setComidas(List<Long> comidas){
        this.comidas=comidas;
    }
}