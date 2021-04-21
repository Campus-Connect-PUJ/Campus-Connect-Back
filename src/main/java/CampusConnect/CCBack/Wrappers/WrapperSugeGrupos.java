package CampusConnect.CCBack.Wrappers;

import java.util.ArrayList;
import java.util.List;

public class WrapperSugeGrupos {

    private List<Long> idsGrupos;
    private long idUsuario;

    public WrapperSugeGrupos(){
        this.idsGrupos = new ArrayList<Long>();
    }

    public long getIdUsuario(){
        return this.idUsuario;
    }

    public void setIDUsuario(long id){
        this.idUsuario = id;
    }

    public List<Long> getIdsRestaurantes(){
        return this.idsGrupos;
    }

    public void setIdsRestaurantes(List<Long> ids){
        this.idsGrupos = ids;
    }
    
}
