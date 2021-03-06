package whitney.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import whitney.services.EmailService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class HelloController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/about")
    public String index() {
        return "Testing pipeline changes - updates as of 4.4.2020!!";
    }

    @RequestMapping("/resource")
    public Map<String, Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/index")
    public String newIndex() {
        // Create the email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("Devinwhit@gmail.com");
        mailMessage.setSubject("Complete Password Reset!");
        mailMessage.setFrom("no-reply@devinwhitney.com");
        mailMessage.setText("ffgdfgdfgdf");

        // Send the email
        emailService.sendEmail(mailMessage);
        return "index";
    }


    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
