package com.thoughtworks.capacity.gtb.mvc.Service;

import com.thoughtworks.capacity.gtb.mvc.Exception.UserNotValidException;
import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();

    public UserService() {
        userList.add(new User(1,"ann","12345","abc@qq.com"));
    }

    public void registerUser(User user){
        if(!Pattern.matches("^[A-Za-z_0-9]{3,10}$", user.getUsername())) {
            throw new UserNotValidException("用户名不合法");
        }
        if(!Pattern.matches("^[0-9]{5,12}$",user.getPassword())){
            throw new UserNotValidException("密码不合法");
        }
        if(user.getEmail()!=null&&!Pattern.matches("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",user.getEmail())){
            throw new UserNotValidException("邮箱地址不合法");
        }
        Optional<User> isExistUser = userList.stream().filter(item -> item.getUsername().equals(user.getUsername())).findAny();
        if(isExistUser.isPresent()){
            throw new UserNotValidException("用户已存在");
        }
        user.setId(userList.size()+1);
        userList.add(user);
    }

    public User loginValidate(String username, String password){
        if(!Pattern.matches("^[A-Za-z_0-9]{3,10}$", username)) {
            throw new UserNotValidException("用户名不合法");
        }
        if(!Pattern.matches("^[0-9]{5,12}$",password)){
            throw new UserNotValidException("密码不合法");
        }
        Optional<User> isExistUser = userList.stream().filter(item -> item.getUsername().equals(username)).findAny();
        if(!isExistUser.isPresent() || !isExistUser.get().getPassword().equals(password)){
            throw new UserNotValidException("用户名或密码错误");
        }else {
            return isExistUser.get();
        }

    }

}
