package server;

import static java.lang.Math.pow;

public class Recommend {
    int maxCentroids=100;
    int numFeatures=100;
    int numGenres=100;
    //for recommend
    int clusters[]=new int[1000];//num of desired clusters
    Features featureExtraction=new Features();
    int userID;//o sa trebuiasca setat dupa ce se face loginul
    int numUsers=1000000;
    int numBooks=1000;
    int numRecommendations;
    Book candidateBooks[]=new Book[100];//NUM_BOOKS
    Book tem=new Book();
    int booksRead[]=new int[100];//num books
    public void setUserID(int userID) {
        this.userID = userID;
    }

    //for extract_features
    double kmeans(int numIterations,int numCentroids,int numUsers)
    {
        featureExtraction.extractFeatures();
        double cost,minDistance=-1;
        double u[][]=new double[maxCentroids][numFeatures];
        int numPoints[]=new int[maxCentroids];
        for(int k=0;k<numCentroids;k++)
        {
            for(int i=1;i<numFeatures;i++)
            {
                u[k][i]=Math.random()*featureExtraction.maxFeature[i];
            }

        }
        for(int iter=0;iter<numIterations;iter++)
        {

            //cluster assigment
            for(int i=0;i<numUsers;i++)
            {
                minDistance=-1;
                for(int k=0;k<numCentroids;k++)
                {
                    double distance=0;
                    for(int j=1;j<numFeatures;j++)
                    {
                        distance=distance+ pow(featureExtraction.features[i][j]-u[k][j],2);
                    }
                    distance=Math.sqrt(distance);
                    if(distance<minDistance ||minDistance==-1)
                    {
                        minDistance=distance;
                        clusters[i]=k;
                    }
                }

            }
            //moving centroid
            for(int k=0;k<numCentroids;k++)
            {
                for(int i=1;i<numFeatures;i++)
                    u[k][i]=0;

            }
            for(int i=0;i<numUsers;i++)
            {
                for(int j=1;j<numFeatures;j++)
                {
                    u[clusters[i]][j]=u[clusters[i]][j]+featureExtraction.features[i][j];
                    numPoints[clusters[i]]+=1;
                }
            }
            for(int i=0;i<numCentroids;i++)
            {
                for(int j=1;j<numFeatures;j++)
                {
                    u[i][j]=u[i][j]/numPoints[i];
                }
            }
        }
        //calculate cost
        cost=0;
        for(int i=0;i<numUsers;i++)
        {
            double distance=0;
            for(int j=1;j<numFeatures;j++)
            {
                distance=distance+Math.pow(featureExtraction.features[i][j]-u[clusters[i]][j],2);
            }
            distance=Math.sqrt(distance);
            //printf("distanta de la user %d=%f\n",i,distance);
            cost=cost+distance;
        }
        cost=cost/(float)(numUsers);
        System.out.println(cost);

        for(int i=0;i<numUsers;i++){
            System.out.println("user"+i+"->"+clusters[i]);
        }

        return cost;
    }
    //recommend
    void update_scores(char type,int user,int rating,int book_id)
    {

        if(user!=userID && booksRead[book_id]!=1)
        {
            candidateBooks[book_id].rating=candidateBooks[book_id].rating+rating;
            candidateBooks[book_id].id=book_id;
        }
        else if(type!='s' && type!='a' && user==userID)
        {
            System.out.println("user:"+userID+"read book "+book_id);
            booksRead[book_id]=1;
        }
    }

    void book_score_ratings(int user)
    {

    }
    void book_score_searches(int user)
    {

    }
    void book_score_downloads(int user)
    {

    }
    void book_score_author(int user)
    {

    }
    void update_book_scores(int user)
    {
        book_score_ratings(user);
        book_score_searches(user);
        book_score_downloads(user);
        book_score_author(user);

    }
    void recommend(int user_id)
    {
        int cluster,max=0;
        int c_min[]=new int[numUsers];

        double cost,cost_min=-1;
        for(int it=0;it<10;it++)
        {
            //kmeans num_iterations,num_centroids,num_users
            cost=kmeans(1000,2,5);
            if(cost<cost_min || cost_min == -1)
            {
                cost_min=cost;
                for(int i=0;i<numUsers;i++)
                {
                    c_min[i]=clusters[i];
                }
            }
        }

        cluster=c_min[user_id];

        //marcam cititele
        for(int i=0;i<numBooks;i++)
        {
            booksRead[i]=0;
        }
        update_book_scores(userID);

        for(int user=1;user<=numUsers;user++)
        {
            if(c_min[user]==cluster&&user!=userID)
            {
                //users in the same cluster as the user we want to make recommendations to
                update_book_scores(user);
            }
        }
        for(int i=0;i<numBooks-1;i++)
        {

            for(int j=i+1;j<numBooks;j++)
            {
                if(candidateBooks[i].rating<candidateBooks[j].rating)
                {
                    tem.rating=candidateBooks[i].rating;
                    tem.id=candidateBooks[i].id;
                    candidateBooks[i].rating=candidateBooks[j].rating;
                    candidateBooks[i].id=candidateBooks[j].id;
                    candidateBooks[j].id=tem.id;
                    candidateBooks[j].rating=tem.rating;
                }
            }
        }

        numRecommendations=0;
        while(candidateBooks[numRecommendations].rating>0)
        {
            numRecommendations++;
        }
    }
}
