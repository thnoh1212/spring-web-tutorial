package mbti.board.Service;

import lombok.RequiredArgsConstructor;
import mbti.board.Exception.BusinessException;
import mbti.board.Model.Post;
import mbti.board.Repository.CommentRepository;
import mbti.board.Repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//TODO: 필요없는 예외호출부분은 로직 정리하면서 제거

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional(rollbackFor = BusinessException.class)
    public void createPost(Post post){
        try {
            post.setTimeBeforeInsert();
            postRepository.save(post);
        } catch(BusinessException e){
            throw new BusinessException("글 저장 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Post.PostForBoard> readPostList(int page, int limit){
        try {
            List<Post> postList = postRepository.findAll(PageRequest.of(page - 1, limit, Sort.by("insDate").descending())).toList();
            return postList.stream().map(Post::of).collect(Collectors.toList());
        } catch(BusinessException e){
            throw new BusinessException("글 저장 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional(readOnly = true)
    public Post readPostDetail(long pageNo){
        Post post = postRepository.findById(pageNo)
            .orElseThrow(() -> new BusinessException("글 정보가 존재하지 않습니다."));
        post.setComments(commentRepository.findAllByPost(post));
        return post;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void updatePost(Post.PostForUpdate postForUpdate){
        Post post = postRepository.findById(postForUpdate.getPostNo())
                .orElseThrow(() -> new BusinessException("기존 글 정보가 존재하지 않습니다."));

        if(post.updatePostInfo(postForUpdate)){
            try {
                postRepository.save(post);
            } catch(BusinessException e){
                throw new BusinessException("글 수정 중 오류가 발생했습니다.", e);
            }
        }
        else{
            throw new BusinessException("기존 글과 수정 요청된 글의 정보가 서로 다릅니다.");
        }


    }

    //TODO: 유저 검증 작업 필요, 임시처리
    @Transactional(rollbackFor = BusinessException.class)
    public void deletePost(String author, long postNo) throws BusinessException{

        var post = postRepository.findById(postNo);

        if(post.isPresent() && post.get().getAuthor().equals(author))
            postRepository.deleteById(postNo);
        else
            throw new BusinessException("삭제하려는 글 정보에 문제가 있습니다.");

    }

}
