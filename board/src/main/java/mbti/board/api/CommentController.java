package mbti.board.api;

import lombok.RequiredArgsConstructor;
import mbti.board.Model.Comment;
import mbti.board.Service.CommentService;
import mbti.board.api.bind.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ApiResult<String> create(
        @RequestBody @Valid Comment comment
    ){
        commentService.createComment(comment);
        return ApiResult.success(null, HttpStatus.OK);
    }

    @PatchMapping
    public ApiResult<String> update(
            @RequestBody @Valid Comment comment
    ){
        commentService.updateComment(comment);
        return ApiResult.success(null, HttpStatus.OK);
    }

    @DeleteMapping
    public ApiResult<String> delete(
            @RequestParam String author,
            @RequestParam long commentNo
    ){
        commentService.deleteComment(author, commentNo);
        return ApiResult.success(null, HttpStatus.OK);
    }

}
