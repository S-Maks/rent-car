package com.car.rent.service;

import com.car.rent.model.DTO.NewDTO;
import com.car.rent.model.News;

import java.util.List;

public interface NewsService {
    List<News> getNewsGeneralInfoByParam(String param);

    void addNews(NewDTO newDTO);

    void deleteNew(Long id);
}
