package com.civka.calculatordemo.controller;

import com.civka.calculatordemo.entity.LabData;
import com.civka.calculatordemo.maintenance.MaintenanceModeManager;
import com.civka.calculatordemo.service.UserAuthenticationService;
import com.civka.calculatordemo.utils.BasicBinary;
import com.civka.calculatordemo.utils.MachineCodeUtil;
import com.civka.calculatordemo.utils.multiply.FirstMultiplyUtil;
import com.civka.calculatordemo.utils.multiply.FourthMultiplyUtil;
import com.civka.calculatordemo.utils.multiply.SecondMultiplyUtil;
import com.civka.calculatordemo.utils.multiply.ThirdMultiplyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MenuController {

    private final MaintenanceModeManager maintenanceModeManager;

    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public MenuController(UserAuthenticationService userAuthenticationService, MaintenanceModeManager maintenanceModeManager) {
        this.userAuthenticationService = userAuthenticationService;
        this.maintenanceModeManager = maintenanceModeManager;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {

        String nickname = userAuthenticationService.getNicknameByAuth();
        if (nickname.equals("failedUser")) return "failed-user-page";
        model.addAttribute("userNickname", nickname);

        model.addAttribute("labData", userAuthenticationService.getLabDataByAuth());

        return maintenanceModeManager.isMaintenanceModeEnabled() ? "maintenance" : "index";
    }

    @GetMapping("/calculate")
    public String getCalculate(Model model) {

        String nickname = userAuthenticationService.getNicknameByAuth();
        if (nickname.equals("failedUser")) return "failed-user-page";
        model.addAttribute("userNickname", nickname);

        model.addAttribute("labData", userAuthenticationService.getLabDataByAuth());

        model.addAttribute("binaryCalc", new BasicBinary());

        BasicBinary binaryCalcConvert = new BasicBinary();
        binaryCalcConvert.setConvertFrom("2");
        binaryCalcConvert.setConvertTo("10");
        model.addAttribute("binaryCalcConvert", binaryCalcConvert);

        return maintenanceModeManager.isMaintenanceModeEnabled() ? "maintenance" : "calculate";
    }

    @PostMapping("/calculate")
    public String doCalculate(@ModelAttribute(name = "binaryCalc") BasicBinary binaryCalc,
                              @ModelAttribute(name = "binaryCalcConvert") BasicBinary binaryCalcConvert,
                              Model model) {

        String nickname = userAuthenticationService.getNicknameByAuth();
        if (nickname.equals("failedUser")) return "failed-user-page";
        model.addAttribute("userNickname", nickname);

        model.addAttribute("labData", userAuthenticationService.getLabDataByAuth());

        model.addAttribute("resultBinaryCalc", binaryCalc);
        model.addAttribute("binaryCalc", binaryCalc);

        model.addAttribute("resultBinaryCalcConvert", binaryCalcConvert);
        if (binaryCalcConvert.getConvertTo() == null || binaryCalcConvert.getConvertFrom() == null) {
            binaryCalcConvert.setConvertFrom("2");
            binaryCalcConvert.setConvertTo("10");
        }
        model.addAttribute("binaryCalcConvert", binaryCalcConvert);

        return maintenanceModeManager.isMaintenanceModeEnabled() ? "maintenance" : "calculate";
    }


    @PostMapping("/processForm")
    public String doLabForm(@ModelAttribute(name = "labData") LabData labData, Model model) {

        String nickname = userAuthenticationService.getNicknameByAuth();
        if (nickname.equals("failedUser")) return "failed-user-page";
        model.addAttribute("userNickname", nickname);

        List<Integer> a = labData.getA(7);

        model.addAttribute("labData", labData);
        model.addAttribute("a", a);

        String x;
        String y;
        String f;
        String g;
        switch (labData.getLabNumber()) {
            case 1:
                f = String.format("1%d%d%d%d1", a.get(6), a.get(5), a.get(4), a.get(3));
                g = String.format("1011%d%d%d1", a.get(2), a.get(1), a.get(0));

                x = "-" + f + "," + g;
                MachineCodeUtil machineCodeX = new MachineCodeUtil(x);

                String appToY = String.format("0101%d%d,1%d%d10000", a.get(3), a.get(2), a.get(1), a.get(0));
                MachineCodeUtil machineCodeAppToY = new MachineCodeUtil(appToY);

                y = BasicBinary.addBinary(machineCodeX.getModifyReverseCode(), machineCodeAppToY.getModifyReverseCode());
                String oldY = "";
                if (y.length() > machineCodeX.getModifyReverseCode().length()) {
                    y = y.substring(1);
                    oldY = y;
                    y = BasicBinary.addBinary(y, y.replaceAll("1", "0").substring(0, y.length() - 1) + "1");
                }
                MachineCodeUtil machineCodeY = new MachineCodeUtil(y, "ОК+");

                String u = BasicBinary.addBinary(machineCodeX.getModifyReverseCode(), machineCodeY.getModifyReverseCode());
                String oldU = "";
                if (u.length() > machineCodeX.getModifyReverseCode().length()) {
                    u = u.substring(1);
                    oldU = u;
                    u = BasicBinary.addBinary(u, u.replaceAll("1", "0").substring(0, u.length() - 1) + "1");
                }
                MachineCodeUtil machineCodeU = new MachineCodeUtil(u, "ОК+");

                String minusY = machineCodeY.getModifyReverseCode().replaceAll("0", "A")
                        .replaceAll("1", "0").replaceAll("A", "1");
                MachineCodeUtil machineCodeMinusY = new MachineCodeUtil(minusY, "ОК+");
                String n = BasicBinary.addBinary(machineCodeX.getModifyReverseCode(), machineCodeMinusY.getModifyReverseCode());
                String oldN = "";
                if (n.length() > machineCodeX.getModifyReverseCode().length()) {
                    n = n.substring(1);
                    oldN = n;
                    n = BasicBinary.addBinary(n, n.replaceAll("1", "0").substring(0, n.length() - 1) + "1");
                }
                MachineCodeUtil machineCodeN = new MachineCodeUtil(n, "ОК+");

                model.addAttribute("F", f);
                model.addAttribute("G", g);
                model.addAttribute("X", machineCodeX);
                model.addAttribute("appToY", machineCodeAppToY);
                model.addAttribute("oldY", oldY);
                model.addAttribute("Y", machineCodeY);
                model.addAttribute("oldU", oldU);
                model.addAttribute("U", machineCodeU);
                model.addAttribute("minusY", machineCodeMinusY);
                model.addAttribute("oldN", oldN);
                model.addAttribute("N", machineCodeN);

                return "sum";
            case 2:
                int operandsBitRate;
                switch (String.format("%d%d%d", a.get(2), a.get(1), a.get(0))) {

                    case "000", "100" -> {
                        if (a.get(2) == 0) {
                            operandsBitRate = 7;
                            x = String.format("1%d%d%d101", a.get(5), a.get(4), a.get(3));
                            y = "1001101";
                        } else {
                            operandsBitRate = 6;
                            x = String.format("11%d%d%d0", a.get(5), a.get(4), a.get(3));
                            y = "101111";
                        }
                        FirstMultiplyUtil firstMultiply = new FirstMultiplyUtil(operandsBitRate, x, y);

                        model.addAttribute("firstMultiply", firstMultiply);
                        return "multiply-1";
                    }

                    case "001", "101" -> {
                        if (a.get(2) == 0) {
                            operandsBitRate = 5;
                            x = String.format("1%d%d%d0", a.get(5), a.get(4), a.get(3));
                            y = "10011";
                        } else {
                            operandsBitRate = 6;
                            x = String.format("10%d%d%d1", a.get(5), a.get(4), a.get(3));
                            y = "111010";
                        }
                        SecondMultiplyUtil secondMultiply = new SecondMultiplyUtil(operandsBitRate, x, y);

                        model.addAttribute("secondMultiply", secondMultiply);
                        return "multiply-2";
                    }

                    case "010", "110" -> {
                        if (a.get(2) == 0) {
                            operandsBitRate = 7;
                            x = String.format("1%d%d%d001", a.get(5), a.get(4), a.get(3));
                            y = "0100111";
                            g = "1101011" + "0".repeat(operandsBitRate);
                            model.addAttribute("G", g);
                        } else {
                            operandsBitRate = 6;
                            x = String.format("10%d%d%d1", a.get(5), a.get(4), a.get(3));
                            y = "101111";
                            g = "100101" + "0".repeat(operandsBitRate);
                            model.addAttribute("G", g);

                        }
                        ThirdMultiplyUtil thirdMultiply = new ThirdMultiplyUtil(operandsBitRate, x, y);

                        model.addAttribute("thirdMultiply", thirdMultiply);
                        return "multiply-3";
                    }

                    case "011", "111" -> {
                        if (a.get(2) == 0) {
                            operandsBitRate = 6;
                            x = String.format("1%d%d%d01", a.get(5), a.get(4), a.get(3));
                            y = "110011";
                        } else {
                            operandsBitRate = 5;
                            x = String.format("10%d%d%d", a.get(5), a.get(4), a.get(3));
                            y = "11001";
                        }
                        FourthMultiplyUtil fourthMultiply = new FourthMultiplyUtil(operandsBitRate, x, y);

                        model.addAttribute("fourthMultiply", fourthMultiply);
                        return "multiply-4";
                    }
                }

            case 3:
            case 4:
            case 5:
            default: return null;
        }
    }
}
