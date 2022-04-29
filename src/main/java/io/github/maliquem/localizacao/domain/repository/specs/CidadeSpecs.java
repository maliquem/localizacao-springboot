package io.github.maliquem.localizacao.domain.repository.specs;

import io.github.maliquem.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {

    private CidadeSpecs(){
    }

    public static Specification<Cidade> propertyEqual(String property, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(property), value);
    }

    public static Specification<Cidade> idEqual(Long id){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Cidade> nameEqual(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Cidade> habitantesGreaterThan(Long value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("habitantes"), value);
    }

    public static Specification<Cidade> habitantesBetween(Long min, Long max){
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("habitantes"), min, max);
    }

    public static Specification<Cidade> nameLike(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name + "%".toUpperCase() );
    }
}
