package mbti.board.Repository;

import mbti.board.Model.Comment;
import mbti.board.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByPost(Post post);
}
