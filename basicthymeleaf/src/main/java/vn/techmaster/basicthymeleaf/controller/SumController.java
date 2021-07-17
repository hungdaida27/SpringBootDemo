package vn.techmaster.basicthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.basicthymeleaf.request.SumRequest;
import vn.techmaster.basicthymeleaf.response.SumResult;

@Controller
@RequestMapping("/sum")
public class SumController {

    @GetMapping
    public String getSumForm(Model model) {
        model.addAttribute("sumRequest", new SumRequest());
        model.addAttribute("sumResult", null);
        return "sum";
    }

    @PostMapping()
    public String handleBMIForm(@ModelAttribute SumRequest request, BindingResult bindingResult, Model model) {
        // Đoạn code này trộn lẫn quá nhiều chức năng. Vi phạm Single Responsibility
        if (! bindingResult.hasErrors()) {
            float sumIndex = request.getNum1() + request.getNum2();

            SumResult sumResult = new SumResult(sumIndex);

            //Trả về đối tượng để Thymeleaf render ra HTML
            model.addAttribute("sumRequest", request);
            //model.addAttribute("sumRequest", new SumRequest());
            model.addAttribute("sumResult", sumResult);
        }
        return "sum"; // Sử dụng template bmi.html trong thư mục resources/templates/sum.html
    }
}
