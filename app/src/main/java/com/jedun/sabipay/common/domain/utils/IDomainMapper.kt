package com.jedun.sabipay.common.domain.utils

interface iDomainMapper<Dto, DomainModel> {

    fun mapToDomain(entity: Dto): DomainModel

}