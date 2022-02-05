package mbti.board.Service;

import lombok.RequiredArgsConstructor;
import mbti.board.Exception.BusinessException;
import mbti.board.Model.Enum.SearchType;
import mbti.board.Model.Post;
import mbti.board.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final PostRepository postRepository;

    public List<Post.PostForBoard> searchPostList(SearchType searchType, String text){
        List<Post> postList = new ArrayList<>();
        switch (searchType){
            case TITLE:
                postList = postRepository.findAllByTitleContainingIgnoreCase(text);
                break;
            case AUTHOR:
                postList = postRepository.findAllByAuthorContainingIgnoreCase(text);
                break;
            case CONTENT:
                postList = postRepository.findAllByMainTextContainingIgnoreCase(text);
                break;
        }

        return postList.stream().map(Post::of).collect(Collectors.toList());
    }
}
