package belitsoft_backend.model;

import java.time.ZonedDateTime;

public class Repo {
    private final String name;
    private final int forksCount;
    private final int stargazersCount;
    private final String htmlUrl;
    private final ZonedDateTime updatedAt;

    public Repo(String name, int forksCount, int stargazersCount, String htmlUrl, ZonedDateTime updatedAt) {
        this.name = name;
        this.forksCount = forksCount;
        this.stargazersCount = stargazersCount;
        this.htmlUrl = htmlUrl;
        this.updatedAt = updatedAt;
    }

    public String getName() { return name; }
    public int getForksCount() { return forksCount; }
    public int getStargazersCount() { return stargazersCount; }
    public String getHtmlUrl() { return htmlUrl; }
    public ZonedDateTime getUpdatedAt() { return updatedAt; }
}