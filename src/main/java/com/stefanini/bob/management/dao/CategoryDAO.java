package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.Category;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Category.class)
public interface CategoryDAO {
}
