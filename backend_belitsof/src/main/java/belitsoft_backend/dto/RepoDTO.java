package belitsoft_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepoDTO {
    private final String name;
    private final int forksCount;
    private final int stargazersCount;
    private final String htmlUrl;
    private final String updatedAt;

    public RepoDTO(String name, int forksCount, int stargazersCount, String htmlUrl, String updatedAt) {
        this.name = name;
        this.forksCount = forksCount;
        this.stargazersCount = stargazersCount;
        this.htmlUrl = htmlUrl;
        this.updatedAt = updatedAt;
    }

    @JsonProperty("name")
    public String getName() { return name; }

    @JsonProperty("forks_count")
    public int getForksCount() { return forksCount; }

    @JsonProperty("stargazers_count")
    public int getStargazersCount() { return stargazersCount; }

    @JsonProperty("html_url")
    public String getHtmlUrl() { return htmlUrl; }

    @JsonProperty("updated_at")
    public String getUpdatedAt() { return updatedAt; }

}
