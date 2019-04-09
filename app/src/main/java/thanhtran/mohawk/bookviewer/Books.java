package thanhtran.mohawk.bookviewer;

import java.util.ArrayList;
import java.util.List;

public class Books {
    public String   kind;
    public int      totalItems;
    public ArrayList<Items> items;

    public class Items {
        public String       kind;
        public String       id;
        public String       etag;
        public String       averageRating;
        public String       ratingsCount;
        public VolumeInfo   volumeInfo;

        @Override
        public String toString() {
            return volumeInfo.title + "\n\t\tby: " + volumeInfo.authors.get(0);
        }
    }

    public class VolumeInfo {
        public String               title;
        public ArrayList<String>    authors;
        public String               description;
        public String               publisher;
        public String               publishedDate;
        public String               pageCount;
        public List<String>         imageLinks;
    }

}
