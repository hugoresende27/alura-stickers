public enum API {

    IMDB_TOP_MOVEIS("https://imdb-api.com/en/API/Top250Movies/k_6k2swk8s",
            new ContentExtractorIMDB()),
    IMDB_TOP_SERIES("https://imdb-api.com/en/API/Top250TVs/k_6k2swk8s",
            new ContentExtractorIMDB()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=PoqBCvCYHGs7XvpEm15MJIx0VR9V5umOE2OanU8D&start_date=2022-06-12&end_date=2022-06-14",
            new ContentExtractorNASA());

    private String url;

    private ContentExtractor extractor;

    API (String url, ContentExtractor extractor) {
        this.url = url;
        this.extractor = extractor;
    }

    public String getUrl() {
        return url;
    }
    public ContentExtractor getExtractor() {
        return extractor;
    }

}

