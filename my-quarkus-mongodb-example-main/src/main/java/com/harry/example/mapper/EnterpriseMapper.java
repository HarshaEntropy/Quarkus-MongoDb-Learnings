package com.harry.example.mapper;

import com.harry.example.dto.Enterprise;
import com.harry.example.entity.EnterpriseEntity;
import org.bson.types.ObjectId;
import org.mapstruct.*;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "cdi",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EnterpriseMapper {

  @Mapping(target = "id", ignore = true)
  EnterpriseEntity toEntity(Enterprise enterprise);

  @Mapping(target = "id", expression = "java(enterpriseEntity.id.toString())")
  Enterprise toDto(EnterpriseEntity enterpriseEntity);

  List<EnterpriseEntity> toEntityList(List<Enterprise> enterpriseList);

  List<Enterprise> toDtoList(List<EnterpriseEntity> enterpriseEntityList);

  @AfterMapping
  default void setEntityId(Enterprise enterprise, @MappingTarget EnterpriseEntity enterpriseEntity) {
    if(Objects.nonNull(enterprise.id))
      enterpriseEntity.id = new ObjectId(enterprise.id);
  }
}
