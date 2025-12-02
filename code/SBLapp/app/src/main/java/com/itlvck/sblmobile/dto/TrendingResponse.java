package com.itlvck.sblmobile.dto;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TrendingResponse {
    private Trending trending;

    public void setTrending(Trending trending) {
        this.trending = trending;
    }

    public Trending getTrending() {
        return trending;
    }

    public static class Trending {
        @SerializedName("bestsellers_date")
        private String lastUpdated;

        @SerializedName("unifiedList") // List for the RecyclerView
        private List<BookItem> unifiedList;

        private List<BookItem> fiction;
        private List<BookItem> nonfiction;


        public String getLastUpdated() {
            return lastUpdated;
        }
        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public List<BookItem> getUnifiedList() {
            return unifiedList;
        }
        public void setUnifiedList(List<BookItem> unifiedList) {
            this.unifiedList = unifiedList;
        }
    }
}
