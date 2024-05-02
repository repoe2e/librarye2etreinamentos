package br.com.e2etreinamentos.library_e2e.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.e2etreinamentos.library_e2e.exception.CadastroLivroException;
import br.com.e2etreinamentos.library_e2e.model.Livros;
import br.com.e2etreinamentos.library_e2e.repository.LivroRepository;

@Service
public class LivrosService {

	@Autowired
	private LivroRepository livroRepository;

	public List<Livros> listarLivros() {
		return livroRepository.findAll();
	}

	public Optional<Livros> listarLivro(Long id) {
		return livroRepository.findById(id);
	}

	public Livros cadastrarLivro(Livros livro) {
		if (livroRepository.existsByTituloAndAutor(livro.getTitulo(), livro.getAutor())) {
			throw new CadastroLivroException.LivroJaCadastradoException();
		}
		if (livro.getTitulo() == null || livro.getAutor() == null) {
			throw new CadastroLivroException.CamposInvalidosException();
		}
		if (livro.getQuantidadeEmEstoque() <= 0) {
			throw new CadastroLivroException.QuantidadeInvalidaException();
		}
		return livroRepository.save(livro);
	}
	
	public List<Livros> cadastrarLivrosEmMassa(List<Livros> livros) {
	    List<Livros> novosLivros = new ArrayList<>();
	    for (Livros livro : livros) {
	        if (livroRepository.existsByTituloAndAutor(livro.getTitulo(), livro.getAutor())) {
	            throw new CadastroLivroException.LivroJaCadastradoException();
	        }
	        if (livro.getTitulo() == null || livro.getAutor() == null) {
	            throw new CadastroLivroException.CamposInvalidosException();
	        }
	        if (livro.getQuantidadeEmEstoque() <= 0) {
	            throw new CadastroLivroException.QuantidadeInvalidaException();
	        }
	        novosLivros.add(livroRepository.save(livro));
	    }
	    return novosLivros;
	}


	public void removerLivro(Long id) {
		livroRepository.deleteById(id);
	}

	public Livros atualizarLivro(Long id, Livros livro) {
		livro.setId(id);
		return livroRepository.save(livro);
	}

}
