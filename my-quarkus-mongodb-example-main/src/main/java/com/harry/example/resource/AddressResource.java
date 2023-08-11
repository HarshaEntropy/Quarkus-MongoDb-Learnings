package com.harry.example.resource;

import com.harry.example.dto.Address;
import com.harry.example.dto.shared.AbstractGenericType;
import com.harry.example.service.impl.AddressService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path(EnterpriseResource.RESOURCE_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource extends AbstractGenericType {

  public static final String RESOURCE_PATH = "/address";

  @Inject
  AddressService addressService;

  @POST
  public Response createAddress(Address address, @Context UriInfo uriInfo) {
    address = this.addressService.createAddress(address);
    return Response.created(genericUriBuilder(address.id, uriInfo).build()).entity(address).build();
  }
}
