package cryst.blogapi.payloads;

import cryst.blogapi.entities.Post;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentDto {

    private Integer id;

    private String content;


}
