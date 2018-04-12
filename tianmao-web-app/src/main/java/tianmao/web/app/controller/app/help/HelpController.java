package tianmao.web.app.controller.app.help;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * help
 */
@Controller
@RequestMapping("/help")
public class HelpController {

    @RequestMapping
    public String help(String type, ModelMap modelMap) {
        if (type == null) {
            type = "aboutus1.jpg";
        }
        if (type.equals("1")) {
            type = "aboutus1.jpg";
        }
        if (type.equals("2")) {
            type = "opt_post2.jpg";
        }
        if (type.equals("3")) {
            type = "gold3.jpg";
        }
        if (type.equals("4")) {
            type = "integral4.jpg";
        }
        if (type.equals("5")) {
            type = "sen_post5.jpg";
        }
        if (type.equals("6")) {
            type = "user_protocol6.jpg";
        }
        if (type.equals("7")) {
            type = "get_out7.jpg";
        }
        if (type.equals("8")) {
            type = "aboutus1.jpg";
        }
        if (type.equals("9")) {
            type = "wallet9.png";
        }
        modelMap.put("name", type);

        return "help";
    }

}