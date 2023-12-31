package com.harry.example.service.impl;

import com.harry.example.dto.DetailEnterprise;
import com.harry.example.dto.Enterprise;
import com.harry.example.dto.EnterpriseAddress;
import com.harry.example.entity.EnterpriseAddressEntity;
import com.harry.example.mapper.DetailEnterpriseMapper;
import com.harry.example.mapper.EnterpriseMapper;
import com.harry.example.entity.AddressEntity;
import com.harry.example.entity.EnterpriseEntity;
import com.harry.example.mapper.EnterpriseAddressMapper;
import com.harry.example.service.EnterpriseInterface;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class EnterpriseService implements EnterpriseInterface {

  @Inject
  EnterpriseMapper enterpriseMapper;
  @Inject
  DetailEnterpriseMapper detailEnterpriseMapper;
  @Inject
  EnterpriseAddressMapper enterpriseAddressMapper;

  public List<Enterprise> findAllEnterprises() {
    return this.enterpriseMapper.toDtoList(EnterpriseEntity.listAll());
  }

  public Enterprise findEnterpriseById(String id) {
    final ObjectId enterpriseId = new ObjectId(id);
    return this.enterpriseMapper.toDto(EnterpriseEntity.findById(enterpriseId));
  }

  public Enterprise createEnterprise(@Valid Enterprise enterprise) {
    EnterpriseEntity enterpriseEntity = this.enterpriseMapper.toEntity(enterprise);
    EnterpriseEntity.persist(enterpriseEntity);
    return this.enterpriseMapper.toDto(enterpriseEntity);
  }

  public DetailEnterprise createDetailEnterprise(@Valid DetailEnterprise detailEnterprise) {
    //create & persist EnterpriseEntity from DetailEnterprise
    EnterpriseEntity enterpriseEntity = this.detailEnterpriseMapper.toEnterpriseEntity(detailEnterprise);
    EnterpriseEntity.persist(enterpriseEntity);

    //create & persist AddressEntity from DetailEnterprise
    AddressEntity addressEntity = this.detailEnterpriseMapper.toAddressEntity(detailEnterprise);
    AddressEntity.persist(addressEntity);

    //create & persist EnterpriseAddressEntity with EnterpriseEntity & AddressEntity
    EnterpriseAddress enterpriseAddress = EnterpriseAddress.of(enterpriseEntity.id.toString(), addressEntity.id.toString());
    EnterpriseAddressEntity enterpriseAddressEntity = this.enterpriseAddressMapper.toEntity(enterpriseAddress);
    EnterpriseAddressEntity.persist(enterpriseAddressEntity);

    return this.detailEnterpriseMapper.toDetailEnterprise(enterpriseEntity, addressEntity);
  }
}
