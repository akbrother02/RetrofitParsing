package aasheesh.com.myapplication.models;

import java.util.List;

/**
 * Created by user on 6/21/18.
 */

public class ApiDataModel {
    public int resultCount;
    public List<DataModel> results;

    public static class DataModel {
        public String wrapperType;
        public String kind;
        public int artistId;
        public int collectionId;
        public int trackId;
        public String artistName;
        public String collectionName;
        public String trackName;
        public String collectionCensoredName;
        public String trackCensoredName;
        public String artistViewUrl;
        public String collectionViewUrl;
        public String trackViewUrl;
        public String previewUrl;
        public String artworkUrl30;
        public String artworkUrl60;
        public String artworkUrl100;
        public double collectionPrice;
        public double trackPrice;
        public String releaseDate;
        public String collectionExplicitness;
        public String trackExplicitness;
        public int discCount;
        public int discNumber;
        public int trackCount;
        public int trackNumber;
        public long trackTimeMillis;
        public String country;
        public String currency;
        public String primaryGenreName;
        public boolean isStreamable;
    }
}
