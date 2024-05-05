package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainng.coursesystem.dao.mapper.AdminMapper;
import com.rainng.coursesystem.dao.mapper.StudentMapper;
import com.rainng.coursesystem.dao.mapper.TeacherMapper;
import com.rainng.coursesystem.manager.LoginStatusManager;
import com.rainng.coursesystem.manager.UserManager;
import com.rainng.coursesystem.model.bo.AuthInfoBO;
import com.rainng.coursesystem.model.bo.LoginStatusBO;
import com.rainng.coursesystem.model.constant.UserType;
import com.rainng.coursesystem.model.entity.AdminEntity;
import com.rainng.coursesystem.model.entity.StudentEntity;
import com.rainng.coursesystem.model.entity.TeacherEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.admin.AdminService;
import com.rainng.coursesystem.service.admin.StudentService;
import com.rainng.coursesystem.util.Md5Encrypt;
import com.rainng.coursesystem.util.RandomNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService extends BaseService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    private static final String PASSWORD_SALT = "_Rain_Ng-_Azure_99";

    private final HttpSession session;
    private final UserManager manager;
    private final LoginStatusManager loginStatusManager;
    private final Md5Encrypt md5Encrypt;

    public UserService(HttpSession session, UserManager manager, LoginStatusManager loginStatusManager, Md5Encrypt md5Encrypt) {
        this.session = session;
        this.manager = manager;
        this.loginStatusManager = loginStatusManager;
        this.md5Encrypt = md5Encrypt;
    }

    public ResultVO login(String username, String password, Integer userType) {
        AuthInfoBO authInfo = manager.getAuthInfoByUsername(username, userType);
        if (authInfo == null) {
            return failedResult("用户不存在");
        }
        String passwordHash = computePasswordHash(password);
        if (!passwordHash.equals(authInfo.getPassword())) {
            return failedResult("密码错误");
        }

        if (authInfo.getUserType().equals(UserType.STUDENT)) {
            manager.updateStudentLastLoginTime(username);
        }

        LoginStatusBO statusBO = LoginStatusBO.fromAuthInfo(authInfo);
        loginStatusManager.setLoginStatus(session, statusBO);

        return result(statusBO);
    }

    public ResultVO getLoginStatus() {
        LoginStatusBO statusBO = loginStatusManager.getLoginStatus(session);
        return result(statusBO);
    }

    public ResultVO logout() {
        loginStatusManager.setLoginStatus(session, null);
        return result("注销成功");
    }

    public String computePasswordHash(String password) {
        String md5 = md5Encrypt.computeHexString(password);
        return md5Encrypt.computeHexString(md5 + PASSWORD_SALT);
    }

    public ResultVO signUp(String username, String password, Integer userType){
        if (userType == UserType.STUDENT) {

            List<StudentEntity> list = studentMapper.selectList(new LambdaQueryWrapper<StudentEntity>().eq(StudentEntity::getNumber, username));
            if(list.size()>0){
                return failedResult("用户名已存在");
            }
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(RandomNumUtil.getRandomNum());
            studentEntity.setClassId(RandomNumUtil.getRandomNum());
            studentEntity.setName(username);
            studentEntity.setNumber(username);
            studentEntity.setPassword(computePasswordHash(password));
            studentEntity.setSex(0);
            studentMapper.insert(studentEntity);
        } else if (userType == UserType.TEACHER) {
            List<TeacherEntity> teacherEntities = teacherMapper.selectList(new LambdaQueryWrapper<TeacherEntity>().eq(TeacherEntity::getTeacherId, username));
            if(teacherEntities.size()>0){
                return failedResult("用户名已存在");
            }
        } else if (userType == UserType.ADMIN) {
            List<AdminEntity> adminEntities = adminMapper.selectList(new LambdaQueryWrapper<AdminEntity>().eq(AdminEntity::getUsername, username));
            if(adminEntities.size()>0){
                return failedResult("用户名已存在");
            }
        }
        return result("已注册");
    }
}
