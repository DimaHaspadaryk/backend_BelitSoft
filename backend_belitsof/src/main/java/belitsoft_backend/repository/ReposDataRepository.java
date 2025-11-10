package belitsoft_backend.repository;

import belitsoft_backend.dto.RepoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Repository
@Slf4j
public class ReposDataRepository {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GITHUB_API_URL = "https://api.github.com/orgs/";

    public List<RepoDTO> fetchRepos(String org) {
        String url = UriComponentsBuilder
                .fromUriString(GITHUB_API_URL + org + "/repos")
                .toUriString();

        try {
            RepoDTO[] repos = restTemplate.getForObject(url, RepoDTO[].class);
            if (repos == null) {
                log.warn("No data for GitHub repository {}", org);
                return List.of();
            }
            return List.of(repos);
        } catch (RestClientException e) {
            log.error("Exception for calling GitHub API {}: {}", org, e.getMessage());
            return List.of();
        }
    }
}
