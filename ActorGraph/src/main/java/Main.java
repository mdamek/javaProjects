import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.SimpleGraph;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String firstActorName = "Jason Statham";
        String secondActorName = "Jason Batemanf";
        Actor firstActor = null;
        Actor secondActor = null;
        try {
            firstActor = RequestsHelper.FetchActor( firstActorName );
            secondActor = RequestsHelper.FetchActor( secondActorName );
        }
        catch (IOException e){
            System.out.println( e.getMessage() );
        }
        Graph<Actor, Movie> graph = new SimpleGraph<>( Movie.class);
        List<Association> actorMovieHashMap;
        List<Actor> globalActors = new ArrayList<>(  );
        globalActors.add( firstActor );

        for ( int i = 0; i < globalActors.size(); i++ ){
            Actor actor = globalActors.get( i );
                actorMovieHashMap = RequestsHelper.FetchActorsFromAllMovies(actor);
                graph = FillGraph( graph, actorMovieHashMap, actor);
                if(IsThereActor( actorMovieHashMap, secondActor ))
                    break;
                List<Actor> actors = actorMovieHashMap.stream()
                        .map( element -> new Actor( element.actor.id, element.actor.name ) ).collect( Collectors.toList() );
                globalActors.addAll( actors );
                globalActors.remove( 0 );
        }
        BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(graph);
        GraphPath<Actor, Movie> shortestPath = bfsp.getPath(firstActor, secondActor);
        List<Movie> edges = shortestPath.getEdgeList();
        List<Actor> actors = shortestPath.getVertexList();
        for(int i = 0; i < actors.size(); ++i){
            if(i == actors.size()-1)
                System.out.print(actors.get(i));
            else
                System.out.print(actors.get(i) + " -> " + edges.get(i).toString() + " -> ");
        }
    }

    private static boolean IsThereActor(List<Association> actorsMovies, Actor actorToSearch){
        for ( Association actual : actorsMovies ) {
            if(actual.actor.id.equals( actorToSearch.id ))
                return true;
        }
        return false;
    }

    private static Graph<Actor, Movie> FillGraph(Graph<Actor, Movie> graph, List<Association> actorsMovies, Actor actualActor){
        actorsMovies.removeIf( element -> element.actor.id.equals( actualActor.id ) );
        graph.addVertex( actualActor );
        for ( Association actual : actorsMovies ) {
            graph.addVertex( actual.actor );
            graph.addEdge( actualActor, actual.actor, actual.movie );
        }
        return graph;
    }


}
