package mbti.board.api;

import lombok.RequiredArgsConstructor;
import mbti.board.Model.Post;
import mbti.board.Service.PostService;
import mbti.board.api.bind.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;


//TODO: 인자값 검증부분을 값 받는곳 -> 프론트 값 전송부분에 검증 들어가면 삭제
@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ApiResult<String> create(
            @RequestBody @Valid Post post
    ){
        postService.createPost(post);
        return ApiResult.success(null, HttpStatus.OK);
    }

    @GetMapping
    public ApiResult<List<Post.postForBoard>> readList(
            @RequestParam @Min(value = 1, message = "첫 페이지보다 더 이전의 페이지를 조회했습니다.") int page,
            @RequestParam @Min(value = 1, message = "한 페이지에 최소 한 개 이상의 정보가 표시되어야 합니다.") int limit
    ){
        return ApiResult.success(postService.readPostList(page, limit), HttpStatus.OK);
    }

    @GetMapping("/{postNo}")
    public ApiResult<Post> readDetail(
            @PathVariable @Min(value = 1, message = "조회하려는 글 번호가 정상적이지 않습니다.") long postNo
    ){
        return ApiResult.success(
            postService.readPostDetail(postNo),
            HttpStatus.OK
        );
    }


    @PatchMapping
    public ApiResult<String> update(
            @RequestBody @Valid Post.postForUpdate postForUpdate
    ){
        postService.updatePost(postForUpdate);
        return ApiResult.success(null, HttpStatus.OK);
    }


    @DeleteMapping
    public ApiResult<String> delete(
            @RequestParam @NotEmpty(message = "작성자 정보가 확인되지 않습니다. 재 로그인 후 다시 시도해주시기 바랍니다.") String author,
            @RequestParam @Min(value = 1, message = "삭제하려는 글 번호가 정상적이지 않습니다.") long pageNo
    ){
        postService.deletePost(author, pageNo);
        return ApiResult.success(null, HttpStatus.OK);
    }


    @GetMapping("/test")
    public ApiResult<String> test(){
        System.out.println("api 호출 성공");
        return ApiResult.success("호출 성공", HttpStatus.OK);
    }
}
