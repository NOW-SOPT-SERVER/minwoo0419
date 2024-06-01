package com.example.sopt_clonecoding.repository;

import com.example.sopt_clonecoding.domain.Image;
import com.example.sopt_clonecoding.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByItem(Item item);
}
