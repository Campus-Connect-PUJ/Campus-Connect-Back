package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tip {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "subMensajeGenerico")
    // @JsonIgnore // https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
    private List<TipoAprendizaje> respuestas;


}
