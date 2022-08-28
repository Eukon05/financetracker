package com.eukon05.financetracker.user.mapper;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.dto.RegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserModelMapper {

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User mapRegisterDTOToUser(RegisterDTO dto);

}
