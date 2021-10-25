package com.car.rent.controller.news;

import com.car.rent.model.DTO.NewDTO;
import com.car.rent.model.News;
import com.car.rent.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/news")
    public String showUsers(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<News> news = newsService.getNewsGeneralInfoByParam(name);
        model.addAttribute("result", news);
        model.addAttribute("search", name);
        return "news/news";
    }

    @GetMapping("/add")
    public String getNewPage() {
        return "news/add";
    }

    @PostMapping("/add")
    public String addNew(@ModelAttribute NewDTO news) {
        newsService.addNews(news);
        return "redirect:/news/news";
    }

    @GetMapping("/delete")
    public String deleteNew(@RequestParam(value = "id", required = false) Long id, Model model) {
        newsService.deleteNew(id);
        return "redirect:/news/news";
    }
}
