package br.edu.senior.devnapratica.pedidospdv.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;
import br.edu.senior.devnapratica.pedidospdv.services.PedidoService;

@RestController
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/v1/pedidos")
	public ResponseEntity<Pedido> realizarPedido(@RequestBody Pedido pedido){
		pedido.getCliente().setId((long) 1);
				
		Pedido pedidoRealizado = pedidoService.salvar(pedido);
		return new ResponseEntity<>(pedidoRealizado, HttpStatus.CREATED);
	}	
	
	@RequestMapping(method = RequestMethod.GET, path = "/v1/pedidos/{pedidoId}")
	public ResponseEntity<Pedido> buscar(@PathVariable("pedidoId") Long idDoPedido) {
		Optional<Pedido> pedidoOpt = pedidoService.buscar(idDoPedido);
		
		if(pedidoOpt.isPresent()) {
			return ResponseEntity.ok(pedidoOpt.get());
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/v1/pedidos/{pedido.id}")
	public ResponseEntity<Pedido> alterar(@RequestBody Pedido pedido){
		Pedido pedidoAlterado = pedidoService.alterar(pedido);
		return new ResponseEntity<Pedido>(pedidoAlterado, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/v1/pedidos/{pedidoId}")
	public void excluir(@PathVariable("pedidoId") Long pedidoId) {
		pedidoService.excluir(pedidoId);	
	}	
	
	
}
