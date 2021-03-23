package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Post;
import CampusConnect.CCBack.Model.RespuestaPost;
import CampusConnect.CCBack.Repository.PostRepository;
import CampusConnect.CCBack.Repository.RespuestaPostRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PostService {
    @Autowired
    private PostRepository repository;

    @Autowired
    private RespuestaPostRepository rpRepo;

    @GetMapping("/posts")
    public Iterable<Post> findAll() {
        return repository.findAll();
    }

    @GetMapping("/post/{id}")
    public Post findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/post/{id}/respuestas")
    public RespuestaPost findRespuestasById(@PathVariable("id") Long id) {
        return rpRepo.findById(id).get();
    }

    @PutMapping("/post")
    public Post crearPost(@RequestBody Post postData) {

        Post post = new Post();

        post.setDescripcion(postData.getDescripcion());
        post.setFecha(postData.getFecha());
        // post.setRespuestas(postData.getRespuestas()); // no es necesario, ya que el constructor se encarga, ademas un post al ser creado siempre tendra una lista vacia de respuestas
        post.setTitulo(postData.getTitulo());
        post.setUsuario(postData.getUsuario());

        return repository.save(post);
    }
}
