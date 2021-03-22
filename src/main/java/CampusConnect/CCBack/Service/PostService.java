package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Post;
import CampusConnect.CCBack.Model.RespuestaPost;
import CampusConnect.CCBack.Repository.PostRepository;
import CampusConnect.CCBack.Repository.RespuestaPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
