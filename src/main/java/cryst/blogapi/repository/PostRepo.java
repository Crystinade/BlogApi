package cryst.blogapi.repository;

import cryst.blogapi.entities.Category;
import cryst.blogapi.entities.Post;
import cryst.blogapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> getByUser(User user);

    List<Post> findByCategory(Category category);

}
