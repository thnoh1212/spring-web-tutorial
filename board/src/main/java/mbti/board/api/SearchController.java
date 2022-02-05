package mbti.board.api;

import lombok.RequiredArgsConstructor;
import mbti.board.Model.Enum.SearchType;
import mbti.board.Model.Post;
import mbti.board.Service.SearchService;
import mbti.board.api.bind.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    final private SearchService searchService;

    @GetMapping()
    public ApiResult<List<Post.PostForBoard>> searchPost(
        @RequestParam SearchType searchType,
        @RequestParam String text
    ){
        return ApiResult.success(
            searchService.searchPostList(searchType, text),
            HttpStatus.OK
        );
    }
}
