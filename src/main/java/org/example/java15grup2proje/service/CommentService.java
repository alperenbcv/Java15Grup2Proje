package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.UpdateCommentDto;
import org.example.java15grup2proje.entity.Auth;
import org.example.java15grup2proje.entity.Comment;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final AuthService authService;
	private final UserService userService;
	
	public Manager tokenToManager(String token){
		User user =  userService.tokenToUser(token);
		if (user.getRole() != ERole.MANAGER) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		return (Manager) user;
	}
	
	public Comment getComment(String token) {
		Auth auth = tokenToManager(token);
		Optional<Comment> optComment = commentRepository.findByManagerIdAndState(auth.getId(), 1);
		if (optComment.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.COMMENT_NOT_FOUND);
		return optComment.get();
	}
	
	public void editComment(@Valid UpdateCommentDto dto) {
		Auth auth = tokenToManager(dto.token());
		Optional<Comment> optComment = commentRepository.findById(dto.commentId());
		if (optComment.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.COMMENT_NOT_FOUND);
		Comment comment = optComment.get();
		
		if (!comment.getManagerId().equals(auth.getId())) throw new Java15Grup2ProjeAppException(ErrorType.NO_PERMISSION);
		comment.setComment(dto.comment());
		commentRepository.save(comment);
	}
	
	public void addComment(@Valid UpdateCommentDto dto) {
		Manager manager = tokenToManager(dto.token());
		Comment comment =
				Comment.builder().comment(dto.comment()).managerId(manager.getId()).companyId(manager.getCompanyId()).build();
		Optional<Comment> optComment = commentRepository.findByManagerIdAndState(manager.getId(), 1);
		if (optComment.isPresent()) throw new Java15Grup2ProjeAppException(ErrorType.EXISTING_COMMENT);
		commentRepository.save(comment);
	}
	
	public void deleteComment(@Valid UpdateCommentDto dto) {
		Auth auth = tokenToManager(dto.token());
		Optional<Comment> optComment = commentRepository.findById(dto.commentId());
		if (optComment.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.COMMENT_NOT_FOUND);
		else if (!optComment.get().getManagerId().equals(auth.getId())) throw new Java15Grup2ProjeAppException(ErrorType.NO_PERMISSION);
		Comment comment = optComment.get();
		comment.setState(0);
		commentRepository.save(comment);
	}
	
	public List<Comment> getAllComments() {
		return commentRepository.findAllByState(1);
	}
}