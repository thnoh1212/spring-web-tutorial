package mbti.board.Repository;

import mbti.board.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
    Containing: 해당 내용을 포함한 데이터 검색 == like %xx%
    IgnoreCase: 대소문자 구별을 하지 않음

 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleContainingIgnoreCase(String title);
    List<Post> findAllByAuthorContainingIgnoreCase(String author);
    List<Post> findAllByMainTextContainingIgnoreCase(String mainText);
}
