package belitsoft_backend.service;

import belitsoft_backend.dto.RepoDTO;
import belitsoft_backend.dto.RepoResponseDTO; // Новый импорт
import belitsoft_backend.model.Repo;          // Новый импорт
import belitsoft_backend.model.RepoSortType;
import belitsoft_backend.repository.ReposDataRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime; // Новый импорт для даты
import java.util.Comparator;
import java.util.List;

@Service
public class RepoService {

    private final ReposDataRepository repoRepository;

    public RepoService(ReposDataRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    private Repo mapToDomain(RepoDTO dto) {
        ZonedDateTime updated = ZonedDateTime.parse(dto.getUpdatedAt());
        return new Repo(
                dto.getName(),
                dto.getForksCount(),
                dto.getStargazersCount(),
                dto.getHtmlUrl(),
                updated
        );
    }

    private RepoResponseDTO mapToResponse(Repo repo) {
        return new RepoResponseDTO(
                repo.getName(),
                repo.getStargazersCount(),
                repo.getHtmlUrl()
        );
    }

    public List<RepoResponseDTO> getRepos(String org, int limit, RepoSortType sortType) {
        List<RepoDTO> dtoList = repoRepository.fetchRepos(org);

        List<Repo> repos = dtoList.stream()
                .map(this::mapToDomain)
                .toList();

        Comparator<Repo> comparator = switch (sortType) {
            case UPDATED -> Comparator.comparing(Repo::getUpdatedAt).reversed();
            case STARS -> Comparator.comparing(Repo::getStargazersCount).reversed();
        };

        return repos.stream()
                .sorted(comparator)
                .limit(limit)
                .map(this::mapToResponse)
                .toList();
    }
}