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

        //Getter
        public String getLastUpdated() {
            return lastUpdated;
        }
        public List<BookItem> getUnifiedList() {
            return unifiedList;
        }

        public List<BookItem> getFiction() {
            return fiction;
        }
        public List<BookItem> getNonfiction() {
            return nonfiction;
        }
        //Setter
        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }
        public void setUnifiedList(List<BookItem> unifiedList) {
            this.unifiedList = unifiedList;
        }
        public void setFiction(List<BookItem> fiction) {
            this.fiction = fiction;
        }
        public void setNonfiction(List<BookItem> nonfiction) {
            this.nonfiction = nonfiction;
        }
    }
}
