package mbti.board.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentNo;

    @ManyToOne
    @JoinColumn(name = "post_no", referencedColumnName = "postNo")
    @JsonBackReference
    private Post post;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime insDate;

    @Column(nullable = false)
    private LocalDateTime updDate;

    public void setTimeBeforeInsert(){
        this.insDate = LocalDateTime.now();
        this.updDate = LocalDateTime.now();
    }

    public boolean updateCommentInfo(Comment.CommentForUpdate comment) {
        if (comment.getCommentNo() == this.getCommentNo()) {
            this.text = comment.text;
            this.updDate = LocalDateTime.now();
            return true;
        }
        return false;

    }


    @Getter
    @Setter
    public static class CommentForUpdate{

        private long commentNo;

        private String author;

        private String text;
    }
}
