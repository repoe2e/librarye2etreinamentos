package br.com.e2etreinamentos.library_e2e.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.e2etreinamentos.library_e2e.exception.CadastroLivroException.LivroJaCadastradoException;
import br.com.e2etreinamentos.library_e2e.model.Livros;
import br.com.e2etreinamentos.library_e2e.service.LivrosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/api/livros")
@Api(value = "Livros Controller", description = "Operações relacionadas a livros")
public class LivrosController {

	@Autowired
	private LivrosService livroService;

	@GetMapping
	@ApiOperation(value = "Lista todos os livros", response = List.class)
	public ResponseEntity<List<Livros>> listarLivros() {
		List<Livros> livros = livroService.listarLivros();
		return ResponseEntity.ok().body(livros);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Obtém um livro pelo ID", response = Livros.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Livro encontrado"),
			@ApiResponse(code = 404, message = "Livro não encontrado") })
	public ResponseEntity<Livros> listarLivro(@PathVariable Long id) {
		Optional<Livros> livro = livroService.listarLivro(id);
		return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/cadastrar")
	@ApiOperation(value = "Cadastra um novo livro")
	public ResponseEntity<?> cadastrarLivro(@RequestBody Livros livro) {
		try {
			Livros novoLivro = livroService.cadastrarLivro(livro);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
		} catch (LivroJaCadastradoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/cadastrar-em-massa")
	@ApiOperation(value = "Cadastra uma lista de livros em massa")
	public ResponseEntity<?> cadastrarLivrosEmMassa(@RequestBody List<Livros> livros) {
		try {
			List<Livros> novosLivros = livroService.cadastrarLivrosEmMassa(livros);
			return ResponseEntity.status(HttpStatus.CREATED).body(novosLivros);
		} catch (LivroJaCadastradoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um livro pelo ID")
	public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
		livroService.removerLivro(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um livro pelo ID")
	public ResponseEntity<Livros> atualizarLivro(@PathVariable Long id, @RequestBody Livros livro) {
		Livros livroAtualizado = livroService.atualizarLivro(id, livro);
		return ResponseEntity.ok(livroAtualizado);
	}
}
