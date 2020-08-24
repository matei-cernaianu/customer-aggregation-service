package com.matei.customeraccountaggregation.service;

import com.matei.customeraccountaggregation.dto.AlertDTO;
import com.matei.customeraccountaggregation.dto.TestAlertDTO;
import com.matei.customeraccountaggregation.entity.Alert;
import com.matei.customeraccountaggregation.entity.User;
import com.matei.customeraccountaggregation.exception.UserNotFoundException;
import com.matei.customeraccountaggregation.mapper.AlertMapper;
import com.matei.customeraccountaggregation.messaging.AlertEventProducer;
import com.matei.customeraccountaggregation.repository.AlertRepository;
import com.matei.customeraccountaggregation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlertMapper alertMapper;

    @Autowired
    private AlertEventProducer alertEventProducer;

    @Transactional
    public AlertDTO upsertUserAlert(AlertDTO alertDTO) {
        final User user = userRepository.findById(alertDTO.getUserId())
                .orElseThrow(UserNotFoundException::new);
        Alert alert = alertMapper.mapToEntity(alertDTO);
        alert.getAlertPK().setUser(user);
        final Alert alertFinal = alertRepository.save(alert);
        return alertMapper.mapToDto(alertFinal);
    }

    public List<AlertDTO> getAlert(Long userId) {
        return alertRepository.findAllByAlertPK_User_UserId(userId).stream()
                .map(alertMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserAlert(UUID accountId) {
        final Integer result = alertRepository.deleteAlertByAlertPK_AccountId(accountId);
        if (result == 0) {
            throw new RuntimeException("No alerts found");
        }
    }

    public void simulateAlert(TestAlertDTO testAlertDTO) {
        final Alert alert = alertRepository.findByAlertPK_User_UserIdAndAlertPK_AccountId(testAlertDTO.getUserId(),
                testAlertDTO.getAccountId());
        if (Objects.nonNull(alert) && (testAlertDTO.getSum() > alert.getThreshold())) {
            final User user = alert.getAlertPK().getUser();
            alertEventProducer.sendAlert(user, testAlertDTO.getAccountId(), testAlertDTO.getSum(), alert.getAlertType());
        }
    }
}
