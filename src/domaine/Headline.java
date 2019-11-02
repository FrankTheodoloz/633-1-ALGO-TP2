package domaine;

import java.util.Date;
import java.util.Objects;

public class Headline implements Comparable {

    private Date date;
    private String type;
    private String headline;

    public Headline(Date date, String type, String headline) {
        this.date = date;
        this.type = type;
        this.headline = headline;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @Override
    public boolean equals(Object o) {
        // if (this == o) return true;
        // if (o == null || getClass() != o.getClass()) return false;
        Headline headline1 = (Headline) o;
        return Objects.equals(date, headline1.date) &&
                Objects.equals(type, headline1.type) &&
                Objects.equals(headline, headline1.headline);
    }

    @Override
    public int compareTo(Object o) {
        Headline other = (Headline) o;
        int compareText = this.getHeadline().compareToIgnoreCase(other.getHeadline());

        // si les titres sont identiques
        if (compareText == 0) {
            return this.getDate().compareTo(other.getDate());
        }
        return compareText;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, type, headline);
    }

    @Override
    public String toString() {
        return "Headline{" +
                "date=" + date +
                ", type='" + type + '\'' +
                ", headline='" + headline + '\'' +
                '}';
    }

}
