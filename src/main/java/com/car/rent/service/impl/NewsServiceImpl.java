package com.car.rent.service.impl;

import com.car.rent.model.DTO.NewDTO;
import com.car.rent.model.News;
import com.car.rent.repository.NewsRepository;
import com.car.rent.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    public List<News> getNewsGeneralInfoByParam(String param) {
        return newsRepository
                .findAll().stream()
                .filter(news -> news.getText().contains(param)
                        || news.getTitle().contains(param))
                .collect(Collectors.toList());
    }

    @Override
    public void addNews(NewDTO newDTO) {
        News news = News.builder()
                .title(newDTO.getTitle())
                .text(newDTO.getText())
                .date(LocalDateTime.now())
                .build();
        newsRepository.save(news);
    }

    @Override
    public void deleteNew(Long id) {
        newsRepository.deleteById(id);
    }
}
