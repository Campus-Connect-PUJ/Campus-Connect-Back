package CampusConnect.CCBack.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.*;
import CampusConnect.CCBack.Repository.ReglasDeAsociacionRepository;
import CampusConnect.CCBack.Wrappers.WrapperReglaAsociacion;
import CampusConnect.CCBack.Wrappers.WrapperTip;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class ReglasDeAsociacionServiceTest {

    @Autowired
    private ReglasDeAsociacionService servicioReglas;

    @Autowired
    private UsuarioGeneralService servicioUsuarios;

    @Autowired
    private TipsService servicioTips;

    @Autowired
    private TipoAprendizajeService servicioTipoAprendizaje;


    public void pruebaTipoAprendizaje(){
        String descripcion = "descripcion";
        TipoAprendizaje tipo = new TipoAprendizaje();
        tipo.setDescripcion("descripcion");
        
        TipoAprendizaje tipoCreado = this.servicioTipoAprendizaje.create(tipo);

        assertAll(
            () -> assertEquals( tipoCreado.getDescripcion() , descripcion)
        );

        // se retorna correctamente el objeto
        assertNotNull(tipoCreado);

        TipoAprendizaje tipoConseguido = this.servicioTipoAprendizaje.findById(tipoCreado.getId());
        // se verifica que el objeto fue creado en la bd

        assertNotNull(tipoConseguido);
        assertEquals(tipoCreado, tipoConseguido);

    }

    public void pruebaUsuario(){
        String nombre = "nombre";
        String apellido = "apellido";
        String email = "email";
        String password = "password";
        int semestre = 1;

        WrapperUsuarioGeneral wug = new WrapperUsuarioGeneral();
        wug.setNombre(nombre);
        wug.setApellido(apellido);
        wug.setEmail(email);
        wug.setPassword(password);
        wug.setSemestre(semestre);

        UsuarioGeneral ugCreado = this.servicioUsuarios.create(wug);

        assertAll(
            () -> assertEquals( ugCreado.getNombre() , nombre), 
            () -> assertEquals( ugCreado.getApellido() , apellido),
            () -> assertEquals( ugCreado.getEmail() , email)
        );

        // se retorna correctamente el objeto
        assertNotNull(ugCreado);

       UsuarioGeneral ugConseguido = this.servicioUsuarios.findById(ugCreado.getId());
        // se verifica que el objeto fue creado en la bd

        assertNotNull(ugConseguido);
        assertEquals(ugCreado, ugConseguido);

    }

    
    public void pruebaTips(){
        String descripcion = "descripcion";
        Long idUsuario = (long) 1;
        Long exigencia = (long) 1;
        Tip t = new Tip();
        t.setDescripcion(descripcion);

        WrapperTip datoTip = new WrapperTip();
        datoTip.setExigencia(exigencia);
        datoTip.setIdUsuario(idUsuario);
        datoTip.setTip(t);
        List<Long> tipos = new ArrayList<>();
        tipos.add((long)1);
        datoTip.setTiposAprendizaje(tipos);

        Tip tcreado = this.servicioTips.crear(datoTip);

        /*
        assertAll(
            () -> assertEquals( ugCreado.getNombre() , nombre), 
            () -> assertEquals( ugCreado.getApellido() , apellido),
            () -> assertEquals( ugCreado.getEmail() , email)
        );

        */
        assertNotNull(tcreado);

        // se busca el objeto
        Tip tConseguida = this.servicioTips.findById(tcreado.getId());

        assertNotNull(tConseguida);
        assertEquals(tConseguida, tcreado);
    }


    public void pruebaReglas() {
        List<Long> antecedentes = new ArrayList<Long>();
        List<Long> consecuencias = new ArrayList<Long>();
        float soporte = (float) 0.8;
        float confianza = 1;
        float lift = 1;

        long a = 1;
        antecedentes.add(a);
        a=2;
        antecedentes.add(a); 
        a=3;
        consecuencias.add(a);

        ReglasDeAsociacion ra = new ReglasDeAsociacion();
        ra.setSoporte(soporte);
        ra.setConfianza(confianza);
        ra.setLift(lift);

        WrapperReglaAsociacion dato = new WrapperReglaAsociacion();
        dato.setSoporte(soporte);
        dato.setConfianza(confianza);
        dato.setLift(lift);
        dato.setAntecedentes(antecedentes);
        dato.setConsequents(consecuencias);

        System.out.println("a " + dato.getSoporte() + dato.getConfianza());
        ReglasDeAsociacion raCreada = this.servicioReglas.crearReglaDeAsociacion(dato);
        
        
        // la informacion sea la misma
        assertAll(
            () -> assertEquals( raCreada.getSoporte(), soporte),
            () -> assertEquals( raCreada.getConfianza() , confianza),
            () -> assertEquals( raCreada.getLift() , lift)
            );

        // se retorna correctamente el objeto
        assertNotNull(raCreada);

        ReglasDeAsociacion raConseguido = this.servicioReglas.findById(raCreada.getId());
        // se verifica que el objeto fue creado en la bd

        assertNotNull(raConseguido);
        assertEquals(raCreada, raConseguido);

    }

    
    @Test
    public void pruebas() {
        pruebaTipoAprendizaje();
        pruebaUsuario();
        pruebaTips();
        //pruebaReglas();
    }

 
}
