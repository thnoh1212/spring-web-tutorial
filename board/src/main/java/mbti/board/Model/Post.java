package mbti.board.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;


//TODO: validation 에러 메시지 문구 재설정

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    @NotEmpty(message = "작성자 정보가 제대로 넘어오지 않음")
    @Column(length = 30, nullable = false)
    private String author;

    @NotEmpty(message = "글 제목 을 입력해 주세요")
    @Column(length = 50, nullable = false)
    private String title;

    @NotEmpty(message = "글 본문 내용을 입력해 주세요")
    @Column(nullable = false)
    private String mainText;

    @Column(nullable = false)
    private LocalDateTime insDate;

    @Column(nullable = false)
    private LocalDateTime updDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> comments;



    @Getter
    @NoArgsConstructor
    public static class PostForUpdate {

        @Min(value = 1, message = "수정하려는 글 번호가 정상적이지 않습니다.")
        private long postNo;

        @NotEmpty(message = "작성자 정보가 제대로 넘어오지 않음")
        private String author;

        @NotEmpty(message = "글 제목을 입력해 주세요.")
        private String title;

        @NotEmpty(message = "글 본문 내용을 입력해 주세요")
        private String mainText;
    }

    @Builder
    @Getter
    public static class PostForBoard {
        private final long postNo;
        private final String author;
        private final String title;
        private final LocalDateTime insDate;
    }

    public void setTimeBeforeInsert(){
        this.insDate = LocalDateTime.now();
        this.updDate = LocalDateTime.now();
    }

    // 함수명 이게 맞나..?
    public PostForBoard of(){
        return PostForBoard.builder().
            postNo(this.postNo).
            author(this.author).
            title(this.title).
            insDate(this.insDate).
            build();
    }

    public boolean updatePostInfo(PostForUpdate post){
        if(this.author.equals(post.getAuthor()) && this.postNo == post.getPostNo()){
            this.title = post.getTitle();
            this.mainText = post.getMainText();
            this.updDate = LocalDateTime.now();
            return true;
        }
        return false;
    }
}
