package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Post;
import CampusConnect.CCBack.Model.RespuestaPost;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.PostRepository;
import CampusConnect.CCBack.Repository.RespuestaPostRepository;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PostService {
    @Autowired
    private PostRepository repository;

    @Autowired
    private RespuestaPostRepository rpRepo;

    @Autowired
    private UsuarioGeneralRepository usuarioRepo;

    @GetMapping("/posts")
    public Iterable<Post> findAll() {
        return repository.findAll();
    }

    @GetMapping("/post/{id}")
    public Post findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/post/{id}/respuestas")
    public RespuestaPost findRespuestasById(@PathVariable("id") final Long id) {
        return rpRepo.findById(id).get();
    }

    @PostMapping("/post/{id}")
    public Post crearPost(
        @RequestBody final Post postData,
        @PathVariable("id") final Long idUsuario
        ) {
        Post post = new Post();
        UsuarioGeneral ug = usuarioRepo.findById(idUsuario).get();
        // no es necesario poner las demas variables, ya que el
        // constructor se encarga, ademas un post al ser creado
        // siempre tendra una lista vacia de respuestas, la fecha
        // puede ser sacada de forma local y no sera reportado
        post.setDescripcion(postData.getDescripcion());
        post.setTitulo(postData.getTitulo());
        post.setUsuario(ug);

        return repository.save(post);
    }
}
