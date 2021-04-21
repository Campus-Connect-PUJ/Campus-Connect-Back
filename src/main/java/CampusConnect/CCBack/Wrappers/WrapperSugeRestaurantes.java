package CampusConnect.CCBack.Wrappers;

import java.util.ArrayList;
import java.util.List;

public class WrapperSugeRestaurantes {
    
    private List<Long> idsRestaurantes;
    private long idUsuario;

    public WrapperSugeRestaurantes(){
        this.idsRestaurantes = new ArrayList<Long>();
    }

    public long getIdUsuario(){
        return this.idUsuario;
    }

    public void setIDUsuario(long id){
        this.idUsuario = id;
    }

    public List<Long> getIdsRestaurantes(){
        return this.idsRestaurantes;
    }

    public void setIdsRestaurantes(List<Long> ids){
        this.idsRestaurantes = ids;
    }
}
