package br.edu.senior.devnapratica.pedidospdv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.senior.devnapratica.pedidospdv.dao.ProdutoDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Produto;

@Service
public class ProdutoService {
	
	private ProdutoDAO produtoDAO;
	
	public List<Produto> buscarTodos(){
		return produtoDAO.buscarTodos();
	}
	public Optional<Produto> buscar(Long produtoId){
		return produtoDAO.buscar(produtoId);
	}
	public Produto salvar(Produto produto) {
		validarProduto(produto.getValor());
		return produtoDAO.salvar(produto);
	}
	public Produto alterar(Produto produto) {
		validarProduto(produto.getValor());
		return produtoDAO.alterar(produto);
	}
	public void excluir (Long produtoId) {
		produtoDAO.excluir(produtoId);
	}
	private void validarProduto(Double valor) {
		if (valor == null) {
			throw new IllegalArgumentException("O valor do produto não pode ser nulo!");
		}
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor do produto deve ser maior que zero!");			
		}		
	}
	
}
