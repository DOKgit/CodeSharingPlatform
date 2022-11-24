package platform.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.entity.Code;
import platform.repository.CodeRepository;
import platform.service.CodeService;

import java.util.UUID;

@Controller
public class WebController {

    @Autowired
    CodeRepository codeRepository;

    @Autowired
    CodeService codeService;

    @GetMapping("/code/{id}")
    public String codeAsHTML(@PathVariable UUID id, Model model) {
        Code code = codeService.viewCodeById(id);
        model.addAttribute(code);
        return "code";
    }

    @GetMapping("code/new")
    public ModelAndView getWriteCodeView(ModelAndView modelAndView) {
        modelAndView.setViewName("newCode");

        return modelAndView;
    }

    @GetMapping("/code/latest")
    public String getLatestCodes(Model model) {
        model.addAttribute("codes", codeService.getLatestCodes());
        return "latestCodes";
    }
}
