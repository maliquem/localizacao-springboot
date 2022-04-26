package io.github.maliquem.localizacao;

import io.github.maliquem.localizacao.domain.entity.Cidade;
import io.github.maliquem.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {

		pesquisarCidadePorNomeLike("porto%");

	}

	void salvarCidade(Long id, String nome, Long habitantes){
		cidadeRepository.save(new Cidade(id, nome, habitantes));
	}

	void pesquisarCidadesTodas(){
		cidadeRepository.findAll().forEach(System.out::println);
	}

	void pesquisarCidadePorNome(String nome){
		cidadeRepository.findByName(nome).forEach(System.out::println);
	}

	void pesquisarCidadePorNomeLike(String nome){
		cidadeRepository.findByNameLike(nome).forEach(System.out::println);
	}

	void pesquisarCidadePorInicio(String nome){
		cidadeRepository.findByNameStartsWith(nome).forEach(System.out::println);
	}

	void pesquisarCidadePorFim(String nome){
		cidadeRepository.findByNameEndsWith(nome).forEach(System.out::println);
	}

	void pesquisarCidade(String nome){
		cidadeRepository.findByNameContains(nome).forEach(System.out::println);
	}

	void pesquisarCidadePorHabitantes(Long habitantes){
		cidadeRepository.findByHabitantes(habitantes).forEach(System.out::println);
	}


	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
