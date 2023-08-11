package com.harry.example.service.impl;

import com.harry.example.dto.Address;
import com.harry.example.mapper.AddressMapper;
import com.harry.example.service.AddressInterface;
import com.harry.example.entity.AddressEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AddressService implements AddressInterface {

  @Inject
  AddressMapper addressMapper;

  public Address createAddress(Address address) {
    AddressEntity addressEntity = this.addressMapper.toEntity(address);
    AddressEntity.persist(addressEntity);
    return this.addressMapper.toDto(addressEntity);
  }
}
