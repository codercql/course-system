package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.vo.request.LoginVO;
import com.rainng.coursesystem.model.vo.request.SignUpVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.model.vo.response.UserDetailResVO;
import com.rainng.coursesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {
    @Autowired
    private UserService service;

//    public UserController(UserService service) {
//        this.service = service;
//    }

    @PostMapping("/login")
    public ResultVO login(@Validated @RequestBody LoginVO loginVO) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();
        return service.login(username, password, userType);
    }

    @RequestMapping("/login/status")
    public ResultVO getLoginStatus() {
        return service.getLoginStatus();
    }

    @RequestMapping("/logout")
    public ResultVO logout() {
        return service.logout();
    }

    @PostMapping("/signUp")
    public ResultVO signUp(@Validated @RequestBody SignUpVO signUpVO) {
        return service.signUp(signUpVO);
    }

    @GetMapping("/getUserList")
    public ResultVO<UserDetailResVO> getUserList(@RequestParam("userType") Integer userType) {
        return service.getUserList(userType);
    }
}
