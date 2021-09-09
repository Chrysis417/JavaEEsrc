package com.lrm.web.admin;

import com.lrm.po.Blog;
import com.lrm.po.User;
import com.lrm.service.UserService;
import com.lrm.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
@RequestMapping("/admin")
public class RegisterController {

    private static final String REG = "admin/register";
    private static final String REDIRECT_LOGIN = "redirect:/admin";

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String RegisterPage(Model model) {
        model.addAttribute("user", new User());
        return REG;
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes attributes) {
        if (userService.checkUser_Register(user.getUsername())) {
            user.setAvatar("https://unsplash.it/100/100?image=1005");
            user.setType(0);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setPassword(MD5Utils.code(user.getPassword()));

            User u = userService.saveUser(user);

            if (u == null) {
                attributes.addFlashAttribute("message ", "注册失败");
            } else {
                attributes.addFlashAttribute("message", "注册成功");
                return REDIRECT_LOGIN;
            }
            return "admin/register";

        } else {
            attributes.addFlashAttribute("message", "用户已存在");
            return "admin/register";
        }
    }


//        blog.setUser((User) session.getAttribute("user"));
//        blog.setType(typeService.getType(blog.getType().getId()));
//        blog.setTags(tagService.listTag(blog.getTagIds()));
//        Blog b;
//        if (blog.getId() == null) {
//            b =  blogService.saveBlog(blog);
//        } else {
//            b = blogService.updateBlog(blog.getId(), blog);
//        }
//
//        if (b == null ) {
//            attributes.addFlashAttribute("message", "操作失败");
//        } else {
//            attributes.addFlashAttribute("message", "操作成功");
//        }
//        return REDIRECT_LIST;
//    }
}
