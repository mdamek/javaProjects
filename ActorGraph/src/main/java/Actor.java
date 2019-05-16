import java.util.Objects;

public class Actor {
    public String id;
    public String name;

    public Actor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Actor))
            return false;
            Actor actor = (Actor)o;
        return this.id.equals( actor.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name );
    }

    @Override
    public String toString() {
        return name +" (" +id+ ")";
    }
}
