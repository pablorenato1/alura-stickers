public class Content {
    
    private final String title;
    private final String urlImage;
    private final String rateImage;

    public Content(String title, String urlImage) {
        this.title = title;
        this.urlImage = urlImage;
        this.rateImage = null;
    }

    public Content(String title, String urlImage, String rateImage) {
        this.title = title;
        this.urlImage = urlImage;
        this.rateImage = rateImage;
    }

    public String getTitle() {
        return title;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public String getRateImage() {
        return rateImage;
    }
    
}
