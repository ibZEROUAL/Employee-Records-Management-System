package com.employee.system.mapper;

import com.employee.system.dto.AuditTrailDto;
import com.employee.system.model.AuditTrail;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuditTrailMapper {

    List<AuditTrail> toEntity(List<AuditTrailDto> auditTrailDto);

    AuditTrail toEntity(AuditTrailDto auditTrailDto);

    List<AuditTrailDto> toDto(List<AuditTrail> auditTrail);

    AuditTrailDto toDto(AuditTrail auditTrail);

}