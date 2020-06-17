package com.backend.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.cursomc.domain.Cliente;
import com.backend.cursomc.repositories.ClienteRepository;
import com.backend.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		return repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
						+ ", Tipo: " + Cliente.class.getName()));
	}
	
}
