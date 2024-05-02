package br.com.e2etreinamentos.library_e2e.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.e2etreinamentos.library_e2e.model.Livros;


public interface LivroRepository extends JpaRepository<Livros,Long> {
	
	@Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Livros l WHERE l.titulo = :titulo AND l.autor = :autor")
    boolean existsByTituloAndAutor(@Param("titulo") String titulo, @Param("autor") String autor);

}
