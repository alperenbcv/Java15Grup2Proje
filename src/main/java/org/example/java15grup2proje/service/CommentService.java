package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.UpdateCommentDto;
import org.example.java15grup2proje.dto.response.CommentResponseDto;
import org.example.java15grup2proje.entity.*;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.mapper.CommentMapper;
import org.example.java15grup2proje.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final AuthService authService;
	private final UserService userService;
	private final ManagerService managerService;
	private final CompanyService companyService;
	
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
	
	public Comment editComment(@Valid UpdateCommentDto dto) {
		Auth auth = tokenToManager(dto.token());
		Optional<Comment> optComment = commentRepository.findById(dto.commentId());
		if (optComment.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.COMMENT_NOT_FOUND);
		Comment comment = optComment.get();
		
		if (!comment.getManagerId().equals(auth.getId())) throw new Java15Grup2ProjeAppException(ErrorType.NO_PERMISSION);
		comment.setComment(dto.comment());
		return commentRepository.save(comment);
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
	
	public List<CommentResponseDto> getAllComments() {
		List<Comment> commentList = commentRepository.findAllByState(1);
		List<CommentResponseDto> commentResponseList =
				commentList.stream().map((comment)-> {
					Optional<Company> optCompany = companyService.findById(comment.getCompanyId());
					if (optCompany.isEmpty()) return null;
					Optional<Manager> optManager = managerService.findById(comment.getManagerId());
					if (optManager.isEmpty()) return null;
					Company company = optCompany.get();
					Manager manager = optManager.get();
							return CommentResponseDto.builder()
									.companyLogoUrl(company.getCompanyLogoUrl())
									.companyName(company.getCompanyName())
									.managerName(manager.getName() + " " + manager.getSurname())
									.comment(comment)
							                         .build();
				}
	).filter(Objects::nonNull).toList();
		return commentResponseList;
	}
	
	public Optional<Comment> findById(String commentId) {
		return commentRepository.findById(commentId);
	}
}