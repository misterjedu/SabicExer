package com.jedun.sabipay.common.utils

interface iDomainMapper<Dto, DomainModel> {

    fun mapToDomain(entity: Dto): DomainModel

}