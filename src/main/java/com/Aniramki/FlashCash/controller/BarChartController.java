//package com.Aniramki.FlashCash.controller;
//
//import com.Aniramki.FlashCash.repository.BarChartRepository;
//import com.Aniramki.FlashCash.service.BarChartService;
//import com.Aniramki.FlashCash.service.dto.BarChartForm;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/bar-chart")
//public class BarChartController {
//
//
//    private final BarChartService barChartService;
//    public BarChartController(BarChartService barChartService) {
//        this.barChartService = barChartService;
//    }
//
//    @PostMapping("/friendAmountMonth")
//    public List<Map<String, Object>> getFriendAmountMonth(
//            @RequestParam int userFromId,
//            @RequestParam(required = false) Integer yearTransfer) {
//        return barChartService.getFriendAmountMonth(userFromId, yearTransfer);
//    }

//    @GetMapping("/friendAmountMonth")
//    public ModelAndView showBarChartForm() {
//        return new ModelAndView("friendAmountMonth", "BarChartForm", new BarChartForm());
//
//    }
//    @PostMapping("/friendAmountMonth")
//    public List<Map<String, Object>> getFriendAmountMonth(
//            @RequestParam int userFromId,
//            @RequestParam(required = false) Integer yearTransfer) {
//        return barChartRepository.callFriendAmountMonthProcedure(userFromId, yearTransfer);
//    }
//}

