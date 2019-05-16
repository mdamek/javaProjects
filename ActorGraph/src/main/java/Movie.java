import java.util.Objects;

public class Movie {
    public String id;
    public String title;

    public Movie(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie))
            return false;
        Movie movie = (Movie)o;
        return this.id.equals( movie.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, title );
    }

    @Override
    public String toString() {
        return title +" (" +id+ ")";
    }
}
