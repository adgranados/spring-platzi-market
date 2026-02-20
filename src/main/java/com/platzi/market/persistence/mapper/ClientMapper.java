package com.platzi.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.platzi.market.domain.entity.Client;
import com.platzi.market.persistence.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClientMapper {
        @Mappings({
                        @Mapping(source = "id", target = "clientId"),
                        @Mapping(source = "nombre", target = "name"),
                        @Mapping(source = "apellidos", target = "lastName"),
                        @Mapping(source = "correoElectronico", target = "email"),
                        @Mapping(source = "celular", target = "cellphone"),
        })
        Client toClient(Cliente cliente);

        @InheritInverseConfiguration
        @Mappings({
                        @Mapping(target = "compras", ignore = true),
        })
        Cliente toCliente(Client client);
}
