package com.example.test.controller;

import com.example.test.dto.RepoDTO;
import org.springframework.web.bind.annotation.*;
import com.example.test.service.RepoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RepoController {
    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK");
    }

    @GetMapping("/org/{org}/repos")
    public List<RepoDTO> getRepos(
            @PathVariable String org,
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "stars") String sort
    ) {
        return repoService.getRepos(org, limit, sort);
    }

}
