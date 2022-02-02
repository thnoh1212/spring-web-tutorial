package mbti.board.Service;

import lombok.RequiredArgsConstructor;
import mbti.board.Exception.BusinessException;
import mbti.board.Model.Comment;
import mbti.board.Model.Post;
import mbti.board.Repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional(rollbackFor = BusinessException.class)
    public void createComment(Comment comment){
        try {
            comment.setTimeBeforeInsert();
            commentRepository.save(comment);
        } catch(BusinessException e){
            throw new BusinessException("글 저장 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void updateComment(Comment commentForUpdate){
        Comment comment = commentRepository.findById(commentForUpdate.getCommentNo())
                .orElseThrow(() -> new BusinessException("기존 댓글 정보가 존재하지 않습니다."));

        if(comment.updateCommentInfo(commentForUpdate)){
            try {
                commentRepository.save(comment);
            } catch(BusinessException e){
                throw new BusinessException("댓글 수정 중 오류가 발생했습니다.", e);
            }
        }
        else{
            throw new BusinessException("기존 댓글과 수정 요청된 댓글의 정보가 서로 다릅니다.");
        }


    }

    @Transactional(rollbackFor = BusinessException.class)
    public void deleteComment(String author, long commentNo) throws BusinessException {

        var comment = commentRepository.findById(commentNo);

        if (comment.isPresent() && comment.get().getAuthor().equals(author))
            commentRepository.deleteById(commentNo);
        else
            throw new BusinessException("삭제하려는 댓글 정보에 문제가 있습니다.");

    }
}
