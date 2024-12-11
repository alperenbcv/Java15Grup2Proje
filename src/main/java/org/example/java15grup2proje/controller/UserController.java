package org.example.java15grup2proje.controller;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.example.java15grup2proje.constant.RestApi.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
	private final UserService userService;
	
	
}