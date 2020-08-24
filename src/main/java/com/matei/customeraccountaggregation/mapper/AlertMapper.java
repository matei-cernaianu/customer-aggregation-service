package com.matei.customeraccountaggregation.mapper;

import com.matei.customeraccountaggregation.dto.AlertDTO;
import com.matei.customeraccountaggregation.entity.Alert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AlertMapper {

    @Mapping(target = "alertPK.user.userId", source = "userId")
    @Mapping(target = "alertPK.accountId", source = "accountId")
    Alert mapToEntity(AlertDTO alertDTO);

    @Mapping(source = "alert.alertPK.user.userId", target = "userId")
    @Mapping(source = "alert.alertPK.accountId", target = "accountId")
    AlertDTO mapToDto(Alert alert);
}
