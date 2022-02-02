package mbti.board.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "post_no", insertable = false, updatable = false)
    private Post postNo;

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


    public boolean updateCommentInfo(Comment comment) {
        if (comment.getCommentNo() == this.getCommentNo()) {
            this.text = comment.text;
            this.updDate = LocalDateTime.now();
            return true;
        }
        return false;

    }
}
