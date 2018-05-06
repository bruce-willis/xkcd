package combruce_willis.github.xkcd.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Comic implements Parcelable {
    public static final Parcelable.Creator<Comic> CREATOR = new Parcelable.Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel source) {
            return new Comic(source);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };
    private int number;
    private String title;
    private String imageUrl;

    Comic(int number, String title, String imageUrl) {
        this.number = number;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    private Comic(Parcel in) {
        this.number = in.readInt();
        this.title = in.readString();
        this.imageUrl = in.readString();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number);
        dest.writeString(this.title);
        dest.writeString(this.imageUrl);
    }
}
