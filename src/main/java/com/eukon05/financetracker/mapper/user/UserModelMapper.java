package com.eukon05.financetracker.mapper.user;

import com.eukon05.financetracker.domain.User;
import com.eukon05.financetracker.dto.RegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserModelMapper {

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User mapRegisterRequestDTOToUser(RegisterDTO dto);

}
