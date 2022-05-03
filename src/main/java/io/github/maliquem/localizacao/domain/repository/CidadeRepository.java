package io.github.maliquem.localizacao.domain.repository;

import io.github.maliquem.localizacao.domain.entity.Cidade;
import io.github.maliquem.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    // PESQUISA PELO NOME COMPLETO USANDO SQL NATIVO
    @Query(nativeQuery = true, value = "SELECT c.id, c.name FROM tb_cidade AS c WHERE c.name =:name ")
    List<CidadeProjection> findByNameSqlNativo(@Param("name") String name);
    // PESQUISA PELO NOME COMPLETO
    List<Cidade> findByName(String name);
    // PESQUISA PELO NOME LIKE E ORGANIZA POR PARAMETRO
    @Query(" SELECT c FROM Cidade c WHERE upper(c.name) LIKE upper(:name) ")
    List<Cidade> findByNameLike(String name, Sort sort);
    // PESQUISA PELO NOME LIKE E ORGANIZA POR PAGINAS
    @Query(" SELECT c FROM Cidade c WHERE upper(c.name) LIKE upper(:name) ")
    Page<Cidade> findByNameLike(String name, Pageable pageable);
    // PESQUISA PELO COMEÇO DO NOME
    List<Cidade> findByNameStartsWith(String name);
    // PESQUISA PELO FINAL DO NOME
    List<Cidade> findByNameEndsWith(String name);
    // PESQUISA POR QUALQUER PARTE DO NOME
    List<Cidade> findByNameContains(String name);
    // PESQUISA PELO NÚMERO DE HABITANTES
    List<Cidade> findByHabitantes(Long habitantes);
    // PESQUISA PELO NÚMERO DE HABITANTES MENOR OU IGUAL
    List<Cidade> findByHabitantesLessThanEqual(Long habitante);
    // PESQUISA PELO NÚMERO DE HABITANTES MAIOR OU IGUAL, E CONTENDO A STRING
    List<Cidade> findByHabitantesGreaterThanEqualAndNameLike(Long habitantes, String name);
}
