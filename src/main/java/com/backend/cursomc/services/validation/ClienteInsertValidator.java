package com.backend.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.backend.cursomc.domain.enums.TipoCliente;
import com.backend.cursomc.dto.ClienteNewDTO;
import com.backend.cursomc.resources.exception.FieldMessage;
import com.backend.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	@Override
	public void initialize(ClienteInsert ann){}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context){
		List<FieldMessage> list = new ArrayList<>();
		
		if(TipoCliente.toEnum(objDto.getTipo()).equals(TipoCliente.PESSOAFISICA) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if(TipoCliente.toEnum(objDto.getTipo()).equals(TipoCliente.PESSOAJURIDICA) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		for(FieldMessage e : list){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
