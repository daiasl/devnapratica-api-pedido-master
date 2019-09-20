package br.edu.senior.devnapratica.pedidospdv.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.services.ProdutoService;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(method = RequestMethod.GET, path="v1/produtos")
	public ResponseEntity<List<Produto>> listar(){
		List<Produto> produtos = produtoService.buscarTodos();
		return ResponseEntity.ok(produtos);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="v1/produtos/{produtoId}")
	public ResponseEntity<Produto> buscar(Long produtoId){
		Optional<Produto> produtoOpt = produtoService.buscar(produtoId);
		
		if (produtoOpt.isPresent()) {
			return ResponseEntity.ok(produtoOpt.get());
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);				
	}
	
	
	
	
}
