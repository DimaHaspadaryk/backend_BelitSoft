package belitsoft_backend.dto;

public class RepoResponseDTO {
    private final String name;
    private final int stars;
    private final String url;

    public RepoResponseDTO(String name, int stars, String url) {
        this.name = name;
        this.stars = stars;
        this.url = url;
    }

    public String getName() { return name; }
    public int getStars() { return stars; }
    public String getUrl() { return url; }
}