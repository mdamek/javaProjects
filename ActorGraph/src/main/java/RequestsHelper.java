import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestsHelper {

    public static Actor FetchActor(String actorName) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url( "https://java.kisim.eu.org/actors/search/" + actorName)
                .build();
        Response response = client.newCall( request ).execute();
        String resString = response.body().string();
        JSONArray jsonArray = new JSONArray( resString );
        JSONObject jsonObj = (JSONObject)jsonArray.get( 0 );
        return new Actor( jsonObj.get( "id" ).toString(), jsonObj.get( "name" ).toString() );
    }

    public static ArrayList<Association> FetchActorsFromAllMovies(Actor actor) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url( "https://java.kisim.eu.org/actors/"+actor.id+"/movies")
                .build();
        Response response = client.newCall( request ).execute();
        String resString = response.body().string();
        JSONArray allMovies = new JSONArray( resString );

        ArrayList<Association> actorsAndMovies = new ArrayList<>(  );

        for ( int i = 0; i < allMovies.length(); i++ ){
            JSONObject movieObject = (JSONObject)allMovies.get( i );
            Movie actualMovie = new Movie( movieObject.get( "id" ).toString(), movieObject.get( "title" ).toString() );
            List<Actor> actors = GetActorsForMovie(movieObject.get( "id" ).toString());
            for ( Actor actualActor : actors ) {
                actorsAndMovies.add(
                        new Association(
                        new Actor( actualActor.id, actualActor.name ),
                        new Movie( actualMovie.id, actualMovie.title )) );
            }
        }
        return actorsAndMovies;
    }

    private static List<Actor> GetActorsForMovie(String  movieId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url( "https://java.kisim.eu.org/movies/" + movieId)
                .build();
        Response response = client.newCall( request ).execute();
        String resString = response.body().string();
        JSONObject movieDetails  = new JSONObject( resString );
        JSONArray actorsDetails = (JSONArray) movieDetails.get( "actors" );
        List<Actor> actors = new ArrayList<>(  );
        for ( int i = 0; i < actorsDetails.length(); i++ ){
            JSONObject movieObject = (JSONObject)actorsDetails.get( i );
            actors.add( new Actor( movieObject.get( "id" ).toString(), movieObject.get( "name" ).toString() ) );
        }
        return actors;
    }

}
