package br.com.star.wars.model.entity;

public class Film {
    private String title;
    private String swApiUrl;
    private String episode_id;

    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(String episode_id) {
        this.episode_id = episode_id;
    }

    public String getSwApiUrl() {
        return swApiUrl;
    }

    public void setSwApiUrl(String swApiUrl) {
        this.swApiUrl = swApiUrl;
    }
}
