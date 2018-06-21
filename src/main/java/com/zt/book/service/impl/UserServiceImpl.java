package com.zt.book.service.impl;

import com.zt.book.dao.UserDao;
import com.zt.book.pojo.Message;
import com.zt.book.pojo.User;
import com.zt.book.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author tzz
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public User login(User user) {
        return userDao.login(user.getUserName(), user.getUserPassword());
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findByLike(String userName) {
        return userDao.findByLike(userName);
    }

    @Override
    public Message delete(String id) {
        Message msg = new Message();
        User user = userDao.findById(id);
        if (user != null) {
            userDao.delete(user);
            msg.setMsg("删除成功");
        } else {
            msg.setMsg("用户不存在");
        }
        return msg;
    }

    @Override
    public Message addUser(User user) {
        Message msg = new Message();
        if (user.getUserName() != null && !user.getUserName().equals("")) {
            User u = userDao.findByName(user.getUserName());
            if (u == null) {
                String id = UUID.randomUUID().toString().substring(0, 4);
                user.setId(id);
                userDao.addUser(user);
                msg.setMsg("用户添加成功");
            } else {
                msg.setMsg("用户名已存在");
            }
        } else {
            msg.setMsg("用户名不可为空");
        }
        return msg;
    }

    @Override
    public Message updateUser(User user) {
        Message msg = new Message();
        User ue = userDao.findById(user.getId());
        if (ue != null) {
            if (!user.getUserName().equals("")) {
                User u = userDao.findByName(user.getUserName());
                if (u == null) {
                    userDao.update(user);
                    msg.setMsg("修改成功");
                } else if (u.getId().equals(user.getId())) {
                    if (user.getUserPassword().equals("")) {
                        user.setUserPassword(u.getUserPassword());
                    }
                    userDao.update(user);
                    msg.setMsg("修改成功");
                } else {
                    msg.setMsg("用户名已存在");
                }
            } else {
                msg.setMsg("用户名不可为空");
            }
        } else {
            msg.setMsg("此用户不存在");
        }
        return msg;
    }

    @Override
    public Message updatePassword(String id, String pwd, String newPwd) {
        Message msg = new Message();
        User u = userDao.findById(id);
        if (u != null) {
            if (u.getUserPassword().equals(pwd)) {
                u.setUserPassword(newPwd);
                userDao.update(u);
                msg.setMsg("密码修改成功");
            } else {
                msg.setMsg("原密码错误");
            }
        } else {
            msg.setMsg("用户不存在");
        }
        return msg;
    }
}
