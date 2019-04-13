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
        public VolumeInfo   volumeInfo; // Contains details of the item

        @Override
        // To display in list view
        public String toString() {
            // Used to print formatting to list view
            return volumeInfo.title;
        }
    }

    public class VolumeInfo {
        public String               title;
        public String               subtitle;
        public ArrayList<String>    authors;
        public String               description;
        public String               publisher;
        public String               publishedDate;
        public imageLinks           imageLinks;
    }

    public class imageLinks {
        public String               thumbnail;
    }

}
