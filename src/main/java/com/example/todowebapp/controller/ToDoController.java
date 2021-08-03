package com.example.todowebapp.controller;


import com.example.todowebapp.model.Mail;
import com.example.todowebapp.model.PasswordReset;
import com.example.todowebapp.model.ToDo;
import com.example.todowebapp.model.User;
import com.example.todowebapp.repository.PasswordResetRepository;
import com.example.todowebapp.service.EmailService;
import com.example.todowebapp.service.SecurityService;
import com.example.todowebapp.service.ToDoService;
import com.example.todowebapp.service.UserService;
import com.example.todowebapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @RequestMapping(value="/login",method=RequestMethod.GET)
    public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("login");
        LoginBean loginBean = new LoginBean();
        model.addObject("loginBean", loginBean);
        return model;
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean)
    {
        ModelAndView model= null;
        boolean isValidUser = userService.isValidUser(loginBean.getUsername(), loginBean.getPassword());
        if(isValidUser)
        {
            System.out.println("User Login Successful");
            request.setAttribute("loggedInUser", loginBean.getUsername());
            model = new ModelAndView("welcome");
        }
        else
        {
            model = new ModelAndView("login");
            model.addObject("loginBean", loginBean);
            request.setAttribute("message", "Invalid credentials!!");
        }
        return model;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm")User userForm, BindingResult bindingResult){
        userValidator.validate(userForm, bindingResult);

        if(bindingResult.hasErrors()){
            return "registration";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }




    @GetMapping({"/", "/welcome"})
    public String showWelcomePage(ModelMap model){
        model.put("name", getLoggedInUserName());
        return "welcome";
    }

    private String getLoggedInUserName(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value="/list-todo", method = RequestMethod.GET)
    public String showToDo(ModelMap model){
        String name = getLoggedInUserName(model);
        model.put("todo", toDoService.getToDoByUser(name));
        return "list-todo";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            return ((UserDetails)principal).getUsername();
        }
        return principal.toString();
    }


    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public ModelAndView showAddToDoPage(){
        return new ModelAndView("add-todo","todo",new ToDo());
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "error";
        }
        todo.setUserName(getLoggedInUserName(model));

        toDoService.saveToDo(todo);
        return "redirect:/list-todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteToDo(@RequestParam long id){
        toDoService.deleteToDo(id);
        return "redirect:/list-todo";
    }

    @RequestMapping(value="/update-todo/{userId}", method = RequestMethod.GET)
    public String showUpdateToDoPage(@PathVariable("userId") long userId, ModelMap model){
        ToDo todo=toDoService.getToDoById(userId).get();
        model.put("todo", todo);
        return "update-todo";
    }

    @RequestMapping(value="/update-todo", method=RequestMethod.POST)
    public String updateToDo(ModelMap model, @Valid ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        todo.setUserName(getLoggedInUserName(model));
        toDoService.updateToDo(todo);
        return "redirect:/list-todo";
    }

    @RequestMapping(value = "forgot-password", method = RequestMethod.GET)
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgot-password");
    }

    @RequestMapping(value="/forgot-password",method=RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView model, @ModelAttribute("email") String email, HttpServletRequest request){
        Optional<User> optional = Optional.ofNullable(userService.findByUsername(email));
        if(!optional.isPresent()){
            model.addObject("errorMessage", "Account not found for this email id.");
        }
        else{
            PasswordReset token = new PasswordReset();
            User user = optional.get();
            token.setUser(user);
            token.setToken(UUID.randomUUID().toString());
            user.setResetToken(UUID.randomUUID().toString());
            token.setExpiryDate(30);
            tokenRepository.save(token);
            userService.save(user);
            String resetUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();
            Mail mail = new Mail();
            mail.setFrom("no-reply");
            mail.setTo(user.getUsername());
            mail.setSubject("Password Reset Request");
            mail.setText("To reset your password, click the link below:\n" +resetUrl+ "/reset-password?token=" + user.getResetToken());
            Map<String, Object> model1 = new HashMap<>();
            model1.put("resetUrl", resetUrl + "/reset-password?token=" + token.getToken());
            model1.put("token",token);
            model1.put("user",user);
            model1.put("resetUrl", resetUrl + "/reset-password?token=" + token.getToken());
            mail.setModel(model1);
            emailService.sendEmail(mail);
            model.addObject("successMessage","A Password reset link has been sent to "+user.getUsername());
        }
        model.setViewName("forgot-password");
        return model;
    }

    @RequestMapping(value="/reset-password", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView model, @ModelAttribute("token")String token){
        Optional<User> user = userService.findUserByResetToken(token);
        if(user.isPresent()){
            model.addObject("resetToken",token);
        }
        else{
            model.addObject("errorMessage", "Oops! This link is invalid");
        }
        model.setViewName("reset-password");
        return model;
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView model, @ModelAttribute Map<String, String> requestParams, RedirectAttributes redir){
        Optional<User> user = userService.findUserByResetToken(requestParams.get("token"));
        if(user.isPresent()){
            User resetUser = user.get();
            resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
            resetUser.setResetToken(null);
            userService.save(resetUser);

            redir.addFlashAttribute("successMessage","You have successfully reset your password. You may now login");
            model.setViewName("redirect:login");
            return model;
        }
        else{
            model.addObject("errorMessage", "Oops! This is an invalid password reset link");
            model.setViewName("reset-password");
        }
        return model;
    }
}