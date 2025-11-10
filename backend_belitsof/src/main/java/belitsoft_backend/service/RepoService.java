package belitsoft_backend.service;

import belitsoft_backend.dto.RepoDTO;
import belitsoft_backend.model.RepoSortType;
import belitsoft_backend.repository.ReposDataRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RepoService {

    private final ReposDataRepository repoRepository;

    public RepoService(ReposDataRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public List<RepoDTO> getRepos(String org, int limit, RepoSortType sortType) {
        List<RepoDTO> repos = repoRepository.fetchRepos(org);

        Comparator<RepoDTO> comparator = switch (sortType) {
            case UPDATED -> Comparator.comparing(RepoDTO::getUpdatedAt).reversed();
            case STARS -> Comparator.comparing(RepoDTO::getStargazersCount).reversed();
        };

        return repos.stream()
                .sorted(comparator)
                .limit(limit)
                .toList();
    }
}
