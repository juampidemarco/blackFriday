package com.cbi.blackFriday.mapper;

import com.cbi.blackFriday.dao.request.OfferRequest;
import com.cbi.blackFriday.entities.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper{

    OfferMapper INSTANCE = Mappers.getMapper( OfferMapper.class );

    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "id_product", source = "request.idProd")
    @Mapping(target = "id_user", source = "request.idClient")
    @Mapping(target = "payment", source = "request.paymentMethod")
    Offer toOffer(OfferRequest request);
}
