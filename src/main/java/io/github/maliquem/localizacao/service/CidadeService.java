package io.github.maliquem.localizacao.service;

import io.github.maliquem.localizacao.domain.entity.Cidade;
import io.github.maliquem.localizacao.domain.repository.CidadeRepository;
import static io.github.maliquem.localizacao.domain.repository.specs.CidadeSpecs.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public void salvarCidade(Long id, String nome, Long habitantes){
        cidadeRepository.save(new Cidade(id, nome, habitantes));
    }

    public void pesquisarCidadesTodas(){
        cidadeRepository.findAll().forEach(System.out::println);
    }

    public void pesquisarCidadePorNome(String nome){
        cidadeRepository.findByName(nome).forEach(System.out::println);
    }

    public void pesquisarCidadePorNomeLike(String nome, String... parametros){
        cidadeRepository.findByNameLike(nome, Sort.by(parametros)).forEach(System.out::println);
    }

    public void pesquisarCidadePorNomeLikePaginado(String nome, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        cidadeRepository.findByNameLike(nome, pageable).forEach(System.out::println);
    }

    public void pesquisarCidadePorInicio(String nome){
        cidadeRepository.findByNameStartsWith(nome).forEach(System.out::println);
    }

    public void pesquisarCidadePorFim(String nome){
        cidadeRepository.findByNameEndsWith(nome).forEach(System.out::println);
    }

    public void pesquisarCidade(String nome){
        cidadeRepository.findByNameContains(nome).forEach(System.out::println);
    }

    public void pesquisarCidadePorHabitantes(Long habitantes){
        cidadeRepository.findByHabitantes(habitantes).forEach(System.out::println);
    }

    public void pesquisarCidadePorHabitantesMenorIgual(Long habitantes){
        cidadeRepository.findByHabitantesLessThanEqual(habitantes).forEach(System.out::println);
    }

    public void pesquisarCidadePorHabitantesMaiorIgualEComNome(Long habitantes, String nome){
        cidadeRepository.findByHabitantesGreaterThanEqualAndNameLike(habitantes, nome).forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return cidadeRepository.findAll(example);
    }

    public void listarCidadesByNameSpec(){
        cidadeRepository.findAll(nameEqual("Fortaleza").and(habitantesGreaterThan(1000000L))).forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (filtro.getId() != null){
            specs = specs.and(idEqual(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getName())){
            specs = specs.and(nameEqual(filtro.getName()));
        }

        if (filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        cidadeRepository.findAll(specs).forEach(System.out::println);
    }
}
