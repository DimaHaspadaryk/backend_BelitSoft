package com.example.test.service;

import com.example.test.dto.RepoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class RepoService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<RepoDTO> getRepos(String org, int limit, String sort) {
        String gitUrl = "https://api.github.com/orgs/";
        String url = UriComponentsBuilder
                .fromUriString(gitUrl + org + "/repos")
                .toUriString();

        RepoDTO[] repos = restTemplate.getForObject(url, RepoDTO[].class);
        if (repos == null) return List.of();

        Comparator<RepoDTO> comparator = sort.equals("updated")
                ? Comparator.comparing(RepoDTO::getUpdatedAt).reversed()
                : Comparator.comparing(RepoDTO::getStargazersCount).reversed();

        return Arrays.stream(repos)
                .sorted(comparator)
                .limit(limit)
                .toList();
    }
}
