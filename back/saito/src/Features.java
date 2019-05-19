public class Features {
    int numGenres=100;

    public int features[][]=new int[10][10];//num users * num genres
    public int maxFeature[]=new int[10];//num genres
    //for extract genres
    public int genresParents[]=new int[100];//numGenres
    //extract_genres
    void genresCompute()
    {

    }

    //extract_features
    void featuresSearches()
    {

    }
    void featuresRatings()
    {

    }
    void featuresDownloads()
    {

    }
    void extractFeatures()
    {
        genresCompute();
        // for(int genre_id=0;genre_id<numGenres;genre_id++)
        // System.out.println("genre"+genre_id+" -> "+genre_parents[genre_id]);
        // fflush(stdout);
        for(int i=1;i<numGenres;i++)
            maxFeature[i]=0;
        for(int i=0;i<numGenres;i++)
            for(int j=0;j<numGenres;j++)
                features[i][j]=0;
        featuresRatings();
        featuresSearches();
        featuresDownloads();
    }

}
