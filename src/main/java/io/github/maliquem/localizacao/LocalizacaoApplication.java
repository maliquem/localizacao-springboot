package io.github.maliquem.localizacao;

import io.github.maliquem.localizacao.domain.entity.Cidade;
import io.github.maliquem.localizacao.domain.repository.CidadeRepository;
import io.github.maliquem.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {

		/**
		 * 		salvarCidade(1L, "São Paulo", 12396372L);
		 * 		salvarCidade(2L, "Rio de Janeiro", 6775561L);
		 * 		salvarCidade(3L, "Fortaleza", 2703391L);
		 * 		salvarCidade(4L, "Salvador", 2900319L);
		 * 		salvarCidade(5L, "Belo Horizonte", 2530701L);
		 * 		salvarCidade(6L, "Porto Alegre", 1492530L);
		 * 		salvarCidade(7L, "Porto Velho", 548952L);
		 * 		salvarCidade(8L, "Palmas", 313349L);
		 * 		salvarCidade(9L, "Recife", 1661017L);
		 * 		salvarCidade(10L, "Natal", 896708L);
		 * 		salvarCidade(11L, "Brasilia", 3094325L);
		 */

		cidadeService.pesquisarCidadePorNomeLikePaginado("%%%%", 1, 2);


	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
