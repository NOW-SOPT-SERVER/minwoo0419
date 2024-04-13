package com.example.sopt_clonecoding.repository;

import com.example.sopt_clonecoding.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
