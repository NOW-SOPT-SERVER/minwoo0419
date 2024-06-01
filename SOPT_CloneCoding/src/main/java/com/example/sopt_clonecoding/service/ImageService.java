package com.example.sopt_clonecoding.service;

import com.example.sopt_clonecoding.domain.Image;
import com.example.sopt_clonecoding.domain.Item;
import com.example.sopt_clonecoding.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public void createImage(
            final String imageUrl,
            final Item item){
        Image image = Image.create(imageUrl, item);
        imageRepository.save(image);
    }

    public List<Image> findAllByItem(Item item){
        return imageRepository.findAllByItem(item);
    }

    @Transactional
    public void removeImage(Image image){
        imageRepository.delete(image);
    }
}
