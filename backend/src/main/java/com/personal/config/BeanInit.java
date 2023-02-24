package com.personal.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.personal.util.ModelMapper;

@Component
public class BeanInit {

	@Bean("mapper")
	public ModelMapper initModelMapper() {
		return Mappers.getMapper(ModelMapper.class);
	}

}
