package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.TimeSheet;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TimeSheet.class)
public interface TimeSheetDAO {
}
