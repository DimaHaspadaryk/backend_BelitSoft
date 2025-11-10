package belitsoft_backend.service;

import belitsoft_backend.dto.RepoDTO;
import belitsoft_backend.dto.RepoResponseDTO;
import belitsoft_backend.model.RepoSortType;
import belitsoft_backend.repository.ReposDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepoServiceTest {

    @Mock
    private ReposDataRepository repoRepository;

    @InjectMocks
    private RepoService repoService;

    private final String TEST_ORG = "test-org";
    private List<RepoDTO> mockRepos;

    @BeforeEach
    void setUp() {
        RepoDTO repoA = new RepoDTO("repo-A", 10, 100, "url-A", "2025-10-01T10:00:00Z");
        RepoDTO repoB = new RepoDTO("repo-B", 5, 200, "url-B", "2025-10-15T10:00:00Z");
        RepoDTO repoC = new RepoDTO("repo-C", 20, 50, "url-C", "2025-10-20T10:00:00Z");

        mockRepos = List.of(repoA, repoB, repoC);
    }

    @Test
    void getRepos_sortByStars() {
        int limit = 2;

        when(repoRepository.fetchRepos(TEST_ORG)).thenReturn(mockRepos);

        List<RepoResponseDTO> result = repoService.getRepos(TEST_ORG, limit, RepoSortType.STARS);

        assertEquals(limit, result.size());
        assertEquals("repo-B", result.get(0).getName());
        assertEquals("repo-A", result.get(1).getName());
        verify(repoRepository).fetchRepos(TEST_ORG);
    }

    @Test
    void getRepos_sortByUpdated() {
        int limit = 2;

        when(repoRepository.fetchRepos(TEST_ORG)).thenReturn(mockRepos);

        List<RepoResponseDTO> result = repoService.getRepos(TEST_ORG, limit, RepoSortType.UPDATED);

        assertEquals(limit, result.size());
        assertEquals("repo-C", result.get(0).getName());
        assertEquals("repo-B", result.get(1).getName());
        verify(repoRepository).fetchRepos(TEST_ORG);
    }

    @Test
    void getRepos_returnAllIfLimitExceeded() {
        int limit = 10;

        when(repoRepository.fetchRepos(TEST_ORG)).thenReturn(mockRepos);

        List<RepoResponseDTO> result = repoService.getRepos(TEST_ORG, limit, RepoSortType.STARS);

        assertEquals(mockRepos.size(), result.size());
        verify(repoRepository).fetchRepos(TEST_ORG);
    }

}