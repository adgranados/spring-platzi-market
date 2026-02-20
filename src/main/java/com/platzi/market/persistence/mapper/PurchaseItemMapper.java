package com.platzi.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.platzi.market.domain.entity.PurchaseItem;
import com.platzi.market.persistence.entity.ComprasProducto;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface PurchaseItemMapper {
        @Mappings({
                        @Mapping(source = "id.idProducto", target = "productId"),
                        @Mapping(source = "cantidad", target = "quantity"),
                        @Mapping(source = "total", target = "total"),
                        @Mapping(source = "estado", target = "active"),
        })
        PurchaseItem toPurchaseItem(ComprasProducto comprasProducto);

        @InheritInverseConfiguration
        @Mappings({
                        @Mapping(target = "id.idCompra", ignore = true),
                        @Mapping(target = "producto", ignore = true),
                        @Mapping(target = "compra", ignore = true),
        })
        ComprasProducto toComprasProducto(PurchaseItem purchaseItem);
}
