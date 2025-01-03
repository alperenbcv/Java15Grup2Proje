package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.UpdateCommentDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.dto.response.CommentResponseDto;
import org.example.java15grup2proje.entity.Comment;
import org.example.java15grup2proje.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
@CrossOrigin("*")
public class CommentController {
	private final CommentService commentService;
	
	@GetMapping(GET_COMMENT)
	public ResponseEntity<BaseResponse<Comment>> getComment(String token){
		return ResponseEntity.ok(BaseResponse.<Comment>builder()
				                         .data(commentService.getComment(token))
				                         .message("comment başarıyla getirildi")
				                         .code(200)
				                         .success(true)
				                             .build());
	}
	
	@PostMapping(EDIT_COMMENT)
	public ResponseEntity<BaseResponse<Comment>> editComment(@RequestBody @Valid UpdateCommentDto dto){
		return ResponseEntity.ok(BaseResponse.<Comment>builder()
				                         .success(true)
				                         .code(200)
				                         .message("")
				                         .data(commentService.editComment(dto))
		                                     .build());
	}
	@PostMapping(ADD_COMMENT)
	public ResponseEntity<BaseResponse<Boolean>> addComment(@RequestBody @Valid UpdateCommentDto dto){
		commentService.addComment(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .success(true)
		                                     .code(200)
		                                     .message("")
		                                     .data(true)
		                                     .build());
	}
	@PostMapping(DELETE_COMMENT)
	public ResponseEntity<BaseResponse<Boolean>> deleteComment(@RequestBody @Valid UpdateCommentDto dto){
		commentService.deleteComment(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .success(true)
		                                     .code(200)
		                                     .message("")
		                                     .data(true)
		                                     .build());
	}
	
	@GetMapping(GET_ALL_COMMENTS)
	public ResponseEntity<BaseResponse<List<CommentResponseDto>>> getAllComments(){
		return ResponseEntity.ok(BaseResponse.<List<CommentResponseDto>>builder()
		                                     .data(commentService.getAllComments())
		                                     .message("comment listesi başarıyla getirildi")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
}