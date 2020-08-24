package com.matei.customeraccountaggregation.controller;

import com.matei.customeraccountaggregation.dto.AlertDTO;
import com.matei.customeraccountaggregation.dto.AlertsDTO;
import com.matei.customeraccountaggregation.dto.TestAlertDTO;
import com.matei.customeraccountaggregation.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public AlertDTO createUserAlert(@Valid @RequestBody AlertDTO alertDTO) {
        return alertService.upsertUserAlert(alertDTO);
    }

    @GetMapping("/{user_id}")
    public AlertsDTO getUserAlert(@PathVariable("user_id") Long id) {
        return new AlertsDTO(alertService.getAlert(id));
    }

    @PutMapping
    public AlertDTO updateUserAlert(@Valid @RequestBody AlertDTO alertDTO) {
        return alertService.upsertUserAlert(alertDTO);
    }

    @DeleteMapping("/{account_id}")
    public void deleteUserAlert(@PathVariable("account_id") UUID accountId) {
        alertService.deleteUserAlert(accountId);
    }

    @PostMapping("/simulate")
    public void simulateAlert(@Valid @RequestBody TestAlertDTO testAlertDTO) {
        alertService.simulateAlert(testAlertDTO);
    }
}
