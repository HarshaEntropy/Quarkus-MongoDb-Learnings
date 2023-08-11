package com.harry.example.mapper;

import com.harry.example.dto.EnterpriseAddress;
import com.harry.example.entity.EnterpriseAddressEntity;
import org.bson.types.ObjectId;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "cdi",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EnterpriseAddressMapper {

  @Mappings({
      @Mapping(target = "id", expression = "java(enterpriseAddressEntity.id.toString())"),
      @Mapping(target = "enterpriseId", source = "enterpriseAddressEntity.enterpriseId"),
      @Mapping(target = "addressId", source = "enterpriseAddressEntity.addressId")
  })
  EnterpriseAddress toDto(EnterpriseAddressEntity enterpriseAddressEntity);

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "enterpriseId", source = "enterpriseAddress.enterpriseId"),
      @Mapping(target = "addressId", source = "enterpriseAddress.addressId")
  })
  EnterpriseAddressEntity toEntity(EnterpriseAddress enterpriseAddress);

  @AfterMapping
  default void setEntityId(EnterpriseAddress enterpriseAddress, @MappingTarget EnterpriseAddressEntity enterpriseAddressEntity) {
    if(Objects.nonNull(enterpriseAddress.id))
      enterpriseAddressEntity.id = new ObjectId(enterpriseAddress.id);
  }
}
