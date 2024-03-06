package com.civka.calculatordemo.controller;

import com.civka.calculatordemo.entity.QuestionData;
import com.civka.calculatordemo.maintenance.MaintenanceModeManager;
import com.civka.calculatordemo.service.QuestionDataService;
import com.civka.calculatordemo.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private final QuestionDataService questionDataService;
    private final UserAuthenticationService userAuthenticationService;
    private final MaintenanceModeManager maintenanceModeManager;

    @Autowired
    public FeedbackController(QuestionDataService questionDataService,
                              UserAuthenticationService userAuthenticationService,
                              MaintenanceModeManager maintenanceModeManager) {

        this.questionDataService = questionDataService;
        this.userAuthenticationService = userAuthenticationService;
        this.maintenanceModeManager = maintenanceModeManager;
    }

    @GetMapping
    public String getFeedback(Model model) {

        String nickname = userAuthenticationService.getNicknameByAuth();
        if (nickname.equals("failedUser")) return "failed-user-page";
        model.addAttribute("userNickname", nickname);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        model.addAttribute("questions", questionDataService.findAll());
        model.addAttribute("userQuestionsIsEmpty", questionDataService.findByUsername(username).isEmpty());
        model.addAttribute("isNoQuestions", questionDataService.findAll().stream()
                .noneMatch(obj -> obj.getAnswer() == null));

        model.addAttribute("questionData", new QuestionData());

        return maintenanceModeManager.isMaintenanceModeEnabled() ? "maintenance" : "feedback";
    }

    @PostMapping
    public String processFeedback(@ModelAttribute(name = "questionData") QuestionData questionData, Model model) {

        String nickname = userAuthenticationService.getNicknameByAuth();
        if (nickname.equals("failedUser")) return "failed-user-page";
        model.addAttribute("userNickname", nickname);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        model.addAttribute("questions", questionDataService.findAll());
        model.addAttribute("userQuestionsIsEmpty", questionDataService.findByUsername(username).isEmpty());
        model.addAttribute("isNoQuestions", questionDataService.findAll().stream()
                .noneMatch(obj -> obj.getAnswer() == null));

        questionData.setUsername(username);
        if (questionData.getTheme().isEmpty()) questionData.setTheme("(без теми)");
        questionDataService.saveQuestionData(questionData);
        model.addAttribute("questionData", new QuestionData());

        return maintenanceModeManager.isMaintenanceModeEnabled() ? "maintenance" : "redirect:/feedback";
    }

    @PostMapping("/answer")
    public String makeAnswer(@RequestParam("questionId") Integer questionId, @RequestParam("answer") String answer) {

        QuestionData questionData = questionDataService.findById(questionId);
        questionData.setAnswer(answer);
        questionData.setAnswerDate(LocalDate.now());
        questionDataService.saveQuestionData(questionData);

        return "redirect:/feedback";
    }

    @GetMapping("/delete/{questionId}")
    public String makeAnswer(@PathVariable("questionId") Integer questionId) {

        questionDataService.deleteQuestionDataById(questionId);

        return "redirect:/feedback";
    }
}
