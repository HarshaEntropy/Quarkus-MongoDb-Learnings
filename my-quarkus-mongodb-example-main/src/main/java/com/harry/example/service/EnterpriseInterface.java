package com.harry.example.service;

import com.harry.example.dto.DetailEnterprise;
import com.harry.example.dto.Enterprise;

import javax.validation.Valid;
import java.util.List;

public interface EnterpriseInterface {
  List<Enterprise> findAllEnterprises();

  Enterprise findEnterpriseById(String id);

  Enterprise createEnterprise(@Valid Enterprise enterpriseEntity);

  DetailEnterprise createDetailEnterprise(@Valid DetailEnterprise detailEnterprise);
}
