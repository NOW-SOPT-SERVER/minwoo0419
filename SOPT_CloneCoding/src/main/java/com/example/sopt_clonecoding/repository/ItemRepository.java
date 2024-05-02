package com.example.sopt_clonecoding.repository;

import com.example.sopt_clonecoding.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByPlace(String place);
}
